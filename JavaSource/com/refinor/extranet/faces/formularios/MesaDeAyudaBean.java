package com.refinor.extranet.faces.formularios;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.component.UIParameter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.mail.Message;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itsolver.util.io.FileUtil;
import com.refinor.extranet.data.Mautorizacion;
import com.refinor.extranet.data.MautorizacionCodigos;
import com.refinor.extranet.data.MautorizacionMotivo;
import com.refinor.extranet.data.Mccss;
import com.refinor.extranet.data.Mchofer;
import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.MdescripcionMotivoAutorizacion;
import com.refinor.extranet.data.Mempleado;
import com.refinor.extranet.data.MgrupoUn;
import com.refinor.extranet.data.MlimiteCarga;
import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.Mvehiculo;
import com.refinor.extranet.data.Mvendedor;
import com.refinor.extranet.data.TipoComprobante;
import com.refinor.extranet.data.dao.AlmacenDAO;
import com.refinor.extranet.data.dao.MautorizacionCodigosDAO;
import com.refinor.extranet.data.dao.MautorizacionDAO;
import com.refinor.extranet.data.dao.MautorizacionMotivoDAO;
import com.refinor.extranet.data.dao.MccssDAO;
import com.refinor.extranet.data.dao.MchoferDAO;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MdescripcionMotivoAutorizacionDAO;
import com.refinor.extranet.data.dao.MempleadoDAO;
import com.refinor.extranet.data.dao.MgrupoUnDAO;
import com.refinor.extranet.data.dao.MlimiteCargaDAO;
import com.refinor.extranet.data.dao.MvehiculoDAO;
import com.refinor.extranet.data.dao.MvendedorDAO;
import com.refinor.extranet.data.dao.TipoComprobanteDAO;
import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.base.AbstReporte;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.CuposTO;
import com.refinor.extranet.to.MAutorizacionTO;
import com.refinor.extranet.to.MEmpleadosTO;
import com.refinor.extranet.to.MProductoTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.DataUtil;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.ChoferYaExisteException;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.DatosObligatoriosException;
import com.refinor.extranet.util.exception.ItemsYaExistenException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.NoSePudeEnviarMailException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;
import com.refinor.extranet.util.paginado.Page;

public class MesaDeAyudaBean extends AbstReporte {
	protected Session sessionHib;
	
	private Integer vendedor;
	private List<SelectItem> vendedores;
	
	private Integer ccss;	
	private List<SelectItem> lstccss;
	
	private Integer cliente;	
	private List<SelectItem> clientes;
	
	private Integer motivoAutorizacion;
	private List<SelectItem> motivosAutorizacion;
	
	private Integer descripcionMotAutorizacion;
	private List<SelectItem> descripcionesMotAutorizacion;
	
	private Integer empleado;
	private List<SelectItem> empleados;
	
	private Integer chofer;
	private List<SelectItem> choferes;
	private String nombreChofer;
	
	private Integer patente;
	private List<SelectItem> patentes;
	private String numeroPatente;
	
	private Integer producto;
	private List<SelectItem> productos;
	
	private String  nroAutorizacion;
	private int pantalla;
	private Messages mensajeria;
	
	private Integer nroRemitoUnoManual;
	private Integer nroRemitoDosManual;
	private Integer nroRemitoUnoAnular;
	private Integer nroRemitoDosAnular;	
	private String litrosACargar;	
	private Date fechaPedido;
	private MautorizacionCodigos codigo;
	
	private String mensajeGuardado;
	private String tipoComprobanteAnular;
	
	private List lstLimiteCarga;
	protected Boolean mostrarLista;
	protected Boolean mostrarFrmLista;	
	
	private Date fltFechaDesde;
	private Date fltFechaHasta;
	
	private Integer tipoCompManual;	
	private List<SelectItem> tiposCompManual;
	private Integer tipoCompAnular;	
	private List<SelectItem> tiposCompAnular;
	private Page paginaSecundaria;	
	private Page pagina;	
	private Integer tamanioPaginacion;
	private String nombreArchivo;
	
	private MAutorizacionTO autorizacion;
	private Boolean nuevoVendedor;
	private String apellidoVendedor;
	private String nombreVendedor;
	
	private String vieneDe;
	
	private Integer codAutorizacion;
	private Integer idCodigoAuto;
	private Integer codEmisor;
	private Date fecha;
	
	

