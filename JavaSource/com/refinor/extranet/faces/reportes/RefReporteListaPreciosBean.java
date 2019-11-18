package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.refinor.extranet.data.MlistaPrecios;
import com.refinor.extranet.data.TipoComprobante;
import com.refinor.extranet.data.dao.McombustibleDespachoDAO;
import com.refinor.extranet.data.dao.MfacturasVDAO;
import com.refinor.extranet.data.dao.MlistaPreciosDAO;
import com.refinor.extranet.data.dao.TipoComprobanteDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.RendicionTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class RefReporteListaPreciosBean extends AbstListado{

	protected Integer listaPrecio;	
	protected List<SelectItem> lstListaPrecio;	
	
	
	public RefReporteListaPreciosBean() {
		
		try{
			cargarListaPrecio(ccss,cliente);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fecha = sdf.format(new Date());
			fltFechaDesde=sdf.parse(fecha);
		}catch(Exception e){
			e.printStackTrace();
			AddErrorMessage(new DataAccessErrorException().getMessage());			
		}
	}
	
	
	
	public void cargarListaPreciosPorCCSS(ValueChangeEvent event){	
		try {
			 Integer nroCcss = ((Integer)event.getNewValue()).intValue(); 
			 cargarListaPrecio(nroCcss,cliente);
			
		}catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar las listas de precios.");
		}	
		
	}
	
	public void cargarListaPreciosPorCliente(ValueChangeEvent event){	
		try {
			 Integer cliente = ((Integer)event.getNewValue()).intValue(); 
			 cargarListaPrecio(ccss,cliente);
			
		}catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar las listas de precios.");
		}	
		
	}
	

	public void cargarListaPrecio(Integer ccss,Integer cliente){		 
		 MlistaPreciosDAO mlistaPrecioDAO = new MlistaPreciosDAO(getSessionHib());
		 lstListaPrecio = new ArrayList<SelectItem>();
				 
		try {
			MlistaPrecios mlistaPrecios;
			List lstDatos = mlistaPrecioDAO.getListaPreciosActivasPorCCSS(ccss,cliente);		
			Iterator it = lstDatos.iterator();	
			lstListaPrecio.add(new SelectItem(new Integer(-1),Const.SELECCIONE));		
			
			while(it.hasNext()) {
				mlistaPrecios = (MlistaPrecios)it.next();
				lstListaPrecio.add(new SelectItem(new Integer(mlistaPrecios.getCodigo()),mlistaPrecios.getDescripcion()));
			}					
			
			
		}catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar las listas de precios.");
		}	
	}

	@Override
	public Boolean getPuedeIngresar() {		
		System.out.println("Verificando permiso para ver la pagina de combustible");			
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}	
	
	@Override
	public void generarExcel(){
		try{			
			System.out.println("Paso por generar excel de lista de precios !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();			
				this.nombreArchivo = exportarExcelBean.generarExcelReporteListaPrecios(getItems(),getFechaActualStr(),getTipoUsuarioLogueado(),getFechaActualStr());	
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
	
	
	
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{		
		try{	
		    MlistaPreciosDAO mlistaPrecioDAO = new MlistaPreciosDAO(getSessionHib());
			setItems(mlistaPrecioDAO.getListaPreciosPorFiltro(ccss,producto, fltFechaDesde,fltFechaHasta,listaPrecio,cliente));
						
			setSubItemsNivel1(getItems());		
			cargarPagina();					
			mostrarLista=new Boolean(true);
			mostrarFrmLista=new Boolean(true);
		}
		/*catch(NoExistenItemsException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
			
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}*/catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}
	}

	public Integer getListaPrecio() {
		return listaPrecio;
	}

	public void setListaPrecio(Integer listaPrecio) {
		this.listaPrecio = listaPrecio;
	}

	public List<SelectItem> getLstListaPrecio() {
		return lstListaPrecio;
	}

	public void setLstListaPrecio(List<SelectItem> lstListaPrecio) {
		this.lstListaPrecio = lstListaPrecio;
	}

}
