/*
 * Created on Jul 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asecor.extranet.util.exception;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExisteEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8254896395101022794L;

	public ExisteEmailException() {
		super("El email  ya se encuentra registrado.");
	}
	
}
