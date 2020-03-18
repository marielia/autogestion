
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
import java.util.Random;
import java.util.StringTokenizer;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
/*import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; */
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itsolver.util.io.FileUtil;
import com.itsolver.util.seguridad.Autenticador;
import com.refinor.extranet.data.Mchofer;
import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.MgrupoUn;
import com.refinor.extranet.data.Msecuencias;
import com.refinor.extranet.data.MunidadN;
import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.dao.AlmacenDAO;
import com.refinor.extranet.data.dao.MchoferDAO;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MgrupoUnDAO;
import com.refinor.extranet.data.dao.MsecuenciasDAO;
import com.refinor.extranet.data.dao.MunidadNDAO;
import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.SendMail;
import com.refinor.extranet.util.exception.ChoferYaExisteException;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.DatosObligatoriosException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.NoSePudeEnviarMailException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;
import com.refinor.extranet.util.exception.VariosChoferesConIgualDNIException;

public class AltaChoferesBean extends AbstBackingBean {
	protected Session sessionHib;
	
	private int pantalla;	//1 - alta chofer 2- moficacion 3-mensaje guardado alta
	private String nombre;
	private String apellido;
	private String pinChofer;
	private Integer numeroDocumento;
	private Boolean inicializado;
	private Boolean activo;
	private Messages mensajeria;
	
	private List<SelectItem> gruposUnidadNegocio;
	private Integer grupoUnidadNegocio;
	private String grupoUnidadNegocioDesc;

	private List<SelectItem> unidadesNegocio;
	private Integer unidadNegocio;
	private String unidadNegocioDesc;
	
	private String mensajeGuardado;
	private Mclientes cliente;	
	private Mchofer mchofer;	

	public AltaChoferesBean() {
		try{
				
			sessionHib = (new AlmacenDAO()).getSession();	
			mensajeria = new Messages();
			
			MusuarioWeb usuario = (MusuarioWeb)getSessionValue("usuario");
			int tipo = usuario.getTipo();
			
			if (getSessionValue("nroChofer")!=null) {
				//es modificacion
				Integer nroChofer = Integer.parseInt(getSessionValue("nroChofer").toString());
				System.out.println("CHOFER a modificar: "+nroChofer);	
				inicializarValoresMod(nroChofer);
				cargarCombos(nroChofer, tipo);	
			} else {
				//es alta
				inicializarValoresAlta();
				generarPin();
				cargarCombos(null, tipo);
			}		
		
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}
		
	}
	
