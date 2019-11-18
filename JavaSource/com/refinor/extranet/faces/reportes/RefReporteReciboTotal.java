package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import java.util.Collections;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;


import com.refinor.extranet.data.dao.MreciboDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.comparator.ReciboTOComparator;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class RefReporteReciboTotal extends AbstListado{

	public Boolean estadoRecibo;
	
	public Boolean getEstadoRecibo() {
		return estadoRecibo;
	}

	public void setEstadoRecibo(Boolean estadoRecibo) {
		this.estadoRecibo = estadoRecibo;
	}

	public RefReporteReciboTotal() {
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
				this.nombreArchivo = exportarExcelBean.generarExcelReporteReciboTotal(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());	
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

			setItems(mReciboDAO.getRecibosPorFechaNroReciboCliente(fltFechaDesde,fltFechaHasta,fltClienteDesde,fltClienteHasta,fltNroReciboDesde,fltNroReciboHasta,estadoRecibo));
			Collections.sort(getItems(), new ReciboTOComparator());
			
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
