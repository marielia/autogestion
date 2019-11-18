package com.refinor.extranet.data;

import com.refinor.extranet.data.base.BaseMovimientoStock;



public class MovimientoStock extends BaseMovimientoStock {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public MovimientoStock () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public MovimientoStock (com.refinor.extranet.data.MovimientoStockId id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/


}