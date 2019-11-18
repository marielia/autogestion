package com.refinor.extranet.faces.formularios;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itsolver.util.seguridad.Autenticador;
import com.itsolver.util.seguridad.PassGenerator;
import com.itsolver.util.seguridad.PasswordService;
import com.refinor.extranet.data.Mchofer;
import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.Mempleado;
import com.refinor.extranet.data.MgrupoUn;
import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.dao.AlmacenDAO;
import com.refinor.extranet.data.dao.MchoferDAO;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MempleadoDAO;
import com.refinor.extranet.data.dao.MgrupoUnDAO;
import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.seguridad.PerfilAdmin;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.MEmpleadosTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.ChoferYaExisteException;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.DatosObligatoriosException;
import com.refinor.extranet.util.exception.ExisteEmailException;
import com.refinor.extranet.util.exception.NoSePudeEnviarMailException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;
import com.refinor.extranet.util.exception.UsuarioYaRegistradoException;

public class AltaUsuarioWebEmpleadoBean extends AbstBackingBean {
	protected Session sessionHib;
	
	private int pantalla;	//1 - alta chofer 2-mensaje guardado alta
	private List<MEmpleadosTO> empleados;
	private String empleado;
	private String email;	
	private String nombreUsuario;	
	private Messages mensajeria;
	private String mensajeGuardado;
	private Integer codEmpleado;
	private Integer codCCSS;
	private Integer nroDocumento;
	
	private String nombre;	
	private String codigoCliente;
	
	public AltaUsuarioWebEmpleadoBean() {
		try{
			sessionHib = (new AlmacenDAO()).getSession();	
			mensajeria = new Messages();
			
			inicializarValoresAlta();
			listarEmpleados();
			
			
			}catch(Exception ex){
				ex.printStackTrace();
				AddErrorMessage(ex.getMessage());
			}
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

	private void inicializarValoresAlta(){		
		this.pantalla= 1 ;				
		
	}
	
	
	/**
	 * Metodo:
	 * Funcion: Cargar combo de empleados
	 *
	 */
	private void listarEmpleados(){
		 MempleadoDAO mdao = new MempleadoDAO(sessionHib);
		 empleados = new ArrayList<MEmpleadosTO>();
	
		try {
			MEmpleadosTO mObj;			
			List lstDatos = mdao.getEmpleados();
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mObj = (MEmpleadosTO)it.next();
				empleados.add(mObj);
			}		
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los empleados.");
		}	
		
	}
	
