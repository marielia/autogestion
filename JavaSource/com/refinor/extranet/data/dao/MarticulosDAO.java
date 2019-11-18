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