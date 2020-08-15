/*
 * Created on Jul 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asecor.util.io;

import java.util.Properties;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.PrintStream;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import javax.naming.Context;
import javax.naming.InitialContext;
/**
 * @author mliz
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileUtil {

	/**
	 * 
	 */
	public FileUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retorna la parte del nombre del archivo en base a un String 
	 * correspondiente al path completo.
	 * 
	 * @param path	Path completo del archivo.
	 * 
	 * @return	Nombre del archivo
	 */
	public String getNameFromPath(String path) {
		return path.substring(path.lastIndexOf("\\") + 1);
	}
	
	/**
	 * Lee un archivo de propiedades
	 * 
	 * @param file	Archivo a leer
	 * 
	 * @return	Properties con las propiedades leidas.
	 */
	public Properties getPropertiesFile(String fileName) {
	    Properties properties = new Properties();
	 
	    try {
	    	InputStream in= getClass().getResourceAsStream(fileName);
	        properties.load(in);
	        in.close();
	        return properties;
	    } catch (IOException ex) {
	    	System.out.println("No se pudo leer el archivo de propiedades.");
	    	return properties;
	    }
	}

	
	/**
	 * Lee un archivo de propiedades. Saca ubicaci�n de server.xml utilizando JNDI
	 * 
	 * @return	Properties con las propiedades leidas.
	 */
	public Properties getPropertiesFile() {
	    Properties properties = new Properties();

	    try {
	    	Context ctx = new InitialContext(); 
	    	Context envCtx = (Context)ctx.lookup( "java:comp/env" ); 
	    	System.out.println((String)envCtx.lookup("archivoConf")); 
	    	InputStream in= new FileInputStream(new File((String)envCtx.lookup("archivoConf")));
	        properties.load(in);
	        in.close();
	        return properties;
	    } catch (IOException ex) {
	    	System.out.println("No se pudo leer el archivo de propiedades.");
	    	return properties;
	    } catch(Exception ex) {
			System.out.println("No se pudo leer el archivo de propiedades - Error al leer su ubicaci�n.");
			return properties;
		}
	}

	/**
	 * Escribe una fila en un archivo de texto.
	 * 
	 * @param archivo	Archivo destino
	 * @param fila		Fila a escribir
	 */
	public void escribirRegistro(String archivo, String fila) {
        //FileOutputStream out;
        try { 
    		BufferedWriter out = new BufferedWriter(new FileWriter(archivo, true));
            PrintStream p; 
            out.write(fila+"\n"); 
            out.close(); 
        } catch (Exception e) { 
            System.out.println ("Error escribiendo el archivo " + archivo); 
        } 
	}

	/**
	 * Crea un archivo vacío con el nombre recibido como parámetro.
	 * 
	 * @param 	nombArch	Nombre del archivo a crear
	 * @return	"SI" si pudo crear el archivo, "EX" si no pudo porque el archivo existe, o "NO" si no pudo.
	 */
	public String[] crearArchivoVacio(String nombArch) { 
		try {
	        File file = new File(nombArch);
	        boolean success= file.createNewFile();
	        String[] result= { (success ? "SI" : "EX"), nombArch};
	        return result;
		} catch(Exception ex) {
	        String[] result= { "NO", "" };
			return result;
		}
	}
	
}
