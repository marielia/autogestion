/**
 * 
 */
package com.refinor.extranet.faces.seguridad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.itsolver.util.io.FileUtil;
import com.itsolver.util.seguridad.Autenticador;
import com.itsolver.util.seguridad.PassGenerator;
import com.itsolver.util.seguridad.PasswordService;
import com.refinor.extranet.data.dao._RootDAO;
import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.seguridad.PerfilAdmin;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.UsuarioNoActivoException;
import com.refinor.extranet.util.exception.UsuarioNoExisteException;

/**
 * @author snieto
 *
 */
public class OlvidoSuContraseniaBean extends AbstBackingBean {

	private Integer id;
	private Integer tipoDocumento;
	private String numeroDocumento;
	private java.util.List<SelectItem> tiposDocumento;
	private java.lang.Boolean mostrarResultado;
	private Session session;
	private Messages mensajeria;
	private String claveNueva;
	private String email;
	private String nombre;
	
	

	public OlvidoSuContraseniaBean() {
		super();
	try{		   		
		 		
		/*session= (new TipoDocumentoDAO()).getSession();
		mensajeria  = new Messages();
		inicializar();
		cargarTiposDocumentos();
		
		FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
		HttpServletResponse response =
			(HttpServletResponse)context.getExternalContext().getResponse();		
		mostrarResultado= new Boolean(false);		
		response.setHeader("Cache-Control", "no-cache");*/
		
	}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());			
		}
		
	}
	
	/**
	 * Inicializacion de campos
	 * */
	private void inicializar() {		
		tipoDocumento = new Integer(-1);
		numeroDocumento = "";		
	}
	
	/**
	 * Cargar los tipos de documentos
	 * */
	/*public void cargarTiposDocumentos() {
		TipoDocumentoDAO tipoDocDAO = new TipoDocumentoDAO(session);		
		tiposDocumento = new ArrayList<SelectItem>();

		try {
			TipoDocumento tipoDoc;
			List tiposDoc = tipoDocDAO.getTiposDocumentoReales();
			Iterator it = tiposDoc.iterator();
			tiposDocumento.add(new SelectItem("-1",mensajeria.getMessage().getString("seleccione")));

			while(it.hasNext()) {
				tipoDoc = (TipoDocumento)it.next();
				tiposDocumento.add(new SelectItem(tipoDoc.getId(), tipoDoc.getDescripcionBreve()));
			}
		} catch(Exception ex) {
			AddErrorMessage(mensajeria.getMessage().getString("no_se_recupero_tipo_doc_msg"));
		}

	}*/
	
	/** 
	 * Chequea el logon del Usuario
	 * 
	 * @return	Outcome
	 * @throws Exception
	 */
	public void aceptar(ActionEvent event) {
/*		UsuarioDAO usrDAO= new UsuarioDAO(session);
		Properties properties= (new FileUtil()).getPropertiesFile();
		HttpSession sesion= this.getSession();
		sesion.setMaxInactiveInterval(Integer.parseInt((String)properties.get(Const.SESSION_TIMEOUT)));		
		PerfilAdmin perfAdmin= new PerfilAdmin(session);
		Usuario usuario= null;
		try {
			
			if((numeroDocumento.trim().equals("") || numeroDocumento.trim().equals("0"))
					|| (this.tipoDocumento==null || this.tipoDocumento==-1)){
				throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_obligatorios_msg"));
			}
								
			usuario= usrDAO.getPorDocumento(tipoDocumento, numeroDocumento);			
			usrDAO.refresh(usuario, session);
									
			// Chequea si el Usuario está Activo
			// no se valido si esta activo o no - 
			//para ambos casos se cambia el estado a "Activo" - 16/10/2007			
			/*if(usuario.getEstado() != Const.ESTADO_ACTIVO)
				throw new UsuarioNoActivoException();
			
			if(usuario.getEmail()==null || usuario.getEmail().trim().equals(""))
				throw new NoTieneMailException(mensajeria.getMessage().getString("no_tiene_mail_registrado"));	
				
			this.email=usuario.getEmail();
			this.nombre ="";
			perfAdmin.limpiarIntentosFallidos(usuario);
			
			//genera una clave
			this.claveNueva = (new PassGenerator()).generateIt();				
			cambiarClave(usuario.getId());
			enviarEmail(this.claveNueva);
			//todo ok
			mostrarResultado= new Boolean(true);
			
			
		}catch (DatosObligatoriosException ex) {
			ex.printStackTrace();
			inicializar();
			AddErrorMessage(ex.getMessage());			
		}catch (UsuarioNoExisteException ex) {
			ex.printStackTrace();
			inicializar();
			AddErrorMessage(ex.getMessage());			
		} catch (UsuarioNoActivoException ex) {			
			inicializar();
			AddErrorMessage(ex.getMessage());			
		}catch (NoTieneMailException ex) {
			ex.printStackTrace();
			inicializar();
			AddErrorMessage(ex.getMessage());			
		} catch (DataAccessErrorException ex) {
			ex.printStackTrace();			
			inicializar();
			AddErrorMessage(ex.getMessage());			
		} catch (Exception ex) {			
			inicializar();
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());			
		} finally {
			session.close();
		}*/
	}
	
	
	public void cambiarClave(int id) {
		/*UsuarioDAO usrDAO= new UsuarioDAO(session);
		PasswordService ps= PasswordService.getInstance();
		try {
			Transaction tx= session.beginTransaction();
			Usuario usuario = usrDAO.load(id, session);
			usuario.setClave(ps.encrypt(this.claveNueva));			
			usuario.setCambioClave(new Integer(1));		
			usuario.setEstado(Const.ESTADO_ACTIVO);	
			usrDAO.update(usuario, session);
			tx.commit();
			this.setSessionValue(Const.USUARIO, usuario);
		} catch(Exception ex) {
			AddErrorMessage((new DataAccessErrorException()).getMessage());
			ex.printStackTrace();
		}*/
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
		System.out.println("Va a utilizar como archivo de Subject: "+props.getProperty("subject_cambio_clave"));
		String subject = GetMensaje(props.getProperty("subject_cambio_clave"));
		System.out.println("Va a utilizar como archivo de Body: "+props.getProperty("mensaje_cambio_clave"));
		String body = GetMensaje(props.getProperty("mensaje_cambio_clave"));
		MimeMessage msg = new MimeMessage(session);			
		msg.setHeader("Content-Type", "text/html; charset=ISO-8859-1");	
		msg.setContentLanguage(new String[] {"es-ar"});
		msg.addHeader("Content-Transfer-Encoding", "base64");		
		msg.setSubject(reemplazarEtiquetas(subject, password), "ISO-8859-1");		
		msg.setSentDate(new java.util.Date());
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.email, false));
		msg.setFrom(new InternetAddress(props.getProperty("mail.from")));
		msg.setHeader("X-Mailer", "sendhtml");		
		msg.setText(reemplazarEtiquetas(body, password), "ISO-8859-1");
		//msg.setDataHandler(new DataHandler(new
		//		ByteArrayDataSource(reemplazarEtiquetas(body, password),"text/html;charset=iso-8859-1")));
		Transport.send(msg);
	}
	
	private String reemplazarEtiquetas(String mensaje, String password) {
		Iterator i = tiposDocumento.iterator();
		
		while (i.hasNext()) {
			SelectItem item = (SelectItem)i.next();
			if (item.getValue().equals(tipoDocumento)) {
				mensaje = mensaje.replaceFirst("#TipoDocumento#", item.getLabel());
			}
		}
		
		if(mensaje.indexOf("#Password#") >= 0)
			mensaje = mensaje.replaceFirst("#Password#", password);
		if(mensaje.indexOf("#Nombre#") >= 0)
			mensaje = mensaje.replaceFirst("#Nombre#", nombre);
		if(mensaje.indexOf("#NumeroDocumento#") >= 0)
			mensaje = mensaje.replaceFirst("#NumeroDocumento#", numeroDocumento);
		if(mensaje.indexOf("#Email#") >= 0)
			mensaje = mensaje.replaceFirst("#Email#", email);

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
	 * @return the mensajeria
	 */
	public Messages getMensajeria() {
		return mensajeria;
	}

	/**
	 * @param mensajeria the mensajeria to set
	 */
	public void setMensajeria(Messages mensajeria) {
		this.mensajeria = mensajeria;
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
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the tipoDocumento
	 */
	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the tiposDocumento
	 */
	public java.util.List<SelectItem> getTiposDocumento() {
		return tiposDocumento;
	}

	/**
	 * @param tiposDocumento the tiposDocumento to set
	 */
	public void setTiposDocumento(java.util.List<SelectItem> tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
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

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the claveNueva
	 */
	public String getClaveNueva() {
		return claveNueva;
	}

	/**
	 * @param claveNueva the claveNueva to set
	 */
	public void setClaveNueva(String claveNueva) {
		this.claveNueva = claveNueva;
	}
}
