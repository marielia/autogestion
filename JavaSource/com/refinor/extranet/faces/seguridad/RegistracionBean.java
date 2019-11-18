/*
 * Created on Aug 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.refinor.extranet.faces.seguridad;

import com.itsolver.util.io.FileUtil;
import com.itsolver.util.seguridad.Autenticador;
import com.itsolver.util.seguridad.PassGenerator;
import com.itsolver.util.seguridad.PassUtil;
import com.itsolver.util.seguridad.PasswordService;

import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao._RootDAO;
import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.seguridad.PerfilAdmin;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.ExisteEmailException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;
import com.refinor.extranet.util.exception.UsuarioYaRegistradoException;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;


/**
 * @author  mliz  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class RegistracionBean extends AbstBackingBean {

	private Integer id;
	private String nombre;
	private String apellido;	
	private String numeroDocumento;
	private String codigoCliente;
	private String nombreUsuario;
	private String email;	
	private java.util.List tiposCliente;
	private java.lang.Boolean mostrarResultado;
	private Session session;
		
	public RegistracionBean() throws Exception {
		super();
		 		
		session= (new MclientesDAO()).getSession();
		inicializar();
				
		FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
		HttpServletResponse response =
			(HttpServletResponse)context.getExternalContext().getResponse();
		mostrarResultado= new Boolean(false);
		response.setHeader("Cache-Control", "no-cache");
	}

	private void inicializar() {
		id = new Integer(0);		
		numeroDocumento = "";
		nombreUsuario = "";
		email = "";
	}
	
	/**
	 * @param id  the id to set
	 * @uml.property  name="id"
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return  the id
	 * @uml.property  name="id"
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param nombre  the nombre to set
	 * @uml.property  name="nombre"
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return  the nombre
	 * @uml.property  name="nombre"
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param apellido  the apellido to set
	 * @uml.property  name="apellido"
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return  the apellido
	 * @uml.property  name="apellido"
	 */
	public String getApellido() {
		return apellido;
	}

	

	/**
	 * @param numeroDocumento  the numeroDocumento to set
	 * @uml.property  name="numeroDocumento"
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return  the numeroDocumento
	 * @uml.property  name="numeroDocumento"
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param nombreUsuario  the nombreUsuario to set
	 * @uml.property  name="nombreUsuario"
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * @return  the nombreUsuario
	 * @uml.property  name="nombreUsuario"
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param email  the email to set
	 * @uml.property  name="email"
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return  the email
	 * @uml.property  name="email"
	 */
	public String getEmail() {
		return email;
	}

	
	
	/**
	 * @param tiposCliente  the tiposCliente to set
	 * @uml.property  name="tiposCliente"
	 */
	public void setTiposCliente(java.util.List tiposCliente) {
		this.tiposCliente = tiposCliente;
	}

	/**
	 * @return  the tiposCliente
	 * @uml.property  name="tiposCliente"
	 */
	public java.util.List getTiposCliente() {
		return tiposCliente;
	}

	public void aceptar(ActionEvent event) {
		//String action = Const.FAIL;
		PerfilAdmin perfAdmin = new PerfilAdmin(session);
		try {
			PasswordService ps= PasswordService.getInstance();
			MusuarioWeb usuario = null;
			usuario = new MusuarioWeb();
			
			usuario.setEmail(email);
			usuario.setEstado(Const.USUARIO_ACTIVO);
			usuario.setCodigoRol(Const.ROL_CLIENTE);
			usuario.setCambioClave(new Integer(1));
			usuario.setIntentosFallidos(new Integer(0));
			usuario.setUltimoIntentoFallido(Const.FECHA_RESET_INTENTO_FALLIDO);			
			usuario.setFechaAlta(Calendar.getInstance().getTime());
			String clave= (new PassGenerator()).generateIt();
			//String clave= "4321";
			usuario.setClave(ps.encrypt(clave));
			usuario.setTipo(1);
			usuario= perfAdmin.registrarUsuario(numeroDocumento, usuario,session);
			
			nombre= obtenerNombre(usuario.getCuit(),email).getDescripcion();
			codigoCliente= usuario.getCodClienteAlfa();
			mostrarResultado= new Boolean(true);
			enviarEmail(clave);
			
		} catch (PersonaNoExisteException ex) {
			AddErrorMessage(ex.getMessage());			
		} catch (UsuarioYaRegistradoException ex) {
			AddErrorMessage(ex.getMessage());
		} catch (ExisteEmailException ex) {
			AddErrorMessage(ex.getMessage()); 
		}catch (DataAccessErrorException ex) {
			AddErrorMessage(ex.getMessage()); 
		} catch(Exception ex) {
			ex.printStackTrace();
			AddErrorMessage("Se produjo un error desconocido al realizar el ingreso de cliente.");
		}
		
	}

	public Mclientes obtenerNombre(String cuit,String email) throws PersonaNoExisteException, DataAccessErrorException{
		MclientesDAO mclientesDAo = new MclientesDAO(session);
		Mclientes mcliente=new Mclientes();
		
		try {
			List clientes = mclientesDAo.getPorDocumento(cuit,email);
			mcliente = (Mclientes)clientes.get(0);
			return mcliente;
		} catch (PersonaNoExisteException e) {			
			e.printStackTrace();
			throw e;
		} catch (DataAccessErrorException e) {			
			e.printStackTrace();
			throw e;
		}		
	}
	public String cancelar() {
		return Const.SALIR;
	}

	private void enviarEmail(String password) throws Exception {
		InitialContext ic = new InitialContext();
		Context envCtx = (Context) ic.lookup("java:comp/env");
		javax.mail.Session session = (javax.mail.Session) envCtx.lookup("mail/Session");
		Properties props = session.getProperties();
		if (props.containsKey("mail.smtp.auth") && props.getProperty("mail.smtp.auth").equals("true"))
		{
			session = javax.mail.Session.getDefaultInstance(session.getProperties(), new Autenticador(props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password")));
		}
		
		System.out.println("Va a utilizar como archivo de Subject: "+props.getProperty("SubjectFile"));
		String subject = GetMensaje(props.getProperty("SubjectFile"));
		
		System.out.println("Va a utilizar como archivo de Body: "+props.getProperty("BodyFile"));
		String body = GetMensaje(props.getProperty("BodyFile"));
		
		MimeMessage msg = new MimeMessage(session);
		msg.setHeader("Content-Type", "text/html; charset=ISO-8859-2");		
		msg.setContentLanguage(new String[] {"es-ar"});
		msg.addHeader("Content-Transfer-Encoding", "base64");
		msg.setSubject(reemplazarEtiquetas(subject, password), "ISO-8859-2");
		msg.setSentDate(new java.util.Date());
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
		msg.setFrom(new InternetAddress(props.getProperty("mail.from")));
		msg.setHeader("X-Mailer", "sendhtml");
		msg.setText(reemplazarEtiquetas(body, password), "ISO-8859-2");
		//msg.setDataHandler(new DataHandler(new
		//		ByteArrayDataSource(reemplazarEtiquetas(body, password),"text/html;charset=iso-8859-1")));
		Transport.send(msg);
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
		
		if(mensaje.indexOf("#Clave#") >= 0)
			mensaje = mensaje.replaceFirst("#Clave#", password);
		
		if(mensaje.indexOf("#Nombre#") >= 0)
			mensaje = mensaje.replaceFirst("#Nombre#", nombre);
		
		if(mensaje.indexOf("#CodigoCliente#") >= 0)
			mensaje = mensaje.replaceFirst("#CodigoCliente#", codigoCliente);
		
		if(mensaje.indexOf("#Email#") >= 0)
			mensaje = mensaje.replaceFirst("#Email#", email);

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

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
}
