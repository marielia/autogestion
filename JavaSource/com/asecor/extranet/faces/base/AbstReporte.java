/*
 * Created on ct 10, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.asecor.extranet.faces.base;

import com.asecor.extranet.faces.base.AbstBackingBean;

import java.util.ArrayList;
import java.util.List;
/**
 * @author  mliz  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class AbstReporte extends AbstBackingBean {

	private boolean mostrarResultado;
	private String fecDesde;
	private String fecHasta;
	private Boolean generaExcel;
	private List items = new ArrayList();
	private List items2 = new ArrayList();
	private List subItemsNivel1 = new ArrayList();
	private List subItemsNivel2 = new ArrayList();
	
	/**
	 * 
	 */
	public AbstReporte() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return  Returns the fecDesde.
	 * @uml.property  name="fecDesde"
	 */
	public java.lang.String getFecDesde() {
		return fecDesde;
	}
	/**
	 * @param fecDesde  The fecDesde to set.
	 * @uml.property  name="fecDesde"
	 */
	public void setFecDesde(java.lang.String fecDesde) {
		this.fecDesde = fecDesde;
	}

	/**
	 * @return  Returns the fecHasta.
	 * @uml.property  name="fecHasta"
	 */
	public java.lang.String getFecHasta() {
		return fecHasta;
	}
	/**
	 * @param fecHasta  The fecHasta to set.
	 * @uml.property  name="fecHasta"
	 */

	public void setFecHasta(java.lang.String fecHasta) {
		this.fecHasta = fecHasta;
	}
	
	/**
	 * @return  the mostrarResultado
	 * @uml.property  name="mostrarResultado"
	 */
	public boolean getMostrarResultado() {
		return mostrarResultado;
	}
	
	/**
	 * @param mostrarResultado  the mostrarResultado to set
	 * @uml.property  name="mostrarResultado"
	 */
	public void setMostrarResultado(boolean mostrarResultado) {
		this.mostrarResultado= mostrarResultado;
	}
	/**
	 * @return  Returns the generaExcel.
	 * @uml.property  name="generaExcel"
	 */
	public Boolean getGeneraExcel() {
		return generaExcel;
	}
	/**
	 * @param generaExcel  The generaExcel to set.
	 * @uml.property  name="generaExcel"
	 */
	public void setGeneraExcel(Boolean generaExcel) {
		this.generaExcel = generaExcel;
	}

	/**
	 * @return  the items
	 * @uml.property  name="items"
	 */
	public List getItems() {
		return items;
	}

	/**
	 * @param items  the items to set
	 * @uml.property  name="items"
	 */
	public void setItems(List items) {
		this.items = items;
	}

	public List getSubItemsNivel1() {
		return subItemsNivel1;
	}

	public void setSubItemsNivel1(List subItemsNivel1) {
		this.subItemsNivel1 = subItemsNivel1;
	}

	public List getSubItemsNivel2() {
		return subItemsNivel2;
	}

	public void setSubItemsNivel2(List subItemsNivel2) {
		this.subItemsNivel2 = subItemsNivel2;
	}

	public List getItems2() {
		return items2;
	}

	public void setItems2(List items2) {
		this.items2 = items2;
	}
}
