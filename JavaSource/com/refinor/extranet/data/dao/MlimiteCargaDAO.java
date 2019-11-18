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

import com.refinor.extranet.data.base.BaseMlimiteCargaDAO;
import com.refinor.extranet.to.CuposTO;
import com.refinor.extranet.to.MChoferTO;
import com.refinor.extranet.to.MProductoTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.DataUtil;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;


public class MlimiteCargaDAO extends BaseMlimiteCargaDAO implements com.refinor.extranet.data.dao.iface.MlimiteCargaDAO {

	public MlimiteCargaDAO () {}
	
	public MlimiteCargaDAO (Session session) {
		super(session);
	}
		
	public static String FIND_CONSUMO_BY_PATENTE_Y_CLIENTE= "findCuposConsumidoByPatenteYcliente";
	public static String FIND_CUPOS_BY_PATENTE_Y_CLIENTE= "findCuposPorVehiculosCliente";
	public static String FIND_PRODUCTOS_POR_VEHICULO_CCSS= "findProductosPorVehiculoCCSS";
	
	public List getCuposConsumoPorClientePatente(String codCliente, String patente, Integer mes, Integer anio) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstCupos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(patente==null) patente="";	
			if(codCliente==null) codCliente="%";	
			
			params.put(Const.PARAM_COD_CLIENTE, codCliente.trim());
			params.put(Const.PARAM_PATENTE, patente.trim()+"%");
			params.put("mes", mes);
			params.put("anio", anio);
			
			Query tiposDocumentoQry = this.getNamedQuery(MlimiteCargaDAO.FIND_CONSUMO_BY_PATENTE_Y_CLIENTE, params, session);
			lstCupos= tiposDocumentoQry.list();
			
			if(lstCupos.size()>0){
				Iterator itCupos = lstCupos.iterator();
				lstCupos= new ArrayList();
				CuposTO cuposTO= new CuposTO();
				Object[] objCupon= null;			
								
				while(itCupos.hasNext()){
					objCupon=(Object[]) itCupos.next();	
					cuposTO= new CuposTO();
					cuposTO.setDescripcion(objCupon[0].toString());
					cuposTO.setPatente(objCupon[1].toString());					
					cuposTO.setCodUnidadNegocio(new Integer(objCupon[2].toString()));
					cuposTO.setDescrUnidadNegocio(objCupon[3].toString());					
					cuposTO.setCupoLitros(new BigDecimal(objCupon[4].toString()));						
										
					cuposTO.setIlimitado(new Boolean(objCupon[5].toString()));
					cuposTO.setCodCliente(new Integer(objCupon[6].toString()));
					cuposTO.setCodProducto(new Integer(objCupon[7].toString()));
					
					if(objCupon[8]!=null){
						cuposTO.setObservacion(objCupon[8].toString());
					}
					cuposTO.setCliDescripcion(objCupon[9].toString());
					
					cuposTO.setCodBarra(objCupon[10].toString());
					cuposTO.setActivo(new Boolean(objCupon[11].toString()));
					cuposTO.setActivoStr(cuposTO.getActivo()? mensajeria.getMessage().getString("si_label") : mensajeria.getMessage().getString("no_label"));
					cuposTO.setMesNro(new Integer(objCupon[12].toString()));
					DataUtil dataUtil = new DataUtil();	
					String mesNum = objCupon[12].toString();
					if( Integer.parseInt(mesNum)<10 && Integer.parseInt(mesNum)>0){
						mesNum = "0"+mesNum;
					}
					
					if(Integer.parseInt(objCupon[12].toString()) ==0){
						mesNum = "--";
					}
					cuposTO.setMes(dataUtil.pasarAMes(mesNum));
					
					String anioNum=objCupon[13].toString();
					if( Integer.parseInt(anioNum)==0){
						anioNum = "--";
					}					
					cuposTO.setAnio(anioNum);
					
					cuposTO.setConsumoLitros(new BigDecimal(objCupon[14].toString()));
					cuposTO.setDisponibleLitros(new BigDecimal(objCupon[15].toString()));
					cuposTO.setCodClienteAlfa(objCupon[16].toString());		
					cuposTO.setCodVehiculo(new Integer(objCupon[17].toString()));
					cuposTO.setFamiliaGrupoArticulo(objCupon[18].toString());
					cuposTO.setFamiliaGrupoArticuloDesc(objCupon[19].toString());					
				
			        lstCupos.add(cuposTO);
				}	
				
				
			}else if(lstCupos.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}	
			
			
	        
