package com.refinor.extranet.faces.base;


import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Session;

import com.itsolver.util.io.FileUtil;
import com.refinor.extranet.data.Marticulos;
import com.refinor.extranet.data.Mccss;
import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.Mexclusion;
import com.refinor.extranet.data.MexclusionTipo;
import com.refinor.extranet.data.MgrupoUn;
import com.refinor.extranet.data.Mprovincias;
import com.refinor.extranet.data.MunidadN;
import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.TipoComprobante;
import com.refinor.extranet.data.dao.AlmacenDAO;
import com.refinor.extranet.data.dao.MarticulosDAO;
import com.refinor.extranet.data.dao.MccssDAO;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MexclusionTipoDAO;
import com.refinor.extranet.data.dao.MgrupoUnDAO;
import com.refinor.extranet.data.dao.MlimiteCargaDAO;
import com.refinor.extranet.data.dao.MpedidosDAO;
import com.refinor.extranet.data.dao.MprovinciasDAO;
import com.refinor.extranet.data.dao.MunidadNDAO;
import com.refinor.extranet.data.dao.TipoComprobanteDAO;
import com.refinor.extranet.to.AnioTO;
import com.refinor.extranet.to.ArchivoTO;
import com.refinor.extranet.to.MEmpleadosTO;
import com.refinor.extranet.to.MProductoTO;
import com.refinor.extranet.to.MesTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.paginado.Page;
import com.sun.management.GarbageCollectorMXBean;

public abstract class AbstListado extends AbstReporte {
	protected Session sessionHib;

	protected String fltNombreChofer;
	protected String fltDescripcionUnidadNegocio;
	protected String fltDescripcionGrupoUN;
	protected Integer fltDNI;
	protected String fltCUIT;
	protected String fltPatente;
	protected String fltCodBarra;
	protected Date fltFechaDesde;
	protected Date fltFechaHasta;	
	protected Date fltFechaDesdeDos;
	protected Date fltFechaHastaDos;
	protected Integer fltNroReciboDesde;
	protected Integer fltNroReciboHasta;
	protected String fltClienteDesde;
	protected String fltClienteHasta;
	protected String fltCodClienteAlfa;
	
	protected Integer fltNroFactura;
	
	protected Integer fltNroAsientoDesde;
	protected Integer fltNroAsientoHasta;
	
	protected String fltCuentaDesde;
	protected String fltCuentaHasta;
	protected Integer fltNroSucursal;
	
	protected String codCliente;
	protected String nomdCliente;
	protected Boolean choferActivo;
	protected Boolean choferBaja;
	protected Boolean choferInicializado;	
	protected Boolean choferNoInicializado;
	protected Boolean vehiculoActivo;	
	protected Boolean vehiculoBaja;	
	protected Boolean vehiculoInicializado;	
	protected Boolean vehiculoNoInicializado;
	
	protected Integer provincia;	
	protected List<SelectItem> provincias;		
	
	/*Mi paginacion*/	
	protected Page paginaSecundaria;	
	protected Page pagina;	
	protected Integer tamanioPaginacion;
	/*end mi paginacion*/
	
	/*exportar excel*/
	protected Boolean mostrarLista;
	protected Boolean mostrarFrmLista;
	protected Boolean mostrarListaSecundaria;
	
	protected Boolean mostrarFrmListaSecundaria;
	
	protected String nombreArchivo;
	protected String nombreArchivoSecundario;
	protected Messages mensajeria;
	protected Integer estado;	
	protected List<SelectItem> estados;
	protected Integer estadoIni;	
	protected List<SelectItem> estadosIni;
	
	protected BigDecimal saldoCtaCte;
	protected BigDecimal remitosAfacturar;
	protected BigDecimal saldoCliente;
	protected int tipoUsuarioLogueado;
	protected int codigoRol;
	
	protected Integer cliente;	
	protected List<SelectItem> clientes;
	
	private List<SelectItem> gruposUnidadNegocio;
	private Integer grupoUnidadNegocio;

	private List<SelectItem> unidadesNegocio;
	private Integer unidadNegocio;
	
	protected Integer retencion;	
	protected List<SelectItem> retenciones;	
	
	protected Integer condicion;	
	protected List<SelectItem> condiciones;	
	
	protected Integer ccss;	
	protected List<SelectItem> lstccss;
	
	protected Integer producto;
	protected List<SelectItem> productos;
	
	protected Integer tipoComprobante;	
	protected List<SelectItem> tiposComprobante;
	
	protected Integer estadoEntregaCombustible;	
	protected List<SelectItem> estadosEntregaCombustible;
	
	protected Integer estadoRemito;	
	protected List<SelectItem> estadosRemito;
	protected String fechaActualStr;
	
	protected Integer tipoExclusion;	
	protected List<SelectItem> tiposExclusion;	
	
