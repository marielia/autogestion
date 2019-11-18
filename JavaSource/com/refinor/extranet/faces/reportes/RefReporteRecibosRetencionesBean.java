package com.refinor.extranet.faces.reportes;

import java.io.IOException;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.refinor.extranet.data.dao.MfacturasVDAO;
import com.refinor.extranet.data.dao.MreciboDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.paginado.Page;

public class RefReporteRecibosRetencionesBean extends AbstListado {
	public Boolean estadoRecibo;
	public Boolean getEstadoRecibo() {
		return estadoRecibo;
	}

	public void setEstadoRecibo(Boolean estadoRecibo) {
		this.estadoRecibo = estadoRecibo;
	}

	public RefReporteRecibosRetencionesBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean getPuedeIngresar() {		
		System.out.println("Verificando permiso para ver la pagina de recibos");			
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}	
	
	@Override
	public void generarExcel(){
		try{			
			System.out.println("Paso por generar excel de recibos !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();			
				this.nombreArchivo = exportarExcelBean.generarExcelReporteReciboConRetenciones(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());	
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


	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception{
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);
		
	}
	
	public void cambiarEstado(ValueChangeEvent event) { 
		   boolean flag = ((Boolean)event.getNewValue()).booleanValue(); 		  
		   setEstadoRecibo(flag);
		} 

	
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{		
		try{	
			MreciboDAO mReciboDAO = new MreciboDAO(getSessionHib());		

			setItems(mReciboDAO.getRecibosConRetenciones(fltFechaDesde,fltFechaHasta,fltClienteDesde,fltClienteHasta,retencion,estadoRecibo));
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
