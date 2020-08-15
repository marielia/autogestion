/**
 * 
 */
package com.asecor.extranet.faces.seguridad;

 
  
import java.io.File;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.asecor.extranet.data.dao.UsuarioWebDAO;
import com.asecor.extranet.data.dao._RootDAO;
import com.asecor.extranet.faces.base.AbstBackingBean;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.DataUtil;
import com.asecor.util.io.FileUtil;
  

 
//@ManagedBean(name = "logonSelBean")
//@RequestScoped
public class LogonSelBean extends AbstBackingBean { 
	
	private String  token;
	private Session session;
	private Boolean puedeIngresar;
	private String  url;
	private Boolean ingresar; 
	
	public void preRenderView() {
	      HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true ); 
	}
	
	public LogonSelBean(){
		super(); 
		
		try
		{  
			
			if(getRequest().getParameter("tk")!=null  )
			{
				FileUtil fileUtil= new FileUtil();
		   		Properties props= fileUtil.getPropertiesFile();
		   		File hibernateCfgXml= new File(props.getProperty(Const.ARCHIVO_CONF_HIBERNATE));   		
		 		_RootDAO.initialize(hibernateCfgXml);
		 		
				session=  new UsuarioWebDAO().getSession(); 
				
				DataUtil dataUtil= new DataUtil();
				
			    this.token = getRequest().getParameter("tk"); 
			    
			    //buscar agencia y usuario
			   
			    // String goTo = dataUtil.getParameter(Const.GO_TO_PORTAL, session); 
			     //System.out.println("goTo "+ goTo);
			     
			  

			  //   if(goTo.equals(Const.INDEX))
			  //   {
			  //  	     this.url = dataUtil.getParameter(Const.URL_INDEX_PORTAL, session); 
				//	     System.out.println("this.url "+ this.url);
			 //    }
			   
			    // getSession().setAttribute("comesFrom",olUserAgency.getAgencyId());
			    // getSession().setAttribute("userId",olUserAgency.getUserId());
			     //System.out.println("comesFrom "+ olUserAgency.getAgencyId());
			     
			     this.puedeIngresar=true;
			     
			     ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			     ec.redirect(this.url); 
			     
			     
			   
			} 
		
		}catch(Exception ex)
		{
			System.out.println("LogoSelBean Error");
			this.puedeIngresar = false;
			ex.printStackTrace();
			
		}
	}
	 
	 
	
	@PostConstruct
	public void postConstruct() {
	FacesContext.getCurrentInstance().getExternalContext().getSession(true);

	} 
	@Override
	public Boolean getPuedeIngresar() {
		// TODO Auto-generated method stub
		return this.puedeIngresar;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getIngresar() {
		return true;
	}

	public void setIngresar(Boolean ingresar) {
		this.ingresar = ingresar;
	}
	
	
	
}
