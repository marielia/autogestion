package com.asecor.extranet.faces.seguridad;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.asecor.extranet.data.TitularWeb;
import com.asecor.extranet.data.dao.UsuarioWebDAO;
import com.asecor.extranet.data.dao._RootDAO;
import com.asecor.extranet.faces.base.AbstBackingBean;
import com.asecor.extranet.seguridad.PerfilAdmin;
import com.asecor.extranet.to.DateTO;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.DataUtil;
import com.asecor.extranet.util.exception.DataAccessErrorException;
import com.asecor.extranet.util.exception.UsuarioNoExisteException;
import com.asecor.util.io.FileUtil;
import com.asecor.util.seguridad.PasswordService;  


public class RegistrationConfirmationBean extends AbstBackingBean{

	private Integer id;
	private String pin;

	private String pass;
	private String repetpass;
	private Session session;
	private String email;	
	private java.lang.Boolean mostrarResultado;
	private String comesFrom; 
	 private String aceptar;

	public RegistrationConfirmationBean() throws Exception {
		super();
		 		
		FileUtil fileUtil= new FileUtil();
   		Properties props= fileUtil.getPropertiesFile();
   		File hibernateCfgXml= new File(props.getProperty(Const.ARCHIVO_CONF_HIBERNATE));   		
 		_RootDAO.initialize(hibernateCfgXml);
 		
		 
		inicializar();
		
		
		if(getRequest().getParameter("email") != null)
		{
			email = getRequest().getParameter("email");
			System.out.println("Email " +  email );
			
			//validar el mail
		}
				
		FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
		HttpServletResponse response =
			(HttpServletResponse)context.getExternalContext().getResponse();
		
		response.setHeader("Cache-Control", "no-cache");
	}

	private void inicializar() {
		this.id = 0;		
		this.pin = "";
	
		this.pass = ""; 
		this.repetpass = "";
		this.mostrarResultado= false;
		this.comesFrom =  ""; 
	}
	
	 
	public void aceptar(ActionEvent event) {
		session= (new UsuarioWebDAO()).getSession();
		PerfilAdmin perfAdmin = new PerfilAdmin(session);
		try {
			TitularWeb usuario =  new TitularWeb(); 
			UsuarioWebDAO olUserDAO= new UsuarioWebDAO(session);
			
			try {

				System.out.println("pin " +  pin );
				System.out.println("email " +  email );
			    usuario = olUserDAO.ExistPinAndEmail(this.pin, this.email);
			}
			catch(UsuarioNoExisteException ex)
			{
				//el pin y el mail no son validos
				throw new UsuarioNoExisteException("La clave de ingreso no es válida.");
			} 
			
			 
		/*	 {
			// usuario = olUserDAO.getByUserName( this.dni );
			 if(usuario!=null)
				 throw new UsuarioYaRegistradoException("El nombre de usuario ya está registrado"); 
			}catch(UsuarioNoExisteException ex)
			{
				
			}
			
			*/
			PasswordService ps= PasswordService.getInstance();
			
			DataUtil dataUtil = new DataUtil();
			DateTO dateTO = dataUtil.getFechaActual(); 
			usuario = olUserDAO.ExistPinAndEmail(this.pin, this.email);
			//usuario.setUserName(username); 
			usuario.setPassword(ps.encrypt(this.pass)); 
			usuario.setUpdateDate( new Date());
			usuario.setUpdateUser(Const.SISTEMA);  
			usuario.setFailedLogons(0); 
			usuario.setPasswordChange(false);
			usuario.setConfirmed(true); 
			usuario.setLastFailedLogon(new Date());
			
			usuario= olUserDAO.confirmUser(usuario); 
			
		//	OlUserAgencyDAO olUserAgencyDAO = new OlUserAgencyDAO(session);
		//	OlUserAgency olUserAgency =	olUserAgencyDAO.getByUser(usuario.getId());			
		//	this.comesFrom= olUserAgency.getAgencyId().toString();
			 
			mostrarResultado= true; 
			
			System.out.println("comesFrom= " +  this.comesFrom  );
			
		} catch (UsuarioNoExisteException ex) {
			AddErrorMessage(ex.getMessage());
		}  catch (DataAccessErrorException ex) {
			AddErrorMessage(ex.getMessage()); 
		} catch(Exception ex) {
			ex.printStackTrace();
			AddErrorMessage("Se produjo un error al confirmar la registración.");
		}
		
	}
  
	public String verificarEmail() {
		return Const.SALIR;
	}
	
	
	 
	public String cancelar() {
		return Const.SALIR;
	}
 
	   
	
	public Boolean getPuedeIngresar() {
		return  true;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRepetpass() {
		return repetpass;
	}

	public void setRepetpass(String repetpass) {
		this.repetpass = repetpass;
	}

	public String getComesFrom() {
		return comesFrom;
	}

	public void setComesFrom(String comesFrom) {
		this.comesFrom = comesFrom;
	}

	public String getAceptar() {
		return aceptar;
	}

	public void setAceptar(String aceptar) {
		this.aceptar = aceptar;
	}

	 
	 
	 
}
