package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.Mvehiculo;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.McombustibleDespachoDAO;
import com.refinor.extranet.data.dao.MfacturasVDAO;
import com.refinor.extranet.data.dao.MlimiteCargaDAO;
import com.refinor.extranet.data.dao.MvehiculoDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.MEmpleadosTO;
import com.refinor.extranet.to.MFacturaVTO;
import com.refinor.extranet.to.RendicionPendienteTO;
import com.refinor.extranet.to.RendicionTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.DatosObligatoriosException;
import com.refinor.extranet.util.exception.NoExistenItemsException;



public class RefReporteListaDetallesRendicionesBean extends AbstListado {

	private Integer rendicion;	
	private List<SelectItem> rendiciones;
	private BigDecimal total;
	public BigDecimal getTotal() {
		return total;
	}



	public void setTotal(BigDecimal total) {
		this.total = total;
	}



	public RefReporteListaDetallesRendicionesBean() {
				
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
	
	@Override
	public Boolean getPuedeIngresar() {		
		System.out.println("Verificando permiso para ver la pagina de detalle de rendiciones");			
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
				this.nombreArchivo = exportarExcelBean.generarExcelReporteDetallesRendicion(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());	
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
			MfacturasVDAO mFacturasVDAO = new MfacturasVDAO(getSessionHib());	
			
			/*if(ccss==-1 || ccss==null ||
					rendicion==-1 || rendicion==null 	){
				throw new DatosObligatoriosException();
			}else{*/
				setItems(mFacturasVDAO.getFacturasPorCCSSyNroRendicion(ccss,rendicion));
			//}
				
			total= obtenerTotal(getItems());	
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
	
	private BigDecimal obtenerTotal(List item){
		BigDecimal total=new BigDecimal(0.00);
		try{
			
			Iterator it= item.iterator();
			MFacturaVTO mFacturaVTO=null;
			while(it.hasNext()){
				mFacturaVTO= new MFacturaVTO();
				mFacturaVTO=(MFacturaVTO)it.next();
				total= total.add(mFacturaVTO.getPreciototal());
			}		
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(new DataAccessErrorException().getMessage());			
		}
		return total;
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
