package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import java.util.Collections;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import com.refinor.extranet.data.dao.MfacturasVDAO;
import com.refinor.extranet.data.dao.MpedidosDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.comparator.FacturaTOComparator;

import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.paginado.Page;

public class RefReporteFacturasBean extends AbstListado {

	public RefReporteFacturasBean() {
		// TODO Auto-generated constructor stub
		 
	}

	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception{
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);
		
	}
	
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		try{			
			MfacturasVDAO mfacturasVDAO = new MfacturasVDAO(getSessionHib());			
	
			
			if(getTipoUsuarioLogueado()==1){
				//es cliente
				setItems(mfacturasVDAO.getFacturaPorClienteFecha(codCliente,fltFechaDesde,fltFechaHasta,condicion,ccss,null,null,fltNroSucursal,fltNroFactura));	
				

			}else if(getTipoUsuarioLogueado()==0){
				//es refipass
				String codClienteAlfa=null;
				if(this.cliente!=null && !this.cliente.equals(new Integer(-1)))
					codClienteAlfa = obtenerCodClienteAlfa(this.cliente);
				
				setItems(mfacturasVDAO.getFacturaPorClienteFecha(codClienteAlfa,fltFechaDesde,fltFechaHasta,condicion,ccss,null,null,fltNroSucursal,fltNroFactura));	
			}
			
			setSubItemsNivel1(getItems());	
			Collections.sort(getItems(), new FacturaTOComparator());
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
	public void generarExcel() {
		try{			
			System.out.println("Paso por generar excel  !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();		
				int codListado=1;
				this.nombreArchivo = exportarExcelBean.generarExcelReporteFacturas(getItems(),getFechaActualStr(),getTipoUsuarioLogueado(),codListado);	
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
	
	public void verRemitos(ActionEvent event){
		try{
			mostrarFrmListaSecundaria= new Boolean(true);
			mostrarListaSecundaria= new Boolean(true);
			mostrarFrmLista= new Boolean(false);
			mostrarLista= new Boolean(false);	
			
			UIParameter component = (UIParameter) event.getComponent().findComponent("ordenFactura");
			Integer ordenFactura = new Integer(component.getValue().toString()).intValue();
			
			UIParameter component1 = (UIParameter) event.getComponent().findComponent("nroRemito");
			Integer nroRemito = new Integer(component1.getValue().toString()).intValue();
			
			UIParameter component2 = (UIParameter) event.getComponent().findComponent("nroSucursal");
			Integer nroSucursal = new Integer(component2.getValue().toString()).intValue();
			
			UIParameter component3 = (UIParameter) event.getComponent().findComponent("codClienteAlfa");
			String codClienteAlfa = component3.getValue().toString();
			
			
			MpedidosDAO mPedidosDAO = new MpedidosDAO(getSessionHib());			
			
			//System.out.println("nroRemito-> "+nroRemito+" nroSucursal-> "+nroSucursal+" codClienteAlfa->"+codClienteAlfa);
			if(nroRemito==0){
				//cta cte
				//System.out.println("ordenFactura-> "+ordenFactura);
				setItems2(mPedidosDAO.getRemitosClienteYOrdenFactura(codClienteAlfa,ordenFactura));
			}else if (nroRemito>0){
				//contado
				setItems2(mPedidosDAO.getRemitosXClienteXRemitoXSucursal(codClienteAlfa,nroRemito,nroSucursal));
			}
			
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
				System.out.println("Paso por generar excel de remitos !!");			
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();			
				this.nombreArchivoSecundario = exportarExcelBean.generarExcelReporteRemitosConsumo(getItems2(),getFechaActualStr());			
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
