package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Transaction;

import com.refinor.extranet.data.Marticulos;
import com.refinor.extranet.data.Mchofer;
import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.MlimiteCarga;
import com.refinor.extranet.data.MlimiteCargaId;
import com.refinor.extranet.data.MunidadN;
import com.refinor.extranet.data.Mvehiculo;
import com.refinor.extranet.data.dao.AlmacenDAO;
import com.refinor.extranet.data.dao.MarticulosDAO;
import com.refinor.extranet.data.dao.MchoferDAO;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MlimiteCargaDAO;
import com.refinor.extranet.data.dao.MpedidosDAO;
import com.refinor.extranet.data.dao.MunidadNDAO;
import com.refinor.extranet.data.dao.MvehiculoDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.AnioTO;
import com.refinor.extranet.to.CuposTO;
import com.refinor.extranet.to.FamiliaTO;
import com.refinor.extranet.to.GrupoTO;
import com.refinor.extranet.to.MesTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.ChoferYaExisteException;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.DatosObligatoriosException;
import com.refinor.extranet.util.exception.ItemsYaExistenException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.NoSePudeEnviarMailException;
import com.refinor.extranet.util.exception.VariosChoferesConIgualDNIException;
import com.refinor.extranet.util.paginado.Page;

public class RefReporteCuposCargaBean extends AbstListado {

	
	protected String vieneDe;
	
	public RefReporteCuposCargaBean() { 
		cargarProductos();
		cargarPatentes();

	}

	/**
	 * Metodo: cargarProductos
	 * Funcion: Cargar los productos disponibles 
	 * @param nroVehiculo
	 * @param ccss
	 */
	public void cargarProductos(){
		try {	
			 MarticulosDAO mArticulosDAO= new MarticulosDAO(sessionHib);
			 productos = new ArrayList<SelectItem>();
			 productos.add(new SelectItem(new Integer(-1),Const.SELECCIONE));		
	     	 
			 Marticulos marticulos;
			 List lstDatos = mArticulosDAO.getArticulosEsCombo();		
			  Iterator it = lstDatos.iterator();			
				
				while(it.hasNext()) {
					marticulos = (Marticulos)it.next();
					productos.add(new SelectItem(new Integer(marticulos.getCodigo()),marticulos.getDescripcion()));
				}					
				
		}catch(Exception ex) {
				ex.printStackTrace();			
				AddErrorMessage("No se han podido recuperar los prodcutos.");
	    }	
	}
	 
	
	
