package com.refinor.extranet.data.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMsecuenciasDAO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;


public class MsecuenciasDAO extends BaseMsecuenciasDAO implements com.refinor.extranet.data.dao.iface.MsecuenciasDAO {

	public MsecuenciasDAO () {}
	
	public MsecuenciasDAO (Session session) {
		super(session);
	}

	public static String FIND_VALOR_POR_CODIGO= "findValorPorCodigo";
	
	public int getValor(String nombreTabla) throws  DataAccessErrorException {
		try {
			int codigo=1;
			
			Map<String, Object> params = new HashMap<String, Object>();			
			params.put(Const.PARAM_NOMB_TABLA, nombreTabla);
			Query usuariosQry = this.getNamedQuery(FIND_VALOR_POR_CODIGO,params, session);			
			List lst = usuariosQry.list();			
						
			if(lst.size()==1)
				codigo = (Integer)lst.get(0);
	        
			return codigo;
			
		}  catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException("Hubo problemas al intentar registrar el Chofer. Espere unos segundos y vuelva a intentarlo. Si el problema sigue Consulte con el Administrador.");
		}
	}

}