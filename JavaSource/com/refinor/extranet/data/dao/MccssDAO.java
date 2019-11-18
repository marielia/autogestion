package com.refinor.extranet.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.Mccss;
import com.refinor.extranet.data.base.BaseMccssDAO;
import com.refinor.extranet.to.MEmpleadosTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class MccssDAO extends BaseMccssDAO implements com.refinor.extranet.data.dao.iface.MccssDAO {

	public MccssDAO () {}
	
	public MccssDAO (Session session) {
		super(session);
	}
	
	
	public static String FIND_CCSS_REG = "findCCSSRegitrados";	
	public static String FIND_CCSS = "findCCSS";	
	public List getCCSSRegistrados() throws DataAccessErrorException, NoExistenItemsException {
			try{
				Messages mensajeria = new Messages();
				List lstPorFiltro= new ArrayList();
			
				Query query = this.getNamedQuery(FIND_CCSS_REG, session);
				lstPorFiltro = query.list();
				
				if(lstPorFiltro.size()>0){
					Iterator it = lstPorFiltro.iterator();
					lstPorFiltro= new ArrayList();
					MEmpleadosTO mEmpleadosTO= new MEmpleadosTO();
					Object[] objChofer= null;					
					
					while(it.hasNext()){
				        objChofer = (Object[]) it.next(); 
				        mEmpleadosTO=new MEmpleadosTO();				        
				        mEmpleadosTO.setCodigo(new Integer(objChofer[0].toString()));			        
				        mEmpleadosTO.setDescripcion(objChofer[1].toString());
				        lstPorFiltro.add(mEmpleadosTO);
					}
					
				}else if(lstPorFiltro.size()==0){
					throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
				}		
		        
				return lstPorFiltro;
			}
			catch(NoExistenItemsException ex){
				ex.printStackTrace();
				throw ex;
			}catch(Exception ex){
				ex.printStackTrace();
				throw new DataAccessErrorException();
			}
			
	}
	
	public static String FIND_CCSS_X_DESCRIPCION = "findCCSSXDescripcion";	
	public Mccss getCCSSporDescripcion(String descripcion) throws DataAccessErrorException, NoExistenItemsException {
		try{			
			List lstPorFiltro= new ArrayList();				
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_DESCRIPCION, descripcion);
			
			Query query = this.getNamedQuery(FIND_CCSS_X_DESCRIPCION, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()==1){
				return (Mccss)lstPorFiltro.get(0);				
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

	
	public List getCCSS(Integer ccssId) throws DataAccessErrorException, NoExistenItemsException {
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CCSS, ccssId);			
			
			Query query = this.getNamedQuery(FIND_CCSS,params, session);			
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}		
	        
			return lstPorFiltro;
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