	public void cargarPatentes(){
		try {	
			 MvehiculoDAO mvehiculoDAO= new MvehiculoDAO(sessionHib);
			 patentes = new ArrayList<SelectItem>();
			 patentes.add(new SelectItem(new Integer(-1),Const.SELECCIONE));		
	     	 
			 Mvehiculo Mvehiculo;
			 List lstDatos = mvehiculoDAO.getVehiculoPorCliente(codCliente);		
			  Iterator it = lstDatos.iterator();			
				
				while(it.hasNext()) {
					Mvehiculo = (Mvehiculo)it.next();
					patentes.add(new SelectItem(new Integer(Mvehiculo.getCodigo()),Mvehiculo.getDominio()));
				}					
				
		}catch(Exception ex) {
				ex.printStackTrace();			
				AddErrorMessage("No se han podido recuperar los prodcutos.");
	    }	
	}
	 
	
	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception{
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);
		
	}
	
	
	private void CargarLista()
	{
		try{
			MlimiteCargaDAO mlimiteCargaDAO = new MlimiteCargaDAO(getSessionHib());	 
			//es cliente
			setItems(mlimiteCargaDAO.getCuposPorClientePatenteFliaGrupoArt(codCliente,fltPatente,familia,grupo,producto));  
			
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
					
			CargarLista(); 
		
	}
	
//	public void buscarCupos(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
//
//		try{			
//			MlimiteCargaDAO mlimiteCargaDAO = new MlimiteCargaDAO(getSessionHib());			
//			//setItems(mlimiteCargaDAO.getCuposPorClientePatente(codCliente,fltPatente));	
//			
//			if(getTipoUsuarioLogueado()==1){
//				//es cliente
//				setItems(mlimiteCargaDAO.getCuposVehiculosSinConsumir(codCliente,fltPatente));	
//			}else if(getTipoUsuarioLogueado()==0){
//				//es refipass
//				String codClienteAlfa=null;
//				if(this.cliente!=null && !this.cliente.equals(new Integer(-1)))
//					codClienteAlfa = obtenerCodClienteAlfa(this.cliente);				
//				setItems(mlimiteCargaDAO.getCuposVehiculosSinConsumir(codClienteAlfa,fltPatente));	
//
//			}
//			
//			setSubItemsNivel1(getItems());		
//			cargarPagina();					
//			mostrarLista=new Boolean(true);
//			mostrarFrmLista=new Boolean(true);
//			
//		}
//		catch(NoExistenItemsException ex){
//			ex.printStackTrace();
//			AddErrorMessage(ex.getMessage());
//			noMostrarLista();
//			
//		}catch(DataAccessErrorException ex){
//			ex.printStackTrace();
//			AddErrorMessage(ex.getMessage());
//			noMostrarLista();
//		}catch(Exception ex){
//			ex.printStackTrace();
//			AddErrorMessage(ex.getMessage());
//			noMostrarLista();
//		}
//	}

	@Override
	public void generarExcel() {
		try{			
			System.out.println("Paso por generar excel de cupos !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();	  
				this.nombreArchivo = exportarExcelBean.generarExcelReporteCargaCupos(getItems(),getFechaActualStr(),getTipoUsuarioLogueado()); 
				 
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
		mostrarFrmModificar= new Boolean(false);
		mostrarFrmBaja= new Boolean(false);
		mostrarFrmAlta= new Boolean(false);
		CargarLista();	
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

	
	protected String dominio;
	protected String artFamGrupo;
	protected String articulo;
	protected String ltrCarga;
	protected String ltrDia;
	protected String ltrMes;
	
	public void verModificar(ActionEvent event){
 		try{
			
		    mostrarFrmModificar= new Boolean(true);
			mostrarFrmListaSecundaria= new Boolean(false);
			mostrarListaSecundaria= new Boolean(false);
			mostrarFrmLista= new Boolean(false);
			mostrarLista= new Boolean(false);	
			mostrarFrmBaja = new Boolean(false);
			
			UIParameter component = (UIParameter) event.getComponent().findComponent("CodVehiculo");
			Integer CodVeh = new Integer(component.getValue().toString()).intValue();
		
			MlimiteCargaDAO mlimiteCargaDAO= new MlimiteCargaDAO(getSessionHib());
			CuposTO cuposTO = mlimiteCargaDAO.getCuposPorCodVehiculo(CodVeh) ;
			
			System.out.println("mvehiculo==>" + cuposTO.getPatente());
			
			dominio = cuposTO.getPatente();
			artFamGrupo = cuposTO.getFamiliaGrupoArticuloDesc();
			articulo = cuposTO.getDescripcion();
			ltrCarga = cuposTO.getLtrCarga().toString();
			ltrDia = cuposTO.getLtrDia().toString();
			ltrMes =  cuposTO.getLtrMes().toString();
			ilimitado = cuposTO.getIlimitado();
			codVehiculo = CodVeh;
			artFamGrupoLetra = cuposTO.getFamiliaGrupoArticulo();
			codProducto = cuposTO.getCodProducto();
			
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

	
	public void verBaja(ActionEvent event){
 		try{
			
		    mostrarFrmModificar= new Boolean(false);
			mostrarFrmListaSecundaria= new Boolean(false);
			mostrarListaSecundaria= new Boolean(false);
			mostrarFrmLista= new Boolean(false);
			mostrarLista= new Boolean(false);	
			mostrarFrmBaja = new Boolean(true);
			
			UIParameter component = (UIParameter) event.getComponent().findComponent("CodVehiculoE");
			Integer CodVeh = new Integer(component.getValue().toString()).intValue();
		
			MlimiteCargaDAO mlimiteCargaDAO= new MlimiteCargaDAO(getSessionHib());
			CuposTO cuposTO = mlimiteCargaDAO.getCuposPorCodVehiculo(CodVeh) ;
			
			System.out.println("mvehiculo==>" + cuposTO.getPatente());
			
			dominio = cuposTO.getPatente();
			artFamGrupo = cuposTO.getFamiliaGrupoArticuloDesc();
			articulo = cuposTO.getDescripcion();
			ltrCarga = cuposTO.getLtrCarga().toString();
			ltrDia = cuposTO.getLtrDia().toString();
			ltrMes =  cuposTO.getLtrMes().toString();
			ilimitado = cuposTO.getIlimitado();
			codVehiculo = CodVeh;
			artFamGrupoLetra = cuposTO.getFamiliaGrupoArticulo();
			codProducto = cuposTO.getCodProducto();
			
		}catch(DataAccessErrorException ex){
			mostrarFrmBaja = new Boolean(false);
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}catch(NoExistenItemsException ex){
			mostrarFrmBaja = new Boolean(false);
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}		
	}

	
	
	public void guardarModificacion(ActionEvent event){
		Transaction tx= null;
		try{	
			
			    mostrarFrmModificar= new Boolean(true);
				mostrarFrmListaSecundaria= new Boolean(false);
				mostrarListaSecundaria= new Boolean(false);
				mostrarFrmLista= new Boolean(false);
				mostrarLista= new Boolean(false);
				mostrarFrmBaja= new Boolean(false); 
				
				if(this.ltrCarga==null || this.ltrCarga.trim().equals("") )
					ltrCarga = "0"; 
				
				if(this.ltrDia==null || this.ltrDia.trim().equals(""))
					ltrDia = "0"; 
				
				if(this.ltrMes==null || this.ltrMes.trim().equals(""))
					ltrMes = "0";
				 
			
			if(ilimitado==false && 
				  new BigDecimal(this.ltrCarga).compareTo(new BigDecimal("0")) == 0  && 
				  new BigDecimal(this.ltrDia).compareTo(new BigDecimal("0"))  == 0  && 
				  new BigDecimal(this.ltrMes).compareTo(new BigDecimal("0")) == 0){
				throw new DatosObligatoriosException("Debe completar los datos.");
			}
			
			if( ilimitado==true && 
					 (   new BigDecimal(this.ltrCarga).compareTo(new BigDecimal("0")) > 0  
					||   new BigDecimal(this.ltrDia).compareTo(new BigDecimal("0")) > 0
					||   new BigDecimal(this.ltrMes).compareTo(new BigDecimal("0")) > 0
					 )){
				throw new DatosObligatoriosException("Si es ilimitado no debe tener datos en litros.");
			}
			
			if( new BigDecimal(this.ltrCarga).compareTo(new BigDecimal("0")) > 0  &&
				(  new BigDecimal(this.ltrDia).compareTo(new BigDecimal("0")) > 0
				||  new BigDecimal(this.ltrMes).compareTo(new BigDecimal("0")) > 0 ) ){
				throw new DatosObligatoriosException("Solo puede informar un límite en litros.");
			}
			
			if( new BigDecimal(this.ltrDia).compareTo(new BigDecimal("0")) > 0  &&
					(  new BigDecimal(this.ltrCarga ).compareTo(new BigDecimal("0")) > 0
					||  new BigDecimal(this.ltrMes).compareTo(new BigDecimal("0")) > 0 ) ){
					throw new DatosObligatoriosException("Solo puede informar un límite en litros.");
				}
			
			if( new BigDecimal(this.ltrMes).compareTo(new BigDecimal("0")) > 0  &&
					(  new BigDecimal(this.ltrDia).compareTo(new BigDecimal("0")) > 0
					||  new BigDecimal(this.ltrCarga).compareTo(new BigDecimal("0")) > 0 ) ){
					throw new DatosObligatoriosException("Solo puede informar un límite en litros.");
				}
			
			MlimiteCarga mlimiteCarga = new MlimiteCarga();			
			MlimiteCargaDAO mlimiteCargaDAO = new MlimiteCargaDAO(getSessionHib());
			System.out.println("Cod Vehiculo " + codVehiculo);
			mlimiteCarga = mlimiteCargaDAO.getLimiteCargaPorCodVehiculo(codVehiculo );			
			
			/*
			 * if(existeChoferConDNICodigoCliente(this.numeroDocumento, mchofer.getCodigo(),
			 * mchofer.getCodCli())){ throw new
			 * ChoferYaExisteException(mensajeria.getMessage().getString(
			 * "nro_documento_ya_registrado_msg")); }
			 */	 
			
			mlimiteCarga.setIlimitado(ilimitado); 		
			mlimiteCarga.setFliaGrupArt(artFamGrupoLetra);
			
			if(this.ltrCarga==null)
				mlimiteCarga.setLitrosPcarga(new BigDecimal(0));
			else
			    mlimiteCarga.setLitrosPcarga(new BigDecimal(ltrCarga));
			
			if(this.ltrDia==null)
				mlimiteCarga.setLitrosPdia(new BigDecimal(0));
			else
			    mlimiteCarga.setLitrosPdia(new BigDecimal(ltrDia));
			
			if(this.ltrMes==null)
				mlimiteCarga.setLitrosPmes(new BigDecimal(0));
			else
			    mlimiteCarga.setLitrosPmes(new BigDecimal(ltrMes));
			
			MlimiteCargaId id = new MlimiteCargaId();
			id.setCodArt(codProducto);
			id.setCodVeh(codVehiculo);
			mlimiteCarga.setId(id);
			
			System.out.println( " ilimitado "+mlimiteCarga.isIlimitado() +" artFamGrupoLetra "+ artFamGrupoLetra + " ltrCarga " + ltrCarga);
			System.out.println( " ltrDia "+ltrDia +" ltrDia "+ ltrDia + " ltrMes " + ltrMes);
			System.out.println(" codProducto " + codProducto + " codVehiculo " + codVehiculo);
			 
			try {
				
				    tx= sessionHib.beginTransaction();				
				    mlimiteCargaDAO.saveOrUpdate( mlimiteCarga,sessionHib); 
				    tx.commit(); 
				    mostrarFrmModificar= new Boolean(false);
					mostrarFrmListaSecundaria= new Boolean(false);
					mostrarListaSecundaria= new Boolean(false);
					 mostrarFrmAlta=new Boolean(false);
					 mostrarFrmBaja=new Boolean(false);
					CargarLista();
					AddErrorMessage("Los datos se actualizaron correctamente.");
			}  catch(Exception excep) {
				excep.printStackTrace();
				tx.rollback();
				throw new DataAccessErrorException();
			}
			
 		} 
//			catch(ChoferYaExisteException ex){
//			ex.printStackTrace();	
//			//pantalla=2;
//			AddErrorMessage(ex.getMessage());
//		}
			catch(DatosObligatoriosException ex){
			ex.printStackTrace();	 
			AddErrorMessage(ex.getMessage());
	  }
			catch(DataAccessErrorException ex){
				ex.printStackTrace();	 
				AddErrorMessage(ex.getMessage());
		}catch(Exception excep) {
			excep.printStackTrace(); 
			AddErrorMessage(new DataAccessErrorException().getMessage());
		}
	}
	
	
	
	
	
	public void guardarAlta(ActionEvent event){
		Transaction tx= null;
		try{	
			
			    mostrarFrmModificar= new Boolean(false);
				mostrarFrmListaSecundaria= new Boolean(false);
				mostrarListaSecundaria= new Boolean(false);
				mostrarFrmLista= new Boolean(false);
				mostrarLista= new Boolean(false);
				mostrarFrmBaja= new Boolean(false); 
				mostrarFrmAlta= new Boolean(true); 
				
				if(this.ltrCarga==null || this.ltrCarga.trim().equals("") )
					ltrCarga = "0"; 
				
				if(this.ltrDia==null || this.ltrDia.trim().equals(""))
					ltrDia = "0"; 
				
				if(this.ltrMes==null || this.ltrMes.trim().equals(""))
					ltrMes = "0";
				 
			
			if(ilimitado==false && 
				  new BigDecimal(this.ltrCarga).compareTo(new BigDecimal("0")) == 0  && 
				  new BigDecimal(this.ltrDia).compareTo(new BigDecimal("0"))  == 0  && 
				  new BigDecimal(this.ltrMes).compareTo(new BigDecimal("0")) == 0){
				throw new DatosObligatoriosException("Debe completar los datos.");
			}
			
			if( ilimitado==true && 
					 (   new BigDecimal(this.ltrCarga).compareTo(new BigDecimal("0")) > 0  
					||   new BigDecimal(this.ltrDia).compareTo(new BigDecimal("0")) > 0
					||   new BigDecimal(this.ltrMes).compareTo(new BigDecimal("0")) > 0
					 )){
				throw new DatosObligatoriosException("Si es ilimitado no debe tener datos en litros.");
			}
			
			if( new BigDecimal(this.ltrCarga).compareTo(new BigDecimal("0")) > 0  &&
				(  new BigDecimal(this.ltrDia).compareTo(new BigDecimal("0")) > 0
				||  new BigDecimal(this.ltrMes).compareTo(new BigDecimal("0")) > 0 ) ){
				throw new DatosObligatoriosException("Solo puede informar un límite en litros.");
			}
			
			if( new BigDecimal(this.ltrDia).compareTo(new BigDecimal("0")) > 0  &&
					(  new BigDecimal(this.ltrCarga ).compareTo(new BigDecimal("0")) > 0
					||  new BigDecimal(this.ltrMes).compareTo(new BigDecimal("0")) > 0 ) ){
					throw new DatosObligatoriosException("Solo puede informar un límite en litros.");
				}
			
			if( new BigDecimal(this.ltrMes).compareTo(new BigDecimal("0")) > 0  &&
					(  new BigDecimal(this.ltrDia).compareTo(new BigDecimal("0")) > 0
					||  new BigDecimal(this.ltrCarga).compareTo(new BigDecimal("0")) > 0 ) ){
					throw new DatosObligatoriosException("Solo puede informar un límite en litros.");
				}
			
			MlimiteCarga mlimiteCarga = new MlimiteCarga();			
			MlimiteCargaDAO mlimiteCargaDAO = new MlimiteCargaDAO(getSessionHib());
			 
			mlimiteCarga = new MlimiteCarga();	
			
			System.out.println(  " patente " + patente);
		    if(existeCupo( this.patente )){ 
				  throw new  ItemsYaExistenException("Ya existe el límite de carga para ese vehículo."); 
				  }
			   
			
			mlimiteCarga.setIlimitado(ilimitado); 
			
			if(agrupado==1) { artFamGrupoLetra="F";}
			if(agrupado==2) { artFamGrupoLetra="G";}
			if(agrupado==3) { artFamGrupoLetra="A";}
			mlimiteCarga.setFliaGrupArt(artFamGrupoLetra);
			
			if(this.ltrCarga==null)
				mlimiteCarga.setLitrosPcarga(new BigDecimal(0));
			else
			    mlimiteCarga.setLitrosPcarga(new BigDecimal(ltrCarga));
			
			if(this.ltrDia==null)
				mlimiteCarga.setLitrosPdia(new BigDecimal(0));
			else
			    mlimiteCarga.setLitrosPdia(new BigDecimal(ltrDia));
			
			if(this.ltrMes==null)
				mlimiteCarga.setLitrosPmes(new BigDecimal(0));
			else
			    mlimiteCarga.setLitrosPmes(new BigDecimal(ltrMes));
			
			MlimiteCargaId id = new MlimiteCargaId();
			id.setCodArt(tipoAgrupado);
			id.setCodVeh(patente);
			mlimiteCarga.setId(id);
			
			System.out.println( " ilimitado "+mlimiteCarga.isIlimitado() +" artFamGrupoLetra "+ artFamGrupoLetra + " ltrCarga " + ltrCarga);
			System.out.println( " ltrDia "+ltrDia +" ltrDia "+ ltrDia + " ltrMes " + ltrMes);
			System.out.println(" tipoAgrupado " + tipoAgrupado + " patente " + patente);
			 
			try {
				
					if(!sessionHib.isOpen())
					{
						sessionHib = (new AlmacenDAO()).getSession();
					}
				    tx= sessionHib.beginTransaction();				
				    mlimiteCargaDAO.save( mlimiteCarga,sessionHib); 
				    tx.commit(); 
				    mostrarFrmModificar= new Boolean(false);
					mostrarFrmListaSecundaria= new Boolean(false);
					mostrarListaSecundaria= new Boolean(false);
					
				    mostrarFrmBaja = new Boolean(false);
				    mostrarFrmAlta = new Boolean(false);
				    CargarLista();
					AddErrorMessage("Los datos se actualizaron correctamente.");
			}  catch(Exception excep) {
				excep.printStackTrace();
				tx.rollback();
				throw new DataAccessErrorException();
			}
			
 		} 
 		catch(ItemsYaExistenException ex){
 			ex.printStackTrace();	 
 			AddErrorMessage(ex.getMessage());
 		}
			catch(DatosObligatoriosException ex){
			ex.printStackTrace();	 
			AddErrorMessage(ex.getMessage());
	  }
			catch(DataAccessErrorException ex){
				ex.printStackTrace();	 
				AddErrorMessage(ex.getMessage());
		}catch(Exception excep) {
			excep.printStackTrace(); 
			AddErrorMessage(new DataAccessErrorException().getMessage());
		}
	}
	
	
	public Boolean existeCupo( Integer codVeh) throws NoExistenItemsException, DataAccessErrorException{
			MlimiteCargaDAO mlimiteCargaDAO= new MlimiteCargaDAO(sessionHib);
			MlimiteCarga mlimiteCarga= new MlimiteCarga();	 
	 try{		 
			 
			mlimiteCarga= mlimiteCargaDAO.getLimiteCargaPorCodVehiculo(codVeh)  ;	
			System.out.println("mlimiteCarga " + mlimiteCarga);
			if(mlimiteCarga==null)
			   return false;
			else
				return true;
			
	 }
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			 return false;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		} 
		
	}
	
	public void guardarBaja(ActionEvent event){
		Transaction tx= null;
		try{	
			
		    mostrarFrmModificar= new Boolean(false);
			mostrarFrmListaSecundaria= new Boolean(false);
			mostrarListaSecundaria= new Boolean(false);
			mostrarFrmLista= new Boolean(false);
			mostrarLista= new Boolean(false);	
			mostrarFrmBaja = new Boolean(true);	 
			
			MlimiteCarga mlimiteCarga = new MlimiteCarga();			
			MlimiteCargaDAO mlimiteCargaDAO = new MlimiteCargaDAO(getSessionHib());
			System.out.println("Cod Vehiculo " + codVehiculo);
			mlimiteCarga = mlimiteCargaDAO.getLimiteCargaPorCodVehiculo(codVehiculo ); 
			
			mlimiteCarga.setIlimitado(ilimitado); 		
			mlimiteCarga.setFliaGrupArt(artFamGrupoLetra); 
			mlimiteCarga.setLitrosPcarga(new BigDecimal(ltrCarga));
			mlimiteCarga.setLitrosPdia(new BigDecimal(ltrDia));
			mlimiteCarga.setLitrosPmes(new BigDecimal(ltrMes));
			MlimiteCargaId id = new MlimiteCargaId();
			id.setCodArt(codProducto);
			id.setCodVeh(codVehiculo);
			mlimiteCarga.setId(id);
			
			System.out.println( " ilimitado "+mlimiteCarga.isIlimitado() +" artFamGrupoLetra "+ artFamGrupoLetra + " ltrCarga " + ltrCarga);
			System.out.println( " ltrDia "+ltrDia +" ltrDia "+ ltrDia + " ltrMes " + ltrMes);
			System.out.println(" codProducto " + codProducto + " codVehiculo " + codVehiculo);
			 
			try {
				
				    tx= sessionHib.beginTransaction();				
				    mlimiteCargaDAO.delete( mlimiteCarga,sessionHib); 
				    tx.commit(); 
				    mostrarFrmModificar= new Boolean(false);
					mostrarFrmListaSecundaria= new Boolean(false);
					mostrarListaSecundaria= new Boolean(false);
					mostrarFrmBaja= new Boolean(false);
					mostrarFrmAlta= new Boolean(false);
					CargarLista();	 
					AddErrorMessage("El registro se eliminó correctamente.");
			}  catch(Exception excep) {
				excep.printStackTrace();
				tx.rollback();
				throw new DataAccessErrorException();
			}
			
 		} 
//			catch(ChoferYaExisteException ex){
//			ex.printStackTrace();	
//			//pantalla=2;
//			AddErrorMessage(ex.getMessage());
//		}
			 
			catch(DataAccessErrorException ex){
				ex.printStackTrace();	 
				AddErrorMessage(ex.getMessage());
		}catch(Exception excep) {
			excep.printStackTrace(); 
			AddErrorMessage(new DataAccessErrorException().getMessage());
		}
	}
	
	
	public void alta(ActionEvent event){
 		//try{
			
		    mostrarFrmModificar= new Boolean(false);
			mostrarFrmListaSecundaria= new Boolean(false);
			mostrarListaSecundaria= new Boolean(false);
			mostrarFrmLista= new Boolean(false);
			mostrarLista= new Boolean(false);	
			mostrarFrmBaja = new Boolean(false);
			mostrarFrmAlta = new Boolean(true); 
			inicializar(); 
			
		/*
		 * }catch(DataAccessErrorException ex){ mostrarListaSecundaria= new
		 * Boolean(false); ex.printStackTrace(); AddErrorMessage(ex.getMessage());
		 * }catch(NoExistenItemsException ex){ mostrarListaSecundaria= new
		 * Boolean(false); ex.printStackTrace(); AddErrorMessage(ex.getMessage()); }
		 */	
	}
	
	
	private void inicializar()
	{
		dominio = "";
		artFamGrupo = "";
		articulo = "";
		ltrCarga = "";
		ltrDia = "";
		ltrMes =  "";
		ilimitado = false;
		codVehiculo = 0;
		artFamGrupoLetra = "";
		codProducto = 0;
		patente=null;
		agrupado=null;
		/*
		 * tipoAgrupado=null; tipoAgrupados = new ArrayList<SelectItem>();
		 * tipoAgrupados.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 */	
	
	}
	
	
	public void cargarTipoAgrupado(ValueChangeEvent event){
		
		    mostrarFrmModificar= new Boolean(false);
			mostrarFrmListaSecundaria= new Boolean(false);
			mostrarListaSecundaria= new Boolean(false);
			mostrarFrmLista= new Boolean(false);
			mostrarLista= new Boolean(false);	
			mostrarFrmBaja = new Boolean(false);
			mostrarFrmAlta = new Boolean(true); 
		
		 Integer nroAgrupado = ((Integer)event.getNewValue()).intValue();
		 try {
				
			 if(nroAgrupado==3)
			 {
				 //Articulo
				 MarticulosDAO marticulosDAO = new MarticulosDAO(sessionHib);
				 tipoAgrupados = new ArrayList<SelectItem>();
				 
						try {
							
							Marticulos marticulos;				
							List lstDatos = marticulosDAO.getArticulosEsCombo();				
							
							if(lstDatos!=null && lstDatos.size()>0){
								Iterator it = lstDatos.iterator();			
								while(it.hasNext()) {
									marticulos = (Marticulos)it.next();
									tipoAgrupados.add(new SelectItem(new Integer(marticulos.getCodigo()),marticulos.getDescripcion()));
								}
							}
							
						} catch(Exception ex) {
							tipoAgrupados = new ArrayList<SelectItem>();
							tipoAgrupados.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
							ex.printStackTrace();				
							AddErrorMessage("No se recuperaron el Tipo Agrupado.");
						}
			 }else if(nroAgrupado==2)
			 {
				 //grupo 
				 MarticulosDAO marticulosDAO = new MarticulosDAO(sessionHib);
				 tipoAgrupados = new ArrayList<SelectItem>();
				 
						try {
							
							GrupoTO grupoTO;				
							List lstDatos = marticulosDAO.getGrupo();				
							
							if(lstDatos!=null && lstDatos.size()>0){
								Iterator it = lstDatos.iterator();			
								while(it.hasNext()) {
									grupoTO = (GrupoTO)it.next();
									tipoAgrupados.add(new SelectItem(new Integer(grupoTO.getCodigo()),grupoTO.getDescripcion()));
								}
							}
							
						} catch(Exception ex) {
							tipoAgrupados = new ArrayList<SelectItem>();
							tipoAgrupados.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
							ex.printStackTrace();				
							AddErrorMessage("No se recuperaron el Tipo Agrupado.");
						}
				 
			 }
			 else if(nroAgrupado==1)
			 {
				 //Familia 
				 MarticulosDAO marticulosDAO = new MarticulosDAO(sessionHib);
				 tipoAgrupados = new ArrayList<SelectItem>();
				 
						try {
							
							FamiliaTO familiaTO;				
							List lstDatos = marticulosDAO.getFamilias();				
							
							if(lstDatos!=null && lstDatos.size()>0){
								Iterator it = lstDatos.iterator();			
								while(it.hasNext()) {
									familiaTO = (FamiliaTO)it.next();
									tipoAgrupados.add(new SelectItem(new Integer(familiaTO.getCodigo()),familiaTO.getDescripcion()));
								}
							}
							
						} catch(Exception ex) {
							tipoAgrupados = new ArrayList<SelectItem>();
							tipoAgrupados.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
							ex.printStackTrace();				
							AddErrorMessage("No se recuperaron el Tipo Agrupado.");
						}
				 
			 } 
			 
			/*
			 * else if(nroAgrupado==-1) { //Familia tipoAgrupados = new
			 * ArrayList<SelectItem>(); tipoAgrupados .add(new SelectItem(new
			 * Integer(-1),Const.SELECCIONE)); }
			 */
					
		} catch(Exception ex) {
			ex.printStackTrace();
			AddErrorMessage("No se recuperaron el Tipo Agrupado.");
		}	
	}
	
	
	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getArtFamGrupo() {
		return artFamGrupo;
	}

	public void setArtFamGrupo(String artFamGrupo) {
		this.artFamGrupo = artFamGrupo;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getLtrCarga() {
		return ltrCarga;
	}

	public void setLtrCarga(String ltrCarga) {
		this.ltrCarga = ltrCarga;
	}

	public String getLtrDia() {
		return ltrDia;
	}

	public void setLtrDia(String ltrDia) {
		this.ltrDia = ltrDia;
	}

	public String getLtrMes() {
		return ltrMes;
	}

	public void setLtrMes(String ltrMes) {
		this.ltrMes = ltrMes;
	}

	public String getVieneDe() {
		return vieneDe;
	}

	public void setVieneDe(String vieneDe) {
		this.vieneDe = vieneDe;
	}
}
