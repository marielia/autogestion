
package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.refinor.extranet.data.dao.MasientoContableDAO;
import com.refinor.extranet.data.dao.MfacturasVDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.paginado.Page;

public class RefReporteMayorContableBean extends AbstListado {

	public RefReporteMayorContableBean() {
		// TODO Auto-generated constructor stub
		//this.nombreArchivo="";
	}

	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception{
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);
		
	}

	@Override
	public void generarExcel() {
		try{			
			//System.out.println("Paso por generar excel  !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();		
				this.nombreArchivo = exportarExcelBean.generarExcelReporteMayorContableVer1_01(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());	
				
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
	
	
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		try{	
			//this.nombreArchivo="";
			MasientoContableDAO mAsientoContableDAO = new MasientoContableDAO(getSessionHib());				
			setItems(mAsientoContableDAO.getMayorContable2(this.fltCuentaDesde,this.fltCuentaHasta,fltFechaDesde,fltFechaHasta,ccss));	
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
	
	
/*	public void cargarPagina(){
		//PAGINACION		
		this.pagina = new Page(getSubItemsNivel1(),1,tamanioPaginacion);
		setSubItemsNivel1(pagina.getPage());
		//generarExcel();
		//PAGINACION
	}*/
	
	public Boolean getPuedeIngresar() {
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}

}
