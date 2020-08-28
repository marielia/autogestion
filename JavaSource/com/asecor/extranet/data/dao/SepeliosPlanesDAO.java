package com.asecor.extranet.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.asecor.extranet.data.Adherentes;
import com.asecor.extranet.data.Cobranzas;
import com.asecor.extranet.data.Polizas;
import com.asecor.extranet.data.SepeliosPlanes;
import com.asecor.extranet.data.base.BasePolizasDAO;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.exception.DataAccessErrorException;
import com.asecor.extranet.util.exception.UsuarioNoExisteException;

public class SepeliosPlanesDAO extends _RootDAO  {

	public static String FIND_BY_POLIZAS = "findPlanByPoliza";

	public SepeliosPlanesDAO() {
	}

	public SepeliosPlanesDAO(Session session) {
		super(session);
	}

	public String findPlanByPoliza(Polizas pol) throws DataAccessErrorException {
		
		try {
		
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_SOLICITUD, pol.getCodSolicitud());
						List result = this.getNamedQuery(FIND_BY_POLIZAS, params, session).list();
			if (result.size() > 0) {
				
				return "FAMILIAR";
			} else if (result.size() == 0)
				return "INDIVIDUAL";
			
			
		} catch (Exception ex) {
			throw new DataAccessErrorException();
		}
		return "INDIVIDUAL";
		
	}

	@Override
	protected Class<SepeliosPlanes> getReferenceClass() {
		return com.asecor.extranet.data.SepeliosPlanes.class;
		
	}

}
