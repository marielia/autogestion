package com.refinor.extranet.data.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.base.BaseMusuarioWebDAO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.UsuarioNoExisteException;


public class MusuarioWebDAO extends BaseMusuarioWebDAO implements com.refinor.extranet.data.dao.iface.MusuarioWebDAO {

	public MusuarioWebDAO () {}
	
	public MusuarioWebDAO (Session session) {
		super(session);
	}
	
	public static String FIND_BY_COD_CLIENTE_ALFA_AND_PASS= "findByCodClienteAlfaAndPass";
	public static String FIND_BY_COD_CLIENTE_ALFA= "findByCodClienteAlfa";
	public static String FIND_USUARIO_BY_DOCUMENTO= "findUsuarioByDocumento";
	public static String FIND_USUARIO_BY_MAIL= "findUsuarioByEmail";
	public static String FIND_USUARIO_BY_COD_EMPLEADO_ADN_CCSS= "findUsuarioByCodEmpleadosYCCSS";
	
	public MusuarioWeb getPorDocumento(String numeroDocumento) throws DataAccessErrorException, UsuarioNoExisteException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_NUMERO_DOCUMENTO, numeroDocumento);
			Query usuarioQry = this.getNamedQuery(FIND_USUARIO_BY_DOCUMENTO, params, session);
			List usuarios= usuarioQry.list();
			if(usuarios.size() == 1)
				return (MusuarioWeb)usuarios.iterator().next();
			else if(usuarios.size() == 0)
				throw new UsuarioNoExisteException();
			else
				throw new DataAccessErrorException();
		} catch (UsuarioNoExisteException ex) {
			throw new UsuarioNoExisteException();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public MusuarioWeb getCodEmpleadoYCCSS(int codEmpleado, int ccss) throws DataAccessErrorException, UsuarioNoExisteException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_CODIGO, codEmpleado);
			params.put(Const.PARAM_COD_CCSS, ccss);
			
			Query usuarioQry = this.getNamedQuery(FIND_USUARIO_BY_COD_EMPLEADO_ADN_CCSS, params, session);
			List usuarios= usuarioQry.list();
			if(usuarios.size() == 1)
				return (MusuarioWeb)usuarios.iterator().next();
			else if(usuarios.size() == 0)
				throw new UsuarioNoExisteException();
			else
				throw new DataAccessErrorException();
		} catch (UsuarioNoExisteException ex) {
			throw new UsuarioNoExisteException();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public MusuarioWeb getPorEmail(String email) throws UsuarioNoExisteException, DataAccessErrorException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.EMAIL, email);
			Query usuariosQry = this.getNamedQuery(FIND_USUARIO_BY_MAIL, params, session);
			List usuarios= usuariosQry.list();
			if(usuarios.size() == 1)
			//if(usuarios.size() >= 1)  // Modificado para Validacion Inhibida para testing !!!
				return (MusuarioWeb)usuarios.iterator().next();
			else if(usuarios.size() == 0)
				throw new UsuarioNoExisteException();
			else
				throw new DataAccessErrorException();
		} catch (UsuarioNoExisteException ex) {
			throw ex;
		} catch (DataAccessErrorException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	public MusuarioWeb getPorDocumentoYClave(String codClienteAlfa, String clave) throws UsuarioNoExisteException, DataAccessErrorException {
		try {			
			Map<String, Object> params= new HashMap<String, Object>();
			
			params.put(Const.PARAM_COD_CLIENTE_ALFA, codClienteAlfa);
			params.put(Const.PARAM_CLAVE, clave);
			List result= this.getNamedQuery(FIND_BY_COD_CLIENTE_ALFA_AND_PASS, params, session).list();
			if(result.size() == 1) 
				return (MusuarioWeb)result.iterator().next();
			else if(result.size() == 0)
				throw new UsuarioNoExisteException();
			else 
				throw new DataAccessErrorException();
		} catch(UsuarioNoExisteException ex) {
			throw ex;
		} catch(Exception ex) {
			throw new DataAccessErrorException();
		}
	}
	
	public MusuarioWeb getPorCodclienteAlfa(String codClienteAlfa) throws UsuarioNoExisteException, DataAccessErrorException {
		try {			
			Map<String, Object> params= new HashMap<String, Object>();
			
			params.put(Const.PARAM_COD_CLIENTE_ALFA, codClienteAlfa);			
			List result= this.getNamedQuery(FIND_BY_COD_CLIENTE_ALFA, params, session).list();
			if(result.size() == 1) 
				return (MusuarioWeb)result.iterator().next();
			else if(result.size() == 0)
				throw new UsuarioNoExisteException();
			else 
				throw new DataAccessErrorException();
		} catch(UsuarioNoExisteException ex) {
			throw ex;
		} catch(Exception ex) {
			throw new DataAccessErrorException();
		}
	}


}