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

import com.refinor.extranet.data.base.BaseMautorizacionDAO;
import com.refinor.extranet.to.MAutorizacionTO;
import com.refinor.extranet.to.MVehiculoTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class MautorizacionDAO extends BaseMautorizacionDAO implements com.refinor.extranet.data.dao.iface.MautorizacionDAO {

	public MautorizacionDAO () {}
	
	public MautorizacionDAO (Session session) {
		super(session);
	}
	
	public static String FIND_AUTORIZACIONES_POR_FILTRO= "findAutorizacionesPorFiltro";
	public static String FIND_AUTORIZACIONES_POR_SUC_NRO_COMP= "findAutorizacionesPorSucYNroCompr";
	
	public List getAutorizacionesPorFiltros(Integer codVendedor,Integer codCcss,Integer codCliente,
			Integer codMotivo,Integer codDescripcion,Integer codChofer, 
			Integer codVehiculo,Date fechaDesde, Date fechaHasta,
			Integer codProducto, Integer tipoCompManual,
			String nroAutorizacion, Integer sucursalManual, 
			Integer nroRemito1, Integer codAutorizacion,
			Integer empleado) throws DataAccessErrorException,NoExistenItemsException{
		
		try{
			Messages mensajeria = new Messages();
			List lstAutorizacionesPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();		
						
			if(codVendedor==null)codVendedor=new Integer(-1);
			params.put(Const.PARAM_COD_VENDEDOR, codVendedor);				
			if(codCcss==null)codCcss=new Integer(-1);
			params.put(Const.PARAM_COD_CCSS, codCcss);				
			if(codCliente==null)codCliente=new Integer(-1);
			params.put(Const.PARAM_COD_CLIENTE, codCliente);	
			if(codMotivo==null)codMotivo=new Integer(-1);
			params.put(Const.PARAM_COD_MOTIVO, codMotivo);
			if(codDescripcion==null)codDescripcion=new Integer(-1);
			params.put(Const.PARAM_COD_DESCRIPCION, codDescripcion);
			if(codChofer==null)codChofer=new Integer(-1);
			params.put(Const.PARAM_COD_CHOFER, codChofer);
			if(codVehiculo==null)codVehiculo=new Integer(-1);
			params.put(Const.PARAM_COD_VEHICULO, codVehiculo);			
			params.put(Const.PARAM_FECHA_DESDE,fechaDesde );
			params.put(Const.PARAM_FECHA_HASTA,fechaHasta );
			
			if(codProducto==null)codProducto=new Integer(-1);
			params.put(Const.PARAM_COD_PRODUCTO, codProducto);
			
			if(tipoCompManual==null)tipoCompManual=new Integer(-1);
			params.put(Const.PARAM_TIPO_COMPROBANTE_MANUAL, tipoCompManual);
			
			if(nroAutorizacion==null || nroAutorizacion.trim().equals(""))nroAutorizacion="%";
			params.put(Const.PARAM_NRO_AUTORIZACION, nroAutorizacion.trim());
			
			if(sucursalManual==null)sucursalManual=new Integer(-1);
			params.put(Const.PARAM_SUC_MANUAL, sucursalManual);
			
			if(nroRemito1==null)nroRemito1=new Integer(-1);
			params.put(Const.PARAM_NRO_REMITO_1, nroRemito1);
			
			if(codAutorizacion==null)codAutorizacion=new Integer(-1);
			params.put(Const.PARAM_COD_AUTORIZACION, codAutorizacion);
			
			if(empleado==null)empleado=new Integer(-1);
			params.put(Const.PARAM_COD_EMPLEADO, empleado);
			
			
			
			Query queryListadoQry = this.getNamedQuery(MautorizacionDAO.FIND_AUTORIZACIONES_POR_FILTRO, params, session);
			lstAutorizacionesPorFiltro= queryListadoQry.list();
			
			if(lstAutorizacionesPorFiltro.size()>0){
				Iterator itVehiculos = lstAutorizacionesPorFiltro.iterator();
				lstAutorizacionesPorFiltro= new ArrayList();
				MAutorizacionTO mAutorizacionTO= new MAutorizacionTO();
				Object[] objVehiculo= null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				while(itVehiculos.hasNext()){
					objVehiculo = (Object[]) itVehiculos.next(); 
			        mAutorizacionTO=new MAutorizacionTO();
			        
			        mAutorizacionTO.setCodAutorizacion(new Integer(objVehiculo[0].toString()));		
			        mAutorizacionTO.setCodVendedor(new Integer(objVehiculo[1].toString()));		
			        mAutorizacionTO.setVendApellido(objVehiculo[2].toString());		
			        mAutorizacionTO.setVendNombre(objVehiculo[3].toString());		
				      
			        mAutorizacionTO.setCodCcss(new Integer(objVehiculo[4].toString()));
			        mAutorizacionTO.setCcddDescripcion(objVehiculo[5].toString());
			        
			        mAutorizacionTO.setCodCliente(new Integer(objVehiculo[6].toString()));
			        mAutorizacionTO.setCliDescripcion(objVehiculo[7].toString());
			        
			        mAutorizacionTO.setCodMotivo(new Integer(objVehiculo[8].toString()));
			        mAutorizacionTO.setMotDescripcion(objVehiculo[9].toString());
			        
			        mAutorizacionTO.setCodDescripcion(new Integer(objVehiculo[10].toString()));
			        mAutorizacionTO.setDesDescripcion(objVehiculo[11].toString());
			        
			        mAutorizacionTO.setCodChofer(new Integer(objVehiculo[12].toString()));		
			        mAutorizacionTO.setChofApellido(objVehiculo[13].toString());		
			        mAutorizacionTO.setChofNombre(objVehiculo[14].toString());		

			        mAutorizacionTO.setCodVehiculo(new Integer(objVehiculo[15].toString()));		
			        mAutorizacionTO.setPatente(objVehiculo[16].toString());		
			        
			        if(objVehiculo[17]!=null){
			        	mAutorizacionTO.setFecha(sdf.format(objVehiculo[17]));		
			        }
			        
			        if(objVehiculo[18]!=null){
			        	mAutorizacionTO.setNroRemito1(new Integer(objVehiculo[18].toString()));		
			        }
			        
			        if(objVehiculo[19]!=null){
			        	mAutorizacionTO.setNroRemito2(new Integer(objVehiculo[19].toString()));				
			        }
			        
			        mAutorizacionTO.setCodNroAutorizacion(new Integer(objVehiculo[20].toString()));		
			        mAutorizacionTO.setNroAutorizacion(objVehiculo[21].toString());	
			        
			        if(objVehiculo[22]!=null){
			        	mAutorizacionTO.setLitrosACargar(new BigDecimal(objVehiculo[22].toString()));
			        }
			        
			        if(objVehiculo[23]!=null){
			        	mAutorizacionTO.setFechaPedido(sdf.format(objVehiculo[23]));	
			        }
			        
			        if(objVehiculo[24]!=null){
			        	mAutorizacionTO.setTipoComprobanteManual(new Integer(objVehiculo[24].toString()));
			        }
			        
			        mAutorizacionTO.setTcmDescripcion(objVehiculo[25].toString());
			        
			        
			        if(mAutorizacionTO.getTipoComprobanteManual()==6 || mAutorizacionTO.getTipoComprobanteManual()==7){
			        	mAutorizacionTO.setTieneAnula(new Boolean(true));
				        mAutorizacionTO.setTipoComprobanteAnular(new Integer(objVehiculo[26].toString()));
				        
				        if(mAutorizacionTO.getTipoComprobanteManual()==6)
				        	mAutorizacionTO.setTcaDescripcion("FACTURA");
				        else if(mAutorizacionTO.getTipoComprobanteManual()==7)
				        	mAutorizacionTO.setTcaDescripcion("REMITO");
			        }else  if(mAutorizacionTO.getTipoComprobanteManual()==1 || mAutorizacionTO.getTipoComprobanteManual()==2){
					    mAutorizacionTO.setTieneAnula(new Boolean(false));
			        }
			        mAutorizacionTO.setProducto(new Integer(objVehiculo[27].toString()));
			        mAutorizacionTO.setProdDecripcion(objVehiculo[28].toString());
			        
			        if(objVehiculo[29]!=null){
			        	 mAutorizacionTO.setSucursalManual(new Integer(objVehiculo[29].toString()));
			        }
			        
			        if(objVehiculo[30]!=null){			       
			        	mAutorizacionTO.setSucursalAnular(new Integer(objVehiculo[30].toString()));
			        }
			        
			        if(objVehiculo[31]!=null){			       
			        	mAutorizacionTO.setFechaUsadoAutorizacion(sdf.format(objVehiculo[31]));
			        }else{
			        	mAutorizacionTO.setFechaUsadoAutorizacion("");
			        }
			        
			        mAutorizacionTO.setCodEmisor(objVehiculo[32]==null?new Integer(0):new Integer(objVehiculo[32].toString()));
			        lstAutorizacionesPorFiltro.add(mAutorizacionTO);
				}				
				
			}else if(lstAutorizacionesPorFiltro.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}				
		
			return lstAutorizacionesPorFiltro;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	public Boolean getAutorizacionPorCompManual(Integer nroSuc, Integer nroComp) throws DataAccessErrorException{
		Boolean existe = new Boolean(false);
		try{
			Map<String, Object> params = new HashMap<String, Object>();		
			params.put(Const.PARAM_NRO_SUCURSAL, nroSuc);	
			params.put(Const.PARAM_COD_COMPROBANTE, nroComp);	
			
			Query queryListadoQry = this.getNamedQuery(MautorizacionDAO.FIND_AUTORIZACIONES_POR_SUC_NRO_COMP, params, session);
			List lstAutorizacionesPorFiltro= queryListadoQry.list();
			if(lstAutorizacionesPorFiltro.size()>0){
				existe = new Boolean(true);
			}else{
				existe = new Boolean(false);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}	
		return existe;
	}

}