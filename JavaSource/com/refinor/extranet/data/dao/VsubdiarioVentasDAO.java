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

import com.refinor.extranet.data.base.BaseVsubdiarioVentasDAO;
import com.refinor.extranet.to.AsientoContableTO;
import com.refinor.extranet.to.MFacturaVTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class VsubdiarioVentasDAO extends BaseVsubdiarioVentasDAO implements com.refinor.extranet.data.dao.iface.VsubdiarioVentasDAO {

	public VsubdiarioVentasDAO () {}
	
	public VsubdiarioVentasDAO (Session session) {
		super(session);
	}

	public static String FIND_SUBDIARIO_VENTAS= "findSubDarioVentas";
	
	public List getSubDiarioPorClienteFecha(Integer codCliente, Date fechaDesde, Date fechaHasta, int condicion, int ccss ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();					
						
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			params.put(Const.PARAM_COD_CCSS, ccss);
			
			Query query = this.getNamedQuery(FIND_SUBDIARIO_VENTAS, params, session);
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
			              
			        mFacturaVTO.setFecha(sdf.format(objChofer[0]));
			        
			        if(objChofer[1]!=null){
				        mFacturaVTO.setTipoComprobante(objChofer[1].toString());				      
				        if(mFacturaVTO.getTipoComprobante().trim().equals("FC") || mFacturaVTO.getTipoComprobante().trim().equals("F")){
				        	 mFacturaVTO.setTipo(mensajeria.getMessage().getString("factura_factura_label"));				        	
				        }else if(mFacturaVTO.getTipoComprobante().trim().equals("NC") ){
				        	//mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_credito_label"));
				        	mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_credito_abreviada_label"));	
				        }else if(mFacturaVTO.getTipoComprobante().trim().equals("ND") ){
				        	//mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_debito_label"));
				        	mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_debito__abreviada_label"));
				        }		
			        }
			        
			        mFacturaVTO.setLetraComprobante(objChofer[2].toString());
			        mFacturaVTO.setCodClienteAlfa(objChofer[3].toString());	
			        mFacturaVTO.setCuit(objChofer[4].toString());	
			        mFacturaVTO.setCliDescripcion(objChofer[5].toString());
			        mFacturaVTO.setCcss(objChofer[6].toString());			       
			        mFacturaVTO.setNroSucursal(new Integer(objChofer[7].toString()));
			        mFacturaVTO.setNroFactura(new Integer(objChofer[8].toString()));	
			        
			        mFacturaVTO.setNetoGravado(new BigDecimal(objChofer[9].toString()));
			        mFacturaVTO.setNetoNoGravado(new BigDecimal(objChofer[10].toString()));
			        mFacturaVTO.setExento(new Integer(objChofer[11].toString()));
			        
			      		       
			        
			         mFacturaVTO.setIva(new BigDecimal(objChofer[12].toString()));
			         
			         if(mFacturaVTO.getTipoComprobante().trim().equals("NC") && mFacturaVTO.getIva().abs().compareTo(new BigDecimal("0.00"))==0){			        	
					     //cuando es nc debe salir en no gravado, para eso le pongo exento =1
					     mFacturaVTO.setExento(new Integer(1));
			        }else if(mFacturaVTO.getTipoComprobante().trim().equals("NC") && mFacturaVTO.getIva().compareTo(new BigDecimal("0.00"))<0){
			        	 //cuando es nc y tiene iva negativo debe salir en gravado, para eso le pongo exento =0
			        	//Marce - 18/06/2009
			        	mFacturaVTO.setNetoGravado(mFacturaVTO.getNetoNoGravado());
			        	 mFacturaVTO.setExento(new Integer(0));
			        }
			         
			         mFacturaVTO.setIva2(new BigDecimal(objChofer[13].toString()));
			         mFacturaVTO.setOtros(new BigDecimal(objChofer[14].toString()));
			         mFacturaVTO.setImpuestoInterno(new BigDecimal(objChofer[15].toString()));
			         mFacturaVTO.setTasaFondo(new BigDecimal(objChofer[16].toString()));
			         mFacturaVTO.setItc(new BigDecimal(objChofer[17].toString()));
			         mFacturaVTO.setPerIVA(new BigDecimal(objChofer[18].toString()));
			         mFacturaVTO.setPerIIBB(new BigDecimal(objChofer[19].toString()));
			         
			         mFacturaVTO.setTotal(new BigDecimal(objChofer[21].toString()));
			         
			        mFacturaVTO.setNroRemito(new Integer(objChofer[20].toString()));
			        
			        mFacturaVTO.setLeyCba(new BigDecimal(objChofer[23].toString()));
			        
			        mFacturaVTO.setCO2(new BigDecimal(objChofer[24].toString()));
			       
			        if(mFacturaVTO.getNroRemito()!=0){			        	
			        	mFacturaVTO.setTipoPagoFactura(mensajeria.getMessage().getString("contado_abreviado_label"));
			        	mFacturaVTO.setCondicion(2);
			        	
			        }else if(mFacturaVTO.getNroRemito().equals(new Integer(0))){
			        	mFacturaVTO.setTipoPagoFactura(mensajeria.getMessage().getString("cta_cte_abreviado_label"));
			        	mFacturaVTO.setCondicion(1);
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
	
	
	
	
	
	
	
	public List getSubDiarioPorClienteFecha2(Integer codCliente, Date fechaDesde, Date fechaHasta, int condicion, int ccss ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();					
						
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			params.put(Const.PARAM_COD_CCSS, ccss);
			
			Query query = this.getNamedQuery(FIND_SUBDIARIO_VENTAS, params, session);
			lstPorFiltro = query.list();			
			
			if(lstPorFiltro.size()>0){
				Iterator it = lstPorFiltro.iterator();
				lstPorFiltro= new ArrayList();
				MFacturaVTO mFacturaVTO= new MFacturaVTO();
				MFacturaVTO mFacturaVTOAux= new MFacturaVTO();
				Object[] objChofer= null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfMY = new SimpleDateFormat("MM / yy");
				SimpleDateFormat sdfD = new SimpleDateFormat("dd");
				
				String fechaAnt="";
			
				BigDecimal totPorDia=new BigDecimal("0.00");
				BigDecimal gravadoPorDia=new BigDecimal("0.00");
				BigDecimal noGravadoPorDia=new BigDecimal("0.00");
				BigDecimal ivaImpPorDia=new BigDecimal("0.00");
				BigDecimal ivaNoInscPorDia=new BigDecimal("0.00");
				BigDecimal ivaPercPorDia=new BigDecimal("0.00");
				BigDecimal iibbPercPorDia=new BigDecimal("0.00");
				BigDecimal itcPorDia=new BigDecimal("0.00");
				BigDecimal tasaPorDia=new BigDecimal("0.00");
				BigDecimal LeyCbaPorDia=new BigDecimal("0.00");
				BigDecimal co2PorDia=new BigDecimal("0.00");
				
				
				BigDecimal totPorMes=new BigDecimal("0.00");
				BigDecimal gravadoPorMes=new BigDecimal("0.00");
				BigDecimal noGravadoPorMes=new BigDecimal("0.00");
				BigDecimal ivaImpPorMes=new BigDecimal("0.00");
				BigDecimal ivaNoInscPorMes=new BigDecimal("0.00");
				BigDecimal ivaPercPorMes=new BigDecimal("0.00");
				BigDecimal iibbPercPorMes=new BigDecimal("0.00");
				BigDecimal itcPorMes=new BigDecimal("0.00");
				BigDecimal tasaPorMes=new BigDecimal("0.00");
				BigDecimal LeyCbaPorMes=new BigDecimal("0.00");
				BigDecimal co2PorMes=new BigDecimal("0.00");
				
				
				
				BigDecimal totPorGral=new BigDecimal("0.00");
				BigDecimal gravadoPorGral=new BigDecimal("0.00");
				BigDecimal noGravadoPorGral=new BigDecimal("0.00");
				BigDecimal ivaImpPorGral=new BigDecimal("0.00");
				BigDecimal ivaNoInscPorGral=new BigDecimal("0.00");
				BigDecimal ivaPercPorGral=new BigDecimal("0.00");
				BigDecimal iibbPercPorGral=new BigDecimal("0.00");
				BigDecimal itcPorGral=new BigDecimal("0.00");
				BigDecimal tasaPorGral=new BigDecimal("0.00");
				BigDecimal LeyCbaGral=new BigDecimal("0.00");
				BigDecimal co2Gral=new BigDecimal("0.00");
				
				Date fechaActAux=null;
				Date fechaAntAux=null;
				
				while(it.hasNext()){
			        objChofer = (Object[]) it.next(); 
			        mFacturaVTO=new MFacturaVTO();
			              
			        mFacturaVTO.setFecha(sdf.format(objChofer[0]));
			       
			        
			        	
			        	
			        if(objChofer[1]!=null){
				        mFacturaVTO.setTipoComprobante(objChofer[1].toString());				      
				        if(mFacturaVTO.getTipoComprobante().trim().equals("FC") || mFacturaVTO.getTipoComprobante().trim().equals("F")){
				        	 mFacturaVTO.setTipo(mensajeria.getMessage().getString("factura_factura_label"));				        	
				        }else if(mFacturaVTO.getTipoComprobante().trim().equals("NC") ){
				        	//mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_credito_label"));
				        	mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_credito_abreviada_label"));	
				        }else if(mFacturaVTO.getTipoComprobante().trim().equals("ND") ){
				        	//mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_debito_label"));
				        	mFacturaVTO.setTipo(mensajeria.getMessage().getString("nota_debito__abreviada_label"));
				        }		
			        }
			        
			        mFacturaVTO.setLetraComprobante(objChofer[2].toString());
			        mFacturaVTO.setCodClienteAlfa(objChofer[3].toString());	
			        mFacturaVTO.setCuit(objChofer[4].toString());	
			        mFacturaVTO.setCliDescripcion(objChofer[5].toString());
			        mFacturaVTO.setCcss(objChofer[6].toString());			       
			        mFacturaVTO.setNroSucursal(new Integer(objChofer[7].toString()));
			        mFacturaVTO.setNroFactura(new Integer(objChofer[8].toString()));	
			        
			        mFacturaVTO.setNetoGravado(new BigDecimal(objChofer[9].toString()));
			        mFacturaVTO.setNetoNoGravado(new BigDecimal(objChofer[10].toString()));
			        mFacturaVTO.setExento(new Integer(objChofer[11].toString()));
			        
			      		       
			        
			         mFacturaVTO.setIva(new BigDecimal(objChofer[12].toString()));
			         
			         if(mFacturaVTO.getTipoComprobante().trim().equals("NC") && mFacturaVTO.getIva().abs().compareTo(new BigDecimal("0.00"))==0){			        	
					     //cuando es nc debe salir en no gravado, para eso le pongo exento =1
					     mFacturaVTO.setExento(new Integer(1));
					     mFacturaVTO.setNetoGravado(new BigDecimal(0));
			        }else if(mFacturaVTO.getTipoComprobante().trim().equals("NC") && mFacturaVTO.getIva().compareTo(new BigDecimal("0.00"))<0){
			        	 //cuando es nc y tiene iva negativo debe salir en gravado, para eso le pongo exento =0
			        	//Marce - 18/06/2009
			        	
			        	mFacturaVTO.setNetoGravado(mFacturaVTO.getNetoNoGravado());
			        	//28072010
			        	mFacturaVTO.setNetoNoGravado(new BigDecimal(0));
			        	mFacturaVTO.setExento(new Integer(0));
			        }else{
//			        	28072010
			        	if(mFacturaVTO.getTipoComprobante().trim().equals("ND") && mFacturaVTO.getExento()!=new Integer(1)){
			        	      mFacturaVTO.setNetoNoGravado(new BigDecimal(0));
			        	      mFacturaVTO.setExento(new Integer(0));
			        	      //mFacturaVTO.setNetoGravado(new BigDecimal(0));
			        	}
			        }
			         
			         mFacturaVTO.setIva2(new BigDecimal(objChofer[13].toString()));
			         mFacturaVTO.setOtros(new BigDecimal(objChofer[14].toString()));
			         mFacturaVTO.setImpuestoInterno(new BigDecimal(objChofer[15].toString()));
			         mFacturaVTO.setTasaFondo(new BigDecimal(objChofer[16].toString()));
			         mFacturaVTO.setItc(new BigDecimal(objChofer[17].toString()));
			         mFacturaVTO.setPerIVA(new BigDecimal(objChofer[18].toString()));
			         mFacturaVTO.setPerIIBB(new BigDecimal(objChofer[19].toString()));
			         
			         mFacturaVTO.setTotal(new BigDecimal(objChofer[21].toString()));
			         mFacturaVTO.setPorcentajeIVA(new BigDecimal(objChofer[22].toString()));
			         mFacturaVTO.setLeyCba(new BigDecimal(objChofer[23].toString()));
			         mFacturaVTO.setCO2(new BigDecimal(objChofer[24].toString()));
				        
			        mFacturaVTO.setNroRemito(new Integer(objChofer[20].toString()));
			       
			        if(mFacturaVTO.getNroRemito()!=0){			        	
			        	mFacturaVTO.setTipoPagoFactura(mensajeria.getMessage().getString("contado_abreviado_label"));
			        	mFacturaVTO.setCondicion(2);
			        	
			        }else if(mFacturaVTO.getNroRemito().equals(new Integer(0))){
			        	mFacturaVTO.setTipoPagoFactura(mensajeria.getMessage().getString("cta_cte_abreviado_label"));
			        	mFacturaVTO.setCondicion(1);
			        }
			        mFacturaVTO.setDis(0);	
			       
			        if(!mFacturaVTO.getFecha().equals(fechaAnt)){			        	
			        	
			        	mFacturaVTOAux=new MFacturaVTO();
			        	mFacturaVTOAux.setCliDescripcion("Totales del día.........");
			        	
			        	if(!fechaAnt.trim().equals(""))
			        		mFacturaVTOAux.setTipoDocuCli(fechaAnt.substring(0,2));
			        	
			        	mFacturaVTOAux.setTotal(totPorDia);
			        	mFacturaVTOAux.setExento(2);
			        	mFacturaVTOAux.setNetoGravado(gravadoPorDia);
			        	mFacturaVTOAux.setNetoNoGravado(noGravadoPorDia);
			        	mFacturaVTOAux.setIva(ivaImpPorDia);
			        	mFacturaVTOAux.setPerIVA(ivaPercPorDia);
			        	mFacturaVTOAux.setPerIIBB(iibbPercPorDia);
			        	mFacturaVTOAux.setItc(itcPorDia);
			        	mFacturaVTOAux.setTasaFondo(tasaPorDia);
			        	mFacturaVTOAux.setLeyCba(LeyCbaPorDia);
			        	mFacturaVTOAux.setCO2(LeyCbaPorDia);
						 
			        	lstPorFiltro.add(mFacturaVTOAux);
			        	//total por mes
			        	totPorMes =  totPorMes.add(totPorDia);
			        	 gravadoPorMes=gravadoPorMes.add(gravadoPorDia);
						 noGravadoPorMes=noGravadoPorMes.add(noGravadoPorDia);
						 ivaImpPorMes=ivaImpPorMes.add(ivaImpPorDia);
						 ivaNoInscPorMes=ivaNoInscPorMes.add(ivaNoInscPorDia);
						 ivaPercPorMes=ivaPercPorMes.add(ivaPercPorDia);
						 iibbPercPorMes=iibbPercPorMes.add(iibbPercPorDia);
						 itcPorMes=itcPorMes.add(itcPorDia);
						 tasaPorMes=tasaPorMes.add(tasaPorDia);
						 LeyCbaPorMes=LeyCbaPorMes.add(LeyCbaPorDia);
						 co2PorMes=co2PorMes.add(co2PorDia);
			        	
			        	//limpio las variables
			        	totPorDia=new BigDecimal("0.00");
			        	 gravadoPorDia=new BigDecimal("0.00");
						 noGravadoPorDia=new BigDecimal("0.00");
						 ivaImpPorDia=new BigDecimal("0.00");
						 ivaNoInscPorDia=new BigDecimal("0.00");
						 ivaPercPorDia=new BigDecimal("0.00");
						 iibbPercPorDia=new BigDecimal("0.00");
						 itcPorDia=new BigDecimal("0.00");
						 tasaPorDia=new BigDecimal("0.00");
						 LeyCbaPorDia=new BigDecimal("0.00");
						 co2PorDia=new BigDecimal("0.00");
						 
			        	fechaAnt = "";
							
			        }
			        
			        
			        
			        
			        
			       // sdfMY POR MES
			        fechaActAux = sdf.parse(mFacturaVTO.getFecha());
			       
			        
			        if(!fechaAnt.equals("")){			        	
			        	 fechaAntAux = sdf.parse(fechaAnt);
			        }
			        
			        if(fechaAntAux!=null && !sdfMY.format(fechaActAux).equals(sdfMY.format(fechaAntAux))){	
			        	mFacturaVTOAux=new MFacturaVTO();
			        	mFacturaVTOAux.setCliDescripcion("Totales del mes.........");
			        	mFacturaVTOAux.setTipoDocuCli(sdfMY.format(fechaAntAux));
			        	mFacturaVTOAux.setTotal(totPorMes);
			        	mFacturaVTOAux.setExento(2);        	
			        	mFacturaVTOAux.setNetoGravado(gravadoPorMes);
			        	mFacturaVTOAux.setNetoNoGravado(noGravadoPorMes);
			        	mFacturaVTOAux.setIva(ivaImpPorMes);
			        	mFacturaVTOAux.setPerIVA(ivaPercPorMes);
			        	mFacturaVTOAux.setPerIIBB(iibbPercPorMes);
			        	mFacturaVTOAux.setItc(itcPorMes);
			        	mFacturaVTOAux.setTasaFondo(tasaPorMes);
			        	mFacturaVTOAux.setLeyCba(LeyCbaPorMes);
			        	mFacturaVTOAux.setCO2(co2PorMes);
			        	lstPorFiltro.add(mFacturaVTOAux);
			        	
//			        	acumulacion gral
					     totPorGral=totPorGral.add(totPorMes);
						 gravadoPorGral=gravadoPorGral.add(gravadoPorMes);
						 noGravadoPorGral=noGravadoPorGral.add(noGravadoPorMes);
						 ivaImpPorGral=ivaImpPorGral.add(ivaImpPorMes);
						 ivaNoInscPorGral=ivaNoInscPorGral.add(ivaNoInscPorMes);
						 ivaPercPorGral=ivaPercPorGral.add(ivaPercPorMes);
						 iibbPercPorGral=iibbPercPorGral.add(iibbPercPorMes);
						 itcPorGral=itcPorGral.add(itcPorMes);
						 tasaPorGral=tasaPorGral.add(tasaPorMes);
						 LeyCbaGral=LeyCbaGral.add(LeyCbaPorMes);
						 
						 co2Gral=co2Gral.add(co2PorMes);
						 
			        	//limlpio las variables
			        	totPorMes=new BigDecimal("0.00");		        	
			        	 gravadoPorMes=new BigDecimal("0.00");
						 noGravadoPorMes=new BigDecimal("0.00");
						 ivaImpPorMes=new BigDecimal("0.00");
						 ivaNoInscPorMes=new BigDecimal("0.00");
						 ivaPercPorMes=new BigDecimal("0.00");
						 iibbPercPorMes=new BigDecimal("0.00");
						 itcPorMes=new BigDecimal("0.00");
						 tasaPorMes=new BigDecimal("0.00");
						 LeyCbaPorMes=new BigDecimal("0.00");
						 co2PorMes=new BigDecimal("0.00");
						 
			        	fechaAnt = "";
							
			        }
			       	        
			        if(condicion==-1 || mFacturaVTO.getCondicion()==condicion){
			        	//dia
			        	 totPorDia =totPorDia.add( mFacturaVTO.getTotal());
			        	 gravadoPorDia= gravadoPorDia.add(mFacturaVTO.getNetoGravado());
			        	 
						 noGravadoPorDia=noGravadoPorDia.add(mFacturaVTO.getNetoNoGravado());
						 
						 
						 ivaImpPorDia=ivaImpPorDia.add(mFacturaVTO.getIva());
						 ivaNoInscPorDia=ivaNoInscPorDia.add(new BigDecimal("0.00"));
						 ivaPercPorDia=ivaPercPorDia.add(mFacturaVTO.getPerIVA());
						 iibbPercPorDia=iibbPercPorDia.add(mFacturaVTO.getPerIIBB());
						 itcPorDia=itcPorDia.add(mFacturaVTO.getItc());
						 tasaPorDia=tasaPorDia.add(mFacturaVTO.getTasaFondo());
			        	 fechaAnt=mFacturaVTO.getFecha();
			        	 LeyCbaPorDia=LeyCbaPorDia.add(mFacturaVTO.getLeyCba());
			        	 co2PorDia=co2PorDia.add(mFacturaVTO.getCO2());
			        	 
			        	  lstPorFiltro.add(mFacturaVTO);
			        	  
					}
			        
			        
			        
			        	
			        
				}
				
				if(lstPorFiltro.size()>0){
					
					mFacturaVTOAux=new MFacturaVTO();
		        	mFacturaVTOAux.setCliDescripcion("Totales del día.........");
		        	mFacturaVTOAux.setTipoDocuCli(fechaAnt.substring(0,2));
		        	mFacturaVTOAux.setTotal(totPorDia);
		        	mFacturaVTOAux.setExento(2);
		        	mFacturaVTOAux.setNetoGravado(gravadoPorDia);
		        	mFacturaVTOAux.setNetoNoGravado(noGravadoPorDia);
		        	mFacturaVTOAux.setIva(ivaImpPorDia);
		        	mFacturaVTOAux.setPerIVA(ivaPercPorDia);
		        	mFacturaVTOAux.setPerIIBB(iibbPercPorDia);
		        	mFacturaVTOAux.setItc(itcPorDia);
		        	mFacturaVTOAux.setTasaFondo(tasaPorDia);
		        	mFacturaVTOAux.setLeyCba(LeyCbaPorDia);
		        	mFacturaVTOAux.setCO2(co2PorDia);
		        	lstPorFiltro.add(mFacturaVTOAux);
		        
		        	//total del mes	
		        	totPorMes =  totPorMes.add(totPorDia);
		        	 gravadoPorMes=gravadoPorMes.add(gravadoPorDia);
					 noGravadoPorMes=noGravadoPorMes.add(noGravadoPorDia);
					 ivaImpPorMes=ivaImpPorMes.add(ivaImpPorDia);
					 ivaNoInscPorMes=ivaNoInscPorMes.add(ivaNoInscPorDia);
					 ivaPercPorMes=ivaPercPorMes.add(ivaPercPorDia);
					 iibbPercPorMes=iibbPercPorMes.add(iibbPercPorDia);
					 itcPorMes=itcPorMes.add(itcPorDia);
					 tasaPorMes=tasaPorMes.add(tasaPorDia);	
					 LeyCbaPorMes=LeyCbaPorMes.add(LeyCbaPorDia);
					 co2PorMes=co2PorMes.add(co2PorDia);
					 
				mFacturaVTOAux=new MFacturaVTO();
	        	mFacturaVTOAux.setCliDescripcion("Totales del mes.........");
	        	
	        	if(fechaAntAux==null){
	        		Date fecha = sdf.parse(fechaAnt);
	        		mFacturaVTOAux.setTipoDocuCli(sdfMY.format(fecha));
	        	}else{
	        	    mFacturaVTOAux.setTipoDocuCli(sdfMY.format(fechaAntAux));
	        	}
	        	
	        	mFacturaVTOAux.setTotal(totPorMes);
	        	mFacturaVTOAux.setExento(2);        	
	        	mFacturaVTOAux.setNetoGravado(gravadoPorMes);
	        	mFacturaVTOAux.setNetoNoGravado(noGravadoPorMes);
	        	mFacturaVTOAux.setIva(ivaImpPorMes);
	        	mFacturaVTOAux.setPerIVA(ivaPercPorMes);
	        	mFacturaVTOAux.setPerIIBB(iibbPercPorMes);
	        	mFacturaVTOAux.setLeyCba(LeyCbaPorMes);
	        	mFacturaVTOAux.setCO2(co2PorMes);
	        	mFacturaVTOAux.setItc(itcPorMes);
	        	mFacturaVTOAux.setTasaFondo(tasaPorMes);				 
	        	lstPorFiltro.add(mFacturaVTOAux);
	        	
	        	//total gral
	        	 totPorGral=totPorGral.add(totPorMes);
				 gravadoPorGral=gravadoPorGral.add(gravadoPorMes);
				 noGravadoPorGral=noGravadoPorGral.add(noGravadoPorMes);
				 ivaImpPorGral=ivaImpPorGral.add(ivaImpPorMes);
				 ivaNoInscPorGral=ivaNoInscPorGral.add(ivaNoInscPorMes);
				 ivaPercPorGral=ivaPercPorGral.add(ivaPercPorMes);
				 iibbPercPorGral=iibbPercPorGral.add(iibbPercPorMes);
				 LeyCbaGral=LeyCbaGral.add(LeyCbaPorMes);
				 itcPorGral=itcPorGral.add(itcPorMes);
				 tasaPorGral=tasaPorGral.add(tasaPorMes);
				 co2Gral=co2Gral.add(co2PorMes);
				 
	        	mFacturaVTOAux=new MFacturaVTO();
	        	mFacturaVTOAux.setCliDescripcion("Total General...........");
	        	mFacturaVTOAux.setTipoDocuCli("");
	        	mFacturaVTOAux.setTotal(totPorGral);
	        	mFacturaVTOAux.setExento(2);        	
	        	mFacturaVTOAux.setNetoGravado(gravadoPorGral);
	        	mFacturaVTOAux.setNetoNoGravado(noGravadoPorGral);
	        	mFacturaVTOAux.setIva(ivaImpPorGral);
	        	mFacturaVTOAux.setPerIVA(ivaPercPorGral);
	        	mFacturaVTOAux.setPerIIBB(iibbPercPorGral);
	        	mFacturaVTOAux.setLeyCba(LeyCbaGral);
	        	mFacturaVTOAux.setCO2(co2Gral);
	        	mFacturaVTOAux.setItc(itcPorGral);
	        	mFacturaVTOAux.setTasaFondo(tasaPorGral);				 
	        	lstPorFiltro.add(mFacturaVTOAux); 
				lstPorFiltro.remove(0);
					
				}
	      } else if(lstPorFiltro.size()==0){
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