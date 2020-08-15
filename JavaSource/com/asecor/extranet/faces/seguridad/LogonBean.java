/**
 * 
 */
package com.asecor.extranet.faces.seguridad;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.asecor.extranet.data.Polizas;
import com.asecor.extranet.data.TitularWeb;
import com.asecor.extranet.data.Titulares;
import com.asecor.extranet.data.dao.PolizasDAO;
import com.asecor.extranet.data.dao.TitularesDAO;
import com.asecor.extranet.data.dao.UsuarioWebDAO;
import com.asecor.extranet.data.dao._RootDAO;
import com.asecor.extranet.faces.base.AbstBackingBean;
import com.asecor.extranet.seguridad.PerfilAdmin;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.exception.DataAccessErrorException;
import com.asecor.extranet.util.exception.TitularNoExisteExcepcion;
import com.asecor.extranet.util.exception.UsuarioNoActivoException;
import com.asecor.extranet.util.exception.UsuarioNoExisteException;
import com.asecor.util.exception.ProtocoloDeSeguridadException;
import com.asecor.util.io.FileUtil;
import com.asecor.util.seguridad.PasswordService;

 


public class LogonBean extends AbstBackingBean {

	private Long dni;
	
	private String clave;
	private Boolean mostrarPagina;
	private String  comesFrom;
	private Session session;
	private Boolean puedeIngresar;
	private String  userId;
	private Titulares titular;
	private String email;
	private List<Polizas> polizas;
	private TitularWeb usuario;
	private Boolean cambiaPass;
private String repetpass;
private String pass;
private Polizas poliza;
private UsuarioWebDAO usrDAO;

	public void preRenderView() {
	      HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true );
	      //tune session params, eg. session.setMaxInactiveInterval(..);

