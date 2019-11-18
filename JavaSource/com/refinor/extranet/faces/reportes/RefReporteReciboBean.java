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

public class RefReporteReciboBean extends AbstListado {

	public Boolean estadoRecibo;
	public RefReporteReciboBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean getPuedeIngresar() {		
		//System.out.println("Verificando permiso para ver la pagina de recibos");			
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}	
	
	@Override
	public void generarExcel(){
		try{			
			//System.out.println("Paso por generar excel de recibos !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();			
				this.nombreArchivo = exportarExcelBean.generarExcelReporteRecibo(getItems(),getFechaActualStr(),getTipoUsuarioLogueado(),estadoRecibo);	
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
			
			if(getTipoUsuarioLogueado()==1){
				//es cliente
				setItems(mReciboDAO.getRecibosPorFecha(codCliente,fltFechaDesde,fltFechaHasta,fltNroReciboDesde,fltNroReciboHasta,estadoRecibo));
				
			}else if(getTipoUsuarioLogueado()==0){
				//es refipass
				String codClienteAlfa=null;
				if(this.cliente!=null && !this.cliente.equals(new Integer(-1)))
					codClienteAlfa = obtenerCodClienteAlfa(this.cliente);

				setItems(mReciboDAO.getRecibosPorFecha(codClienteAlfa,fltFechaDesde,fltFechaHasta,fltNroReciboDesde,fltNroReciboHasta,estadoRecibo));

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
	
	public void verRemitos(ActionEvent event){
		try{
			mostrarFrmListaSecundaria= new Boolean(true);
			mostrarListaSecundaria= new Boolean(true);
			mostrarFrmLista= new Boolean(false);
			mostrarLista= new Boolean(false);	
			
			UIParameter component = (UIParameter) event.getComponent().findComponent("codCliente");
			Integer codCliente = new Integer(component.getValue().toString()).intValue();
			
			UIParameter component1 = (UIParameter) event.getComponent().findComponent("nroOperCaja");
			Integer nroOperCaja = new Integer(component1.getValue().toString()).intValue();		  			   
					
			MfacturasVDAO mFacturasVDAO = new MfacturasVDAO(getSessionHib());
			setItems2(mFacturasVDAO.getFacturasPorReciboCliente(codCliente,nroOperCaja));
			
			setSubItemsNivel2(getItems2());			

			this.paginaSecundaria = new Page(getSubItemsNivel2(),1,tamanioPaginacion);
			setSubItemsNivel2(paginaSecundaria.getPage());
			generarExcelSecundario();		
			
			mostrarFrmListaSecundaria= new Boolean(true);
			mostrarListaSecundaria= new Boolean(true);
			mostrarFrmLista= new Boolean(false);
			mostrarLista= new Boolean(false);		
		}catch(DataAccessErrorException ex){
			mostrarListaSecundaria= new Boolean(false);
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}catch(NoExistenItemsException ex){
			mostrarListaSecundaria= new Boolean(false);
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}		
	}
	
	public void volverPrincipal(ActionEvent event){
		mostrarFrmListaSecundaria= new Boolean(false);
		mostrarListaSecundaria= new Boolean(false);
		mostrarFrmLista= new Boolean(true);
		mostrarLista= new Boolean(true);		
	}
	
	public void generarExcelSecundario() {
		try{			
			//System.out.println("Paso por generar excel de remitos !!");			
			ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();			
			int codListado=2;
			this.nombreArchivoSecundario = exportarExcelBean.generarExcelReporteFacturas(getItems2(),getFechaActualStr(),getTipoUsuarioLogueado(),codListado);	
		}catch(IOException ex){
			ex.printStackTrace();
			this.nombreArchivoSecundario = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
			this.nombreArchivoSecundario = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}

	}

	public Boolean getEstadoRecibo() {
		return estadoRecibo;
	}

	public void setEstadoRecibo(Boolean estadoRecibo) {
		this.estadoRecibo = estadoRecibo;
	}
	

}
