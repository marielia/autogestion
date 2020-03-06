package com.refinor.extranet.data.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.Mchofer;
import com.refinor.extranet.data.Mvehiculo;
import com.refinor.extranet.data.base.BaseMvehiculoDAO;
import com.refinor.extranet.to.MChoferTO;
import com.refinor.extranet.to.MVehiculoTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.VariosChoferesConIgualDNIException;
import com.refinor.extranet.util.exception.VariosVehiculosPorPatenteYClienteException;


public class MvehiculoDAO extends BaseMvehiculoDAO implements com.refinor.extranet.data.dao.iface.MvehiculoDAO {

	public MvehiculoDAO () {}
	
	public MvehiculoDAO (Session session) {
		super(session);
	}
	public static String FIND_VEHICULO_BY_CLIENTE_UNIDAD_NEGOCIO_PATENTE_ESTADO= "findVehiculoByClienteUnidadNegocioPatenteEstado";
	public static String FIND_VEHICULOS_POR_CLIENTE= "findVehiculoByCliente";
	public static String FIND_CODIGO_VEHICULO= "findCodVehiculo";
	private static String FIND_VEHICULO_POR_DOMINIO_CLIENTE= "findVehiculoPorDominioCliente";
	public static String FIND_VEHICULO_POR_CODIGO= "findVehiculoPorCodigo";
	public static String FIND_VEHICULO_POR_CLIENTE= "findVehiculoPorCliente";
	
	public Mvehiculo getVehiculoPorDominioYClienteEstado(String dominio, Integer codCliente,int estado) throws NoExistenItemsException, VariosVehiculosPorPatenteYClienteException, IOException, DataAccessErrorException{
		try{
			Messages mensajeria = new Messages();
			List lstPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();			
			params.put(Const.PARAM_PATENTE, dominio);
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			Boolean estadoVActi=new Boolean(false);
			Boolean estadoVAnul=new Boolean(false);
			if(estado==-1){
				//buscar todos
				estadoVActi=new Boolean(true);
				estadoVAnul=new Boolean(false);
			}else if(estado==1){
				estadoVActi=new Boolean(true);
				estadoVAnul=new Boolean(true);
			}else if(estado==0){
				estadoVActi=new Boolean(false);
				estadoVAnul=new Boolean(false);
			}
			
			params.put(Const.ACTIVO, estadoVActi);
			params.put(Const.ANULADO, estadoVAnul);
			
			Query query = this.getNamedQuery(FIND_VEHICULO_POR_DOMINIO_CLIENTE, params, session);
			lstPorFiltro= query.list();
			
			if(lstPorFiltro.size()==1){
				return (Mvehiculo)lstPorFiltro.get(0);				
			}else if(lstPorFiltro.size()==0){
				 throw new NoExistenItemsException(mensajeria.getMessage().getString("vehiculo_no_existe_msg"));
			}else {
				 throw new VariosVehiculosPorPatenteYClienteException(mensajeria.getMessage().getString("varios_vehiculos_msg"));
			}			
			
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(VariosVehiculosPorPatenteYClienteException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();			
			throw new DataAccessErrorException();
		}
		
	}
	
	public int getCodigo() throws  DataAccessErrorException {
		try {
			int codigo=1;
			Query usuariosQry = this.getNamedQuery(FIND_CODIGO_VEHICULO, session);			
			List lst = usuariosQry.list();			
						
			if(lst.size()==1)
				codigo = (Integer)lst.get(0);
	        
			return codigo+1;
			
		}  catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException("Hubo problemas al intentar registrar el Chofer. Espere unos segundos y vuelva a intentarlo. Si el problema sigue Consulte con el Administrador.");
		}
	}
	
	public List getVehiculoPorClienteUnidadNegocioPatenteEstado(String codCliente,Integer unidadNegocio, String descripcionUnidadNegocio, Integer grupo,String patente,Boolean vehiculoActivo,Boolean vehiculoBaja,Boolean vehiculoInicializado,Boolean vehiculoNoInicializado) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstVehiculosPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(descripcionUnidadNegocio==null) descripcionUnidadNegocio="";
			if(patente==null) patente="";
			if(codCliente==null) codCliente="%";
						
			params.put(Const.PARAM_COD_CLIENTE, codCliente.trim());		
			params.put(Const.PARAM_DESCRIPCION_UNIDAD_NEGOCIO, descripcionUnidadNegocio.trim()+"%");
			params.put(Const.PARAM_PATENTE, patente.trim()+"%");
			params.put(Const.PARAM_VEHICULO_ACTIVO, vehiculoActivo);
			params.put(Const.PARAM_VEHICULO_BAJA, vehiculoBaja);
			params.put(Const.PARAM_VEHICULO_INICIALIZADO, vehiculoInicializado);
			params.put(Const.PARAM_VEHICULO_NO_INICIALIZADO, vehiculoNoInicializado);
			
			params.put(Const.PARAM_COD_UNIDAD_NEGOCIO, unidadNegocio);
			params.put(Const.PARAM_COD_GRUPO_UN, grupo);
			
			Query queryListadoQry = this.getNamedQuery(MvehiculoDAO.FIND_VEHICULO_BY_CLIENTE_UNIDAD_NEGOCIO_PATENTE_ESTADO, params, session);
			lstVehiculosPorFiltro= queryListadoQry.list();
			
