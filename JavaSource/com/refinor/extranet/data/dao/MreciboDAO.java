package com.refinor.extranet.data.dao;
//import java.io.Console;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import org.apache.tools.ant.taskdefs.FixCRLF.AddAsisRemove;
import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.MjurisdiccionTrs;
import com.refinor.extranet.data.Mprovincias;
import com.refinor.extranet.data.Mrecibo;
import com.refinor.extranet.data.base.BaseMreciboDAO;
import com.refinor.extranet.to.DocumentoAplicadoTO;
import com.refinor.extranet.to.MChoferTO;
import com.refinor.extranet.to.MReciboTO;
import com.refinor.extranet.to.ReciboRetencionPresTO;
import com.refinor.extranet.to.ReciboRetencionTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class MreciboDAO extends BaseMreciboDAO implements com.refinor.extranet.data.dao.iface.MreciboDAO {

	public MreciboDAO () {}
	
	public MreciboDAO (Session session) {
		super(session);
	}

	public static String FIND_RECIBOS_BY_FECHAS= "findRecibosByFechas";
	public static String FIND_RECIBOS_BY_FECHAS_NRO_RECIBO_CLIENTE= "findRecibosByFechasNroReciboCliente";
	public static String FIND_RECIBOS_WITH_RETENCIONES= "findRecibosWithRetenciones";
	public static String FIND_RECIBOS_SIN_FACTURAS_BY_FECHAS_NRO_RECIBO_CLIENTE= "findRecibosSinFacturaByFechasNroReciboCliente";
	public static String FIND_RECIBOS_APLICADOS= "findRecibosAplicados";
	public static String FIND_RECIBOS_POR_NROOPERCAJA= "findRecibosPorNroCaja";
	
	
	public List getRecibosPorFecha(String codCliente, Date fechaDesde, Date fechaHasta, Integer nroRecDesde,Integer nroRecHasta , Boolean estadoRecibo ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
					
			if(codCliente==null) codCliente="%";
			params.put(Const.PARAM_COD_CLIENTE, codCliente.trim());
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			
			if(nroRecDesde!=null && nroRecHasta==null)
				nroRecHasta=nroRecDesde;
			
			if(nroRecDesde==null && nroRecHasta!=null)
				nroRecDesde=0;
			
			params.put(Const.PARAM_NRO_RECIBO_DESDE, nroRecDesde);
			params.put(Const.PARAM_NRO_RECIBO_HASTA, nroRecHasta);
			
			Query query = this.getNamedQuery(MreciboDAO.FIND_RECIBOS_BY_FECHAS, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MReciboTO mReciboTO= new MReciboTO();
				Object[] objChofer= null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        mReciboTO=new MReciboTO();
			        mReciboTO.setNumero(new Integer(objChofer[0].toString()));		
			        mReciboTO.setMontoTotal(new BigDecimal(objChofer[1].toString()));
			        mReciboTO.setFecha(sdf.format(objChofer[2]));		
			        mReciboTO.setCodCliente(new Integer(objChofer[3].toString()));	
			        mReciboTO.setCliDescripcion(objChofer[4].toString());	
			        mReciboTO.setNroOperCaja(new Integer(objChofer[5].toString()));
			        
			        if(objChofer[6]!=null){
			        	mReciboTO.setFechaBaja(sdf.format(objChofer[6]));	
			        	mReciboTO.setEstadoRecibo(Const.ANULADO);
			        }else{
			        	mReciboTO.setEstadoRecibo(Const.ACTIVO);
			        }
			      
			        if(estadoRecibo){
			        	//es anulado si es true
			        	if(mReciboTO.getEstadoRecibo().equals(Const.ANULADO)){			        
			        		lstPorFiltro.add(mReciboTO);
			        	}
			        }else{
			        	//es activo si es false
			        	if(mReciboTO.getEstadoRecibo().equals(Const.ACTIVO)){			        
			        		lstPorFiltro.add(mReciboTO);
			        	}
			        }
			        
				}
				
				if(lstPorFiltro.size()==0){
					throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
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
	
	
	
	public List getRecibosPorFechaNroReciboCliente(Date fechaDesde, Date fechaHasta, String clienteDesde,String clienteHasta, Integer nroRecDesde,Integer nroRecHasta ,Boolean estadoRecibo) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
							
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			
			params.put(Const.PARAM_NRO_RECIBO_DESDE, nroRecDesde);
			params.put(Const.PARAM_NRO_RECIBO_HASTA, nroRecHasta);
			
			if(clienteDesde.equals(""))clienteDesde=null;
			if(clienteHasta.equals(""))clienteHasta=null;
			params.put(Const.PARAM_CLIENTE_DESDE, clienteDesde);
			params.put(Const.PARAM_CLIENTE_HASTA, clienteHasta);
			
			
			Query query = this.getNamedQuery(MreciboDAO.FIND_RECIBOS_BY_FECHAS_NRO_RECIBO_CLIENTE, params, session);
			lstPorFiltro = query.list();
			
			query= this.getNamedQuery(MreciboDAO.FIND_RECIBOS_SIN_FACTURAS_BY_FECHAS_NRO_RECIBO_CLIENTE, params, session);
			List auxRecibos = query.list();
			
			if(auxRecibos.size()>0){
				lstPorFiltro.addAll(auxRecibos);
			}
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MReciboTO mReciboTO= new MReciboTO();
				Object[] objChofer= null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        mReciboTO=new MReciboTO();
			        
			        mReciboTO.setCodClienteAlfa(objChofer[0].toString());	
			        mReciboTO.setCliDescripcion(objChofer[1].toString());	
			        mReciboTO.setFecha(sdf.format(objChofer[2]));	
			        mReciboTO.setTipoComprobanteRecibo(objChofer[3].toString());
			        mReciboTO.setSucursalRecibo(new Integer(objChofer[4].toString()));			        
			        mReciboTO.setNumero(new Integer(objChofer[5].toString()));		
			        mReciboTO.setMontoTotal(new BigDecimal(objChofer[6].toString()));
			        
			        if(objChofer[7]!=null){
			        	mReciboTO.setTipoComprobanteFactura(mensajeria.getMessage().getString("factura_factura_label")+" "+objChofer[7].toString());
			        }
			        
			        if(objChofer[8]!=null){
			        	mReciboTO.setSucursalFactura(new Integer(objChofer[8].toString()));
			        }
			        
			        if(objChofer[9]!=null){
			        	mReciboTO.setNroFactura(new Integer(objChofer[9].toString())); 
			        }
			        
			        if(objChofer[10]!=null){
			        	mReciboTO.setFechaVto(sdf.format(objChofer[10])); 
			        }
			        
			        if(objChofer[11]!=null){
			        	 mReciboTO.setFechaBaja(sdf.format(objChofer[11])); 
			        	 mReciboTO.setEstadoRecibo(Const.ANULADO); 
			        }else{
			        	 mReciboTO.setEstadoRecibo(Const.ACTIVO); 
			        }
			        
			        mReciboTO.setNombreSucursal(objChofer[12].toString());
			        mReciboTO.setImporteRecibo(new BigDecimal(objChofer[13].toString()));
			       
			        
			        if(estadoRecibo){
			        	//es anulado si es true
			        	if(mReciboTO.getEstadoRecibo().equals(Const.ANULADO)){			        
			        		lstPorFiltro.add(mReciboTO);
			        	}
			        }else{
			        	//es activo si es false
			        	if(mReciboTO.getEstadoRecibo().equals(Const.ACTIVO)){			        
			        		lstPorFiltro.add(mReciboTO);
			        	}
			        }
				}
				
				if(lstPorFiltro.size()==0){
					throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
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
	
	
	
	
	public List getRecibosConRetenciones(Date fechaDesde, Date fechaHasta, String clienteDesde,String clienteHasta,int retencion, Boolean estadoRecibo) throws DataAccessErrorException,NoExistenItemsException{
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
			
			
			Query query = this.getNamedQuery(MreciboDAO.FIND_RECIBOS_WITH_RETENCIONES, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				ReciboRetencionTO mReciboTO= new ReciboRetencionTO();
				Object[] objChofer= null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        mReciboTO=new ReciboRetencionTO();
			        
			        mReciboTO.setNroRecibo(new Integer(objChofer[0].toString()));		
			        mReciboTO.setFechaRecibo(sdf.format(objChofer[1]));
			        
			        
			        mReciboTO.setCodIva(new Integer(objChofer[2].toString()));
			        mReciboTO.setTipoRetIva(objChofer[3].toString());
			        mReciboTO.setNroCertIva(new BigDecimal(objChofer[4].toString()));
			        if(objChofer[5]!=null)
			        	mReciboTO.setFechaIva(sdf.format(objChofer[5]));			        
			        mReciboTO.setImporteIva(new BigDecimal(objChofer[6].toString()));
			        
			        mReciboTO.setCodGanancias(new Integer(objChofer[7].toString()));
			        mReciboTO.setTipoRetGanancias(objChofer[8].toString());
			        mReciboTO.setNroCertGanancias(new BigDecimal(objChofer[9].toString()));
			        mReciboTO.setImporteGanancias(new BigDecimal(objChofer[10].toString()));
			        
			        if(objChofer[11]!=null)
			        	mReciboTO.setFechaGanancias(sdf.format(objChofer[11]));			        
			        mReciboTO.setCodIngresoBrutos(new Integer(objChofer[12].toString()));
			        mReciboTO.setTipoRetIngresoBrutos(objChofer[13].toString());
			        mReciboTO.setNroCertIngresoBrutos(new BigDecimal(objChofer[14].toString()));
			        mReciboTO.setImporteIngresoBrutos(new BigDecimal(objChofer[15].toString()));
			        if(objChofer[16]!=null)
			        	mReciboTO.setFechaIngresoBrutos(sdf.format(objChofer[16]));
			        mReciboTO.setJuridIngresoBrutos(new Integer(objChofer[17].toString()));
			        			        
			        mReciboTO.setCodSIJP(new Integer(objChofer[18].toString()));
			        mReciboTO.setTipoRetSIJP(objChofer[19].toString());
			        mReciboTO.setNroCertSIJP(new BigDecimal(objChofer[20].toString()));
			        if(objChofer[21]!=null)
			        	mReciboTO.setFechaSIJP(sdf.format(objChofer[21]));
			        mReciboTO.setImporteSIJP(new BigDecimal(objChofer[22].toString()));
				    
			        mReciboTO.setCodSegSocP(new Integer(objChofer[23].toString()));
			        mReciboTO.setTipoRetSegSoc(objChofer[24].toString());
			        mReciboTO.setNroCertSegSoc(new BigDecimal(objChofer[25].toString()));
			        if(objChofer[26]!=null)
			        	mReciboTO.setFechaSegSoc(sdf.format(objChofer[26]));
			        mReciboTO.setImporteSegSoc(new BigDecimal(objChofer[27].toString()));
				
			        mReciboTO.setCodTRS(new Integer(objChofer[28].toString()));
			        mReciboTO.setTipoRetTRS(objChofer[29].toString());
			        mReciboTO.setNroCertTRS(new BigDecimal(objChofer[30].toString()));
			        if(objChofer[31]!=null)
			        	mReciboTO.setFechaTRS(sdf.format(objChofer[31]));
			        mReciboTO.setImporteTRS(new BigDecimal(objChofer[32].toString()));
			        mReciboTO.setJuridTRS(new Integer(objChofer[33].toString()));
			  
			        
			        mReciboTO.setCodClienteAlfa(objChofer[34].toString());
			        mReciboTO.setCodClienteInt(new Integer(objChofer[35].toString()));				      
			        mReciboTO.setCliDescripcion(objChofer[36].toString());		       		        
			       
			        
			        if(objChofer[37]!=null){
			        	 mReciboTO.setFechaBaja(sdf.format(objChofer[37])); 
			        	 mReciboTO.setEstadoRecibo(Const.ANULADO); 
			        }else{
			        	 mReciboTO.setEstadoRecibo(Const.ACTIVO); 
			        }
			        
			   		        
			        mReciboTO.setCodTISSH(new Integer(objChofer[38].toString()));
			        mReciboTO.setTipoRetTISSH(objChofer[39].toString());
			        mReciboTO.setNroCertTISSH(new BigDecimal(objChofer[40].toString()));
			        if(objChofer[41]!=null)
			        	mReciboTO.setFechaCertTISSH(sdf.format(objChofer[41]));
			        mReciboTO.setImporteTISSH(new BigDecimal(objChofer[42].toString()));
			        
			        mReciboTO.setCodTMT(new Integer(objChofer[43].toString()));
			        mReciboTO.setTipoRetTMT(objChofer[44].toString());
			        mReciboTO.setNroCertTMTartagal(new BigDecimal(objChofer[45].toString()));
			        if(objChofer[46]!=null)
			        	mReciboTO.setFechaTmTartagal(sdf.format(objChofer[46]));
			        mReciboTO.setImporteImpTMTartagal(new BigDecimal(objChofer[47].toString()));
			        
			        
			        mReciboTO.setCodTEMTUc(new Integer(objChofer[48].toString()));
			        mReciboTO.setTipoRetTEMTUc(objChofer[49].toString());
			        mReciboTO.setNroPercTEMTUc(new BigDecimal(objChofer[50].toString()));
			        if(objChofer[51]!=null)
			        	mReciboTO.setFecPercTEMTUc(sdf.format(objChofer[51]));
			        mReciboTO.setImpPercTEMTUc(new BigDecimal(objChofer[52].toString()));
			        
			        //System.out.println("ggggg  tucuman");			
			        
			        if(estadoRecibo){
			        	//es anulado si es true
			        	if(mReciboTO.getEstadoRecibo().equals(Const.ANULADO)){			        
			        		lstPorFiltro.add(mReciboTO);
			        	}
			        }else{
			        	//es activo si es false
			        	if(mReciboTO.getEstadoRecibo().equals(Const.ACTIVO)){			        
			        		lstPorFiltro.add(mReciboTO);
			        	}
			        }
				}
				
				if(lstPorFiltro.size()==0){
					throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
				}
				
			}else if(lstPorFiltro.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}		
	        
			//armar una lista al revez
			List lstRetenciones = new ArrayList<ReciboRetencionPresTO>();
			ReciboRetencionPresTO reciboRetencionPresTO=null;
			ReciboRetencionTO reciboRetencionTO=null;
			for(int i=0;i<lstPorFiltro.size();i++){
				reciboRetencionPresTO= new ReciboRetencionPresTO();
				reciboRetencionTO= new ReciboRetencionTO();
				
				reciboRetencionTO= (ReciboRetencionTO)lstPorFiltro.get(i);
				
				for(int j=0; j<9;j++){
				//daton en comun
					reciboRetencionPresTO= new ReciboRetencionPresTO();
					reciboRetencionPresTO.setNroRecibo(reciboRetencionTO.getNroRecibo());
					reciboRetencionPresTO.setFechaRecibo(reciboRetencionTO.getFechaRecibo());
					reciboRetencionPresTO.setCodClienteAlfa(reciboRetencionTO.getCodClienteAlfa());
					reciboRetencionPresTO.setRazonSocial(reciboRetencionTO.getCliDescripcion());
					reciboRetencionPresTO.setEstadoRecibo(reciboRetencionTO.getEstadoRecibo());
					
					if(j==0){
						//iva
						reciboRetencionPresTO.setNroCertificado(reciboRetencionTO.getNroCertIva());
						reciboRetencionPresTO.setTipoRetencion(reciboRetencionTO.getTipoRetIva());
						reciboRetencionPresTO.setCodTipoRetencion(reciboRetencionTO.getCodIva());
						reciboRetencionPresTO.setFechaCertificado(reciboRetencionTO.getFechaIva());
						reciboRetencionPresTO.setImporte(reciboRetencionTO.getImporteIva());
						reciboRetencionPresTO.setDescrJuridiccion("-----");
					}else if(j==1){
						//ganancias
						reciboRetencionPresTO.setNroCertificado(reciboRetencionTO.getNroCertGanancias());
						reciboRetencionPresTO.setTipoRetencion(reciboRetencionTO.getTipoRetGanancias());
						reciboRetencionPresTO.setCodTipoRetencion(reciboRetencionTO.getCodGanancias());
						reciboRetencionPresTO.setFechaCertificado(reciboRetencionTO.getFechaGanancias());
						reciboRetencionPresTO.setImporte(reciboRetencionTO.getImporteGanancias());	
						reciboRetencionPresTO.setDescrJuridiccion("-----");
					}else if(j==2){
						//ingresos brutos
						reciboRetencionPresTO.setNroCertificado(reciboRetencionTO.getNroCertIngresoBrutos());
						reciboRetencionPresTO.setTipoRetencion(reciboRetencionTO.getTipoRetIngresoBrutos());
						reciboRetencionPresTO.setCodTipoRetencion(reciboRetencionTO.getCodIngresoBrutos());
						//reciboRetencionPresTO.setFechaCertificado(reciboRetencionTO.getFechaIngresoBrutos());
						reciboRetencionPresTO.setImporte(reciboRetencionTO.getImporteIngresoBrutos());
						reciboRetencionPresTO.setCodJuridiccion(reciboRetencionTO.getJuridIngresoBrutos());
						//obtener descripcion de la jurididccion
						System.out.println("iibb "+reciboRetencionPresTO.getNroCertificado());	
						if(!reciboRetencionPresTO.getNroCertificado().equals(new BigDecimal(0))){
							reciboRetencionPresTO.setDescrJuridiccion(obtenerJuridiccionIngresosBrutos(reciboRetencionTO.getJuridIngresoBrutos()));
						}
						
					}else if(j==3){
						//SIJP
						reciboRetencionPresTO.setNroCertificado(reciboRetencionTO.getNroCertSIJP());
						reciboRetencionPresTO.setTipoRetencion(reciboRetencionTO.getTipoRetSIJP());
						reciboRetencionPresTO.setCodTipoRetencion(reciboRetencionTO.getCodSIJP());
						reciboRetencionPresTO.setFechaCertificado(reciboRetencionTO.getFechaSIJP());
						reciboRetencionPresTO.setImporte(reciboRetencionTO.getImporteSIJP());					
						reciboRetencionPresTO.setDescrJuridiccion("-----");
					}else if(j==4){
						//seguridad social
						reciboRetencionPresTO.setNroCertificado(reciboRetencionTO.getNroCertSegSoc());
						reciboRetencionPresTO.setTipoRetencion(reciboRetencionTO.getTipoRetSegSoc());
						reciboRetencionPresTO.setCodTipoRetencion(reciboRetencionTO.getCodSegSocP());
						reciboRetencionPresTO.setFechaCertificado(reciboRetencionTO.getFechaSegSoc());
						reciboRetencionPresTO.setImporte(reciboRetencionTO.getImporteSegSoc());	
						reciboRetencionPresTO.setDescrJuridiccion("-----");
					}else if(j==5){
						//TRS
						reciboRetencionPresTO.setNroCertificado(reciboRetencionTO.getNroCertTRS());
						reciboRetencionPresTO.setTipoRetencion(reciboRetencionTO.getTipoRetTRS());
						reciboRetencionPresTO.setCodTipoRetencion(reciboRetencionTO.getCodTRS());
						reciboRetencionPresTO.setFechaCertificado(reciboRetencionTO.getFechaTRS());
						reciboRetencionPresTO.setImporte(reciboRetencionTO.getImporteTRS());
						reciboRetencionPresTO.setCodJuridiccion(reciboRetencionTO.getJuridTRS());
						//obtener descripcion de la jurididccion
						if(!reciboRetencionPresTO.getNroCertificado().equals(new BigDecimal(0))){
							reciboRetencionPresTO.setDescrJuridiccion(obtenerJuridiccionTRS(reciboRetencionTO.getJuridTRS()));
						}
					}else if(j==6){
						//seguridad social
						reciboRetencionPresTO.setNroCertificado(reciboRetencionTO.getNroCertTISSH());
						reciboRetencionPresTO.setTipoRetencion(reciboRetencionTO.getTipoRetTISSH());
						reciboRetencionPresTO.setCodTipoRetencion(reciboRetencionTO.getCodTISSH());
						reciboRetencionPresTO.setFechaCertificado(reciboRetencionTO.getFechaCertTISSH());
						reciboRetencionPresTO.setImporte(reciboRetencionTO.getImporteTISSH());	
						reciboRetencionPresTO.setDescrJuridiccion("-----");
					}else if(j==7){
						//seguridad social
						reciboRetencionPresTO.setNroCertificado(reciboRetencionTO.getNroCertTMTartagal());
						reciboRetencionPresTO.setTipoRetencion(reciboRetencionTO.getTipoRetTMT());
						reciboRetencionPresTO.setCodTipoRetencion(reciboRetencionTO.getCodTMT());
						reciboRetencionPresTO.setFechaCertificado(reciboRetencionTO.getFechaTmTartagal());
						reciboRetencionPresTO.setImporte(reciboRetencionTO.getImporteImpTMTartagal());	
						reciboRetencionPresTO.setDescrJuridiccion("-----");
					}else if(j==8){
						//seguridad social
						System.out.println("ggggg  tucuman");			
						reciboRetencionPresTO.setNroCertificado(reciboRetencionTO.getNroPercTEMTUc());
						reciboRetencionPresTO.setTipoRetencion(reciboRetencionTO.getTipoRetTEMTUc());
						reciboRetencionPresTO.setCodTipoRetencion(reciboRetencionTO.getCodTEMTUc());
						reciboRetencionPresTO.setFechaCertificado(reciboRetencionTO.getFecPercTEMTUc());
						reciboRetencionPresTO.setImporte(reciboRetencionTO.getImpPercTEMTUc());	
						reciboRetencionPresTO.setDescrJuridiccion("-----");
					}
					
					
					
					
					if(!reciboRetencionPresTO.getNroCertificado().equals(new BigDecimal(0))){
						//solo lo agrego cuando tengo un nro de certificado distinto de cero.
						
						//filtrar por nro de retencion
						if(retencion==-1 || reciboRetencionPresTO.getCodTipoRetencion()==retencion){
							lstRetenciones.add(reciboRetencionPresTO);
						}
					}
				}
				
				
				
			}
			
			if(lstRetenciones.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}
			return lstRetenciones;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public String obtenerJuridiccionIngresosBrutos(int codJuridiccion) throws DataAccessErrorException{
		try{
			Mprovincias mProvincias= new Mprovincias();
			MprovinciasDAO mProvinciasDAO = new MprovinciasDAO(getSession());			
			mProvincias = mProvinciasDAO.load(codJuridiccion, getSession());
			return mProvincias.getDescripcion();			
		}catch(Exception ex){
			ex.printStackTrace();
			return "";
		}
		
	}
	
	public String obtenerJuridiccionTRS(int codJuridiccion) throws DataAccessErrorException{
		try{
			MjurisdiccionTrs mJurisdiccionTrs= new MjurisdiccionTrs();
			MjurisdiccionTrsDAO mJurisdiccionTrsDAO = new MjurisdiccionTrsDAO(getSession());			
			mJurisdiccionTrs = mJurisdiccionTrsDAO.load(codJuridiccion, getSession());
			return mJurisdiccionTrs.getDescripcion();			
		}catch(Exception ex){
			ex.printStackTrace();
			return "";
		}
		
	}
	
	
	
	
	public List getRecibosAplicados(Integer nroOperacion, Integer nroSucursal, Integer codCliente) throws DataAccessErrorException
	{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();					
					
			params.put(Const.PARAM_NRO_OPERACION, nroOperacion);
			params.put(Const.PARAM_NRO_SUCURSAL, nroSucursal);
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			
			Query query = this.getNamedQuery(MreciboDAO.FIND_RECIBOS_APLICADOS, params, session);
			lstPorFiltro = query.list();	        
			return lstPorFiltro;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	
	public Mrecibo getRecibosPorNroOperCaja(Integer nroOperCaja, Integer codCliente) throws DataAccessErrorException
	{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();					
					
			params.put(Const.PARAM_NRO_OPER_CAJA, nroOperCaja);
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			
			Query query = this.getNamedQuery(MreciboDAO.FIND_RECIBOS_POR_NROOPERCAJA, params, session);
			lstPorFiltro = query.list();	        
			return (Mrecibo)lstPorFiltro.get(0);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public double obtenerTotalReciboCompleto(int codCliente){
		double total= 0;
		try{
		
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CLIENTE, codCliente);			
			Query query = this.getNamedQuery("findTotalReciboCompleto", params, session);
			
			List lst = query.list();
			Iterator itTot = lst.iterator();
			while(itTot.hasNext()){
				total= Double.parseDouble(itTot.next().toString()) ;
			}
			
			params=null;
			query=null;
			itTot=null;			
			lst=null;
			
		}catch( Exception e){			
			e.printStackTrace();
		}
		
		return total;
	}
	
	public double obtenerTotalReciboMrecibo(int codCliente){
		double total= 0;
		try{
		
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CLIENTE, codCliente);			
			Query query = this.getNamedQuery("findTotalReciboMrecibo", params, session);
			
			List lst = query.list();
			Iterator itTot = lst.iterator();
			while(itTot.hasNext()){
				total= Double.parseDouble(itTot.next().toString()) ;
			}
			
			params=null;
			query=null;
			itTot=null;			
			lst=null;
			
		}catch( Exception e){			
			e.printStackTrace();
		}
		
		return total;
	}
	

	public double obtenerTotalReciboMctacte(int codCliente){
		double total= 0;
		try{
		
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CLIENTE, codCliente);			
			Query query = this.getNamedQuery("findTotalReciboMCtaCte", params, session);
			
			List lst = query.list();
			Iterator itTot = lst.iterator();
			while(itTot.hasNext()){
				total= Double.parseDouble(itTot.next().toString()) ;
			}
			
			params=null;
			query=null;
			itTot=null;			
			lst=null;
			
		}catch( Exception e){			
			e.printStackTrace();
		}
		
		return total;
	}
}