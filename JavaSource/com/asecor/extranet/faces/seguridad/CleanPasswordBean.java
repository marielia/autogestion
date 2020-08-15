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



/**
 * @author Nieto Silvana
 *
 */
public class CleanPasswordBean extends AbstBackingBean{

	private Integer id;
	private String pin; 
	private String pass;
	private String repetpass;
	private Session session;
	private String userId;	
	private java.lang.Boolean mostrarResultado;
	private String comesFrom;
	 
		
	public CleanPasswordBean() throws Exception {
		super();
		 		
		FileUtil fileUtil= new FileUtil();
   		Properties props= fileUtil.getPropertiesFile();
   		File hibernateCfgXml= new File(props.getProperty(Const.ARCHIVO_CONF_HIBERNATE));   		
 		_RootDAO.initialize(hibernateCfgXml); 
		 
		inicializar();
		
		
		//if(getRequest().getParameter("userId") != null)
		if( getSession().getAttribute("userId")  != null)
		{
			userId =  getSession().getAttribute("userId").toString() ;
			System.out.println(" userId  " +  userId ); 
		}
		
		//if(getRequest().getParameter("comesFrom") != null)
		if( getSession().getAttribute("comesFrom")  != null)
		{
			comesFrom = getSession().getAttribute("comesFrom").toString() ;
			System.out.println("comesFrom  " +  comesFrom ); 
		}
				
		FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
		HttpServletResponse response =
			(HttpServletResponse)context.getExternalContext().getResponse();
		
		response.setHeader("Cache-Control", "no-cache");
	}

	private void inicializar() {
		id = new Integer(0);		
		pin = ""; 
		pass = ""; 
		repetpass = "";
		mostrarResultado= new Boolean(false);
		comesFrom="";
	}
	
	 
	public void aceptar(ActionEvent event) {
		session= (new UsuarioWebDAO()).getSession();
		PerfilAdmin perfAdmin = new PerfilAdmin(session);
		try {
			TitularWeb usuario =  new TitularWeb(); 
			UsuarioWebDAO olUserDAO= new UsuarioWebDAO(session);
			
			try {
				System.out.println("pin " +  pin );
				System.out.println("userId " +  userId );
			    usuario = olUserDAO.get(Integer.parseInt(this.userId));
			    
			    if(usuario==null)
			    {
			    	throw new UsuarioNoExisteException("El Usuario no existe");
			    }
			    
				System.out.println("pin " +  pin );
				System.out.println("usuario.getPin() " +  usuario.getPin() );
				
				//aqui
				
			    if(!usuario.getPin().trim().equals( this.pin.trim()))
			    {
			    	throw new UsuarioNoExisteException("La clave de ingreso no es válida.");
			    }
			}
			catch(UsuarioNoExisteException ex)
			{
				//el pin y el mail no son validos
				ex.printStackTrace();
				throw new UsuarioNoExisteException(ex.getMessage());
			} 
			 
			
			PasswordService ps= PasswordService.getInstance();
			
			DataUtil dataUtil = new DataUtil();
			DateTO dateTO = dataUtil.getFechaActual(); 
			usuario.setPassword(ps.encrypt(this.pass)); 
			usuario.setUpdateDate( new Date( dateTO.getAnio(), dateTO.getMes(),dateTO.getDia(),dateTO.getHora(),dateTO.getMinuto(),dateTO.getSegundo()));
			usuario.setUpdateUser(Const.SISTEMA);  
			usuario.setFailedLogons(0); 
			usuario.setPasswordChange(false); 
			usuario.setLastFailedLogon(new Date( dateTO.getAnio(), dateTO.getMes(),dateTO.getDia(),dateTO.getHora(),dateTO.getMinuto(),dateTO.getSegundo()));
			
			
			if(!session.isOpen())
			{
				session = (new UsuarioWebDAO()).getSession();
				olUserDAO= new UsuarioWebDAO(session);
			}
			
			usuario= olUserDAO.confirmUser(usuario); 
			 
			mostrarResultado= new Boolean(true);  
		 
			
		} catch (UsuarioNoExisteException ex) {
			AddErrorMessage(ex.getMessage());
		}   catch (DataAccessErrorException ex) {
			AddErrorMessage(ex.getMessage()); 
		} catch(Exception ex) {
			ex.printStackTrace();
			AddErrorMessage("Se produjo un error al blanquear la constraseña.");
		}
		
	}
  
	
	 
	public String cancelar() {
		return Const.SALIR;
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

 

	public String getRepetpass() {
		return repetpass;
	}

	public void setRepetpass(String repetpass) {
		this.repetpass = repetpass;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getComesFrom() {
		return comesFrom;
	}

	public void setComesFrom(String comesFrom) {
		this.comesFrom = comesFrom;
	}

	 
	 
	 
}
