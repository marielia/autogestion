package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import com.refinor.extranet.data.dao.MctasCtesDAO;
import com.refinor.extranet.data.dao.MfacturasVDAO;
import com.refinor.extranet.data.dao.MpedidosDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.RendicionPendienteTO;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.paginado.Page;

public class RefReporteRendicionPendienteBean extends AbstListado {

	
	public RefReporteRendicionPendienteBean() {
			
					
			try{
				MfacturasVDAO mFacturaVDAO = new MfacturasVDAO(getSessionHib());		
				setItems(mFacturaVDAO.getRendicionesPendientesPorCCSS());
				
				setSubItemsNivel1(getItems());		
				cargarPagina();					
				cargcarListaPotFiltros();
			}catch(NoExistenItemsException ex){
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

	
	public void verTodosLosDatos(ActionEvent event){
			try{
				mostrarFrmListaSecundaria= new Boolean(true);
				mostrarListaSecundaria= new Boolean(true);
				mostrarFrmLista= new Boolean(false);
				mostrarLista= new Boolean(false);	
				
				UIParameter component = (UIParameter) event.getComponent().findComponent("codCCSSparam");
				Integer codCentroServicio = new Integer(component.getValue().toString()).intValue();				
				
				MfacturasVDAO mFacturasVDAO = new MfacturasVDAO(getSessionHib());			
				
				System.out.println("codCentroServicio-> "+codCentroServicio);
				setItems2(mFacturasVDAO.getFacturaPendienteDeRendicion( codCentroServicio ));				
				
				setSubItemsNivel2(getItems2());			

				this.paginaSecundaria = new Page(getSubItemsNivel2(),1,tamanioPaginacion);
				setSubItemsNivel2(paginaSecundaria.getPage());
				generarExcelSecundario();			
						
			}catch(DataAccessErrorException ex){				
				cargcarListaPotFiltros();
				ex.printStackTrace();
				AddErrorMessage(ex.getMessage());
			}catch(NoExistenItemsException ex){
				cargcarListaPotFiltros();
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
	
	public void cargcarListaPotFiltros() {
		mostrarLista=new Boolean(true);
		mostrarFrmLista=new Boolean(true);
		mostrarListaSecundaria= new Boolean(false);
	}
	
	@Override
	public Boolean getPuedeIngresar() {		
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}

	@Override
	public void generarExcel() {
		try{			
			System.out.println("Paso por generar excel !!");
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();			
				this.nombreArchivo = exportarExcelBean.generarExcelReporteRendicionesPendientes(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());
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
	
	
	public void generarExcelSecundario() {
		try{				
			ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();	
			int codListado=1;
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


}
