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

import com.refinor.extranet.data.Mempleado;
import com.refinor.extranet.data.MempleadoId;
import com.refinor.extranet.data.base.BaseMcombustibleDespachoDAO;
import com.refinor.extranet.to.CombustibleTO;
import com.refinor.extranet.to.ConsumoTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class McombustibleDespachoDAO extends BaseMcombustibleDespachoDAO implements com.refinor.extranet.data.dao.iface.McombustibleDespachoDAO {

	public McombustibleDespachoDAO () {}
	
	public McombustibleDespachoDAO (Session session) {
		super(session);
	}

	public static String FIND_COMBUSTIBLES= "findCombustibles";
	
	
	public List getCombustiblesPorFiltros(Integer ccss, Integer producto, Date fechaDesde, Date fechaHasta, Integer tipoComprobante, Integer estado) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstComb= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
					
			if(ccss==null){ccss=new Integer(-1);}
			if(producto==null){producto=new Integer(-1);}
			if(tipoComprobante==null){tipoComprobante=new Integer(-1);}
			if(estado==null){estado=new Integer(-1);}
			
			
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);			
			params.put(Const.PARAM_COD_CCSS, ccss);
			params.put(Const.PARAM_COD_PRODUCTO, producto);
			params.put(Const.PARAM_TIPO_COMPROBANTE, tipoComprobante);	
			params.put(Const.PARAM_ESTADO, estado);	
			
			
			
			Query query=null;
			query = this.getNamedQuery(FIND_COMBUSTIBLES, params, session);			
			lstComb= query.list();
			
			if(lstComb.size()>0){
				Iterator itRemitos = lstComb.iterator();
				lstComb= new ArrayList();
				CombustibleTO consumoTO= new CombustibleTO();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Object[] objRemito= null;
				
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next(); 
			        consumoTO=new CombustibleTO();   		        		        
			        
			        consumoTO.setId(new Integer(objRemito[0].toString()));			        
				    consumoTO.setCcss(new Integer(objRemito[1].toString()));
				    consumoTO.setCcssDesc(objRemito[2].toString());
				    consumoTO.setProducto(new Integer(objRemito[3].toString()));
				    consumoTO.setProductoDesc(objRemito[4].toString());
				    consumoTO.setLitros(new BigDecimal(objRemito[5].toString()));
				    consumoTO.setFecha(sdf.format(objRemito[6]));
				    consumoTO.setTipoComprobanteCod(new Integer(objRemito[7].toString()));				    
				    consumoTO.setTipoComprobanteDesc(objRemito[8].toString());
				    consumoTO.setLetraComprobante(objRemito[9].toString());
				    consumoTO.setNroComprobante(new Integer(objRemito[10].toString()));	
				    consumoTO.setEstado(new Integer(objRemito[11].toString()));	
				    if(consumoTO.getEstado().equals(new Integer(1))){
				    	consumoTO.setEstadoDesc(mensajeria.getMessage().getString("recibido_label"));
				    } else if(consumoTO.getEstado().equals(new Integer(0))){
				    	consumoTO.setEstadoDesc(mensajeria.getMessage().getString("enviado_label"));
				    }
				    
				    if(objRemito[12]!=null){
					    consumoTO.setResponsable(new Integer(objRemito[12].toString()));		
					    consumoTO.setResponsableDesc(obtenerEmpleado(consumoTO.getResponsable()));
				    }
				    
				    lstComb.add(consumoTO);
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
	
	private String obtenerEmpleado(int nroEmpleado) throws Exception{
		String nombre="";
		try{
			
			MempleadoDAO emDAO= new MempleadoDAO(session);			
			Mempleado mempleado= new Mempleado();			
			List lst= emDAO.getEmpleadoPorCodigo(nroEmpleado);
			if(lst.size()>0){
				mempleado = (Mempleado)lst.get(0);
				nombre=mempleado.getDescripcion();
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;			
		}
		return nombre;
	}
}