package com.refinor.extranet.data;

import com.refinor.extranet.data.base.BaseMctasCtes;



public class MctasCtes extends BaseMctasCtes {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public MctasCtes () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public MctasCtes (com.refinor.extranet.data.MctasCtesId id) {
		super(id);
	}

	public static String FIND_TODAS_LAS_FACTURAS_AGRUPADAS= "findTodasLasFacturasAgrupadas";
	
/*[CONSTRUCTOR MARKER END]*/


}