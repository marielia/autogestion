package com.asecor.extranet.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.asecor.extranet.data.Polizas;
import com.asecor.extranet.data.Titulares;
import com.asecor.extranet.data.base.BasePolizasDAO;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.exception.DataAccessErrorException;
import com.asecor.extranet.util.exception.UsuarioNoExisteException;

public class PolizasDAO extends BasePolizasDAO implements com.asecor.extranet.data.dao.iface.PolizasDAO {

	
	public static String FIND_BY_ID = "findById";  
	public static String FIND_BY_TITULAR= "findPolizasByTitular";

	public PolizasDAO () {}
	
	public PolizasDAO (Session session) {
		super(session);
	}
	public List<Polizas> findPolizasByTitular(Titulares titular) throws UsuarioNoExisteException, DataAccessErrorException {
		try {	
			 List listado=new ArrayList<Polizas>();
			Map<String, Object> params= new HashMap<String, Object>(); 
			params.put(Const.PARAM_TITULAR, titular);
			//params.put(Const.PARAM_BAJA, 0);
			params.put(Const.PARAM_RAMA,"SP");
 
			List result= this.getNamedQuery(FIND_BY_TITULAR, params, session).list();
			if(result.size() > 0) {
				result.listIterator().forEachRemaining(listado::add);
				return listado;
			}
			else if(result.size() == 0)
				throw new UsuarioNoExisteException();
			else 
				throw new DataAccessErrorException();
		} catch(UsuarioNoExisteException ex) {
			throw ex;
		} catch(Exception ex) {
			throw new DataAccessErrorException();
		}
	}
	
	 


}