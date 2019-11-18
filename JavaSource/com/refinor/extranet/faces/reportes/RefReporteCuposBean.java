package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MlimiteCargaDAO;
import com.refinor.extranet.data.dao.MpedidosDAO;
import com.refinor.extranet.data.dao.MvehiculoDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.AnioTO;
import com.refinor.extranet.to.MesTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.paginado.Page;

public class RefReporteCuposBean extends AbstListado {

	
	protected String vieneDe;
	public RefReporteCuposBean() {
		cargarComboMeses();
		cargarComboAnio();		
	}

	public void cargarComboMeses(){
		if(getRequest().getParameter("vaPor")!=null){
			vieneDe=getRequest().getParameter("vaPor").toString();
		}	
		 
		 MpedidosDAO mPedDAO = new MpedidosDAO(sessionHib);
		 lstMeses = new ArrayList<SelectItem>();
		 lstMeses.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
		try {
			MesTO mesTO;
			List lstDatos = mPedDAO.getMeses();		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mesTO = (MesTO)it.next();
				lstMeses.add(new SelectItem(new Integer(mesTO.getNroMes()),mesTO.getNombreMes()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los meses.");
		}	
		
	}
	
	public void cargarComboAnio(){
		 MpedidosDAO mPedDAO = new MpedidosDAO(sessionHib);
		 lstAnios = new ArrayList<SelectItem>();
		 lstAnios.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
		try {
			AnioTO anioTO;
			List lstDatos = mPedDAO.getAnios();		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				anioTO = (AnioTO)it.next();
				lstAnios.add(new SelectItem(new Integer(anioTO.getCodAnio()),anioTO.getAnio() ));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los años.");
		}	
		
	}
	
	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception{
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);
		
	}
	
	
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{

		try{			
			MlimiteCargaDAO mlimiteCargaDAO = new MlimiteCargaDAO(getSessionHib());			
			//setItems(mlimiteCargaDAO.getCuposPorClientePatente(codCliente,fltPatente));	
			
			if(getTipoUsuarioLogueado()==1){
				//es cliente
				setItems(mlimiteCargaDAO.getCuposConsumoPorClientePatente(codCliente,fltPatente,mes, anio));	
			}else if(getTipoUsuarioLogueado()==0){
				//es refipass
				String codClienteAlfa=null;
				if(this.cliente!=null && !this.cliente.equals(new Integer(-1)))
					codClienteAlfa = obtenerCodClienteAlfa(this.cliente);
				
				setItems(mlimiteCargaDAO.getCuposConsumoPorClientePatente(codClienteAlfa,fltPatente, mes, anio));	

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
	
	public void buscarCupos(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{

		try{			
			MlimiteCargaDAO mlimiteCargaDAO = new MlimiteCargaDAO(getSessionHib());			
			//setItems(mlimiteCargaDAO.getCuposPorClientePatente(codCliente,fltPatente));	
			
			if(getTipoUsuarioLogueado()==1){
				//es cliente
				setItems(mlimiteCargaDAO.getCuposVehiculosSinConsumir(codCliente,fltPatente));	
			}else if(getTipoUsuarioLogueado()==0){
				//es refipass
				String codClienteAlfa=null;
				if(this.cliente!=null && !this.cliente.equals(new Integer(-1)))
					codClienteAlfa = obtenerCodClienteAlfa(this.cliente);				
				setItems(mlimiteCargaDAO.getCuposVehiculosSinConsumir(codClienteAlfa,fltPatente));	

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

	@Override
	public void generarExcel() {
		try{			
			System.out.println("Paso por generar excel de cupos !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();		
				
				if(vieneDe.equals("CON")){
				this.nombreArchivo = exportarExcelBean.generarExcelReporteCuposConsumo(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());
				}else{
					if(vieneDe.equals("CUP")){
						this.nombreArchivo = exportarExcelBean.generarExcelReporteCupos(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());
					}
				}
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
		System.out.println("Verificando permiso para ver la pagina de cupos");	
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
			
			UIParameter component = (UIParameter) event.getComponent().findComponent("nroCliente");
			Integer nroCliente = new Integer(component.getValue().toString()).intValue();
			
			UIParameter component1 = (UIParameter) event.getComponent().findComponent("nroPatente");
			String  nroPatente = component1.getValue().toString();
			
			UIParameter component2 = (UIParameter) event.getComponent().findComponent("nroProducto");
			Integer nroProducto = new Integer(component2.getValue().toString()).intValue();	
			
			UIParameter component5 = (UIParameter) event.getComponent().findComponent("familiaGrupoArticulo");
			String familiaGrupoArticulo = component5.getValue().toString();			
			
			UIParameter component3 = (UIParameter) event.getComponent().findComponent("mes");
			String mess=component3.getValue().toString();
			if(mess.equals("--"))
				mess = "0";
			Integer mes = new Integer(mess).intValue();	
			
			UIParameter component4 = (UIParameter) event.getComponent().findComponent("anio");
			String anios=component4.getValue().toString();
			if(anios.equals("--"))
				anios = "0";
			Integer anio = new Integer(anios).intValue();	
			
			MpedidosDAO mPedidosDAO = new MpedidosDAO(getSessionHib());
			setItems2(mPedidosDAO.getRemitosPorClienteYPatente(nroCliente,Integer.parseInt(nroPatente),nroProducto,mes,anio,familiaGrupoArticulo));
			
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
	
	public void verRemitosCupos(ActionEvent event){
		try{
			mostrarFrmListaSecundaria= new Boolean(true);
			mostrarListaSecundaria= new Boolean(true);
			mostrarFrmLista= new Boolean(false);
			mostrarLista= new Boolean(false);	
			
			UIParameter component = (UIParameter) event.getComponent().findComponent("nroCliente");
			Integer nroCliente = new Integer(component.getValue().toString()).intValue();
			
			UIParameter component1 = (UIParameter) event.getComponent().findComponent("nroPatente");
			String  nroPatente = component1.getValue().toString();
			
			UIParameter component2 = (UIParameter) event.getComponent().findComponent("nroProducto");
			Integer nroProducto = new Integer(component2.getValue().toString()).intValue();					
			
			MpedidosDAO mPedidosDAO = new MpedidosDAO(getSessionHib());			
			setItems2(mPedidosDAO.getRemitosPorClienteYPatente(nroCliente,Integer.parseInt(nroPatente),nroProducto,-1,-1,"A"));
			
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
				this.nombreArchivoSecundario = exportarExcelBean.generarExcelReporteRemitos(getItems2(),"sil");			
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

	

	public String getVieneDe() {
		return vieneDe;
	}

	public void setVieneDe(String vieneDe) {
		this.vieneDe = vieneDe;
	}
}
