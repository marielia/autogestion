package com.refinor.extranet.faces.reportes;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.faces.event.ActionEvent;

import com.itsolver.util.io.FileUtil;
import com.refinor.extranet.data.Mvehiculo;
import com.refinor.extranet.data.dao.AlmacenDAO;
import com.refinor.extranet.data.dao.MchoferDAO;
import com.refinor.extranet.data.dao.MvehiculoDAO;
import com.refinor.extranet.data.dao._RootDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.base.AbstReporte;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class RefReporteVehiculosBean extends AbstListado {

	public RefReporteVehiculosBean() {
		
	}

	@Override
	public Boolean getPuedeIngresar() {	
		System.out.println("Verificando permiso para ver la pagina de vehiculos");	
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}

	@Override
	public void generarExcel(){
		try{			
			System.out.println("Paso por generar excel de vehiculos !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();			
				this.nombreArchivo = exportarExcelBean.generarExcelReporteVehiculos(getItems(),getFechaActualStr(),vehiculoActivo,getTipoUsuarioLogueado());	
			}
		}catch(IOException ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}
	}


	@Override
	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception{
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);
		
	}
	
	
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		
	try{			
		MvehiculoDAO mVehiculoDAO = new MvehiculoDAO(getSessionHib());	
		
		
		if(estado.equals(new Integer(-1))){
			vehiculoActivo=new Boolean(true);
			vehiculoBaja=new Boolean(false);
		}else if(estado.equals(new Integer(1))){
			vehiculoActivo=new Boolean(true);
			vehiculoBaja=new Boolean(true);
		}else if(estado.equals(new Integer(0))){
			vehiculoActivo=new Boolean(false);
			vehiculoBaja=new Boolean(false);
		}
		
		if(estadoIni.equals(new Integer(-1))){
			vehiculoInicializado=new Boolean(true);
			vehiculoNoInicializado=new Boolean(false);
		}else if(estadoIni.equals(new Integer(1))){
			vehiculoInicializado=new Boolean(true);
			vehiculoNoInicializado=new Boolean(true);
		}else if(estadoIni.equals(new Integer(0))){
			vehiculoInicializado=new Boolean(false);
			vehiculoNoInicializado=new Boolean(false);
		}
		
		
		if(getTipoUsuarioLogueado()==1){
			//es cliente			
			setItems(mVehiculoDAO.getVehiculoPorClienteUnidadNegocioPatenteEstado(codCliente,getUnidadNegocio(),fltDescripcionUnidadNegocio,getGrupoUnidadNegocio(), fltPatente,vehiculoActivo,vehiculoBaja,vehiculoInicializado,vehiculoNoInicializado));
			
		}else if(getTipoUsuarioLogueado()==0){
			//es refipass
			String codClienteAlfa=null;
			if(this.cliente!=null && !this.cliente.equals(new Integer(-1)))
				codClienteAlfa = obtenerCodClienteAlfa(this.cliente);
			
			setItems(mVehiculoDAO.getVehiculoPorClienteUnidadNegocioPatenteEstado(codClienteAlfa,getUnidadNegocio(),fltDescripcionUnidadNegocio,getGrupoUnidadNegocio(), fltPatente,vehiculoActivo,vehiculoBaja,vehiculoInicializado,vehiculoNoInicializado));

		}
		
		setSubItemsNivel1(getItems());		
		cargarPagina();					
		mostrarLista=new Boolean(true);
		mostrarFrmLista=new Boolean(true);
	}
	catch(NoExistenItemsException ex){
		ex.printStackTrace();
		AddErrorMessage(ex.getMessage());
		noMostrarLista();
		
	}catch(DataAccessErrorException ex){
		ex.printStackTrace();
		AddErrorMessage(ex.getMessage());
		noMostrarLista();
	}catch(Exception ex){
		ex.printStackTrace();
		AddErrorMessage(ex.getMessage());
		noMostrarLista();
	}
	}
	
	
}
