/**
 * 
 */
package com.asecor.extranet.faces.seguridad;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.asecor.extranet.data.TitularWeb;
import com.asecor.extranet.data.dao.UsuarioWebDAO;
import com.asecor.extranet.faces.base.AbstBackingBean;
import com.asecor.extranet.seguridad.PerfilAdmin;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.exception.DataAccessErrorException;
import com.asecor.extranet.util.exception.UsuarioNoConfirmadoException;
import com.asecor.extranet.util.exception.UsuarioNoExisteException;
import com.asecor.util.io.FileUtil;

/**
 * @author snieto
 *
 */
public class ForgotYouPasswordBean extends AbstBackingBean {

	private Integer id; 
	 
	private Session session;  
	private String dni;
	private String email;	
	private java.lang.Boolean mostrarResultado; 
	 
	protected Integer estado;	
	protected List<SelectItem> estados;
	
	private String comesFrom;

	public ForgotYouPasswordBean() {
		super();
	try{		   		
		 		
		session= (new UsuarioWebDAO()).getSession(); 
		inicializar(); 
		mostrarResultado = new Boolean(false);
		
		System.out.println("getSessionValue(\"comesFrom\")  " +  getSession().getAttribute("comesFrom") );
		System.out.println("this.comesFrom  " + this.comesFrom );
		if (getSession().getAttribute("comesFrom")!=null) { 
			this.comesFrom = getSession().getAttribute("comesFrom").toString();
			System.out.println("this.comesFrom  " + this.comesFrom );
		}
		
		FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
		HttpServletResponse response =
			(HttpServletResponse)context.getExternalContext().getResponse(); 
		response.setHeader("Cache-Control", "no-cache"); 
		
	}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());			
		}
		
	}
	
	 
	private void inicializar() {		
		id = new Integer(0); 
		email = "";
		dni = ""; 
		
		estados = new ArrayList<SelectItem>(); 
		//estados.add(new SelectItem(new Integer(1),"CELULAR"));
		estados.add(new SelectItem(new Integer(0),"EMAIL"));	
		estado = 0;
	}
	
	 
	public void aceptar(ActionEvent event) {
 		UsuarioWebDAO usrDAO= new UsuarioWebDAO(session);
		Properties properties= (new FileUtil()).getPropertiesFile();
		HttpSession sesion= this.getSession();
		sesion.setMaxInactiveInterval(Integer.parseInt((String)properties.get(Const.SESSION_TIMEOUT)));		
		PerfilAdmin perfAdmin= new PerfilAdmin(session);
		TitularWeb usuario= null;
		try { 
								
			usuario = usrDAO.getByEmailAndDni(this.email, this.dni);			
			usrDAO.refresh(usuario, session);
									
			// Chequea si el Usuario  confirmado			 		
			 if(!usuario.getConfirmed())
			 	throw new UsuarioNoConfirmadoException("El usuario no confirmó su registro"); 
		 					 
			perfAdmin.limpiarIntentosFallidos(usuario);
			
			//genera una clave 
			usuario.setPin(new Date().getTime()+"");
			cambiarPIN(usuario);
			enviarConGMail(usuario);
		 
			mostrarResultado= new Boolean(true);
			
			
		} catch (UsuarioNoExisteException ex) {
			ex.printStackTrace();
			//inicializar();
			AddErrorMessage(ex.getMessage());			
		} catch (UsuarioNoConfirmadoException ex) {			
			//inicializar();
			AddErrorMessage(ex.getMessage());			
		}  catch (DataAccessErrorException ex) {
			ex.printStackTrace();			
			//inicializar();
			AddErrorMessage(ex.getMessage());			
		} catch (Exception ex) {			
			//inicializar();
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());			
		} finally {
			session.close();
		} 
	}
	
	
	public void cambiarPIN(TitularWeb usuario) {
		UsuarioWebDAO usrDAO= new UsuarioWebDAO(session); 
		try {
			Transaction tx= session.beginTransaction(); 
						
			usuario.setPasswordChange(true);		
			usuario.setActive(true);	
			usrDAO.update(usuario, session);
			tx.commit();
			this.setSessionValue(Const.USUARIO, usuario);
		} catch(Exception ex) {
			AddErrorMessage((new DataAccessErrorException()).getMessage());
			ex.printStackTrace();
		}
	}
	
 
	
