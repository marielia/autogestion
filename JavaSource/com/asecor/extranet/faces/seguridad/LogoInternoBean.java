/**
 * 
 */
package com.asecor.extranet.faces.seguridad;

 
  
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.asecor.extranet.faces.base.AbstBackingBean;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.Messages;
  

 
//@ManagedBean(name = "logoFirstBean")
//@RequestScoped
public class LogoInternoBean extends AbstBackingBean { 
	
	 
	 
	private Session session;
	private Boolean puedeIngresar;
	private Boolean ingresar; 
	
	private String comesFrom;
	
	public void preRenderView() {
	      HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true ); 
	}
	
	public LogoInternoBean(){
		super(); 
		
		try
		{ 	
			Messages mes= new Messages();
			ResourceBundle ResourceBundle = mes.getMessage();
		    String sistema = ResourceBundle.getString("contexto_sistema");
			
			this.puedeIngresar = false;
			String url=  sistema + "/pages/web/app/salir.jsf";
			
			System.out.println(" entrando a  Logo Interno  "   ); 
 			  
			if(getRequest().getParameter("page")!=null  && getRequest().getParameter("page").equals("REG"))
			{ 	  
				if(getRequest().getParameter(Const.COMES_FROM)!=null  )
				{  	
				    this.comesFrom = getRequest().getParameter(Const.COMES_FROM); 
				    getSession().setAttribute(Const.COMES_FROM, this.comesFrom ); 
				    
				    this.puedeIngresar = true;
				    url = "index.jsf"; 
	   		     } 
			}
			
			
			if(getRequest().getParameter("page")!=null  && getRequest().getParameter("page").equals("CLEAN"))
			{
				if(getRequest().getParameter(Const.COMES_FROM)!=null  )
				{  	
				    this.comesFrom = getRequest().getParameter(Const.COMES_FROM); 
				    getSession().setAttribute(Const.COMES_FROM, this.comesFrom );
				    System.out.println("comesFrom  " +  this.comesFrom  ); 
				    
				    getSession().setAttribute("userId", getRequest().getParameter("userId"));
				    System.out.println("userId " +  getRequest().getParameter("userId")  );
				    
				    this.puedeIngresar = true; 
				    url=  sistema +"/pages/web/app/asecorol_clean_pass.jsf";
				   
	   		     } 
			}
			
			
			  ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			  ec.redirect(url); 
		
		}catch(Exception ex)
		{
			System.out.println("Logo Interno Bean Error");
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

	public String getComesFrom() {
		return comesFrom;
	}

	public void setComesFrom(String comesFrom) {
		this.comesFrom = comesFrom;
	}
	
	
	
}
