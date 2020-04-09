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
import com.refinor.extranet.data.Mvendedor;
import com.refinor.extranet.data.base.BaseMchoferDAO;
import com.refinor.extranet.to.MChoferTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.VariosChoferesConIgualDNIException;


public class MchoferDAO extends BaseMchoferDAO implements com.refinor.extranet.data.dao.iface.MchoferDAO {

	public MchoferDAO () {}
	
	public MchoferDAO (Session session) {
		super(session);
	}
	public static String FIND_CHOFER_BY_NOMBRE_GRUPO_UN_UNIDAD_NEGOCIO= "findChoferByNombreGrupoUNUnidadNegocio";
	public static String FIND_CODIGO_CHOFER= "findCodigoChofer";
	public static String FIND_CHOFER_POR_DNI_COD_CHOFER_CLIENTE= "findChoferDNIxCodigoChofxCliente";
	public static String FIND_CHOFER_POR_DNI_CLIENTE= "findChoferDNIxCliente";
	public static String FIND_CHOFER_POR_NRO_DOCUMENTO= "findChoferNroDocumento";
	public static String FIND_CHOFER_POR_CLIENTE= "findChoferPorCliente";
	public static String FIND_CHOFER_POR_CODIGO = "findChoferPorCodigo";
	
	public static String FIND_CHOFER_ACTIVO_POR_CLIENTE = "findChoferActivoPorCliente";
	
	public Mchofer getChoferPorNroDocumento(Integer nroDocumento) throws NoExistenItemsException, VariosChoferesConIgualDNIException, IOException, DataAccessErrorException{
		try{
			Messages mensajeria = new Messages();
			List lstChoferesPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();			
			params.put(Const.PARAM_DNI, nroDocumento);
			
			Query query = this.getNamedQuery(MchoferDAO.FIND_CHOFER_POR_NRO_DOCUMENTO, params, session);
			lstChoferesPorFiltro= query.list();
			
			if(lstChoferesPorFiltro.size()==1){
				return (Mchofer)lstChoferesPorFiltro.get(0);				
			}else if(lstChoferesPorFiltro.size()==0){
				 throw new NoExistenItemsException(mensajeria.getMessage().getString("chofer_no_existe_msg"));
			}else {
				return (Mchofer)lstChoferesPorFiltro.get(0);
			}			
			
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}/*catch(VariosChoferesConIgualDNIException ex){
			ex.printStackTrace();
			throw ex;
		}*/catch(Exception ex){
			ex.printStackTrace();			
			throw new DataAccessErrorException();
		}
		
	}
	
