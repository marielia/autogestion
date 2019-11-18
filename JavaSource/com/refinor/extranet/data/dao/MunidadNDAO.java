package com.refinor.extranet.data.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.base.BaseMunidadNDAO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;


public class MunidadNDAO extends BaseMunidadNDAO implements com.refinor.extranet.data.dao.iface.MunidadNDAO {

	public MunidadNDAO () {}
	
	public MunidadNDAO (Session session) {
		super(session);
	}	
	
	public static String FIND_UNIDADES_NEGOCIO_POR_GRUPO= "findUnidadesNegocioPorGrupo";	
	
	public List getUnidadesNegocioPorGrupo(Integer grupo) throws DataAccessErrorException {
		try {			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_GRUPO_UNIDAD_NEGOCIO, grupo);
			Query query = this.getNamedQuery(FIND_UNIDADES_NEGOCIO_POR_GRUPO,params, session);
			List lstDatos= query.list();
			if(lstDatos.size() == 0) {
				return null;
			} else {
				return lstDatos;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
}