	protected Integer mes;	
	protected List<SelectItem> lstMeses;
	protected Integer anio;	
	protected List<SelectItem> lstAnios;
	
	protected Integer optRefacturacion;	
	protected List<SelectItem> optRefacturaciones;
	
	private List lstArchivos;	
	private String mensaje2000Registros;
	
	public String getMensaje2000Registros() {
		return mensaje2000Registros;
	}


	public void setMensaje2000Registros(String mensaje2000Registros) {
		this.mensaje2000Registros = mensaje2000Registros;
	}


	public List getLstArchivos() {
		return lstArchivos;
	}


	public void setLstArchivos(List lstArchivos) {
		this.lstArchivos = lstArchivos;
	}


	public AbstListado() {
		try{
				 		
			sessionHib = (new AlmacenDAO()).getSession();			
			inicializacion();	
				
			cargarComboClientes();
			cargarComboRetenciones();
			cargarComboCondiciones();
			cargarComboGrupo(null);
			cargarComboCCSS();
			cargarProductos();
			cargarComprobantes();
			cargarEstadosCombustible();
			cargarEstadosRemitos();
			cargarComboJurisdicciones();			
			////System.out.println("Paso por el AbstListado()");
		}/*catch(NoExistenItemsException ex){
			noMostrarLista();
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}*/catch(DataAccessErrorException ex){
			//noMostrarLista();
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex){
			//noMostrarLista();
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}
	}

	
	/*private void fecha(){
		try{
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}*/
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
	
	
	/**
	 * Metodo:cargarComboCCSS
	 * Funcion: cargar el combo de centros de servicios
	 *
	 */
	private void cargarComboJurisdicciones(){
		MprovinciasDAO mprovinciasDAO = new MprovinciasDAO(sessionHib);
		 provincias = new ArrayList<SelectItem>();
		 provincias.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
		try {
			Mprovincias  mProvincias;
			List lstDatos = mprovinciasDAO.getJuridcciones();		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mProvincias = (Mprovincias)it.next();
				provincias.add(new SelectItem(new Integer(mProvincias.getCodigo()),mProvincias.getDescripcion()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar las jurisdicciones.");
		}	
		
	}
	
	/**
	 * 
	 *
	 */	
	public void cargarEstadosCombustible(){		 
		 estadosEntregaCombustible = new ArrayList<SelectItem>();
		 estadosEntregaCombustible.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 estadosEntregaCombustible.add(new SelectItem(new Integer(0),Const.ESTADO_ENVIADO));
		 estadosEntregaCombustible.add(new SelectItem(new Integer(1),Const.ESTADO_RECIBIDO));
		
		
	}
	
	public void cargarEstadosRemitos(){		 
		estadosRemito = new ArrayList<SelectItem>();
		estadosRemito.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		estadosRemito.add(new SelectItem(new Integer(0),Const.ESTADO_FACTURADO));
		estadosRemito.add(new SelectItem(new Integer(1),Const.ESTADO_NO_FACTURADO));	
	}
	
	/**
	 * Metodo: cargarTiposComprobantesVenta
	 * Funcion: Cargar el combo de tipos de comprobantes de venta. (Tabla TipoComprobante)
	 * Solo debe cargar: Remito, Factura, remito credito y nota de credito
	 */
	public void cargarComprobantes(){
		 TipoComprobanteDAO tipoComprobanteDAO = new TipoComprobanteDAO(sessionHib);
		 tiposComprobante = new ArrayList<SelectItem>();
		 
		try {
			TipoComprobante tipoComprobante;
			List lstDatos = tipoComprobanteDAO.getTiposComprobantesParaCombustible();		
			Iterator it = lstDatos.iterator();	
			tiposComprobante.add(new SelectItem(new Integer(-1),Const.SELECCIONE));		
			
			while(it.hasNext()) {
				tipoComprobante = (TipoComprobante)it.next();
				tiposComprobante.add(new SelectItem(new Integer(tipoComprobante.getId()),tipoComprobante.getNombre()));
			}					
			
			//tipoCompManual= new Integer(1);
		}catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los comprobantes.");
		}	
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
			 List lstDatos = mArticulosDAO.getArticulos();		
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
	
	/**
	 * 
	 * @param codCliente
	 * @return
	 * @throws DataAccessErrorException
	 */
	public String obtenerNumeroDocumento(Integer codCliente) throws DataAccessErrorException{
		try{
			MclientesDAO mclienteDAO= new MclientesDAO(sessionHib);
			Mclientes mcliente= new Mclientes();
			mcliente= mclienteDAO.get(codCliente, sessionHib);
			return mcliente.getCuit();
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}		
	}
	
	/**
	 * 
	 * @param codCliente
	 * @return
	 * @throws DataAccessErrorException
	 */
	public String obtenerCodClienteAlfa(Integer codCliente) throws DataAccessErrorException{
		try{
			MclientesDAO mclienteDAO= new MclientesDAO(sessionHib);
			Mclientes mcliente= new Mclientes();
			mcliente= mclienteDAO.get(codCliente, sessionHib);
			return mcliente.getCodClienteAlfa();
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}		
	}
	
	public Double obtenerTopeCredito(Integer codCliente) throws DataAccessErrorException{
		try{
			if(!sessionHib.isOpen())sessionHib = (new MclientesDAO()).getSession();	
			MclientesDAO mclienteDAO= new MclientesDAO(sessionHib);
			Mclientes mcliente= new Mclientes();
			mcliente= mclienteDAO.get(codCliente, sessionHib);
			return mcliente.getTopeCredito();
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}		
	}
	
	/**
	 * Cargar el combo de clientes
	 *
	 */	
	public void cargarComboClientes(){
		 MclientesDAO mClientesDAO = new MclientesDAO(sessionHib);
		 clientes = new ArrayList<SelectItem>();
		 clientes.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
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
	 * Cargar el combo de impuestos - tipo de exclusion
	 *
	 */	
	public void cargarComboTipoImpuestos(){
		MexclusionTipoDAO exclusionTipoDAO =  new MexclusionTipoDAO(sessionHib);
		tiposExclusion  = new ArrayList<SelectItem>();
		tiposExclusion.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 
		try {
			MexclusionTipo mexclusion;
			List lstDatos = exclusionTipoDAO.getTiposExclusiones();		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mexclusion = (MexclusionTipo)it.next();
				tiposExclusion.add(new SelectItem(new Integer(mexclusion.getCodigo()),mexclusion.getDescripcion()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los tipos de exclsion.");
		}	
		
	}
	
	/**
	 *  cargar el combo de retenciones
	 *
	 */	
	public void cargarComboRetenciones(){		 
		 retenciones = new ArrayList<SelectItem>();
		 retenciones.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 retenciones.add(new SelectItem(new Integer(10),Const.RETENCION_IVA));
		 retenciones.add(new SelectItem(new Integer(11),Const.RETENCION_GANANCIAS));
		 retenciones.add(new SelectItem(new Integer(12),Const.RETENCION_INGRESOS_BRUTOS));
		 retenciones.add(new SelectItem(new Integer(13),Const.RETENCION_SIJP));		
		 retenciones.add(new SelectItem(new Integer(14),Const.RETENCION_SEGURIDAD_SOCIAL));
		 retenciones.add(new SelectItem(new Integer(15),Const.RETENCION_TRS));
		 retenciones.add(new SelectItem(new Integer(16),Const.RETENCION_TISSH));
		 retenciones.add(new SelectItem(new Integer(17),Const.RETENCION_TMT));
		 retenciones.add(new SelectItem(new Integer(18),"Percepción TEM SM TUC"));
	}
	
	/**
	 * 
	 *
	 */
	public void cargarComboCondiciones(){		 
		 condiciones = new ArrayList<SelectItem>();
		 condiciones.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		 condiciones.add(new SelectItem(new Integer(1),Const.CONDICION_CTA_CTE));	
		 condiciones.add(new SelectItem(new Integer(2),Const.CONDICION_CONTADO));
		
	}
	
	public void cargarSusGrupos(ValueChangeEvent event){
		try{
			Integer codCliente = ((Integer)event.getNewValue()).intValue(); 	
			//System.out.println("codCliente ->"+codCliente);
			cargarComboGrupo(codCliente);
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(new DataAccessErrorException().getMessage());
			
		}
	}
	
	private void cargarComboGrupo(Integer codClienteInt){		
		 MgrupoUnDAO mgrupoUnDAO = new MgrupoUnDAO(sessionHib);
		 gruposUnidadNegocio = new ArrayList<SelectItem>();
		 gruposUnidadNegocio.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		try {
			MgrupoUn mgrupoUn;
			MusuarioWeb usuario = (MusuarioWeb)getSession().getAttribute(Const.USUARIO);	
			String numeroDoc = null;
			
			if(usuario.getTipo()==1){
				numeroDoc=usuario.getCuit();			
			}else if(usuario.getTipo()==0 && codClienteInt!=null){	
				//System.out.println("codCliente ->"+codClienteInt);
				numeroDoc=obtenerNumeroDocumento(codClienteInt);
			}
			
			List lstDatos = mgrupoUnDAO.getGruposUNPorCliente(numeroDoc);
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mgrupoUn = (MgrupoUn)it.next();
				gruposUnidadNegocio.add(new SelectItem(new Integer(mgrupoUn.getCodigo()),mgrupoUn.getDescripcion()));
			}
					
			unidadesNegocio = new ArrayList<SelectItem>();
			unidadesNegocio.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
		} catch(Exception ex) {
			ex.printStackTrace();
			unidadesNegocio = new ArrayList<SelectItem>();
			unidadesNegocio.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
			ex.printStackTrace();				
			//AddErrorMessage("No se han podido recuperar los Grupos de Unidad de Negocio.");
		}	
		
	}
	
	
	
	public void cargarUnidadesNegocio(ValueChangeEvent event){
		
		 Integer nroGrupo = ((Integer)event.getNewValue()).intValue(); 
		 try {
				
			 MunidadNDAO munidadNDao = new MunidadNDAO(sessionHib);
			 unidadesNegocio = new ArrayList<SelectItem>();
			 unidadesNegocio.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
			 
			try {
				MunidadN munidadN;				
				List lstDatos = munidadNDao.getUnidadesNegocioPorGrupo(nroGrupo);				
				
				if(lstDatos!=null && lstDatos.size()>0){
					Iterator it = lstDatos.iterator();			
					while(it.hasNext()) {
						munidadN = (MunidadN)it.next();
						unidadesNegocio.add(new SelectItem(new Integer(munidadN.getCodigo()),munidadN.getDescripcion()));
					}
				}
				
			} catch(Exception ex) {
				unidadesNegocio = new ArrayList<SelectItem>();
				unidadesNegocio.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
				ex.printStackTrace();				
				//AddErrorMessage(mensajeria.getMessage().getString("grupo_no_tiene_unidades_negocio"));
			}		
					
		} catch(Exception ex) {
			ex.printStackTrace();
			AddErrorMessage("No se recupero el Grupo.");
		}	
	}
	
	/**
	 * 
	 * @param event
	 */
	public void presentarListaInforme(ActionEvent event){
		try{		
			cargcarListaPotFiltros();
			setSubItemsNivel1(getItems());
			cargarPagina();
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
	 * Salir de la pantalla de Cupon Factura.
	 * @author snieto
	 * @return String Pagina a donde vuelve
	 */
	public String volver() {		
		String goToPage = Const.ANCLA_LISTA_REPORTES_DISPONIBLES;	
		setItems(null);
		setItems2(null);
		setSubItemsNivel1(null);
		setSubItemsNivel2(null);
		
		  fltNombreChofer 	=null;
		  fltDescripcionUnidadNegocio 	=null;
		  fltDescripcionGrupoUN 	=null;
		  fltDNI 	=null;
		  fltCUIT 	=null;
		  fltPatente 	=null;
		  fltCodBarra 	=null;
		  fltFechaDesde 	=null;
		  fltFechaHasta 	=null;
		  fltFechaDesdeDos 	=null;
		  fltFechaHastaDos 	=null;
		  fltNroReciboDesde 	=null;
		  fltNroReciboHasta 	=null;
		  fltClienteDesde 	=null;
		  fltClienteHasta 	=null;
		  fltCodClienteAlfa 	=null;
		  fltNroFactura 	=null;
		  fltNroAsientoDesde 	=null;
		  fltNroAsientoHasta 	=null;
		  fltCuentaDesde 	=null;
		  fltCuentaHasta 	=null;
		  fltNroSucursal 	=null;
		  codCliente 	=null;
		  nomdCliente 	=null;
		  choferActivo 	=null;
		  choferBaja 	=null;
		  choferInicializado 	=null;
		  choferNoInicializado 	=null;
		  vehiculoActivo 	=null;
		  vehiculoBaja 	=null;
		  vehiculoInicializado 	=null;
		  vehiculoNoInicializado 	=null;
		  provincia 	=null;
		  provincias 	=null;
		  paginaSecundaria 	=null;
		  pagina 	=null;
		  tamanioPaginacion 	=null;
		  mostrarLista 	=null;
		  mostrarFrmLista 	=null;
		  mostrarListaSecundaria 	=null;
		  mostrarFrmListaSecundaria 	=null;
		  nombreArchivo 	=null;
		  nombreArchivoSecundario 	=null;
		  mensajeria 	=null;
		  estado 	=null;
		  estados 	=null;
		  estadoIni 	=null;
		  estadosIni 	=null;
		  saldoCtaCte 	=null;
		  remitosAfacturar 	=null;
		  saldoCliente 	=null;		  
		  cliente 	=null;
		  clientes 	=null;
		  gruposUnidadNegocio 	=null;
		  grupoUnidadNegocio 	=null;
		  unidadesNegocio 	=null;
		  unidadNegocio 	=null;
		  retencion 	=null;
		  retenciones 	=null;
		  condicion 	=null;
		  condiciones 	=null;
		  ccss 	=null;
		  lstccss 	=null;
		  producto 	=null;
		  productos 	=null;
		  tipoComprobante 	=null;
		  tiposComprobante 	=null;
		  estadoEntregaCombustible 	=null;
		  estadosEntregaCombustible 	=null;
		  estadoRemito 	=null;
		  estadosRemito 	=null;
		  fechaActualStr 	=null;
		  tipoExclusion 	=null;
		  tiposExclusion 	=null;
		  mes 	=null;
		  lstMeses 	=null;
		  anio 	=null;
		  lstAnios 	=null;
		  optRefacturacion 	=null;
		  optRefacturaciones 	=null;
		  lstArchivos 	=null;
		  mensaje2000Registros 	=null;	
		
		  Runtime.getRuntime().gc();
		

		
		
		try{	
			if(getLstArchivos()!=null){
				//borrar los archivos				
				ArchivoTO archivo = new ArchivoTO();
				File fichero = null;
				for(int i=0;i<getLstArchivos().size();i++){
					archivo = (ArchivoTO) getLstArchivos().get(i);					
					fichero = new File(archivo.getPathFisico());
					if (fichero.delete())   
						System.out.println("El fichero ha sido borrado satisfactoriamente");
					else   
						System.out.println("El fichero no puede ser borrado");					
				}
			}
		}catch(Exception ex){	
			  ex.printStackTrace();
			  AddErrorMessage(ex.getMessage());
			}
			
		return goToPage;
			
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
	
	public void cargarPaginaMejorado(){
		//PAGINACION		
		this.pagina = new Page(getSubItemsNivel1(),1,tamanioPaginacion);
		setSubItemsNivel1(pagina.getPage());		
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
	
/*INICIO PAGINACION*/
	
	/**
	 * Paginacion
	 * Metodo: siguiente
	 * Funcion:avanza una pagina
	 * @param event
	 */
	public void siguienteSecundaria(ActionEvent event){	
		try{
			paginaSecundaria.setNumpage(paginaSecundaria.getNumpage()+1);
			this.setSubItemsNivel2(paginaSecundaria.getPagina(paginaSecundaria.getNumpage()));
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
	public void ultimaSecundaria(ActionEvent event){	
		try{	
			paginaSecundaria.setNumpage(paginaSecundaria.getLastPage());
			this.setSubItemsNivel2(paginaSecundaria.getPagina(paginaSecundaria.getNumpage()));
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
	public void primeraSecundaria(ActionEvent event){	
		try{	
			paginaSecundaria.setNumpage(1);
			this.setSubItemsNivel2(paginaSecundaria.getPagina(paginaSecundaria.getNumpage()));
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
	public void anteriorSecundaria(ActionEvent event){	
		try{			
			this.paginaSecundaria.setNumpage(paginaSecundaria.getNumpage()-1);
			this.setSubItemsNivel2(paginaSecundaria.getPagina(paginaSecundaria.getNumpage()));
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(mensajeria.getMessage().getString("problemas_paginacion_msg"));
		}	
		
	}	
	/* FIN PAGINACION */

	public void inicializacion() throws DataAccessErrorException{
		try{			
			mensajeria = new Messages();
			
			mostrarLista=new Boolean(false);
			mostrarFrmLista=new Boolean(true);
			mostrarListaSecundaria=new Boolean(false);
			mostrarFrmListaSecundaria=new Boolean(false);
			
			Properties properties= (new FileUtil()).getPropertiesFile();		
			this.tamanioPaginacion= Integer.parseInt((String)properties.get(Const.TAMANIO_PAGINA_ARCHIVO));		
			
			
			//codCliente="S001";
			MusuarioWeb usuario = (MusuarioWeb)getSession().getAttribute(Const.USUARIO);			
			codCliente=usuario.getCodClienteAlfa();	
			tipoUsuarioLogueado=usuario.getTipo();
			codigoRol=usuario.getCodigoRol();
			cargarOpciones();
			
			this.pagina= new Page();
		}catch(Exception ex){
			noMostrarLista();
			ex.printStackTrace();
			AddErrorMessage(new DataAccessErrorException().getMessage());
		}
	} 
	
	/**
	 * 
	 *
	 */
	public void cargarOpciones(){
		estados = new ArrayList<SelectItem>();
		estados.add(new SelectItem(new Integer(-1),"Ver Todos"));
		estados.add(new SelectItem(new Integer(1),"Activos"));
		estados.add(new SelectItem(new Integer(0),"No Activos"));	
		estado=new Integer(-1);
		
		estadosIni = new ArrayList<SelectItem>();
		estadosIni.add(new SelectItem(new Integer(-1),"Ver Todos"));
		estadosIni.add(new SelectItem(new Integer(1),"Inicializados"));
		estadosIni.add(new SelectItem(new Integer(0),"No Inicializados"));	
		estadoIni=new Integer(-1);
	}
	
	public abstract void generarExcel();
	
	public abstract void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception;
	
	public String getFltDescripcionGrupoUN() {
		return fltDescripcionGrupoUN;
	}

	public void setFltDescripcionGrupoUN(String fltDescripcionGrupoUN) {
		this.fltDescripcionGrupoUN = fltDescripcionGrupoUN;
	}

	public String getFltDescripcionUnidadNegocio() {
		return fltDescripcionUnidadNegocio;
	}

	public void setFltDescripcionUnidadNegocio(String fltDescripcionUnidadNegocio) {
		this.fltDescripcionUnidadNegocio = fltDescripcionUnidadNegocio;
	}

	public String getFltNombreChofer() {
		return fltNombreChofer;
	}

	public void setFltNombreChofer(String fltNombreChofer) {
		this.fltNombreChofer = fltNombreChofer;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public Boolean getChoferActivo() {
		return choferActivo;
	}

	public void setChoferActivo(Boolean choferActivo) {
		this.choferActivo = choferActivo;
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

	public Integer getTamanioPaginacion() {
		return tamanioPaginacion;
	}

	public void setTamanioPaginacion(Integer tamanioPaginacion) {
		this.tamanioPaginacion = tamanioPaginacion;
	}

	public Integer getFltDNI() {
		return fltDNI;
	}

	public void setFltDNI(Integer fltDNI) {
		this.fltDNI = fltDNI;
	}
	
	@Override
	public Boolean getPuedeIngresar() {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean getMostrarFrmLista() {
		return mostrarFrmLista;
	}

	public void setMostrarFrmLista(Boolean mostrarFrmLista) {
		this.mostrarFrmLista = mostrarFrmLista;
	}

	public String getFltPatente() {
		return fltPatente;
	}

	public void setFltPatente(String fltPatente) {
		this.fltPatente = fltPatente;
	}

	public Boolean getVehiculoActivo() {
		return vehiculoActivo;
	}

	public void setVehiculoActivo(Boolean vehiculoActivo) {
		this.vehiculoActivo = vehiculoActivo;
	}

	public Boolean getChoferBaja() {
		return choferBaja;
	}

	public void setChoferBaja(Boolean choferBaja) {
		this.choferBaja = choferBaja;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public List<SelectItem> getEstados() {
		return estados;
	}

	public void setEstados(List<SelectItem> estados) {
		this.estados = estados;
	}

	public Boolean getVehiculoBaja() {
		return vehiculoBaja;
	}

	public void setVehiculoBaja(Boolean vehiculoBaja) {
		this.vehiculoBaja = vehiculoBaja;
	}

	public Integer getEstadoIni() {
		return estadoIni;
	}

	public void setEstadoIni(Integer estadoIni) {
		this.estadoIni = estadoIni;
	}

	public List<SelectItem> getEstadosIni() {
		return estadosIni;
	}

	public void setEstadosIni(List<SelectItem> estadosIni) {
		this.estadosIni = estadosIni;
	}

	public String getFltCodBarra() {
		return fltCodBarra;
	}

	public void setFltCodBarra(String fltCodBarra) {
		this.fltCodBarra = fltCodBarra;
	}

	public Boolean getVehiculoInicializado() {
		return vehiculoInicializado;
	}

	public void setVehiculoInicializado(Boolean vehiculoInicializado) {
		this.vehiculoInicializado = vehiculoInicializado;
	}

	public Boolean getVehiculoNoInicializado() {
		return vehiculoNoInicializado;
	}

	public void setVehiculoNoInicializado(Boolean vehiculoNoInicializado) {
		this.vehiculoNoInicializado = vehiculoNoInicializado;
	}

	public Session getSessionHib() {
		return sessionHib;
	}

	public void setSessionHib(Session sessionHib) {
		this.sessionHib = sessionHib;
	}

	public Messages getMensajeria() {
		return mensajeria;
	}

	public void setMensajeria(Messages mensajeria) {
		this.mensajeria = mensajeria;
	}

	public Boolean getMostrarFrmListaSecundaria() {
		return mostrarFrmListaSecundaria;
	}

	public void setMostrarFrmListaSecundaria(Boolean mostrarFrmListaSecundaria) {
		this.mostrarFrmListaSecundaria = mostrarFrmListaSecundaria;
	}

	public Boolean getMostrarListaSecundaria() {
		return mostrarListaSecundaria;
	}

	public void setMostrarListaSecundaria(Boolean mostrarListaSecundaria) {
		this.mostrarListaSecundaria = mostrarListaSecundaria;
	}

	public Page getPaginaSecundaria() {
		return paginaSecundaria;
	}

	public void setPaginaSecundaria(Page paginaSecundaria) {
		this.paginaSecundaria = paginaSecundaria;
	}

	public String getNombreArchivoSecundario() {
		return nombreArchivoSecundario;
	}

	public void setNombreArchivoSecundario(String nombreArchivoSecundario) {
		this.nombreArchivoSecundario = nombreArchivoSecundario;
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

	public BigDecimal getRemitosAfacturar() {
		return remitosAfacturar;
	}

	public void setRemitosAfacturar(BigDecimal remitosAfacturar) {
		this.remitosAfacturar = remitosAfacturar;
	}

	public BigDecimal getSaldoCliente() {
		return saldoCliente;
	}

	public void setSaldoCliente(BigDecimal saldoCliente) {
		this.saldoCliente = saldoCliente;
	}

	public BigDecimal getSaldoCtaCte() {
		return saldoCtaCte;
	}

	public void setSaldoCtaCte(BigDecimal saldoCtaCte) {
		this.saldoCtaCte = saldoCtaCte;
	}

	public int getTipoUsuarioLogueado() {
		return tipoUsuarioLogueado;
	}

	public void setTipoUsuarioLogueado(int tipoUsuarioLogueado) {
		this.tipoUsuarioLogueado = tipoUsuarioLogueado;
	}

	public Boolean getChoferInicializado() {
		return choferInicializado;
	}

	public void setChoferInicializado(Boolean choferInicializado) {
		this.choferInicializado = choferInicializado;
	}

	public Boolean getChoferNoInicializado() {
		return choferNoInicializado;
	}

	public void setChoferNoInicializado(Boolean choferNoInicializado) {
		this.choferNoInicializado = choferNoInicializado;
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
	public List<SelectItem> getGruposUnidadNegocio() {
		return gruposUnidadNegocio;
	}

	public void setGruposUnidadNegocio(List<SelectItem> gruposUnidadNegocio) {
		this.gruposUnidadNegocio = gruposUnidadNegocio;
	}

	public Integer getGrupoUnidadNegocio() {
		return grupoUnidadNegocio;
	}

	public void setGrupoUnidadNegocio(Integer grupoUnidadNegocio) {
		this.grupoUnidadNegocio = grupoUnidadNegocio;
	}

	public List<SelectItem> getUnidadesNegocio() {
		return unidadesNegocio;
	}

	public void setUnidadesNegocio(List<SelectItem> unidadesNegocio) {
		this.unidadesNegocio = unidadesNegocio;
	}

	public Integer getUnidadNegocio() {
		return unidadNegocio;
	}

	public void setUnidadNegocio(Integer unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}

	public String getFltClienteDesde() {
		return fltClienteDesde;
	}

	public void setFltClienteDesde(String fltClienteDesde) {
		this.fltClienteDesde = fltClienteDesde;
	}

	public String getFltClienteHasta() {
		return fltClienteHasta;
	}

	public void setFltClienteHasta(String fltClienteHasta) {
		this.fltClienteHasta = fltClienteHasta;
	}

	public Integer getFltNroReciboDesde() {
		return fltNroReciboDesde;
	}

	public void setFltNroReciboDesde(Integer fltNroReciboDesde) {
		this.fltNroReciboDesde = fltNroReciboDesde;
	}

	public Integer getFltNroReciboHasta() {
		return fltNroReciboHasta;
	}

	public void setFltNroReciboHasta(Integer fltNroReciboHasta) {
		this.fltNroReciboHasta = fltNroReciboHasta;
	}

	public Integer getRetencion() {
		return retencion;
	}

	public void setRetencion(Integer retencion) {
		this.retencion = retencion;
	}

	public List<SelectItem> getRetenciones() {
		return retenciones;
	}

	public void setRetenciones(List<SelectItem> retenciones) {
		this.retenciones = retenciones;
	}

	public Integer getCondicion() {
		return condicion;
	}

	public void setCondicion(Integer condicion) {
		this.condicion = condicion;
	}

	public List<SelectItem> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<SelectItem> condiciones) {
		this.condiciones = condiciones;
	}

	public Integer getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(Integer tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public List<SelectItem> getTiposComprobante() {
		return tiposComprobante;
	}

	public void setTiposComprobante(List<SelectItem> tiposComprobante) {
		this.tiposComprobante = tiposComprobante;
	}

	public Integer getEstadoEntregaCombustible() {
		return estadoEntregaCombustible;
	}

	public void setEstadoEntregaCombustible(Integer estadoEntregaCombustible) {
		this.estadoEntregaCombustible = estadoEntregaCombustible;
	}

	public List<SelectItem> getEstadosEntregaCombustible() {
		return estadosEntregaCombustible;
	}

	public void setEstadosEntregaCombustible(
			List<SelectItem> estadosEntregaCombustible) {
		this.estadosEntregaCombustible = estadosEntregaCombustible;
	}

	public Integer getEstadoRemito() {
		return estadoRemito;
	}

	public void setEstadoRemito(Integer estadoRemito) {
		this.estadoRemito = estadoRemito;
	}

	public List<SelectItem> getEstadosRemito() {
		return estadosRemito;
	}

	public void setEstadosRemito(List<SelectItem> estadosRemito) {
		this.estadosRemito = estadosRemito;
	}

	public Integer getProvincia() {
		return provincia;
	}

	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}

	public List<SelectItem> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<SelectItem> provincias) {
		this.provincias = provincias;
	}

	public String getFltCuentaDesde() {
		return fltCuentaDesde;
	}

	public void setFltCuentaDesde(String fltCuentaDesde) {
		this.fltCuentaDesde = fltCuentaDesde;
	}

	public String getFltCuentaHasta() {
		return fltCuentaHasta;
	}

	public void setFltCuentaHasta(String fltCuentaHasta) {
		this.fltCuentaHasta = fltCuentaHasta;
	}

	public Integer getFltNroSucursal() {
		return fltNroSucursal;
	}

	public void setFltNroSucursal(Integer fltNroSucursal) {
		this.fltNroSucursal = fltNroSucursal;
	}

	public Date getFltFechaDesdeDos() {
		return fltFechaDesdeDos;
	}

	public void setFltFechaDesdeDos(Date fltFechaDesdeDos) {
		this.fltFechaDesdeDos = fltFechaDesdeDos;
	}

	public Date getFltFechaHastaDos() {
		return fltFechaHastaDos;
	}

	public void setFltFechaHastaDos(Date fltFechaHastaDos) {
		this.fltFechaHastaDos = fltFechaHastaDos;
	}

	public String getFechaActualStr() {
		try{
			/*SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmm");
			String aux =  sdf.format(getSessionValue(Const.FECHA_ACTUAL));
			Date fechaActualDt = sdf.parse(aux);	*/
			
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			this.fechaActualStr = sdf.format(new Date());
			//this.fechaActualStr = fechaActualStr;
				
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return fechaActualStr;
	}

	
	public void setFechaActualStr(String fechaActualStr) {
		this.fechaActualStr = fechaActualStr;
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


	public Integer getFltNroAsientoDesde() {
		return fltNroAsientoDesde;
	}


	public void setFltNroAsientoDesde(Integer fltNroAsientoDesde) {
		this.fltNroAsientoDesde = fltNroAsientoDesde;
	}


	public Integer getFltNroAsientoHasta() {
		return fltNroAsientoHasta;
	}


	public void setFltNroAsientoHasta(Integer fltNroAsientoHasta) {
		this.fltNroAsientoHasta = fltNroAsientoHasta;
	}


	public Integer getFltNroFactura() {
		return fltNroFactura;
	}


	public void setFltNroFactura(Integer fltNroFactura) {
		this.fltNroFactura = fltNroFactura;
	}


	public Integer getTipoExclusion() {
		return tipoExclusion;
	}


	public void setTipoExclusion(Integer tipoExclusion) {
		this.tipoExclusion = tipoExclusion;
	}


	public List<SelectItem> getTiposExclusion() {
		return tiposExclusion;
	}


	public void setTiposExclusion(List<SelectItem> tiposExclusion) {
		this.tiposExclusion = tiposExclusion;
	}


	public Integer getAnio() {
		return anio;
	}


	public void setAnio(Integer anio) {
		this.anio = anio;
	}


	public List<SelectItem> getLstAnios() {
		return lstAnios;
	}


	public void setLstAnios(List<SelectItem> lstAnios) {
		this.lstAnios = lstAnios;
	}


	public List<SelectItem> getLstMeses() {
		return lstMeses;
	}


	public void setLstMeses(List<SelectItem> lstMeses) {
		this.lstMeses = lstMeses;
	}


	public Integer getMes() {
		return mes;
	}


	public void setMes(Integer mes) {
		this.mes = mes;
	}


	public Integer getOptRefacturacion() {
		return optRefacturacion;
	}


	public void setOptRefacturacion(Integer optRefacturacion) {
		this.optRefacturacion = optRefacturacion;
	}


	public List<SelectItem> getOptRefacturaciones() {
		return optRefacturaciones;
	}


	public void setOptRefacturaciones(List<SelectItem> optRefacturaciones) {
		this.optRefacturaciones = optRefacturaciones;
	}


	public String getFltCUIT() {
		return fltCUIT;
	}


	public void setFltCUIT(String fltCUIT) {
		this.fltCUIT = fltCUIT;
	}


	public String getNomdCliente() {
		return nomdCliente;
	}


	public void setNomdCliente(String nomdCliente) {
		this.nomdCliente = nomdCliente;
	}


	public String getFltCodClienteAlfa() {
		return fltCodClienteAlfa;
	}


	public void setFltCodClienteAlfa(String fltCodClienteAlfa) {
		this.fltCodClienteAlfa = fltCodClienteAlfa;
	}
	
	
	
}
