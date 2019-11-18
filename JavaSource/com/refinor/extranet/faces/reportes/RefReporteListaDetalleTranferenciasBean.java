package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.refinor.extranet.data.Mccss;
import com.refinor.extranet.data.dao.MccssDAO;
import com.refinor.extranet.data.dao.McuentasBancosDAO;
import com.refinor.extranet.data.dao.MfacturasVDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.CcssRendicionTransferenciaTO;
import com.refinor.extranet.to.MCuentaBancoTO;
import com.refinor.extranet.to.RendicionTO;
import com.refinor.extranet.to.RendicionTranfTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class RefReporteListaDetalleTranferenciasBean extends AbstListado {
	private Integer rendicion;	
	private List<SelectItem> rendiciones;
	
	/**
	 * 
	 */
	public RefReporteListaDetalleTranferenciasBean() {
		if(rendiciones==null){
			rendiciones = new ArrayList<SelectItem>();
			rendiciones.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		}
	}

	
	public void cargarSusRendiciones(ValueChangeEvent event){
		try{
		     Integer codCCSS = ((Integer)event.getNewValue()).intValue();
		     rendiciones = new ArrayList<SelectItem>();
		     rendiciones.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
			 
			 if(codCCSS!=-1 && codCCSS!=null){
				
				 MfacturasVDAO mFacturasVDAO= new MfacturasVDAO(sessionHib);
				 RendicionTO rendicionTO = new RendicionTO();
				 List lstDatos = mFacturasVDAO.getRendicionesPorCCSS(codCCSS);		
				 Iterator it = lstDatos.iterator();			
					
					while(it.hasNext()) {
						rendicionTO = (RendicionTO)it.next();
						rendiciones.add(new SelectItem(new Integer(rendicionTO.getCodRendicion()),rendicionTO.getDescRendicion()));
					}	 
				
			 }
		}
		catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage("Este CCSS no posee rendiciones.");	
			
		}
		noMostrarLista();
	}
	
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{		
		try{	
		    setItems(obtenerListaCCSSConSusRendicionesTransferencias());
			
			setSubItemsNivel1(getItems());			
			cargarPagina();					
			mostrarLista=new Boolean(true);
			mostrarFrmLista=new Boolean(true);
		}
		/*catch(DatosObligatoriosException ex){
			ex.printStackTrace();
			AddErrorMessage(mensajeria.getMessage().getString("datos_onligatorios_label"));
			noMostrarLista();			
		}*/
		/*catch(NoExistenItemsException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();			
		}*/catch(DataAccessErrorException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}
	}
	
	
	public List obtenerListaCCSSConSusRendicionesTransferencias() throws DataAccessErrorException{
		 List<CcssRendicionTransferenciaTO> lstCcssRendicionesTransferencias = new ArrayList();
		try{
		
		  McuentasBancosDAO mccuentaBco =  new McuentasBancosDAO(getSessionHib());
		 MccssDAO mccssDAO =  new MccssDAO(getSessionHib());
		 List lstCcss = mccssDAO.getCCSS(ccss);
		 List<RendicionTranfTO> lstRendiciones=null;
		 Iterator itCcss = lstCcss.iterator();
		 Mccss ccss= new Mccss();
		 CcssRendicionTransferenciaTO CcssRendTransfTO= new CcssRendicionTransferenciaTO();
		 
		 
		 while(itCcss.hasNext()){
			 ccss = ( Mccss )itCcss.next();
			 try{
			 lstRendiciones = mccuentaBco.getRendicionesYSusTranferencias(ccss.getCodCcss(),rendicion);
			 }catch(Exception ex){
				 ex.printStackTrace();
				 lstRendiciones=null;
			 }
			 CcssRendTransfTO = new CcssRendicionTransferenciaTO();
			 CcssRendTransfTO.setCcss(ccss);
			 CcssRendTransfTO.setRendiciones(lstRendiciones);
			 CcssRendTransfTO.setMensajeRendicion(lstRendiciones==null?"El CCSS no tiene rendiciones.":"");
			 lstCcssRendicionesTransferencias.add(CcssRendTransfTO);		 
			 
		 }
		 
		 
		 
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
		return lstCcssRendicionesTransferencias ;
	}
	@Override
	public void cargcarListaPotFiltros() throws NoExistenItemsException,
			DataAccessErrorException, Exception {
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);

	}

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
	
	@Override
	public Boolean getPuedeIngresar() {		
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}


	public Integer getRendicion() {
		return rendicion;
	}


	public void setRendicion(Integer rendicion) {
		this.rendicion = rendicion;
	}


	public List<SelectItem> getRendiciones() {
		return rendiciones;
	}


	public void setRendiciones(List<SelectItem> rendiciones) {
		this.rendiciones = rendiciones;
	}

}
