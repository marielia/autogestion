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

import com.refinor.extranet.data.base.BaseMexclusionDAO;
import com.refinor.extranet.to.MExclusionTO;
import com.refinor.extranet.to.PercepcionesIIBBTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.DataUtil;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class MexclusionDAO extends BaseMexclusionDAO implements com.refinor.extranet.data.dao.iface.MexclusionDAO {

	public MexclusionDAO () {}
	
	public MexclusionDAO (Session session) {
		super(session);
	}
	public static String FIND_EXCLUSION_POR_FILTROS= "findExclusionesPorFiltro";	
	
	public List getExclusiones(Date fechaDesde, Date fechaHasta, Integer cliente, int provincia, BigDecimal porcentajeExclusion, int tipoExclusion ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();		
			
			if(porcentajeExclusion==null) porcentajeExclusion=new BigDecimal(-1);
			
			params.put(Const.PARAM_PORCENTAJE_EXCLUSION, porcentajeExclusion );
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
			
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_AUX, fechaAux);		
			params.put(Const.PARAM_COD_CLIENTE, cliente);			
			params.put(Const.PARAM_COD_PROVINCIA, provincia );
			params.put(Const.PARAM_TIPO_EXCLUSION, tipoExclusion );
			Query query = this.getNamedQuery(FIND_EXCLUSION_POR_FILTROS, params, session);
			
			lstPorFiltro = query.list();			
			DataUtil dataUtil = new DataUtil();
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MExclusionTO mExclusion= new MExclusionTO();
				Object[] objeto= null;		
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				while(it.hasNext()){
					objeto = (Object[]) it.next(); 
					mExclusion = new MExclusionTO();
			       
					if(objeto[0]!=null)
					  mExclusion.setFechaDesde(sdf1.format(objeto[0]));
					
					if(objeto[1]!=null)
					 mExclusion.setFechaHasta(sdf1.format(objeto[1]));
					
					mExclusion.setCliDescripcion(objeto[3].toString());
					mExclusion.setCodClienteAlfa(objeto[4].toString());
					mExclusion.setTipoExcDescripcion(objeto[6].toString());
					mExclusion.setPorcentajeExclusion(new BigDecimal(objeto[7].toString()));
					mExclusion.setProvDescripcion(objeto[9].toString());			    
			        lstPorFiltro.add(mExclusion);
					
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