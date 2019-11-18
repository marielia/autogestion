package com.refinor.extranet.data.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import com.refinor.extranet.data.base.BaseMasientoContableDAO;
import com.refinor.extranet.to.AsientoContableTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class MasientoContableDAO extends BaseMasientoContableDAO implements com.refinor.extranet.data.dao.iface.MasientoContableDAO {

	public MasientoContableDAO () {}
	
	public MasientoContableDAO (Session session) {
		super(session);
	}
	public static String FIND_MAYOR_CONTABLE_MFACTURA= "findMayorContableMFacturaV";
	public static String FIND_MAYOR_CONTABLE_MPEDIDO= "findMayorContableMPedido";
	public static String FIND_MAYOR_CONTABLE_MRECIBO= "findMayorContableMRecibo";
	
	public static String FIND_SUMAS_SALDOS= "findSumasySaldos";
	public static String FIND_ASIENTOS_POR_CCSS_NROEJER_NROASIENTO= "findAsientosPorCCSSNroEjerNroAsiento";
	public static String FIND_ASIENTOS_POR_CCSS_NROEJER_NROASIENTO_v2= "findAsientosPorCCSSNroEjerNroAsientov2";
	
	public static String FIND_MAYOR_CONTABLE_MFACTURA_II= "findMayorContableMFacturaV3";
	public static String FIND_LIBRO_DIARIO= "findLibroDiario";
	
	public List getMayorContable(String cuentaDesde,String cuentaHasta, Date fechaDesde, Date fechaHasta , Integer ccss) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(cuentaHasta.trim().equals("") && !cuentaDesde.trim().equals("")) cuentaHasta=cuentaDesde;
			if(fechaHasta==null && fechaDesde!=null) fechaHasta=fechaDesde;
			if(cuentaHasta.trim().equals("") && cuentaDesde.trim().equals("")){
				cuentaHasta=null;
				cuentaDesde=null;
			}			
			params.put(Const.PARAM_CUENTA_DESDE, cuentaDesde);
			params.put(Const.PARAM_CUENTA_HASTA, cuentaHasta);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);	
			params.put(Const.PARAM_COD_CCSS, ccss);		
					
			Query query = this.getNamedQuery(FIND_MAYOR_CONTABLE_MFACTURA, params, session);
			lstPorFiltro = query.list();
			
			query = this.getNamedQuery(FIND_MAYOR_CONTABLE_MPEDIDO, params, session);
			List mpedido = query.list();
			
			if(mpedido.size()>0){
				lstPorFiltro.addAll(mpedido);
			}		
			
			query = this.getNamedQuery(FIND_MAYOR_CONTABLE_MRECIBO, params, session);
			List mrecibo = query.list();
			
			if(mrecibo.size()>0){
				lstPorFiltro.addAll(mrecibo);
			}
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				AsientoContableTO asientoContableTO= new AsientoContableTO();
				Object[] objChofer= null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfH = new SimpleDateFormat("HH:mm");
				BigDecimal axuSaldo= new BigDecimal(0.00);
				String cuenta ="";
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        asientoContableTO = new AsientoContableTO();
			       
					asientoContableTO.setCcddId(new Integer(objChofer[0].toString()));
			        asientoContableTO.setCcssDesc(objChofer[1].toString());
			        asientoContableTO.setNroEjercicio(new Integer(objChofer[2].toString()));
			        asientoContableTO.setNroAsiento(new Integer(objChofer[3].toString()));
			        asientoContableTO.setCuenta(objChofer[4].toString());
			        asientoContableTO.setDebHab(objChofer[5].toString());
			        asientoContableTO.setValor(new BigDecimal(objChofer[6].toString()));
			        asientoContableTO.setLeyenda(objChofer[7].toString());
			        if(objChofer[8]!=null){
			        	asientoContableTO.setFecha(sdf.format(objChofer[8]));
			        	asientoContableTO.setHora(sdfH.format(objChofer[8]));
			        }
			        
			        if(objChofer[9]!=null){
			        	asientoContableTO.setCuentaDesc(objChofer[9].toString());			        	
			        }
			        
			       if(new Integer(objChofer[10].toString()).equals(new Integer (0) ))
			          asientoContableTO.setNroSuc(null);
			       else{
			    	   asientoContableTO.setNroSuc(new Integer(objChofer[10].toString()));
			       }
			        asientoContableTO.setNroComp(new Integer(objChofer[11].toString()));
			        if(objChofer[12]!=null){
			        	  asientoContableTO.setTipoComp(objChofer[12].toString().trim());
			        	  asientoContableTO.setLetra(objChofer[13].toString().trim());
			        	  
				       //System.out.println(asientoContableTO.getTipoComp());
				        if(asientoContableTO.getTipoComp().trim().equals("FC")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("factura_factura_label"));     	
				        }else if(asientoContableTO.getTipoComp().trim().equals("NC")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("nota_credito_label"));				        	 
				        }else if(asientoContableTO.getTipoComp().trim().equals("ND")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("nota_debito_label"));
				        }else if(asientoContableTO.getTipoComp().trim().equals("REM")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("remito_label_x"));
				        }else if(asientoContableTO.getTipoComp().trim().equals("REC")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("recibo_label_x"));
				        }
				        
			        }
			        
			        if(!cuenta.equals(asientoContableTO.getCuenta())){
			           	cuenta = asientoContableTO.getCuenta();
			        	axuSaldo= new BigDecimal(0.00);
			        	
			        	if(lstPorFiltro.size()>0){
			        		((AsientoContableTO)lstPorFiltro.get(lstPorFiltro.size()-1)).setUltimo(1);
			        	}
			        }
			        
			        if(asientoContableTO.getDebHab().equals("D")){
				        	 axuSaldo = axuSaldo.add(asientoContableTO.getValor());
				     }else if(asientoContableTO.getDebHab().equals("H")){
				        	axuSaldo = axuSaldo.subtract(asientoContableTO.getValor());
				     }
				       
				        asientoContableTO.setSaldo(axuSaldo);
			       
			        lstPorFiltro.add(asientoContableTO);
					
				}
				
				if(lstPorFiltro.size()>0){
	        		((AsientoContableTO)lstPorFiltro.get(lstPorFiltro.size()-1)).setUltimo(1);
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
	
	
	
	public List getMayorContable2(String cuentaDesde,String cuentaHasta, Date fechaDesde, Date fechaHasta , Integer ccss) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(cuentaHasta.trim().equals("") && !cuentaDesde.trim().equals("")) cuentaHasta=cuentaDesde;
			if(fechaHasta==null && fechaDesde!=null) fechaHasta=fechaDesde;
			if(cuentaHasta.trim().equals("") && cuentaDesde.trim().equals("")){
				cuentaHasta=null;
				cuentaDesde=null;
			}			
			params.put(Const.PARAM_CUENTA_DESDE, cuentaDesde);
			params.put(Const.PARAM_CUENTA_HASTA, cuentaHasta);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);	
			params.put(Const.PARAM_COD_CCSS, ccss);		
					
			Query query = this.getNamedQuery(FIND_MAYOR_CONTABLE_MFACTURA_II, params, session);
			lstPorFiltro = query.list();			
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				AsientoContableTO asientoContableTO= new AsientoContableTO();
				Object[] objChofer= null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfH = new SimpleDateFormat("HH:mm");
				BigDecimal axuSaldo= new BigDecimal(0.00);
				String auxFechaD=null;
				String auxFechaM=null;
				String auxFechaA=null;
				String cuenta ="";
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        asientoContableTO = new AsientoContableTO();
			       
					asientoContableTO.setCcddId(new Integer(objChofer[0].toString()));
			        asientoContableTO.setCcssDesc(objChofer[1].toString());
			        asientoContableTO.setNroEjercicio(new Integer(objChofer[2].toString()));
			        asientoContableTO.setNroAsiento(new Integer(objChofer[3].toString()));
			        asientoContableTO.setCuenta(objChofer[4].toString().trim());
			        asientoContableTO.setDebHab(objChofer[5].toString());
			        asientoContableTO.setValor(new BigDecimal(objChofer[6].toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
			        asientoContableTO.setLeyenda(objChofer[7].toString());
			        
			        if(objChofer[8]!=null){	        	
			        	asientoContableTO.setFecha(objChofer[8].toString());
			        	auxFechaA=asientoContableTO.getFecha().substring(0, 4);
			        	auxFechaM=asientoContableTO.getFecha().substring(4, 6);
			        	auxFechaD=asientoContableTO.getFecha().substring(6, 8);
			        	
			        	asientoContableTO.setFecha(auxFechaD+"/"+auxFechaM+"/"+auxFechaA);
			        	//asientoContableTO.setFecha(sdf.format(objChofer[8]));
			        	
			        }
			        if(objChofer[18]!=null){			        	
			        	asientoContableTO.setHora(objChofer[18].toString());
			        }
			        
			        if(objChofer[9]!=null){
			        	asientoContableTO.setCuentaDesc(objChofer[9].toString());			        	
			        }
			        
			       if(new Integer(objChofer[10].toString()).equals(new Integer (0) ))
			          asientoContableTO.setNroSuc(null);
			       else{
			    	   asientoContableTO.setNroSuc(new Integer(objChofer[10].toString()));
			       }
			       
			     //System.out.println(asientoContableTO.getNroComp()+" ");
			     //System.out.println(asientoContableTO.getNroSuc()); 
			       
			        asientoContableTO.setNroComp(new Integer(objChofer[11].toString()));
			        if(objChofer[12]!=null){
			        	  asientoContableTO.setTipoComp(objChofer[12].toString().trim());
			        	  asientoContableTO.setLetra(objChofer[13].toString().trim());
			        	  
				       //System.out.println(asientoContableTO.getTipoComp());
				        if(asientoContableTO.getTipoComp().trim().equals("FC")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("factura_factura_label"));     	
				        }else if(asientoContableTO.getTipoComp().trim().equals("NC")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("nota_credito_label"));				        	 
				        }else if(asientoContableTO.getTipoComp().trim().equals("ND")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("nota_debito_label"));
				        }else if(asientoContableTO.getTipoComp().trim().equals("REM")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("remito_label_x"));
				        }else if(asientoContableTO.getTipoComp().trim().equals("REC")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("recibo_label_x"));
				        }else if(asientoContableTO.getTipoComp().trim().equals("RECAN")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("recibo_anulado_label_x"));
				        }else if(asientoContableTO.getTipoComp().trim().equals("CHEQUE")){
				        	asientoContableTO.setTipoComprobante("CHEQUE");
				        }
				        
			        }
			        
			        if(!cuenta.trim().equals(asientoContableTO.getCuenta().trim())){
			           	cuenta = asientoContableTO.getCuenta();
			        	axuSaldo= new BigDecimal(0.00);
			        	
			        	if(lstPorFiltro.size()>0){
			        		((AsientoContableTO)lstPorFiltro.get(lstPorFiltro.size()-1)).setUltimo(1);
			        	}
			        }
			        
			        if(asientoContableTO.getDebHab().equals("D")){
				        	 axuSaldo = (axuSaldo.add(asientoContableTO.getValor())).setScale(2, BigDecimal.ROUND_HALF_UP);
				     }else if(asientoContableTO.getDebHab().equals("H")){
				        	axuSaldo = (axuSaldo.subtract(asientoContableTO.getValor())).setScale(2, BigDecimal.ROUND_HALF_UP);
				     }
				       
				        asientoContableTO.setSaldo(axuSaldo);
			       
				        asientoContableTO.setCodClienteAlfa(objChofer[14].toString().trim());
				        asientoContableTO.setDescripcionCliente(objChofer[15].toString().trim());
				        if(asientoContableTO.getTipoComp().trim().equals("CHEQUE")){
				          asientoContableTO.setComprobanteAsociado("Recibo: "+objChofer[16].toString().trim());
				        }
				        
				        
				        if(objChofer[17]!=null && !new Integer(objChofer[17].toString()).equals(new Integer(0))){
				          asientoContableTO.setNroRendicion(objChofer[17].toString().trim());
				        }
			        lstPorFiltro.add(asientoContableTO);
					
				}
								
				if(lstPorFiltro.size()>0){
	        		((AsientoContableTO)lstPorFiltro.get(lstPorFiltro.size()-1)).setUltimo(1);
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
	
	
	public List getLibroDiario(Date fechaDesde, Date fechaHasta) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(fechaHasta==null && fechaDesde!=null) fechaHasta=fechaDesde;
					
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);					
					
			Query query = this.getNamedQuery(FIND_LIBRO_DIARIO, params, session);
			lstPorFiltro = query.list();			
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				AsientoContableTO asientoContableTO= new AsientoContableTO();
				AsientoContableTO asientoContableTOSaldo= new AsientoContableTO();
				Object[] objChofer= null;
				
				
				BigDecimal axuSaldo= new BigDecimal(0.00);
				BigDecimal axuSaldo2= new BigDecimal(0.00);
				String auxFechaD=null;
				String auxFechaM=null;
				String auxFechaA=null;
				Integer asiento =0;
				BigDecimal saldoPorMes= new BigDecimal(0.00);
				BigDecimal saldoPorMes2= new BigDecimal(0.00);
				
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        asientoContableTO = new AsientoContableTO();
			       
					asientoContableTO.setCcddId(new Integer(objChofer[0].toString()));
			        asientoContableTO.setCcssDesc(objChofer[1].toString());
			        asientoContableTO.setNroEjercicio(new Integer(objChofer[2].toString()));
			        asientoContableTO.setNroAsiento(new Integer(objChofer[3].toString()));
			        asientoContableTO.setCuenta(objChofer[4].toString().trim());
			        asientoContableTO.setDebHab(objChofer[5].toString());
			        asientoContableTO.setValor(new BigDecimal(objChofer[6].toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
			        asientoContableTO.setLeyenda(objChofer[7].toString());
			        
			        if(objChofer[8]!=null){	        	
			        	asientoContableTO.setFecha(objChofer[8].toString());
			        	auxFechaA=asientoContableTO.getFecha().substring(2, 4);
			        	auxFechaM=asientoContableTO.getFecha().substring(4, 6);
			        	auxFechaD=asientoContableTO.getFecha().substring(6, 8);
			        	asientoContableTO.setFecha(auxFechaD+"/"+auxFechaM+"/"+auxFechaA);
			        }
			        if(objChofer[18]!=null){			        	
			        	asientoContableTO.setHora(objChofer[18].toString());
			        }
			        
			        if(objChofer[9]!=null){
			        	asientoContableTO.setCuentaDesc(objChofer[9].toString());			        	
			        }
			        
			       if(new Integer(objChofer[10].toString()).equals(new Integer (0) ))
			          asientoContableTO.setNroSuc(null);
			       else{
			    	   asientoContableTO.setNroSuc(new Integer(objChofer[10].toString()));
			       }
			       
			        asientoContableTO.setNroComp(new Integer(objChofer[11].toString()));
			        if(objChofer[12]!=null){
			        	  asientoContableTO.setTipoComp(objChofer[12].toString().trim());
			        	  asientoContableTO.setLetra(objChofer[13].toString().trim());
			        	  
				       //System.out.println(asientoContableTO.getTipoComp());
				        if(asientoContableTO.getTipoComp().trim().equals("FC")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("factura_factura_label"));     	
				        }else if(asientoContableTO.getTipoComp().trim().equals("NC")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("nota_credito_label"));				        	 
				        }else if(asientoContableTO.getTipoComp().trim().equals("ND")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("nota_debito_label"));
				        }else if(asientoContableTO.getTipoComp().trim().equals("REM")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("remito_label_x"));
				        }else if(asientoContableTO.getTipoComp().trim().equals("REC")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("recibo_label_x"));
				        }else if(asientoContableTO.getTipoComp().trim().equals("RECAN")){
				        	asientoContableTO.setTipoComprobante(mensajeria.getMessage().getString("recibo_anulado_label_x"));
				        }else if(asientoContableTO.getTipoComp().trim().equals("CHEQUE")){
				        	asientoContableTO.setTipoComprobante("CHEQUE");
				        }
				        
			        }
			        
			        
			        asientoContableTO.setCodClienteAlfa(objChofer[14].toString().trim());
			        asientoContableTO.setDescripcionCliente(objChofer[15].toString().trim());
			        asientoContableTO.setIdCuenta(new Integer(objChofer[19].toString()));
			        if(asientoContableTO.getTipoComp().trim().equals("CHEQUE")){
			          asientoContableTO.setComprobanteAsociado("Recibo: "+objChofer[16].toString().trim());
			        }
			        
			        
			        if(objChofer[17]!=null && !new Integer(objChofer[17].toString()).equals(new Integer(0))){
			          asientoContableTO.setNroRendicion(objChofer[17].toString().trim());
			        }
			       
			        
			        if(!asiento.equals(asientoContableTO.getNroAsiento())){			        	
			        	asientoContableTOSaldo= new AsientoContableTO();
			        	asientoContableTOSaldo.setCuentaDesc("Total de Asiento");
			        	asientoContableTOSaldo.setValorDebe(axuSaldo.abs());
			        	asientoContableTOSaldo.setValorHaber(axuSaldo2.abs());
			        	asientoContableTOSaldo.setNroAsiento(null);
			        	asientoContableTOSaldo.setUltimo(new Integer(1));
			        	lstPorFiltro.add(asientoContableTOSaldo);
			        	asiento = 0;
			        	saldoPorMes = saldoPorMes.abs().add(axuSaldo.abs());
			        	saldoPorMes2 = saldoPorMes2.abs().add(axuSaldo2.abs());
			        	
			        	axuSaldo= new BigDecimal(0.00);
			        	axuSaldo2= new BigDecimal(0.00);			        	
			        }
			       
			        
			        if(asientoContableTO.getDebHab().equals("D")){
				        	 axuSaldo = (axuSaldo.add(asientoContableTO.getValor())).setScale(2, BigDecimal.ROUND_HALF_UP);
				     }else if(asientoContableTO.getDebHab().equals("H")){
				        	axuSaldo2 = (axuSaldo2.subtract(asientoContableTO.getValor())).setScale(2, BigDecimal.ROUND_HALF_UP);
				     }  
				        
				        asiento = asientoContableTO.getNroAsiento();				        
				        
				        lstPorFiltro.add(asientoContableTO);
			       
					
				}
								
				if(lstPorFiltro.size()>0){
					asientoContableTOSaldo= new AsientoContableTO();
		        	asientoContableTOSaldo.setCuentaDesc("Total de Asiento");
		        	asientoContableTOSaldo.setValorDebe(axuSaldo.abs());
		        	asientoContableTOSaldo.setValorHaber(axuSaldo2.abs());
		        	asientoContableTOSaldo.setNroAsiento(null);
		        	asientoContableTOSaldo.setUltimo(new Integer(1));
		        	lstPorFiltro.add(asientoContableTOSaldo);
		        	saldoPorMes = saldoPorMes.add(axuSaldo.abs());
		        	saldoPorMes2 = saldoPorMes2.add(axuSaldo2.abs());

		        	//saldo por mes
		        	asientoContableTOSaldo= new AsientoContableTO();
		        	asientoContableTOSaldo.setCuentaDesc("Total del Periodo");
		        	asientoContableTOSaldo.setValorDebe(saldoPorMes.abs().setScale(2, BigDecimal.ROUND_HALF_UP));
		        	asientoContableTOSaldo.setValorHaber(saldoPorMes2.abs().setScale(2, BigDecimal.ROUND_HALF_UP));
		        	asientoContableTOSaldo.setNroAsiento(null);
		        	asientoContableTOSaldo.setUltimo(new Integer(1));
		        	lstPorFiltro.add(asientoContableTOSaldo);
		        	
					lstPorFiltro.remove(0);
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
	
	
	public List getSumasSaldos(Date fechaDesde, Date fechaHasta , Integer ccss) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();		
			
			if(fechaHasta==null && fechaDesde!=null) fechaHasta=fechaDesde;	
			if(fechaDesde==null && fechaHasta!=null) fechaDesde=fechaHasta;	
			
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);	
			params.put(Const.PARAM_COD_CCSS, ccss);		
					
			Query query = this.getNamedQuery(FIND_SUMAS_SALDOS, params, session);
			lstPorFiltro = query.list();			
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				AsientoContableTO asientoContableTO= new AsientoContableTO();
				Object[] objChofer= null;
								
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        asientoContableTO = new AsientoContableTO();
			       
					
			        asientoContableTO.setCuenta(objChofer[0].toString());			       
			        asientoContableTO.setValor(new BigDecimal(objChofer[3].toString()));			       
			        if(objChofer[4]!=null){
			        	asientoContableTO.setCuentaDesc(objChofer[4].toString());			        	
			        }       
			       
			        lstPorFiltro.add(asientoContableTO);
					
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
	
	
	public List getAsientosConDiferencias(Integer asientoDesde,Integer asientoHasta, Integer ccss) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			List lstAsientosConDiff= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(asientoHasta == null) asientoHasta=new Integer("999999999");			
			if(asientoDesde== null) asientoDesde=0;			
			if(ccss==null)ccss=-1; //traer todos los ccss
			
			params.put(Const.PARAM_NRO_ASIENTO_DESDE, asientoDesde);
			params.put(Const.PARAM_NRO_ASIENTO_HASTA, asientoHasta);			
			params.put(Const.PARAM_COD_CCSS, ccss);		
					
			Query query = this.getNamedQuery(FIND_ASIENTOS_POR_CCSS_NROEJER_NROASIENTO, params, session);
			lstPorFiltro = query.list();	
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				AsientoContableTO asientoContableTO= new AsientoContableTO();
				Object[] objChofer= null;		
				
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        asientoContableTO = new AsientoContableTO();
			       
					asientoContableTO.setCcddId(new Integer(objChofer[0].toString()));
			        asientoContableTO.setCcssDesc(objChofer[1].toString());
			        asientoContableTO.setNroEjercicio(new Integer(objChofer[2].toString()));
			        asientoContableTO.setNroAsiento(new Integer(objChofer[3].toString()));			       
			        asientoContableTO.setDebHab(objChofer[4].toString());
			        asientoContableTO.setValor(new BigDecimal(objChofer[5].toString()));
			        System.out.println("asiento"+asientoContableTO.getNroAsiento());
			        
			        if(asientoContableTO.getDebHab().trim().equals("D")) {
			        	asientoContableTO.setValorDebe(asientoContableTO.getValor().setScale(2, BigDecimal.ROUND_HALF_UP));
				     }else if(asientoContableTO.getDebHab().trim().equals("H")){
					    	((AsientoContableTO)lstPorFiltro.get(lstPorFiltro.size()-1)).setValorHaber(asientoContableTO.getValor().setScale(2, BigDecimal.ROUND_HALF_UP));
					    	((AsientoContableTO)lstPorFiltro.get(lstPorFiltro.size()-1)).setSaldo(((AsientoContableTO)lstPorFiltro.get(lstPorFiltro.size()-1)).getValorDebe().setScale(2, BigDecimal.ROUND_HALF_UP).subtract(((AsientoContableTO)lstPorFiltro.get(lstPorFiltro.size()-1)).getValorHaber().setScale(2, BigDecimal.ROUND_HALF_UP)));
				     }   
				     
				     //las filas desde la bd en pares d,h,d,h,d,h. Solo debo armar una sola linea para mostrar
				     if(asientoContableTO.getDebHab().trim().equals("D")) {
				    	 lstPorFiltro.add(asientoContableTO);
				     }
				     
				     if(asientoContableTO.getDebHab().trim().equals("H") && 
				    		 ((AsientoContableTO)lstPorFiltro.get(lstPorFiltro.size()-1)).getSaldo().abs().compareTo(new BigDecimal("0.009"))>0) {
				    	 lstAsientosConDiff.add(((AsientoContableTO)lstPorFiltro.get(lstPorFiltro.size()-1)));
				     }
				     
				     
					
				}
								
				
			}
			
			if(lstPorFiltro.size()==0 || lstAsientosConDiff.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}		
	        
			return lstAsientosConDiff;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public List getAsientosConDiferenciasv2(Integer asientoDesde,Integer asientoHasta, Integer ccss) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			List lstAsientosConDiff= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(asientoHasta == null) asientoHasta=new Integer("999999999");			
			if(asientoDesde== null) asientoDesde=0;			
			if(ccss==null)ccss=-1; //traer todos los ccss
			
			params.put(Const.PARAM_NRO_ASIENTO_DESDE, asientoDesde);
			params.put(Const.PARAM_NRO_ASIENTO_HASTA, asientoHasta);			
			params.put(Const.PARAM_COD_CCSS, ccss);		
					
			Query query = this.getNamedQuery(FIND_ASIENTOS_POR_CCSS_NROEJER_NROASIENTO_v2, params, session);
			lstPorFiltro = query.list();	
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				AsientoContableTO asientoContableTO= new AsientoContableTO();
				Object[] objChofer= null;		
				
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        asientoContableTO = new AsientoContableTO();
			       
					asientoContableTO.setCcddId(new Integer(objChofer[0].toString()));
			        asientoContableTO.setCcssDesc(objChofer[1].toString());
			        asientoContableTO.setNroEjercicio(new Integer(objChofer[2].toString()));
			        asientoContableTO.setNroAsiento(new Integer(objChofer[3].toString()));			       
			        asientoContableTO.setDebHab(objChofer[4].toString());
			        asientoContableTO.setValorDebe(new BigDecimal(objChofer[5].toString()));
			        asientoContableTO.setDebHab2(objChofer[6].toString());
			        asientoContableTO.setValorHaber(new BigDecimal(objChofer[7].toString()));
			        asientoContableTO.setSaldo(new BigDecimal(objChofer[8].toString()));
			       lstPorFiltro.add(asientoContableTO);	     
					
				}
								
				
			}
			
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
}