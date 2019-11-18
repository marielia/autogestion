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

import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.MctasCtes;
import com.refinor.extranet.data.base.BaseMctasCtesDAO;
import com.refinor.extranet.to.CtasCtesTO;
import com.refinor.extranet.to.PagoAdelTO;
import com.refinor.extranet.to.RemitoTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class MctasCtesDAO extends BaseMctasCtesDAO implements com.refinor.extranet.data.dao.iface.MctasCtesDAO {

	public MctasCtesDAO () {}
	
	public MctasCtesDAO (Session session) {
		super(session);
	}

	
	public static String FIND_FACTURAS_IMPAGAS_POR_CLIENTE= "findFacturasImpagasPorCliente";
	public static String FIND_REMITOS_IMPAGAS_POR_CLIENTE= "findRemitosImpagasPorCliente";
	public static String FIND_RECIBOS_NO_APLICADOS_A_FACTURAS= "findRecibosNoAplicadosAFacturas";	
	public static String FIND_DOCUMENTOS_IMPAGOS_POR_CLIENTE= "findDocumentoACuentaImpagosPorCliente";
	public static String FIND_TODAS_LAS_FACTURAS_AGRUPADAS= "findTodasLasFacturasAgrupadas";
	public static String FIND_NC_APLICADA_FAC_CLIENTE= "findNCAplicaFacturaCliente";
	public static String FIND_PAGO_ADEL_APLICADA_FAC_CLIENTE= "findPagoAdelAplicaFacturaCliente";
	public static String FIND_NC_REMITO_NO_APLICADO= "findNCyReciboNoAplicados";
	public static String FIND_NC_NO_APLICADO= "findNCNoAplicados";
	public static String FIND_REMITO_MCTACTE= "findTotalRemitos";
	
	public List getDocumentosConSaldo(String codCliente) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstDocumentosCtaCte= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();	
			
			//NC o REC no aplicados - es decir pendientes de aplicar 
			//query = this.getNamedQuery(MctasCtesDAO.FIND_RECIBOS_NO_APLICADOS_A_FACTURAS, params, session);
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put(Const.PARAM_COD_CLIENTE, -1);
			params2.put(Const.PARAM_FECHA_DESDE, null);
			params2.put(Const.PARAM_FECHA_HASTA, null);	
			params2.put(Const.PARAM_COD_CLIENTE_ALFA, codCliente);
			Query query = this.getNamedQuery("buscarNCConSaldo", params2, session);			
			lstDocumentosCtaCte= query.list();
			
			//facturas
			if(lstDocumentosCtaCte.size()>0){
				Iterator itRemitos = lstDocumentosCtaCte.iterator();
				lstDocumentosCtaCte= new ArrayList();
				CtasCtesTO ctasCtesTO= new CtasCtesTO();
				Object[] objRemito= null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next(); 
			        ctasCtesTO=new CtasCtesTO();
			        
			       
			        ctasCtesTO.setTipoComprobante(objRemito[8].toString());
			        ctasCtesTO.setCliDescripcion(objRemito[6].toString());
			        ctasCtesTO.setCodClienteAlfa(objRemito[7].toString());
			        if(objRemito[1]!=null){
			        	 ctasCtesTO.setFecha(sdf.format(objRemito[1]));	
			        }
			        
			        if(objRemito[11]!=null){
			        	ctasCtesTO.setVencimiento(sdf.format(objRemito[11]));
			        }
			        
			        if(objRemito[4]!=null){
			        	ctasCtesTO.setComprobanteNumero(new Integer(objRemito[4].toString()));
			        }
			        
			        if(!new Integer(objRemito[3].toString()).equals(new Integer(0))){
			        	ctasCtesTO.setSucursal(new Integer(objRemito[3].toString()));
			        }
			        
			        ctasCtesTO.setCodCliente(new Integer(objRemito[14].toString()));
			        
			        //saldo
			        ctasCtesTO.setImporte(new BigDecimal(objRemito[15].toString()));
			        
			        //total de la factura
			        ctasCtesTO.setNetoFactura(new BigDecimal(objRemito[5].toString()));

			        
			        if(ctasCtesTO.getTipoComprobante().trim().equals("PAGOADEL")){			        	
				        	PagoAdelTO pagoAdelTO = new PagoAdelTO();
				        	if(!session.isOpen()) session = (new MfacturasVDAO()).getSession();				        	
				        	MfacturasVDAO mfacturasVDAO = new MfacturasVDAO(session);
				        	pagoAdelTO =  mfacturasVDAO.ObtenerPagoAdel(ctasCtesTO.getCodCliente(),ctasCtesTO.getComprobanteNumero(),ctasCtesTO.getImporte());
				        	
				        	ctasCtesTO.setFecha(pagoAdelTO.getFecha());
				        	//mFacturaVTO.setTipo(pagoAdelTO.getTipo());
				        	
				        	if(pagoAdelTO.getNroSucursal()!=null) {
				        		ctasCtesTO.setSucursal(pagoAdelTO.getNroSucursal());
				        	}else{
				        		ctasCtesTO.setSucursal(0);
				        	}
				        	
				        	ctasCtesTO.setComprobanteNumero(pagoAdelTO.getNroFactura());
				        	
				        	ctasCtesTO.setNetoFactura(pagoAdelTO.getTotal());
				        	
				        	ctasCtesTO.setVencimiento(pagoAdelTO.getFeVto());	
				        	
				        	ctasCtesTO.setCodCliente(pagoAdelTO.getCodCliente());
				        	//ctasCtesTO.setImporte(pagoAdelTO.getSaldo());
				        	if( pagoAdelTO.getTipo().equals("Nota de Crédito No Aplicada")){
				        		ctasCtesTO.setTipoComprobante(Const.NC_NO_APLICADO);	
				        	}else if( pagoAdelTO.getTipo().equals("Recibo No Aplicado")){
				        		ctasCtesTO.setTipoComprobante(Const.PAGO_NO_APLICADO_RECIBO);	
				        	}
				        	
				        	
				        }
			        
			        
			        
			        if(ctasCtesTO.getTipoComprobante().trim().equals("FC")){
			        	ctasCtesTO.setComprobanteDescripcion(Const.FACTURA+" "+objRemito[19].toString());
			        }else if(ctasCtesTO.getTipoComprobante().trim().equals("ND")){
			        	ctasCtesTO.setComprobanteDescripcion(Const.NOTA_DEBITO);//+" "+objRemito[3].toString()
			        }else if( 		ctasCtesTO.getTipoComprobante().trim().equals(Const.PAGO_NO_APLICADO_RECIBO) ){
			        	ctasCtesTO.setComprobanteDescripcion(Const.RECIBO_NO_APLICADO);
			        }else if(ctasCtesTO.getTipoComprobante().trim().equals(Const.NC_NO_APLICADO)){
			        	ctasCtesTO.setComprobanteDescripcion(Const.NC_NO_APLICADO);
			        }else if(ctasCtesTO.getTipoComprobante().trim().equals(Const.VALOR_POSDATADO)){
			        	ctasCtesTO.setComprobanteDescripcion(Const.VALOR_POSDATADO);
			        }
			        
			        //importe adeudado
			        if(ctasCtesTO.getTipoComprobante().trim().equals("FC") || ctasCtesTO.getTipoComprobante().trim().equals("ND")){
			        	ctasCtesTO.setImporte(new BigDecimal(objRemito[15].toString()));
			        }else if(ctasCtesTO.getTipoComprobante().trim().equals(Const.REMITO)){
			        	ctasCtesTO.setImporte(new BigDecimal(objRemito[5].toString()));
			        }else if(ctasCtesTO.getTipoComprobante().trim().equals(Const.VALOR_POSDATADO)){
			        	ctasCtesTO.setImporte(new BigDecimal(objRemito[5].toString()));
			        }
			        
			        ctasCtesTO.setEstadoInt(-1);			        
			        	if(ctasCtesTO.getTipoComprobante().trim().equals("FC") 
			        			|| ctasCtesTO.getTipoComprobante().trim().equals("NC")
			        			|| ctasCtesTO.getTipoComprobante().trim().equals("ND")
			        			||  ctasCtesTO.getTipoComprobante().trim().equals(Const.NC_NO_APLICADO)
			        			){
						        	
			        	if(ctasCtesTO.getVencimiento()!=null){
			        	 ctasCtesTO.setEstadoInt(obtenerIndiceImporteVencido(sdf.parse(ctasCtesTO.getVencimiento())));			        	 
			        	}
			        	 
					    
					       
			        }
			        		        	
			        ctasCtesTO.setTopeCredito(new BigDecimal(objRemito[18].toString())); 
			       lstDocumentosCtaCte.add(ctasCtesTO);
			        
				}				
				
			}else if(lstDocumentosCtaCte.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}
		
	        
			return lstDocumentosCtaCte;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public List getFacturasNoAbonadas(String codCliente,int mostrarDocNoAplicados) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstDocumentosCtaCte= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();		
			
			if(codCliente==null)codCliente="%";
			params.put(Const.PARAM_COD_CLIENTE_ALFA, codCliente);		
			
			//FAC, NC ; ND impagas
			Query query = this.getNamedQuery(MctasCtesDAO.FIND_FACTURAS_IMPAGAS_POR_CLIENTE, params, session);
			lstDocumentosCtaCte= query.list();
			
			//NC o REC no aplicados - es decir pendientes de aplicar 
			//query = this.getNamedQuery(MctasCtesDAO.FIND_RECIBOS_NO_APLICADOS_A_FACTURAS, params, session);
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put(Const.PARAM_COD_CLIENTE, -1);
			params2.put(Const.PARAM_FECHA_DESDE, null);
			params2.put(Const.PARAM_FECHA_HASTA, null);	
			params2.put(Const.PARAM_COD_CLIENTE_ALFA, codCliente);
			query = this.getNamedQuery("buscarNCyRCNoAplicados", params2, session);
			
			List recibos= query.list();
			
			if(recibos!=null){
				lstDocumentosCtaCte.addAll(recibos);
			}
			
			
			//facturas
		//	if(lstDocumentosCtaCte.size()>0){
				Iterator itRemitos = lstDocumentosCtaCte.iterator();
				lstDocumentosCtaCte= new ArrayList();
				CtasCtesTO ctasCtesTO= new CtasCtesTO();
				Object[] objRemito= null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next(); 
			        ctasCtesTO=new CtasCtesTO();
			        
			       
			        ctasCtesTO.setTipoComprobante(objRemito[8].toString());
			        ctasCtesTO.setCliDescripcion(objRemito[6].toString());
			        ctasCtesTO.setCodClienteAlfa(objRemito[7].toString());
			        if(objRemito[1]!=null){
			        	 ctasCtesTO.setFecha(sdf.format(objRemito[1]));	
			        }
			        
			        if(objRemito[11]!=null){
			        	ctasCtesTO.setVencimiento(sdf.format(objRemito[11]));
			        }
			        
			        if(objRemito[4]!=null){
			        	ctasCtesTO.setComprobanteNumero(new Integer(objRemito[4].toString()));
			        }
			        
			        if(!new Integer(objRemito[3].toString()).equals(new Integer(0))){
			        	ctasCtesTO.setSucursal(new Integer(objRemito[3].toString()));
			        }
			        
			        ctasCtesTO.setCodCliente(new Integer(objRemito[14].toString()));
			        
			        //saldo
			        ctasCtesTO.setImporte(new BigDecimal(objRemito[15].toString()));
			        
			        //total de la factura
			        ctasCtesTO.setNetoFactura(new BigDecimal(objRemito[5].toString()));

			        
			        if(ctasCtesTO.getTipoComprobante().trim().equals("PAGOADEL")){			        	
				        	PagoAdelTO pagoAdelTO = new PagoAdelTO();
				        	if(!session.isOpen()) session = (new MfacturasVDAO()).getSession();				        	
				        	MfacturasVDAO mfacturasVDAO = new MfacturasVDAO(session);
				        	pagoAdelTO =  mfacturasVDAO.ObtenerPagoAdel(ctasCtesTO.getCodCliente(),ctasCtesTO.getComprobanteNumero(),ctasCtesTO.getImporte());
				        	
				        	ctasCtesTO.setFecha(pagoAdelTO.getFecha());
				        	//mFacturaVTO.setTipo(pagoAdelTO.getTipo());
				        	
				        	if(pagoAdelTO.getNroSucursal()!=null) {
				        		ctasCtesTO.setSucursal(pagoAdelTO.getNroSucursal());
				        	}else{
				        		ctasCtesTO.setSucursal(0);
				        	}
				        	
				        	ctasCtesTO.setComprobanteNumero(pagoAdelTO.getNroFactura());
				        	
				        	ctasCtesTO.setNetoFactura(pagoAdelTO.getTotal());
				        	/*mFacturaVTO.setCliDescripcion(pagoAdelTO.getCliDescripcion());
				        	mFacturaVTO.setCodClienteAlfa(pagoAdelTO.getCodClienteAlfa());*/
				        	//ctasCtesTO.setPagoAplicado(pagoAdelTO.getPagoAplicado());
				        	//ctasCtesTO.setNetoFactura(pagoAdelTO.getNetoFactura());
				        	
				        	ctasCtesTO.setVencimiento(pagoAdelTO.getFeVto());	
				        	
				        	ctasCtesTO.setCodCliente(pagoAdelTO.getCodCliente());
				        	//ctasCtesTO.setImporte(pagoAdelTO.getSaldo());
				        	if( pagoAdelTO.getTipo().equals("Nota de Crédito No Aplicada")){
				        		ctasCtesTO.setTipoComprobante(Const.NC_NO_APLICADO);	
				        	}else if( pagoAdelTO.getTipo().equals("Recibo No Aplicado")){
				        		ctasCtesTO.setTipoComprobante(Const.PAGO_NO_APLICADO_RECIBO);	
				        	}
				        	
				        	
				        }
			        
			        
			        
			        if(ctasCtesTO.getTipoComprobante().trim().equals("FC")){
			        	ctasCtesTO.setComprobanteDescripcion(Const.FACTURA+" "+objRemito[19].toString());
			        }/*else if(objRemito[8].toString().trim().equals("NC")){
			        	ctasCtesTO.setComprobanteDescripcion(Const.NOTA_CREDITO);//+" "+objRemito[3].toString()
			        }*/else if(ctasCtesTO.getTipoComprobante().trim().equals("ND")){
			        	ctasCtesTO.setComprobanteDescripcion(Const.NOTA_DEBITO);//+" "+objRemito[3].toString()
			        }/*else if(ctasCtesTO.getTipoComprobante().trim().equals(Const.REMITO)){
			        	ctasCtesTO.setComprobanteDescripcion(Const.REMITO);
			        }*/else if(/*objRemito[8].toString().trim().equals(Const.RECIBO_NO_APLICADO) ||*/
			        		ctasCtesTO.getTipoComprobante().trim().equals(Const.PAGO_NO_APLICADO_RECIBO) ){
			        	ctasCtesTO.setComprobanteDescripcion(Const.RECIBO_NO_APLICADO);
			        }else if(ctasCtesTO.getTipoComprobante().trim().equals(Const.NC_NO_APLICADO)){
			        	ctasCtesTO.setComprobanteDescripcion(Const.NC_NO_APLICADO);
			        }else if(ctasCtesTO.getTipoComprobante().trim().equals(Const.VALOR_POSDATADO)){
			        	ctasCtesTO.setComprobanteDescripcion(Const.VALOR_POSDATADO);
			        }/*else if(ctasCtesTO.getTipoComprobante().trim().equals("PAGOADEL")){
			        	ctasCtesTO.setComprobanteDescripcion("PAGOADEL");
			        }*/
			        
			        
			        
			        
			        //importe adeudado
			        if(ctasCtesTO.getTipoComprobante().trim().equals("FC") || ctasCtesTO.getTipoComprobante().trim().equals("ND")){
			        	ctasCtesTO.setImporte(new BigDecimal(objRemito[15].toString()));
			        }/*else if(objRemito[8].toString().trim().equals("NC")){
			        	ctasCtesTO.setImporte(new BigDecimal(objRemito[15].toString()));
			        }*/else if(ctasCtesTO.getTipoComprobante().trim().equals(Const.REMITO)){
			        	ctasCtesTO.setImporte(new BigDecimal(objRemito[5].toString()));
			        }/*else if(objRemito[8].toString().trim().equals(Const.RECIBO_NO_APLICADO)){
			        	ctasCtesTO.setImporte(new BigDecimal(objRemito[5].toString()));
			        }*/else if(ctasCtesTO.getTipoComprobante().trim().equals(Const.PAGO_NO_APLICADO_RECIBO) 
			        		/*|| ctasCtesTO.getTipoComprobante().toString().trim().equals("PAGOADEL") */){
			        	//ctasCtesTO.setNetoFactura(ctasCtesTO.getNetoFactura().multiply(new BigDecimal(-1)));
			        	//ctasCtesTO.setImporte(new BigDecimal(objRemito[15].toString()));
			        }else if(ctasCtesTO.getTipoComprobante().trim().equals(Const.NC_NO_APLICADO)){
			        	 //importe adeudado - saldo
			        	// ctasCtesTO.setImporte(new BigDecimal(objRemito[15].toString()));
//					      total de la factura
			        	// ctasCtesTO.setNetoFactura(new BigDecimal(objRemito[5].toString()));
			        	 
			        }else if(ctasCtesTO.getTipoComprobante().trim().equals(Const.VALOR_POSDATADO)){
			        	ctasCtesTO.setImporte(new BigDecimal(objRemito[5].toString()));
			        }
			        
			        ctasCtesTO.setEstadoInt(-1);
			       /* if(!objRemito[8].toString().trim().equals(Const.RECIBO_NO_APLICADO) 
			        		&& !ctasCtesTO.getTipoComprobante().trim().equals(Const.PAGO_NO_APLICADO_RECIBO) 
			        		&& !objRemito[8].toString().trim().equals(Const.NC_NO_APLICADO)
			        		&& !ctasCtesTO.getTipoComprobante().trim().equals(Const.VALOR_POSDATADO)
			        		&& !ctasCtesTO.getTipoComprobante().trim().equals("PAGOADEL")){*/
			        
			        	if(ctasCtesTO.getTipoComprobante().trim().equals("FC") 
			        			|| ctasCtesTO.getTipoComprobante().trim().equals("NC")
			        			|| ctasCtesTO.getTipoComprobante().trim().equals("ND")
			        			||  ctasCtesTO.getTipoComprobante().trim().equals(Const.NC_NO_APLICADO)
			        			){
						        	
			        	if(ctasCtesTO.getVencimiento()!=null){
			        	 ctasCtesTO.setEstadoInt(obtenerIndiceImporteVencido(sdf.parse(ctasCtesTO.getVencimiento())));			        	 
			        	}
			        	 
					     if(ctasCtesTO.getEstadoInt()==1)
					        	ctasCtesTO.setEstadoStr(mensajeria.getMessage().getString("fact_venc_30_label"));
					     else if(ctasCtesTO.getEstadoInt()==2)
					        	ctasCtesTO.setEstadoStr(mensajeria.getMessage().getString("fact_venc_mas_30_label"));
					     else if(ctasCtesTO.getEstadoInt()==0)
					        	ctasCtesTO.setEstadoStr(mensajeria.getMessage().getString("fact_no_venc_label"));
					       
			        }
			        		        	
			        ctasCtesTO.setTopeCredito(new BigDecimal(objRemito[18].toString()));
			     
			        if(ctasCtesTO.getImporte().abs().compareTo(new BigDecimal(0.10))>0){
			        	lstDocumentosCtaCte.add(ctasCtesTO);
			        }
				}				
				
			//}else if(lstDocumentosCtaCte.size()==0){
			//	throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			//}
		
	        
			return lstDocumentosCtaCte;
		}
		/*catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}*/catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	private String obtenerTipoComprobante(String tipo){
		if(tipo.equals("PagoAdel")){
			return "RECIBO";		
		}else if(tipo.equals("NC")){
			return "NOTA DE CREDITO";
		}else if(tipo.equals("ND")){
			return "NOTA DE DEBITO";
		}else{
			return "";
		}
	}
	private int obtenerIndiceImporteVencido(Date fechaVencimiento){
		int index=0;				
		long cantDias= getCantidadDeDias(new Date(), fechaVencimiento);
		
		if(cantDias>0 && cantDias<=30){
			//vencidas entre 0 y 30 dias.
			 //System.out.println("1");
			index=1;			
		}else if(cantDias>30){
			//vencidas mayor a 30 dias.
			//System.out.println("2");
			index=2;			
		}else if(cantDias<=0){
			//no vencidas
			//System.out.println("4");
			index=0;
		}		
		
		 //System.out.println("index --> "+index);
		return index;
	}
	
	private long getCantidadDeDias(Date fechaInicial,Date fechaFinal){
		 long ldias=0;
	 try {			    
		    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");			
		    String fechaInicioString = df.format(fechaInicial);	   
			fechaInicial = df.parse(fechaInicioString);			    
		    String fechaFinalString = df.format(fechaFinal);
		    fechaFinal = df.parse(fechaFinalString);	
		    df = new SimpleDateFormat("MM");
		    
		    long fechaInicialMs = fechaInicial.getTime();
		    long fechaFinalMs = fechaFinal.getTime();		   
		    long diferencia = fechaInicialMs - fechaFinalMs;		   		    
		    ldias = diferencia / (1000 * 60 * 60 * 24);	    		    
		    
		    //System.out.println("cantDias --> "+ldias);
		    
	 } catch (Exception e) {			
			e.printStackTrace();
	}
	    return ldias;
	}
	
	public List getNotaCreditoAplicadaFacturaCliente(Integer nroOperacion, Integer nroSucursal, Integer codCliente) throws DataAccessErrorException
	{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();					
					
			params.put(Const.PARAM_NRO_OPERACION, nroOperacion);
			params.put(Const.PARAM_NRO_SUCURSAL, nroSucursal);
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			
			Query query = this.getNamedQuery(FIND_NC_APLICADA_FAC_CLIENTE, params, session);
			lstPorFiltro = query.list();	        
			return lstPorFiltro;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public static String FIND_PAGO_ADEL_APLICADA_FAC_CLIENTE_V2= "findPagoAdelAplicaFacturaClienteV2";
	
	public List getPagoAdelantadoAplicadaFacturaClienteV2(Integer nroOperacion, Integer nroSucursal, Integer codCliente) throws DataAccessErrorException
	{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();					
					
			params.put(Const.PARAM_NRO_OPERACION, nroOperacion);
			params.put(Const.PARAM_NRO_SUCURSAL, nroSucursal);
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			//params.put(Const.PARAM_TIPO_OPERACION, Const.PARAM_APLICAADEL_FACTURA_CLIENTE);
			
			Query query = this.getNamedQuery(FIND_PAGO_ADEL_APLICADA_FAC_CLIENTE_V2, params, session);
			lstPorFiltro = query.list();	        
			return lstPorFiltro;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public BigDecimal getTotalRemitosAFacturar(int codCliente) throws DataAccessErrorException,NoExistenItemsException{
		BigDecimal total= new BigDecimal(0.00);
		try{
				
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(!session.isOpen()) session = (new MpedidosDAO()).getSession();		
			
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			
			Query query = this.getNamedQuery(MctasCtesDAO.FIND_REMITO_MCTACTE, params, session);
			lstRemitos= query.list();
			
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();				
						
				while(itRemitos.hasNext()){						
			            total= new BigDecimal(itRemitos.next().toString());					
				}				
			}
	        
			return total;
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		return total;
	}
	
	
	
	public BigDecimal getTotalSaldoPorComprobanteCliente(int codCliente) throws DataAccessErrorException,NoExistenItemsException{
		BigDecimal total= new BigDecimal(0.00);
		try{
				
			List lst= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(!session.isOpen()) session = (new MpedidosDAO()).getSession();		
			
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			
			Query query = this.getNamedQuery("findSaldoPorComprobanteCliente", params, session);
			lst= query.list();
			
			
			if(lst.size()>0){
				Iterator itRemitos = lst.iterator();				
						
				while(itRemitos.hasNext()){						
			            total= new BigDecimal(itRemitos.next().toString());					
				}				
			}
	        
			return total;
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		return total;
	}
	

}