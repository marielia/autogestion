package com.asecor.extranet.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.asecor.extranet.data.Adherentes;
import com.asecor.extranet.data.Cobranzas;
import com.asecor.extranet.data.Polizas;
import com.asecor.extranet.data.base.BasePolizasDAO;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.exception.DataAccessErrorException;
import com.asecor.extranet.util.exception.UsuarioNoExisteException;

public class AdherentesDAO extends BasePolizasDAO {

	public static String FIND_BY_POLIZAS = "findAdherentesByPolizas";

	public AdherentesDAO() {
	}

	public AdherentesDAO(Session session) {
		super(session);
	}

	public List<Adherentes> findAdherentesByPoliza(Polizas pol) throws DataAccessErrorException {
		List listado = new ArrayList<Adherentes>();
		try {
		
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_SOLICITUD, pol.getCodSolicitud());
						List result = this.getNamedQuery(FIND_BY_POLIZAS, params, session).list();
			if (result.size() > 0) {
				result.listIterator().forEachRemaining(listado::add);
				return listado;
			} else if (result.size() == 0)
				return null;
			
			return listado;
		} catch (Exception ex) {
			throw new DataAccessErrorException();
		}
	}

}
