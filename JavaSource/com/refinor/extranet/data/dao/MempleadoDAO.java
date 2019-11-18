package com.refinor.extranet.data.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMempleadoDAO;
import com.refinor.extranet.to.MEmpleadosTO;
import com.refinor.extranet.to.MFacturaVTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;


public class MempleadoDAO extends BaseMempleadoDAO implements com.refinor.extranet.data.dao.iface.MempleadoDAO {

	public MempleadoDAO () {}
	
	public MempleadoDAO (Session session) {
		super(session);
	}

	
	public static String FIND_EMPLEADOS_POR_CODIGO_Y_CCSSS= "findEmpleadosPorCodigoYCCSS";	
	public static String FIND_EMPLEADOS_POR_CODIGO= "findEmpleadosPorCodigo";	
	
	public static String FIND_EMPLEADOS= "findEmpleados";	
	public static String FIND_EMPLEADOS_REGISTRADOS= "findEmpleadosRegitrados";	
	
	public List getEmpleadoPorCodigo(int codigo) throws PersonaNoExisteException, DataAccessErrorException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_CODIGO, codigo);			
			Query personasQry = this.getNamedQuery(FIND_EMPLEADOS_POR_CODIGO, params, session);
			List personas= personasQry.list();
			if(personas.size() == 0)
				throw new PersonaNoExisteException();
			else
				return personas;
		} catch (PersonaNoExisteException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public List getEmpleadoPorCodigoyCCSS(int codigo, int ccss) throws PersonaNoExisteException, DataAccessErrorException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_CODIGO, codigo);
			params.put(Const.PARAM_COD_CCSS, ccss);
			Query personasQry = this.getNamedQuery(FIND_EMPLEADOS_POR_CODIGO_Y_CCSSS, params, session);
			List personas= personasQry.list();
			if(personas.size() == 0)
				throw new PersonaNoExisteException();
			else
				return personas;
		} catch (PersonaNoExisteException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public List getEmpleadosRegistrados() throws DataAccessErrorException, NoExistenItemsException {
			try{
				Messages mensajeria = new Messages();
				List lstPorFiltro= new ArrayList();
			
				Query query = this.getNamedQuery(FIND_EMPLEADOS_REGISTRADOS, session);
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


	
	public List getEmpleados() throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
		
			Query query = this.getNamedQuery(FIND_EMPLEADOS, session);
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
			        mEmpleadosTO.setNroLegajo(new Integer(objChofer[2].toString()));
			        mEmpleadosTO.setActivo(new Boolean(objChofer[3].toString()));
			        mEmpleadosTO.setCcssid(new Integer(objChofer[4].toString()));			        
			        mEmpleadosTO.setDni(new Integer(objChofer[5].toString()));
			        mEmpleadosTO.setDescrCCSS(objChofer[6].toString());	     
			        
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

}