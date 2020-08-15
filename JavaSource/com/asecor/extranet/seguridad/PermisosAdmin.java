package com.asecor.extranet.seguridad;
/*
 * Created on Jun 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 
package com.refinor.extranet.seguridad;

import java.util.HashSet;
import java.util.Iterator;

import com.refinor.extranet.data.Funcion;


*//**
 * @author mdiz
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 *//*
public class PermisosAdmin {
	
	private HashSet<Funcion> funciones;
	
	
	public PermisosAdmin() {
	}
	
	public boolean tieneFuncion(Integer funcionId) {
		Iterator i = funciones.iterator();
		
		while (i.hasNext()) {
			if (((Funcion)i.next()).getId()==funcionId) {
				return true;
			}
		}
		
		return false;
	}

	*//**
	 * @return the funciones
	 *//*
	public HashSet<Funcion> getFunciones() {
		return funciones;
	}

	*//**
	 * @param funciones the funciones to set
	 *//*
	public void setFunciones(HashSet<Funcion> funciones) {
		this.funciones = funciones;
	}
	
}
*/