	public Mchofer getChoferPorCodigo(Integer nroChofer) throws NoExistenItemsException, IOException, DataAccessErrorException{
		try{
			Messages mensajeria = new Messages();
			List lstChoferesPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();			
			params.put(Const.PARAM_COD_CHOFER, nroChofer);
			
			Query query = this.getNamedQuery(MchoferDAO.FIND_CHOFER_POR_CODIGO, params, session);
			lstChoferesPorFiltro= query.list();
			
			if(lstChoferesPorFiltro.size()==1){
				return (Mchofer)lstChoferesPorFiltro.get(0);				
			}else if(lstChoferesPorFiltro.size()==0){
				 throw new NoExistenItemsException(mensajeria.getMessage().getString("chofer_no_existe_msg"));
			}else {
				return (Mchofer)lstChoferesPorFiltro.get(0);
			}			
		}catch(NoExistenItemsException ex){
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
			Query usuariosQry = this.getNamedQuery(FIND_CODIGO_CHOFER, session);			
			List lst = usuariosQry.list();			
						
			if(lst.size()==1)
				codigo = (Integer)lst.get(0);
	        
			return codigo+1;
			
		}  catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException("Hubo problemas al intentar registrar el Chofer. Espere unos segundos y vuelva a intentarlo. Si el problema sigue Consulte con el Administrador.");
		}
	}
	
	public List getChoferesPorNombreGrupoUNUnidadNegocio(String codCliente, String nombre,Integer unidadNegocio,String descripcionUnidadNegocio, Integer grupo,String descripcionGrupoUN,Integer dni,Boolean choferActivo,Boolean choferBaja,Boolean choferInicializado,Boolean choferNoInicializado) throws DataAccessErrorException,NoExistenItemsException{
		try{
			Messages mensajeria = new Messages();
			List lstChoferesPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(nombre==null) nombre="";
			if(descripcionUnidadNegocio==null) descripcionUnidadNegocio="";
			if(descripcionGrupoUN==null) descripcionGrupoUN="";
			if(dni==null) dni=new Integer(-1);
			if(codCliente==null) codCliente="%";
			if(unidadNegocio==null) unidadNegocio=new Integer(-1);
			if(grupo==null) grupo=new Integer(-1);
			
			params.put(Const.PARAM_COD_CLIENTE, codCliente.trim());
			params.put(Const.PARAM_NOMBRE, nombre.trim()+"%");
			params.put(Const.PARAM_DESCRIPCION_UNIDAD_NEGOCIO, descripcionUnidadNegocio.trim()+"%");
			params.put(Const.PARAM_DESCRIPCION_GRUPO_UN, descripcionGrupoUN.trim()+"%");
			params.put(Const.PARAM_CHOFER_ACTIVO, choferActivo);
			params.put(Const.PARAM_CHOFER_BAJA, choferBaja);
			params.put(Const.PARAM_DNI, dni);
			params.put(Const.PARAM_CHOFER_INICIALIZADO, choferInicializado);
			params.put(Const.PARAM_CHOFER_NO_INICIALIZADO, choferNoInicializado);
			params.put(Const.PARAM_COD_UNIDAD_NEGOCIO, unidadNegocio);
			params.put(Const.PARAM_COD_GRUPO_UN, grupo);
			
			Query tiposDocumentoQry = this.getNamedQuery(MchoferDAO.FIND_CHOFER_BY_NOMBRE_GRUPO_UN_UNIDAD_NEGOCIO, params, session);
			lstChoferesPorFiltro= tiposDocumentoQry.list();
			
			if(lstChoferesPorFiltro.size()>0){
				Iterator itChoferes = lstChoferesPorFiltro.iterator();
				lstChoferesPorFiltro= new ArrayList();
				MChoferTO mChoferTO= new MChoferTO();
				Object[] objChofer= null;
				SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
				while(itChoferes.hasNext()){
			        objChofer = (Object[]) itChoferes.next(); 
			        mChoferTO=new MChoferTO();
			        mChoferTO.setCodChofer(new Integer(objChofer[0].toString()));			       
			        mChoferTO.setApellidoChofer(objChofer[1].toString());
			        mChoferTO.setNombreChofer(objChofer[2].toString());
			        mChoferTO.setCodUnidadNegocio(new Integer(objChofer[3].toString()));
			        mChoferTO.setDescrUnidadNegocio(objChofer[4].toString());
			        mChoferTO.setCodGrupoUN(new Integer(objChofer[5].toString()));
			        mChoferTO.setDescrGrupoUN(objChofer[6].toString());
			        mChoferTO.setCodClienteAlfa(objChofer[7].toString());	
			        mChoferTO.setDniChofer(new Integer(objChofer[8].toString()));
			        
			        mChoferTO.setActivo(new Boolean(objChofer[9].toString()));
			        mChoferTO.setActivoDesc(mChoferTO.getActivo()? mensajeria.getMessage().getString("si_label") : mensajeria.getMessage().getString("no_label"));
	
			        mChoferTO.setInicializado(new Boolean(objChofer[10].toString()));
			        mChoferTO.setInicializadoDesc(mChoferTO.getInicializado()? mensajeria.getMessage().getString("si_label") : mensajeria.getMessage().getString("no_label"));			        
	
			        mChoferTO.setCliDescripcion(objChofer[11].toString());
			        mChoferTO.setCodCliente(new Integer(objChofer[12].toString()));
			        mChoferTO.setPin(objChofer[13].toString());
			        if(objChofer[14]!=null){
			          mChoferTO.setFechaAlta(sdf.format(objChofer[14]));
			        }
			        if(objChofer[15]!=null){
				          mChoferTO.setFechaBaja(sdf.format(objChofer[15]));
				        }
			       
			        lstChoferesPorFiltro.add(mChoferTO);
				}
			
			
				
				
				
			}else if(lstChoferesPorFiltro.size()==0){
				throw new NoExistenItemsException(mensajeria.getMessage().getString("no_se_econtraron_registros_msg"));
			}	
			
			/*lstChoferesPorFiltro= new ArrayList();
			MChoferTO mChoferTO= new MChoferTO();
			
			for(int i=0; i<5;i++){			       
		        mChoferTO=new MChoferTO();
		        mChoferTO.setCodChofer(i);			       
		        mChoferTO.setApellidoChofer("Nieto "+i);
		        mChoferTO.setNombreChofer("Silvana "+i);
		        mChoferTO.setCodUnidadNegocio(i+1);
		        mChoferTO.setDescrUnidadNegocio("Unidad neg "+i*2);
		        mChoferTO.setCodGrupoUN(new Integer(2));
		        mChoferTO.setDescrGrupoUN("Grupo Nro "+2);
		        mChoferTO.setCodClienteAlfa("NA23");	
		        mChoferTO.setDniChofer(new Integer(25632589));	
		        lstChoferesPorFiltro.add(mChoferTO);
			}*/
	        
			return lstChoferesPorFiltro;
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	public List getChoferesPorCliente(Integer nroCliente,Boolean activo, Boolean inicializado,String nombreChofer) throws NoExistenItemsException, VariosChoferesConIgualDNIException, IOException, DataAccessErrorException{
		try{
			
			if(nombreChofer==null) nombreChofer="%";
			List lstChoferesPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();			
			params.put(Const.PARAM_COD_CLIENTE_INT, nroCliente);
			params.put(Const.PARAM_CHOFER_INICIALIZADO, inicializado);
			params.put(Const.PARAM_CHOFER_ACTIVO, activo);
			params.put(Const.PARAM_NOMBRE_CHOFER, "%"+nombreChofer+"%");
			
			Query query = this.getNamedQuery(MchoferDAO.FIND_CHOFER_POR_CLIENTE, params, session);
			lstChoferesPorFiltro= query.list();			
			return lstChoferesPorFiltro;					
			
		}catch(Exception ex){
			ex.printStackTrace();			
			throw new DataAccessErrorException();
		}
		
	}
	

	public Mchofer getChoferxDNIxCodChoferxCodCliente(Integer nroDocumento, Integer codChofer, Integer codCliente) throws NoExistenItemsException, VariosChoferesConIgualDNIException, IOException, DataAccessErrorException{
		try{
			Messages mensajeria = new Messages();
			List lstChoferesPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();			
			params.put(Const.PARAM_DNI, nroDocumento);
			params.put(Const.PARAM_COD_CHOFER, codChofer);
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			
			Query query = this.getNamedQuery(MchoferDAO.FIND_CHOFER_POR_DNI_COD_CHOFER_CLIENTE, params, session);
			lstChoferesPorFiltro= query.list();
			
			if(lstChoferesPorFiltro.size()==1){
				return (Mchofer)lstChoferesPorFiltro.get(0);				
			}else if(lstChoferesPorFiltro.size()==0){
				 throw new NoExistenItemsException(mensajeria.getMessage().getString("chofer_no_existe_msg"));
			}else {
				return (Mchofer)lstChoferesPorFiltro.get(0);
			}			
			
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();			
			throw new DataAccessErrorException();
		}
		
	}
	
	
	public Mchofer getChoferxDNIxCodCliente(Integer nroDocumento, Integer codCliente) throws NoExistenItemsException, VariosChoferesConIgualDNIException, IOException, DataAccessErrorException{
		try{
			Messages mensajeria = new Messages();
			List lstChoferesPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();			
			params.put(Const.PARAM_DNI, nroDocumento);		
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			
			Query query = this.getNamedQuery(MchoferDAO.FIND_CHOFER_POR_DNI_CLIENTE, params, session);
			lstChoferesPorFiltro= query.list();
			
			if(lstChoferesPorFiltro.size()==1){
				return (Mchofer)lstChoferesPorFiltro.get(0);				
			}else if(lstChoferesPorFiltro.size()==0){
				 throw new NoExistenItemsException(mensajeria.getMessage().getString("chofer_no_existe_msg"));
			}else {
				return (Mchofer)lstChoferesPorFiltro.get(0);
			}			
			
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();			
			throw new DataAccessErrorException();
		}
		
	}
	
	
	
	public static String FIND_CHOFER_POR_NOMBRE_CLIENTE = "findChoferPorNombreCliente";	
	public Mchofer getChoferPorNombreCliente(String descripcion, Integer cliente) throws DataAccessErrorException, NoExistenItemsException {
		try{			
			List lstPorFiltro= new ArrayList();				
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_DESCRIPCION, descripcion);
			params.put(Const.PARAM_COD_CLIENTE, cliente);
			
			Query query = this.getNamedQuery(FIND_CHOFER_POR_NOMBRE_CLIENTE, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()==1 || lstPorFiltro.size()>0){
				return (Mchofer)lstPorFiltro.get(0);				
			}else {
				throw new NoExistenItemsException();
			}			
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}		
	}
	
	
	
	public Mchofer getChoferActivoPorCliente(Integer nroDocumento, Integer codCliente) throws NoExistenItemsException, VariosChoferesConIgualDNIException, IOException, DataAccessErrorException{
		try{
			Messages mensajeria = new Messages();
			List lstChoferesPorFiltro= new ArrayList();
			Map<String, Object> params = new HashMap<String, Object>();			
			params.put(Const.PARAM_DNI, nroDocumento);		
			params.put(Const.PARAM_COD_CLIENTE, codCliente);
			
			Query query = this.getNamedQuery(MchoferDAO.FIND_CHOFER_ACTIVO_POR_CLIENTE, params, session);
			lstChoferesPorFiltro= query.list();
			
			if(lstChoferesPorFiltro.size()==1){
				return (Mchofer)lstChoferesPorFiltro.get(0);				
			}else {
				 throw new NoExistenItemsException(mensajeria.getMessage().getString("chofer_no_existe_msg"));
			} 		
			
		}catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();			
			throw new DataAccessErrorException();
		}
		
	}

}