package com.refinor.extranet.faces.reportes;

import java.awt.SystemTray;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import com.refinor.extranet.data.dao.MpedidosDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class RefReporteRemitosCompletoBean extends AbstListado {
	

	public Boolean verPrecioCimp;
	
	

	
	public Boolean getVerPrecioCimp() {
		return verPrecioCimp;
	}


	public void setVerPrecioCimp(Boolean verPrecioCimp) {
		this.verPrecioCimp = verPrecioCimp;
	}

	public void cambiarEstado(ValueChangeEvent event) { 
		   boolean flag = ((Boolean)event.getNewValue()).booleanValue(); 		  
		   setVerPrecioCimp(flag);
	} 
	

	public RefReporteRemitosCompletoBean() {
		// TODO Auto-generated constructor stub
		nombreArchivo= "";
		cargarOpcionesRefacturacion();		
		FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
		HttpServletResponse response =
			(HttpServletResponse)context.getExternalContext().getResponse();			
		response.setHeader("Cache-Control", "no-cache");
	}

	
	private void cargarOpcionesRefacturacion(){
		optRefacturaciones = new ArrayList<SelectItem>();
		//optRefacturaciones.add(new SelectItem(new Integer(4),"Ver Todos"));
		optRefacturaciones.add(new SelectItem(new Integer(1),"Ver Remitos Anulados No Refacturados"));
		optRefacturaciones.add(new SelectItem(new Integer(2),"Ver Remitos Refacturados"));	
		optRefacturaciones.add(new SelectItem(new Integer(3),"Ver Remitos Anulados No Refacturados y Remitos Refacturados"));	
		optRefacturacion=new Integer(1);
	}
	@Override
	public Boolean getPuedeIngresar() {		
		System.out.println("Verificando permiso para ver la pagina de remitos");			
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}	
	
	public String generarExcelNuevoRef(ActionEvent evetnt) {
		try{			
			System.out.println("Paso por generar excel !!");
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();				
				this.nombreArchivo = exportarExcelBean.generarExcelReporteRemitoCompletoVer1_01(getItems(),getFechaActualStr(),getTipoUsuarioLogueado(),getCodCliente(),"ref",getVerPrecioCimp());	
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
		
		return this.nombreArchivo;

	}
	
	public String generarExcelNuevo(ActionEvent evetnt) {
		try{			
			System.out.println("Paso por generar excel !!");
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();				
				this.nombreArchivo = exportarExcelBean.generarExcelReporteRemitoCompletoVer1_01(getItems(),getFechaActualStr(),getTipoUsuarioLogueado(),getCodCliente(),"noref",getVerPrecioCimp());
				exportarExcelBean=null;
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
		
		return this.nombreArchivo;

	}
	
	@Override
	public void generarExcel(){
		try{			
			//System.out.println("Paso por generar excel de recibos !!");	
			//if(getItems()!=null && getItems().size()!=0){
				//ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();
				//this.nombreArchivo = exportarExcelBean.generarExcelReporteRemitoCompletoVer1_01(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());	
			//}
		}/*catch(IOException ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}*/catch(Exception ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}
	}
	
	public void borrarArchivo(String ruta){
		File miArchivo = new File(ruta);		
		if (miArchivo.exists())  
		{  
			if (miArchivo.delete())  
			   {       /* Archivo borrado con éxito            */      }  
			else  
			   {       /* El archivo no se ha podido borrar    */      }  
 
		}
	}


	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception{
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);
		
	}
	
	
	
	public void buscarRefacturados(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{		
		try{	
			this.nombreArchivo="";
			MpedidosDAO mPedidoDAO = new MpedidosDAO(getSessionHib());		
		
			setItems(mPedidoDAO.getRemitosAnuladosRefacturadosYNoRefacturados(fltFechaDesde,fltFechaHasta,fltClienteDesde,fltClienteHasta,fltNroReciboDesde,fltNroReciboHasta,condicion,estadoRemito,fltNroSucursal,fltFechaDesdeDos,fltFechaHastaDos,ccss,optRefacturacion));
			//Collections.sort(getItems(), new RemitoTOComparator());
			
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
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{		
		try{	
			this.nombreArchivo="";
			MpedidosDAO mPedidoDAO = new MpedidosDAO(getSessionHib()); 
			
			if(getTipoUsuarioLogueado()==1){
				//es cliente
				fltClienteDesde=codCliente;
				fltClienteHasta=codCliente;
				System.out.println("remitos todos por cliente ->" + fltClienteDesde +" "+ fltClienteHasta);
				setItems(mPedidoDAO.getRemitosFactutadosyNoFacturados(fltFechaDesde,fltFechaHasta,fltClienteDesde,fltClienteHasta,fltNroReciboDesde,fltNroReciboHasta,condicion,estadoRemito,fltNroSucursal,fltFechaDesdeDos,fltFechaHastaDos,ccss,optRefacturacion,getVerPrecioCimp()));
				
			}else if(getTipoUsuarioLogueado()==0){
				//es refipass  
				setItems(mPedidoDAO.getRemitosFactutadosyNoFacturados(fltFechaDesde,fltFechaHasta,fltClienteDesde,fltClienteHasta,fltNroReciboDesde,fltNroReciboHasta,condicion,estadoRemito,fltNroSucursal,fltFechaDesdeDos,fltFechaHastaDos,ccss,optRefacturacion,getVerPrecioCimp()));
  			}
			
			mPedidoDAO=null;			
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
