/*
 * Created on Oct 10, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.refinor.extranet.util.exception;

/**
 * @author mliz
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UsuarioNoActivoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNoActivoException() {
		super("Usuario Inactivo/Bloqueado.");	
	}
}
