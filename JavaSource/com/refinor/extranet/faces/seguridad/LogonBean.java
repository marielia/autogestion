/**
 * 
 */
package com.refinor.extranet.faces.seguridad;

import com.itsolver.util.io.FileUtil;
import com.itsolver.util.seguridad.PasswordService;
import com.refinor.extranet.data.Mccss;
import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.dao.MccssDAO;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MusuarioWebDAO;
import com.refinor.extranet.data.dao._RootDAO;
import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.seguridad.PerfilAdmin;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.UsuarioNoActivoException;
import com.refinor.extranet.util.exception.UsuarioNoExisteException;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author  mliz
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LogonBean extends AbstBackingBean {

	private String codigoCliente;
	private String clave;
	private Boolean mostrarPagina;
	private Session session;
	
	public void preRenderView() {
	      HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true );
	      //tune session params, eg. session.setMaxInactiveInterval(..);

	      //perform other pre-render stuff, like setting user context...
	}
	
	public LogonBean(){
		super();
		mostrarPagina= new Boolean(true);		
			
 		FileUtil fileUtil= new FileUtil();
   		Properties props= fileUtil.getPropertiesFile();
   		File hibernateCfgXml= new File(props.getProperty(Const.ARCHIVO_CONF_HIBERNATE));   		
 		_RootDAO.initialize(hibernateCfgXml);
 		
		session= (new MclientesDAO()).getSession();		
		
		
		FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
		HttpServletResponse response =
			(HttpServletResponse)context.getExternalContext().getResponse();
		response.setHeader("Cache-Control", "no-cache");
	}
	
	@Override
	public Boolean getPuedeIngresar() {
		// TODO Auto-generated method stub
		return new Boolean(true);
	}
	
	public String validarUsuario(){
		String goToPage = Const.ANCLA_LISTA_REPORTES_DISPONIBLES;			
		return goToPage;
	}
	
	public String chkLogon() {
		MusuarioWebDAO usrDAO= new MusuarioWebDAO(session);		
		
		Properties properties= (new FileUtil()).getPropertiesFile();
		HttpSession sesion= this.getSession();
		sesion.setMaxInactiveInterval(Integer.parseInt((String)properties.get(Const.SESSION_TIMEOUT)));
		PasswordService ps= PasswordService.getInstance();
		PerfilAdmin perfAdmin= new PerfilAdmin(session);
		MusuarioWeb usuario= null;
		try {
			
			if(codigoCliente.trim().equals("0"))
				throw new UsuarioNoExisteException();		
			
			usuario= usrDAO.getPorDocumentoYClave(codigoCliente, ps.encrypt(clave));
			
			usrDAO.refresh(usuario, session);
			// Chequea si el Usuario está Activo
			if(usuario.getEstado() != Const.USUARIO_ACTIVO)
				throw new UsuarioNoActivoException();
			
			perfAdmin.limpiarIntentosFallidos(usuario);
			
			sesion.setAttribute(Const.USUARIO, usuario);		
			
			if(usuario.getTipo()==1){				
				MclientesDAO clienteDAO = new MclientesDAO(session);
				List clientes = clienteDAO.getPorDocumento(usuario.getCuit());			
				sesion.setAttribute(Const.CLIENTE, (Mclientes)clientes.get(0));
			}
			
			if(usuario.getTipo()==2){				
				MccssDAO ccssDAO = new MccssDAO(session);
				List ccss = ccssDAO.getCCSS(usuario.getCodigoCcssEmpleado());			
				sesion.setAttribute(Const.CCSS, (Mccss)ccss.get(0));
			}
			
			
			if(usuario.getCambioClave() == 1)
				return Const.ANCLA_CAMBIO_PASSWORD;
			else{
				this.setSessionValue(Const.FECHA_ACTUAL, new Date());	
				return Const.ANCLA_LISTA_REPORTES_DISPONIBLES;	
			}
			
			
		} catch (UsuarioNoExisteException ex) {
				// Chequea si el error fue de contraseña
				try {
					usuario= usrDAO.getPorCodclienteAlfa(codigoCliente);
					
					// Chequea si el Usuario está Activo
					if(usuario.getEstado() != Const.USUARIO_ACTIVO)
						throw new UsuarioNoActivoException();
					
					perfAdmin.registrarLogonFallido(usuario);
					AddErrorMessage("Error en nombre de Usuario o Clave.");
					return Const.ANCLA_INDEX;
				} catch(UsuarioNoActivoException exp) {
					// Chequea si es un Usuario Interno
					exp.printStackTrace();
					AddErrorMessage(exp.getMessage());
					return Const.ANCLA_INDEX;
				} catch(UsuarioNoExisteException exp) {
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
		} finally {
			session.close();
		}
	}
	
	public String cancelar() {
		this.codigoCliente=null;		
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

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	
	@PostConstruct
	public void postConstruct() {
	FacesContext.getCurrentInstance().getExternalContext().getSession(true);

	}
	
	
}
