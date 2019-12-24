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

//import sun.management.snmp.util.MibLogger;
import com.refinor.extranet.data.MfacturasV;
import com.refinor.extranet.data.MfacturasVId;
import com.refinor.extranet.data.Mrecibo;
import com.refinor.extranet.data.base.BaseMfacturasVDAO;
import com.refinor.extranet.to.CtasCtesTO;
import com.refinor.extranet.to.DocumentoAplicadoTO;
import com.refinor.extranet.to.MFacturaVTO;
import com.refinor.extranet.to.MImpuestoTO;
import com.refinor.extranet.to.MReciboTO;
import com.refinor.extranet.to.MovimientoStockTO;
import com.refinor.extranet.to.PagoAdelTO;
import com.refinor.extranet.to.PercepcionesIIBBTO;
import com.refinor.extranet.to.PercepcionesIVATO;
import com.refinor.extranet.to.RendicionPendienteTO;
import com.refinor.extranet.to.RendicionTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.DataUtil;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
//import com.sun.accessibility.internal.resources.accessibility;


public class MfacturasVDAO extends BaseMfacturasVDAO implements com.refinor.extranet.data.dao.iface.MfacturasVDAO {

	public MfacturasVDAO () {}
	
	public MfacturasVDAO (Session session) {
		super(session);
	}
	
	public static String FIND_FACTURAS_BY_CLIENTE_FECHAS= "findFacturasPorClienteYFechas";
	public static String FIND_FACTURAS_NOABONADAS_BY_CLIENTE_FECHAS= "findFacturasNoAbonadasPorClienteYFechas";
	public static String FIND_FACTURAS_NOABONADAS_2_BY_CLIENTE_FECHAS= "findFacturasNoAbonadas2PorClienteYFechas";
	public static String FIND_COBRANZAS_BY_CLIENTE_FECHAS= "findCobranzasPorClienteYFechas";
	public static String FIND_FACTURAS_BY_RECIBO_CLIENTE= "findFacturaPorReciboCliente";
	
	public static String FIND_CCSS_X_IMPORTES= "findCCSSeImportes";
	public static String FIND_FECHA_MAXIMA_CCSS_FACTURA= "findFechaMaximaCCSSFactura";
	public static String FIND_RENDICIONES_POR_CCSS= "findRendicionesPorCCSS";
	public static String FIND_FACTURAS_POR_RENDICIONES_POR_CCSS= "findFacturasContadoPorNroRendicion";
	
	public static String FIND_PERCEPCIONES_PIVA_CERO= "findPercepcionesIVAPIVACero";
	public static String FIND_PERCEPCIONES_PIVA_DISTINTO_CERO= "findPercepcionesIVAPIVADistintoCero";
	
	public static String FIND_PERCEPCIONES_IIBB= "findPercepcionesIIBB";
	public static String FIND_MOVIMIENTO_STOCK= "findMovimientoStock";
	public static String FIND_STOCK= "findStockCombustible";
	public static String FIND_FACTURAS_PENDIENTES_RENDICION= "findFacturasPendientesDeRendicion";	
	
	
	public List getPercepcionesIIBB(String clienteDesde,String clienteHasta, Date fechaDesde, Date fechaHasta, int provincia ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(clienteHasta.trim().equals("") && !clienteDesde.trim().equals("")) clienteHasta=clienteDesde;
			if(fechaHasta==null && fechaDesde!=null) fechaHasta=fechaDesde;
			if(clienteHasta.trim().equals("") && clienteDesde.trim().equals("")){
				clienteHasta=null;
				clienteDesde=null;
			}
			
			
			params.put(Const.PARAM_CLIENTE_DESDE, clienteDesde);
			params.put(Const.PARAM_CLIENTE_HASTA, clienteHasta);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);			
			params.put(Const.PARAM_COD_PROVINCIA,provincia );
			
			Query query = this.getNamedQuery(MfacturasVDAO.FIND_PERCEPCIONES_IIBB, params, session);
			lstPorFiltro = query.list();			
			DataUtil dataUtil = new DataUtil();
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				PercepcionesIIBBTO percepciones= new PercepcionesIIBBTO();
				Object[] objChofer= null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        percepciones = new PercepcionesIIBBTO();
			       
			        percepciones.setCodCliente(new Integer(objChofer[0].toString()));
			        percepciones.setCodClienteAlfa(objChofer[1].toString());
			        percepciones.setDescripcionCli(objChofer[2].toString());
			        percepciones.setCuit(objChofer[3].toString());
			        percepciones.setFechaEmisionComprobante(sdf.format(objChofer[4]));
	      
			        percepciones.setCodComprobanteStr(objChofer[5].toString());
			        percepciones.setTipo(obtenerTipo(percepciones.getCodComprobanteStr()));
			        percepciones.setCodComprobanteInt(ObtenerCodComprobantes(percepciones.getCodComprobanteStr()));
			             
			        percepciones.setTipoComprobante(objChofer[6].toString());
			       		        
			        percepciones.setNroSucursal(new Integer(objChofer[7].toString()));
			        percepciones.setNroFactura(new Integer(objChofer[8].toString()));
			       
