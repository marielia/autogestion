package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.event.ActionEvent;

import com.refinor.extranet.data.dao.MexclusionDAO;
import com.refinor.extranet.data.dao.MfacturasVDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class RefReporteExclusionPorClienteBean extends AbstListado {
	private BigDecimal porcentajeExclusion;
	

	public RefReporteExclusionPorClienteBean() {
		try{
			cargarComboTipoImpuestos();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fecha = sdf.format(new Date());
			fltFechaDesde=sdf.parse(fecha);
		}catch(Exception e){
			e.printStackTrace();
			AddErrorMessage(new DataAccessErrorException().getMessage());			
		}
	}

	
	public Boolean getPuedeIngresar() {		
		System.out.println("Verificando permiso para ver la pagina de exclusion");			
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}	
	
	@Override
	public void generarExcel(){
		try{			
			System.out.println("Paso por generar excel de exclusion !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();			
				this.nombreArchivo = exportarExcelBean.generarExcelReporteExclusion(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());	
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
			MexclusionDAO mExclusionDAO = new MexclusionDAO(getSessionHib());
			setItems(mExclusionDAO.getExclusiones(fltFechaDesde,fltFechaHasta,cliente,provincia,porcentajeExclusion,tipoExclusion));			
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
	
	public BigDecimal getPorcentajeExclusion() {
		return porcentajeExclusion;
	}


	public void setPorcentajeExclusion(BigDecimal porcentajeExclusion) {
		this.porcentajeExclusion = porcentajeExclusion;
	}



}
