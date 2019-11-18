package com.refinor.extranet.data;

import com.refinor.extranet.data.base.BaseVsaldoPorComprobanteClienteId;

public class VsaldoPorComprobanteClienteId extends BaseVsaldoPorComprobanteClienteId {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public VsaldoPorComprobanteClienteId () {}
	
	public VsaldoPorComprobanteClienteId (
		java.lang.Integer clteProv,
		java.lang.Integer nroOper,
		java.math.BigDecimal imp) {

		super (
			clteProv,
			nroOper,
			imp);
	}
/*[CONSTRUCTOR MARKER END]*/


}