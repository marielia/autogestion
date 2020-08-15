/**
 * 
 */
package com.asecor.extranet.util;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author snieto
 *
 */
public class Messages {
	private ResourceBundle message; 
	/**
	 * @author snieto
	 * setMessageProperty: cargar el archivo de mesagge	
	 * @throws IOException 
	 * 	
	 */
	public Messages() throws IOException{	
	String lenguaje = new String("es");
	String pais = new String("AR");
	Locale currentLocale = new Locale(lenguaje, pais);
	
	this.message = ResourceBundle.getBundle("com.asecor.extranet.bundle.Messages",currentLocale);	
	
	}
	/**
	 * @return the message
	 */
	public ResourceBundle getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(ResourceBundle message) {
		this.message = message;
	}

}
