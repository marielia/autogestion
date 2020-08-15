/*
 * Created on Aug 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asecor.util.seguridad;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Autenticador extends javax.mail.Authenticator {

	
	
	
	
	private String usuario;
	private String password;
	
	public Autenticador() {
		super();
	}

	
	public Autenticador(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;
	}

	
	
	
	public javax.mail.PasswordAuthentication getPasswordAuthentication() {
		return new javax.mail.PasswordAuthentication(usuario, password);
	}
	
}
