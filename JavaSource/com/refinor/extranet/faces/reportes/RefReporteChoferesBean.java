package com.refinor.extranet.faces.reportes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.event.ActionEvent;

import org.hibernate.Session;
import org.hibernate.exception.DataException;

import com.itsolver.util.io.FileUtil;
import com.refinor.extranet.data.Almacen;
import com.refinor.extranet.data.AlmacenId;
import com.refinor.extranet.data.Mchofer;
import com.refinor.extranet.data.dao.AlmacenDAO;
import com.refinor.extranet.data.dao.MchoferDAO;
import com.refinor.extranet.data.dao._RootDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.base.AbstReporte;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.paginado.Page;

public class RefReporteChoferesBean extends AbstListado{
	

	public RefReporteChoferesBean() {
		
	}

	
	
	@Override
	public Boolean getPuedeIngresar() {		
		System.out.println("Verificando permiso para ver la pagina de choferes");			
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}	
	
	@Override
	public void generarExcel(){
		try{			
			System.out.println("Paso por generar excel de choferes !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();			
				this.nombreArchivo = exportarExcelBean.generarExcelReporteChofer(getItems(),getFechaActualStr(),choferActivo,getTipoUsuarioLogueado());
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
			MchoferDAO mChoferDAO = new MchoferDAO(getSessionHib());		
			
			if(estado.equals(new Integer(-1))){
				choferActivo=new Boolean(true);
				choferBaja=new Boolean(false);
			}else if(estado.equals(new Integer(1))){
				choferActivo=new Boolean(true);
				choferBaja=new Boolean(true);
			}else if(estado.equals(new Integer(0))){
				choferActivo=new Boolean(false);
				choferBaja=new Boolean(false);
			}
			
			if(estadoIni.equals(new Integer(-1))){
				choferInicializado=new Boolean(true);
				choferNoInicializado=new Boolean(false);
			}else if(estadoIni.equals(new Integer(1))){
				choferInicializado=new Boolean(true);
				choferNoInicializado=new Boolean(true);
			}else if(estadoIni.equals(new Integer(0))){
				choferInicializado=new Boolean(false);
				choferNoInicializado=new Boolean(false);
			}
			
			if(getTipoUsuarioLogueado()==1){
				//es cliente
				setItems(mChoferDAO.getChoferesPorNombreGrupoUNUnidadNegocio(codCliente,fltNombreChofer,getUnidadNegocio(),fltDescripcionUnidadNegocio,getGrupoUnidadNegocio(),fltDescripcionGrupoUN,fltDNI,choferActivo,choferBaja,choferInicializado,choferNoInicializado));
			}else if(getTipoUsuarioLogueado()==0){
				//es refipass
				String codClienteAlfa=null;
				if(this.cliente!=null && !this.cliente.equals(new Integer(-1)))
					codClienteAlfa = obtenerCodClienteAlfa(this.cliente);
				
				setItems(mChoferDAO.getChoferesPorNombreGrupoUNUnidadNegocio(codClienteAlfa,fltNombreChofer,getUnidadNegocio(), fltDescripcionUnidadNegocio,getGrupoUnidadNegocio(), fltDescripcionGrupoUN,fltDNI,choferActivo,choferBaja,choferInicializado,choferNoInicializado));
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
