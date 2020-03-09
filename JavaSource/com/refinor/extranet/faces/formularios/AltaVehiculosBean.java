package com.refinor.extranet.faces.formularios;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
/*import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;*/

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itsolver.util.seguridad.Autenticador;
import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.MgrupoUn;
import com.refinor.extranet.data.Msecuencias;
import com.refinor.extranet.data.MunidadN;
import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.Mvehiculo;
import com.refinor.extranet.data.dao.AlmacenDAO;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MgrupoUnDAO;
import com.refinor.extranet.data.dao.MsecuenciasDAO;
import com.refinor.extranet.data.dao.MunidadNDAO;
import com.refinor.extranet.data.dao.MvehiculoDAO;
import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.DatosObligatoriosException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.NoSePudeEnviarMailException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;
import com.refinor.extranet.util.exception.VariosVehiculosPorPatenteYClienteException;
import com.refinor.extranet.util.exception.VehiculoYaExisteException;

public class AltaVehiculosBean extends AbstBackingBean {

	private Session sessionHib;	
	private int pantalla;	//1 - alta chofer 2- moficacion 3-mensaje guardado alta
	
	private Boolean inicializado;
	private Boolean activo;
	private Messages mensajeria;
	
	private String dominio;
	private String codBarra;

	private List<SelectItem> gruposUnidadNegocio;
	private Integer grupoUnidadNegocio;
	private String grupoUnidadNegocioDesc;

	private List<SelectItem> unidadesNegocio;
	private Integer unidadNegocio;
	private String unidadNegocioDesc;
	
	private String mensajeGuardado;
	private Mclientes cliente;	
	private Mvehiculo vehiculo;
	