	/**
	 * Constructor
	 *
	 */
	public MesaDeAyudaBean() {
		try{
			sessionHib = (new AlmacenDAO()).getSession();	
			mensajeria = new Messages();
			
			inicializarValores();
			cargarCombos();	
			
//			viene por modificar
			if(getSessionValue("codigoAutorizacion")!=null){
				Integer codigoAutorizacion =Integer.parseInt(getSessionValue("codigoAutorizacion").toString());
				System.out.println("codigoAutorizacion a modificar: "+codigoAutorizacion);
				getSession().removeAttribute("codigoAutorizacion");
				vieneDe="MOD";
				inicializarValoresMod(codigoAutorizacion);
			}else{
				vieneDe="NUE";
			}

			
			
			}catch(DataAccessErrorException ex){
				ex.printStackTrace();
				AddErrorMessage(ex.getMessage());
			}catch(Exception ex){
				ex.printStackTrace();
				AddErrorMessage(ex.getMessage());
			}
			
	}
	
	
	private void inicializarValoresMod(Integer codigoAutorizacion){
		try{
		MautorizacionDAO mAutorizacionDAO = new MautorizacionDAO(sessionHib);		
		List lstautorizacion = mAutorizacionDAO.getAutorizacionesPorFiltros(null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, codigoAutorizacion,null);
		
		autorizacion= new MAutorizacionTO();
		if(lstautorizacion.size()>0){
			autorizacion= (MAutorizacionTO)lstautorizacion.get(0);
			
			//es la clave primaria del la tabla mautorizacion
			codAutorizacion = autorizacion.getCodAutorizacion();
			//Datos de la Autorización
			this.vendedor = autorizacion.getCodVendedor();
			this.ccss = autorizacion.getCodCcss();
			this.cliente = autorizacion.getCodCliente();
			this.motivoAutorizacion = autorizacion.getCodMotivo();
			
			//cargar el combo de descripcciones
			 cargarDescripcionDeMotivoAutorizacion(this.motivoAutorizacion);
			this.descripcionMotAutorizacion = autorizacion.getCodDescripcion();
			
			//Datos Chofer/Patente
			cargarChoferPatente(this.cliente);
			this.chofer = autorizacion.getCodChofer();
			this.patente = autorizacion.getCodVehiculo();
			
			//tabla de cupos
			cargarCuposDeVehiculos(autorizacion.getCodVehiculo(),autorizacion.getCodCliente());
			
			//datos de comprobante manual
			this.tipoCompManual= autorizacion.getTipoComprobanteManual();
			//suc y remito manual.
			this.nroRemitoUnoManual=autorizacion.getSucursalManual();
			this.nroRemitoDosManual=autorizacion.getNroRemito1();
			
			this.producto=autorizacion.getProducto();
			cargarProductos(this.patente,autorizacion.getCodCcss());
			
			this.litrosACargar = autorizacion.getLitrosACargar()==null || autorizacion.getLitrosACargar().equals(new BigDecimal(0)) ?"":autorizacion.getLitrosACargar().toString();
			
			if(autorizacion.getTipoComprobanteAnular()+""!=null && autorizacion.getTipoComprobanteAnular()==6){
				this.tipoComprobanteAnular = "FACTURA";
			}else if(autorizacion.getTipoComprobanteAnular()+""!=null && autorizacion.getTipoComprobanteAnular()==7){
				this.tipoComprobanteAnular = "REMITO";
			}else{
				this.tipoComprobanteAnular=null;
			}
						
			this.nroRemitoDosAnular= autorizacion.getNroRemito2()==null || autorizacion.getNroRemito2().equals(new Integer(0))?null:autorizacion.getNroRemito2();
			this.nroRemitoUnoAnular= autorizacion.getSucursalAnular()+""==null || autorizacion.getSucursalAnular()==0?null:autorizacion.getSucursalAnular();
						
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.fechaPedido = sdf.parse(autorizacion.getFechaPedido());
			
			this.fecha= sdf.parse(autorizacion.getFecha());
			this.idCodigoAuto=autorizacion.getCodNroAutorizacion();
			this.codEmisor= autorizacion.getCodEmisor();
			this.nroAutorizacion= autorizacion.getNroAutorizacion();
					
		}
		
		
		
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(new DataAccessErrorException().getMessage());
		}
	}
	/**
	 * Metodo:inicializarValores
	 * Funcion: Inicializar valores de atributos
	 * @throws DataAccessErrorException
	 */
	private void inicializarValores() throws DataAccessErrorException{
		this.pantalla= 1 ;	
		
		mostrarFrmLista= new Boolean(true);
		mostrarLista= new Boolean(false);
		
		this.vendedor=-1;
		this.ccss=-1;
		this.cliente=-1;
		this.motivoAutorizacion=-1;
		this.descripcionMotAutorizacion=-1;
		this.chofer=-1;
		this.patente=-1;
		this.producto=-1;
		
		this.fltFechaDesde=null;
		this.fltFechaHasta=null;
		this.fechaPedido=null;
		this.nuevoVendedor= new Boolean(false);
		
		cargarTiposComprobantesVenta();
			
		Properties properties= (new FileUtil()).getPropertiesFile();		
		this.tamanioPaginacion= Integer.parseInt((String)properties.get(Const.TAMANIO_PAGINA_ARCHIVO));		
		this.pagina= new Page();
		this.nombreChofer=null;
		this.numeroPatente=null;
		this.nroAutorizacion=null;
		this.nroRemitoUnoManual=null;
		this.nroRemitoDosManual=null;	
	}
	
