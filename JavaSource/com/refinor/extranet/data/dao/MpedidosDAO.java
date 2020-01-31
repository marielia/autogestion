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

import com.refinor.extranet.data.Mchofer;
import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.base.BaseMpedidosDAO;
import com.refinor.extranet.to.AnioTO;
import com.refinor.extranet.to.ConsumoTO;
import com.refinor.extranet.to.CuposTO;
import com.refinor.extranet.to.ImpuestoLeyVialCbaTO;
import com.refinor.extranet.to.MChoferTO;
import com.refinor.extranet.to.MReciboTO;
import com.refinor.extranet.to.MesTO;
import com.refinor.extranet.to.RemitoTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.DataUtil;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class MpedidosDAO extends BaseMpedidosDAO implements com.refinor.extranet.data.dao.iface.MpedidosDAO {

	public MpedidosDAO () {}
	
	public MpedidosDAO (Session session) {
		super(session);
	}	
	
	public static String FIND_REMITOS_BY_CLIENTE_PATENTE= "findRemitosByClientePatente";
	public static String FIND_REMITOS_BY_CLIENTE_PATENTE_FAMILIA= "findRemitosByClientePatenteFamilia";
	public static String FIND_REMITOS_BY_CLIENTE_PATENTE_GRUPO= "findRemitosByClientePatenteGrupo";
	
	public static String OBTENER_SUMA_CONSUMO_POR_CLIENTE_PATENTE= "obtenerSumaConsumoPorClientePatente";
	public static String FIND_CONSUMOS_VEHICULOS= "findConsumosVehiculos";
	public static String FIND_CONSUMOS_CHOFERES= "findConsumosChoferes";	
	public static String FIND_CONSUMOS_GRUPO_VEHICULOS= "findConsumosPorGrupoVehiculos";
	public static String FIND_CONSUMOS_GRUPO_CHOFERES= "findConsumosPorGrupoChoferes";	
	public static String FIND_REMITOS_BY_CLIENTE_PATENTE_PRODUCTO_FECHAS= "findRemitosByClientePatenteProductoFechas";
	public static String FIND_REMITOS_BY_CLIENTE_DNI_PRODUCTO_FECHAS= "findRemitosByClienteDNIProductoFechas";
	public static String FIND_REMITOS_BY_CLIENTE_PRODUCTO_FECHAS= "findRemitosByClienteProductoFechas";
	public static String FIND_REMITOS_BY_CLIENTE= "findRemitosByCliente";
	public static String FIND_REMITOS_A_FACTURAR= "findRemitosAFacturar";
	public static String FIND_REMITOS_BY_CLIENTE_ORDEN_FACTURA= "findRemitosPorCienteyOrdenFactura";
	public static String FIND_REMITOS_BY_CLIENTE_REMITO_SUCURSAL= "findRemitosPorCienteRemitoSucursal";	
	public static String FIND_REMITOS_FACTURADOS_EN_MFACTURAV= "findRemitosFacturadoEnMFacturaV";
	public static String FIND_REMITOS_FACTURADOS_EN_MREMITOFACTURA= "findRemitosFacturadoEnMRemitoFactura";
	public static String FIND_REMITOS_FACTURADOS_EN_MREMITOFACTURA_REFACTURADOS= "findRemitosFacturadoEnMRemitoFacturaRefacturados";
	
	
	public static String FIND_REMITOS_NO_FACTURADOS= "findRemitosNoFacturados";
	public static String FIND_REMITOS_NO_FACTURADOS_NO_REFACTURADOS= "findRemitosNoFacturadosNoRefacturados";
	
	public static String FIND_MESES_M_PEDIDOS= "finMesesMPedidos";
	public static String FIND_ANIOS_M_PEDIDOS= "finAniosMPedidos";	
	public static String FIND_REMITOS_FACTURADOS_Y_NO_FACTURADOS_V2= "findRemitosFacturadYNoFacturadosV_2";
	
	
	public static String FIND_VENTAS = "findVentas";	
	public static String FIND_ALICUOTA= "findAlicuota";	
	
	
	public List getRemitosXClienteXRemitoXSucursal(String codCliente,Integer nroRemito,Integer nroSucursal) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();					
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			params.put(Const.PARAM_NRO_REMITO, nroRemito);	
			params.put(Const.PARAM_NRO_SUCURSAL, nroSucursal);	
			
			Query query = this.getNamedQuery(MpedidosDAO.FIND_REMITOS_BY_CLIENTE_REMITO_SUCURSAL, params, session);
			lstRemitos= query.list();
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();
				lstRemitos= new ArrayList();
				RemitoTO remitoTO= new RemitoTO();
				Object[] objRemito= null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
			
				
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next(); 
			        remitoTO=new RemitoTO();
			        
			        remitoTO.setFecha(sdf.format(objRemito[1]));
			        remitoTO.setHora(sdfHora.format(objRemito[1]));
			        remitoTO.setNroSucursal(new Integer(objRemito[2].toString()));			        
			        remitoTO.setNroRemito(new Integer(objRemito[3].toString()));			        
			        remitoTO.setCodProducto(new Integer(objRemito[4].toString()));			        
			        remitoTO.setDescProducto(objRemito[5].toString());
			        remitoTO.setLitros(new BigDecimal(objRemito[6].toString()));
			        remitoTO.setMontoTotal(new BigDecimal(objRemito[7].toString()));
			        remitoTO.setPatente(objRemito[8].toString());		
			        remitoTO.setApellidoChofer(objRemito[9].toString());
			        remitoTO.setNombreChofer(objRemito[10].toString());
			        
			        remitoTO.setCodUnidadNegocioV(new Integer(objRemito[11].toString()));
			        remitoTO.setDescrUnidadNegocioV(objRemito[12].toString());
			        remitoTO.setCodGrupoUNV(new Integer(objRemito[13].toString()));
			        remitoTO.setDescrGrupoUNV(objRemito[14].toString());
					
			        remitoTO.setCodUnidadNegocioC(new Integer(objRemito[15].toString()));
			        remitoTO.setDescrUnidadNegocioC(objRemito[16].toString());
			        remitoTO.setCodGrupoUNC(new Integer(objRemito[17].toString()));
			        remitoTO.setDescrGrupoUNC(objRemito[18].toString());	
			        remitoTO.setCcss(objRemito[19].toString());	
			        
			        remitoTO.setCodClienteAlfa(objRemito[21].toString());
			        remitoTO.setCliDescripcion(objRemito[22].toString());
			        
			        remitoTO.setPrecioConImpuestos(new BigDecimal(objRemito[23].toString()));
			        
	        lstRemitos.add(remitoTO);
				}
				
				
			}else if(lstRemitos.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}
		
	        
			return lstRemitos;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
		public List getRemitosClienteYOrdenFactura(String codCliente,Integer ordenFactura) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();			
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			params.put(Const.PARAM_ORDEN_FACTURA, ordenFactura);	
			
			Query query = this.getNamedQuery(MpedidosDAO.FIND_REMITOS_BY_CLIENTE_ORDEN_FACTURA, params, session);
			lstRemitos= query.list();
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();
				lstRemitos= new ArrayList();
				RemitoTO remitoTO= new RemitoTO();
				Object[] objRemito= null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
				Integer suc=0,rem=0;
				
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next(); 
			        remitoTO=new RemitoTO();
			        
			        remitoTO.setFecha(sdf.format(objRemito[1]));
			        remitoTO.setHora(sdfHora.format(objRemito[1]));
			        remitoTO.setNroSucursal(new Integer(objRemito[2].toString()));			        
			        remitoTO.setNroRemito(new Integer(objRemito[3].toString()));	
			        remitoTO.setMontoTotal(new BigDecimal(objRemito[7].toString()));
			      		      		        
			        remitoTO.setCodProducto(new Integer(objRemito[4].toString()));			        
			        remitoTO.setDescProducto(objRemito[5].toString());
			        remitoTO.setLitros(new BigDecimal(objRemito[6].toString()));
			       
			        remitoTO.setPatente(objRemito[8].toString());	
			        
			        if(objRemito[9]!=null){
			        	remitoTO.setApellidoChofer(objRemito[9].toString());
			        }
			        
			        if(objRemito[10]!=null){
			        	 remitoTO.setNombreChofer(objRemito[10].toString());
			        }
			        
			        remitoTO.setCodUnidadNegocioV(new Integer(objRemito[11].toString()));
			        remitoTO.setDescrUnidadNegocioV(objRemito[12].toString());
			        remitoTO.setCodGrupoUNV(new Integer(objRemito[13].toString()));
			        remitoTO.setDescrGrupoUNV(objRemito[14].toString());
					
			        
			        if(objRemito[15]!=null){
			        	 remitoTO.setCodUnidadNegocioC(new Integer(objRemito[15].toString()));
			        }
			        if(objRemito[16]!=null){
			        	remitoTO.setDescrUnidadNegocioC(objRemito[16].toString());
			        }
			        if(objRemito[17]!=null){
			        	 remitoTO.setCodGrupoUNC(new Integer(objRemito[17].toString()));
			        }
			        
			        if(objRemito[18]!=null){
			        	 remitoTO.setDescrGrupoUNC(objRemito[18].toString());	
			        }	       
			        
			        remitoTO.setCcss(objRemito[19].toString());	
			        
			        remitoTO.setCodClienteAlfa(objRemito[21].toString());	
			        remitoTO.setCliDescripcion(objRemito[22].toString());	
			        remitoTO.setPrecioConImpuestos(new BigDecimal(objRemito[23].toString()));	
			        
			        
			        lstRemitos.add(remitoTO);
				}
				
				
			}else if(lstRemitos.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}
		
	        
			return lstRemitos;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public BigDecimal getTotalRemitosAFacturar(String codClienteAlfa) throws DataAccessErrorException,NoExistenItemsException{
		try{
			
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(!session.isOpen()) session = (new MpedidosDAO()).getSession();			
			
					
			
			int codClienteInt=-1;
			
			if(codClienteAlfa!=null){
				MclientesDAO mclienteDAO = new MclientesDAO(session);
				Mclientes mclientes = mclienteDAO.getClienteporCodClienteAlfa(codClienteAlfa);	
				codClienteInt = mclientes.getCodigo();
			}
			
			params.put(Const.PARAM_COD_CLIENTE, codClienteInt);	
			
			
			Query query = this.getNamedQuery(MpedidosDAO.FIND_REMITOS_A_FACTURAR, params, session);
			lstRemitos= query.list();
			BigDecimal total= new BigDecimal(0.00);
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();				
						
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
	
	
	public BigDecimal getTotalRemitosAFacturar(int codCliente) throws DataAccessErrorException,NoExistenItemsException{
		try{
			
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(!session.isOpen()) session = (new MpedidosDAO()).getSession();		
			
			params.put(Const.PARAM_COD_CLIENTE, codCliente);	
			
			
			Query query = this.getNamedQuery(MpedidosDAO.FIND_REMITOS_A_FACTURAR, params, session);
			lstRemitos= query.list();
			BigDecimal total= new BigDecimal(0.00);
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();				
						
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
	
	public List getRemitosPorCliente(String codCliente) throws DataAccessErrorException,NoExistenItemsException{
			try{
				Messages mensajeria = new Messages();
				List lstRemitos= new ArrayList();
				Map<String, Object> params = new HashMap<String, Object>();
				if(codCliente==null)codCliente="%";
				params.put(Const.PARAM_COD_CLIENTE, codCliente);				
				
				Query query = this.getNamedQuery(MpedidosDAO.FIND_REMITOS_BY_CLIENTE, params, session);
				lstRemitos= query.list();
				
				if(lstRemitos.size()>0){
					Iterator itRemitos = lstRemitos.iterator();
					lstRemitos= new ArrayList();
					RemitoTO remitoTO= new RemitoTO();
					Object[] objRemito= null;
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
					
					while(itRemitos.hasNext()){
				        objRemito = (Object[]) itRemitos.next(); 
				        remitoTO=new RemitoTO();
				        
				        remitoTO.setFecha(sdf.format(objRemito[1]));	
				        remitoTO.setHora(sdfHora.format(objRemito[1]));
				        remitoTO.setNroSucursal(new Integer(objRemito[2].toString()));
				        if(objRemito[3]!=null)
				        	remitoTO.setNroRemito(new Integer(objRemito[3].toString()));
				        
				        remitoTO.setCodProducto(new Integer(objRemito[4].toString()));			        
				        remitoTO.setDescProducto(objRemito[5].toString());
				        remitoTO.setLitros(new BigDecimal(objRemito[6].toString()));
				        remitoTO.setMontoTotal(new BigDecimal(objRemito[7].toString()));
				        remitoTO.setPatente(objRemito[8].toString());
				         
				        if(objRemito[9]!=null)
				        	remitoTO.setApellidoChofer(objRemito[9].toString());
				        
				        if(objRemito[10]!=null)
				        	remitoTO.setNombreChofer(objRemito[10].toString());
				        
				        remitoTO.setCodUnidadNegocioV(new Integer(objRemito[11].toString()));
				        remitoTO.setDescrUnidadNegocioV(objRemito[12].toString());
				        remitoTO.setCodGrupoUNV(new Integer(objRemito[13].toString()));
				        remitoTO.setDescrGrupoUNV(objRemito[14].toString());
						
				        if(objRemito[15]!=null)
				        	remitoTO.setCodUnidadNegocioC(new Integer(objRemito[15].toString()));
				        
				        if(objRemito[16]!=null)
				        		remitoTO.setDescrUnidadNegocioC(objRemito[16].toString());
				        
				        if(objRemito[17]!=null)
				        	remitoTO.setCodGrupoUNC(new Integer(objRemito[17].toString()));
				        
				        if(objRemito[18]!=null)
				        	remitoTO.setDescrGrupoUNC(objRemito[18].toString());
				        remitoTO.setCcss(objRemito[19].toString());	
				        
				        
				        
				        remitoTO.setCodClienteAlfa(objRemito[20].toString());	
				        remitoTO.setCliDescripcion(objRemito[21].toString());	
				        
				        remitoTO.setPrecioConImpuestos(new BigDecimal(objRemito[22].toString()));	
						
				        remitoTO.setPiva(new BigDecimal(objRemito[23].toString()));
				        remitoTO.setIibb(new BigDecimal(objRemito[24].toString()));
				      
				        
				        lstRemitos.add(remitoTO);
					}
					
					
				}else if(lstRemitos.size()==0){
					throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
				}
			
		        
				return lstRemitos;
			}
			catch(NoExistenItemsException ex){
				ex.printStackTrace();
				throw ex;
			}catch(Exception ex){
				ex.printStackTrace();
				throw new DataAccessErrorException();
			}
		}
	
	
		public List getRemitosPorClienteYPatente(Integer codCliente, Integer patente,Integer nroProducto,Integer mes, Integer anio, String familiaGrupoArticulo) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			String nameQuery = ""; 
			params.put(Const.PARAM_COD_CLIENTE_INT, codCliente);
			params.put(Const.PARAM_PATENTE, patente);
			params.put(Const.PARAM_COD_PRODUCTO, nroProducto);
			params.put("mes", mes);
			params.put("anio", anio);
						
			
			if(familiaGrupoArticulo.equals("A"))
				nameQuery=MpedidosDAO.FIND_REMITOS_BY_CLIENTE_PATENTE;
			else if(familiaGrupoArticulo.equals("F"))
				nameQuery=MpedidosDAO.FIND_REMITOS_BY_CLIENTE_PATENTE_FAMILIA;			
			else if(familiaGrupoArticulo.equals("G"))
				nameQuery=MpedidosDAO.FIND_REMITOS_BY_CLIENTE_PATENTE_GRUPO;
			
			
			Query query = this.getNamedQuery(nameQuery, params, session);
			lstRemitos= query.list();
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();
				lstRemitos= new ArrayList();
				RemitoTO remitoTO= new RemitoTO();
				Object[] objRemito= null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next(); 
			        remitoTO=new RemitoTO();
			        remitoTO.setFecha(sdf.format(objRemito[0]));
			        remitoTO.setHora(sdfHora.format(objRemito[0]));
			        
			        remitoTO.setNroSucursal(new Integer(objRemito[1].toString()));
			        remitoTO.setNroRemito(new Integer(objRemito[2].toString()));
			        remitoTO.setCodProducto(new Integer(objRemito[3].toString()));
			        remitoTO.setDescProducto(objRemito[4].toString());
			        remitoTO.setPrecioKilo(new BigDecimal(objRemito[5].toString()));
			        remitoTO.setMontoTotal(new BigDecimal(objRemito[6].toString()));
			        remitoTO.setLitros(new BigDecimal(objRemito[7].toString()));
			        remitoTO.setPatente(objRemito[8].toString());		
			        
			        
			        if(objRemito[9]!=null)
			        	remitoTO.setCodChofer(new Integer(objRemito[9].toString()));
			        
			        if(objRemito[10]!=null)
			        	remitoTO.setApellidoChofer(objRemito[10].toString());
			        
			        if(objRemito[11]!=null)
			        	remitoTO.setNombreChofer(objRemito[11].toString());
			        
			        remitoTO.setCodClienteAlta(objRemito[12].toString());					
			        lstRemitos.add(remitoTO);
				}
				
				
			}else if(lstRemitos.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}	
			
			/*lstRemitos= new ArrayList();
			remitoTO remitoTO= new remitoTO();
			
			for(int i=0; i<5;i++){			       
		        remitoTO=new remitoTO();
		        remitoTO.setCodChofer(i);			       
		        remitoTO.setApellidoChofer("Nieto "+i);
		        remitoTO.setNombreChofer("Silvana "+i);
		        remitoTO.setCodUnidadNegocio(i+1);
		        remitoTO.setDescrUnidadNegocio("Unidad neg "+i*2);
		        remitoTO.setCodGrupoUN(new Integer(2));
		        remitoTO.setDescrGrupoUN("Grupo Nro "+2);
		        remitoTO.setCodClienteAlfa("NA23");	
		        remitoTO.setDniChofer(new Integer(25632589));	
		        lstRemitos.add(remitoTO);
			}*/
	        
			return lstRemitos;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}				
	
	public BigDecimal getConsumido(String patente, Integer codCliente,Integer codProducto) throws DataAccessErrorException,NoExistenItemsException{
		try{
			
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
					
			params.put(Const.PARAM_COD_CLIENTE_INT, codCliente);
			params.put(Const.PARAM_PATENTE, patente);	
			params.put(Const.PARAM_COD_PRODUCTO, codProducto);			
			
			Query query = this.getNamedQuery(MpedidosDAO.OBTENER_SUMA_CONSUMO_POR_CLIENTE_PATENTE, params, session);
			lstRemitos= query.list();
			BigDecimal totalConsumido= new BigDecimal(0.00);
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();				
				Object[] objRemito= null;				
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next();			        
			        totalConsumido= new BigDecimal(objRemito[3].toString());  		        
				}				
			}
	        
			return totalConsumido;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	public List getConsumosPorVehiculo(String codCliente,Date fechaDesde, Date fechaHasta, String patente,Integer unidadNegocio, String unidadN,Integer grupo, String grupoUN,Boolean vienePorConsumoGrupo ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
				
			if(!vienePorConsumoGrupo){
				if(patente==null)patente="";				
			}
			
			if(unidadN==null)unidadN="";
			if(grupoUN==null){grupoUN="";}
			
			if(unidadNegocio==null){unidadNegocio=new Integer(-1);}
			if(grupo==null){grupo=new Integer(-1);}
			if(codCliente==null){codCliente="%";}
						
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);	
			params.put(Const.PARAM_DESCRIPCION_GRUPO_UN, grupoUN.trim()+"%");
			params.put(Const.PARAM_DESCRIPCION_UNIDAD_NEGOCIO, unidadN.trim()+"%");
			params.put(Const.PARAM_COD_UNIDAD_NEGOCIO, unidadNegocio);	
			params.put(Const.PARAM_COD_GRUPO_UN, grupo);	
			
			if(!vienePorConsumoGrupo){
				params.put(Const.PARAM_PATENTE, patente.trim()+"%");				
			}	
			
			Query query=null;
			if(!vienePorConsumoGrupo){
				query = this.getNamedQuery(MpedidosDAO.FIND_CONSUMOS_VEHICULOS, params, session);
			}else if(vienePorConsumoGrupo){
				query = this.getNamedQuery(MpedidosDAO.FIND_CONSUMOS_GRUPO_VEHICULOS, params, session);
			}	
			lstRemitos= query.list();
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();
				lstRemitos= new ArrayList();
				ConsumoTO consumoTO= new ConsumoTO();
				
				Object[] objRemito= null;
				
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next(); 
			        consumoTO=new ConsumoTO();
			        
			        if(!vienePorConsumoGrupo){
				        consumoTO.setDominio(objRemito[0].toString());
				        consumoTO.setCodUnidadNegocio(new Integer(objRemito[1].toString()));
				        consumoTO.setDescrUnidadNegocio(objRemito[2].toString());
				        consumoTO.setCodGrupoUN(new Integer(objRemito[3].toString()));
				        consumoTO.setDescrGrupoUN(objRemito[4].toString());
				        consumoTO.setConsumoLitros(new BigDecimal(objRemito[5].toString()));
				        consumoTO.setMontoTotal(new BigDecimal(objRemito[6].toString())); 
				        consumoTO.setCodProducto(new Integer(objRemito[7].toString()));
				        consumoTO.setDescProducto(objRemito[8].toString());
				        consumoTO.setCodClienteInt(new Integer(objRemito[9].toString()));
				        consumoTO.setCliDescripcion(objRemito[10].toString());
			        }else if(vienePorConsumoGrupo){
			        	
			        	consumoTO.setCodGrupoUN(new Integer(objRemito[0].toString()));
				        consumoTO.setDescrGrupoUN(objRemito[1].toString());
				        consumoTO.setCodUnidadNegocio(new Integer(objRemito[2].toString()));
				        consumoTO.setDescrUnidadNegocio(objRemito[3].toString());
				        consumoTO.setConsumoLitros(new BigDecimal(objRemito[4].toString()));
				        consumoTO.setMontoTotal(new BigDecimal(objRemito[5].toString())); 
				        consumoTO.setCodProducto(new Integer(objRemito[6].toString()));
				        consumoTO.setDescProducto(objRemito[7].toString());
				        consumoTO.setCodClienteInt(new Integer(objRemito[8].toString()));
				        consumoTO.setCliDescripcion(objRemito[9].toString());
			    		
			        }
			     			        
			        lstRemitos.add(consumoTO);
				}
				
				
			}else if(lstRemitos.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}	
			
			        
			return lstRemitos;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public List getConsumosPorChofer(String codCliente,Date fechaDesde, Date fechaHasta, String nombre, Integer unidadNegocio, String unidadN,Integer grupo, String grupoUN,Boolean vienePorConsumoGrupo ) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
				
			if(!vienePorConsumoGrupo){
				if(nombre==null)nombre="";				
			}
			
			if(unidadN==null)unidadN="";
			if(grupoUN==null){grupoUN="";}
			if(codCliente==null){codCliente="%";}
			if(unidadNegocio==null){unidadNegocio=new Integer(-1);}
			if(grupo==null){grupo=new Integer(-1);}
			
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);	
			params.put(Const.PARAM_DESCRIPCION_GRUPO_UN, grupoUN.trim()+"%");
			params.put(Const.PARAM_DESCRIPCION_UNIDAD_NEGOCIO, unidadN.trim()+"%");
			params.put(Const.PARAM_COD_UNIDAD_NEGOCIO, unidadNegocio);	
			params.put(Const.PARAM_COD_GRUPO_UN, grupo);	
			
			if(!vienePorConsumoGrupo){
				params.put(Const.PARAM_NOMBRE, nombre.trim()+"%");					
			}	
			
			Query query=null;
			if(!vienePorConsumoGrupo){
				query = this.getNamedQuery(MpedidosDAO.FIND_CONSUMOS_CHOFERES, params, session);
			}else if(vienePorConsumoGrupo){
				query = this.getNamedQuery(MpedidosDAO.FIND_CONSUMOS_GRUPO_CHOFERES, params, session);
			}	
			lstRemitos= query.list();
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();
				lstRemitos= new ArrayList();
				ConsumoTO consumoTO= new ConsumoTO();
				
				Object[] objRemito= null;
				
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next(); 
			        consumoTO=new ConsumoTO();
			        
			        if(!vienePorConsumoGrupo){			        	
			        	
			        	consumoTO.setDniChofer(new Integer(objRemito[0].toString()));
				        consumoTO.setApellidoChofer(objRemito[1].toString());
				        consumoTO.setNombreChofer(objRemito[2].toString());
				        consumoTO.setCodUnidadNegocio(new Integer(objRemito[3].toString()));
				        consumoTO.setDescrUnidadNegocio(objRemito[4].toString());
				        consumoTO.setCodGrupoUN(new Integer(objRemito[5].toString()));
				        consumoTO.setDescrGrupoUN(objRemito[6].toString());
				        consumoTO.setConsumoLitros(new BigDecimal(objRemito[7].toString()));
				        consumoTO.setMontoTotal(new BigDecimal(objRemito[8].toString())); 
				        consumoTO.setCodProducto(new Integer(objRemito[9].toString()));
				        consumoTO.setDescProducto(objRemito[10].toString());
				        consumoTO.setCodClienteInt(new Integer(objRemito[11].toString()));
				        consumoTO.setCodChofer(new Integer(objRemito[12].toString()));
				        consumoTO.setCliDescripcion(objRemito[13].toString());
				        
				        
			        }else if(vienePorConsumoGrupo){
			        	
			        	consumoTO.setCodGrupoUN(new Integer(objRemito[0].toString()));
				        consumoTO.setDescrGrupoUN(objRemito[1].toString());
				        consumoTO.setCodUnidadNegocio(new Integer(objRemito[2].toString()));
				        consumoTO.setDescrUnidadNegocio(objRemito[3].toString());
				        consumoTO.setConsumoLitros(new BigDecimal(objRemito[4].toString()));
				        consumoTO.setMontoTotal(new BigDecimal(objRemito[5].toString())); 
				        consumoTO.setCodProducto(new Integer(objRemito[6].toString()));
				        consumoTO.setDescProducto(objRemito[7].toString());
				        consumoTO.setCodClienteInt(new Integer(objRemito[8].toString()));
				        consumoTO.setCliDescripcion(objRemito[9].toString());
			    		
			        }
			     			        
			        lstRemitos.add(consumoTO);
				}
				
				
			}else if(lstRemitos.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}	
			
			        
			return lstRemitos;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	public List getAlicuota(Date fechaDesde, Date fechaHasta) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();				
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			Query query=null;
			
			
			//System.out.println("ENTRO  a getVenta");
			
			query = this.getNamedQuery(MpedidosDAO.FIND_ALICUOTA, params, session);			
			
			lstRemitos= query.list();
			
			//System.out.println("ENTRO a getVenta - cant registros "+ lstRemitos.size());
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();
				lstRemitos= new ArrayList();				
				
				Object[] objRemito= null;
				
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next();
			        
			        //System.out.println("ENTRO objRemito[0].toString()" + objRemito[0].toString());					
			        
			        lstRemitos.add(objRemito[0].toString());
				}
				
				//System.out.println("SALE getVenta");
				
			}else if(lstRemitos.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}	
			
			        
			return lstRemitos;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	
	public List getVenta(Date fechaDesde, Date fechaHasta) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();				
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			Query query=null;
			
			
			//System.out.println("ENTRO  a getVenta");
			
			query = this.getNamedQuery(MpedidosDAO.FIND_VENTAS, params, session);
			
			
			lstRemitos= query.list();
			
			//System.out.println("ENTRO a getVenta - cant registros "+ lstRemitos.size());
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();
				lstRemitos= new ArrayList();				
				
				Object[] objRemito= null;
				
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next();
			        
			      // System.out.println("ENTRO objRemito[0].toString()" + objRemito[0].toString());					
			        
			        lstRemitos.add(objRemito[0].toString());
				}
				
				//System.out.println("SALE getVenta");
				
			}else if(lstRemitos.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}	
			
			        
			return lstRemitos;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	public List getRemitosConsumoVehiculoPorClientePatenteProductoFechas(Integer codCliente, String patente,Integer nroProducto,Date fechaDesde,Date fechaHasta,Integer nroUnidNeg,Integer nroGrupoUnidNeg) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
					
			if(patente==null)patente="%";
				
			params.put(Const.PARAM_COD_CLIENTE_INT, codCliente);
			params.put(Const.PARAM_PATENTE, patente);
			params.put(Const.PARAM_COD_PRODUCTO, nroProducto);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			params.put(Const.PARAM_COD_UNIDAD_NEGOCIO, nroUnidNeg);
			params.put(Const.PARAM_COD_GRUPO_UN, nroGrupoUnidNeg);
			
			Query query = this.getNamedQuery(MpedidosDAO.FIND_REMITOS_BY_CLIENTE_PATENTE_PRODUCTO_FECHAS, params, session);
			lstRemitos= query.list();
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();
				lstRemitos= new ArrayList();
				RemitoTO remitoTO= new RemitoTO();
				Object[] objRemito= null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
				 
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next(); 
			        remitoTO=new RemitoTO();
			        
			        remitoTO.setFecha(sdf.format(objRemito[1]));
			        remitoTO.setHora(sdfHora.format(objRemito[1]));
			        remitoTO.setNroSucursal(new Integer(objRemito[2].toString()));			        
			        remitoTO.setNroRemito(new Integer(objRemito[3].toString()));			        
			        remitoTO.setCodProducto(new Integer(objRemito[4].toString()));			        
			        remitoTO.setDescProducto(objRemito[5].toString());
			        remitoTO.setLitros(new BigDecimal(objRemito[6].toString()));
			        remitoTO.setMontoTotal(new BigDecimal(objRemito[7].toString()));
			        remitoTO.setPatente(objRemito[8].toString()); 
			        
			        if(objRemito[9]!=null)
			        	remitoTO.setApellidoChofer(objRemito[9].toString());
			        
			        if(objRemito[10]!=null)
			        	remitoTO.setNombreChofer(objRemito[10].toString());
			        
			        remitoTO.setCodUnidadNegocioV(new Integer(objRemito[11].toString()));
			        remitoTO.setDescrUnidadNegocioV(objRemito[12].toString());
			        remitoTO.setCodGrupoUNV(new Integer(objRemito[13].toString()));
			        remitoTO.setDescrGrupoUNV(objRemito[14].toString());
					
			        if(objRemito[15]!=null)
			        	remitoTO.setCodUnidadNegocioC(new Integer(objRemito[15].toString()));
			        
			        if(objRemito[16]!=null)
			        		remitoTO.setDescrUnidadNegocioC(objRemito[16].toString());
			        
			        if(objRemito[17]!=null)
			        	remitoTO.setCodGrupoUNC(new Integer(objRemito[17].toString()));
			        
			        if(objRemito[18]!=null)
			        	remitoTO.setDescrGrupoUNC(objRemito[18].toString());
			        remitoTO.setCcss(objRemito[19].toString());	
					
			        lstRemitos.add(remitoTO);
				}
				
				
			}else if(lstRemitos.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}
		
	        
			return lstRemitos;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}		
	
	public List getRemitosConsumoChoferesPorClientePatenteProductoFechas(Integer codCliente, Integer dni,Integer nroProducto,Date fechaDesde,Date fechaHasta,Integer nroUnidNeg,Integer nroGrupoUnidNeg,Boolean vienePorGrupoChofer) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstRemitos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
					
			if(dni==null)dni=-1;
				
			params.put(Const.PARAM_COD_CLIENTE_INT, codCliente);
			
			if(!vienePorGrupoChofer)
				params.put(Const.PARAM_DNI, dni);
			
			params.put(Const.PARAM_COD_PRODUCTO, nroProducto);
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			params.put(Const.PARAM_COD_UNIDAD_NEGOCIO, nroUnidNeg);
			params.put(Const.PARAM_COD_GRUPO_UN, nroGrupoUnidNeg);
			
			Query query = null;
			
			if(!vienePorGrupoChofer)
				query=this.getNamedQuery(MpedidosDAO.FIND_REMITOS_BY_CLIENTE_DNI_PRODUCTO_FECHAS, params, session);
			else
				query=this.getNamedQuery(MpedidosDAO.FIND_REMITOS_BY_CLIENTE_PRODUCTO_FECHAS, params, session);
			
			
			lstRemitos= query.list();
			
			if(lstRemitos.size()>0){
				Iterator itRemitos = lstRemitos.iterator();
				lstRemitos= new ArrayList();
				RemitoTO remitoTO= new RemitoTO();
				Object[] objRemito= null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
				
				while(itRemitos.hasNext()){
			        objRemito = (Object[]) itRemitos.next(); 
			        remitoTO=new RemitoTO();
			        
			        remitoTO.setFecha(sdf.format(objRemito[1]));	
			        remitoTO.setHora(sdfHora.format(objRemito[1]));
			        remitoTO.setNroSucursal(new Integer(objRemito[2].toString()));			        
			        remitoTO.setNroRemito(new Integer(objRemito[3].toString()));			        
			        remitoTO.setCodProducto(new Integer(objRemito[4].toString()));			        
			        remitoTO.setDescProducto(objRemito[5].toString());
			        remitoTO.setLitros(new BigDecimal(objRemito[6].toString()));
			        remitoTO.setMontoTotal(new BigDecimal(objRemito[7].toString()));
			        remitoTO.setPatente(objRemito[8].toString());	
			        
			        if(objRemito[9]!=null)
			        	remitoTO.setApellidoChofer(objRemito[9].toString());
			        
			        if(objRemito[10]!=null)
			        	remitoTO.setNombreChofer(objRemito[10].toString());
			        
			        remitoTO.setCodUnidadNegocioV(new Integer(objRemito[11].toString()));
			        remitoTO.setDescrUnidadNegocioV(objRemito[12].toString());
			        remitoTO.setCodGrupoUNV(new Integer(objRemito[13].toString()));
			        remitoTO.setDescrGrupoUNV(objRemito[14].toString());
					
			        if(objRemito[15]!=null)
			        	remitoTO.setCodUnidadNegocioC(new Integer(objRemito[15].toString()));
			        
			        if(objRemito[16]!=null)
			        		remitoTO.setDescrUnidadNegocioC(objRemito[16].toString());
			        
			        if(objRemito[17]!=null)
			        	remitoTO.setCodGrupoUNC(new Integer(objRemito[17].toString()));
			        
			        if(objRemito[18]!=null)
			        	remitoTO.setDescrGrupoUNC(objRemito[18].toString());
			        
			        remitoTO.setCcss(objRemito[19].toString());
					
			        lstRemitos.add(remitoTO);
				}
				
				
			}else if(lstRemitos.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}
		
	        
			return lstRemitos;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	

	
	public List getRemitosAnuladosRefacturadosYNoRefacturados(Date fechaDesde, Date fechaHasta, String clienteDesde,String clienteHasta, Integer nroRecDesde,Integer nroRecHasta, int condicion, int facturado , Integer nroSucursal,Date fechaDesdeDos, Date fechaHastaDos,Integer ccss,Integer optRefacturacion) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstRemitos= new ArrayList();
			List lstRemitosCompleta= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
							
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			
			if(nroRecDesde!=null && nroRecHasta==null) nroRecHasta=nroRecDesde;
			if(nroRecHasta!=null && nroRecDesde==null) nroRecDesde=nroRecHasta;
			
			params.put(Const.PARAM_NRO_REMITO_DESDE, nroRecDesde);
			params.put(Const.PARAM_NRO_REMITO_HASTA, nroRecHasta);
			
			if(clienteDesde.equals(""))clienteDesde=null;
			if(clienteHasta.equals(""))clienteHasta=null;
			params.put(Const.PARAM_CLIENTE_DESDE, clienteDesde);
			params.put(Const.PARAM_CLIENTE_HASTA, clienteHasta);
			
			if(nroSucursal==null) nroSucursal=-1;
			params.put(Const.PARAM_NRO_SUCURSAL, nroSucursal);
			
			params.put(Const.PARAM_COD_CCSS, ccss);
			
			
			
			Query query = null;
			
			if((fechaDesdeDos==null && fechaHastaDos==null) && (optRefacturacion.equals(new Integer(1)) || optRefacturacion.equals(new Integer(3)))){
				if((facturado==1 || facturado==-1)){				
					query = this.getNamedQuery(FIND_REMITOS_NO_FACTURADOS_NO_REFACTURADOS, params, session);				
					lstRemitos=new ArrayList();
					lstRemitos= query.list();			
					lstRemitosCompleta.addAll(cargarRemitos(lstRemitos,1,condicion,facturado));
				}
			}
			
			System.out.println("paso no facturado");
			if(facturado==0 || facturado==-1){	
				
				params.put(Const.PARAM_FECHA_DESDE_DOS, fechaDesdeDos);
				params.put(Const.PARAM_FECHA_HASTA_DOS, fechaHastaDos);
				
				if(condicion!=2 && (optRefacturacion.equals(new Integer(2)) || optRefacturacion.equals(new Integer(3)))){
					query = this.getNamedQuery(FIND_REMITOS_FACTURADOS_EN_MREMITOFACTURA_REFACTURADOS, params, session);
					lstRemitos=new ArrayList();
					lstRemitos= query.list();			
					lstRemitosCompleta.addAll(cargarRemitos(lstRemitos,0,condicion,facturado));
				}
			}
			
			
			
			if(lstRemitosCompleta.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}
		
	        
			return lstRemitosCompleta;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}

	
	public List getRemitosFactutadosyNoFacturados(Date fechaDesde, Date fechaHasta, String clienteDesde,String clienteHasta, Integer nroRecDesde,Integer nroRecHasta, int condicion, int facturado , Integer nroSucursal,Date fechaDesdeDos, Date fechaHastaDos,Integer ccss,Integer optRefacturacion,boolean verPrecioCImp) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstRemitos= new ArrayList();
			List lstRemitosCompleta= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
							
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			
			if(nroRecDesde!=null && nroRecHasta==null) nroRecHasta=nroRecDesde;
			if(nroRecHasta!=null && nroRecDesde==null) nroRecDesde=nroRecHasta;
			
			params.put(Const.PARAM_NRO_REMITO_DESDE, nroRecDesde);
			params.put(Const.PARAM_NRO_REMITO_HASTA, nroRecHasta);
			
			if(clienteDesde.equals(""))clienteDesde=null;
			if(clienteHasta.equals(""))clienteHasta=null;
			params.put(Const.PARAM_CLIENTE_DESDE, clienteDesde);
			params.put(Const.PARAM_CLIENTE_HASTA, clienteHasta);
			
			if(nroSucursal==null) nroSucursal=-1;
			params.put(Const.PARAM_NRO_SUCURSAL, nroSucursal);
			
			params.put(Const.PARAM_COD_CCSS, ccss);
			
			Integer verPrecioCImpInt=  verPrecioCImp ? 1 : 0;
			params.put("verPrecioCImp", verPrecioCImpInt);	
			
			
			Query query = null;
			
			//if((fechaDesdeDos==null && fechaHastaDos==null) && optRefacturacion.equals(new Integer(4))){
			if((fechaDesdeDos==null && fechaHastaDos==null)){
				if((facturado==1 || facturado==-1)){				
					query = this.getNamedQuery(FIND_REMITOS_NO_FACTURADOS, params, session);				
					lstRemitos=new ArrayList();
					lstRemitos= query.list();			
					lstRemitosCompleta.addAll(cargarRemitos(lstRemitos,1,condicion,facturado));
				}
			}
			
			System.out.println("paso no facturado");
			if(facturado==0 || facturado==-1){	
				
				params.put(Const.PARAM_FECHA_DESDE_DOS, fechaDesdeDos);
				params.put(Const.PARAM_FECHA_HASTA_DOS, fechaHastaDos);
				
					if(condicion!=1){	
					query = this.getNamedQuery(FIND_REMITOS_FACTURADOS_EN_MFACTURAV, params, session);
					lstRemitos= query.list();			
					lstRemitosCompleta.addAll(cargarRemitos(lstRemitos,0,condicion,facturado));
				}
				
				if(condicion!=2){	
					query = this.getNamedQuery(FIND_REMITOS_FACTURADOS_EN_MREMITOFACTURA, params, session);
					lstRemitos=new ArrayList();
					lstRemitos= query.list();			
					lstRemitosCompleta.addAll(cargarRemitos(lstRemitos,0,condicion,facturado));
				}
			}
			
			
			lstRemitos= null;	
			if(lstRemitosCompleta.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}
			
			return lstRemitosCompleta;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}finally{			
			fechaDesde= null;
			fechaHasta= null;			
			nroRecDesde= null;
			nroRecHasta= null;
			clienteDesde= null;
			clienteHasta= null;
			nroSucursal= null;			
			ccss= null;
		}
	}

	public List getRemitosFactutadosyNoFacturadosV_2(Date fechaDesde, Date fechaHasta, String clienteDesde,String clienteHasta, Integer nroRecDesde,Integer nroRecHasta, int condicion, int facturado , Integer nroSucursal) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstRemitos= new ArrayList();
			List lstRemitosCompleta= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
							
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			
			if(nroRecDesde!=null && nroRecHasta==null) nroRecHasta=nroRecDesde;
			if(nroRecHasta!=null && nroRecDesde==null) nroRecDesde=nroRecHasta;
			
			params.put(Const.PARAM_NRO_REMITO_DESDE, nroRecDesde);
			params.put(Const.PARAM_NRO_REMITO_HASTA, nroRecHasta);
			
			if(clienteDesde.equals(""))clienteDesde=null;
			if(clienteHasta.equals(""))clienteHasta=null;
			params.put(Const.PARAM_CLIENTE_DESDE, clienteDesde);
			params.put(Const.PARAM_CLIENTE_HASTA, clienteHasta);
			
			if(nroSucursal==null) nroSucursal=-1;
			params.put(Const.PARAM_NRO_SUCURSAL, nroSucursal);
			
			params.put(Const.PARAM_ESTADO, facturado);
			
			Query query = null;
			
			//if(facturado==0 || facturado==-1){			
				query = this.getNamedQuery(FIND_REMITOS_FACTURADOS_Y_NO_FACTURADOS_V2, params, session);
				lstRemitos= query.list();			
				lstRemitosCompleta.addAll(cargarRemitosV_2(lstRemitos,condicion,facturado));	
			/*}
			
			if(facturado==1 || facturado==-1){				
				query = this.getNamedQuery(FIND_REMITOS_NO_FACTURADOS, params, session);
				lstRemitos=new ArrayList();
				lstRemitos= query.list();			
				lstRemitosCompleta.addAll(cargarRemitos(lstRemitos,1,condicion,facturado));
			}*/
			
			if(lstRemitosCompleta.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}
		
	        
			return lstRemitosCompleta;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	private List cargarRemitos(List lstRemitos,int estado,int condicion, int facturado ) throws DataAccessErrorException{
		try{
		Messages mensajeria = new Messages();
			List lstRemitosCompleta= new ArrayList();
		if(lstRemitos.size()>0){
			Iterator itRemitos = lstRemitos.iterator();
			lstRemitosCompleta= new ArrayList();
			RemitoTO remitoTO= new RemitoTO();
			Object[] objRemito= null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
			int suc=0,rem=0;
			while(itRemitos.hasNext()){
		        objRemito = (Object[]) itRemitos.next(); 
		        remitoTO=new RemitoTO();
		        		        
		        remitoTO.setFecha(sdf.format(objRemito[1]));	
		        remitoTO.setHora(sdfHora.format(objRemito[1]));
		        remitoTO.setNroSucursal(new Integer(objRemito[2].toString()));			        
		        remitoTO.setNroRemito(new Integer(objRemito[3].toString()));			        
		        remitoTO.setCodProducto(new Integer(objRemito[4].toString()));			        
		        remitoTO.setDescProducto(objRemito[5].toString());
		        remitoTO.setLitros(new BigDecimal(objRemito[6].toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
		       		
		       System.out.println("remito "+remitoTO.getNroSucursal()+" "+remitoTO.getNroRemito()); 
		        remitoTO.setMontoTotal(new BigDecimal(objRemito[7].toString()));
		        
		        remitoTO.setPatente(objRemito[8].toString());
		        
		        remitoTO.setCodClienteAlfa(objRemito[20].toString());
		        
		        if(objRemito[9]!=null){
		        	 remitoTO.setApellidoChofer(objRemito[9].toString());					    
		        }
		        
		        if(objRemito[10]!=null){
		        	 remitoTO.setNombreChofer(objRemito[10].toString());			    
		        }
		       
		        remitoTO.setCodUnidadNegocioV(new Integer(objRemito[11].toString()));
		        remitoTO.setDescrUnidadNegocioV(objRemito[12].toString());
		        remitoTO.setCodGrupoUNV(new Integer(objRemito[13].toString()));
		        remitoTO.setDescrGrupoUNV(objRemito[14].toString());
				
		        if(objRemito[15]!=null){
		        	remitoTO.setCodUnidadNegocioC(new Integer(objRemito[15].toString()));
		        }
		        
		        if(objRemito[16]!=null){
		        	 remitoTO.setDescrUnidadNegocioC(objRemito[16].toString());
		        }
		        
		        if(objRemito[17]!=null){
		        	remitoTO.setCodGrupoUNC(new Integer(objRemito[17].toString()));
		        }
		        
		        if(objRemito[18]!=null){
		        	 remitoTO.setDescrGrupoUNC(objRemito[18].toString());
		        }	        
		       
		        remitoTO.setCcss(objRemito[19].toString());		        
		        
		        remitoTO.setCliDescripcion(objRemito[21].toString());
		        remitoTO.setPrecioKilo(new BigDecimal(objRemito[22].toString()).setScale(4));
		        remitoTO.setItc(new BigDecimal(objRemito[23].toString()));
		        remitoTO.setTasaFondo(new BigDecimal(objRemito[24].toString()));
		        
			    remitoTO.setCO2(new BigDecimal(objRemito[46].toString()));
			      
		       // remitoTO.setIva(new BigDecimal(objRemito[25].toString()));
		        remitoTO.setIva(obtenerIva(remitoTO.getMontoTotal(),remitoTO.getItc(),remitoTO.getTasaFondo(),remitoTO.getLitros(),remitoTO.getCO2()));
		        remitoTO.setKilometraje(new BigDecimal(objRemito[26].toString()));
		        
		        if(objRemito[27]!=null){
		        	remitoTO.setNroLiquidacion(new Integer(objRemito[27].toString()));
		        }
		        remitoTO.setCodBarra(objRemito[28].toString());
		        if(objRemito[29]!=null){
		        	remitoTO.setNroAutorizacion(objRemito[29].toString());
		        }
		        remitoTO.setNroSucursalFactura(new Integer(objRemito[30].toString()));
		        remitoTO.setNroFactura(new Integer(objRemito[31].toString()));
		        
		      
		       
		        if(objRemito[32]!=null){				        
			        if(objRemito[32].toString().trim().equals("FC") || objRemito[32].toString().trim().equals("F")){
			        	remitoTO.setTipoComprobante(Const.FACTURA+" "+objRemito[33].toString());
			        }else if(objRemito[32].toString().equals("NC")){
			        	remitoTO.setTipoComprobante(Const.NOTA_CREDITO);
			        }else if(objRemito[32].toString().equals("ND")){
			        	remitoTO.setTipoComprobante(Const.NOTA_DEBITO);
			        }		
		        }
		       
		        remitoTO.setFacturado(estado);
		        
		        if(estado==0){
		        	//Facturado
		        	remitoTO.setFacturadoString(Const.ESTADO_FACTURADO);		        	
		        }else if(estado==1){
//		        	NO Facturado
		        	remitoTO.setFacturadoString(Const.ESTADO_NO_FACTURADO);
		        }
		        remitoTO.setCondicion(new Integer(objRemito[34].toString())); 
		        if(remitoTO.getCondicion()==Const.CONDICION_COD_CTA_CTE){
		        	remitoTO.setCondicionDesc(mensajeria.getMessage().getString("cta_cte_abreviado_label"));
		        }else if(remitoTO.getCondicion()==Const.CONDICION_COD_CONTADO){
		        	remitoTO.setCondicionDesc(mensajeria.getMessage().getString("contado_abreviado_label"));
		        }
		        
		        // yapa
		        ////System.out.println("suc ->"+remitoTO.getNroSucursal().toString()+ " rem-> "+remitoTO.getNroRemito().toString());
		        if(suc == remitoTO.getNroSucursal().intValue() && rem==remitoTO.getNroRemito().intValue()){
		        	 remitoTO.setMontoTotal(new BigDecimal(objRemito[7].toString()));	
		        	////System.out.println("suc ->"+ remitoTO.getMontoTotal());
		        }
		       
		        suc=remitoTO.getNroSucursal().intValue();
		        rem=remitoTO.getNroRemito().intValue();
		        
		       // //System.out.println("suc ->"+suc.toString()+ " rem-> "+rem.toString() );
		        //yapa
		        
		        if(objRemito[36]!=null){
		           remitoTO.setIibb(new BigDecimal(objRemito[36].toString()));
		        }
		        
		        if(objRemito[44]!=null){
			           remitoTO.setLeyCba(new BigDecimal(objRemito[44].toString()));
			        }
		        
		        if(objRemito[45]!=null){
			           remitoTO.setPiva(new BigDecimal(objRemito[45].toString()));
			        }
		        
		      
		        	    	
		    	remitoTO.setNroAsientoContable(new BigDecimal(objRemito[37].toString()));
		    	remitoTO.setNroEjercicioContable(new BigDecimal(objRemito[38].toString()));
		    	remitoTO.setPrecioListaPorCCSSArticuloFecha(new BigDecimal(objRemito[39].toString()));
		        remitoTO.setComparacionPrecioCImp(remitoTO.getPrecioKilo().equals(remitoTO.getPrecioListaPorCCSSArticuloFecha())?"":"Error Precio");
		        
		        if(objRemito[40]!=null)
		         remitoTO.setFechaFacturacion(sdf.format(objRemito[40]));
		        else
		          remitoTO.setFechaFacturacion("----");	
		        
		        if(objRemito[41]!=null)
			         remitoTO.setReFacturacion(objRemito[41].toString());
			        else
			          remitoTO.setReFacturacion("----");	
		        
		        if(condicion==-1 || remitoTO.getCondicion()==condicion){
		        	 if(facturado==-1 || remitoTO.getFacturado()==facturado){
		        		 lstRemitosCompleta.add(remitoTO);
		        	 }
				}
			}
		
		
		}
			return lstRemitosCompleta;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	private List cargarRemitosV_2(List lstRemitos,int condicion, int facturado ) throws DataAccessErrorException{
		try{
		Messages mensajeria = new Messages();
			List lstRemitosCompleta= new ArrayList();
		if(lstRemitos.size()>0){
			Iterator itRemitos = lstRemitos.iterator();
			lstRemitosCompleta= new ArrayList();
			RemitoTO remitoTO= new RemitoTO();
			Object[] objRemito= null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
			Integer suc=0,rem=0;
			while(itRemitos.hasNext()){
		        objRemito = (Object[]) itRemitos.next(); 
		        remitoTO=new RemitoTO();
		        
		        remitoTO.setFecha(sdf.format(objRemito[1]));	
		        remitoTO.setHora(sdfHora.format(objRemito[1]));
		        remitoTO.setNroSucursal(new Integer(objRemito[2].toString()));			        
		        remitoTO.setNroRemito(new Integer(objRemito[3].toString()));			        
		        remitoTO.setCodProducto(new Integer(objRemito[4].toString()));			        
		        remitoTO.setDescProducto(objRemito[5].toString());
		        remitoTO.setLitros(new BigDecimal(objRemito[6].toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
		        
		        remitoTO.setMontoTotal(new BigDecimal(objRemito[7].toString()));
		        
		        remitoTO.setPatente(objRemito[8].toString());
		        
		        remitoTO.setCodClienteAlfa(objRemito[20].toString());
		        
		        if(objRemito[9]!=null){
		        	 remitoTO.setApellidoChofer(objRemito[9].toString());					    
		        }
		        
		        if(objRemito[10]!=null){
		        	 remitoTO.setNombreChofer(objRemito[10].toString());			    
		        }
		       
		        remitoTO.setCodUnidadNegocioV(new Integer(objRemito[11].toString()));
		        remitoTO.setDescrUnidadNegocioV(objRemito[12].toString());
		        remitoTO.setCodGrupoUNV(new Integer(objRemito[13].toString()));
		        remitoTO.setDescrGrupoUNV(objRemito[14].toString());
				
		        if(objRemito[15]!=null){
		        	remitoTO.setCodUnidadNegocioC(new Integer(objRemito[15].toString()));
		        }
		        
		        if(objRemito[16]!=null){
		        	 remitoTO.setDescrUnidadNegocioC(objRemito[16].toString());
		        }
		        
		        if(objRemito[17]!=null){
		        	remitoTO.setCodGrupoUNC(new Integer(objRemito[17].toString()));
		        }
		        
		        if(objRemito[18]!=null){
		        	 remitoTO.setDescrGrupoUNC(objRemito[18].toString());
		        }	        
		       
		        remitoTO.setCcss(objRemito[19].toString());		        
		        
		        remitoTO.setCliDescripcion(objRemito[21].toString());
		        remitoTO.setPrecioKilo(new BigDecimal(objRemito[22].toString()).setScale(4));
		        remitoTO.setItc(new BigDecimal(objRemito[23].toString()));
		        remitoTO.setTasaFondo(new BigDecimal(objRemito[24].toString()));
		       // remitoTO.setIva(new BigDecimal(objRemito[25].toString()));
		        remitoTO.setIva(obtenerIva(remitoTO.getMontoTotal(),remitoTO.getItc(),remitoTO.getTasaFondo(),remitoTO.getLitros(),remitoTO.getCO2()));
		        remitoTO.setKilometraje(new BigDecimal(objRemito[26].toString()));
		        
		        if(objRemito[27]!=null){
		        	remitoTO.setNroLiquidacion(new Integer(objRemito[27].toString()));
		        }
		        remitoTO.setCodBarra(objRemito[28].toString());
		        if(objRemito[29]!=null){
		        	remitoTO.setNroAutorizacion(objRemito[29].toString());
		        }
		        remitoTO.setNroSucursalFactura(new Integer(objRemito[30].toString()));
		        remitoTO.setNroFactura(new Integer(objRemito[31].toString()));
		        
		      
		       
		        if(objRemito[32]!=null){				        
			        if(objRemito[32].toString().trim().equals("FC") || objRemito[32].toString().trim().equals("F")){
			        	remitoTO.setTipoComprobante(Const.FACTURA+" "+objRemito[33].toString());
			        }else if(objRemito[32].toString().equals("NC")){
			        	remitoTO.setTipoComprobante(Const.NOTA_CREDITO);
			        }else if(objRemito[32].toString().equals("ND")){
			        	remitoTO.setTipoComprobante(Const.NOTA_DEBITO);
			        }		
		        }
		       
		        remitoTO.setFacturado(Integer.parseInt(objRemito[39].toString()));
		        
		        if(remitoTO.getFacturado()==0){
		        	//Facturado
		        	remitoTO.setFacturadoString(Const.ESTADO_FACTURADO);		        	
		        }else if(remitoTO.getFacturado()==1){
//		        	NO Facturado
		        	remitoTO.setFacturadoString(Const.ESTADO_NO_FACTURADO);
		        }
		        remitoTO.setCondicion(new Integer(objRemito[34].toString())); 
		        if(remitoTO.getCondicion()==Const.CONDICION_COD_CTA_CTE){
		        	remitoTO.setCondicionDesc(mensajeria.getMessage().getString("cta_cte_abreviado_label"));
		        }else if(remitoTO.getCondicion()==Const.CONDICION_COD_CONTADO){
		        	remitoTO.setCondicionDesc(mensajeria.getMessage().getString("contado_abreviado_label"));
		        }
		        
		        // yapa
		        ////System.out.println("suc ->"+remitoTO.getNroSucursal().toString()+ " rem-> "+remitoTO.getNroRemito().toString());
		        if(suc.equals(remitoTO.getNroSucursal()) && rem.equals(remitoTO.getNroRemito())){
		        	 remitoTO.setMontoTotal(new BigDecimal(objRemito[7].toString()));	
		        	////System.out.println("suc ->"+ remitoTO.getMontoTotal());
		        }
		       
		        suc=remitoTO.getNroSucursal();
		        rem=remitoTO.getNroRemito();
		        
		       // //System.out.println("suc ->"+suc.toString()+ " rem-> "+rem.toString() );
		        //yapa
		        
		        if(objRemito[36]!=null){
		           remitoTO.setIibb(new BigDecimal(objRemito[36].toString()));
		        }
		        	    	
		    	remitoTO.setNroAsientoContable(new BigDecimal(objRemito[37].toString()));
		    	remitoTO.setNroEjercicioContable(new BigDecimal(objRemito[38].toString()));
		        
		        if(condicion==-1 || remitoTO.getCondicion()==condicion){
		        	 if(facturado==-1 || remitoTO.getFacturado()==facturado){
		        		 lstRemitosCompleta.add(remitoTO);
		        	 }
				}
			}
		
		
		}
			return lstRemitosCompleta;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	private BigDecimal obtenerIva(BigDecimal montoTotal,BigDecimal itc,BigDecimal tasaFondo,BigDecimal litros, BigDecimal co2){
		BigDecimal iva= new BigDecimal(0);
		try{
			BigDecimal neto = montoTotal.subtract(itc.multiply(litros)).subtract(tasaFondo.multiply(litros)).subtract(co2);
			//BigDecimal ivaAux = neto.divide(new BigDecimal(0.21));	
			
			Double ivaAux = (Math.round((neto.doubleValue() / new BigDecimal("1.21").doubleValue()) * 100.0))/100.0; 						
			//sumaAsePorPersconaAux = ( Math.round( (lstActividadPorPersonas.get(i).getCoberturas().get(k).getSumaAsegurada().doubleValue() / lstActividadPorPersonas.get(i).getCoberturas().get(k).getCantidadPersonas().doubleValue() * 100.0) ) ) / 100.0;
			        	
			iva = neto.subtract(new BigDecimal(ivaAux));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return iva;
		
	}
	
	public List getMeses() throws NoExistenItemsException, DataAccessErrorException{
		try{
			Messages mensajeria = new Messages();
			List lstRes= new ArrayList();
			Query tiposDocumentoQry = this.getNamedQuery(FIND_MESES_M_PEDIDOS,  session);
			lstRes= tiposDocumentoQry.list();
			
			if(lstRes.size()>0){
				Iterator itCupos = lstRes.iterator();
				lstRes= new ArrayList();
				MesTO mesTO= new MesTO();
				Object[] objResultado= null;
				
				while(itCupos.hasNext()){
					objResultado=(Object[]) itCupos.next();	
					mesTO= new MesTO();	
					mesTO.setNroMes(Integer.parseInt(objResultado[0].toString())); 
					DataUtil dataUtil = new DataUtil();	
					String mesNum = objResultado[0].toString();
					if( Integer.parseInt(mesNum)<10){
						mesNum = "0"+mesNum;
					}
					mesTO.setNombreMes(dataUtil.pasarAMes(mesNum));					
			        lstRes.add(mesTO);
				}		
				
			}else if(lstRes.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}	
			
			
	        
			return lstRes;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}

	public List getAnios() throws NoExistenItemsException, DataAccessErrorException{
		try{
			Messages mensajeria = new Messages();
			List lstRes= new ArrayList();
			Query qry = this.getNamedQuery(FIND_ANIOS_M_PEDIDOS,  session);
			lstRes= qry.list();
			
			if(lstRes.size()>0){
				Iterator itCupos = lstRes.iterator();
				lstRes= new ArrayList();
				AnioTO anioTO= new AnioTO();
				Object[] objResultado= null;
				
				while(itCupos.hasNext()){
					objResultado=(Object[]) itCupos.next();	
					anioTO= new AnioTO();	
					anioTO.setCodAnio(Integer.parseInt(objResultado[0].toString()));					
					anioTO.setAnio(objResultado[1].toString());					
			        lstRes.add(anioTO);
				}		
				
			}else if(lstRes.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}	
			
			
	        
			return lstRes;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
public static String FIND_IMPUESTO_LEY_CBA= "findImpuestoLeyVialCba";
public static String FIND_IMPUESTO_LEY_CBA_EN_MFACTURAV= "findImpuestoLeyVialCbaEnMFactv";
public static String FIND_IMPUESTO_LEY_CBA_EN_MREMITOFACTURA= "findImpuestoLeyVialCbaEnMRemFact";
	
	public List listarImpuestoLeyVialCba(Integer producto, Date fechaDesde,Date fechaHasta, Integer codCliente, Integer nroSucursal,Integer codCcss, int estadoRemito) throws NoExistenItemsException, DataAccessErrorException{
		try{
			
			Messages mensajeria = new Messages();
			List lstImpuestos= new ArrayList();
			List lstImpuestosTodos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put(Const.PARAM_COD_PRODUCTO, producto);			
			params.put(Const.PARAM_FECHA_DESDE, fechaDesde);
			params.put(Const.PARAM_FECHA_HASTA, fechaHasta);
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			if(nroSucursal==null) nroSucursal=-1;
			params.put(Const.PARAM_NRO_SUCURSAL, nroSucursal);
			params.put(Const.PARAM_COD_CCSS, codCcss);
			
				
			Query query = null;
			
			if((estadoRemito==1 || estadoRemito==-1)){				
				query = this.getNamedQuery(FIND_IMPUESTO_LEY_CBA, params, session);				
				lstImpuestos=new ArrayList();
				lstImpuestos = query.list();	
				lstImpuestosTodos.addAll(lstImpuestos);
				
			}
		
			if(estadoRemito==0 || estadoRemito==-1){				
				
					query = this.getNamedQuery(FIND_IMPUESTO_LEY_CBA_EN_MFACTURAV, params, session);
					lstImpuestos=new ArrayList();
					lstImpuestos= query.list();			
					lstImpuestosTodos.addAll(lstImpuestos);
				
				
					query = this.getNamedQuery(FIND_IMPUESTO_LEY_CBA_EN_MREMITOFACTURA, params, session);
					lstImpuestos=new ArrayList();
					lstImpuestos= query.list();			
					lstImpuestosTodos.addAll(lstImpuestos);
				
			}
				
			
			lstImpuestos = new ArrayList();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
			
			
			if(lstImpuestosTodos.size()>0){
				Iterator it = lstImpuestosTodos.iterator();
				lstImpuestos= new ArrayList();
				Object[] objImpuesto= null;
				RemitoTO remitoTO = null;
				int estado=0;
				while(it.hasNext()){
			        objImpuesto = (Object[]) it.next();	
			        remitoTO= new RemitoTO();
			      
			        remitoTO.setFecha(sdf.format(objImpuesto[1]));	
			        remitoTO.setHora(sdfHora.format(objImpuesto[1]));
			        
			        remitoTO.setNroSucursal(new Integer(objImpuesto[2].toString()));
			        remitoTO.setNroRemito(new Integer(objImpuesto[3].toString()));			        
			        remitoTO.setDescProducto(objImpuesto[5].toString());
			        
			        remitoTO.setLitros(new BigDecimal(objImpuesto[6].toString())) ;
			        remitoTO.setMontoTotal(new BigDecimal(objImpuesto[7].toString())) ;
			        
			        remitoTO.setPatente(objImpuesto[8].toString());

			        if(objImpuesto[9]!=null){
			      		        	 remitoTO.setApellidoChofer(objImpuesto[9].toString());					    
			      	}
			      		        
      		        if(objImpuesto[10]!=null){
      		        	 remitoTO.setNombreChofer(objImpuesto[10].toString());			    
      		        }
			        remitoTO.setCliDescripcion(objImpuesto[11].toString());
			        
			        remitoTO.setLeyCba(new BigDecimal(objImpuesto[12].toString())) ;
			        
			        if(remitoTO.getLeyCba()!=null && remitoTO.getLitros()!=null)
			        	remitoTO.setTotalLeyCba(remitoTO.getLeyCba().multiply(remitoTO.getLitros()));
			        else
			        	remitoTO.setTotalLeyCba(new BigDecimal(0));
			        
			        remitoTO.setCcss(objImpuesto[13].toString());			        
			        
			        estado = new Integer(objImpuesto[15].toString());
			        
			        if(estado==0){
			        	//Facturado
			        	remitoTO.setFacturadoString(Const.ESTADO_FACTURADO);		        	
			        }else if(estado==1){
//			        	NO Facturado
			        	remitoTO.setFacturadoString(Const.ESTADO_NO_FACTURADO);
			        }
			        
			        lstImpuestos.add(remitoTO);	
				}
				
			}	
			
			if(lstImpuestos.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}		
	        
			return lstImpuestos;
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
}