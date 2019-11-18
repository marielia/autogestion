package com.refinor.extranet.data.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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

import com.refinor.extranet.data.base.BaseMovimientoStockDAO;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.to.MovimientoStockTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class MovimientoStockDAO extends BaseMovimientoStockDAO implements com.refinor.extranet.data.dao.iface.MovimientoStockDAO {

	public MovimientoStockDAO () {}
	
	public MovimientoStockDAO (Session session) {
		super(session);
	}
		Messages mensajeria;	
		public static String FIND_MOVIMIENTO= "findMovimiento";
		public static String FIND_LITROS_ACUMULADO= "findLitrosAcumulados";

	
		public List getMovimientoStock(Integer producto, Date fechaDesde,Date fechaHasta, Integer codCCSS) throws NoExistenItemsException, DataAccessErrorException{
		try{
			
			Messages mensajeria = new Messages();
			List lstMovimientoTotal= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put(Const.PARAM_COD_PRODUCTO, producto);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);			
			params.put(Const.PARAM_COD_CCSS, codCCSS);
			params.put(Const.COD_HEAD_OFICCE, Const.COD_CCSS_HEAD_OFICCE);	
		
					 
			Query query = this.getNamedQuery(FIND_MOVIMIENTO, params, session);
						
			lstMovimientoTotal = query.list();				
			Integer ccss=0,prod=0;
			String fecha="01/01/1800";
			BigDecimal saldo= new BigDecimal("0.00");
			
			if(lstMovimientoTotal.size()>0){
				Iterator it = lstMovimientoTotal.iterator();
				lstMovimientoTotal= new ArrayList();
				Object[] objChofer= null;
				MovimientoStockTO movimientoStockTO = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				int contador=0;
				while(it.hasNext()){
			        objChofer = (Object[]) it.next();	
			        movimientoStockTO= new MovimientoStockTO();
			       movimientoStockTO.setCodCCSS(new Integer(objChofer[0].toString()));
			        movimientoStockTO.setDescrCCSS(objChofer[1].toString());
			        movimientoStockTO.setCodProducto(new Integer(objChofer[2].toString()));
			        movimientoStockTO.setDescrProducto(objChofer[3].toString());
			        
			        movimientoStockTO.setSigno(new Integer(objChofer[13].toString()));
			        movimientoStockTO.setLitros(new BigDecimal(objChofer[14].toString()));
			        
			        if(objChofer[5]!=null){
			        	movimientoStockTO.setFechaMovimiento(sdf.format(objChofer[5]));
			        }
			        
			        
			        movimientoStockTO.setCodTipoMovimiento(new Integer(objChofer[6].toString()));
			        movimientoStockTO.setDescrMovimiento(objChofer[7].toString());
			        
//COMPOSICION DE SALDO DE LITROS
			           movimientoStockTO.setSaldoLitros(new BigDecimal("0.00"));			           
				       //System.out.println("ccss ->"+movimientoStockTO.getCodCCSS().toString()+ " prod -> "+movimientoStockTO.getCodProducto().toString());
				        
				       if(ccss.equals(movimientoStockTO.getCodCCSS()) 
				    		   && prod.equals(movimientoStockTO.getCodProducto())  ){ 
				    	   movimientoStockTO.setSaldoLitros(saldo.add(movimientoStockTO.getLitros()));				        	
				       }else{
				    	   	   	//por egreso de combustible, resto del saldo
				    	   movimientoStockTO.setSaldoLitros(movimientoStockTO.getLitros());					    	  				    	  
				    	   
				    	   if(lstMovimientoTotal.size()>0){
				        		((MovimientoStockTO)lstMovimientoTotal.get(lstMovimientoTotal.size()-1)).setUltimo(1);
				        	}				        	
				       }
				       
				       fecha= movimientoStockTO.getFechaMovimiento();
				       ccss= movimientoStockTO.getCodCCSS();
				       prod= movimientoStockTO.getCodProducto();
				       saldo= movimientoStockTO.getSaldoLitros();
//COMPOSICION DE SALDO DE LITROS
			      
			        
			        movimientoStockTO.setSucursal(new Integer(objChofer[8].toString()));
			        movimientoStockTO.setNroComprobante(new Integer(objChofer[9].toString()));
			        movimientoStockTO.setCodTipoComprobante(new Integer(objChofer[10].toString()));
			        movimientoStockTO.setDescrComprobante(objChofer[11].toString());
			        movimientoStockTO.setLetraC(objChofer[12].toString());
			        
			        //System.out.println(movimientoStockTO.getDescrCCSS()+" "+ movimientoStockTO.getNroComprobante()+" "+movimientoStockTO.getSucursal());
			        contador=contador+1;
			        System.out.println(contador+" con");
			       lstMovimientoTotal.add(movimientoStockTO);
				}
				
				if(lstMovimientoTotal.size()>0){
	        		((MovimientoStockTO)lstMovimientoTotal.get(lstMovimientoTotal.size()-1)).setUltimo(1);
	        	}
				
			}else{
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}		
	        
			return lstMovimientoTotal;
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}					
	
	public String getArchivoExcel(Integer producto, Date fechaDesde,Date fechaHasta, Integer codCCSS,String path) throws NoExistenItemsException, DataAccessErrorException{
		
		try {
			
			
			Query query = session.getNamedQuery("spGeneraArchivo");
			query.setInteger("producto", producto);
			query.setDate("fechaDesde", fechaDesde);
			query.setDate("fechaHasta", fechaHasta);
			query.setInteger("codCCSS", codCCSS);
			query.setString("path", path);
			String ok = query.list().toString();
			return ok;
			
		} catch(Exception excep) {
			excep.printStackTrace();
			
			throw new DataAccessErrorException();
		}	
	}
	
	public String getMovimientoStockV2(Integer producto, Date fechaDesde,Date fechaHasta, Integer codCCSS) throws NoExistenItemsException, DataAccessErrorException{
		
		String nombArch="";
		try{
			
			Messages mensajeria = new Messages();
			List lstMovimientoTotal= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put(Const.PARAM_COD_PRODUCTO, producto);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);			
			params.put(Const.PARAM_COD_CCSS, codCCSS);
			params.put(Const.COD_HEAD_OFICCE, Const.COD_CCSS_HEAD_OFICCE);	
		
			int cantidadRegistros=100;
			int inicio = 0;
			int maxRegistros=500;
			String fecha= "fecha" ;
			nombArch = mensajeria.getMessage().getString("info_movimiento_stock")+"_"+fecha+".xls";	
		
			
			while(inicio < maxRegistros){		
			
			Query query = this.getNamedQuery(FIND_MOVIMIENTO, params, session).setMaxResults(cantidadRegistros).setFirstResult(inicio);
			lstMovimientoTotal= query.list();
			Iterator it = lstMovimientoTotal.iterator();
			Object[] objChofer= null;
			MovimientoStockTO movimientoStockTO= null;
			
			while(it.hasNext()){
				  objChofer = (Object[]) it.next();	
			      movimientoStockTO= new MovimientoStockTO();
			     
			      movimientoStockTO.setNroComprobante(new Integer(objChofer[9].toString()));
			      System.out.println(" " + movimientoStockTO.getNroComprobante());
			}
			
			inicio = inicio + cantidadRegistros;
			
			
				
			
			}
	        
			//return lstMovimientoTotal;
			return nombArch;
		}/*catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}*/catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}

	
	public List getMovimientoStockMejorado(Integer producto, Date fechaDesde,Date fechaHasta, Integer codCCSS,String archivo) throws NoExistenItemsException, DataAccessErrorException{
		try{			
			Messages mensajeria = new Messages();
			List lstMovimientoTotal= new ArrayList();
			List lstMovimientoParcial= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put(Const.PARAM_COD_PRODUCTO, producto);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);			
			params.put(Const.PARAM_COD_CCSS, codCCSS);
			params.put(Const.COD_HEAD_OFICCE, Const.COD_CCSS_HEAD_OFICCE);	
			
			int inicio=0;
			int cantidadReg=2000;
			
		
     		BigDecimal saldo= new BigDecimal("0.00");
     		Integer ccss=0,prod=0;
     		String fecha="01/01/1800";
			
			Boolean hayRegistros= new Boolean(true);
			while(hayRegistros){							
			
			Query query = this.getNamedQuery(FIND_MOVIMIENTO, params, session).setMaxResults(cantidadReg).setFirstResult(inicio);;
					
			lstMovimientoTotal = query.list();					
			if(lstMovimientoTotal.size()>0){
				Iterator it = lstMovimientoTotal.iterator();
				lstMovimientoTotal= new ArrayList();
				Object[] objChofer= null;
				MovimientoStockTO movimientoStockTO = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				while(it.hasNext()){
			        objChofer = (Object[]) it.next();	
			        movimientoStockTO= new MovimientoStockTO();
			        movimientoStockTO.setCodCCSS(new Integer(objChofer[0].toString()));
			        movimientoStockTO.setDescrCCSS(objChofer[1].toString());
			        movimientoStockTO.setCodProducto(new Integer(objChofer[2].toString()));
			        movimientoStockTO.setDescrProducto(objChofer[3].toString());
			        
			        movimientoStockTO.setSigno(new Integer(objChofer[13].toString()));
			        movimientoStockTO.setLitros(new BigDecimal(objChofer[14].toString()));
			        
			        if(objChofer[5]!=null){
			        	movimientoStockTO.setFechaMovimiento(sdf.format(objChofer[5]));
			        }
			        			        
			        movimientoStockTO.setCodTipoMovimiento(new Integer(objChofer[6].toString()));
			        movimientoStockTO.setDescrMovimiento(objChofer[7].toString());			        
			       
			        movimientoStockTO.setSaldoLitros(new BigDecimal("0.00"));
			        
				       if(ccss.equals(movimientoStockTO.getCodCCSS()) 
				    		   && prod.equals(movimientoStockTO.getCodProducto())  ){				    	   
				    	   movimientoStockTO.setSaldoLitros(saldo.add(movimientoStockTO.getLitros()));
				        	
				       }else{
				     		   movimientoStockTO.setSaldoLitros(movimientoStockTO.getLitros());				    	 			        	
				       }
				       
				       fecha= movimientoStockTO.getFechaMovimiento();
				       ccss= movimientoStockTO.getCodCCSS();
				       prod= movimientoStockTO.getCodProducto();
				       saldo= movimientoStockTO.getSaldoLitros();			      
			        
			        movimientoStockTO.setSucursal(new Integer(objChofer[8].toString()));			        
			        if(movimientoStockTO.getSucursal().equals(new Integer(0)))
			        	movimientoStockTO.setSucursal(null);
			        
			        movimientoStockTO.setNroComprobante(new Integer(objChofer[9].toString()));
			        movimientoStockTO.setCodTipoComprobante(new Integer(objChofer[10].toString()));
			        movimientoStockTO.setDescrComprobante(objChofer[11].toString());
			        movimientoStockTO.setLetraC(objChofer[12].toString());
			        
			       lstMovimientoTotal.add(movimientoStockTO);
			       
			       if(lstMovimientoParcial.size() < cantidadReg){
			          lstMovimientoParcial.add(movimientoStockTO);
			       }
			       
				}
				
				if(lstMovimientoTotal.size()>0){										
					generaArchivoMovimientoStock(lstMovimientoTotal,archivo,inicio==0?true:false);
					lstMovimientoTotal= new ArrayList();	        	
	        	}								
				
			}else{
				//ya no hay registros - inicio = cantidadMaximaReg, para cortar el while
				//inicio = cantidadMaximaReg;
				hayRegistros= new Boolean(false);
			}
			
			inicio=inicio+cantidadReg;
				
			}
			
			
			if(lstMovimientoParcial.size()==0)
			{
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}		
	        
			return lstMovimientoParcial;
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}					

	
	public void generaArchivoMovimientoStock( List lsrPersonasAP,String archivo,Boolean escribeTitulo) throws DataAccessErrorException{
		
		try{			
			        mensajeria = new Messages();
					ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();		
					exportarExcelBean.generaArchivoMovimientoStock(lsrPersonasAP,archivo,escribeTitulo);				
				
		}catch(IOException ex){
			ex.printStackTrace();
			throw new DataAccessErrorException(mensajeria.getMessage().getString("exportacion_erronea_msg"));				
		}catch(Exception ex){
			ex.printStackTrace();				
		}  
		mensajeria=null;
	   			
	}
	
	public BigDecimal obtenerAcumuladoLitroAnterior(Integer producto, Integer codCCSS, Date fechaDesde) throws NoExistenItemsException, DataAccessErrorException{
		try{
			
			List lstMovimientoTotal= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put(Const.PARAM_COD_PRODUCTO, producto);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_COD_CCSS, codCCSS);
			params.put(Const.COD_HEAD_OFICCE, Const.COD_CCSS_HEAD_OFICCE);	
					 
			Query query = this.getNamedQuery(FIND_LITROS_ACUMULADO, params, session);
						
			lstMovimientoTotal = query.list();
			BigDecimal total= new BigDecimal(0.00);
			
			if(lstMovimientoTotal.size()>0){
				Iterator itRemitos = lstMovimientoTotal.iterator();				
						
				while(itRemitos.hasNext()){						
			            total= new BigDecimal(itRemitos.next().toString());					
				}				
			}
	        
			return total;	
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
		
	}
	
}