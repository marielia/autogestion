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
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.asecor.util.io.FileUtil;
import com.asecor.util.seguridad.Autenticador;
import com.asecor.util.seguridad.PassGenerator;
import com.asecor.util.seguridad.PasswordService;
import com.captcha.botdetect.web.jsf.JsfCaptcha;
import com.asecor.extranet.data.TitularWeb;
import com.asecor.extranet.data.dao.UsuarioWebDAO;
import com.asecor.extranet.faces.base.AbstBackingBean;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.DataUtil;
import com.asecor.extranet.seguridad.PerfilAdmin;
import com.asecor.extranet.to.DateTO;
import com.asecor.extranet.util.exception.DataAccessErrorException;
import com.asecor.extranet.util.exception.ExisteEmailException;
import com.asecor.extranet.util.exception.PersonaNoExisteException;
import com.asecor.extranet.util.exception.UsuarioYaRegistradoException; 

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


public class UserRegisterBean extends AbstBackingBean{

	private Integer id;
	private String nombres;
	private String surname;

	private Session session;
	private String phoneNumber;
	private String email;	
	private java.lang.Boolean mostrarResultado;
	private Long dni;
	private Date fechaNacimiento;
	
	private String codigoCliente;
	private String nombreUsuario;  
	
	protected Integer estado;	
	protected List<SelectItem> estados;
	
	private String comesFrom;
	private String captchaCode;
	  private JsfCaptcha captcha;
	public UserRegisterBean() throws Exception {
		super();
		
		session= (new UsuarioWebDAO()).getSession();
		inicializar(); 
		
		FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
		HttpServletResponse response =
			(HttpServletResponse)context.getExternalContext().getResponse();
		response.setHeader("Cache-Control", "no-cache");
	}

	private void inicializar() {
		id = 0;		
		nombres = "";
		surname = "";
		email = "";
		phoneNumber = "";
		//dni=0;
		fechaNacimiento=new Date();
		
		estados = new ArrayList<SelectItem>(); 
		//estados.add(new SelectItem(new Integer(1),"CELULAR"));
		estados.add(new SelectItem(0,"EMAIL"));
		estado = 0;
		 
	}
	public void validate() {
	    // validate the Captcha to check we're not dealing with a bot
	    boolean isHuman = captcha.validate(captchaCode);

	    if (isHuman) {
	      // TODO: Captcha validation passed, perform protected action
	    } else {
	      // TODO: Captcha validation failed, show error message
	    }

	   
	  }
	 
		public String aceptar() {
			boolean isHuman = captcha.validate(captchaCode);
			if (isHuman) {
				PerfilAdmin perfAdmin = new PerfilAdmin(session);
				try {
					PasswordService ps = PasswordService.getInstance();
					TitularWeb usuario = new TitularWeb();
					DataUtil dataUtil = new DataUtil();
					DateTO dateTO = dataUtil.getFechaActual();
					usuario.setNombres(nombres);
					// usuario.setSurname(surname.trim());
					usuario.setEmail(email.trim());
					usuario.setTelefono(phoneNumber.trim());
					usuario.setActive(true);
					usuario.setCreateDate(new Date());
					usuario.setCreateUser(Const.SISTEMA);
					usuario.setPin(new Date().getTime() + "");
					usuario.setConfirmed(false);
					usuario.setDni(dni);
					usuario.setFechaNacimiento(fechaNacimiento);
					// guardar la relacion usuario y agencia
					UsuarioWebDAO userDAO = new UsuarioWebDAO(session);
					if (!userDAO.existEmail(email.trim())) {
						usuario = userDAO.registerUser(usuario);

						mostrarResultado = true;

						if (estado == 0) {
							// registracion por email
							enviarConGMail(usuario);

						}
					} else {
						throw new ExisteEmailException();
					}
				} catch (PersonaNoExisteException ex) {
					AddErrorMessage(ex.getMessage());
				} catch (UsuarioYaRegistradoException ex) {
					AddErrorMessage(ex.getMessage());
				} catch (ExisteEmailException ex) {
					AddErrorMessage(ex.getMessage());
				} catch (DataAccessErrorException ex) {
					AddErrorMessage(ex.getMessage());
				} catch (Exception ex) {
					ex.printStackTrace();
					AddErrorMessage("Se produjo un error desconocido al realizar verificar el email.");
					mostrarResultado = false;

				}

			} else {
				this.captchaCode = "";
				AddErrorMessage("Debe ingresar los caracteres del catpcha");
				mostrarResultado = false;
			}
			return "registrar";
		}
	 
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String cancelar() {
		return Const.SALIR;
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
   		System.out.println(" olUser.getEmail() "+ olUser.getEmail());
   		String mail_to = olUser.getEmail();
        String subject =  GetMensaje(property.getProperty("asuntoRegistracion"));
 	    String body =   GetMensaje(property.getProperty("cuerpoRegistracion")); 
   	   
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

	
		
 		MimeBodyPart mimeBodyPart = new MimeBodyPart();
 		mimeBodyPart.setContent(reemplazarEtiquetas(body,olUser), "text/html"); 
 		Multipart multipart = new MimeMultipart();
 	    // Agregar la parte del mensaje HTML al multiPart
 		multipart.addBodyPart(mimeBodyPart); 
 		message.setContent(multipart); 
 	
 		message.setSubject( "Confirmaci\u00F3n de registro", "UTF-8"); // MimeUtility.encodeText("Confirmaci\\u00F3n de registro", "utf-8", "B") ); //subject
		//message.setText(reemplazarEtiquetas(body,olUser) );  
		
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
	
 
	
	private String reemplazarEtiquetas(String mensaje, String password) {		
		
//		if(mensaje.indexOf("#Clave#") >= 0)
//			mensaje = mensaje.replaceFirst("#Clave#", password);
//		
//		if(mensaje.indexOf("#Nombre#") >= 0)
//			mensaje = mensaje.replaceFirst("#Nombre#", nombre);
//		
//		if(mensaje.indexOf("#CodigoCliente#") >= 0)
//			mensaje = mensaje.replaceFirst("#CodigoCliente#", codigoCliente);
//		
//		if(mensaje.indexOf("#Email#") >= 0)
//			mensaje = mensaje.replaceFirst("#Email#", email);

		return mensaje;
	}
	
	public Boolean getPuedeIngresar() {
		return new Boolean(true);
	}
	
	/**
	 * @return  Returns the mostrarFormulario.
	 * @uml.property  name="mostrarResultado"
	 */
	public java.lang.Boolean getMostrarResultado() {
		return mostrarResultado;
	}
	/**
	 * @param mostrarFormulario  The mostrarFormulario to set.
	 * @uml.property  name="mostrarResultado"
	 */
	public void setMostrarResultado(java.lang.Boolean mostrarResultado) {
		this.mostrarResultado = mostrarResultado;
	}

	@Override
	protected void finalize() throws Throwable {
		session.close();
		super.finalize();
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String name) {
		this.nombres = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	public JsfCaptcha getCaptcha() {
		return captcha;
	}

	public void setCaptcha(JsfCaptcha captcha) {
		this.captcha = captcha;
	}

	 
	 
}
