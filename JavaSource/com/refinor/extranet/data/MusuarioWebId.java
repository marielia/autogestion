package com.refinor.extranet.data;

import com.refinor.extranet.data.base.BaseMusuarioWebId;

public class MusuarioWebId extends BaseMusuarioWebId {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public MusuarioWebId () {}
	
	public MusuarioWebId (
		int id,
		java.lang.String cuit,
		java.lang.String email,
		java.lang.String clave,
		char estado,
		int cambioClave,
		int intentosFallidos,
		java.util.Date ultimoIntentoFallido,
		java.util.Date fechaAlta,
		int tipo,
		java.lang.Integer creadorId,
		java.lang.String codClienteAlfa) {

		super (
			id,
			cuit,
			email,
			clave,
			estado,
			cambioClave,
			intentosFallidos,
			ultimoIntentoFallido,
			fechaAlta,
			tipo,
			creadorId,
			codClienteAlfa);
	}
/*[CONSTRUCTOR MARKER END]*/


}