package com.refinor.extranet.faces.reportes;


import java.io.IOException;
import javax.faces.event.ActionEvent;
import com.refinor.extranet.data.dao.MchoferDAO;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class RefReporteClientesBean extends AbstListado{
	

	public RefReporteClientesBean() {
		
	}

	
	
	@Override
	public Boolean getPuedeIngresar() {		
		System.out.println("Verificando permiso para ver la pagina de choferes");			
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}	
	
	@Override
	public void generarExcel(){
		try{			
			System.out.println("Paso por generar excel de choferes !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();			
				this.nombreArchivo = exportarExcelBean.generarExcelReporteClientes(getItems(),getFechaActualStr());
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
	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception{
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);
		
	}
	
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		try{			
			MclientesDAO mclientesDAO = new MclientesDAO(getSessionHib());		
			
			if(estado.equals(new Integer(-1))){
				choferActivo=new Boolean(true);
				choferBaja=new Boolean(false);
			}else if(estado.equals(new Integer(1))){
				choferActivo=new Boolean(true);
				choferBaja=new Boolean(true);
			}else if(estado.equals(new Integer(0))){
				choferActivo=new Boolean(false);
				choferBaja=new Boolean(false);
			}		
			
				
				setItems(mclientesDAO.getClientesporFiltros(fltCodClienteAlfa, cliente, fltCUIT, nomdCliente, choferActivo, choferBaja));
			
			
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
