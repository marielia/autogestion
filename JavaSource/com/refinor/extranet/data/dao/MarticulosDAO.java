package com.refinor.extranet.data.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.Marticulos;
import com.refinor.extranet.data.TipoComprobante;
import com.refinor.extranet.data.base.BaseMarticulosDAO;
import com.refinor.extranet.to.AnioTO;
import com.refinor.extranet.to.FamiliaTO;
import com.refinor.extranet.to.GrupoTO;
import com.refinor.extranet.to.ImpuestoLeyVialCbaTO;
import com.refinor.extranet.to.MovimientoStockTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;


public class MarticulosDAO extends BaseMarticulosDAO implements com.refinor.extranet.data.dao.iface.MarticulosDAO {

	public MarticulosDAO () {}
	
	public MarticulosDAO (Session session) {
		super(session);}

	
	
	public static String FIND_ARTICULOS= "findArticulos";
	public static String FIND_ARTICULOS_Es_COMBO= "findArticulosEsCombo"; 
	public static String FIND_FAMILIAS= "findFamilias";
	public static String FIND_GRUPO= "findGrupos";
	
	public List getArticulos() throws PersonaNoExisteException, DataAccessErrorException {
		try {		
			Query personasQry = this.getNamedQuery(FIND_ARTICULOS, session);
			List articulos= personasQry.list();
			return articulos;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public List getArticulosEsCombo() throws PersonaNoExisteException, DataAccessErrorException {
		try {		
			Query personasQry = this.getNamedQuery(FIND_ARTICULOS_Es_COMBO, session);
			List articulos= personasQry.list();
			return articulos;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	//get familia - especies
	public List getFamilias() throws NoExistenItemsException, DataAccessErrorException { 

			try{
				Messages mensajeria = new Messages();
				List lstRes= new ArrayList();
				Query qry = this.getNamedQuery(FIND_FAMILIAS,  session);
				lstRes= qry.list();
				
				if(lstRes.size()>0){
					Iterator itCupos = lstRes.iterator();
					lstRes= new ArrayList();
					FamiliaTO familiaTO= new FamiliaTO();
					Object[] objResultado= null;
					
					while(itCupos.hasNext()){
						objResultado=(Object[]) itCupos.next();	
						familiaTO= new FamiliaTO();	
						familiaTO.setCodigo(Integer.parseInt(objResultado[0].toString()));					
						familiaTO.setDescripcion(objResultado[1].toString());					
				        lstRes.add(familiaTO);
					}		
					
				}else if(lstRes.size()==0){
					throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
				}	
				 
		        
				return lstRes;
			}
			catch(NoExistenItemsException ex){
				ex.printStackTrace();
				throw ex;
			}catch(Exception ex){
				ex.printStackTrace();
				throw new DataAccessErrorException();
			}
	}
	
	//variante- grupo
	public List getGrupo() throws NoExistenItemsException, DataAccessErrorException { 
		 try{
				Messages mensajeria = new Messages();
				List lstRes= new ArrayList();
				Query qry = this.getNamedQuery(FIND_GRUPO,  session);
				lstRes= qry.list();
				
				if(lstRes.size()>0){
					Iterator itCupos = lstRes.iterator();
					lstRes= new ArrayList();
					GrupoTO grupoTO= new GrupoTO();
					Object[] objResultado= null;
					
					while(itCupos.hasNext()){
						objResultado=(Object[]) itCupos.next();	
						grupoTO= new GrupoTO();	
						grupoTO.setCodigo(Integer.parseInt(objResultado[0].toString()));					
						grupoTO.setDescripcion(objResultado[1].toString());					
				        lstRes.add(grupoTO);
					}		
					
				}else if(lstRes.size()==0){
					throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
				}	
				 
		        
				return lstRes;
			}
			catch(NoExistenItemsException ex){
				ex.printStackTrace();
				throw ex;
			}catch(Exception ex){
				ex.printStackTrace();
				throw new DataAccessErrorException();
			}
	}
	
	public static String FIND_PRODUCTO_POR_DESCRIPCION = "findProductoPorDescripcion";	
	public Marticulos getProductoPorDescripcion(String descripcion) throws DataAccessErrorException, NoExistenItemsException {
		try{			
			List lstPorFiltro= new ArrayList();				
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_DESCRIPCION, descripcion);				
			
			Query query = this.getNamedQuery(FIND_PRODUCTO_POR_DESCRIPCION, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()==1 || lstPorFiltro.size()>0){
				return (Marticulos)lstPorFiltro.get(0);				
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