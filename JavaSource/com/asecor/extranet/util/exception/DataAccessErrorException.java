/*
 * Created on Sep 14, 2006
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
public class DataAccessErrorException extends Exception {

	public DataAccessErrorException() {
		super("Hubo problemas al acceder a los datos. Consulte al Administrador del Sistema");
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataAccessErrorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public DataAccessErrorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DataAccessErrorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