	/**
	 * Metodo: cargarTiposComprobantesVenta
	 * Funcion: Cargar el combo de tipos de comprobantes de venta. (Tabla TipoComprobante)
	 * Solo debe cargar: Remito, Factura, remito credito y nota de credito
	 */
	public void cargarTiposComprobantesVenta(){
		 TipoComprobanteDAO tipoComprobanteDAO = new TipoComprobanteDAO(sessionHib);
		 tiposCompManual = new ArrayList<SelectItem>();
		 
		try {
			TipoComprobante tipoComprobante;
			List lstDatos = tipoComprobanteDAO.getTiposComprobantesVenta();		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				tipoComprobante = (TipoComprobante)it.next();
				tiposCompManual.add(new SelectItem(new Integer(tipoComprobante.getId()),tipoComprobante.getNombre()));
			}					
			
			//tipoCompManual= new Integer(1);
		}catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los comprobantes.");
		}	
	}
	
	
	/*
	public void generarCodigoAutorizacionEvent(ValueChangeEvent event){
		try {			
			if(codigo==null){
				generarCodigoAutorizacion();
			}			
		} catch (DataAccessErrorException e) {			
			e.printStackTrace();
			AddErrorMessage(e.getMessage());
		}
	}*/
	
	/**
	 * Metodo: generarCodigoAutorizacion
	 * Funcion: Generar el codigo de autorización - usa un store procedura del sql server
	 */
	private void generarCodigoAutorizacion() throws DataAccessErrorException{		
		MautorizacionCodigosDAO mAutorizacionCodigosDAO= new MautorizacionCodigosDAO(sessionHib);		
		try {
			codigo= new MautorizacionCodigos();
			codigo= mAutorizacionCodigosDAO.getCodigoAutorizacion();			
		} catch (DataAccessErrorException e) {			
			e.printStackTrace();
			throw e;
		}
			
	}
	/**
	 * Metodo: cargarCombos
	 * Funcion: Llamar a las funciones de carga de combo de Vendedor, CCSS, cliente, motivo de autorizacion.
	 * @throws DataAccessErrorException
	 */
	private void cargarCombos() throws DataAccessErrorException{
		try{
			cargarComboVendedor();
			cargarComboCCSS();
			cargarComboClientes();
			cargarComboMotivoAutorizacion();
			cargarEmpleados();
			
			if(descripcionesMotAutorizacion==null){
				descripcionesMotAutorizacion = new ArrayList<SelectItem>();
				descripcionesMotAutorizacion.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
			}
			
			if(choferes==null){
				choferes = new ArrayList<SelectItem>();
				choferes.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
			}
			
			if(patentes==null){
				patentes = new ArrayList<SelectItem>();
				patentes.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
			}
			
			if(productos==null){
				productos = new ArrayList<SelectItem>();
				productos.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
			}
			
		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}		
	}
	
	
	/**
	 * Metodo: guardar
	 * Funcion: Guardar datos de autorizacion. Valida los datos obligatorios. 
	 * Genera el codigo de autorizacion antes de guardar.
	 * @param event
	 * @throws DatosObligatoriosException
	 * @throws ParseException
	 */
	public void guardar(ActionEvent event) throws DatosObligatoriosException, ParseException{
		Transaction tx= null;
		try{

			//validaciones de datos obligatorios
			if(//this.vendedor.equals(new Integer(-1)) || 
					this.ccss.equals(new Integer(-1)) ||
					this.cliente.equals(new Integer(-1)) ||
					this.motivoAutorizacion.equals(new Integer(-1)) ||
					this.descripcionMotAutorizacion.equals(new Integer(-1)) ||
					this.chofer.equals(new Integer(-1)) ||
					this.patente.equals(new Integer(-1)) || 
					//this.nroRemitoUnoManual==null ||
					//this.nroRemitoDosManual==null ||
					this.producto.equals(new Integer(-1)) || 
					//this.litrosACargar==null ||
					//this.litrosACargar.trim().equals("") ||
					this.fechaPedido==null ||
					this.tipoCompManual==null) 	{
				throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
			}	
			
			if(this.tipoCompManual.equals(6) || this.tipoCompManual.equals(7)){
				if(	this.nroRemitoUnoAnular==null ||
					this.nroRemitoDosAnular==null ) 	{
					throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
				}	
				
			}
			
			if(!this.nuevoVendedor){
				if(	this.vendedor.equals(new Integer(-1))) 	{
					throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
				}				
			}
			
			if(this.nuevoVendedor){				
				if(	this.apellidoVendedor==null || this.apellidoVendedor.trim().equals("") ||
						this.nombreVendedor==null || this.nombreVendedor.trim().equals("") ) 	{
						throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
				}else{
					this.vendedor = guardarVendedor();
				}
			}
			
			
			Mautorizacion mAutorizacion = new Mautorizacion();	
			mAutorizacion.setCodVendedor(this.vendedor);
			mAutorizacion.setCodCcss(this.ccss);
			mAutorizacion.setCodCliente(this.cliente);
			mAutorizacion.setCodMotivo(this.motivoAutorizacion);	
			mAutorizacion.setDescripcion("");
			mAutorizacion.setCodDescripcion(this.descripcionMotAutorizacion);
			mAutorizacion.setCodChofer(this.chofer);
			mAutorizacion.setCodVehiculo(this.patente);
			
			
			if(this.nroRemitoUnoManual!=null && this.nroRemitoDosManual!=null){				
				mAutorizacion.setSucursalManual(this.nroRemitoUnoManual);
				mAutorizacion.setNroRemito1(this.nroRemitoDosManual);	
				if(vieneDe.equals("NUE")){
					//verificar que no este registrado en Mautorizacion
					validarExistenciaRemito(this.nroRemitoUnoManual,this.nroRemitoDosManual);
				}
			}
			
			if(this.litrosACargar!=null && !this.litrosACargar.trim().equals("")) {
				if(this.litrosACargar.contains(",")){
					this.litrosACargar= this.litrosACargar.replace(',', '.');
				}
				
				mAutorizacion.setLitrosAcargar(new BigDecimal(this.litrosACargar));
			}				
				
			mAutorizacion.setProducto(this.producto);	
			mAutorizacion.setTipoComprobanteManual(this.tipoCompManual);		
			
			
			if(this.nroRemitoUnoAnular!=null && this.nroRemitoDosAnular!=null){				
				mAutorizacion.setNroRemito2(this.nroRemitoDosAnular);
				mAutorizacion.setSucursalAnular(nroRemitoUnoAnular);
				
				if(this.tipoCompManual.equals(new Integer(6))){
					//si es nota de credito, le asocio factura
					mAutorizacion.setTipoComprobanteAnular(new Integer(1));
				}else if(this.tipoCompManual.equals(new Integer(7))){
					//si es remito credito, le asocio remito
					mAutorizacion.setTipoComprobanteAnular(new Integer(2));
					
				}
			}
			
			SimpleDateFormat  sdfH = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			mAutorizacion.setFechaPedido(sdfH.parse( sdf.format(this.fechaPedido)+" 00:00:00"));
			
			
			if(vieneDe.equals("NUE")){
				String fechaHoy = sdf.format(new Date());
				mAutorizacion.setFecha(sdf.parse(fechaHoy));
				
				generarCodigoAutorizacion();
				mAutorizacion.setIdCodigoAuto(this.codigo.getCodigo());
				
				MusuarioWeb musuarioWeb=(MusuarioWeb)getSessionValue("usuario");
				mAutorizacion.setCodEmisor(musuarioWeb.getId());
				
			}	else if(vieneDe.equals("MOD")){
					mAutorizacion.setCodigo(codAutorizacion);
					mAutorizacion.setFecha(this.fecha);
					mAutorizacion.setCodEmisor(this.codEmisor);
					mAutorizacion.setIdCodigoAuto(this.idCodigoAuto);
			}
			
			try {
				
				if(!sessionHib.isOpen()){
					sessionHib = (new MautorizacionDAO()).getSession();		
				}
				
				tx= sessionHib.beginTransaction();
					MautorizacionDAO mAautorizacionDAO = new MautorizacionDAO(sessionHib);
					if(vieneDe.equals("NUE")){
					   mAautorizacionDAO.save(mAutorizacion, sessionHib);
					}else if(vieneDe.equals("MOD")){							
						mAautorizacionDAO.update(mAutorizacion, sessionHib);
					}
				tx.commit();	
				if(vieneDe.equals("NUE")){
				   mensajeGuardado=mensajeria.getMessage().getString("registro_ok_autorizacion_msg")+" "+this.codigo.getNroAuto();
				}else if(vieneDe.equals("MOD")){
					vieneDe="MODOK";
				   mensajeGuardado= mensajeria.getMessage().getString("modificacion_ok_autorizacion_msg")+" "+this.nroAutorizacion+" "+mensajeria.getMessage().getString("modificacion_ok_autorizacion_2_msg");
				}
				pantalla=2;
			} catch(Exception excep) {
				excep.printStackTrace();
				tx.rollback();
				throw new DataAccessErrorException();
			}
			
		}catch(DatosObligatoriosException ex){
			ex.printStackTrace();		
			AddErrorMessage(ex.getMessage());
		}catch(ItemsYaExistenException ex){
			ex.printStackTrace();		
			AddErrorMessage(ex.getMessage());
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(new DataAccessErrorException().getMessage());
		}
		sessionHib.close();
	}
	
	
	public void validarExistenciaRemito(Integer nroSuc, Integer nroComp)throws Exception,ItemsYaExistenException{
		try{
			MautorizacionDAO autorizacionDAO = new MautorizacionDAO(sessionHib);
			Boolean existe = autorizacionDAO.getAutorizacionPorCompManual(nroSuc,nroComp);
			if(existe){
				String mens= mensajeria.getMessage().getString("el_comprobante_label")+" "+nroSuc.toString()+ " "+nroComp.toString()+" "+mensajeria.getMessage().getString("tiene_autorizacion_label");
				throw new ItemsYaExistenException(mens);
			}
		}catch(ItemsYaExistenException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();			
		}
	}
	/**
	 * Metodo: guardarVendedor
	 * Funcion: Guardar un vendedor
	 * @return codigo de vendedor
	 * @throws DataAccessErrorException
	 */
	public Integer guardarVendedor() throws DataAccessErrorException{
		Transaction tx= null;
		int codVen=0;
		try {
			tx= sessionHib.beginTransaction();
				MvendedorDAO mVendedorDAO = new MvendedorDAO(sessionHib);
				Mvendedor mVendedor = new Mvendedor();
				mVendedor.setNombre(this.nombreVendedor);
				mVendedor.setApellido(this.apellidoVendedor);
				mVendedor.setIdUserLock(new Boolean(false));
				codVen= mVendedorDAO.save(mVendedor, sessionHib);					
			tx.commit();							
			return codVen;
		} catch(Exception excep) {
			excep.printStackTrace();
			tx.rollback();
			throw new DataAccessErrorException();
		}
	}
	
	/**
	 * Metodo: cargarSusCombosAsociados
	 * Funcion: Cargar los combos asociados a cliente. Carga los choferes y patentes para el cliente seleccionado
	 * @param event
	 */
	public void cargarSusCombosAsociados(ValueChangeEvent event){		
		 Integer nroCliente = ((Integer)event.getNewValue()).intValue(); 
		 cargarChoferPatente(nroCliente);
		
	}
	
	
	
	public void buscarChoferPorFiltro(ActionEvent event){
		 //cargar los CHOFERES x nombre	
		 MchoferDAO mChoferDAO = new MchoferDAO(sessionHib);
		 choferes = new ArrayList<SelectItem>();
		 choferes.add(new SelectItem(new Integer(-1),""));		 
		try {
			
			if(cliente.equals(new Integer(-1))){
				throw new DatosObligatoriosException(mensajeria.getMessage().getString("debe_seleccionar_cliente_label"));
			}
			
			Mchofer mChofer;
			List lstDatos = mChoferDAO.getChoferesPorCliente(cliente,true,true,nombreChofer);		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mChofer = (Mchofer)it.next();
				choferes.add(new SelectItem(new Integer(mChofer.getCodigo()),mChofer.getApellido()+", "+mChofer.getNombre()));
			}					
		}catch(DatosObligatoriosException ex) {
			ex.printStackTrace();			
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar Choferes.");
		}	
	}
	
	
	
	public void buscarPatentePorFiltro(ActionEvent event){
		 //cargar los vehiculos x patente	
		 MvehiculoDAO mVehiculoDAO = new MvehiculoDAO(sessionHib);
		 patentes = new ArrayList<SelectItem>();
		 patentes.add(new SelectItem(new Integer(-1),""));	 
		try {
			
			if(cliente.equals(new Integer(-1))){
				throw new DatosObligatoriosException(mensajeria.getMessage().getString("debe_seleccionar_patentes_label"));
			}
			
			Mvehiculo mVehiculo;
			List lstDatos = mVehiculoDAO.getVehiculosPorCliente(cliente,true,true,numeroPatente);		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mVehiculo = (Mvehiculo)it.next();
				patentes.add(new SelectItem(new Integer(mVehiculo.getCodigo()),mVehiculo.getDominio()));
			}		
			
		}catch(DatosObligatoriosException ex) {
			ex.printStackTrace();			
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar las patentes.");
		}	
	}
	public void cargarChoferPatente(Integer nroCliente){
		this.nombreChofer=null;
		this.numeroPatente=null;
		
		 lstLimiteCarga=new ArrayList();
		 productos= new ArrayList<SelectItem>();
		 productos.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
		 //cargar los CHOFERES
		 MchoferDAO mChoferDAO = new MchoferDAO(sessionHib);
		 choferes = new ArrayList<SelectItem>();
		 choferes.add(new SelectItem(new Integer(-1),""));
		 
		try {
			Mchofer mChofer;
			List lstDatos = mChoferDAO.getChoferesPorCliente(nroCliente,true,true,null);		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mChofer = (Mchofer)it.next();
				choferes.add(new SelectItem(new Integer(mChofer.getCodigo()),mChofer.getApellido()+", "+mChofer.getNombre()));
			}					
			
			
		}catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar Choferes.");
		}	
		
		//CARGAR las PATENTES
		MvehiculoDAO mVehiculoDAO = new MvehiculoDAO(sessionHib);
		 patentes = new ArrayList<SelectItem>();
		 patentes.add(new SelectItem(new Integer(-1),""));
		 
		try {
			Mvehiculo mVehiculo;
			List lstDatos = mVehiculoDAO.getVehiculosPorCliente(nroCliente,true,true,null);		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mVehiculo = (Mvehiculo)it.next();
				patentes.add(new SelectItem(new Integer(mVehiculo.getCodigo()),mVehiculo.getDominio()));
			}		
			
		}catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar Patentes.");
		}	
	}
	
	
	
	/**
	 * Metodo:cargarProductosCCSS
	 * @param event
	 */
	public void cargarProductosCCSS(ValueChangeEvent event){		
		 Integer nroCcss = ((Integer)event.getNewValue()).intValue(); 	 
		
		 
		//CARGAR PRODUCTOS PARA LA PATENTE Y CENTRO DE SERVICIO
		if(patente!=null && patente!=-1 &&  nroCcss!=null && nroCcss!=-1){
			cargarProductos(this.patente,nroCcss);
		}else{
			
		}
		//END - CARGAR PRODUCTOS PARA LA PATENTE Y CENTRO DE SERVICIO
		
	}
	/**
	 * Metodo: cargarProductos
	 * Funcion: Cargar los productos disponibles para el centro de servicio seleccionado y para la patente seleccionada.
	 * @param nroVehiculo
	 * @param ccss
	 */
	public void cargarProductos(int nroVehiculo, int ccss){
		try {	
			 MlimiteCargaDAO mLimiteCargaDAO= new MlimiteCargaDAO(sessionHib);
			 productos = new ArrayList<SelectItem>();
			 productos.add(new SelectItem(new Integer(-1),Const.SELECCIONE));		
	     	 
			 MProductoTO mProductoTO;
			 List lstDatos = mLimiteCargaDAO.getProductosPorPatenteCCSS(nroVehiculo,ccss);		
			  Iterator it = lstDatos.iterator();			
				
				while(it.hasNext()) {
					mProductoTO = (MProductoTO)it.next();
					productos.add(new SelectItem(new Integer(mProductoTO.getCodArticulo()),mProductoTO.getDescripcion()));
				}					
				
		}catch(Exception ex) {
				ex.printStackTrace();			
				AddErrorMessage("No se han podido recuperar datos de consumo.");
	    }	
	}
	/**
	 * Metodo: cargarSusDescripciones
	 * Funcion: Cargar las descripciones de un motivo de autorizacion seleccionado.
	 * @param event
	 */
	public void cargarSusDescripciones(ValueChangeEvent event){	
		try{
		 Integer nroMotivo = ((Integer)event.getNewValue()).intValue();	
		 cargarDescripcionDeMotivoAutorizacion(nroMotivo);
		}catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar Descripciones.");
		}	
	}
	
	private void cargarDescripcionDeMotivoAutorizacion(Integer nroMotivo)
	{
		MdescripcionMotivoAutorizacionDAO mDescripcionMotivoAutorizacionDAO = new MdescripcionMotivoAutorizacionDAO(sessionHib);
		 descripcionesMotAutorizacion = new ArrayList<SelectItem>();
		 descripcionesMotAutorizacion.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
		try {
			MdescripcionMotivoAutorizacion mDescripcionMotivoAutorizacion;
			List lstDatos = mDescripcionMotivoAutorizacionDAO.getDescripcionesPorMotAutorizacion(nroMotivo);		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mDescripcionMotivoAutorizacion = (MdescripcionMotivoAutorizacion)it.next();
				descripcionesMotAutorizacion.add(new SelectItem(new Integer(mDescripcionMotivoAutorizacion.getCodigo()),mDescripcionMotivoAutorizacion.getDescripcion()));
			}				
			
		}catch(Exception ex) {
			ex.printStackTrace();		
			
		}	
	}
	
	/**
	 * Metodo:cargarLimiteCarga
	 * Funcion: Carga el listado de cupo para una patente seleccioanda.
	 * Lo que tiene disponible para consumir, lo consumido, y lo que le queda por consumir de los 
	 *  combustible que tiene asignado 
	 * @param event
	 */
	public void cargarLimiteCarga(ValueChangeEvent event){
		Integer nroVehiculo=null;
		
		if ( event.getNewValue()!=null) {
		  nroVehiculo = ((Integer)event.getNewValue()).intValue();
		}
		 
		 //CARGAR LIMITE DE CARGA - CONSUMO Y DISPONIBLES de productos 
		
		 if(nroVehiculo!=null && nroVehiculo!=-1 ){			 
			 cargarCuposDeVehiculos(nroVehiculo,this.cliente);
			 
		 }else{
			 lstLimiteCarga=new ArrayList();
		 }
		 //END - CARGAR LIMITE DE CARGA - CONSUMO Y DISPONIBLES de productos
		
		//CARGAR PRODUCTOS PARA LA PATENTE Y CENTRO DE SERVICIO
		if(nroVehiculo!=null && nroVehiculo!=-1 && this.ccss!=null && this.ccss!=-1){
			cargarProductos(nroVehiculo,this.ccss);
		}
		//END - CARGAR PRODUCTOS PARA LA PATENTE Y CENTRO DE SERVICIO
		
	}
	
	
	private void cargarCuposDeVehiculos(Integer nroVehiculo,Integer cliente){
		 MlimiteCargaDAO mLimiteCargaDAO= new MlimiteCargaDAO(sessionHib);
		 MvehiculoDAO mVehiculoDAO = new MvehiculoDAO(sessionHib);
		 Mvehiculo mVehiculo = new Mvehiculo();
		 mVehiculo = mVehiculoDAO.get(nroVehiculo, sessionHib);
		 System.out.println("dominio "+mVehiculo.getDominio());
		 
		 MclientesDAO  mclientesDAO= new  MclientesDAO(sessionHib);
		 Mclientes  mclientes= new  Mclientes();
		 mclientes= mclientesDAO.get(cliente, sessionHib);
		 System.out.println("cliente "+mclientes.getCodigo()+" "+mclientes.getCodClienteAlfa());
		 
		   try {    
				 
			 SimpleDateFormat sdf= new SimpleDateFormat("MM");
			 SimpleDateFormat sdf1= new SimpleDateFormat("yyyy");
			 
			 Integer mes = Integer.parseInt(sdf.format(new Date()));
			 Integer anio = Integer.parseInt(sdf1.format(new Date()));
			 System.out.println("mes "+mes);
			 System.out.println("anio "+anio);
			
			 lstLimiteCarga = mLimiteCargaDAO.getCuposConsumoPorClientePatente(mclientes.getCodClienteAlfa(), mVehiculo.getDominio(),mes,anio);
				 
			}catch(Exception ex) {
				ex.printStackTrace();			
				//AddErrorMessage("No se han podido recuperar datos de consumo.");
				try{
				  lstLimiteCarga=mLimiteCargaDAO.getCuposVehiculosSinConsumir(mclientes.getCodClienteAlfa(), mVehiculo.getDominio());
				}catch(Exception ex1) {
					ex1.printStackTrace();	
					AddErrorMessage("No se han podido recuperar datos de consumo.");
					lstLimiteCarga=null;
			   }
			}		
	}
	/**
	 * Metodo: cargarComboClientes
	 * Funcion: Cargar el combo de clientes activos.
	 *
	 */
	private void cargarEmpleados(){
		 MempleadoDAO mEmpleadoDAO = new MempleadoDAO(sessionHib);
		 empleados = new ArrayList<SelectItem>();
		 empleados.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
		try {
			 MEmpleadosTO  Mempleado;
			List lstDatos = mEmpleadoDAO.getEmpleadosRegistrados();		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				Mempleado = (MEmpleadosTO)it.next();
				empleados.add(new SelectItem(new Integer(Mempleado.getCodigo()),Mempleado.getDescripcion()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los Empleados.");
		}	
		
	}
	
	/**
	 * Metodo: cargarComboClientes
	 * Funcion: Cargar el combo de clientes activos.
	 *
	 */
	private void cargarComboClientes(){
		 MclientesDAO mClientesDAO = new MclientesDAO(sessionHib);
		 clientes = new ArrayList<SelectItem>();
		 clientes.add(new SelectItem(new Integer(-1),""));
		 
		try {
			Mclientes mClientes;
			List lstDatos = mClientesDAO.getClientePorEstado(true);		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mClientes = (Mclientes)it.next();
				clientes.add(new SelectItem(new Integer(mClientes.getCodigo()),mClientes.getDescripcion()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los Clientes.");
		}	
		
	}
	
	/**
	 * Metodo:cargarComboMotivoAutorizacion
	 * funcion:Cargar el combo de motivos de autorizacion
	 *
	 */
	private void cargarComboMotivoAutorizacion(){
		 MautorizacionMotivoDAO mAutorizacionMotivoDAO = new MautorizacionMotivoDAO(sessionHib);
		 motivosAutorizacion = new ArrayList<SelectItem>();
		 motivosAutorizacion.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
		try {
			MautorizacionMotivo mAutorizacionMotivo;
			List lstDatos = mAutorizacionMotivoDAO.findAll(sessionHib);		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mAutorizacionMotivo = (MautorizacionMotivo)it.next();
				motivosAutorizacion.add(new SelectItem(new Integer(mAutorizacionMotivo.getCodigo()),mAutorizacionMotivo.getDescripcion()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los Motivos de autorizacion.");
		}	
		
	}
	
	/**
	 * Metodo :cargarComboVendedor
	 * Funcon: Cargcar el combo de vendedores
	 *
	 */
	private void cargarComboVendedor(){
		 MvendedorDAO mVendedorDAO = new MvendedorDAO(sessionHib);
		 vendedores = new ArrayList<SelectItem>();
		 vendedores.add(new SelectItem(new Integer(-1),""));
		 
		try {
			MEmpleadosTO mVendedor;			
			List lstDatos = mVendedorDAO.getVendedorRegistrados();		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mVendedor = (MEmpleadosTO)it.next();
				vendedores.add(new SelectItem(new Integer(mVendedor.getCodigo()),mVendedor.getDescripcionApe()+", "+mVendedor.getDescripcion()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los vendedores.");
		}	
		
	}
	
	/**
	 * Metodo:cargarComboCCSS
	 * Funcion: cargar el combo de centros de servicios
	 *
	 */
	private void cargarComboCCSS(){
		 MccssDAO mCcssDAO = new MccssDAO(sessionHib);
		 lstccss = new ArrayList<SelectItem>();
		 lstccss.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
		try {
			MEmpleadosTO  mCcss;
			List lstDatos = mCcssDAO.getCCSSRegistrados();		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mCcss = (MEmpleadosTO)it.next();
				lstccss.add(new SelectItem(new Integer(mCcss.getCodigo()),mCcss.getDescripcion()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los ccss.");
		}	
		
	}
	
	@Override
	public Boolean getPuedeIngresar() {		
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}

	public Integer getVendedor() {
		return vendedor;
	}

	public void setVendedor(Integer vendedor) {
		this.vendedor = vendedor;
	}

	public List<SelectItem> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<SelectItem> vendedores) {
		this.vendedores = vendedores;
	}
	
	public Messages getMensajeria() {
		return mensajeria;
	}

	public void setMensajeria(Messages mensajeria) {
		this.mensajeria = mensajeria;
	}

	public int getPantalla() {
		return pantalla;
	}

	public void setPantalla(int pantalla) {
		this.pantalla = pantalla;
	}

	public Integer getCcss() {
		return ccss;
	}

	public void setCcss(Integer ccss) {
		this.ccss = ccss;
	}

	public List<SelectItem> getLstccss() {
		return lstccss;
	}

	public void setLstccss(List<SelectItem> lstccss) {
		this.lstccss = lstccss;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public List<SelectItem> getClientes() {
		return clientes;
	}

	public void setClientes(List<SelectItem> clientes) {
		this.clientes = clientes;
	}

	public Integer getMotivoAutorizacion() {
		return motivoAutorizacion;
	}

	public void setMotivoAutorizacion(Integer motivoAutorizacion) {
		this.motivoAutorizacion = motivoAutorizacion;
	}

	public List<SelectItem> getMotivosAutorizacion() {
		return motivosAutorizacion;
	}

	public void setMotivosAutorizacion(List<SelectItem> motivosAutorizacion) {
		this.motivosAutorizacion = motivosAutorizacion;
	}


	public List<SelectItem> getDescripcionesMotAutorizacion() {
		return descripcionesMotAutorizacion;
	}

	public void setDescripcionesMotAutorizacion(
			List<SelectItem> descripcionesMotAutorizacion) {
		this.descripcionesMotAutorizacion = descripcionesMotAutorizacion;
	}

	public Integer getDescripcionMotAutorizacion() {
		return descripcionMotAutorizacion;
	}

	public void setDescripcionMotAutorizacion(Integer descripcionMotAutorizacion) {
		this.descripcionMotAutorizacion = descripcionMotAutorizacion;
	}

	public Integer getChofer() {
		return chofer;
	}

	public void setChofer(Integer chofer) {
		this.chofer = chofer;
	}

	public List<SelectItem> getChoferes() {
		return choferes;
	}

	public void setChoferes(List<SelectItem> choferes) {
		this.choferes = choferes;
	}

	public Integer getPatente() {
		return patente;
	}

	public void setPatente(Integer patente) {
		this.patente = patente;
	}

	public List<SelectItem> getPatentes() {
		return patentes;
	}

	public void setPatentes(List<SelectItem> patentes) {
		this.patentes = patentes;
	}
	

	public Integer getNroRemitoDosAnular() {
		return nroRemitoDosAnular;
	}

	public void setNroRemitoDosAnular(Integer nroRemitoDosAnular) {
		this.nroRemitoDosAnular = nroRemitoDosAnular;
	}

	public Integer getNroRemitoDosManual() {
		return nroRemitoDosManual;
	}

	public void setNroRemitoDosManual(Integer nroRemitoDosManual) {
		this.nroRemitoDosManual = nroRemitoDosManual;
	}

	public Integer getNroRemitoUnoAnular() {
		return nroRemitoUnoAnular;
	}

	public void setNroRemitoUnoAnular(Integer nroRemitoUnoAnular) {
		this.nroRemitoUnoAnular = nroRemitoUnoAnular;
	}

	public Integer getNroRemitoUnoManual() {
		return nroRemitoUnoManual;
	}

	public void setNroRemitoUnoManual(Integer nroRemitoUnoManual) {
		this.nroRemitoUnoManual = nroRemitoUnoManual;
	}

	public MautorizacionCodigos getCodigo() {
		return codigo;
	}

	public void setCodigo(MautorizacionCodigos codigo) {
		this.codigo = codigo;
	}

	public String getMensajeGuardado() {
		return mensajeGuardado;
	}

	public void setMensajeGuardado(String mensajeGuardado) {
		this.mensajeGuardado = mensajeGuardado;
	}

	public Date getFltFechaDesde() {
		return fltFechaDesde;
	}

	public void setFltFechaDesde(Date fltFechaDesde) {
		this.fltFechaDesde = fltFechaDesde;
	}

	public Date getFltFechaHasta() {
		return fltFechaHasta;
	}

	public void setFltFechaHasta(Date fltFechaHasta) {
		this.fltFechaHasta = fltFechaHasta;
	}

	

	
	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	
	
	
	public String getLitrosACargar() {
		return litrosACargar;
	}

	public void setLitrosACargar(String litrosACargar) {
		this.litrosACargar = litrosACargar;
	}

	/**
	 * MEtodo:buscar
	 * Funcion: Buscar las autorizaciones que coincidan con los filtros ingresados.
	 * @param event
	 * @throws NoExistenItemsException
	 * @throws DataAccessErrorException
	 * @throws Exception
	 */
	public void buscar(ActionEvent event) throws NoExistenItemsException, DataAccessErrorException, Exception {
		
		try{
			
			/*MusuarioWeb musuarioWeb=(MusuarioWeb)getSessionValue("usuario");
			if(musuarioWeb.getCodigoRol().equals(Const.ROL_EMPLEADO)){
				this.empleado=musuarioWeb.getId();				
			}*/
			
			if(this.vendedor!=null || this.ccss!=null || this.cliente !=null ||
					this.motivoAutorizacion!=null  || this.descripcionMotAutorizacion!=null ||
					this.chofer !=null  || this.patente!=null || fltFechaDesde !=null  || fltFechaHasta!=null ){
				MautorizacionDAO mAutorizacionDAO = new MautorizacionDAO(sessionHib);		
				setItems(mAutorizacionDAO.getAutorizacionesPorFiltros(this.vendedor, this.ccss, this.cliente, this.motivoAutorizacion, this.descripcionMotAutorizacion, this.chofer, this.patente, fltFechaDesde, fltFechaHasta,
					this.producto, this.tipoCompManual, this.nroAutorizacion, this.nroRemitoUnoManual, this.nroRemitoDosManual, null,this.empleado));
			
				setSubItemsNivel1(getItems());
				cargarPagina();
				mostrarLista= new Boolean(true);
			}else{
				mostrarLista= new Boolean(false);
			}
		}
		catch(NoExistenItemsException ex){
			noMostrarLista();
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}catch(DataAccessErrorException ex){
			noMostrarLista();
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex){
			noMostrarLista();
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}
	
	}
	
	/**
	 * Salir de la pantalla.
	 * @author snieto
	 * @return String Pagina a donde vuelve
	 */
	public String volver() {
		String goToPage = Const.ANCLA_LISTA_REPORTES_DISPONIBLES;		
		if(vieneDe.equals("NUE") || vieneDe.equals("MODOK")){
			goToPage = Const.ANCLA_LISTA_REPORTES_DISPONIBLES;
		}else if(vieneDe.equals("MOD")){
			try {
				inicializarValores();
			} catch (DataAccessErrorException e) {				
				e.printStackTrace();
			}
			goToPage = Const.ANCLA_LISTA_AUTORIZACIONES;
		}
		sessionHib.close();	
		return goToPage;
			
	}	
	
	
	public void volverAFormulario(ActionEvent event) {		
		try {		
				setPantalla(1);
				inicializarValores();
							
				descripcionesMotAutorizacion = new ArrayList<SelectItem>();
				descripcionesMotAutorizacion.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
					
				choferes = new ArrayList<SelectItem>();
				choferes.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
							
				patentes = new ArrayList<SelectItem>();
				patentes.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
				
				productos = new ArrayList<SelectItem>();
				productos.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
			
			
		} catch (DataAccessErrorException e1) {
			e1.printStackTrace();
			AddErrorMessage(e1.getMessage());
		}
		
	}
	
	
	
	/**
	 * 
	 *
	 */
	public void cargarPagina(){
		//PAGINACION		
		this.pagina = new Page(getSubItemsNivel1(),1,tamanioPaginacion);
		setSubItemsNivel1(pagina.getPage());
		generarExcel();
		//PAGINACION
	}
	
	
	/**
	 * 
	 *
	 */
	public void noMostrarLista(){
		setItems(new ArrayList());
		mostrarLista= new Boolean(false);
	}
	/*INICIO PAGINACION*/
	
	/**
	 * Paginacion
	 * Metodo: siguiente
	 * Funcion:avanza una pagina
	 * @param event
	 */
	public void siguiente(ActionEvent event){	
		try{
			pagina.setNumpage(pagina.getNumpage()+1);
			this.setSubItemsNivel1(pagina.getPagina(pagina.getNumpage()));
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(mensajeria.getMessage().getString("problemas_paginacion_msg"));
		}	
	}
	
	
	/**
	 * Paginacion
	 * Metodo: ir
	 * Funcion: va a una pagina determinada
	 * @param event
	 */
	public void ir(ActionEvent event){	
		try{
			if(pagina.getNumpage()== null || pagina.getNumpage().toString().trim().equals("")){
				pagina.setNumpage(1);	
			}else if(pagina.getNumpage()>pagina.getLastPage()){
                pagina.setNumpage(pagina.getLastPage());				
			}else if(pagina.getNumpage()<1){
				 pagina.setNumpage(1);
			}
			
			this.setSubItemsNivel1(pagina.getPagina(pagina.getNumpage()));
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(mensajeria.getMessage().getString("problemas_paginacion_msg"));
		}	
		
	}
	/**
	 * Paginacion
	 * Metodo: ultima
	 * Funcion: Va a la ultima pagina
	 * @param event
	 */
	public void ultima(ActionEvent event){	
		try{	
			pagina.setNumpage(pagina.getLastPage());
			this.setSubItemsNivel1(pagina.getPagina(pagina.getNumpage()));
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(mensajeria.getMessage().getString("problemas_paginacion_msg"));
		}	
		
	}
	
	/**
	 * Paginacion
	 * Metodo: primera
	 * Funcion: Va a la primera pagina
	 * @param event
	 */
	public void primera(ActionEvent event){	
		try{	
			pagina.setNumpage(1);
			this.setSubItemsNivel1(pagina.getPagina(pagina.getNumpage()));
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(mensajeria.getMessage().getString("problemas_paginacion_msg"));
		}	
		
	}
	
	
	/**
	 * Paginacion
	 * Metodo: anterior
	 * Funcion: retrocede una pagina
	 * @param event
	 */
	public void anterior(ActionEvent event){	
		try{			
			this.pagina.setNumpage(pagina.getNumpage()-1);
			this.setSubItemsNivel1(pagina.getPagina(pagina.getNumpage()));
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(mensajeria.getMessage().getString("problemas_paginacion_msg"));
		}	
		
	}	
	/* FIN PAGINACION */
	
	/**
	 * Metodo: verTodosLosDatos
	 * Funcion: Traer todos los datos de una autorizacion seleccionada
	 */
	public void verTodosLosDatos(ActionEvent event){
		try{
			UIParameter component = (UIParameter) event.getComponent().findComponent("codAutorizacion");
			Integer codAutorizacion = new Integer(component.getValue().toString()).intValue();
						
			MautorizacionDAO mAutorizacionDAO = new MautorizacionDAO(sessionHib);		
			List lstautorizacion = mAutorizacionDAO.getAutorizacionesPorFiltros(null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, codAutorizacion,null);
			
			if(lstautorizacion.size()>0)
				autorizacion= (MAutorizacionTO)lstautorizacion.get(0);
			
			
			mostrarFrmLista=new Boolean(false);
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}
		
	}	
	
	
	/**
	 * Metodo: verTodosLosDatos
	 * Funcion: Traer todos los datos de una autorizacion seleccionada
	 */
	public void modificarDatos(ActionEvent event){
		try{
			UIParameter component = (UIParameter) event.getComponent().findComponent("codAutorizacion2");
			Integer codAutorizacion = new Integer(component.getValue().toString()).intValue();
						
			MautorizacionDAO mAutorizacionDAO = new MautorizacionDAO(sessionHib);		
			List lstautorizacion = mAutorizacionDAO.getAutorizacionesPorFiltros(null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, codAutorizacion,null);
			
			if(lstautorizacion.size()>0)
				autorizacion= (MAutorizacionTO)lstautorizacion.get(0);
			
			
			mostrarFrmLista=new Boolean(false);
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}
		
	}	
	
	public void volverPrincipal(ActionEvent event) {		
		mostrarFrmLista=new Boolean(true);
			
	}
	
	public void generarExcel() {
		try{			
			System.out.println("Paso por generar excel de listado de autorizaciones !!");			
			ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();			
			this.nombreArchivo = exportarExcelBean.generarExcelReporteAutorizaciones(getItems(),"");			
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

	public List getLstLimiteCarga() {
		return lstLimiteCarga;
	}

	public void setLstLimiteCarga(List lstLimiteCarga) {
		this.lstLimiteCarga = lstLimiteCarga;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public List<SelectItem> getProductos() {
		return productos;
	}

	public void setProductos(List<SelectItem> productos) {
		this.productos = productos;
	}

	public Integer getTipoCompAnular() {
		return tipoCompAnular;
	}

	public void setTipoCompAnular(Integer tipoCompAnular) {
		this.tipoCompAnular = tipoCompAnular;
	}

	public Integer getTipoCompManual() {
		return tipoCompManual;
	}

	public void setTipoCompManual(Integer tipoCompManual) {
		this.tipoCompManual = tipoCompManual;
	}

	public List<SelectItem> getTiposCompAnular() {
		return tiposCompAnular;
	}

	public void setTiposCompAnular(List<SelectItem> tiposCompAnular) {
		this.tiposCompAnular = tiposCompAnular;
	}

	public List<SelectItem> getTiposCompManual() {
		return tiposCompManual;
	}

	public void setTiposCompManual(List<SelectItem> tiposCompManual) {
		this.tiposCompManual = tiposCompManual;
	}

	public String getTipoComprobanteAnular() {
		return tipoComprobanteAnular;
	}

	public void setTipoComprobanteAnular(String tipoComprobanteAnular) {
		this.tipoComprobanteAnular = tipoComprobanteAnular;
	}

	public Boolean getMostrarFrmLista() {
		return mostrarFrmLista;
	}

	public void setMostrarFrmLista(Boolean mostrarFrmLista) {
		this.mostrarFrmLista = mostrarFrmLista;
	}

	public Boolean getMostrarLista() {
		return mostrarLista;
	}

	public void setMostrarLista(Boolean mostrarLista) {
		this.mostrarLista = mostrarLista;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public Page getPagina() {
		return pagina;
	}

	public void setPagina(Page pagina) {
		this.pagina = pagina;
	}

	public Page getPaginaSecundaria() {
		return paginaSecundaria;
	}

	public void setPaginaSecundaria(Page paginaSecundaria) {
		this.paginaSecundaria = paginaSecundaria;
	}

	public Integer getTamanioPaginacion() {
		return tamanioPaginacion;
	}

	public void setTamanioPaginacion(Integer tamanioPaginacion) {
		this.tamanioPaginacion = tamanioPaginacion;
	}

	public String getNroAutorizacion() {
		return nroAutorizacion;
	}

	public void setNroAutorizacion(String nroAutorizacion) {
		this.nroAutorizacion = nroAutorizacion;
	}

	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}

	public List<SelectItem> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<SelectItem> empleados) {
		this.empleados = empleados;
	}

	public String getNombreChofer() {
		return nombreChofer;
	}

	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}

	public String getNumeroPatente() {
		return numeroPatente;
	}

	public void setNumeroPatente(String numeroPatente) {
		this.numeroPatente = numeroPatente;
	}


	public String getApellidoVendedor() {
		return apellidoVendedor;
	}

	public void setApellidoVendedor(String apellidoVendedor) {
		this.apellidoVendedor = apellidoVendedor;
	}

	public String getNombreVendedor() {
		return nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}

	public Boolean getNuevoVendedor() {
		return nuevoVendedor;
	}

	public void setNuevoVendedor(Boolean nuevoVendedor) {
		this.nuevoVendedor = nuevoVendedor;
	}

	public MAutorizacionTO getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(MAutorizacionTO autorizacion) {
		this.autorizacion = autorizacion;
	}

	

	public String getVieneDe() {
		return vieneDe;
	}


	public void setVieneDe(String vieneDe) {
		this.vieneDe = vieneDe;
	}


	public Integer getCodEmisor() {
		return codEmisor;
	}


	public void setCodEmisor(Integer codEmisor) {
		this.codEmisor = codEmisor;
	}


	public Integer getIdCodigoAuto() {
		return idCodigoAuto;
	}


	public void setIdCodigoAuto(Integer idCodigoAuto) {
		this.idCodigoAuto = idCodigoAuto;
	}
	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Integer getCodAutorizacion() {
		return codAutorizacion;
	}


	public void setCodAutorizacion(Integer codAutorizacion) {
		this.codAutorizacion = codAutorizacion;
	}



}
