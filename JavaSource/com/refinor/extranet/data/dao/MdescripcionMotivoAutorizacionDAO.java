package com.refinor.extranet.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.MautorizacionMotivo;
import com.refinor.extranet.data.MdescripcionMotivoAutorizacion;
import com.refinor.extranet.data.base.BaseMdescripcionMotivoAutorizacionDAO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;


public class MdescripcionMotivoAutorizacionDAO extends BaseMdescripcionMotivoAutorizacionDAO implements com.refinor.extranet.data.dao.iface.MdescripcionMotivoAutorizacionDAO {

	public MdescripcionMotivoAutorizacionDAO () {}
	
	public MdescripcionMotivoAutorizacionDAO (Session session) {
		super(session);
	}
	
	public static String FIND_DESCRIPCIONES_POR_MOTIVO_AUTORIZACION= "findDescripcionesPorMotivoAutorizacion";	
	public List getDescripcionesPorMotAutorizacion(Integer nroMotivoAutorizacion) throws PersonaNoExisteException, DataAccessErrorException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_NRO_MOTIVO_AUT, nroMotivoAutorizacion);			
			Query query = this.getNamedQuery(FIND_DESCRIPCIONES_POR_MOTIVO_AUTORIZACION, params, session);
			List lstDatos= query.list();
			return lstDatos;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}	
	
	public static String FIND_DESCRIPCION_MOTIVO_AUTORIZACION_POR_DESCRIPCION = "findDescripcionMotivosAutorizacionPorDescripcion";	
	public MdescripcionMotivoAutorizacion getDescripcionMotivoAutorizacionPorDescripcion(String descripcion, Integer nroMotivoAutorizacion) throws DataAccessErrorException, NoExistenItemsException {
		try{			
			List lstPorFiltro= new ArrayList();				
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_DESCRIPCION, descripcion);
			params.put(Const.PARAM_NRO_MOTIVO_AUT, nroMotivoAutorizacion);	
			
			Query query = this.getNamedQuery(FIND_DESCRIPCION_MOTIVO_AUTORIZACION_POR_DESCRIPCION, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()==1 || lstPorFiltro.size()>0){
				return (MdescripcionMotivoAutorizacion)lstPorFiltro.get(0);				
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