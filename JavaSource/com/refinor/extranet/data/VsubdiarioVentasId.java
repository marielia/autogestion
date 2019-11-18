package com.refinor.extranet.data;

import com.refinor.extranet.data.base.BaseVsubdiarioVentasId;

public class VsubdiarioVentasId extends BaseVsubdiarioVentasId {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public VsubdiarioVentasId () {}
	
	public VsubdiarioVentasId (
		java.util.Date fecha,
		java.lang.String tipoComprobante,
		java.lang.String tipo,
		java.lang.Integer nroComprobante,
		java.lang.String cuit,
		java.lang.String descCliente,
		java.math.BigDecimal netoGravado,
		int exento,
		java.math.BigDecimal iva,
		java.math.BigDecimal iva2,
		java.math.BigDecimal otrasPercepciones,
		java.math.BigDecimal totalComprobante,
		java.lang.Integer hiperCliente,
		java.lang.Integer catIva,
		java.lang.String descripcion,
		java.math.BigDecimal impuestoInterno,
		java.math.BigDecimal itc,
		java.math.BigDecimal tasaFondo,
		short nroSucursal,
		java.math.BigDecimal iibb,
		java.math.BigDecimal piva,
		java.math.BigDecimal nogravado) {

		super (
			fecha,
			tipoComprobante,
			tipo,
			nroComprobante,
			cuit,
			descCliente,
			netoGravado,
			exento,
			iva,
			iva2,
			otrasPercepciones,
			totalComprobante,
			hiperCliente,
			catIva,
			descripcion,
			impuestoInterno,
			itc,
			tasaFondo,
			nroSucursal,
			iibb,
			piva,
			nogravado);
	}
/*[CONSTRUCTOR MARKER END]*/


}