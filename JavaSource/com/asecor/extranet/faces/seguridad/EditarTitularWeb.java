package com.asecor.extranet.faces.seguridad;

import java.io.File;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.asecor.extranet.data.TitularWeb;
import com.asecor.extranet.data.dao.UsuarioWebDAO;
import com.asecor.extranet.data.dao._RootDAO;
import com.asecor.extranet.faces.base.AbstBackingBean;
import com.asecor.extranet.util.Const;
import com.asecor.util.exception.ProtocoloDeSeguridadException;
import com.asecor.util.io.FileUtil;
import com.asecor.util.seguridad.PasswordService;

public class EditarTitularWeb extends AbstBackingBean {
	private Session session;
	private UsuarioWebDAO usrDAO;
private String pass;
private Boolean cambiaPass;
	private TitularWeb usuario;

	public EditarTitularWeb() {
		super();
		try
		{
	
			
	 		FileUtil fileUtil= new FileUtil();
	   		Properties props= fileUtil.getPropertiesFile();
	   		File hibernateCfgXml= new File(props.getProperty(Const.ARCHIVO_CONF_HIBERNATE)); 
	   				_RootDAO.initialize(hibernateCfgXml);
	 		
			session=  new UsuarioWebDAO().getSession(); 
		
		//	inicializarValores(); 
			    
		//	 this.comesFrom =  "1819"; 
		//	 puedeIngresar = true; 
			 
			FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
			response.setHeader("Cache-Control", "no-cache"); 
		
		}catch(Exception ex)
		{
			ex.printStackTrace();			
		}
	}

	@Override
	public Boolean getPuedeIngresar() {
		// TODO Auto-generated method stub
		return null;
	}

	public void preRenderView() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		// tune session params, eg. session.setMaxInactiveInterval(..);

		// perform other pre-render stuff, like setting user context...
	}

	public String cambiarPasswordrr() {
		PasswordService ps = PasswordService.getInstance();
		System.out.println(this.usuario.getId());
		// this.usuario.setPasswordChange(true);
		try {
			this.usuario.setPassword(ps.encrypt(this.pass));
		} catch (ProtocoloDeSeguridadException e) {
			// TODO Auto-generated catch block
			AddErrorMessage("Se produjo un error al cambiar la contraseña.");
		}
		usrDAO.saveOrUpdate(this.usuario);
		this.cambiaPass = true;
		return "polizasUsuarios";
	}

	public TitularWeb getUsuario() {
		return usuario;
	}

	public void setUsuario(TitularWeb usuario) {
		this.usuario = usuario;
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
