package com.refinor.extranet.faces.reportes;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.itsolver.util.io.FileUtil;

import com.refinor.extranet.data.dao.MccssDAO;

import com.refinor.extranet.data.dao.MovimientoStockDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.ArchivoTO;
import com.refinor.extranet.to.MEmpleadosTO;

import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class RefReporteMovimientoStock extends AbstListado {

	public RefReporteMovimientoStock() {		
		cargarComboCCSSSinHeadOffice();		
	}
	
	/**
	 * Metodo:cargarComboCCSS
	 * Funcion: cargar el combo de centros de servicios sin el head office
	 *
	 */
	private void cargarComboCCSSSinHeadOffice(){
		 MccssDAO mCcssDAO = new MccssDAO(sessionHib);
		 lstccss = new ArrayList<SelectItem>();
		 lstccss.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
		try {
			MEmpleadosTO  mCcss;
			List lstDatos = mCcssDAO.getCCSSRegistrados();			
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mCcss = (MEmpleadosTO)it.next();
				if(mCcss.getCodigo()!=Const.COD_CCSS_HEAD_OFICCE){
				   lstccss.add(new SelectItem(new Integer(mCcss.getCodigo()),mCcss.getDescripcion()));
				}
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los ccss.");
		}	
		
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
				this.nombreArchivo = exportarExcelBean.generarExcelReporteMovimientoStock2(getItems(),"sil",getFechaActualStr());	
				
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
			MovimientoStockDAO movimientoDAO= new MovimientoStockDAO(sessionHib);
			setItems(movimientoDAO.getMovimientoStock(this.producto,fltFechaDesde,fltFechaHasta,ccss));						
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
	
	
	
	public void buscarMejorado(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		try{		
			setMensaje2000Registros("");
			MovimientoStockDAO movimientoDAO= new MovimientoStockDAO(sessionHib);	
			String achivo = mensajeria.getMessage().getString("info_movimiento_stock")+"_"+getFechaActualStr()+".xls";	
			setItems(movimientoDAO.getMovimientoStockMejorado(producto, fltFechaDesde, fltFechaHasta, ccss,achivo));
			setSubItemsNivel1(getItems());			
			cargarPaginaMejorado();
			FileUtil fileUtil= new FileUtil();
			Properties props= fileUtil.getPropertiesFile();	
			this.nombreArchivo=	getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+props.getProperty("loadArchivosExcel")+achivo;
			
			if(getLstArchivos()==null){
				setLstArchivos(new ArrayList());				
			}
			
			ArchivoTO archivo= new ArchivoTO();
			archivo.setPathFisico(props.getProperty("saveArchivosExcel")+achivo);
			getLstArchivos().add(archivo);				
				
			if(getItems()!=null && getItems().size()==2000){
			  setMensaje2000Registros(mensajeria.getMessage().getString("mensaje_limite_registros_label"));
			}
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
	public Boolean getPuedeIngresar() {
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}

	
}
