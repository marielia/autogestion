/**
 * 
 */
package com.asecor.extranet.faces.seguridad;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;

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

 


@WebFilter("/app/*")
public class LogonBean  extends AbstBackingBean implements Filter {

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
	private boolean cambiaPass;
	private boolean cambiarTelefono;
	private String repetpass;
	private String pass;
	private String newPass;
	private Polizas poliza;
	private UsuarioWebDAO usrDAO;
	private String chkLogon;
private String cambiarPassword;
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
			 
			//FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
			//HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
			//response.setHeader("Cache-Control", "no-cache"); 
		
		}catch(Exception ex)
		{
			ex.printStackTrace();			
		}
	}
	
	
	public String cambiarPassword() {

		PasswordService ps = PasswordService.getInstance();
		try {
			System.out.println(ps.encrypt(this.pass));
			System.out.println(usuario.getPassword());
			if (ps.encrypt(this.pass).equalsIgnoreCase(usuario.getPassword())) {
				System.out.println(this.usuario.getId());

				try {
					this.usuario.setPassword(ps.encrypt(this.newPass));
				} catch (ProtocoloDeSeguridadException e) {
					// TODO Auto-generated catch block
					AddErrorMessage("Se produjo un error al cambiar la contraseña.");
				}
				usrDAO.saveOrUpdate(this.usuario);
				
				//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
				//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				this.cambiaPass = true;
			}else {
				AddErrorMessage("Error la contraseña ingresada no es correcta.");
			}
		} catch (ProtocoloDeSeguridadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "cambio_password";
	}
	public String cambiarTelefono() {
	
		PasswordService ps = PasswordService.getInstance();
		try {
			System.out.println(ps.encrypt(this.pass));
			System.out.println(usuario.getPassword());
			if (ps.encrypt(this.pass).equalsIgnoreCase(usuario.getPassword())) {
				System.out.println(this.usuario.getId());

				this.usuario.setNombres(this.usuario.getNombres().toUpperCase());
				usrDAO.saveOrUpdate(this.usuario);
				
				//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
				//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				//this.ca = true;
			}else {
				AddErrorMessage("Error la contraseña ingresada no es correcta.");
				return "cambio_usuario";
			}
		} catch (ProtocoloDeSeguridadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "polizasUsuarios";
		}
	
	
	public void permission() throws IOException {
		

       if(this.getUsuario().getActive() == null) {
            System.out.println("*** The user has no permission to visit this page. *** ");
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("salir.jsf");
           
        } else {
            System.out.println("*** The session is still active. User is logged in. *** ");
        }
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
		 
		
		try { 
			
			if(email.equals(" "))
				throw new UsuarioNoExisteException();		
			System.out.println(ps.encrypt(clave));
			System.out.println(clave);
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
			 puedeIngresar=true;
			 //if(!usuario.getConfirmed())
				// return Const.ANCLA_EMAIL; 
			 
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
					 if(usuario==null)
					throw new UsuarioNoExisteException();
					// Chequea si el Usuario estÃ¡ Activo
				 	if(!usuario.getActive())
				 	   throw new UsuarioNoActivoException();
					
				 	perfAdmin.registrarLogonFallido(usuario);
				 	
				 	System.out.println("El usuario o la contraseña no son válidos.");
					AddErrorMessage("El usuario o la contraseña no son válidos.");
					
					 //return Const.ANCLA_INDEX;
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
		 return Const.ANCLA_INDEX;
	} 
	
	public String salir() {
		this.puedeIngresar=false;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	
		//NavigationUtils.redirect("/admin/login");
		return Const.ANCLA_INDEX;
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

	public boolean getCambiaPass() {
		return cambiaPass;
	}

	public void setCambiaPass(boolean cambiaPass) {
		this.cambiaPass = cambiaPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getCambiarPassword() {
		return cambiarPassword;
	}

	public void setCambiarPassword(String cambiarPassword) {
		this.cambiarPassword = cambiarPassword;
	}

	public boolean isCambiarTelefono() {
		return cambiarTelefono;
	}

	public void setCambiarTelefono(boolean cambiarTelefono) {
		this.cambiarTelefono = cambiarTelefono;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		   HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        HttpSession session = request.getSession(false);

	        if (session == null || session.getAttribute("user") == null) {
	            response.sendRedirect(request.getContextPath() + "/login"); // No logged-in user found, so redirect to login page.
	        } else {
	            chain.doFilter(req, res); // Logged-in user found, so just continue request.
	        }
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public String getChkLogon() {
		return chkLogon;
	}

	public void setChkLogon(String chkLogon) {
		this.chkLogon = chkLogon;
	}


	
	
}
