package com.refinor.extranet.data;

import com.refinor.extranet.data.base.BaseVasientoContableId;

public class VasientoContableId extends BaseVasientoContableId {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public VasientoContableId () {}
	
	public VasientoContableId (
		int ccssId,
		java.lang.String descCcss,
		java.lang.Long nroEjercicio,
		long nroAsiento,
		java.lang.String cuenta,
		java.lang.String debHab,
		java.math.BigDecimal valorDebHab,
		java.lang.String leyenda,
		java.lang.String fecha,
		java.lang.String descCuenta,
		int nroSuc,
		java.lang.Integer nroFac,
		java.lang.String compr,
		java.lang.String letra,
		java.lang.String codigoCli,
		java.lang.String cliente,
		int comprobanteAsociado,
		java.lang.Long nroRendicion,
		java.lang.String hora) {

		super (
			ccssId,
			descCcss,
			nroEjercicio,
			nroAsiento,
			cuenta,
			debHab,
			valorDebHab,
			leyenda,
			fecha,
			descCuenta,
			nroSuc,
			nroFac,
			compr,
			letra,
			codigoCli,
			cliente,
			comprobanteAsociado,
			nroRendicion,
			hora);
	}
/*[CONSTRUCTOR MARKER END]*/


}