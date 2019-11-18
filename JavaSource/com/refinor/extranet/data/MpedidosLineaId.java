package com.refinor.extranet.data;

import com.refinor.extranet.data.base.BaseMpedidosLineaId;

public class MpedidosLineaId extends BaseMpedidosLineaId {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public MpedidosLineaId () {}
	
	public MpedidosLineaId (
		int codPedido,
		int codLinea,
		int sucursal) {

		super (
			codPedido,
			codLinea,
			sucursal);
	}
/*[CONSTRUCTOR MARKER END]*/


}