private  void enviarConGMail(TitularWeb olUser) throws Exception {
		
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
   		String mail_to = olUser.getEmail();
        String subject =  GetMensaje(property.getProperty("asuntoOlvidoSuContrasenia"));
 	    String body =   GetMensaje(property.getProperty("cuerpoOlvidoSuContrasenia")); 
   	   
		Properties props = new Properties();

		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", mail_smtp_host);  
		props.put("mail.smtp.ssl.trust", mail_smtp_ssl_trust );

		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", mail_smtp_starttls_enable);

		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port",mail_smtp_port);

		// Nombre del usuario
		//props.setProperty("mail.smtp.user", mail_smtp_user);

		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", mail_smtp_auth);

		//javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
		
		javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
			
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(mail_from, mail_smtp_password);

            }

        });

		// Para obtener un log de salida más extenso
		session.setDebug(true);
		
		MimeMessage message = new MimeMessage(session);

		// Quien envia el correo
		message.setFrom(new InternetAddress(mail_from,"asecorportal"));

		// A quien va dirigido
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(  mail_to )); 
		 
		message.setSubject(  "Recuperar Contrase\u00F1a", "UTF-8");
		//message.setText(reemplazarEtiquetas(body,olUser) ); 
 		
		
 		MimeBodyPart mimeBodyPart = new MimeBodyPart();
 		mimeBodyPart.setContent(reemplazarEtiquetas(body,olUser), "text/html"); 
 		Multipart multipart = new MimeMultipart();
 	    // Agregar la parte del mensaje HTML al multiPart
 		multipart.addBodyPart(mimeBodyPart);
 		message.setContent(multipart);
 		
		
		Transport t = session.getTransport("smtp");

		// Aqui usuario y password de gmail
		//t.connect(mail_from , mail_smtp_password );
		t.connect();
		try {
			t.sendMessage(message,message.getAllRecipients());
		} finally {
			t.close();
		}
		
	}
	  
	  private String reemplazarEtiquetas(String mensaje, TitularWeb olUser) {		
			
			 if(mensaje.indexOf("#user#") >= 0)
			 	mensaje = mensaje.replaceFirst("#user#", olUser.getNombres() );//+" "+ olUser.getSurname());  
			
			 if(mensaje.indexOf("#pin#") >= 0)
				 	mensaje = mensaje.replaceFirst("#pin#",  olUser.getPin());
			 
			 if(mensaje.indexOf("#email#") >= 0)
				 	mensaje = mensaje.replaceFirst("#email#",  olUser.getEmail());
			 
			//f(mensaje.indexOf("#cel#") >= 0)
				//mensaje = mensaje.replaceFirst("#cel#",  olUser.getPhoneNumber());
			 
			 if(mensaje.indexOf("#userId#") >= 0)
				 	mensaje = mensaje.replaceFirst("#userId#",  olUser.getId().toString());
			 
			 
			 System.out.println("comesFrom "+comesFrom);
			 
			 if(mensaje.indexOf("#comesFrom#") >= 0)
				 	mensaje = mensaje.replaceFirst("#comesFrom#",  this.comesFrom);
			 
			 
			 
			 
			return mensaje;
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
	
 
	
	 
	
	/* (non-Javadoc)
	 * @see com.refinor.extranet.faces.base.AbstBackingBean#getPuedeIngresar()
	 */
	@Override
	public Boolean getPuedeIngresar() {
		return new Boolean(true);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	 

	/**
	 * @return the mostrarResultado
	 */
	public java.lang.Boolean getMostrarResultado() {
		return mostrarResultado;
	}

	/**
	 * @param mostrarResultado the mostrarResultado to set
	 */
	public void setMostrarResultado(java.lang.Boolean mostrarResultado) {
		this.mostrarResultado = mostrarResultado;
	}

	 
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	

	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
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


	public String getComesFrom() {
		return comesFrom;
	}


	public void setComesFrom(String comesFrom) {
		this.comesFrom = comesFrom;
	}

	 
}
