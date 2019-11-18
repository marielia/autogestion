package com.refinor.extranet.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;



import com.refinor.extranet.data.MautorizacionMotivo;
import com.refinor.extranet.data.Mvendedor;
import com.refinor.extranet.data.base.BaseMautorizacionMotivoDAO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class MautorizacionMotivoDAO extends BaseMautorizacionMotivoDAO implements com.refinor.extranet.data.dao.iface.MautorizacionMotivoDAO {

	public MautorizacionMotivoDAO () {}
	
	public MautorizacionMotivoDAO (Session session) {
		super(session);
	}
	
	public static String FIND_MOTIVO_AUTORIZACION_POR_DESCRIPCION = "findMotivosAutorizacionPorDescripcion";	
	public MautorizacionMotivo getMotivoAutorizacionPorDescripcion(String descripcion) throws DataAccessErrorException, NoExistenItemsException {
		try{			
			List lstPorFiltro= new ArrayList();				
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_DESCRIPCION, descripcion);
			
			Query query = this.getNamedQuery(FIND_MOTIVO_AUTORIZACION_POR_DESCRIPCION, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()==1 || lstPorFiltro.size()>0){
				return (MautorizacionMotivo)lstPorFiltro.get(0);				
			}else {
				throw new NoExistenItemsException();
			}	        
			
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
		
	}



}