	      //perform other pre-render stuff, like setting user context...
	}
	
	public LogonBean(){
		super(); 
		
		try
		{
	
			
	 		FileUtil fileUtil= new FileUtil();
	   		Properties props= fileUtil.getPropertiesFile();
	   		File hibernateCfgXml= new File(props.getProperty(Const.ARCHIVO_CONF_HIBERNATE)); 
	   				_RootDAO.initialize(hibernateCfgXml);
	 		
			session=  new UsuarioWebDAO().getSession(); 
		
			inicializarValores(); 
			    
			 this.comesFrom =  "1819"; 
			 puedeIngresar = true; 
			 
			FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
			response.setHeader("Cache-Control", "no-cache"); 
		
		}catch(Exception ex)
		{
			ex.printStackTrace();			
		}
	}
	
	
	public String cambiarPassword() {
		PasswordService ps= PasswordService.getInstance();
		System.out.println(this.usuario.getId());
	//	this.usuario.setPasswordChange(true);
		try {
			this.usuario.setPassword(ps.encrypt(this.pass));
		} catch (ProtocoloDeSeguridadException e) {
			// TODO Auto-generated catch block
			AddErrorMessage("Se produjo un error al cambiar la contraseña.");
		} 
		usrDAO.saveOrUpdate(this.usuario);
		this.cambiaPass=true;
			return "polizasUsuarios";
		}
	public String cambiarTelefono() {
	
		System.out.println(this.usuario.getId());
	//	this.usuario.setPasswordChange(true);
		
		usrDAO.saveOrUpdate(this.usuario);
		this.cambiaPass=true;
		
			return "polizasUsuarios";
		}
	
	
	 
	
	private void inicializarValores()
	{
		dni=null;
		clave="";
		email=null;
		mostrarPagina= true;
		usuario=new TitularWeb();
		
	}
	
	@Override
	public Boolean getPuedeIngresar() { 
		return puedeIngresar;
	}
	
	public String validarUsuario(){
		String goToPage = Const.ANCLA_BIENVENIDO;			
		return goToPage;
	}
	
	public String chkLogon() {
		
		 
		 usrDAO = new UsuarioWebDAO(session);	
		//DataUtil dataUtil = new DataUtil();
		Properties properties= (new FileUtil()).getPropertiesFile();
		HttpSession sesion= this.getSession();
		 
		sesion.setMaxInactiveInterval(Integer.parseInt((String)properties.get(Const.SESSION_TIMEOUT)));
		PasswordService ps= PasswordService.getInstance();
		PerfilAdmin perfAdmin= new PerfilAdmin(session);
		 
		puedeIngresar=true;
		try { 
			
			if(email.equals(" "))
				throw new UsuarioNoExisteException();		
			
			//usuario= usrDAO.getByUserDniAndPass(dni, ps.encrypt(clave)); 
			this.usuario= usrDAO.getByEmailAndPass(email, ps.encrypt(clave));
			
			usrDAO.refresh(usuario, session);
			// Chequea si el Usuario estÃ¡ Activo
			if(!usuario.getActive())
				throw new UsuarioNoActivoException();
			
			if(usuario.getPasswordChange())
				throw new DataAccessErrorException("El usuario no terminó el proceso de cambio de contraseña");
			
			perfAdmin.limpiarIntentosFallidos(usuario); 
			//TODO buscar si existe el dni en los titulares
			TitularesDAO titularesDAO=new TitularesDAO(session);
			 titular=titularesDAO.findTitularByUserDni(usuario.getDni());
			 System.out.println(titular.getId());
			 //Titulares tit=new Titulares(titular.getId());
			// Set<Polizas> lista=new HashSet<Polizas>();
			// lista=titular.getPolizas();
			 PolizasDAO polizasdao=new PolizasDAO(session);
			// titular.getPolizas();
			 polizas= polizasdao.findPolizasByTitular(titular);
			 
			
			 
			// this.polizas.addAll(titular.getPolizas());
			 
				   
			return Const.ANCLA_POLIZAS_USUARIO;//Const.ANCLA_BIENVENIDO; 	
		} catch (TitularNoExisteExcepcion ex) {
			sesion.setAttribute(Const.USUARIO, null);
			
			AddErrorMessage(ex.getMessage());
			sesion.setAttribute(Const.CLIENTE, null);
			 return Const.ANCLA_INDEX;
		 	
			
		} catch (UsuarioNoExisteException ex) {
				// Chequea si el error fue de contraseÃ±a
				 try {
					 System.out.println("codigoCliente ");
					 usuario= usrDAO.getByUserDni(usuario.getDni());
					
					// Chequea si el Usuario estÃ¡ Activo
				 	if(!usuario.getActive())
				 	   throw new UsuarioNoActivoException();
					
				 	perfAdmin.registrarLogonFallido(usuario);
				 	
				 	System.out.println("El usuario o la contraseña no son válidos.");
					AddErrorMessage("El usuario o la contraseña no son válidos.");
					 
					 return Const.ANCLA_INDEX;
			    } catch(UsuarioNoActivoException exp) {
					// Chequea si es un Usuario Interno
					exp.printStackTrace();
					AddErrorMessage(exp.getMessage());
				
					 return Const.ANCLA_INDEX;
				} catch( UsuarioNoExisteException exp) {
					// Chequea si es un Usuario Interno
						exp.printStackTrace();
						AddErrorMessage(exp.getMessage());
					 	return Const.ANCLA_INDEX;

				} catch(Exception e) {
					e.printStackTrace();
					AddErrorMessage(new DataAccessErrorException().getMessage());					
					 return Const.ANCLA_INDEX;
				}
				
				
 		} catch (UsuarioNoActivoException exp) {
 				sesion.setAttribute(Const.USUARIO, null);
 				sesion.setAttribute(Const.CLIENTE, null);
 				AddErrorMessage(exp.getMessage());
 				 return Const.ANCLA_INDEX;
		}catch (DataAccessErrorException ex) {
			ex.printStackTrace();
			sesion.setAttribute(Const.USUARIO, null);
			AddErrorMessage(ex.getMessage());
			sesion.setAttribute(Const.CLIENTE, null);
			 return Const.ANCLA_INDEX;
		} catch (Exception ex) {
			sesion.setAttribute(Const.USUARIO, null);
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			sesion.setAttribute(Const.CLIENTE, null);
			 return Const.ANCLA_INDEX;
		}  finally {
			//if(sesion.isNew())
			//session.close();
		}
	} 
	
	public String salir() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		//NavigationUtils.redirect("/admin/login");
		return "index";
	}


	public Titulares getTitular() {
		return titular;
	}

	public void setTitular(Titulares titular) {
		this.titular = titular;
	}

	public String cancelar() {
		this.dni=null;		
		this.clave=null;
		getSession().setAttribute(Const.CLIENTE, null);
		getSession().setAttribute(Const.USUARIO, null);
		return Const.ANCLA_INDEX;
	}
	

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}



	public Boolean getMostrarPagina() {
		return mostrarPagina;
	}

	public void setMostrarPagina(Boolean mostrarPagina) {
		this.mostrarPagina = mostrarPagina;
	}

	public Long getCodigoCliente() {
		return dni;
	}

	public void setCodigoCliente(Long codigoCliente) {
		this.dni = codigoCliente;
	}

	
	@PostConstruct
	public void postConstruct() {
	FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	/*PolizasDAO polizasdao=new PolizasDAO(session);
	 try {
		polizas= polizasdao.findPolizasByTitular(titular);
	} catch (UsuarioNoExisteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DataAccessErrorException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
	}

	public String getComesFrom() {
		return comesFrom;
	}

	public void setComesFrom(String comesFrom) {
		this.comesFrom = comesFrom;
	}

	public void setPuedeIngresar(Boolean puedeIngresar) {
		this.puedeIngresar = puedeIngresar;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Polizas> getPolizas() {
		return polizas;
	}

	public void setPolizas(List<Polizas> polizas) {
		this.polizas = polizas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Polizas getPoliza() {
		return poliza;
	}

	public void setPoliza(Polizas poliza) {
		this.poliza = poliza;
	}

	public TitularWeb getUsuario() {
		return usuario;
	}

	public void setUsuario(TitularWeb usuario) {
		this.usuario = usuario;
	}

	public String getRepetpass() {
		return repetpass;
	}

	public void setRepetpass(String repetpass) {
		this.repetpass = repetpass;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Boolean getCambiaPass() {
		return cambiaPass;
	}

	public void setCambiaPass(Boolean cambiaPass) {
		this.cambiaPass = cambiaPass;
	}


	
	
}
