/*
 * Created on Aug 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asecor.extranet.faces.base; 

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 



/**
 * @author mliz
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class AbstBackingBean {
	/**
	 * 
	 */
	public AbstBackingBean() {
		super();
		//this.setSessionValue(Const.FECHA_ACTUAL, new Date());		
	}
	
	/**
	 * Agrega un mensaje a visualizar en la capa de presentaci�n
	 * 
	 * @param message	Mensaje a agregar
	 */
	public void AddErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));		
	}
	
	/**
	 * Devuelve un objeto almacenado en la sesión en curso
	 * 
	 * @param nombreObj	Objeto a recuperar
	 * 
	 * @return	Objeto solicitado
	 */
	public Object getSessionValue(String nombreObj) {
		ExternalContext extCtx = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession sesion = (HttpSession)extCtx.getSession(false);
		return sesion.getAttribute(nombreObj);
	}
	
	
	
	/**
	 * Setea un objeto en la sesión en curso
	 * 
	 * @param nombreObj	Objeto a recuperar
	 * 
	 * @return	Objeto a setear
	 */
	public void setSessionValue(String nombreObj, Object objeto) {
		ExternalContext extCtx = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession sesion = (HttpSession)extCtx.getSession(false);
		sesion.setAttribute(nombreObj, objeto);
	}
	
	/**
	 * Pone en null un objeto de sesión
	 * 
	 * @param nombreObj
	 */
	public void clearSessionValue(String nombreObj) {
		ExternalContext extCtx = FacesContext.getCurrentInstance().getExternalContext(); 
		HttpSession sesion = (HttpSession)extCtx.getSession(false);
		sesion.setAttribute(nombreObj, null);
	}
	
	/**
	 * Recupera la sesión actual
	 * 
	 * @return	Sesión
	 */
	public HttpSession getSession() {
		ExternalContext extCtx = FacesContext.getCurrentInstance().getExternalContext(); 
		return (HttpSession)extCtx.getSession(false);
	}
	
	/**
	 * Recupera el request actual
	 * 
	 * @return	Request
	 */
	public HttpServletRequest getRequest() {
		ExternalContext extCtx = FacesContext.getCurrentInstance().getExternalContext(); 
		return (HttpServletRequest)extCtx.getRequest();
	}
	
	/**
	 * Recupera el request actual
	 * 
	 * @return	Request
	 */
	public HttpServletResponse getResponse() {
		ExternalContext extCtx = FacesContext.getCurrentInstance().getExternalContext(); 
		return (HttpServletResponse)extCtx.getResponse();
	}
	
	/**
	 * Evalua si tiene permiso para ingresar en la Función
	 * 
	 * @return Boolean
	 */
	public abstract Boolean getPuedeIngresar();
	

}
