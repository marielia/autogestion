package com.refinor.extranet.data;

import com.refinor.extranet.data.base.BaseMibimpuestoId;

public class MibimpuestoId extends BaseMibimpuestoId {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public MibimpuestoId () {}
	
	public MibimpuestoId (
		int pciaCcss,
		int inscripIb,
		int inscripIva,
		int actividadCli,
		int tipoProd) {

		super (
			pciaCcss,
			inscripIb,
			inscripIva,
			actividadCli,
			tipoProd);
	}
/*[CONSTRUCTOR MARKER END]*/


}