package com.asecor.extranet.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.asecor.extranet.data.Cobranzas;
import com.asecor.extranet.data.Polizas;
import com.asecor.extranet.data.base.BasePolizasDAO;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.exception.DataAccessErrorException;
import com.asecor.extranet.util.exception.UsuarioNoExisteException;

public class CobranzasDAO extends BasePolizasDAO {

	public static String FIND_BY_POLIZAS = "findCobranzasByPolizas";

	public CobranzasDAO() {
	}

	public CobranzasDAO(Session session) {
		super(session);
	}

	public List<Cobranzas> findCobranzasByPoliza(Polizas pol) throws DataAccessErrorException {
		try {
			List listado = new ArrayList<Polizas>();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_POLIZAS, pol);
			//params.put(Const.PARAM_ESTADO_CUOTA, "COB");
						List result = this.getNamedQuery(FIND_BY_POLIZAS, params, session).list();
			if (result.size() > 0) {
				result.listIterator().forEachRemaining(listado::add);
				if (listado.size() > 10) {
					return listado.subList(0, 10);
				}
				return listado;
			} else if (result.size() == 0)
				throw new UsuarioNoExisteException();
			else
				throw new DataAccessErrorException();

		} catch (Exception ex) {
			throw new DataAccessErrorException();
		}
	}

}
