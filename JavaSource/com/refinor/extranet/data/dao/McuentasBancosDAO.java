package com.refinor.extranet.data.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMcuentasBancosDAO;
import com.refinor.extranet.to.AsientoContableTO;
import com.refinor.extranet.to.MCuentaBancoTO;
import com.refinor.extranet.to.RendicionTranfTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class McuentasBancosDAO extends BaseMcuentasBancosDAO implements com.refinor.extranet.data.dao.iface.McuentasBancosDAO {

	public McuentasBancosDAO () {}
	
	public McuentasBancosDAO (Session session) {
		super(session);
	}

	private static String FIND_RENDICIONES_POR_CCSS_RENDICION="findRendicionesPorCCSSRendicion";
	private static String FIND_TRANSFERENCIA_POR_NROOPER_RENDICION="findTransferenciaPorNroOperRendicion";
	public List<RendicionTranfTO> getRendicionesYSusTranferencias(Integer ccss,Integer nroRendicion) throws NoExistenItemsException, DataAccessErrorException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();		
					
			params.put(Const.PARAM_COD_CCSS, ccss);
			params.put(Const.PARAM_NRO_RENDICION, nroRendicion);
								
			Query query = this.getNamedQuery(FIND_RENDICIONES_POR_CCSS_RENDICION, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MCuentaBancoTO cuentaBancoTO= new MCuentaBancoTO();
				Object[] objChofer= null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				RendicionTranfTO rendicionTranfTO= new RendicionTranfTO();
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        cuentaBancoTO = new MCuentaBancoTO();
			       
			        cuentaBancoTO.setNroOper(new Integer(objChofer[0].toString()));
			        cuentaBancoTO.setCodCcss(new Integer(objChofer[1].toString()));
			        cuentaBancoTO.setDescripcionCcss(objChofer[2].toString());
			        cuentaBancoTO.setNroEjercicio(new Integer(objChofer[3].toString()));
			        cuentaBancoTO.setNroAsiento(new Integer(objChofer[4].toString()));
			        cuentaBancoTO.setCuentaBanco(objChofer[5]==null?"":objChofer[5].toString());
			        cuentaBancoTO.setFecha(sdf.format(objChofer[6]));
			        cuentaBancoTO.setImporte(new BigDecimal(objChofer[7].toString()));
			        cuentaBancoTO.setTipoOperacion(objChofer[8].toString());
			        cuentaBancoTO.setCodOperacion(objChofer[9].toString());
			        cuentaBancoTO.setNroRendicion(new Integer(objChofer[10].toString()));
			        
			        if(objChofer[11]!=null){
			        	cuentaBancoTO.setNroOperRendicion(new Integer(objChofer[11].toString()));
			        }		        
			        rendicionTranfTO= new RendicionTranfTO();
			        rendicionTranfTO.setRendicion(cuentaBancoTO);
			        try{
			        rendicionTranfTO.setLstTransferencias(getTranferencias(cuentaBancoTO.getNroRendicion(),cuentaBancoTO.getNroOper()));
			        } catch(Exception ex){
			        	ex.printStackTrace();
			        }
			        lstPorFiltro.add(rendicionTranfTO);
					
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
	
	
	
	
	public List<MCuentaBancoTO> getTranferencias(Integer rendicion,Integer nroOperRendicion) throws NoExistenItemsException, DataAccessErrorException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();		
					
			params.put(Const.PARAM_NRO_OPER_RENDICION, nroOperRendicion);
			params.put(Const.PARAM_NRO_RENDICION, rendicion);
								
			Query query = this.getNamedQuery(FIND_TRANSFERENCIA_POR_NROOPER_RENDICION, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MCuentaBancoTO cuentaBancoTO= new MCuentaBancoTO();
				Object[] objChofer= null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");				
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        cuentaBancoTO = new MCuentaBancoTO();
			       
			        cuentaBancoTO.setNroOper(new Integer(objChofer[0].toString()));
			        cuentaBancoTO.setCodCcss(new Integer(objChofer[1].toString()));
			        cuentaBancoTO.setDescripcionCcss(objChofer[2].toString());
			        cuentaBancoTO.setNroEjercicio(new Integer(objChofer[3].toString()));
			        cuentaBancoTO.setNroAsiento(new Integer(objChofer[4].toString()));
			        cuentaBancoTO.setCuentaBanco(objChofer[5]==null?"":objChofer[5].toString());
			        cuentaBancoTO.setFecha(sdf.format(objChofer[6]));
			        cuentaBancoTO.setImporte(new BigDecimal(objChofer[7].toString()));
			        cuentaBancoTO.setTipoOperacion(objChofer[8].toString());
			        cuentaBancoTO.setCodOperacion(objChofer[9].toString());
			        cuentaBancoTO.setNroRendicion(new Integer(objChofer[10].toString()));
			        
			        if(objChofer[11]!=null){
			        	cuentaBancoTO.setNroOperRendicion(new Integer(objChofer[11].toString()));
			        }		        
			      
			        lstPorFiltro.add(cuentaBancoTO);
					
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