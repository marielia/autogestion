package com.refinor.extranet.data.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.MdescripcionMotivoAutorizacion;
import com.refinor.extranet.data.TipoComprobante;
import com.refinor.extranet.data.base.BaseTipoComprobanteDAO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.VariosChoferesConIgualDNIException;


public class TipoComprobanteDAO extends BaseTipoComprobanteDAO implements com.refinor.extranet.data.dao.iface.TipoComprobanteDAO {

	public TipoComprobanteDAO () {}
	
	public TipoComprobanteDAO (Session session) {
		super(session);
	}
	
	public static String FIND_TIPOS_COMPROBANTES= "findTiposComprobantesVenta";
	public static String FIND_TIPOS_COMPROBANTES_PARA_COMBUSTIBLES= "findTiposComprobantesParaCombustible";
	
	public List getTiposComprobantesVenta() throws NoExistenItemsException, VariosChoferesConIgualDNIException, IOException, DataAccessErrorException{
		try{
			
			List lst= new ArrayList();
			Query query = this.getNamedQuery(FIND_TIPOS_COMPROBANTES,  session);
			lst= query.list();			
			return lst;					
			
		}catch(Exception ex){
			ex.printStackTrace();			
			throw new DataAccessErrorException();
		}
		
	}
	
	
	public List getTiposComprobantesParaCombustible() throws NoExistenItemsException, VariosChoferesConIgualDNIException, IOException, DataAccessErrorException{
		try{			
			List lst= new ArrayList();
			Query query = this.getNamedQuery(FIND_TIPOS_COMPROBANTES_PARA_COMBUSTIBLES,  session);
			lst= query.list();			
			return lst;					
			
		}catch(Exception ex){
			ex.printStackTrace();			
			throw new DataAccessErrorException();
		}
		
	}
	
	
	public static String FIND_TIPO_COMPROBANTE_POR_DESCRIPCION = "findTipoComprobantePorDescripcion";	
	public TipoComprobante getTipoComprobantePorDescripcion(String descripcion) throws DataAccessErrorException, NoExistenItemsException {
		try{			
			List lstPorFiltro= new ArrayList();				
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_DESCRIPCION, descripcion);				
			
			Query query = this.getNamedQuery(FIND_TIPO_COMPROBANTE_POR_DESCRIPCION, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()==1 || lstPorFiltro.size()>0){
				return (TipoComprobante)lstPorFiltro.get(0);				
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