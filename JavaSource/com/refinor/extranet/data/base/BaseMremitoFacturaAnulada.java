package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MRemitoFacturaAnulada table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MRemitoFacturaAnulada"
 */

public abstract class BaseMremitoFacturaAnulada  implements Serializable {

	public static String REF = "MremitoFacturaAnulada";
	public static String PROP_NRO_REMITO = "nroRemito";
	public static String PROP_SUC_RTO = "sucRto";
	public static String PROP_ID = "id";
	public static String PROP_ORDEN_FACTURA = "ordenFactura";


	// constructors
	public BaseMremitoFacturaAnulada () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMremitoFacturaAnulada (com.refinor.extranet.data.MremitoFacturaAnuladaId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.MremitoFacturaAnuladaId id;

	// fields
	private java.lang.Long ordenFactura;
	private java.lang.Long nroRemito;
	private java.lang.Integer sucRto;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.MremitoFacturaAnuladaId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.MremitoFacturaAnuladaId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: OrdenFactura
	 */
	public java.lang.Long getOrdenFactura () {
		return ordenFactura;
	}

	/**
	 * Set the value related to the column: OrdenFactura
	 * @param ordenFactura the OrdenFactura value
	 */
	public void setOrdenFactura (java.lang.Long ordenFactura) {
		this.ordenFactura = ordenFactura;
	}



	/**
	 * Return the value associated with the column: NroRemito
	 */
	public java.lang.Long getNroRemito () {
		return nroRemito;
	}

	/**
	 * Set the value related to the column: NroRemito
	 * @param nroRemito the NroRemito value
	 */
	public void setNroRemito (java.lang.Long nroRemito) {
		this.nroRemito = nroRemito;
	}



	/**
	 * Return the value associated with the column: Suc_rto
	 */
	public java.lang.Integer getSucRto () {
		return sucRto;
	}

	/**
	 * Set the value related to the column: Suc_rto
	 * @param sucRto the Suc_rto value
	 */
	public void setSucRto (java.lang.Integer sucRto) {
		this.sucRto = sucRto;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MremitoFacturaAnulada)) return false;
		else {
			com.refinor.extranet.data.MremitoFacturaAnulada mremitoFacturaAnulada = (com.refinor.extranet.data.MremitoFacturaAnulada) obj;
			if (null == this.getId() || null == mremitoFacturaAnulada.getId()) return false;
			else return (this.getId().equals(mremitoFacturaAnulada.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}