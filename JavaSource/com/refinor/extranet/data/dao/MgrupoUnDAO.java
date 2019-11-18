package com.refinor.extranet.data.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.base.BaseMgrupoUnDAO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;


public class MgrupoUnDAO extends BaseMgrupoUnDAO implements com.refinor.extranet.data.dao.iface.MgrupoUnDAO {

	public MgrupoUnDAO () {}
	
	public MgrupoUnDAO (Session session) {
		super(session);
	}

	public static String FIND_GRUPOS_POR_CLIENTE= "findGruposPorCliente";	
	
	public List getGruposUNPorCliente(String numeroDocumento) throws DataAccessErrorException {
		try {
			Integer codclienteInt=new Integer(-1);
			
			if(numeroDocumento!=null){
				MclientesDAO mclientesDAO = new MclientesDAO(session);
				List clientes = mclientesDAO.getPorDocumento(numeroDocumento);
				codclienteInt = ((Mclientes)clientes.get(0)).getCodigo();
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CLIENTE_INT, codclienteInt);
			Query query = this.getNamedQuery(FIND_GRUPOS_POR_CLIENTE,params, session);
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