	public void guardar(ActionEvent event){
		pantalla= 2;
		Transaction tx= null;
		 PerfilAdmin perfAdmin = new PerfilAdmin(sessionHib);
			try {
				
			//validaciones
			if(this.nombreUsuario==null || this.nombreUsuario.trim().equals("") || this.email==null || this.email.trim().equals("")
					){
				throw new DatosObligatoriosException(mensajeria.getMessage().getString("datos_onligatorios_label"));
			}	
						
			PasswordService ps= PasswordService.getInstance();
			MusuarioWeb usuario = null;
			usuario = new MusuarioWeb();
			
			usuario.setEmail(email);
			usuario.setCodClienteAlfa(nombreUsuario);
			usuario.setCuit(nroDocumento.toString());
			usuario.setEstado(Const.USUARIO_ACTIVO);
			usuario.setCodigoRol(Const.ROL_EMPLEADO);
			usuario.setCambioClave(new Integer(1));
			usuario.setIntentosFallidos(new Integer(0));
			usuario.setUltimoIntentoFallido(Const.FECHA_RESET_INTENTO_FALLIDO);			
			usuario.setFechaAlta(Calendar.getInstance().getTime());
			String clave= (new PassGenerator()).generateIt();
			usuario.setClave(ps.encrypt(clave));
			usuario.setTipo(0);
			usuario.setCodigoEmpleado(codEmpleado);
			usuario.setCodigoCcssEmpleado(codCCSS)	;		
			usuario= perfAdmin.registrarUsuarioEmpleado(codEmpleado, codCCSS, usuario,sessionHib);
								
			enviarEmail(clave);
						
			//envio de mail a refipass para hacerle saber que hay un chofer esperando el ok				
			mensajeGuardado=mensajeria.getMessage().getString("registro_ok_usuario_empleado_msg");
			pantalla=3;
			
		} catch (PersonaNoExisteException ex) {
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());			
		} catch (UsuarioYaRegistradoException ex) {
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		} catch (ExisteEmailException ex) {
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage()); 
		}catch (DataAccessErrorException ex) {
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage()); 
		} catch(Exception ex) {
			ex.printStackTrace();
			AddErrorMessage("Se produjo un error desconocido al realizar el ingreso de empleado.");
		}
		
	}
	
	private void enviarEmail(String password) throws Exception {
		try{
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
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;		
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
	
	private String reemplazarEtiquetas(String mensaje, String password) {		
		
		if(mensaje.indexOf("#Clave#") >= 0)
			mensaje = mensaje.replaceFirst("#Clave#", password);
		
		if(mensaje.indexOf("#Nombre#") >= 0)
			mensaje = mensaje.replaceFirst("#Nombre#", empleado);
		
		if(mensaje.indexOf("#CodigoCliente#") >= 0)
			mensaje = mensaje.replaceFirst("#CodigoCliente#", nombreUsuario);
		
		if(mensaje.indexOf("#Email#") >= 0)
			mensaje = mensaje.replaceFirst("#Email#", email);

		return mensaje;
	}
	
	public Mempleado obtenerNombreEmpleado(int codigo,int ccss) throws PersonaNoExisteException, DataAccessErrorException{
		MempleadoDAO mEmpleadoDAO = new MempleadoDAO(sessionHib);
		Mempleado mEmpleado=new Mempleado();
		
		try {
			List empleados = mEmpleadoDAO.getEmpleadoPorCodigoyCCSS(codigo,ccss);
			mEmpleado = (Mempleado)empleados.get(0);
			return mEmpleado;
		} catch (PersonaNoExisteException e) {			
			e.printStackTrace();
			throw e;
		} catch (DataAccessErrorException e) {			
			e.printStackTrace();
			throw e;
		}		
	}
	
	/**
	 * Salir de la pantalla.
	 * @author snieto
	 * @return String Pagina a donde vuelve
	 */
	public String volver() {		
		String goToPage = Const.ANCLA_LISTA_REPORTES_DISPONIBLES;			
		return goToPage;
			
	}
	
	public void volverPaginaPrincipal(ActionEvent event) {		
		pantalla=1;			
	}
	
	
	
	public void mostrarAltaEmpleado(ActionEvent event){
		UIParameter component = (UIParameter) event.getComponent().findComponent("codEmpleado");
		codEmpleado = new Integer(component.getValue().toString()).intValue();
		
		UIParameter component1 = (UIParameter) event.getComponent().findComponent("codCCSS");
		codCCSS = new Integer(component1.getValue().toString()).intValue();
		
		UIParameter component2 = (UIParameter) event.getComponent().findComponent("empleado");
		empleado =component2.getValue().toString();
		
		UIParameter component3 = (UIParameter) event.getComponent().findComponent("nroDoc");
		nroDocumento =new Integer(component3.getValue().toString()).intValue();
		
		this.nombreUsuario=null;
		this.email=null;
		pantalla=2;
		
	}
	@Override
	public Boolean getPuedeIngresar() {
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

/*	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}*/




	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public int getPantalla() {
		return pantalla;
	}

	public void setPantalla(int pantalla) {
		this.pantalla = pantalla;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<MEmpleadosTO> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<MEmpleadosTO> empleados) {
		this.empleados = empleados;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public Integer getCodCCSS() {
		return codCCSS;
	}

	public void setCodCCSS(Integer codCCSS) {
		this.codCCSS = codCCSS;
	}

	public Integer getCodEmpleado() {
		return codEmpleado;
	}

	public void setCodEmpleado(Integer codEmpleado) {
		this.codEmpleado = codEmpleado;
	}

	public Integer getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(Integer nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

}
