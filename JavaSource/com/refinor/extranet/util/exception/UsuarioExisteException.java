/*
 * Created on Jul 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.refinor.extranet.util.exception;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UsuarioExisteException extends Exception {

	public UsuarioExisteException() {
		super("El código de usuario está siendo utilizado por otro usuario.");
	}
	
}
