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
public class PersonaNoExisteException extends Exception {

	public PersonaNoExisteException() {
		super("No existe un Cliente con el CUIT y Cuenta de Email ingresado. Puede comunicarse con Refinor para verificar sus datos.");
	}
	
}
