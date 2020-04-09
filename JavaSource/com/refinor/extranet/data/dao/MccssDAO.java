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

import com.refinor.extranet.data.Mccss;
import com.refinor.extranet.data.base.BaseMccssDAO;
import com.refinor.extranet.to.CCSSTO;
import com.refinor.extranet.to.FormaPagoTO;
import com.refinor.extranet.to.MEmpleadosTO;
import com.refinor.extranet.to.MTurnoTO;
import com.refinor.extranet.to.MVehiculoTO;
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
	public static String FIND_CCSS_X_DESCRIPCION = "findCCSSXDescripcion";
	public static String FIND_TURNO_VIGENTE = "findTurnoVigente";
	public static String FIND_PERIODO_MENSUAL_VIGENTE = "findPeriodoMensualVigente";
	public static String FIND_CCSSTO = "findCCSSTO";
	
	
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
	
	
	public MTurnoTO getTurnoVigenteAbierto(int codSector, int codEstadoTurno, int codCcss) throws DataAccessErrorException, NoExistenItemsException {
		try{			
			List lstPorFiltro= new ArrayList();				
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_SECTOR, codSector);
			params.put(Const.PARAM_COD_ESTADO_TURNO, codEstadoTurno);
			params.put(Const.PARAM_COD_CCSS, codCcss);
			
			Query query = this.getNamedQuery(FIND_TURNO_VIGENTE, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()==1){
				Iterator itTurno = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MTurnoTO mTurnoTO= new MTurnoTO();
				SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
				Object[] obj = null;
				
				while(itTurno.hasNext())
				{
					obj = (Object[]) itTurno.next(); 
					mTurnoTO = new MTurnoTO();
					mTurnoTO.setCodTurno(new Integer(obj[0].toString()));
					mTurnoTO.setDescTurno(obj[1].toString());					
					mTurnoTO.setCodSector(new Integer(obj[2].toString()));						
					mTurnoTO.setCodTurnoVigencia(new Integer(obj[3].toString()));	
					mTurnoTO.setCodEstadoTurno(new Integer(obj[4].toString())); 					 
					mTurnoTO.setCodAlmacen(new Integer(obj[5].toString()));	
					mTurnoTO.setDescAlmacen(obj[6].toString());
					lstPorFiltro.add(mTurnoTO);
				}		
				
				
				return (MTurnoTO)lstPorFiltro.get(0);				
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
		
	public Boolean ExistePeriodoMensual(int codCcss, Date fecha) throws DataAccessErrorException, NoExistenItemsException {
		try{			
			List lstPorFiltro= new ArrayList();				
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_FECHA, fecha); 
			params.put(Const.PARAM_COD_CCSS, codCcss);
			
			Query query = this.getNamedQuery(FIND_PERIODO_MENSUAL_VIGENTE, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()==1){ 
				return true;				
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
	
	
	public CCSSTO getCCSSTO(int codCcss) throws DataAccessErrorException, NoExistenItemsException {
		try{			
			List lstPorFiltro= new ArrayList();				
			Map<String, Object> params = new HashMap<String, Object>(); 
			params.put(Const.PARAM_COD_CCSS, codCcss);
			
			Query query = this.getNamedQuery(FIND_CCSSTO, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()==1){  
				Iterator it  = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				CCSSTO ccssTO= new CCSSTO(); 
				Object[] obj = null;
				
				while(it.hasNext())
				{
					obj = (Object[]) it .next(); 
					ccssTO = new CCSSTO();
					ccssTO.setCodigo(new Integer(obj[0].toString()));
					ccssTO.setDescripcion(obj[1].toString());					
					ccssTO.setNroLegJefe(new Integer(obj[2].toString()));						
					ccssTO.setConsumidorFinalId(new BigDecimal(obj[3].toString()));	
					ccssTO.setPcia(new Integer(obj[4].toString())); 
					ccssTO.setEsTerceros(new Boolean(obj[5].toString()));
					ccssTO.setEsHoffice(new Boolean(obj[6].toString())); 
					lstPorFiltro.add(ccssTO);
				}					
				
				return (CCSSTO)lstPorFiltro.get(0); 
			     				
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
	
	
	
	public List getFormasPagos(int condVta, int codCCSS ) throws DataAccessErrorException, NoExistenItemsException {
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>(); 
			params.put(Const.PARAM_CON_VTA, condVta);
			params.put(Const.PARAM_COD_CCSS, codCCSS);
			
			Query query = this.getNamedQuery("findFormasPago",params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				FormaPagoTO formaPagoTO= new FormaPagoTO();
				Object[] objChofer= null;					
				
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        formaPagoTO=new FormaPagoTO();				        
			        formaPagoTO.setCodigo(new Integer(objChofer[0].toString()));			        
			        formaPagoTO.setDescripcion(objChofer[1].toString());
			        lstPorFiltro.add(formaPagoTO);
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