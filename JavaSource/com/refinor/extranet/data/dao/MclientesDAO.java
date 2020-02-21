package com.refinor.extranet.data.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.Mccss;
import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.base.BaseMclientesDAO;
import com.refinor.extranet.to.MChoferTO;
import com.refinor.extranet.to.MClienteTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;
import com.refinor.extranet.util.exception.UsuarioNoExisteException;


public class MclientesDAO extends BaseMclientesDAO implements com.refinor.extranet.data.dao.iface.MclientesDAO {

	public MclientesDAO () {}
	
	public MclientesDAO (Session session) {
		super(session);
	}
	
	public static String FIND_PERSONAS_BY_DOCUMENTO= "findPersonaByDocumento";
	public static String FIND_PERSONAS_BY_DOCUMENTO_EMAIL= "findPersonaByDocumentoEMail";
	public static String FIND_PERSONAS_POR_ESTADO= "findPersonasPorEstado";
	public static String FIND_CLIENTE_POR_ID= "findClientePorId";
	
	
	public List getPorDocumento(String numeroDocumento, String email) throws PersonaNoExisteException, DataAccessErrorException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_NUMERO_DOCUMENTO, numeroDocumento);
			params.put(Const.EMAIL, email);
			Query personasQry = this.getNamedQuery(FIND_PERSONAS_BY_DOCUMENTO_EMAIL, params, session);
			List personas= personasQry.list();
			if(personas.size() == 0)
				throw new PersonaNoExisteException();
			else
				return personas;
		} catch (PersonaNoExisteException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public List getPorDocumento(String numeroDocumento) throws PersonaNoExisteException, DataAccessErrorException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_NUMERO_DOCUMENTO, numeroDocumento);			
			Query personasQry = this.getNamedQuery(FIND_PERSONAS_BY_DOCUMENTO, params, session);
			List personas= personasQry.list();
			if(personas.size() == 0)
				throw new PersonaNoExisteException();
			else
				return personas;
		} catch (PersonaNoExisteException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public List getClientePorEstado(Boolean estado) throws PersonaNoExisteException, DataAccessErrorException {
		try {	
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_ESTADO, estado);	
			Query personasQry = this.getNamedQuery(FIND_PERSONAS_POR_ESTADO,params, session);
			List personas= personasQry.list();
			return personas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public List getClientePorId(Integer id) throws PersonaNoExisteException, DataAccessErrorException {
		try {	
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CLIENTE, id);	
			Query personasQry = this.getNamedQuery(FIND_CLIENTE_POR_ID,params, session);
			List personas= personasQry.list();
			return personas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public Mclientes getClienteById(Integer id) throws PersonaNoExisteException, DataAccessErrorException {
		try {	
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CLIENTE, id);	
			Query personasQry = this.getNamedQuery(FIND_CLIENTE_POR_ID,params, session);
			List personas= personasQry.list();
			if(personas.size() == 1)
				return (Mclientes)personas.iterator().next();
			else if(personas.size() == 0)
				return null;
			else
				throw new DataAccessErrorException();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	/**
	 *  Recupera un Usuario por su correo electrónico.
	 *  
	 * @param email	Correo electrónico del Usuario
	 * @return	El Usuario encontrado.
	 * @throws UsuarioNoExisteException
	 * @throws DataAccessErrorException
	 */
	public Mclientes getPorEmail(String email) throws  DataAccessErrorException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("email", email);
			Query usuariosQry = this.getNamedQuery("findUsuarioByEmail", params, session);
			List usuarios= usuariosQry.list();
//			if(usuarios.size() == 1)
			if(usuarios.size() == 1)  // Modificado para Validacion Inhibida para testing !!!
				return (Mclientes)usuarios.iterator().next();
			else if(usuarios.size() == 0)
				return null;
			else
				throw new DataAccessErrorException();
		} catch (DataAccessErrorException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	
	public static String FIND_CLIENTE_x_COD_CLIENTE_ALFA = "findClienteXCodClienteAlfa";	
	public Mclientes getClienteporCodClienteAlfa(String descripcion) throws DataAccessErrorException, NoExistenItemsException {
		try{			
			List lstPorFiltro= new ArrayList();				
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_CLIENTE_ALFA, descripcion);
			
			Query query = this.getNamedQuery(FIND_CLIENTE_x_COD_CLIENTE_ALFA, params, session);
			lstPorFiltro = query.list();
			
			if(lstPorFiltro.size()==1){
				return (Mclientes)lstPorFiltro.get(0);				
			}else {
				throw new NoExistenItemsException();
			}	        
			
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
		
	}
	
	public static String FIND_CLIENTE_BY_FILTROS = "findClienteByFiltros";	
	public List getClientesporFiltros(String codClienteAlfa,int codCliente,String cuit,String nombreCliente, Boolean activo,Boolean baja) throws DataAccessErrorException, NoExistenItemsException {
			try{			
				List lstPorFiltro= new ArrayList();	
				Messages mensajeria = new Messages();
				
				codClienteAlfa= "%"+codClienteAlfa+"%";
				nombreCliente= "%"+nombreCliente+"%";
				if(cuit==null || cuit.trim().equals("")) cuit="%";				
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(Const.PARAM_COD_CLIENTE_ALFA, codClienteAlfa);
				params.put(Const.PARAM_COD_CLIENTE, codCliente);
				params.put(Const.PARAM_CUIT, cuit);
				params.put(Const.PARAM_DESCRIPCION, nombreCliente);
				params.put(Const.PARAM_ACTIVO, activo);
				params.put(Const.PARAM_BAJA, baja);					
				
				Query query = this.getNamedQuery(FIND_CLIENTE_BY_FILTROS, params, session);
				lstPorFiltro= query.list();
				
				if(lstPorFiltro.size()>0){
					Iterator itCliente = lstPorFiltro.iterator();
					lstPorFiltro= new ArrayList();
					MClienteTO mClienteTO= new MClienteTO();
					Object[] objChofer= null;					
					SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
					
					while(itCliente.hasNext()){
				        objChofer = (Object[]) itCliente.next(); 
				        mClienteTO=new MClienteTO();
				        mClienteTO.setCodClienteAlfa(objChofer[0].toString());			       
				        mClienteTO.setNombre(objChofer[1].toString());
				        mClienteTO.setCuit(objChofer[2].toString());
				       if(objChofer[3]!=null) mClienteTO.setCorreo(objChofer[3].toString());
				       if(objChofer[4]!=null) mClienteTO.setTelefono(objChofer[4].toString());
				        mClienteTO.setDomicilio(objChofer[5].toString());
				        mClienteTO.setCodPostal(new Integer(objChofer[6].toString()));
				        mClienteTO.setLocalidad(objChofer[7].toString());	
				        mClienteTO.setProvincia(objChofer[8].toString());
				        mClienteTO.setCategoriaIVA(objChofer[9].toString());
				        mClienteTO.setLimiteCredito(new BigDecimal(objChofer[10].toString()));mClienteTO.setActivo(new Boolean(objChofer[11].toString()));
				        mClienteTO.setActivoDesc(mClienteTO.getActivo()? mensajeria.getMessage().getString("si_label") : mensajeria.getMessage().getString("no_label"));
		
				        mClienteTO.setNumeroIIBB(new BigDecimal(objChofer[12].toString()));	
				        mClienteTO.setCondicionVta(objChofer[13].toString());
				        mClienteTO.setInscripcionIIBB(objChofer[14].toString());
				        if(objChofer[15]!=null)mClienteTO.setFechaBaja(sdf.format(objChofer[15]));
				        
				        lstPorFiltro.add(mClienteTO);
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
	
	
	
	

}