	public AltaVehiculosBean() {
		try{
			
			sessionHib = (new AlmacenDAO()).getSession();	
			mensajeria = new Messages();
			
			MusuarioWeb usuario = (MusuarioWeb)getSessionValue("usuario");
			int tipo = usuario.getTipo();
			
			if (getSessionValue("nroVehiculo")!=null) {
				//es modificacion
				Integer nroVehiculo =Integer.parseInt(getSessionValue("nroVehiculo").toString());
				System.out.println("nroVehiculo a modificar: "+nroVehiculo);
				inicializarValoresMod(nroVehiculo);
				cargarCombos(nroVehiculo, tipo);	
			} else {
				//es alta			
				inicializarValoresAlta();				
				cargarCombos(null, tipo);
			}			
			
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}
	}
	/**
	 * Metodo:obtenerMailCliente
	 * Funcion: Con el codigo de cliente obtener su mail.
	 * @param codCliente
	 * @return
	 * @throws NoSePudeEnviarMailException
	 * @throws DataAccessErrorException
	 */
	public String obtenerMailCliente(Integer codCliente) throws NoSePudeEnviarMailException,DataAccessErrorException{
		try{
			MclientesDAO MclienteDAO = new MclientesDAO(sessionHib);
			Mclientes Mcliente = new Mclientes();
			Mcliente = MclienteDAO.load(codCliente, sessionHib);
			
			if(Mcliente.getImail()==null){
				throw new NoSePudeEnviarMailException(mensajeria.getMessage().getString("no_se_envio_mail_msg"));
			}
			return Mcliente.getImail();
		}catch(NoSePudeEnviarMailException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	/**
	 * Metodo:enviarMailCliente
	 * Funcion: Enviar mail al cliente informando la confirmacion de datos de modificacion por parte del admin.
	 * @param email
	 * @throws NoSePudeEnviarMailException
	 */
	/*
	 * private void enviarMailCliente(String email) throws
	 * NoSePudeEnviarMailException { try{ InitialContext ic = new InitialContext();
	 * Context envCtx = (Context) ic.lookup("java:comp/env"); javax.mail.Session
	 * session = (javax.mail.Session) envCtx.lookup("mail/Session"); Properties
	 * props = session.getProperties(); if (props.containsKey("mail.smtp.auth") &&
	 * props.getProperty("mail.smtp.auth").equals("true")) { session =
	 * javax.mail.Session.getDefaultInstance(session.getProperties(), new
	 * Autenticador(props.getProperty("mail.smtp.user"),
	 * props.getProperty("mail.smtp.password"))); }
	 * 
	 * System.out.println("Va a utilizar como archivo de Subject: "+props.
	 * getProperty("asuntoVehiculoModificacion")); String subject =
	 * GetMensaje(props.getProperty("asuntoVehiculoModificacion"));
	 * 
	 * System.out.println("Va a utilizar como archivo de Body: "+props.getProperty(
	 * "cuerpoVehiculoModificacion")); String body =
	 * GetMensaje(props.getProperty("cuerpoVehiculoModificacion"));
	 * System.out.println("se enviara a: "+email);
	 * 
	 * MimeMessage msg = new MimeMessage(session); msg.setHeader("Content-Type",
	 * "text/html; charset=ISO-8859-2"); msg.setContentLanguage(new String[]
	 * {"es-ar"}); msg.addHeader("Content-Transfer-Encoding", "base64");
	 * msg.setSubject(reemplazarEtiquetas(subject), "ISO-8859-2");
	 * msg.setSentDate(new java.util.Date());
	 * msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email,
	 * false)); msg.setFrom(new InternetAddress(props.getProperty("mail.from")));
	 * msg.setHeader("X-Mailer", "sendhtml"); msg.setText(reemplazarEtiquetas(body),
	 * "ISO-8859-2");
	 * 
	 * Transport.send(msg); }catch (Exception e) { e.printStackTrace(); throw new
	 * NoSePudeEnviarMailException(mensajeria.getMessage().getString(
	 * "no_se_envio_mail_msg")); } }
	 */
	
	/**
	 * Metodo:guardarModificacion
	 * Funcion: Guardar las modificaciones que realiza el admin al seleccionar un vehiculo.
	 * Puede ingresar el codigo de barra, seleccionar los campos inicializado y activo.
	 * @param event
	 */
	public void guardarModificacion(ActionEvent event){
		Transaction tx= null;
		try{	
			if(this.codBarra==null || this.codBarra.trim().equals("")){
				throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
			}
			
			Mvehiculo mVehiculo = new Mvehiculo();		
			MvehiculoDAO mVehDAO = new MvehiculoDAO(sessionHib);
			mVehiculo = mVehDAO.get(vehiculo.getCodigo(), sessionHib);	
			mVehiculo.setCodBarra(this.codBarra);
			mVehiculo.setInicializado(this.inicializado);			
			mVehiculo.setActivo(this.activo);
			mVehiculo.setCodUnidadN(this.unidadNegocio);
			String email = obtenerMailCliente(mVehiculo.getCodCliente());
			try {
				tx= sessionHib.beginTransaction();				
				mVehDAO.update(mVehiculo,sessionHib);
				//envio mail al cliente avidandole que su veh esta ok por refipass
//				enviarMailCliente(email);
				tx.commit();								
				mensajeGuardado=mensajeria.getMessage().getString("vehiculo_ok_por_administracion_msg");
				pantalla=3;
//			}catch(NoSePudeEnviarMailException excep) {
//				excep.printStackTrace();
//				tx.rollback();
//				AddErrorMessage(excep.getMessage());
			} catch(Exception excep) {
				excep.printStackTrace();
				tx.rollback();
				throw new DataAccessErrorException();
			}
			
		}catch(DatosObligatoriosException excep) {
			excep.printStackTrace();
			pantalla=2;
			AddErrorMessage(excep.getMessage());
		}catch(NoSePudeEnviarMailException ex){
			ex.printStackTrace();			
			pantalla=2;
			AddErrorMessage(ex.getMessage());
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();			
			pantalla=2;
			AddErrorMessage(ex.getMessage());
		}catch(Exception excep) {
			excep.printStackTrace();
			pantalla=2;			
			AddErrorMessage(new DataAccessErrorException().getMessage());
		}
	}
	
	/**
	 * Metodo:inicializarValoresMod
	 * Funcion: Inicializar los atributos por la modificacion. Recibe el codigo de Vehiculo 
	 * y busca sus datos para mostrarlo
	 * @param codigoVehiculo
	 */
	private void inicializarValoresMod(int codigoVehiculo){
		//buscar los datos del vehiculo a mostrar
		try{
			MvehiculoDAO mVehiculoDAO =  new MvehiculoDAO(sessionHib);
			vehiculo =  new Mvehiculo();
			vehiculo = mVehiculoDAO.get(codigoVehiculo,sessionHib);
			mVehiculoDAO.refresh(vehiculo,sessionHib);
			this.dominio=vehiculo.getDominio();
			this.codBarra= vehiculo.getCodBarra();
			
			this.inicializado = vehiculo.isInicializado();
			this.activo = vehiculo.isActivo();	
			
			//unidad de negocio
			this.unidadNegocio=vehiculo.getCodUnidadN();
			MunidadNDAO mUnidadNDAO = new MunidadNDAO(sessionHib);
			MunidadN mUnidadN = new MunidadN();
			mUnidadN= mUnidadNDAO.load(unidadNegocio, sessionHib);
			
			this.unidadNegocioDesc = mUnidadN.getDescripcion();

			// grupo
			MgrupoUnDAO mGrupoUnDAO = new MgrupoUnDAO(sessionHib);
			MgrupoUn mGrupoUn = new MgrupoUn();
			mGrupoUn= mGrupoUnDAO.load(mUnidadN.getCodGrupoUn(), sessionHib);
			this.grupoUnidadNegocioDesc= mGrupoUn.getDescripcion();
			this.grupoUnidadNegocio = mGrupoUn.getCodigo();
			
			this.pantalla=  2;	
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(new DataAccessErrorException().getMessage());
		}
		
	}
	/**
	 * Metodo:cargarSusUnidadesDeNegocio
	 * Funcion: Cuando se selecciona un grupo de unidad de negocio, al presionar el boton "Buscar Un Neg", el sistema
	 * filtra las unidades de negocio para ese grupo.
	 * @param event
	 */
	public void cargarSusUnidadesDeNegocio(ActionEvent event){	
		try {
						
			 MunidadNDAO munidadNDao = new MunidadNDAO(sessionHib);
			 unidadesNegocio = new ArrayList<SelectItem>();
		
			try {
				MunidadN munidadN;				
				List lstDatos = munidadNDao.getUnidadesNegocioPorGrupo(grupoUnidadNegocio);				
				
				Iterator it = lstDatos.iterator();			
				while(it.hasNext()) {
					munidadN = (MunidadN)it.next();
					unidadesNegocio.add(new SelectItem(new Integer(munidadN.getCodigo()),munidadN.getDescripcion()));
				}
				
			} catch(Exception ex) {
				unidadesNegocio = new ArrayList<SelectItem>();
				unidadesNegocio.add(new SelectItem(new Integer(0),"- Seleccione un Grupo -"));
				ex.printStackTrace();				
				AddErrorMessage(mensajeria.getMessage().getString("grupo_no_tiene_unidades_negocio"));
			}		
					
		} catch(Exception ex) {
			ex.printStackTrace();
			AddErrorMessage("No se recupero el Grupo.");
		}	
	}
	
	public void cargarUnidadesNegocio(ValueChangeEvent event){
		
		 Integer nroGrupo = ((Integer)event.getNewValue()).intValue(); 
		 try {
				
			 MunidadNDAO munidadNDao = new MunidadNDAO(sessionHib);
			 unidadesNegocio = new ArrayList<SelectItem>();
			 
			try {
				
				MunidadN munidadN;				
				List lstDatos = munidadNDao.getUnidadesNegocioPorGrupo(nroGrupo);				
				
				if(lstDatos!=null && lstDatos.size()>0){
					Iterator it = lstDatos.iterator();			
					while(it.hasNext()) {
						munidadN = (MunidadN)it.next();
						unidadesNegocio.add(new SelectItem(new Integer(munidadN.getCodigo()),munidadN.getDescripcion()));
					}
				} else {
					unidadesNegocio.add(new SelectItem(new Integer(0),"- Seleccione un Grupo -"));
				}
				
			} catch(Exception ex) {
				unidadesNegocio = new ArrayList<SelectItem>();
				unidadesNegocio.add(new SelectItem(new Integer(-1),Const.SELECCIONE));
				ex.printStackTrace();				
				AddErrorMessage(mensajeria.getMessage().getString("grupo_no_tiene_unidades_negocio"));
			}		
					
		} catch(Exception ex) {
			ex.printStackTrace();
			AddErrorMessage("No se recupero el Grupo.");
		}	
	}
	
	/**
	 * Metodo:cargarCombos
	 * Funcion: Cargar los combos necesarios 
	 * 1 - Carga el combo de grupo - Por el alta de vehiculo
	 *
	 */
	private void cargarCombos(Integer nroVehiculo, int tipo){
		
		MgrupoUnDAO mgrupoUnDAO = new MgrupoUnDAO(sessionHib);
		MvehiculoDAO mVehiculoDAO = new MvehiculoDAO(sessionHib);
		gruposUnidadNegocio = new ArrayList<SelectItem>();
	
		try {
			List lstDatos = null;
			MgrupoUn mgrupoUn;
			
			if(tipo==1) {
				//es cliente
				MusuarioWeb usuario = (MusuarioWeb)getSession().getAttribute(Const.USUARIO);	
				lstDatos = mgrupoUnDAO.getGruposUNPorCliente(usuario.getCuit());
			} else {
				//es refipass
				MclientesDAO mClientesDAO = new MclientesDAO(sessionHib);
				Mvehiculo mVehiculo = mVehiculoDAO.getVehiculoPorCodigo(nroVehiculo);
				Mclientes mCliente = mClientesDAO.getClienteById(mVehiculo.getCodCliente());	
				lstDatos = mgrupoUnDAO.getGruposUNPorCliente(mCliente.getCuit());
			}
					
			unidadesNegocio = new ArrayList<SelectItem>();
			
			if(nroVehiculo!=null) {
				MunidadNDAO munidadNDao = new MunidadNDAO(sessionHib);
				try {
					MunidadN munidadN;				
					List lstDatos1 = munidadNDao.getUnidadesNegocioPorGrupo(grupoUnidadNegocio);				
					
					Iterator it1 = lstDatos1.iterator();			
					while(it1.hasNext()) {
						munidadN = (MunidadN)it1.next();
						unidadesNegocio.add(new SelectItem(new Integer(munidadN.getCodigo()),munidadN.getDescripcion()));
					}
				} catch(Exception ex) {
					AddErrorMessage(ex.getMessage());
					unidadesNegocio = new ArrayList<SelectItem>();
					unidadesNegocio.add(new SelectItem(new Integer(0),"- Seleccione un Grupo -"));
					ex.printStackTrace();				
					AddErrorMessage(mensajeria.getMessage().getString("grupo_no_tiene_unidades_negocio"));
				}		
			} else {
				gruposUnidadNegocio.add(new SelectItem(new Integer(0),"- Seleccione un Grupo -"));
				unidadesNegocio.add(new SelectItem(new Integer(0),"- Seleccione un Grupo -"));
			}
			
			Iterator it = lstDatos.iterator();
			
			while(it.hasNext()) {
				mgrupoUn = (MgrupoUn)it.next();
				gruposUnidadNegocio.add(new SelectItem(new Integer(mgrupoUn.getCodigo()),mgrupoUn.getDescripcion()));
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los Grupos de Unidad de Negocio.");
		}	
		
	}
	
	/**
	 * Metodo: guardar
	 * Funcion: Guardar los datos del vehiculo por el alta. Perfil Cliente
	 * @param event
	 */
	public void guardar(ActionEvent event){
		Transaction tx= null;
		try{
			if(this.dominio==null || this.dominio.trim().equals("") || this.grupoUnidadNegocio==0 || this.unidadNegocio==0){
				throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
			}	
			
			//	validaciones
			if(existeVehiculoPatentePorCliente(this.dominio,obtenerCodCliente().getCodigo())){
				throw new VehiculoYaExisteException(mensajeria.getMessage().getString("patente_ya_registrada_msg"));
			}
			
			Mvehiculo mvehiculo = new Mvehiculo();			
			//mvehiculo.setCodigo(obtenerCodigoVehiculo());
			mvehiculo.setCodCliente(obtenerCodCliente().getCodigo());
			mvehiculo.setDominio(this.dominio);
			System.out.println("inicializado->"+inicializado+ " activo->"+activo);
			mvehiculo.setInicializado(this.inicializado);			
			mvehiculo.setActivo(this.activo);
			mvehiculo.setCodUnidadN(this.unidadNegocio);
			mvehiculo.setIdUserLock(new Boolean(false));
			mvehiculo.setFAlta(new Date());
			
			try {
				tx= sessionHib.beginTransaction();
					MvehiculoDAO mVDAO = new MvehiculoDAO(sessionHib);
					mVDAO.save(mvehiculo, sessionHib);
					//agregado el 22/06/2009 - marcela
					
//					enviarMailAdministrador();
				tx.commit();
				//envio de mail a refipass para hacerle saber que hay un chofer esperando el ok				
				mensajeGuardado=mensajeria.getMessage().getString("registro_ok_vehiculo_msg");
				pantalla=3;
//			}catch(NoSePudeEnviarMailException excep) {
//				excep.printStackTrace();
//				tx.rollback();
//				AddErrorMessage(excep.getMessage());
			} catch(Exception excep) {
				excep.printStackTrace();
				tx.rollback();
				throw new DataAccessErrorException();
			}
			
		}catch(DatosObligatoriosException ex){
			ex.printStackTrace();		
			AddErrorMessage(ex.getMessage());
		}catch(IOException ex){
			ex.printStackTrace();		
			AddErrorMessage(ex.getMessage());
		}catch(VehiculoYaExisteException ex){
			ex.printStackTrace();		
			AddErrorMessage(ex.getMessage());
		}catch(PersonaNoExisteException ex){
			ex.printStackTrace();		
			AddErrorMessage(ex.getMessage());
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(new DataAccessErrorException().getMessage());
		}
		
	}
	
	/**
	 * Metodo:	existeVehiculoPatentePorCliente
	 * Funcion:	Verifica la existencia de un vehiculo con el nro de patente ingresado para un cliente determinado
	 * @param dominio
	 * @param codCliente
	 * @return
	 * @throws IOException
	 * @throws DataAccessErrorException
	 */
	public Boolean existeVehiculoPatentePorCliente(String dominio, Integer codCliente) throws IOException, DataAccessErrorException{
		MvehiculoDAO mVehiculoDAO= new MvehiculoDAO(sessionHib);
		Mvehiculo mVehiculo= new Mvehiculo();		
		try{			
			mVehiculo= mVehiculoDAO.getVehiculoPorDominioYClienteEstado(dominio,codCliente,-1);	
			return true;
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			return false;
		}catch(VariosVehiculosPorPatenteYClienteException ex){
			ex.printStackTrace();
			return true;
		}
		
	}
	
	/**
	 * Metodo:enviarMailAdministrador
	 * Funcion: Enviar un mail al admin informando que hay un vehiculo a la espera de confirmacion de datos.
	 * Se envia el alta de vehiculo por parte del cliente.
	 * @throws NoSePudeEnviarMailException
	 */
	/*
	 * private void enviarMailAdministrador() throws NoSePudeEnviarMailException {
	 * try{ InitialContext ic = new InitialContext(); Context envCtx = (Context)
	 * ic.lookup("java:comp/env"); javax.mail.Session session = (javax.mail.Session)
	 * envCtx.lookup("mail/Session"); Properties props = session.getProperties(); if
	 * (props.containsKey("mail.smtp.auth") &&
	 * props.getProperty("mail.smtp.auth").equals("true")) { session =
	 * javax.mail.Session.getDefaultInstance(session.getProperties(), new
	 * Autenticador(props.getProperty("mail.smtp.user"),
	 * props.getProperty("mail.smtp.password"))); }
	 * 
	 * System.out.println("Va a utilizar como archivo de Subject: "+props.
	 * getProperty("asuntoVehiculoAlta")); String subject =
	 * GetMensaje(props.getProperty("asuntoVehiculoAlta"));
	 * 
	 * System.out.println("Va a utilizar como archivo de Body: "+props.getProperty(
	 * "cuerpoVehiculoAlta")); String body =
	 * GetMensaje(props.getProperty("cuerpoVehiculoAlta"));
	 * System.out.println("se enviara a: "+props.getProperty("emailRefipass"));
	 * String email=props.getProperty("emailRefipass"); MimeMessage msg = new
	 * MimeMessage(session); msg.setHeader("Content-Type",
	 * "text/html; charset=ISO-8859-2"); msg.setContentLanguage(new String[]
	 * {"es-ar"}); msg.addHeader("Content-Transfer-Encoding", "base64");
	 * msg.setSubject(reemplazarEtiquetas(subject), "ISO-8859-2");
	 * msg.setSentDate(new java.util.Date());
	 * msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email,
	 * false)); msg.setFrom(new InternetAddress(props.getProperty("mail.from")));
	 * msg.setHeader("X-Mailer", "sendhtml"); msg.setText(reemplazarEtiquetas(body),
	 * "ISO-8859-2");
	 * 
	 * Transport.send(msg); }catch (Exception e) { e.printStackTrace(); throw new
	 * NoSePudeEnviarMailException(mensajeria.getMessage().getString(
	 * "no_se_envio_mail_msg")); } }
	 */
	
	/**
	 * 
	 * @param archivo
	 * @return
	 * @throws Exception
	 */
	private String GetMensaje(String archivo) throws Exception {
		String mensaje = "";
		try {
			FileInputStream file = new FileInputStream(archivo);
			BufferedReader br = new BufferedReader(new InputStreamReader(file));
			
			while (br.ready()) {
				mensaje += br.readLine() + "\r";
			}
			
			br.close();
			file.close();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return mensaje;
	}
	
	/**
	 * Reemplaza etiquetas del archivo usado para envio de mail.
	 * @param mensaje
	 * @return
	 */
	private String reemplazarEtiquetas(String mensaje) {		
		
		if(mensaje.indexOf("#cliente#") >= 0)
			mensaje = mensaje.replaceFirst("#cliente#", cliente.getDescripcion());
		
		
		if(mensaje.indexOf("#dominio#") >= 0)
			mensaje = mensaje.replaceFirst("#dominio#", dominio);	
		
		
		if(mensaje.indexOf("#inicializado#") >= 0)
			mensaje = mensaje.replaceFirst("#inicializado#", this.inicializado? "Si":"No");	
		
		if(mensaje.indexOf("#activo#") >= 0)
			mensaje = mensaje.replaceFirst("#activo#", this.activo? "Si":"No");	
		
		
		if(mensaje.indexOf("#grupo#") >= 0)
			mensaje = mensaje.replaceFirst("#grupo#", this.grupoUnidadNegocioDesc);	
		
		if(mensaje.indexOf("#unidad#") >= 0)
			mensaje = mensaje.replaceFirst("#unidad#", this.unidadNegocioDesc);	
		
		return mensaje;
	}
	
	
	/**
	 * Metodo: obtenerCodCliente
	 * Funcion: Obtener el cliente loqueado.
	 * @return cliente loguueado
	 * @throws DataAccessErrorException
	 * @throws PersonaNoExisteException
	 */
	private Mclientes obtenerCodCliente() throws DataAccessErrorException, PersonaNoExisteException{		
		try {
			MusuarioWeb usuario = (MusuarioWeb)getSession().getAttribute(Const.USUARIO);
						
			MclientesDAO mclientesDAO = new MclientesDAO(sessionHib);
			List clientes = mclientesDAO.getPorDocumento(usuario.getCuit());
			cliente = ((Mclientes)clientes.get(0));
			return cliente;
		} catch (PersonaNoExisteException e) {			
			e.printStackTrace();
			AddErrorMessage(e.getMessage());
			throw e;
		} catch (DataAccessErrorException e) {		
			e.printStackTrace();
			AddErrorMessage(e.getMessage());
			throw e;
			
		}
		
	}
	
	/**
	 * Metodo: obtenerCodigoVehiculo
	 * Funcion: Obtener codigo de vehiculo a guardar
	 * @return int codigo de vehiculo
	 * @throws DataAccessErrorException
	 */
	public int obtenerCodigoVehiculo() throws DataAccessErrorException{
		try{
			MvehiculoDAO mVehiculosDAO =  new MvehiculoDAO(sessionHib);			
			int codigo= mVehiculosDAO.getCodigo();
			return codigo;
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	/**
	 * volver a la pantalla de lista de menu
	 * @author snieto
	 * @return String Pagina a donde vuelve
	 */
	public String volver() {		
		getSession().removeAttribute("nroVehiculo");	
		String goToPage = Const.ANCLA_LISTA_REPORTES_DISPONIBLES;			
		return goToPage;
			
	}
	
	/**
	 * volver a lista de vehiculos.
	 * @author snieto
	 * @return String Pagina a donde vuelve
	 */
	public String volverListaVehiculos() {		
		getSession().removeAttribute("nroVehiculo");	
		String goToPage = Const.ANCLA_LISTA_VEHICULOS;			
		return goToPage;
			
	}
	
	/**
	 * Inicializacion de atributos
	 *
	 */
	private void inicializarValoresAlta(){
		this.dominio=null;		
		this.inicializado =  new Boolean (false);
		this.activo =  new Boolean (false);	
		this.pantalla= 1 ;	
		
	}

	@Override
	public Boolean getPuedeIngresar() {
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Mclientes getCliente() {
		return cliente;
	}

	public void setCliente(Mclientes cliente) {
		this.cliente = cliente;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
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

	public String getGrupoUnidadNegocioDesc() {
		return grupoUnidadNegocioDesc;
	}

	public void setGrupoUnidadNegocioDesc(String grupoUnidadNegocioDesc) {
		this.grupoUnidadNegocioDesc = grupoUnidadNegocioDesc;
	}

	public Boolean getInicializado() {
		return inicializado;
	}

	public void setInicializado(Boolean inicializado) {
		this.inicializado = inicializado;
	}

	public String getMensajeGuardado() {
		return mensajeGuardado;
	}

	public void setMensajeGuardado(String mensajeGuardado) {
		this.mensajeGuardado = mensajeGuardado;
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

	public Session getSessionHib() {
		return sessionHib;
	}

	public void setSessionHib(Session sessionHib) {
		this.sessionHib = sessionHib;
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

	public String getUnidadNegocioDesc() {
		return unidadNegocioDesc;
	}

	public void setUnidadNegocioDesc(String unidadNegocioDesc) {
		this.unidadNegocioDesc = unidadNegocioDesc;
	}
	public Mvehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Mvehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