			return lstCupos;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}	
	
	public List getCuposVehiculosSinConsumir(String codCliente, String patente) throws DataAccessErrorException, NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstCupos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(patente==null) patente="";	
			if(codCliente==null) codCliente="%";	
			
			params.put(Const.PARAM_COD_CLIENTE, codCliente.trim());
			params.put(Const.PARAM_PATENTE, patente.trim()+"%");			
			
			Query tiposDocumentoQry = this.getNamedQuery(MlimiteCargaDAO.FIND_CUPOS_BY_PATENTE_Y_CLIENTE, params, session);
			lstCupos= tiposDocumentoQry.list();
			
			if(lstCupos.size()>0){
				Iterator itCupos = lstCupos.iterator();
				lstCupos= new ArrayList();
				CuposTO cuposTO= new CuposTO();
				Object[] objCupon= null;
				
				String codClienteAlfa="";
				Integer mesAux= new Integer(0);
				Integer anioAux= new Integer(0);
				
				while(itCupos.hasNext()){
					objCupon=(Object[]) itCupos.next();	
					cuposTO= new CuposTO();
					cuposTO.setDescripcion(objCupon[0].toString());
					cuposTO.setPatente(objCupon[1].toString());					
					cuposTO.setCodUnidadNegocio(new Integer(objCupon[2].toString()));
					cuposTO.setDescrUnidadNegocio(objCupon[3].toString());					
					cuposTO.setCupoLitros(new BigDecimal(objCupon[4].toString()));						
										
					cuposTO.setIlimitado(new Boolean(objCupon[5].toString()));
					cuposTO.setCodCliente(new Integer(objCupon[6].toString()));
					cuposTO.setCodProducto(new Integer(objCupon[7].toString()));
					
					if(objCupon[8]!=null){
						cuposTO.setObservacion(objCupon[8].toString());
					}
					cuposTO.setCliDescripcion(objCupon[9].toString());
					
					cuposTO.setCodBarra(objCupon[10].toString());
					cuposTO.setActivo(new Boolean(objCupon[11].toString()));
					cuposTO.setActivoStr(cuposTO.getActivo()? mensajeria.getMessage().getString("si_label") : mensajeria.getMessage().getString("no_label"));
					cuposTO.setCodClienteAlfa(objCupon[12].toString());
					cuposTO.setCodVehiculo(new Integer(objCupon[13].toString()));
					
					//para mesa de ayuda
					cuposTO.setConsumoLitros(new BigDecimal(0));
					cuposTO.setDisponibleLitros(cuposTO.getCupoLitros());
					 SimpleDateFormat sdf= new SimpleDateFormat("MM");
					 SimpleDateFormat sdf1= new SimpleDateFormat("yyyy");
					 Integer mes = Integer.parseInt(sdf.format(new Date()));
					 Integer anio = Integer.parseInt(sdf1.format(new Date()));
					DataUtil dataUtil = new DataUtil();	
					String mesNum = mes.toString();
					if( Integer.parseInt(mesNum)<10){
						mesNum = "0"+mesNum;
					}
					cuposTO.setMes(dataUtil.pasarAMes(mesNum));
					cuposTO.setAnio(anio.toString());
					cuposTO.setFamiliaGrupoArticulo(objCupon[14].toString());
					cuposTO.setFamiliaGrupoArticuloDesc(objCupon[15].toString());
					
					
			        lstCupos.add(cuposTO);
				}	
				
				
			}else if(lstCupos.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}	
			
			
	        
			return lstCupos;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public List getProductosPorPatenteCCSS(int codVehiculo, int codCcss) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstCupos= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();			
			params.put(Const.PARAM_COD_VEHICULO, codVehiculo);
			params.put(Const.PARAM_COD_CCSS, codCcss);			
			
			Query tiposDocumentoQry = this.getNamedQuery(MlimiteCargaDAO.FIND_PRODUCTOS_POR_VEHICULO_CCSS, params, session);
			lstCupos= tiposDocumentoQry.list();
			
			
				Iterator itCupos = lstCupos.iterator();
				lstCupos= new ArrayList();
				MProductoTO mProductoTO= new MProductoTO();
				Object[] objCupon= null;
				
				while(itCupos.hasNext()){
					objCupon=(Object[]) itCupos.next();	
					mProductoTO= new MProductoTO();
					mProductoTO.setCodArticulo(new Integer(objCupon[0].toString()));
					mProductoTO.setDescripcion(objCupon[1].toString());					
			        lstCupos.add(mProductoTO);
				}		
		
			return lstCupos;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}


}