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

import com.refinor.extranet.data.MlistaPrecios;
import com.refinor.extranet.data.TipoComprobante;
import com.refinor.extranet.data.base.BaseMlistaPreciosDAO;
import com.refinor.extranet.to.CombustibleTO;
import com.refinor.extranet.to.MAutorizacionTO;
import com.refinor.extranet.to.MListaPrecioTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class MlistaPreciosDAO extends BaseMlistaPreciosDAO implements com.refinor.extranet.data.dao.iface.MlistaPreciosDAO {

	public MlistaPreciosDAO () {}
	
	public MlistaPreciosDAO (Session session) {
		super(session);
	}

	
	public static String FIND_LISTA_PRECIOS_POR_CCSS = "findListaPreciosPorCCSS";	
	public static String FIND_LISTA_PRECIOS_POR_FILTROS = "findListaPreciosPorFiltros";
	
	public List getListaPreciosActivasPorCCSS(Integer ccss, Integer cliente) throws DataAccessErrorException  {
		try{			
			List lstPorFiltro= new ArrayList();				
			Map<String, Object> params = new HashMap<String, Object>();
			if(ccss==null)ccss=-1;
			params.put(Const.PARAM_COD_CCSS, ccss);	
			params.put(Const.PARAM_COD_CLIENTE, cliente);	
			
			Query query = this.getNamedQuery(FIND_LISTA_PRECIOS_POR_CCSS, params, session);
			lstPorFiltro = query.list();
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MlistaPrecios mlistaPrecios= new MlistaPrecios();
				Object[] objVehiculo= null;
								
				while(it.hasNext()){
					objVehiculo = (Object[]) it.next(); 
					mlistaPrecios=new MlistaPrecios();
			        
					mlistaPrecios.setCodigo(new Integer(objVehiculo[0].toString()));
					mlistaPrecios.setDescripcion(objVehiculo[1].toString());
					lstPorFiltro.add(mlistaPrecios);
				}				
				
			}				
		
			
			return lstPorFiltro;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
		
	}	
	
	
	
	public List getListaPreciosPorFiltro(Integer ccss, Integer producto, Date fechaDesde, Date fechaHasta, Integer listaPrecio,Integer cliente) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstComb= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
					
			if(ccss==null){ccss=new Integer(-1);}
			if(producto==null){producto=new Integer(-1);}
			if(listaPrecio==null){listaPrecio=new Integer(-1);}					
			if(cliente==null){cliente = new Integer(-1);}
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaAux = new Date();		
			
			if(fechaHasta!=null && fechaDesde!=null){
				fechaAux = fechaDesde;				
			}
			
			if(fechaHasta==null && fechaDesde==null){
				
				fechaAux = new Date(2030,12,30);
			}
			
			if(fechaHasta==null && fechaDesde!=null){
				fechaAux = new Date(2030,12,30);					
				fechaHasta = fechaAux;
			}			
			
			params.put(Const.PARAM_FECHA_AUX, fechaAux);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);			
			params.put(Const.PARAM_COD_CCSS, ccss);
			params.put(Const.PARAM_COD_PRODUCTO, producto);
			params.put(Const.PARAM_COD_LISTA_PRECIO, listaPrecio);		
			params.put(Const.PARAM_COD_CLIENTE, cliente);
			
			Query query=null;
			query = this.getNamedQuery(FIND_LISTA_PRECIOS_POR_FILTROS, params, session);			
			lstComb= query.list();
			
			if(lstComb.size()>0){
				Iterator itRemitos = lstComb.iterator();
				lstComb= new ArrayList();
				MListaPrecioTO mListaPrecioTO= new MListaPrecioTO();
				Object[] objRemito= null;
				
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next(); 
			        mListaPrecioTO=new MListaPrecioTO();   		        		        
			      
			        if(objRemito[0]!=null)
			           mListaPrecioTO.setDescripcionCCSS(objRemito[0].toString());	
			        
			        if(objRemito[1]!=null)
			           mListaPrecioTO.setCodCCSS(new Integer(objRemito[1].toString()));
			        
			        mListaPrecioTO.setDescripcionListaPrecio(objRemito[2].toString());	
			        mListaPrecioTO.setCodListaPrecio(new Integer(objRemito[3].toString()));
			        
			        mListaPrecioTO.setDescripcionProducto(objRemito[4].toString());
			        mListaPrecioTO.setCodProducto(new Integer(objRemito[5].toString()));
			        
			        if(objRemito[6]!=null)
			          mListaPrecioTO.setFechaDesde(sdf.format(objRemito[6]));
			        
			        if(objRemito[7]!=null)
			          mListaPrecioTO.setFechaHasta(sdf.format(objRemito[7]));	
			        else
			        	 mListaPrecioTO.setFechaHasta("Vigente");
			        
			        mListaPrecioTO.setPrecio(new BigDecimal(objRemito[8].toString()));
				   
			        if(objRemito[10]!=null)
				           mListaPrecioTO.setDescripcionCliente(objRemito[10].toString());	
				        
			        if(objRemito[11]!=null)
			           mListaPrecioTO.setCodCliente(new Integer(objRemito[11].toString()));
			   
			        if(objRemito[12]!=null)
				           mListaPrecioTO.setTipoLista(objRemito[12].toString());
				   
				        
				    lstComb.add(mListaPrecioTO);
				}
				
				
			}else if(lstComb.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}	
			
			        
			return lstComb;
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