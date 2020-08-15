/*
 * Created on Sep 18, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asecor.extranet.util.exception;

/**
 * @author mliz
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UsuarioYaRegistradoException extends Exception {

	public UsuarioYaRegistradoException() {
		super("El Usuario ya fue registrado con anterioridad.");
	}
	
	public UsuarioYaRegistradoException(String mensaje) {
		super(mensaje);
	}
	
}


