package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import javax.faces.event.ActionEvent;
import com.refinor.extranet.data.dao.MpedidosDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class RefReporteImpuestoLeyCordoba extends AbstListado {

	private String nombreArchivoTxt;
	
	
	public RefReporteImpuestoLeyCordoba(){
		super();		
	}
	
	/**
	 * Nombre: buscar
	 * Funcion: Genero la vista previa del libro diario
	 */
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		try{			
			
			MpedidosDAO  mPedidosDAO = new MpedidosDAO(sessionHib);
			setItems(mPedidosDAO.listarImpuestoLeyVialCba(this.producto,fltFechaDesde,fltFechaHasta,cliente,fltNroSucursal,ccss, estadoRemito));	
		     
				 
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
	
	@Override
	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException, Exception {
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);
		
	}

	@Override
	public void generarExcel() {
		try{			
			//System.out.println("Paso por generar excel  !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();		
				this.nombreArchivo = exportarExcelBean.generarExcelReporteImpuestosLeyCb(getItems(),getFechaActualStr());	
				
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

	public Boolean getPuedeIngresar() {
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}

}