	public void guardarModificacion(ActionEvent event){
		Transaction tx= null;
		try{	
			if(this.nombre==null || this.nombre.trim().equals("") 
					|| this.apellido==null || this.apellido.trim().equals("") 
					|| this.pinChofer==null || this.pinChofer.trim().equals("") 
					|| this.numeroDocumento==null || this.numeroDocumento.equals(0)){
				throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
			}
			
			Mchofer mChoferInt = new Mchofer();			
			MchoferDAO mChoferDAO = new MchoferDAO(sessionHib);
			mChoferInt = mChoferDAO.get(mchofer.getCodigo(), sessionHib);			
			
			if(existeChoferConDNICodigoCliente(this.numeroDocumento, mchofer.getCodigo(), mchofer.getCodCli())){
				throw new ChoferYaExisteException(mensajeria.getMessage().getString("nro_documento_ya_registrado_msg"));
			}			
			
			Boolean activoBol = mChoferInt.isActivo(); 
			Boolean inicializadoBol = mChoferInt.isInicializado();
			
			mChoferInt.setInicializado(this.inicializado);			
			mChoferInt.setActivo(this.activo);		
			mChoferInt.setNombre(nombre);
			mChoferInt.setApellido(apellido);
			mChoferInt.setDni(numeroDocumento);
			mChoferInt.setPinChof(this.pinChofer.trim());
			mChoferInt.setUnidadNeg(this.unidadNegocio);
			
			String email="";
			String mensajeMail="";
			if((activoBol.equals(new Boolean(false)) && mChoferInt.isActivo().equals(new Boolean(true))) 
				&& (inicializadoBol.equals(new Boolean(false)) && mChoferInt.isInicializado().equals(new Boolean(true)))){
				System.out.println("mChoferInt.getCodCli() "+mChoferInt.getCodCli());
				email = obtenerMailCliente(mChoferInt.getCodCli());
			}
			try {
				
				tx= sessionHib.beginTransaction();				
					mChoferDAO.update(mChoferInt,sessionHib);
					//envio mail al cliente avidandole que su chofer esta ok por refipass
					if((activoBol.equals(new Boolean(false)) && mChoferInt.isActivo().equals(new Boolean(true))) 
							&& (inicializadoBol.equals(new Boolean(false)) && mChoferInt.isInicializado().equals(new Boolean(true)))){
						enviarMailCliente(email);
						mensajeMail = mensajeria.getMessage().getString("chofer_ok_por_administracion_msg_2");
					}
				tx.commit();								
					mensajeGuardado=mensajeria.getMessage().getString("chofer_ok_por_administracion_msg")+" " + mensajeMail;
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
			
//		}catch(NoSePudeEnviarMailException ex){
//			ex.printStackTrace();			
//			pantalla=2;
//			AddErrorMessage(ex.getMessage());
		}catch(DatosObligatoriosException excep) {
			excep.printStackTrace();
			pantalla=2;
			AddErrorMessage(excep.getMessage());
		}catch(ChoferYaExisteException ex){
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
	
	public String obtenerMailCliente(Integer codCliente) throws NoSePudeEnviarMailException,DataAccessErrorException{
		try{
			MclientesDAO MclienteDAO = new MclientesDAO(sessionHib);
			Mclientes Mcliente = new Mclientes();
			Mcliente = MclienteDAO.get(codCliente, sessionHib);
			
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
	
	
	public void guardar(ActionEvent event){
		Transaction tx= null;
		try{
			if(this.nombre==null || this.nombre.trim().equals("") || this.apellido==null || 
				this.apellido.trim().equals("") || this.numeroDocumento==null || 
				this.grupoUnidadNegocio==0 || this.unidadNegocio==0){
				throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
			}	
			
			
			if(existeChoferConDNICodigoCliente(this.numeroDocumento, obtenerCodCliente().getCodigo())){					
				throw new ChoferYaExisteException(mensajeria.getMessage().getString("nro_documento_ya_registrado_msg"));
			}
			
			Mchofer mChofer = new Mchofer();			
			//mChofer.setCodigo(obtenerCodigoChofer());
			mChofer.setCodigo(obtenerCodigoChoferDesdeSecuencia());
			mChofer.setCodCli(obtenerCodCliente().getCodigo());
			mChofer.setNombre(nombre);
			mChofer.setApellido(apellido);
			mChofer.setPinChof(this.pinChofer.trim());
			mChofer.setDni(this.numeroDocumento);
			System.out.println("inicializado->"+inicializado+ " activo->"+activo);
			mChofer.setInicializado(this.inicializado);			
			mChofer.setActivo(this.activo);
			mChofer.setUnidadNeg(this.unidadNegocio);
			mChofer.setIdUserLock(new Boolean(false));
			mChofer.setFAlta(new Date());
			
			try {
				tx= sessionHib.beginTransaction();
					MchoferDAO mChoferDAO = new MchoferDAO(sessionHib);
					mChoferDAO.save(mChofer, sessionHib);
					
//					agregado el 22/06/2009 - marcela	
					MsecuenciasDAO mSecuenciaDAO= new MsecuenciasDAO(sessionHib);
					Msecuencias mSecuencia= new Msecuencias();	
					mSecuencia= mSecuenciaDAO.load(Const.NOMB_TABLA_MCHOFER, sessionHib);
					mSecuencia.setSecuencia(mChofer.getCodigo()+1);				
					mSecuenciaDAO.update(mSecuencia,sessionHib);
					
 				//enviarMailAdministrador();
 				/*try {
 					enviarMailAdministrador("smtp.gmail.com", "587", "false", "true", "true","nietosilvana@gmail.com", "Gasgas259", "CHECKING SETTINGS", "CHECKING EMAIL FUNCTIONALITY", "text/html", "nietosilvana@yahoo.com.ar");
 		        } catch (Exception ex) {
 		            ex.printStackTrace();
 		        } */  

					
					
 				 try {
 					enviarConGMail();
 		            
 		        } catch (Exception e) {
 		            System.out.println("Error en el envio de mail: " + e.getMessage());
 		            e.printStackTrace();
 		        }
 				 
				tx.commit();
				//envio de mail a refipass para hacerle saber que hay un chofer esperando el ok				
				mensajeGuardado=mensajeria.getMessage().getString("registro_ok_chofer_mag");
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
		}catch(ChoferYaExisteException ex){
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
	
	
	private  void enviarConGMail() throws Exception {
		
		FileUtil fileUtil= new FileUtil();
   		Properties property= fileUtil.getPropertiesFile();
   		String mail_smtp_password= property.getProperty("mail_smtp_password");
   		String mail_smtp_host= property.getProperty("mail_smtp_host");
   		String mail_smtp_ssl_trust= property.getProperty("mail_smtp_ssl_trust");
   		String mail_smtp_starttls_enable= property.getProperty("mail_smtp_starttls_enable");
   		String mail_smtp_port = property.getProperty("mail_smtp_port");
   		String mail_smtp_user = property.getProperty("mail_smtp_user");
   		String mail_smtp_auth = property.getProperty("mail_smtp_auth");
   		String mail_from = property.getProperty("mail_from");
   		String mail_to = property.getProperty("mail_to");
   	    String subject =  GetMensaje(property.getProperty("asuntoChoferAlta"));
   	    String body =   GetMensaje(property.getProperty("cuerpoChoferAlta"));
   	 
		Properties props = new Properties();

		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", mail_smtp_host);  
		props.put("mail.smtp.ssl.trust", mail_smtp_ssl_trust );

		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", mail_smtp_starttls_enable);

		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port",mail_smtp_port);

		// Nombre del usuario
		props.setProperty("mail.smtp.user", mail_smtp_user);

		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", mail_smtp_auth);

		javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);

		// Para obtener un log de salida más extenso
		session.setDebug(true);
		
		MimeMessage message = new MimeMessage(session);

		// Quien envia el correo
		message.setFrom(new InternetAddress(mail_from));

		// A quien va dirigido
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(  mail_to ));

		message.setSubject(reemplazarEtiquetas(subject));
		message.setText(reemplazarEtiquetas(body));
		
		Transport t = session.getTransport("smtp");

		// Aqui usuario y password de gmail
		t.connect(mail_from , mail_smtp_password );
		t.sendMessage(message,message.getAllRecipients());
		t.close();
		
		
	}

	
	  private void enviarMailCliente(String email) throws  NoSePudeEnviarMailException { 
		  try{ 
//			  InitialContext ic = new InitialContext();
//	  
//			  Context envCtx = (Context) ic.lookup("java:comp/env"); 
//			  javax.mail.Session  session = (javax.mail.Session) envCtx.lookup("mail/Session"); 
//			  Properties props = session.getProperties(); 
//			  
//			  if (props.containsKey("mail.smtp.auth") && props.getProperty("mail.smtp.auth").equals("true")) { 
//				  		session =  javax.mail.Session.getDefaultInstance(session.getProperties(), new  Autenticador(props.getProperty("mail.smtp.user"),
//						  props.getProperty("mail.smtp.password"))); 
//				  }
//	  
//			  System.out.println("Va a utilizar como archivo de Subject: "+props.  getProperty("asuntoChoferModificacion")); 
//			  String subject =  GetMensaje(props.getProperty("asuntoChoferModificacion"));
//			  
//			  System.out.println("Va a utilizar como archivo de Body: "+props.getProperty(  "cuerpoChoferModificacion")); 
//			  String body =   GetMensaje(props.getProperty("cuerpoChoferModificacion"));
//			  System.out.println("se enviara a: "+email);
//			  
//			  MimeMessage msg = new MimeMessage(session); 
//			  msg.setHeader("Content-Type",   "text/html; charset=ISO-8859-2"); 
//			  msg.setContentLanguage(new String[]   {"es-ar"}); 
//			  msg.addHeader("Content-Transfer-Encoding", "base64");
//			  msg.setSubject(reemplazarEtiquetas(subject), "ISO-8859-2");
//			  msg.setSentDate(new java.util.Date());
//			  msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email,   false)); 
//			  msg.setFrom(new InternetAddress(props.getProperty("mail.from")));
//			  msg.setHeader("X-Mailer", "sendhtml"); 
//			  msg.setText(reemplazarEtiquetas(body),  "ISO-8859-2");
//			  
//			  Transport.send(msg); 
			  
			  
			    FileUtil fileUtil= new FileUtil();
		   		Properties property= fileUtil.getPropertiesFile();
		   		String mail_smtp_password= property.getProperty("mail_smtp_password");
		   		String mail_smtp_host= property.getProperty("mail_smtp_host");
		   		String mail_smtp_ssl_trust= property.getProperty("mail_smtp_ssl_trust");
		   		String mail_smtp_starttls_enable= property.getProperty("mail_smtp_starttls_enable");
		   		String mail_smtp_port = property.getProperty("mail_smtp_port");
		   		String mail_smtp_user = property.getProperty("mail_smtp_user");
		   		String mail_smtp_auth = property.getProperty("mail_smtp_auth");
		   		String mail_from = property.getProperty("mail_from");
		   		String mail_to = property.getProperty("mail_to");
		   	    String subject =  GetMensaje(property.getProperty("asuntoChoferModificacion"));
		   	    String body =   GetMensaje(property.getProperty("cuerpoChoferModificacion"));
		   	 
				Properties props = new Properties();

				// Nombre del host de correo, es smtp.gmail.com
				props.setProperty("mail.smtp.host", mail_smtp_host);  
				props.put("mail.smtp.ssl.trust", mail_smtp_ssl_trust );

				// TLS si está disponible
				props.setProperty("mail.smtp.starttls.enable", mail_smtp_starttls_enable);

				// Puerto de gmail para envio de correos
				props.setProperty("mail.smtp.port",mail_smtp_port);

				// Nombre del usuario
				props.setProperty("mail.smtp.user", mail_smtp_user);

				// Si requiere o no usuario y password para conectarse.
				props.setProperty("mail.smtp.auth", mail_smtp_auth);

				javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);

				// Para obtener un log de salida más extenso
				session.setDebug(true);
				
				MimeMessage message = new MimeMessage(session);

				// Quien envia el correo
				message.setFrom(new InternetAddress(mail_from));

				// A quien va dirigido
				System.out.println("email "+ email);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(  email ));

				message.setSubject(reemplazarEtiquetas(subject));
				message.setText(reemplazarEtiquetas(body));
				
				Transport t = session.getTransport("smtp");

				// Aqui usuario y password de gmail
				t.connect(mail_from , mail_smtp_password );
				t.sendMessage(message,message.getAllRecipients());
				t.close();
			  
			  
			  
	  }catch (Exception e) { 
		  e.printStackTrace(); 
		  throw new  NoSePudeEnviarMailException(mensajeria.getMessage().getString(  "no_se_envio_mail_msg")); 
		  } 
     }
	 
	
	
	
	/*
	 * private void enviarMailAdministrador() throws NoSePudeEnviarMailException {
	 * 
	 * try{ InitialContext ic = new InitialContext(); Context envCtx = (Context)
	 * ic.lookup("java:comp/env"); javax.mail.Session session = (javax.mail.Session)
	 * envCtx.lookup("mail/Session"); Properties props = session.getProperties();
	 * 
	 * if (props.containsKey("mail.smtp.auth") &&
	 * props.getProperty("mail.smtp.auth").equals("true")) { session =
	 * javax.mail.Session.getDefaultInstance(session.getProperties(), new
	 * Autenticador(props.getProperty("mail.smtp.user"),
	 * props.getProperty("mail.smtp.password"))); }
	 * 
	 * System.out.println("Va a utilizar como archivo de Subject: "+props.
	 * getProperty("asuntoChoferAlta")); String subject =
	 * GetMensaje(props.getProperty("asuntoChoferAlta"));
	 * 
	 * System.out.println("Va a utilizar como archivo de Body: "+props.getProperty(
	 * "cuerpoChoferAlta")); String body =
	 * GetMensaje(props.getProperty("cuerpoChoferAlta"));
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
	 * Transport.send(msg); } catch (Exception e) { e.printStackTrace(); throw new
	 * NoSePudeEnviarMailException(mensajeria.getMessage().getString(
	 * "no_se_envio_mail_msg")); }
	 * 
	 * }
	 */
	 
	
	
	
	  private void enviarMailAdministrador( String host, String port, String useSSL, String useTLS, String useAuth, String user, String password, String subject, String content, String type, String recipients)
	            throws NoSuchProviderException, AddressException, MessagingException {
		  // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también. 
	/*  String  remitente = "nietosilvana"; //Para la dirección nomcuenta@gmail.com
	  
	  Properties props = System.getProperties(); 
	  props.put("mail.smtp.host", "smtp.gmail.com"); //El servidor SMTP de Google 
	  props.put("mail.smtp.user", remitente); 
	  props.put("mail.smtp.clave", "Gasgas259"); //La clave de la	  cuenta 
	  props.put("mail.smtp.auth", "false"); //Usar autenticación mediante	  usuario y clave 
	  props.put("mail.smtp.starttls.enable", "true"); //Para  conectar de manera segura al servidor SMTP 
	  
	  props.put("mail.smtp.port",	  "587"); //El puerto SMTP seguro de Google
	  
	  javax.mail.Session session = javax.mail.Session.getDefaultInstance(props); 
	  MimeMessage  message = new MimeMessage(session);
	  
	  try {
		  message.setFrom(new InternetAddress(remitente)); 
		  message.addRecipients(Message.RecipientType.TO, "nietosilvana@yahoo.com.ar");  //Se podrían añadir varios de la misma manera 
		  message.setSubject("hola");
		  message.setText("soy el cuerpo del mail"); 
		  Transport transport =	  session.getTransport("smtp"); 
		  transport.connect("smtp.gmail.com", remitente,  "Gasgas259"); 
		  transport.send(message);
	      transport.close(); 
	  } catch (MessagingException me)
	  {
		  me.printStackTrace();
	  }
	  //Si se produce un error } */
		  
		  try {	  
			  Properties props = new Properties();
		        props.setProperty("mail.transport.protocol", "smtp");
		        props.setProperty("mail.smtp.host", host);
		        props.setProperty("mail.smtp.port", port);        
		        if (useSSL != null && !useSSL.equals("false") && useSSL.equals("true")) {
		            props.setProperty("mail.smtp.ssl.enable", useSSL);
		            props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		            props.setProperty("mail.smtp.socketFactory.port", port);

		        }
		        if (useTLS != null && !useTLS.equals("false") && useTLS.equals("true")) {
		            props.setProperty("mail.smtp.starttls.enable", useTLS);
		            props.setProperty("mail.smtp.socketFactory.fallback", "true");
		            
		        }   
		        props.setProperty("mail.smtp.auth", useAuth);
		        props.setProperty("mail.from", user);  
		        props.setProperty("mail.smtp.user", user);
		        props.setProperty("mail.password", password);

		        javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props, new Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(props.getProperty("mail.smtp.user"), props
		                        .getProperty("mail.password"));
		            }
		        });   

		        Transport transport = mailSession.getTransport();

		        MimeMessage message = new MimeMessage(mailSession);
		        message.setHeader("Subject", subject);
		        message.setContent(content, type);

		        StringTokenizer tokenizer = new StringTokenizer(recipients, ";");
		        while (tokenizer.hasMoreTokens()) {
		            String recipient = tokenizer.nextToken();
		            message.addRecipient(Message.RecipientType.TO,
		                    new InternetAddress(recipient));
		        }

		        transport.connect();
		        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		        transport.close();
		   
		  } catch (MessagingException me)
		  {
			  me.printStackTrace();
		  }
		      
	  }
	 
	
	
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
	
	private String reemplazarEtiquetas(String mensaje) {		
		
		if(mensaje.indexOf("#cliente#") >= 0)
			mensaje = mensaje.replaceFirst("#cliente#", cliente.getDescripcion());
		
		if(mensaje.indexOf("#dni#") >= 0)
			mensaje = mensaje.replaceFirst("#dni#", numeroDocumento.toString());
		
		if(mensaje.indexOf("#nombre#") >= 0)
			mensaje = mensaje.replaceFirst("#nombre#", apellido+", "+nombre);
		
		if(mensaje.indexOf("#pin#") >= 0)
			mensaje = mensaje.replaceFirst("#pin#", pinChofer);	
		
		
		if(mensaje.indexOf("#chofer#") >= 0)
			mensaje = mensaje.replaceFirst("#chofer#", apellido+", "+nombre);	
		
		
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
	 * Metodo:existeChoferConDNI
	 * Funcion:Verifica la existencia de un chofer con el nro de documento ingresado
	 * @param numeroDocumento
	 * @return
	 * @throws IOException
	 * @throws DataAccessErrorException
	 */
	public Boolean existeChoferConDNI(Integer numeroDocumento) throws IOException, DataAccessErrorException{
		MchoferDAO mchoferDAO= new MchoferDAO(sessionHib);
		Mchofer mchofer= new Mchofer();		
		try{			
			mchofer= mchoferDAO.getChoferPorNroDocumento(numeroDocumento);	
			return true;
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			return false;
		}catch(VariosChoferesConIgualDNIException ex){
			ex.printStackTrace();
			return true;
		}
		
	}
	
	/**
	 * Metodo:existeChoferConDNI
	 * Funcion:Verifica la existencia de un chofer con el nro de documento ingresado, cod chofer y cliente
	 * @param numeroDocumento
	 * @return
	 * @throws IOException
	 * @throws DataAccessErrorException
	 */
	public Boolean existeChoferConDNICodigoCliente(Integer numeroDocumento,Integer codChofer, Integer codCliente) throws IOException, DataAccessErrorException{
		MchoferDAO mchoferDAO= new MchoferDAO(sessionHib);
		Mchofer mchofer= new Mchofer();		
		try{			
			mchofer= mchoferDAO.getChoferxDNIxCodChoferxCodCliente(numeroDocumento,codChofer,codCliente);	
			return true;
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			return false;
		}catch(VariosChoferesConIgualDNIException ex){
			ex.printStackTrace();
			return true;
		}
		
	}
	
	/**
	 * Metodo:existeChoferConDNI
	 * Funcion:Verifica la existencia de un chofer con el nro de documento ingresado, cod chofer y cliente
	 * @param numeroDocumento
	 * @return
	 * @throws IOException
	 * @throws DataAccessErrorException
	 */
	public Boolean existeChoferConDNICodigoCliente(Integer numeroDocumento,Integer codCliente) throws IOException, DataAccessErrorException{
		MchoferDAO mchoferDAO= new MchoferDAO(sessionHib);
		Mchofer mchofer= new Mchofer();		
		try{			
			mchofer= mchoferDAO.getChoferxDNIxCodCliente(numeroDocumento,codCliente);	
			return true;
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			return false;
		}catch(VariosChoferesConIgualDNIException ex){
			ex.printStackTrace();
			return true;
		}
		
	}
	
	public int obtenerCodigoChofer() throws DataAccessErrorException{
		try{
			MchoferDAO mChoferDAO= new MchoferDAO(sessionHib);
			int codigo= mChoferDAO.getCodigo();
			return codigo;
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	public int obtenerCodigoChoferDesdeSecuencia() throws DataAccessErrorException{
		try{
			MsecuenciasDAO mSecunciasDAO= new MsecuenciasDAO(sessionHib);
			int codigo= mSecunciasDAO.getValor(Const.NOMB_TABLA_MCHOFER);		
		
			return codigo;
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	private void generarPin(){	
		try{
			Random rand = new Random();
			Integer pinRandom = rand.nextInt(10000);
			this.pinChofer= pinRandom.toString();
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}
	}
	
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
	
	private void cargarCombos(Integer nroChofer, int tipo){
		 
		MgrupoUnDAO mgrupoUnDAO = new MgrupoUnDAO(sessionHib);
		MchoferDAO mChoferDAO = new MchoferDAO(sessionHib);
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
				Mchofer mChofer = mChoferDAO.getChoferPorCodigo(nroChofer);
				Mclientes mCliente = mClientesDAO.getClienteById(mChofer.getCodCli());	
				lstDatos = mgrupoUnDAO.getGruposUNPorCliente(mCliente.getCuit());
			}
			
			unidadesNegocio = new ArrayList<SelectItem>();
			
			if(nroChofer!=null) {
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
	
	private void inicializarValoresAlta(){
		this.nombre=null;
		this.apellido=null;
		this.pinChofer=null;
		this.numeroDocumento=null;
		this.inicializado =  new Boolean (false);
		this.activo =  new Boolean (false);	
		this.pantalla= 1 ;		
		this.pinChofer=null;
		
	}
	
	private void inicializarValoresMod(int nroChofer){
		//buscar los datos del chofer a mostrar
		try{
			MchoferDAO mchoferDAO =  new MchoferDAO(sessionHib);
			mchofer = new Mchofer();
			mchofer = mchoferDAO.get(nroChofer, sessionHib);
			mchoferDAO.refresh(mchofer, sessionHib);
			this.numeroDocumento = mchofer.getDni();
			this.nombre=mchofer.getNombre();
			this.apellido=mchofer.getApellido();
			this.pinChofer=mchofer.getPinChof();
			
			this.inicializado =  mchofer.isInicializado();
			this.activo =  mchofer.isActivo();	
			
			//unidad de negocio
			this.unidadNegocio=mchofer.getUnidadNeg();
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
	 * volver a la pantalla de lista de menu
	 * @author snieto
	 * @return String Pagina a donde vuelve
	 */
	public String volver() {		
		getSession().removeAttribute("nroChofer");	
		String goToPage = Const.ANCLA_LISTA_REPORTES_DISPONIBLES;			
		return goToPage;
			
	}
	
	/**
	 * volver a lista de choferes.
	 * @author snieto
	 * @return String Pagina a donde vuelve
	 */
	public String volverListaChoferes() {	
		getSession().removeAttribute("nroChofer");	
		String goToPage = Const.ANCLA_LISTA_CHOFERES;			
		return goToPage;
			
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public Boolean getInicializado() {
		return inicializado;
	}

	public void setInicializado(Boolean inicializado) {
		this.inicializado = inicializado;
	}

	public String getNombre() {
		return nombre;
	}

//	public Integer getNroChofer() {
//		return nroChofer;
//	}
//
//	public void setNroChofer(Integer nroChofer) {
//		this.nroChofer = nroChofer;
//	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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

	public String getPinChofer() {
		return pinChofer;
	}

	public void setPinChofer(String pinChofer) {
		this.pinChofer = pinChofer;
	}
	public Mclientes getCliente() {
		return cliente;
	}

	public void setCliente(Mclientes cliente) {
		this.cliente = cliente;
	}

	public String getMensajeGuardado() {
		return mensajeGuardado;
	}

	public void setMensajeGuardado(String mensajeGuardado) {
		this.mensajeGuardado = mensajeGuardado;
	}

	
	public String getGrupoUnidadNegocioDesc() {
		return grupoUnidadNegocioDesc;
	}

	public void setGrupoUnidadNegocioDesc(String grupoUnidadNegocioDesc) {
		this.grupoUnidadNegocioDesc = grupoUnidadNegocioDesc;
	}

	public String getUnidadNegocioDesc() {
		return unidadNegocioDesc;
	}

	public void setUnidadNegocioDesc(String unidadNegocioDesc) {
		this.unidadNegocioDesc = unidadNegocioDesc;
	}

	public Mchofer getMchofer() {
		return mchofer;
	}

	public void setMchofer(Mchofer mchofer) {
		this.mchofer = mchofer;
	}

	public int getPantalla() {
		return pantalla;
	}

	public void setPantalla(int pantalla) {
		this.pantalla = pantalla;
	}
}