			if(lstVehiculosPorFiltro.size()>0){
				Iterator itVehiculos = lstVehiculosPorFiltro.iterator();
				lstVehiculosPorFiltro= new ArrayList();
				MVehiculoTO mVehiculoTO= new MVehiculoTO();
				SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
				Object[] objVehiculo= null;
				while(itVehiculos.hasNext()){
					objVehiculo = (Object[]) itVehiculos.next(); 
			        mVehiculoTO=new MVehiculoTO();
			        mVehiculoTO.setCodigo(new Integer(objVehiculo[0].toString()));		
			        mVehiculoTO.setDominio(objVehiculo[1].toString());		
			        mVehiculoTO.setCodBarra(objVehiculo[2].toString());		
			        
			        mVehiculoTO.setCodUnidadNegocio(new Integer(objVehiculo[3].toString()));
			        mVehiculoTO.setDescrUnidadNegocio(objVehiculo[4].toString());
			        
			        mVehiculoTO.setCodGrupoUN(new Integer(objVehiculo[5].toString()));
			        mVehiculoTO.setDescrGrupoUN(objVehiculo[6].toString());
			        
			        mVehiculoTO.setCodCliente(new Integer(objVehiculo[7].toString()));
			        mVehiculoTO.setCodClienteAlfa(objVehiculo[8].toString());
			        
			        mVehiculoTO.setActivo(new Boolean(objVehiculo[9].toString()));
			        mVehiculoTO.setActivoDesc(mVehiculoTO.getActivo()? mensajeria.getMessage().getString("si_label") : mensajeria.getMessage().getString("no_label"));
			        mVehiculoTO.setInicializado(new Boolean(objVehiculo[10].toString()));
			        mVehiculoTO.setInicializadoDesc(mVehiculoTO.getInicializado()? mensajeria.getMessage().getString("si_label") : mensajeria.getMessage().getString("no_label"));
			        
			        mVehiculoTO.setCliDescripcion(objVehiculo[11].toString());
			       
			        if(objVehiculo[12]!=null){
			        	mVehiculoTO.setFechaAlta(sdf.format(objVehiculo[12]));
				    }
			        if(objVehiculo[13]!=null){
			        	mVehiculoTO.setFechaBaja(sdf.format(objVehiculo[13]));
				    }
				        
			        lstVehiculosPorFiltro.add(mVehiculoTO);
				}				
				
			}else if(lstVehiculosPorFiltro.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}				
		
			return lstVehiculosPorFiltro;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public List getVehiculosPorCliente(Integer nroCliente,Boolean activo, Boolean inicializado,String dominio) throws NoExistenItemsException, VariosChoferesConIgualDNIException, IOException, DataAccessErrorException{
		try{
			if(dominio==null) dominio="%";
			List lstVehiculoPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();			
			params.put(Const.PARAM_COD_CLIENTE_INT, nroCliente);
			params.put(Const.PARAM_CHOFER_INICIALIZADO, inicializado);
			params.put(Const.PARAM_CHOFER_ACTIVO, activo);
			params.put(Const.PARAM_DOMINIO, "%"+dominio+"%");
			
			Query query = this.getNamedQuery(MvehiculoDAO.FIND_VEHICULOS_POR_CLIENTE, params, session);
			lstVehiculoPorFiltro= query.list();			
			return lstVehiculoPorFiltro;					
			
		}catch(Exception ex){
			ex.printStackTrace();			
			throw new DataAccessErrorException();
		}
		
	}
	
	public Mvehiculo getVehiculoPorCodigo(Integer nroVehiculo) throws NoExistenItemsException, IOException, DataAccessErrorException{
		try{
			Messages mensajeria = new Messages();
			List lstVehiculosPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();			
			params.put(Const.PARAM_COD_VEHICULO, nroVehiculo);
			
			Query query = this.getNamedQuery(MvehiculoDAO.FIND_VEHICULO_POR_CODIGO, params, session);
			lstVehiculosPorFiltro= query.list();
			
			if(lstVehiculosPorFiltro.size()==1){
				return (Mvehiculo)lstVehiculosPorFiltro.get(0);				
			}else if(lstVehiculosPorFiltro.size()==0){
				 throw new NoExistenItemsException(mensajeria.getMessage().getString("vehiculo_no_existe_msg"));
			}else {
				return (Mvehiculo)lstVehiculosPorFiltro.get(0);
			}			
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();			
			throw new DataAccessErrorException();
		}
		
	}
	
	
	public List getVehiculoPorCliente( String CodCliente ) throws NoExistenItemsException,    DataAccessErrorException{
		try{
			Messages mensajeria = new Messages();
			List lstVehiculosPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>(); 
			 
			if(CodCliente==null) CodCliente="%";
						
			params.put(Const.PARAM_COD_CLIENTE, CodCliente.trim());		
			 
			
			Query queryListadoQry = this.getNamedQuery(MvehiculoDAO.FIND_VEHICULO_POR_CLIENTE, params, session);
			lstVehiculosPorFiltro= queryListadoQry.list();
			
			if(lstVehiculosPorFiltro.size()>0){
				Iterator itVehiculos = lstVehiculosPorFiltro.iterator();
				lstVehiculosPorFiltro= new ArrayList();
				Mvehiculo  mVehiculo = new Mvehiculo ();
			 
				Object[] objVehiculo= null;
				while(itVehiculos.hasNext()){
					objVehiculo = (Object[]) itVehiculos.next(); 
					mVehiculo=new Mvehiculo ();
					mVehiculo.setCodigo(new Integer(objVehiculo[0].toString()));		
					mVehiculo.setDominio(objVehiculo[1].toString()); 
			        lstVehiculosPorFiltro.add(mVehiculo);
				}				
				
			}else if(lstVehiculosPorFiltro.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}				
		
			return lstVehiculosPorFiltro;
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