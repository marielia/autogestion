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
import com.asecor.util.io.FileUtil;
  

 
//@ManagedBean(name = "logoFirstBean")
//@RequestScoped
public class LogoFirstBean extends AbstBackingBean { 
	
	 
	 
	private Session session;
	private Boolean puedeIngresar;
	private Boolean ingresar; 
	
	public void preRenderView() {
	      HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true ); 
	}
	
	public LogoFirstBean(){
		super(); 
		
		try
		{  
			
			//tomar la url la pagina de donde vengo
			 String url = getRequest().getHeader("Referer");
			 System.out.println("Vengo de " +  url); 
			
			 
			FileUtil fileUtil= new FileUtil();
			Properties props= fileUtil.getPropertiesFile();
			File hibernateCfgXml= new File(props.getProperty(Const.ARCHIVO_CONF_HIBERNATE));   		
			_RootDAO.initialize(hibernateCfgXml);
			
			session=  new UsuarioWebDAO().getSession(); 
				
			//OlAgencyUrlDAO olAgencyUrlDAO= new OlAgencyUrlDAO(session);
		//	OlAgencyUrl olAgencyUrl = olAgencyUrlDAO.getByUrl("http://200.80.28.114:9595/asecorclienteDEV/indexDemo.html");//url);//
			//aqui volver
		  //  getSession().setAttribute(Const.COMES_FROM,  olAgencyUrl.getAgencyId());
			//System.out.println(Const.COMES_FROM+" " +  olAgencyUrl.getAgencyId() ); 
			
			 this.puedeIngresar=true;
			 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		     ec.redirect("index.jsf"); 
 
		
		}catch(Exception ex)
		{
			System.out.println("LogoFirstBean Error");
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

	public Boolean getIngresar() {
		return true;
	}

	public void setIngresar(Boolean ingresar) {
		this.ingresar = ingresar;
	}
	
	
	
}