			        percepciones.setBruto(new BigDecimal(objChofer[9].toString()));
			        percepciones.setNeto(new BigDecimal(objChofer[10].toString()));
			        percepciones.setItcTasa(new BigDecimal(objChofer[11].toString()));
			        percepciones.setItc(new BigDecimal(objChofer[12].toString()));
			        percepciones.setTasa(new BigDecimal(objChofer[13].toString()));
			        percepciones.setPercepcion(new BigDecimal(objChofer[14].toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
			        percepciones.setProvincia(objChofer[16].toString());  
			        	
			        percepciones.setBaseImponible(new BigDecimal(objChofer[21].toString()));
			        percepciones.setAlicuota(new BigDecimal(objChofer[22].toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
			        percepciones.setImporteFactura(new BigDecimal(objChofer[23].toString()));
			       
			        percepciones.setCodArticulo(new Integer(objChofer[24].toString()));
			        percepciones.setDescArticulo(objChofer[25].toString());
			        percepciones.setNroIIBB(objChofer[26].toString());  
			        
			        
			        percepciones.setNroFacturaCompuesto("0"+percepciones.getCodComprobanteInt()+percepciones.getTipoComprobante()+dataUtil.rellenarConCeros(4, percepciones.getNroSucursal().toString())+dataUtil.rellenarConCeros(8, percepciones.getNroFactura().toString()));
			        percepciones.setNroCertificado(percepciones.getNroFacturaCompuesto());
			        
			        
			        if(percepciones.getAlicuota().compareTo(new BigDecimal("0.00"))>0){
			        	Double netoCalculado = (Math.round(percepciones.getPercepcion().doubleValue() * 100) / percepciones.getAlicuota().doubleValue()) ;						
			        	percepciones.setNetoCalculado(new BigDecimal(netoCalculado));
			        }
			        
			        //parchesito de iibb - comentado por factura 202 (percepcion negativa ) 09/01/2009
			     //   if(percepciones.getBaseImponible().compareTo(new BigDecimal("0.00"))>0){
			       
			        	percepciones.setBaseImponible(percepciones.getNetoCalculado());
			       // }
				 if(percepciones.getAlicuota().compareTo(new BigDecimal("0.00"))>0){
			        lstPorFiltro.add(percepciones);
				 }
					
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
	
	
	public List getPercepcionesIIBB2(String clienteDesde,String clienteHasta, Date fechaDesde, Date fechaHasta, int provincia ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(clienteHasta.trim().equals("") && !clienteDesde.trim().equals("")) clienteHasta=clienteDesde;
			if(fechaHasta==null && fechaDesde!=null) fechaHasta=fechaDesde;
			if(clienteHasta.trim().equals("") && clienteDesde.trim().equals("")){
				clienteHasta=null;
				clienteDesde=null;
			}
			
			
			params.put(Const.PARAM_CLIENTE_DESDE, clienteDesde);
			params.put(Const.PARAM_CLIENTE_HASTA, clienteHasta);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);			
			params.put(Const.PARAM_COD_PROVINCIA,provincia );
			
			Query query = this.getNamedQuery(MfacturasVDAO.FIND_PERCEPCIONES_IIBB, params, session);
			lstPorFiltro = query.list();			
			DataUtil dataUtil = new DataUtil();
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				PercepcionesIIBBTO percepciones= new PercepcionesIIBBTO();
				Object[] objChofer= null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				Integer codProvincia = 0;
				Integer codInscripcionIBCliente=0;
				Integer codActividadCliente=0;
				Integer codCategoriaIVACliente=0;
				Boolean esCombustible=new Boolean(false);
				Boolean esGasoil=new Boolean(false);
				Boolean cambiarProvincia=new Boolean(false);
				BigDecimal precioSinImpuesto=new BigDecimal(0);
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        percepciones = new PercepcionesIIBBTO();
			       
			        percepciones.setCodCliente(new Integer(objChofer[0].toString()));
			        percepciones.setCodClienteAlfa(objChofer[1].toString());
			        percepciones.setDescripcionCli(objChofer[2].toString());
			        percepciones.setCuit(objChofer[3].toString());
			        percepciones.setFechaEmisionComprobante(sdf.format(objChofer[4]));
	      
			        percepciones.setCodComprobanteStr(objChofer[5].toString());
			        percepciones.setTipo(obtenerTipo(percepciones.getCodComprobanteStr()));
			        percepciones.setCodComprobanteInt(ObtenerCodComprobantes(percepciones.getCodComprobanteStr()));
			             
			        percepciones.setTipoComprobante(objChofer[6].toString());
			       		        
			        percepciones.setNroSucursal(new Integer(objChofer[7].toString()));
			        percepciones.setNroFactura(new Integer(objChofer[8].toString()));
			       
			        percepciones.setBruto(new BigDecimal(objChofer[9].toString()));
			        percepciones.setNeto(new BigDecimal(objChofer[10].toString()));
			        
			        if(sdf.parse(percepciones.getFechaEmisionComprobante()).before(new Date("01/01/2013"))){
			            percepciones.setItcTasa(new BigDecimal(objChofer[11].toString()));
			        }else{
			        	if( objChofer[11] != null && objChofer[11] != null){
				        	precioSinImpuesto = new BigDecimal(0);
				        	precioSinImpuesto = new BigDecimal(objChofer[29].toString());
				        	percepciones.setItcTasa(new BigDecimal(objChofer[11].toString()).add(precioSinImpuesto));
			        	}
			        }
			        percepciones.setItc(new BigDecimal(objChofer[12].toString()));
			        percepciones.setTasa(new BigDecimal(objChofer[13].toString()));
			        percepciones.setPercepcion(new BigDecimal(objChofer[14].toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
			        
			        
			        
			        percepciones.setCodInscripcionIBCliente(new Integer(objChofer[17].toString()));
			        percepciones.setCodActividadCliente(new Integer(objChofer[18].toString()));
			        percepciones.setCodCategoriaIVACliente(new Integer(objChofer[21].toString()));
			        percepciones.setEsCombustible(new Boolean(obtenerEsCombustibleEsGasoil(objChofer[19].toString())));
			        percepciones.setEsGasoil(new Boolean(obtenerEsCombustibleEsGasoil(objChofer[20].toString())));
			       
			        percepciones.setProvincia(objChofer[16].toString());  
			        percepciones.setCodProvincia(new Integer(objChofer[15].toString()));
			        
			        if(objChofer[27]!=null){
			        	percepciones.setDescricpionGastos1(objChofer[27].toString());
			        	 cambiarProvincia = obtenerProvincia(percepciones.getDescricpionGastos1().trim(),percepciones.getProvincia().trim()); 
					        
					        if(cambiarProvincia){			        	 
							     percepciones.setCodProvincia(10);
							     percepciones.setProvincia("SALTA");  
					        }
			        } 
			        percepciones.setImporteFactura(new BigDecimal(objChofer[23].toString()));
			       
			        percepciones.setCodArticulo(new Integer(objChofer[24].toString()));
			        percepciones.setDescArticulo(objChofer[25].toString());
			        percepciones.setNroIIBB(objChofer[26].toString());  			        
			        
			        percepciones.setNroFacturaCompuesto("0"+percepciones.getCodComprobanteInt()+percepciones.getTipoComprobante()+dataUtil.rellenarConCeros(4, percepciones.getNroSucursal().toString())+dataUtil.rellenarConCeros(8, percepciones.getNroFactura().toString()));
			        percepciones.setNroCertificado(percepciones.getNroFacturaCompuesto());
			        
			        percepciones.setLeyCba(new BigDecimal(objChofer[28].toString()));
			        
			        lstPorFiltro.add(percepciones);
				
					
				}
				
				it = lstPorFiltro.iterator();
				lstPorFiltro = new ArrayList();
				Integer sucursalAux=0;
				Integer facturaAux=0;
				Integer codProductoAux=0;
				Integer codProvAux=0;
				BigDecimal brutoAux=new BigDecimal(0);
				BigDecimal netoAux=new BigDecimal(0);
				BigDecimal itcTasaAux=new BigDecimal(0);
				BigDecimal baseImponible=new BigDecimal(0);
				BigDecimal percepcion=new BigDecimal(0);
				int pasarALista=0;
				while(it.hasNext()){
					percepciones = (PercepcionesIIBBTO) it.next(); 			         
			        
			        codProvincia = percepciones.getCodProvincia();
					codInscripcionIBCliente= percepciones.getCodInscripcionIBCliente();
					codActividadCliente= percepciones.getCodActividadCliente();
					codCategoriaIVACliente= percepciones.getCodCategoriaIVACliente();
					esCombustible=percepciones.getEsCombustible();
					esGasoil=percepciones.getEsGasoil();
					
			        percepciones.setAlicuota(obtenerAlicuotaDeMIbImpuesto(codProvincia,codInscripcionIBCliente,codActividadCliente,codCategoriaIVACliente,esCombustible,esGasoil));
			        
			        if(percepciones.getAlicuota().compareTo(new BigDecimal("0.00"))>0){
			        	Double netoCalculado = (Math.round(percepciones.getPercepcion().doubleValue() * 100) / percepciones.getAlicuota().doubleValue()) ;						
			        	percepciones.setNetoCalculado(new BigDecimal(netoCalculado));
			        	percepciones.setBaseImponible(percepciones.getNetoCalculado());			       
			        }else{
			        	percepciones.setNetoCalculado(new BigDecimal("0.00"));
			        	percepciones.setBaseImponible(percepciones.getNetoCalculado());		
			        }
			        
			        
			        //a partir del 1 de enero 2013 el calculo es distinto
			       // Monto Imponible = Presio sin impuesto + itc + tasa
			       // Neto Calculado = (iibb * 100)/ alicuota
			        //iibb = ( neto calculado * alicuota ) /100 
			        
			        if( sucursalAux.equals(percepciones.getNroSucursal()) &&
				        facturaAux.equals(percepciones.getNroFactura()) &&
				        //codProductoAux.equals(percepciones.getCodArticulo())
				        codProvAux.equals(percepciones.getCodProvincia())
				        ){
			        	//sumo bruto, neto, itctasa,base imponible
			        	percepciones.setBruto(percepciones.getBruto().add(brutoAux));
			        	percepciones.setNetoCalculado(percepciones.getNetoCalculado().add(netoAux));
			        	percepciones.setItcTasa(percepciones.getItcTasa().add(itcTasaAux));
			        	percepciones.setBaseImponible(percepciones.getBaseImponible().add(baseImponible));
			        	percepciones.setPercepcion(percepciones.getPercepcion().add(percepcion));			        	
			        	pasarALista = 1; 			        	
			        }
			        
			        sucursalAux = percepciones.getNroSucursal();
			        facturaAux = percepciones.getNroFactura();
			        codProductoAux=percepciones.getCodArticulo();
			        codProvAux=percepciones.getCodProvincia();
			        brutoAux = percepciones.getBruto();
			        netoAux=percepciones.getNetoCalculado();
					itcTasaAux=percepciones.getItcTasa();
					baseImponible=percepciones.getBaseImponible();
					percepcion= percepciones.getPercepcion();
			        
			        //ver quien pasa
					if(percepciones.getAlicuota().compareTo(new BigDecimal("0.00"))>0){
						lstPorFiltro.add(percepciones);	
						if(pasarALista==1){							
							lstPorFiltro.remove(lstPorFiltro.size()-2);
						}
					}
					 pasarALista = 0;  
					
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
	
	private Boolean obtenerEsCombustibleEsGasoil (String es){	
		if(es.trim().equals("1")){
			return true;
		}else{
			return false;
		}		
	}
	
	private Boolean obtenerProvincia(String descricpionGastos1, String provincia){
		Boolean cambiarProv = new Boolean (false);
		Boolean cadenaAux = descricpionGastos1.toUpperCase().contains(provincia.toUpperCase());
		
		if(cadenaAux){
			 cambiarProv = new Boolean (false);
		}else{
			cambiarProv = new Boolean (true);
		}
		return cambiarProv;
	} 
    
	
	
	//obtener el valor de alicuota, porcenta de la tabla mibimpuesto
	private BigDecimal obtenerAlicuotaDeMIbImpuesto(Integer codProvincia,Integer codInscripcionIBCliente,Integer codActividadCliente,Integer codCategoriaIVACliente, Boolean esCombustible,Boolean esGasoil){
		BigDecimal alicuota = new BigDecimal(0);
		
		MibimpuestoDAO mibimpuestoDAO = new MibimpuestoDAO(getSession());
		try {
			List lstImpuesto = mibimpuestoDAO.getImpuestoPorProvinciaIncripIIBB(codProvincia,codInscripcionIBCliente);
			List lst = new ArrayList();
			List lst2 = new ArrayList();
			Iterator itImp = null;
			MImpuestoTO impuesto =  new MImpuestoTO();	
			
			if(lstImpuesto.size()==0){	
				codInscripcionIBCliente =0;
				lstImpuesto = mibimpuestoDAO.getImpuestoPorProvinciaIncripIIBB(codProvincia,codInscripcionIBCliente);										
			}
			
			itImp = lstImpuesto.iterator();	
			while(itImp.hasNext()){
				impuesto = (MImpuestoTO)itImp.next();
				lst.add(impuesto);							
			}
			
			if(lst.size()>0){
				Iterator itImp2 = lst.iterator();
				while(itImp2.hasNext()){
					impuesto = (MImpuestoTO)itImp2.next();
					
					if(impuesto.getInscripcionIVA().equals(codCategoriaIVACliente)){
						lst2.add(impuesto);					
					}			
				}
				
				if(lst2.size()>0){
					//no hay reg
				}else{
					lst2 = new ArrayList();
					itImp2 = lst.iterator();
					while(itImp2.hasNext()){
						impuesto = (MImpuestoTO)itImp2.next();						
						if(impuesto.getInscripcionIVA().equals(0)){
							lst2.add(impuesto);					
						}			
					}
					
					if(lst2.size()>0){
						Iterator itImp3 = lst2.iterator();
						List lst3= new ArrayList();
						while(itImp3.hasNext()){
							impuesto = (MImpuestoTO)itImp3.next();							
							if(impuesto.getActividad().equals(codActividadCliente)){
								lst3.add(impuesto);					
							}			
						}
						
						if(lst3.size()>0){

							Iterator itImp7 = lst3.iterator();
							lst = new ArrayList();
							
							while(itImp7.hasNext()){
								impuesto = (MImpuestoTO)itImp7.next();	
								
								if(impuesto.getTipoProducto().equals(obtenerTipoProd(esCombustible,esGasoil))){
									lst.add(impuesto);					
								}			
							}
							
							if(lst.size()>0){
								//tomo el impuesto
								alicuota = ((MImpuestoTO)lst.get(0)).getAlicuota();
							}else{
								
								Iterator itImp6 = lst3.iterator();
								lst2 =  new ArrayList();
								while(itImp6.hasNext()){
									impuesto = (MImpuestoTO)itImp6.next();	
									
									if(impuesto.getTipoProducto().equals(0)){
										lst2.add(impuesto);					
									}			
								}
								
								if(lst2.size()>0){
//									tomo el impuesto
									alicuota = ((MImpuestoTO)lst2.get(0)).getAlicuota();
								}else{
									//no hay reg no se puede seguir ERROR
								}
							}
							
						
							
							
							
						}else{
							
							Iterator itImp4 = lst2.iterator();
							lst2 = new ArrayList();
							
							while(itImp4.hasNext()){
								impuesto = (MImpuestoTO)itImp4.next();							
								if(impuesto.getActividad().equals(0)){
									lst2.add(impuesto);					
								}			
							}
							
							if(lst2.size()>0){
								Iterator itImp5 = lst2.iterator();
								lst = new ArrayList();
								
								while(itImp5.hasNext()){
									impuesto = (MImpuestoTO)itImp5.next();	
									
									if(impuesto.getTipoProducto().equals(obtenerTipoProd(esCombustible,esGasoil))){
										lst.add(impuesto);					
									}			
								}
								
								if(lst.size()>0){
									//tomo el impuesto
									alicuota = ((MImpuestoTO)lst.get(0)).getAlicuota();
								}else{
									
									Iterator itImp6 = lst2.iterator();
									lst2 =  new ArrayList();
									while(itImp6.hasNext()){
										impuesto = (MImpuestoTO)itImp6.next();	
										
										if(impuesto.getTipoProducto().equals(0)){
											lst2.add(impuesto);					
										}			
									}
									
									if(lst2.size()>0){
//										tomo el impuesto
										alicuota = ((MImpuestoTO)lst2.get(0)).getAlicuota();
									}else{
										//no hay reg no se puede seguir ERROR
									}
								}
								
							}else{
//								no hay reg no se puede seguir ERROR
							}
						}
					}else{
						//no hay reg no se puede seguir ERROR
					}
					
					
				}
			}
			
		} catch (DataAccessErrorException e) {			
			e.printStackTrace();
		}
		
		return alicuota;
	}
	
	
	private Integer obtenerTipoProd(Boolean esCombustible, Boolean esGasoil){
		Integer tipoProd=0;
		if(esCombustible.equals(new Boolean(false)) && esGasoil.equals(new Boolean(false))){
			tipoProd = 3;
		}else if(esCombustible.equals(new Boolean(true)) && esGasoil.equals(new Boolean(false))){
			tipoProd= 2;
		}else if(esCombustible.equals(new Boolean(true)) && esGasoil.equals(new Boolean(true))){
			tipoProd= 1;
		}else{
			tipoProd= 0;
		}
		System.out.println("tipoProd" +tipoProd.toString());
		return tipoProd;
		
		
	}
	
	
	private String obtenerTipo(String comp) throws Exception{
		Messages mensajeria = new Messages();
		String comprobante=null;
		try{
		
		 if(comp.trim().equals("FC")){
			 	comprobante= mensajeria.getMessage().getString("factura_factura_label");	        	
		    }else if(comp.trim().equals("NC") ){
		    	comprobante= mensajeria.getMessage().getString("nota_credito_label");
		    }else if(comp.trim().equals("ND") ){
		    	comprobante= mensajeria.getMessage().getString("nota_debito_label");
		    }	
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		return comprobante;
	}
	
	private Integer ObtenerCodComprobantes(String comp) throws Exception{		 
			Integer comprobante=null;
	try{
			
		   if(comp.trim().equals("FC")){
			 	comprobante= 1;	        	
		    }else if(comp.trim().equals("NC") ){
		    	comprobante= 3;
		    }else if(comp.trim().equals("ND") ){
		    	comprobante= 2;
		    }	
	}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
	}
		return comprobante;
			
	}
	
	public List getPercepcionesIva(Integer codCliente, Date fechaDesde, Date fechaHasta, int ccss ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put(Const.PARAM_COD_CLIENTE_INT, codCliente);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			params.put(Const.PARAM_COD_CCSS, ccss);
			
			Query query = this.getNamedQuery(MfacturasVDAO.FIND_PERCEPCIONES_PIVA_CERO, params, session);
			lstPorFiltro = query.list();
			
			query = this.getNamedQuery(MfacturasVDAO.FIND_PERCEPCIONES_PIVA_DISTINTO_CERO, params, session);
			lstPorFiltro.addAll(query.list());
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				PercepcionesIVATO percepcionesIVATO= new PercepcionesIVATO();
				Object[] objChofer= null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        percepcionesIVATO = new PercepcionesIVATO();
			       
			        percepcionesIVATO.setCodComprobanteStr(objChofer[0].toString());
			        
			        if(percepcionesIVATO.getCodComprobanteStr().trim().equals("FC")){
				        	 percepcionesIVATO.setCodComprobanteInt(1);
				    }else if(percepcionesIVATO.getCodComprobanteStr().trim().equals("NC") ){
				        	percepcionesIVATO.setCodComprobanteInt(3);
				    }else if(percepcionesIVATO.getCodComprobanteStr().trim().equals("ND") ){
				        	percepcionesIVATO.setCodComprobanteInt(2);
				    }	
			        
			        percepcionesIVATO.setFechaEmisionComprobante(sdf.format(objChofer[1]));
			        percepcionesIVATO.setNroSucursal(new Integer(objChofer[2].toString()));
			        percepcionesIVATO.setNroFactura(new Integer(objChofer[3].toString()));
			        percepcionesIVATO.setImporte(new BigDecimal(objChofer[4].toString()));
			        percepcionesIVATO.setCodImpuesto(Const.COD_IMPUESTO);
			        percepcionesIVATO.setCodRegimen(Const.COD_REGIMEN);
			        percepcionesIVATO.setCodOperacion(Const.COD_OPERACION);
			        percepcionesIVATO.setBaseCalculo(new BigDecimal(objChofer[5].toString()));
			        percepcionesIVATO.setFechaEmisionRetencion(sdf.format(objChofer[6]));
			        percepcionesIVATO.setCodCondicion(Const.COD_CONDICION);
			        
			        percepcionesIVATO.setMontoDeLaRetencion(new BigDecimal(objChofer[7].toString()));
			        percepcionesIVATO.setPorcExclusion(new BigDecimal(objChofer[8].toString()));		        
			       
			        lstPorFiltro.add(percepcionesIVATO);
					
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
	
	
	public List getFacturaPorClienteFecha(String codCliente, Date fechaDesde, Date fechaHasta, int condicion, int ccss,Date fechaDesdeDos, Date fechaHastaDos,Integer nroSucursal, Integer nroFactura ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
					
			if(codCliente==null)codCliente="%";
			if(ccss==0) ccss=-1;
			
			params.put(Const.PARAM_COD_CLIENTE, codCliente.trim());
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			params.put(Const.PARAM_COD_CCSS, ccss);
			
			params.put(Const.PARAM_FECHA_DESDE_DOS, fechaDesdeDos);
			params.put(Const.PARAM_FECHA_HASTA_DOS, fechaHastaDos);
			
			if(nroSucursal==null){
				nroSucursal = -1;
			}
			
			if(nroFactura==null){
				nroFactura = -1;
			}
			params.put(Const.PARAM_NRO_SUCURSAL, nroSucursal);
			params.put(Const.PARAM_NRO_FACTURA, nroFactura);
			
			params.put(Const.PARAM_TIPO_OPERACION, Const.PARAM_COBRO_FACTURA_CLIENTE_CTA_CTE);
			Query query = this.getNamedQuery(MfacturasVDAO.FIND_FACTURAS_BY_CLIENTE_FECHAS, params, session);
			lstPorFiltro = query.list();
			
			query = this.getNamedQuery(MfacturasVDAO.FIND_FACTURAS_NOABONADAS_BY_CLIENTE_FECHAS, params, session);
			List lstAuxFacturas = query.list();
			
			if(lstAuxFacturas.size()>0){				
				lstPorFiltro.addAll(lstAuxFacturas);
			}		
			
			query = this.getNamedQuery(MfacturasVDAO.FIND_FACTURAS_NOABONADAS_2_BY_CLIENTE_FECHAS, params, session);
			List lstAuxFacturas2 = query.list();		
			
			if(lstAuxFacturas2.size()>0){				
				lstPorFiltro.addAll(lstAuxFacturas2);
			}
			
			
			int tipo=0;
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MFacturaVTO mFacturaVTO= new MFacturaVTO();
				Object[] objChofer= null;
				
			
				List<CtasCtesTO> lstPagosAplicados = new ArrayList<CtasCtesTO>();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Integer codClienteInt = new Integer("0");
				
				Integer suc=0,rem=0;
				BigDecimal saldo= new BigDecimal("0.00");
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        mFacturaVTO=new MFacturaVTO();
			        
			        mFacturaVTO.setOrder(new Integer(objChofer[0].toString()));			        
			        mFacturaVTO.setFecha(sdf.format(objChofer[1]));
			        
			        if(objChofer[9]!=null){
				        mFacturaVTO.setTipoComprobante(objChofer[9].toString());
				       // //System.out.println(mFacturaVTO.getTipoComprobante());
				        if(mFacturaVTO.getTipoComprobante().trim().equals("FC") || mFacturaVTO.getTipoComprobante().trim().equals("F")){
				        	 mFacturaVTO.setTipo(mensajeria.getMessage().getString("factura_factura_label")+" "+objChofer[2].toString());
				        	 tipo=1;
				        }else if(mFacturaVTO.getTipoComprobante().trim().equals("NC") ){
				        	mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_credito_label"));
				        	 tipo=2;
				        }else if(mFacturaVTO.getTipoComprobante().trim().equals("ND") ){
				        	mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_debito_label"));
				        	 tipo=3;
				        }		
			        }
			       
			        mFacturaVTO.setNroSucursal(new Integer(objChofer[3].toString()));
			        mFacturaVTO.setNroFactura(new Integer(objChofer[4].toString()));			        
			        mFacturaVTO.setNetoFactura(new BigDecimal(objChofer[5].toString()));
			        mFacturaVTO.setNroRemito(new Integer(objChofer[6].toString()));
			        mFacturaVTO.setCliDescripcion(objChofer[7].toString());
			        mFacturaVTO.setCodClienteAlfa(objChofer[8].toString());			        
			        mFacturaVTO.setPagoAplicado(new BigDecimal(objChofer[10].toString()));
			        mFacturaVTO.setSaldo(new BigDecimal(objChofer[11].toString()));
			        
			        
			        if(mFacturaVTO.getNroRemito()!=0){
			        	mFacturaVTO.setSaldo(new BigDecimal(0.00));	
			        	mFacturaVTO.setPagoAplicado(mFacturaVTO.getNetoFactura());
			        	mFacturaVTO.setTipoPagoFactura(mensajeria.getMessage().getString("contado_abreviado_label"));
			        	mFacturaVTO.setCondicion(2);
			        	
			        }else if(mFacturaVTO.getNroRemito().equals(new Integer(0))){
			        	mFacturaVTO.setTipoPagoFactura(mensajeria.getMessage().getString("cta_cte_abreviado_label"));
			        	mFacturaVTO.setCondicion(1);
			        }
			        
			        
			        if(tipo==0 || tipo==1){
				        if(mFacturaVTO.getSaldo().compareTo(new BigDecimal(0.00))<0){
				        	mFacturaVTO.setSaldo(new BigDecimal(0.00));			        	
				        }
			        }
			        
			        //yapa
				       //System.out.println("suc ->"+mFacturaVTO.getNroSucursal().toString()+ " getNroFactura -> "+mFacturaVTO.getNroFactura().toString());
				        if(suc.equals(mFacturaVTO.getNroSucursal()) && rem.equals(mFacturaVTO.getNroFactura())){
				        	mFacturaVTO.setSaldo(saldo.add(mFacturaVTO.getPagoAplicado()));	
				        	// ////System.out.println("suc ->"+ remitoTO.getMontoTotal());
				        }
				       
				        suc=mFacturaVTO.getNroSucursal();
				        rem=mFacturaVTO.getNroFactura();
				        saldo = mFacturaVTO.getSaldo();
				        
				       // ////System.out.println("suc ->"+suc.toString()+ " rem-> "+rem.toString() );
				        //yapa
			        
			        mFacturaVTO.setIva(new BigDecimal(objChofer[12].toString()));
			        mFacturaVTO.setPerIVA(new BigDecimal(objChofer[13].toString()));
			        mFacturaVTO.setPerIIBB(new BigDecimal(objChofer[14].toString()));
			        mFacturaVTO.setTasaFondo(new BigDecimal(objChofer[15].toString()));
			        mFacturaVTO.setItc(new BigDecimal(objChofer[16].toString()));
			        mFacturaVTO.setNroAsientoContable(new BigDecimal(objChofer[17].toString()));
			        mFacturaVTO.setPrecioNeto(new BigDecimal(objChofer[18].toString()));			        
			       
			        if(objChofer[19]!=null){
			        	mFacturaVTO.setFeVto(sdf.format(objChofer[19]));
			        }
			        
			        mFacturaVTO.setCcss(objChofer[20].toString());
			        mFacturaVTO.setNroEjercicioContable(new BigDecimal(objChofer[21].toString()));
			        
			        if(objChofer[22]!=null){
			           mFacturaVTO.setNroRendicionRefipass(new Integer(objChofer[22].toString()));
			        }
			        
			        mFacturaVTO.setCodCliente(new Integer(objChofer[23].toString()));
			        mFacturaVTO.setLeyCba(new BigDecimal(objChofer[24].toString()));
			        
			        if(objChofer[25]!=null)
			        	mFacturaVTO.setCO2(new BigDecimal(objChofer[25].toString()));
			        
			        if(mFacturaVTO.getTipoComprobante().trim().equals("NC") ){		        	
			        	
			        	if(!codClienteInt.equals(mFacturaVTO.getCodCliente())){
			        		//traer la lista de  nc no aplicadas
			        		lstPagosAplicados = new ArrayList<CtasCtesTO>();
			        		MctasCtesDAO ctactedao= new MctasCtesDAO(getSession());
			        		try{
							   lstPagosAplicados = ctactedao.getDocumentosConSaldo(mFacturaVTO.getCodClienteAlfa());
			        		}catch(Exception ex){								
								
							}
			        	}			        	
			        	codClienteInt = mFacturaVTO.getCodCliente();			        	
			        	mFacturaVTO.setSaldo(ObtenerSaldoNCV2(lstPagosAplicados,mFacturaVTO.getNroSucursal(),mFacturaVTO.getNroFactura())) ;
			        	
			        }
			        
			        if(condicion==-1 || mFacturaVTO.getCondicion()==condicion){
			        	  lstPorFiltro.add(mFacturaVTO);
					}
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
	
	
	private Integer ObtenerCtaReg(Integer codCliente, Integer nroOper){	
		
		Integer ctactereg=0;
		 List lstPorFiltro= new ArrayList();
		 
	 	if(!session.isOpen()) session = (new MfacturasVDAO()).getSession();	 
	 
	    Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put(Const.PARAM_COD_CLIENTE, codCliente);
		params2.put(Const.PARAM_NRO_OPERACION, nroOper);
		Query query = this.getNamedQuery("findeCtacteregNC", params2, getSession());
		
		lstPorFiltro = query.list();
		
		if(lstPorFiltro.size()>0){
			Iterator it = lstPorFiltro.iterator();
			lstPorFiltro= new ArrayList();
			Object[] objChofer= null;				
			while(it.hasNext()){
		        objChofer = (Object[]) it.next();	
		        if(objChofer[0]==null){
		        	ctactereg=0;
		        }else{	
		        	ctactereg = new Integer(objChofer[0].toString());
		        }
		       	       
			}
		}
				
			
		return ctactereg;
		
	}
	
	private BigDecimal ObtenerSaldoNCV2(List<CtasCtesTO> lstNCNoaplicada, int nroSuc, int nroFac){
		Iterator it = lstNCNoaplicada.iterator();		
		CtasCtesTO ctasCtesTO= new CtasCtesTO();	
		BigDecimal saldo = new BigDecimal(0);					
		while(it.hasNext()){
			ctasCtesTO = (CtasCtesTO) it.next(); 			
			if(ctasCtesTO.getSucursal() == nroSuc && ctasCtesTO.getComprobanteNumero() == nroFac){
				saldo = ctasCtesTO.getImporte();
			}
		}
		return saldo;
		
	}
	
	private BigDecimal ObtenerSaldoNC(List<MFacturaVTO> lstNCNoaplicada, Integer nroSuc, Integer nroFac){
		Iterator it = lstNCNoaplicada.iterator();		
		MFacturaVTO mFacturaVTO= new MFacturaVTO();	
		BigDecimal saldo = new BigDecimal(0);					
		while(it.hasNext()){
			mFacturaVTO = (MFacturaVTO) it.next(); 			
			if(mFacturaVTO.getNroSucursal().equals(nroSuc) && mFacturaVTO.getNroFactura().equals(nroFac)){
				saldo = mFacturaVTO.getTotal();
			}
		}
		return saldo;
		
	}
	private List<MFacturaVTO> ObtenerListaNCConSaldo(Date fechaDesde, Date fechaHasta, Integer codCliente){
		
    List lstPorFiltro= new ArrayList();
    Map<String, Object> params2 = new HashMap<String, Object>();
	params2.put(Const.PARAM_COD_CLIENTE, codCliente);
	params2.put(Const.PARAM_FECHA_DESDE, fechaDesde);
	params2.put(Const.PARAM_FECHA_HASTA, fechaHasta);		
	/*traer los nc y recibos no aplicados*/
	Query query = this.getNamedQuery(MctasCtesDAO.FIND_NC_NO_APLICADO, params2, session);
	
	List lstPorFiltroAux = query.list();
	if(lstPorFiltroAux.size()>0){
		Iterator it = lstPorFiltroAux.iterator();
		lstPorFiltroAux= new ArrayList();
		MFacturaVTO mFacturaVTO= new MFacturaVTO();
		Object[] objChofer= null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						
		while(it.hasNext()){
	        objChofer = (Object[]) it.next(); 
	        mFacturaVTO=new MFacturaVTO();
	        mFacturaVTO.setOrder(new Integer(objChofer[0].toString()));			        
	        mFacturaVTO.setFecha(sdf.format(objChofer[1]));			        
	        mFacturaVTO.setTipo(objChofer[2].toString());			        
	        mFacturaVTO.setNroSucursal(new Integer(objChofer[3].toString()));			        
	        if(objChofer[4]!=null){
	        	mFacturaVTO.setNroFactura(new Integer(objChofer[4].toString()));
	        }
	        
	        mFacturaVTO.setTotal(new BigDecimal(objChofer[5].toString()));//total de la factura
	       
	        mFacturaVTO.setCliDescripcion(objChofer[6].toString());
	        mFacturaVTO.setCodClienteAlfa(objChofer[7].toString());	
	        
	        mFacturaVTO.setPagoAplicado(new BigDecimal(objChofer[9].toString()));
	        mFacturaVTO.setNetoFactura(new BigDecimal(objChofer[10].toString()));
	        
	        if(objChofer[11]!=null){
	        	mFacturaVTO.setFeVto(sdf.format(objChofer[11]));
	        }
	        
	        mFacturaVTO.setCcss(objChofer[12].toString());			        
	        mFacturaVTO.setNroRemito(new Integer(objChofer[13].toString()));
	        mFacturaVTO.setCodCliente(new Integer(objChofer[14].toString()));
	        
	        mFacturaVTO.setSaldo(new BigDecimal(objChofer[15].toString()));
	        
	        if(mFacturaVTO.getSaldo().equals(new BigDecimal("0.00"))){
	        	mFacturaVTO.setSaldo(mFacturaVTO.getSaldo().abs()); 
	        }
	        
	        if(objChofer[16].toString().equals("NCNOAPLI"))
	        	mFacturaVTO.setTipoComprobante("NCNOAPLI");
	        else if(objChofer[16].toString().equals("RECNOAPLI"))
	        	mFacturaVTO.setTipoComprobante("RECNOAPLI");
	        else if(objChofer[16].toString().equals("RECNOAPLI2")){
	        	mFacturaVTO.setTipoComprobante("RECNOAPLI2");
	        	mFacturaVTO.setTotal(mFacturaVTO.getTotal().multiply(new BigDecimal(-1)));
	        }
	        
	        
	        lstPorFiltro.add(mFacturaVTO);
			
		}
		
	}
	
	return lstPorFiltro;
	}
	
	private BigDecimal pagoAplicadoTotal;
	
	public BigDecimal getPagoAplicadoTotal() {
		return pagoAplicadoTotal;
	}

	public void setPagoAplicadoTotal(BigDecimal pagoAplicadoTotal) {
		this.pagoAplicadoTotal = pagoAplicadoTotal;
	}

	public List getTodasLasFacturasAgrupadas(Date fechaDesde, Date fechaHasta, Date fechaDesdeDos, Date fechaHastaDos , Integer codCliente) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();			
			
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);		
			params.put(Const.PARAM_FECHA_DESDE_DOS, fechaDesdeDos);
			params.put(Const.PARAM_FECHA_HASTA_DOS, fechaHastaDos);			
			params.put(Const.PARAM_TIPO_OPERACION, Const.PARAM_COBRO_FACTURA_CLIENTE_CTA_CTE);
			
			Query query = this.getNamedQuery(MctasCtesDAO.FIND_TODAS_LAS_FACTURAS_AGRUPADAS, params, session);
			lstPorFiltro = query.list();
			
			
			int tipo=0;
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MFacturaVTO mFacturaVTO= new MFacturaVTO();
				Object[] objChofer= null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        mFacturaVTO=new MFacturaVTO();
			        mFacturaVTO.setOrder(new Integer(objChofer[0].toString()));			        
			        mFacturaVTO.setFecha(sdf.format(objChofer[1]));
			        
			        if(objChofer[8]!=null){
				        mFacturaVTO.setTipoComprobante(objChofer[8].toString());
				       // //System.out.println(mFacturaVTO.getTipoComprobante());
				        if(mFacturaVTO.getTipoComprobante().trim().equals("FC") || mFacturaVTO.getTipoComprobante().trim().equals("F")){
				        	 mFacturaVTO.setTipo(mensajeria.getMessage().getString("factura_factura_label")+" "+objChofer[2].toString());
				        	 tipo=1;
				        }else if(mFacturaVTO.getTipoComprobante().trim().equals("NC") ){
				        	mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_credito_label"));
				        	 tipo=2;
				        }else if(mFacturaVTO.getTipoComprobante().trim().equals("ND") ){
				        	mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_debito_label"));
				        	 tipo=3;
				        }		
			        }
			       
			        mFacturaVTO.setNroSucursal(new Integer(objChofer[3].toString()));
			        mFacturaVTO.setNroFactura(new Integer(objChofer[4].toString()));
			        
			        mFacturaVTO.setTotal(new BigDecimal(objChofer[5].toString()));//total de la factura
			       
			        mFacturaVTO.setCliDescripcion(objChofer[6].toString());
			        mFacturaVTO.setCodClienteAlfa(objChofer[7].toString());	
			        
			        mFacturaVTO.setPagoAplicado(new BigDecimal(objChofer[9].toString()));
			        mFacturaVTO.setNroRemito(new Integer(objChofer[13].toString()));      
			        
			        if(mFacturaVTO.getNroRemito()!=0){			        	
			        	mFacturaVTO.setPagoAplicado(mFacturaVTO.getTotal());
			        	mFacturaVTO.setTipoPagoFactura(mensajeria.getMessage().getString("contado_abreviado_label"));
			        	mFacturaVTO.setCondicion(2);			        	
			        }else if(mFacturaVTO.getNroRemito().equals(new Integer(0))){
			        	mFacturaVTO.setTipoPagoFactura(mensajeria.getMessage().getString("cta_cte_abreviado_label"));
			        	mFacturaVTO.setCondicion(1);
			        }
			      
			        
			        mFacturaVTO.setNetoFactura(new BigDecimal(objChofer[10].toString()));
			        if(objChofer[11]!=null){
			        	mFacturaVTO.setFeVto(sdf.format(objChofer[11]));
			        }
			        
			        mFacturaVTO.setCcss(objChofer[12].toString());
			        mFacturaVTO.setCodCliente(new Integer(objChofer[14].toString()));
			        
			        mFacturaVTO.setSaldo(new BigDecimal(objChofer[15].toString()));
			        
			        if(mFacturaVTO.getSaldo().equals(new BigDecimal("0.00"))){
			        	mFacturaVTO.setSaldo(mFacturaVTO.getSaldo().abs()); 
			        }
			        
			        mFacturaVTO.setLstDocumentoAplicados(obtenerListaDocumentosAplicados(mFacturaVTO.getOrder(), mFacturaVTO.getNroSucursal(),mFacturaVTO.getCodCliente(),mFacturaVTO.getTotal()));
			       
			        mFacturaVTO.setPagoAplicado(pagoAplicadoTotal);
			        lstPorFiltro.add(mFacturaVTO);
					
				}
				
				
				
			}
			
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put(Const.PARAM_COD_CLIENTE, codCliente);
			params2.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params2.put(Const.PARAM_FECHA_HASTA, fechaHasta);	
			params2.put(Const.PARAM_COD_CLIENTE_ALFA, '%');
			//traer los nc y recibos no aplicados
			//query = this.getNamedQuery(MctasCtesDAO.FIND_NC_REMITO_NO_APLICADO, params2, session);
			query = this.getNamedQuery("buscarNCyRCNoAplicados", params2, session);
			
			List lstPorFiltroAux = query.list();
			if(lstPorFiltroAux.size()>0){
				Iterator it = lstPorFiltroAux.iterator();
				lstPorFiltroAux= new ArrayList();
				MFacturaVTO mFacturaVTO= new MFacturaVTO();
				Object[] objChofer= null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        mFacturaVTO=new MFacturaVTO();
			        mFacturaVTO.setOrder(new Integer(objChofer[0].toString()));			        
			        if(objChofer[1]!=null) mFacturaVTO.setFecha(sdf.format(objChofer[1]));			        
			        mFacturaVTO.setTipo(objChofer[2].toString());			        
			        mFacturaVTO.setNroSucursal(new Integer(objChofer[3].toString()));
			        
			        if(objChofer[4]!=null){
			        	mFacturaVTO.setNroFactura(new Integer(objChofer[4].toString()));
			        }
			        
			        mFacturaVTO.setTotal(new BigDecimal(objChofer[5].toString()));//total de la factura
			       
			        mFacturaVTO.setCliDescripcion(objChofer[6].toString());
			        mFacturaVTO.setCodClienteAlfa(objChofer[7].toString());	
			        
			        mFacturaVTO.setPagoAplicado(new BigDecimal(objChofer[9].toString()));
			        mFacturaVTO.setNetoFactura(new BigDecimal(objChofer[10].toString()));
			        
			        if(objChofer[11]!=null){
			        	mFacturaVTO.setFeVto(sdf.format(objChofer[11]));
			        }
			        
			        mFacturaVTO.setCcss(objChofer[12].toString());			        
			        mFacturaVTO.setNroRemito(new Integer(objChofer[13].toString()));
			        mFacturaVTO.setCodCliente(new Integer(objChofer[14].toString()));
			        
			        mFacturaVTO.setSaldo(new BigDecimal(objChofer[15].toString()));
			        
			        if(mFacturaVTO.getSaldo().equals(new BigDecimal("0.00"))){
			        	mFacturaVTO.setSaldo(mFacturaVTO.getSaldo().abs()); 
			        }
			        
			        if(objChofer[16].toString().equals("NCNOAPLI"))
			        	mFacturaVTO.setTipoComprobante("NCNOAPLI");
			        else if(objChofer[16].toString().equals("RECNOAPLI"))
			        	mFacturaVTO.setTipoComprobante("RECNOAPLI");
			        else if(objChofer[16].toString().equals("RECNOAPLI2")){
			        	mFacturaVTO.setTipoComprobante("RECNOAPLI2");
			        	mFacturaVTO.setTotal(mFacturaVTO.getTotal().multiply(new BigDecimal(-1)));
			        }
			        
			        
			        if(mFacturaVTO.getTipo().equals("PAGOADEL")){
			        	PagoAdelTO pagoAdelTO = new PagoAdelTO();
			        	pagoAdelTO = ObtenerPagoAdel(mFacturaVTO.getCodCliente(),mFacturaVTO.getNroFactura(),mFacturaVTO.getTotal());
			        	
			        	mFacturaVTO.setFecha(pagoAdelTO.getFecha());
			        	mFacturaVTO.setTipo(pagoAdelTO.getTipo());
			        	if(pagoAdelTO.getNroSucursal()!=null && !pagoAdelTO.getNroSucursal().equals(new Integer(0))) {
			        		mFacturaVTO.setNroSucursal(pagoAdelTO.getNroSucursal());
			        	}else{
			        		mFacturaVTO.setNroSucursal(null);
			        	}
			        	mFacturaVTO.setNroFactura(pagoAdelTO.getNroFactura());
			        	mFacturaVTO.setTotal(pagoAdelTO.getTotal());
			        	mFacturaVTO.setCliDescripcion(pagoAdelTO.getCliDescripcion());
			        	mFacturaVTO.setCodClienteAlfa(pagoAdelTO.getCodClienteAlfa());
			        	mFacturaVTO.setPagoAplicado(pagoAdelTO.getPagoAplicado());
			        	mFacturaVTO.setNetoFactura(pagoAdelTO.getNetoFactura());
			        	mFacturaVTO.setFeVto(pagoAdelTO.getFeVto());			        	
			        	mFacturaVTO.setCodCliente(pagoAdelTO.getCodCliente());
			        	mFacturaVTO.setSaldo(pagoAdelTO.getSaldo());
			        	mFacturaVTO.setTipoComprobante(pagoAdelTO.getTipoComprobante());	
			        	
			        }
			        
			        lstPorFiltro.add(mFacturaVTO);
					
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
	
	
	public PagoAdelTO ObtenerPagoAdel(int codCliente, int nroOpera, BigDecimal total) throws DataAccessErrorException{
	try{	
		Map<String, Object> param = new HashMap<String, Object>();
    	param.put("codCliente", codCliente);						
    	param.put("nroOper", nroOpera);		
    	
    	if(!session.isOpen()) session = (new MfacturasVDAO()).getSession();;
    	
		Query queryPagoAdel = this.getNamedQuery("buscarPagoAdelNoAplicados", param, session);
		List lstPagoAdel = queryPagoAdel.list();
		PagoAdelTO pagoAdelTO = new PagoAdelTO();
		
		if(lstPagoAdel.size()>0){
			Iterator itPagoAdel = lstPagoAdel.iterator();
			lstPagoAdel= new ArrayList();							
			Object[] objPagoAdel= null;																		
			while(itPagoAdel.hasNext()){
				objPagoAdel = (Object[]) itPagoAdel.next(); 
				pagoAdelTO.setNroOper(new Integer(objPagoAdel[0].toString()));
				pagoAdelTO.setNroOperCaja(new Integer(objPagoAdel[1].toString()));
				pagoAdelTO.setImporte(new BigDecimal(objPagoAdel[2].toString()));
				pagoAdelTO.setTipoOper (objPagoAdel[3].toString());
				pagoAdelTO.setNroSucursal(new Integer(objPagoAdel[4].toString()));
			}
			
			boolean esPrimeraNC=true;
			PagoAdelTO pagoAdelTOAux = new PagoAdelTO();
			if(!pagoAdelTO.getNroOper().equals(0) && pagoAdelTO.getNroOperCaja().equals(0) && pagoAdelTO.getTipoOper().equalsIgnoreCase("PagoAdel")){
				//sigo buscanco
				pagoAdelTOAux = pagoAdelTO;
				while(!pagoAdelTOAux.getNroOper().equals(0) && pagoAdelTOAux.getNroOperCaja().equals(0) 
						&& pagoAdelTOAux.getTipoOper().equalsIgnoreCase("PagoAdel")){
					
					Map<String, Object> param2 = new HashMap<String, Object>();
					param2.put("codCliente", codCliente);						
					param2.put("nroOper", pagoAdelTOAux.getNroOper());		
			    	
			    	if(!session.isOpen()) session = (new MfacturasVDAO()).getSession()	;		    	
					Query queryPagoAdel2 = this.getNamedQuery("buscarPagoAdelNoAplicados", param2, session);
					List lstPagoAdel2 = queryPagoAdel2.list();									
					if(lstPagoAdel2.size()>0){
						Iterator itPagoAdel2 = lstPagoAdel2.iterator();
						lstPagoAdel2= new ArrayList();							
						Object[] objPagoAdel2= null;																		
						while(itPagoAdel2.hasNext()){
							objPagoAdel2 = (Object[]) itPagoAdel2.next(); 
							pagoAdelTOAux.setNroOper(new Integer(objPagoAdel2[0].toString()));
							pagoAdelTOAux.setNroOperCaja(new Integer(objPagoAdel2[1].toString()));
							pagoAdelTOAux.setImporte(new BigDecimal(objPagoAdel2[2].toString()));
							pagoAdelTOAux.setTipoOper (objPagoAdel2[3].toString());
							pagoAdelTOAux.setNroSucursal(new Integer(objPagoAdel2[4].toString()));
							pagoAdelTOAux.setCliDescripcion(objPagoAdel2[5].toString());
							pagoAdelTOAux.setCodClienteAlfa(objPagoAdel2[6].toString());
						}
						
						
						if(pagoAdelTO.getTipoOper().equalsIgnoreCase("NC"))
						    esPrimeraNC=false;
					}			
					
				}				
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			
			if(!pagoAdelTO.getNroOper().equals(0) && pagoAdelTO.getNroOperCaja().equals(0) && pagoAdelTO.getTipoOper().equalsIgnoreCase("NC")){
				//es una nc
				MfacturasVDAO mfacturasVDAO = new MfacturasVDAO(session);
				MfacturasVId aux = new MfacturasVId();
				aux.setNroSucursal(pagoAdelTO.getNroSucursal().shortValue());
				aux.setOrden(pagoAdelTO.getNroOper());
				MfacturasV mfacturasV = mfacturasVDAO.get(aux);
				
				pagoAdelTO.setOrder(0);
				pagoAdelTO.setFecha(sdf.format(mfacturasV.getFecha()));
				pagoAdelTO.setTipo("Nota de Crédito No Aplicada");	
				pagoAdelTO.setNroSucursal(pagoAdelTO.getNroSucursal());
				pagoAdelTO.setNroFactura(mfacturasV.getNroFactura());
				pagoAdelTO.setTotal(total);   //(esPrimeraNC ? total   //: pagoAdelTOAux.getImporte());
				pagoAdelTO.setCliDescripcion(pagoAdelTOAux.getCliDescripcion());
				pagoAdelTO.setCodClienteAlfa(pagoAdelTOAux.getCodClienteAlfa());
				pagoAdelTO.setPagoAplicado(new BigDecimal(0));
				pagoAdelTO.setNetoFactura(new BigDecimal(0));
				pagoAdelTO.setFeVto(sdf.format(mfacturasV.getFevto()));
				pagoAdelTO.setCodCliente(codCliente);
				pagoAdelTO.setSaldo(new BigDecimal(0));//  esPrimeraNC ? new BigDecimal(0) : pagoAdelTO.getImporte());				
				pagoAdelTO.setTipoComprobante("NCNOAPLI");
				
			}else if(pagoAdelTO.getNroOper().equals(0) && !pagoAdelTO.getNroOperCaja().equals(0) && pagoAdelTO.getTipoOper().equalsIgnoreCase("PagoAdel")){
				//es una recibo
				MreciboDAO mreciboDAO = new MreciboDAO(session);
				Mrecibo mrecibo = mreciboDAO.getRecibosPorNroOperCaja(pagoAdelTO.getNroOperCaja(), codCliente);			
				
				pagoAdelTO.setOrder(0);
				pagoAdelTO.setFecha(sdf.format(mrecibo.getFecha()));
				pagoAdelTO.setTipo("Recibo No Aplicado");	
				pagoAdelTO.setNroSucursal(null);
				pagoAdelTO.setNroFactura(mrecibo.getNroRec());
				pagoAdelTO.setTotal(mrecibo.getImporte().multiply(new BigDecimal(-1)));   //(esPrimeraNC ? total   //: pagoAdelTOAux.getImporte());
				pagoAdelTO.setCliDescripcion(pagoAdelTOAux.getCliDescripcion());
				pagoAdelTO.setCodClienteAlfa(pagoAdelTOAux.getCodClienteAlfa());
				pagoAdelTO.setPagoAplicado(new BigDecimal(0));
				pagoAdelTO.setNetoFactura(new BigDecimal(0));
				pagoAdelTO.setFeVto(null);
				pagoAdelTO.setCodCliente(codCliente);
				pagoAdelTO.setSaldo(total);//  esPrimeraNC ? new BigDecimal(0) : pagoAdelTO.getImporte());				
				pagoAdelTO.setTipoComprobante("RECNOAPLI2");
				
			}
		}
		
    	
    	return pagoAdelTO;
    	
	}catch(Exception ex){
		ex.printStackTrace();
		throw new DataAccessErrorException();
	}
		
	}
	
	public List obtenerListaDocumentosAplicados(Integer nroOperacion, Integer nroSucursal,Integer codCliente, BigDecimal totalFactura){
		pagoAplicadoTotal= new BigDecimal("0.00");
		List lstDocumentoAplicados= new ArrayList<DocumentoAplicadoTO>();
		
		/*try{			
			//buscar recibos
			MreciboDAO mreciboDAO = new MreciboDAO(getSession());
			lstDocumentoAplicados = mreciboDAO.getRecibosAplicados(nroOperacion,nroSucursal,codCliente);			
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		
		
		try{			
			//buscar AplicaNCFacturaCliente
			MctasCtesDAO mCtasCtesDAO = new MctasCtesDAO(getSession());
			List lstDocNCAplicaFac = mCtasCtesDAO.getNotaCreditoAplicadaFacturaCliente(nroOperacion,nroSucursal,codCliente);
			
			if(lstDocNCAplicaFac.size()>0){
				lstDocumentoAplicados.addAll(lstDocNCAplicaFac);		
			}
				
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		
		try{			
			//buscar pagos adelantados
			MctasCtesDAO mCtasCtesDAO = new MctasCtesDAO(getSession());
			List lstDocNCAplicaFac = mCtasCtesDAO.getPagoAdelantadoAplicadaFacturaCliente(nroOperacion,nroSucursal,codCliente);
			
			if(lstDocNCAplicaFac.size()>0){
				lstDocumentoAplicados.addAll(lstDocNCAplicaFac);		
			}
				
		}catch(Exception ex){
			ex.printStackTrace();			
		}*/
		
		try{			
			//buscar pagos adelantados
			MctasCtesDAO mCtasCtesDAO = new MctasCtesDAO(getSession());
			List lstDocNCAplicaFac = mCtasCtesDAO.getPagoAdelantadoAplicadaFacturaClienteV2(nroOperacion,nroSucursal,codCliente);
			
			if(lstDocNCAplicaFac.size()>0){
				lstDocumentoAplicados.addAll(lstDocNCAplicaFac);		
			}
				
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		
		
		if(lstDocumentoAplicados.size()>0){
			Iterator it = lstDocumentoAplicados.iterator();
			lstDocumentoAplicados= new ArrayList();
			DocumentoAplicadoTO documentoAplicadoTO= new DocumentoAplicadoTO();
			Object[] obj = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			BigDecimal saldo = new BigDecimal("0.00");
			while(it.hasNext()){
		        obj = (Object[]) it.next(); 
		        documentoAplicadoTO=new DocumentoAplicadoTO();
		        documentoAplicadoTO.setNombre(obj[0].toString());
		        
		        if(obj[1]!=null){
		        	documentoAplicadoTO.setNumero(obj[1].toString());
		        	
		        	if(obj[7].toString().trim().equals("NC")){
		        		documentoAplicadoTO.setNumero(documentoAplicadoTO.getNumero()+' '+obj[6].toString());
		        	}
		        }
		        if(obj[2]!=null){
		        	 documentoAplicadoTO.setFecha(sdf.format(obj[2]));
		        }
		       
		        documentoAplicadoTO.setBruto(new BigDecimal(obj[3].toString()));
		        saldo = totalFactura.add(documentoAplicadoTO.getBruto());	
		        
		        /*
		        if(saldo.abs().compareTo(new BigDecimal("0.099"))<0){
		        	saldo = new BigDecimal("0.00");
		        }*/
		        documentoAplicadoTO.setSaldo(saldo);
		        if(saldo.equals(new BigDecimal("0.00")) || saldo.abs().compareTo(new BigDecimal("0.099"))<0){
		        	 documentoAplicadoTO.setSaldo(documentoAplicadoTO.getSaldo().abs()); 
		        }
		       
		        totalFactura = documentoAplicadoTO.getSaldo();
		        pagoAplicadoTotal = pagoAplicadoTotal.add(documentoAplicadoTO.getBruto());
		        
		        lstDocumentoAplicados.add(documentoAplicadoTO);
		       
			}
		}
		
		return lstDocumentoAplicados;		
	}
	
	public List getCobranzasPorClienteFecha(Date fechaDesde, Date fechaHasta , String clienteDesde, String clienteHasta ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
					
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			
			if(clienteDesde.equals(""))clienteDesde=null;
			if(clienteHasta.equals(""))clienteHasta=null;
			params.put(Const.PARAM_CLIENTE_DESDE, clienteDesde);
			params.put(Const.PARAM_CLIENTE_HASTA, clienteHasta);
			
			Query query = this.getNamedQuery(MfacturasVDAO.FIND_COBRANZAS_BY_CLIENTE_FECHAS, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MFacturaVTO mFacturaVTO= new MFacturaVTO();
				Object[] objChofer= null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        mFacturaVTO=new MFacturaVTO();
			        mFacturaVTO.setOrder(new Integer(objChofer[0].toString()));			        
			        mFacturaVTO.setFecha(sdf.format(objChofer[1]));
			        
			        if(objChofer[9]!=null){
				        mFacturaVTO.setTipoComprobante(objChofer[9].toString());
				        //System.out.println(mFacturaVTO.getTipoComprobante());
				        if(mFacturaVTO.getTipoComprobante().trim().equals("FC") || mFacturaVTO.getTipoComprobante().trim().equals("F")){
				        	 mFacturaVTO.setTipo(mensajeria.getMessage().getString("factura_factura_label")+" "+objChofer[2].toString());
				        }else if(mFacturaVTO.getTipoComprobante().trim().equals("NC") || mFacturaVTO.getTipoComprobante().equals("N")){
				        	mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_credito_label"));
				        }else if(mFacturaVTO.getTipoComprobante().trim().equals("ND") ){
				        	mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_debito_label"));				        	
				        }	
			        }
			      		       
			        mFacturaVTO.setNroSucursal(new Integer(objChofer[3].toString()));
			        mFacturaVTO.setNroFactura(new Integer(objChofer[4].toString()));			        
			        mFacturaVTO.setNetoFactura(new BigDecimal(objChofer[5].toString()));
			        mFacturaVTO.setNroRemito(new Integer(objChofer[6].toString()));
			        mFacturaVTO.setCliDescripcion(objChofer[7].toString());
			        mFacturaVTO.setCodClienteAlfa(objChofer[8].toString());	
			        
			        if(new BigDecimal(objChofer[10].toString()).compareTo(new BigDecimal(1.00))>0){
			        	mFacturaVTO.setPagoAplicado(new BigDecimal(objChofer[10].toString()));
			        }else if( new BigDecimal(objChofer[10].toString()).compareTo(new BigDecimal(1.00))<0){
			        	mFacturaVTO.setPagoAplicado(new BigDecimal(0.00));
			        }
			        mFacturaVTO.setSaldo(new BigDecimal(objChofer[11].toString()));
			        mFacturaVTO.setFeVto(sdf.format(objChofer[12]));
			        lstPorFiltro.add(mFacturaVTO);
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
	
		
	public List getFacturasPorReciboCliente(Integer codCliente,Integer nroOperCaja ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
				System.out.println("nroOperCaja "+nroOperCaja);	
				System.out.println("codCliente "+codCliente);	
				
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			params.put(Const.PARAM_NRO_OPER_CAJA, nroOperCaja);	
			params.put(Const.PARAM_TIPO_OPERACION, Const.PARAM_COBRO_FACTURA_CLIENTE_CTA_CTE);	
			params.put(Const.PARAM_TIPO_OPERACION_DOS, Const.PARAM_APLICAADEL_FACTURA_CLIENTE);			
			
			Query query = this.getNamedQuery(MfacturasVDAO.FIND_FACTURAS_BY_RECIBO_CLIENTE, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MFacturaVTO mFacturaVTO= new MFacturaVTO();
				Object[] objChofer= null;
				

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        mFacturaVTO=new MFacturaVTO();
			        mFacturaVTO.setNroOperCaja(new Integer(objChofer[0].toString()));		
			        mFacturaVTO.setNroRecibo(new Integer(objChofer[1].toString()));	
			        mFacturaVTO.setFecha(sdf.format(objChofer[2]));	
			        mFacturaVTO.setTipo(mensajeria.getMessage().getString("factura_factura_label")+" "+objChofer[3].toString());			        
			        mFacturaVTO.setNroSucursal(new Integer(objChofer[4].toString()));			        
			        mFacturaVTO.setNroFactura(new Integer(objChofer[5].toString()));			        
			        mFacturaVTO.setNetoFactura(new BigDecimal(objChofer[6].toString()));		        
			        mFacturaVTO.setCliDescripcion(objChofer[7].toString());	
			        mFacturaVTO.setPagoAplicado(new BigDecimal(objChofer[8].toString()));	
			        mFacturaVTO.setSaldo(new BigDecimal(objChofer[9].toString()));	
			        mFacturaVTO.setCodClienteAlfa(objChofer[10].toString());	
			        if(mFacturaVTO.getSaldo().compareTo(new BigDecimal(0.00))<0){
			        	mFacturaVTO.setSaldo(new BigDecimal(0.00));			        	
			        }
			        
			        lstPorFiltro.add(mFacturaVTO);
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

	
	
	public List getRendicionesPendientesPorCCSS() throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
									
			Query query = this.getNamedQuery(MfacturasVDAO.FIND_CCSS_X_IMPORTES, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				RendicionPendienteTO rendicionPendienteTO= new RendicionPendienteTO();
				Object[] objChofer= null;
				

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        rendicionPendienteTO=new RendicionPendienteTO();
			        rendicionPendienteTO.setCodCCSS(new Integer(objChofer[0].toString()));		
			        rendicionPendienteTO.setDescrCCSS(objChofer[1].toString());	
			        rendicionPendienteTO.setImporte(new BigDecimal(objChofer[2].toString()));
			        
			        rendicionPendienteTO.setCantidadDias(obtenerCantidadDias(rendicionPendienteTO.getCodCCSS()));
			        
			        lstPorFiltro.add(rendicionPendienteTO);
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
	
	private long obtenerCantidadDias(Integer codCCSS) throws DataAccessErrorException{
		try{
			long cantDias=-1;
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CCSS, codCCSS);
			Query query = this.getNamedQuery(MfacturasVDAO.FIND_FECHA_MAXIMA_CCSS_FACTURA, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				Object[] objChofer= null;				

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaMax=null;
				String auxFecha=null;
				while(it.hasNext()){
			        objChofer = (Object[]) it.next();	
			        if(objChofer[1]==null){
			        	auxFecha=null;
			        }else{		        	
			        	auxFecha=sdf.format(objChofer[1]);
			        }
			       	       
				}
				
				if(auxFecha!=null){
					fechaMax= sdf.parse(auxFecha);
					cantDias= getCantidadDeDias(new Date(), fechaMax);
				}
				
			}else if(lstPorFiltro.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}		
	        
			return cantDias;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
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
	
	
	public List getRendicionesPorCCSS(Integer codCCSS) throws DataAccessErrorException{
		try{
			
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CCSS, codCCSS);
			Query query = this.getNamedQuery(MfacturasVDAO.FIND_RENDICIONES_POR_CCSS, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				Object[] objChofer= null;
				RendicionTO rendicionTO = null;
				while(it.hasNext()){
			        objChofer = (Object[]) it.next();	
			        rendicionTO= new RendicionTO();
			        rendicionTO.setCodRendicion(new Integer(objChofer[0].toString()));
			        rendicionTO.setDescRendicion(objChofer[0].toString());
			        lstPorFiltro.add(rendicionTO);      
				}		
				
			}else if(lstPorFiltro.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}		
	        
			return lstPorFiltro;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	
	public List getFacturasPorCCSSyNroRendicion(int codCCSS,Integer nroRendicion) throws DataAccessErrorException, NoExistenItemsException{
		try{
			
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(nroRendicion==null){
				nroRendicion=  new Integer(-1);	
			}			
			
			params.put(Const.PARAM_COD_CCSS, codCCSS);
			params.put(Const.PARAM_NRO_RENDICION, nroRendicion);
			Query query = this.getNamedQuery(MfacturasVDAO.FIND_FACTURAS_POR_RENDICIONES_POR_CCSS, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				Object[] objChofer= null;
				MFacturaVTO mFacturaVTO = null;
				 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
				while(it.hasNext()){
			        objChofer = (Object[]) it.next();	
			        mFacturaVTO= new MFacturaVTO();
			        mFacturaVTO.setFecha(sdf.format(objChofer[0]));
			        mFacturaVTO.setNroSucursal(new Integer(objChofer[1].toString()));
			        mFacturaVTO.setNroFactura(new Integer(objChofer[2].toString()));
			       
			        mFacturaVTO.setCodCliente(new Integer(objChofer[3].toString()));
			        mFacturaVTO.setCliDescripcion(objChofer[4].toString());
			        
			        mFacturaVTO.setCcss(objChofer[6].toString());
			        mFacturaVTO.setCodArticulo(new Integer(objChofer[7].toString()));
			        mFacturaVTO.setDescArticulo(objChofer[8].toString());
			        
			        mFacturaVTO.setCantidad(new BigDecimal(objChofer[9].toString()));
			        mFacturaVTO.setPrecioKilo(new BigDecimal(objChofer[10].toString()));
			        mFacturaVTO.setPreciototal(new BigDecimal(objChofer[11].toString()));
			        
			        mFacturaVTO.setPatente(objChofer[12].toString());
			        
			        mFacturaVTO.setDniChofer(objChofer[13].toString());
			        mFacturaVTO.setApellidoChofer(objChofer[14].toString());
			        mFacturaVTO.setNombreChofer(objChofer[15].toString());
			        mFacturaVTO.setNroRendicionRefipass(new Integer(objChofer[16].toString()));
			        mFacturaVTO.setMontoTotalSubdiario(new BigDecimal(objChofer[17].toString()));
			        
			        if (objChofer[18]!=null){
			           mFacturaVTO.setFechaRendicion(sdf.format(objChofer[18]));
			        }
			        
			        lstPorFiltro.add(mFacturaVTO);      
				}		
				
			}else if(lstPorFiltro.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}		
	        
			return lstPorFiltro;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	
	public List getMovimientoStock(Integer producto, Date fechaDesde,Date fechaHasta, Integer codCCSS,int codigoStock) throws NoExistenItemsException, DataAccessErrorException{
		try{
			
			Messages mensajeria = new Messages();
			List lstMovimientoTotal= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put(Const.PARAM_COD_PRODUCTO, producto);
			
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);			
			params.put(Const.PARAM_COD_CCSS, codCCSS);
			params.put(Const.COD_HEAD_OFICCE, Const.COD_CCSS_HEAD_OFICCE);			
			
			Query query = null;
			if(codigoStock==1){
				//viene por volumen vendido
			 params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			 query = this.getNamedQuery(MfacturasVDAO.FIND_MOVIMIENTO_STOCK, params, session);
			}else if(codigoStock==2){
				 query = this.getNamedQuery(MfacturasVDAO.FIND_STOCK, params, session);
			}
			
			lstMovimientoTotal = query.list();				
			
			if(lstMovimientoTotal.size()>0){
				Iterator it = lstMovimientoTotal.iterator();
				lstMovimientoTotal= new ArrayList();
				Object[] objChofer= null;
				MovimientoStockTO movimientoStockTO = null;
				Integer codProd = 0;
				Integer codCS = 0;
				BigDecimal litrosAux=new BigDecimal(0.00);
				while(it.hasNext()){
			        objChofer = (Object[]) it.next();	
			        movimientoStockTO= new MovimientoStockTO();
			        movimientoStockTO.setCodCCSS(new Integer(objChofer[0].toString()));
			        movimientoStockTO.setDescrCCSS(objChofer[1].toString());
			        movimientoStockTO.setCodProducto(new Integer(objChofer[2].toString()));
			        movimientoStockTO.setDescrProducto(objChofer[3].toString());
			        movimientoStockTO.setLitros(new BigDecimal(objChofer[4].toString()));	
			        
			        if(codigoStock==2){
			        	 movimientoStockTO.setLitrosdespachados(new BigDecimal(objChofer[5].toString()));	
			        }
			        
			      //  //System.out.println("ccss ->"+movimientoStockTO.getCodCCSS().toString()+ " prod ->"+movimientoStockTO.getCodProducto().toString());
			        if(codCS.equals(movimientoStockTO.getCodCCSS()) && codProd.equals(movimientoStockTO.getCodProducto())){
			        	litrosAux = ((MovimientoStockTO)lstMovimientoTotal.get(lstMovimientoTotal.size()-1)).getLitros();
			        	
			        	 if(codigoStock==1){
			        		 litrosAux = litrosAux.add(movimientoStockTO.getLitros());
			        	   ((MovimientoStockTO)lstMovimientoTotal.get(lstMovimientoTotal.size()-1)).setLitros(litrosAux);
			        	 }else if(codigoStock==2){
			        		 litrosAux = litrosAux.subtract(movimientoStockTO.getLitros());
			        	   ((MovimientoStockTO)lstMovimientoTotal.get(lstMovimientoTotal.size()-1)).setLitros(litrosAux);
			        	 }
			        	
			        }else{
			        	
			        	if(codigoStock==2){
			        		//movimientoStockTO.setLitros(movimientoStockTO.getLitros().subtract(movimientoStockTO.getLitrosdespachados()));				        	   
			        		movimientoStockTO.setLitros(movimientoStockTO.getLitrosdespachados().subtract(movimientoStockTO.getLitros()));
				        }
			        	
			        	lstMovimientoTotal.add(movimientoStockTO);      			        	
			        }
			       
			        codCS=movimientoStockTO.getCodCCSS();
			        codProd=movimientoStockTO.getCodProducto();
			        
			      //  //System.out.println("codCS ->"+codCS.toString()+ " codProd-> "+codProd.toString() );
			        
			        
				}
				
			}	
			
			if(lstMovimientoTotal.size()==0){
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
	
	
	public List getFacturaPendienteDeRendicion( Integer ccss ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CCSS, ccss);			
			Query query = this.getNamedQuery(MfacturasVDAO.FIND_FACTURAS_PENDIENTES_RENDICION, params, session);
			lstPorFiltro = query.list();			
			
			int tipo=0;
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MFacturaVTO mFacturaVTO= new MFacturaVTO();
				Object[] objChofer= null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				Integer suc=0,rem=0;
				BigDecimal saldo= new BigDecimal("0.00");
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        mFacturaVTO=new MFacturaVTO();
			        mFacturaVTO.setOrder(new Integer(objChofer[0].toString()));			        
			        mFacturaVTO.setFecha(sdf.format(objChofer[1]));
			        
			        if(objChofer[9]!=null){
				        mFacturaVTO.setTipoComprobante(objChofer[9].toString());
				       // //System.out.println(mFacturaVTO.getTipoComprobante());
				        if(mFacturaVTO.getTipoComprobante().trim().equals("FC") || mFacturaVTO.getTipoComprobante().trim().equals("F")){
				        	 mFacturaVTO.setTipo(mensajeria.getMessage().getString("factura_factura_label")+" "+objChofer[2].toString());
				        	 tipo=1;
				        }else if(mFacturaVTO.getTipoComprobante().trim().equals("NC") ){
				        	mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_credito_label"));
				        	 tipo=2;
				        }else if(mFacturaVTO.getTipoComprobante().trim().equals("ND") ){
				        	mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_debito_label"));
				        	 tipo=3;
				        }		
			        }
			       
			        mFacturaVTO.setNroSucursal(new Integer(objChofer[3].toString()));
			        mFacturaVTO.setNroFactura(new Integer(objChofer[4].toString()));			        
			        mFacturaVTO.setNetoFactura(new BigDecimal(objChofer[5].toString()));
			        mFacturaVTO.setNroRemito(new Integer(objChofer[6].toString()));
			        mFacturaVTO.setCliDescripcion(objChofer[7].toString());
			        mFacturaVTO.setCodClienteAlfa(objChofer[8].toString());			        
			       	mFacturaVTO.setSaldo(new BigDecimal(0.00));	
			        mFacturaVTO.setPagoAplicado(mFacturaVTO.getNetoFactura());
			        mFacturaVTO.setTipoPagoFactura(mensajeria.getMessage().getString("contado_abreviado_label"));
			        mFacturaVTO.setCondicion(2);			        
			        
			        mFacturaVTO.setIva(new BigDecimal(objChofer[12].toString()));
			        mFacturaVTO.setPerIVA(new BigDecimal(objChofer[13].toString()));
			        mFacturaVTO.setPerIIBB(new BigDecimal(objChofer[14].toString()));
			        mFacturaVTO.setTasaFondo(new BigDecimal(objChofer[15].toString()));
			        mFacturaVTO.setItc(new BigDecimal(objChofer[16].toString()));
			        mFacturaVTO.setNroAsientoContable(new BigDecimal(objChofer[17].toString()));
			        mFacturaVTO.setPrecioNeto(new BigDecimal(objChofer[18].toString()));			        
			       
			        if(objChofer[19]!=null){
			        	mFacturaVTO.setFeVto(sdf.format(objChofer[19]));
			        }
			        
			        mFacturaVTO.setCcss(objChofer[20].toString());
			        mFacturaVTO.setNroEjercicioContable(new BigDecimal(objChofer[21].toString()));
			        
			        //agrego la factura a la lista
			        lstPorFiltro.add(mFacturaVTO);
					
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
	
	
	public double obtenerTotalFacturadoCompleto(int codCliente){
		double totalFact= 0;
		try{
		
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CLIENTE, codCliente);			
			Query query = this.getNamedQuery("findTotalFacturadoCompleto", params, session);
			
			List lst = query.list();
			Iterator itTot = lst.iterator();
			while(itTot.hasNext()){
				totalFact= Double.parseDouble(itTot.next().toString()) ;
			}
			
			params=null;
			query=null;
			itTot=null;			
			lst=null;
			
		}catch( Exception e){			
			e.printStackTrace();
		}
		
		return totalFact;
	}
	
	
	
	
	
	public double obtenerTotalFacturadoDeMFacturasV(int codCliente){
		double totalFact= 0;
		try{
		
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CLIENTE, codCliente);			
			Query query = this.getNamedQuery("findTotalFacturadoDeMfactura", params, session);
			
			List lst = query.list();
			Iterator itTot = lst.iterator();
			while(itTot.hasNext()){
				totalFact= Double.parseDouble(itTot.next().toString()) ;
			}
			
			params=null;
			query=null;
			itTot=null;			
			lst=null;
			
		}catch( Exception e){			
			e.printStackTrace();
		}
		
		return totalFact;
	}
	
	public double obtenerTotalFacturadoDeMCtaCte(int codCliente){
		double totalFact= 0;
		try{
		
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CLIENTE, codCliente);			
			Query query = this.getNamedQuery("findTotalFacturadoDeMCtaCte", params, session);
			
			List lst = query.list();
			Iterator itTot = lst.iterator();
			while(itTot.hasNext()){
				totalFact= Double.parseDouble(itTot.next().toString()) ;
			}
			
			params=null;
			query=null;
			itTot=null;			
			lst=null;
			
		}catch( Exception e){			
			e.printStackTrace();
		}
		
		return totalFact;
	}
	
}