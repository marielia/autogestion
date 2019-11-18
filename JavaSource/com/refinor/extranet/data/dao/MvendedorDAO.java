package com.refinor.extranet.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.Mvendedor;
import com.refinor.extranet.data.base.BaseMvendedorDAO;
import com.refinor.extranet.to.MEmpleadosTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class MvendedorDAO extends BaseMvendedorDAO implements com.refinor.extranet.data.dao.iface.MvendedorDAO {

	public MvendedorDAO () {}
	
	public MvendedorDAO (Session session) {
		super(session);
	}
	
	public static String FIND_VENDEDORES_REG = "findVendedoresRegitrados";	

	public List getVendedorRegistrados() throws DataAccessErrorException, NoExistenItemsException {
			try{
				Messages mensajeria = new Messages();
				List lstPorFiltro= new ArrayList();
			
				Query query = this.getNamedQuery(FIND_VENDEDORES_REG, session);
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
				        mEmpleadosTO.setDescripcionApe(objChofer[2].toString());  
				        
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
	

	public static String FIND_VENDEDOR_POR_NOMBRE = "findVendedorPorNombre";	
	public Mvendedor getVendedorPorNombre(String descripcion) throws DataAccessErrorException, NoExistenItemsException {
		try{			
			List lstPorFiltro= new ArrayList();				
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_DESCRIPCION, descripcion);
			
			Query query = this.getNamedQuery(FIND_VENDEDOR_POR_NOMBRE, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()==1 || lstPorFiltro.size()>0){
				return (Mvendedor)lstPorFiltro.get(0);				
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