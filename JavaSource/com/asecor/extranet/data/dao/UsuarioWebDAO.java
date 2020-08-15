package com.asecor.extranet.data.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.asecor.extranet.data.TitularWeb;
import com.asecor.extranet.data.base.BaseOlUserDAO;
//import com.asecor.extranet.data.OlUserAgency;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.exception.DataAccessErrorException;
import com.asecor.extranet.util.exception.ExisteEmailException;
import com.asecor.extranet.util.exception.PersonaNoExisteException;
import com.asecor.extranet.util.exception.UsuarioNoExisteException;
import com.asecor.extranet.util.exception.UsuarioYaRegistradoException;


public class UsuarioWebDAO extends BaseOlUserDAO implements com.asecor.extranet.data.dao.iface.OlUserDAO {

	public UsuarioWebDAO () {}
	
	public UsuarioWebDAO (Session session) {
		super(session);
	}
	public static String FIND_BY_DNI_AND_PASS= "findByUserDniAndPass";
	public static String FIND_BY_EMAIL_AND_PASS= "findByUserEmailAndPass";
	public static String FIND_BY_USER_AND_PASS= "findByUserNameAndPass";
	public static String FIND_BY_USER_DNI= "findByUserDni";
	public static String FIND_USER_BY_MAIL_AND_PHONE= "findUserByEmailAndDNI";
	public static String FIND_USER_MAIL_AND_= "findUserByMailAndPin";
	public static String FIND_USER_BY_MAIL_AND_DNI= "findUserByEmailAndDni";
	
	
	public TitularWeb ExistPinAndEmail(String pin, String email) throws DataAccessErrorException, UsuarioNoExisteException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_PIN, pin);
			params.put(Const.PARAM_EMAIL, email);
			params.put(Const.ACTIVE, true);
			
			Query usuarioQry = this.getNamedQuery(FIND_USER_MAIL_AND_, params, session);
			List usuarios= usuarioQry.list();
			if(usuarios.size() == 1)
				return (TitularWeb)usuarios.iterator().next();
			else if(usuarios.size() == 0)
				throw new UsuarioNoExisteException();
			else
				throw new DataAccessErrorException();
		}   catch (UsuarioNoExisteException ex) {
			throw new UsuarioNoExisteException();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	public TitularWeb getByEmailAndDNI(String email, Long dni) throws UsuarioNoExisteException, DataAccessErrorException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.EMAIL, email);
			params.put(Const.DNI, dni);
			params.put(Const.ACTIVE, true);
			
			Query usuariosQry = this.getNamedQuery(FIND_USER_BY_MAIL_AND_DNI, params, session);
			List usuarios= usuariosQry.list();
			if(usuarios.size() > 0) 
				return (TitularWeb)usuarios.iterator().next();
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
	
	
	public TitularWeb getByUserDniAndPass(Long dni, String pass) throws UsuarioNoExisteException, DataAccessErrorException {
		try {			
			Map<String, Object> params= new HashMap<String, Object>(); 
			params.put(Const.PARAM_USER_DNI, dni);
			params.put(Const.PARAM_PASS, pass);
 
			List result= this.getNamedQuery(FIND_BY_DNI_AND_PASS, params, session).list();
			if(result.size() == 1) 
				return (TitularWeb)result.iterator().next();
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
	
	
	public TitularWeb getByEmailAndDni(String email, String dni) throws UsuarioNoExisteException, DataAccessErrorException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.EMAIL, email);
			params.put(Const.DNI, dni);
			params.put(Const.ACTIVE, true);
			
			Query usuariosQry = this.getNamedQuery(FIND_USER_BY_MAIL_AND_DNI, params, session);
			List usuarios= usuariosQry.list();
			if(usuarios.size() == 1) 
				return (TitularWeb)usuarios.iterator().next();
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
	public TitularWeb getByUserDni(Long dni) throws UsuarioNoExisteException, DataAccessErrorException {
		try {			
			Map<String, Object> params= new HashMap<String, Object>(); 
			params.put(Const.PARAM_USER_DNI, dni);
			params.put(Const.ACTIVE, true);
			
			List result= this.getNamedQuery(FIND_BY_USER_DNI, params, session).list();
			if(result.size() == 1) 
				return (TitularWeb)result.iterator().next();
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

	
	public TitularWeb registerUser(TitularWeb usr ) 
			throws PersonaNoExisteException, ExisteEmailException, UsuarioYaRegistradoException, 
					DataAccessErrorException {
			      Transaction tx= null;  
				 
				  try {
						  getByEmailAndDNI(usr.getEmail(),usr.getDni());
						throw new ExisteEmailException();   
				 	} catch(UsuarioNoExisteException e) {	
						// Crea el nuevo usuario 
						tx= session.beginTransaction(); 
						try {
							save(usr, session);
							
							/*OlUserAgency olUserAgency = new OlUserAgency();
							olUserAgency.setAgencyId(agencyId);
							olUserAgency.setUserId(usr.getId());
							OlUserAgencyDAO olUserAgencyDAO= new OlUserAgencyDAO(session);
							olUserAgencyDAO.save(olUserAgency,session);
							*/
							tx.commit();
							return usr;
						} catch(Exception excep) {
							excep.printStackTrace();
							tx.rollback();
							throw new DataAccessErrorException();
						}
			        }catch(ExisteEmailException e) {	// El Mail ya está en uso por otro usuario  // Validacion Inhibida para testing !!!
						throw e;
					} catch(Exception e) {
						throw new DataAccessErrorException();
					} 
				  
						
			}
	
	public TitularWeb confirmUser(TitularWeb usr)  throws  DataAccessErrorException {
		 Transaction tx= null; 
			try { 
				 tx= session.beginTransaction(); 
				 saveOrUpdate(usr, session);
				 tx.commit();
				return usr;
			} catch(Exception excep) {
				excep.printStackTrace();
				tx.rollback();
				throw new DataAccessErrorException();
			}   
						
   }

	public TitularWeb getByEmailAndPass(String email, String pass) throws UsuarioNoExisteException, DataAccessErrorException {
		try {			
			Map<String, Object> params= new HashMap<String, Object>(); 
			params.put(Const.PARAM_EMAIL, email);
			params.put(Const.PARAM_PASS, pass);
 
			List result= this.getNamedQuery(FIND_BY_EMAIL_AND_PASS, params, session).list();
			if(result.size() == 1) 
				return (TitularWeb)result.iterator().next();
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
   
			
		/*
	public void saveAgency(OlUser usr, Integer agencyId, String token)  throws  DataAccessErrorException {
		 Transaction tx= null; 
			try { 
				
				 OlUserAgencyDAO olUserAgencyDAO = new OlUserAgencyDAO(session);
				 OlUserAgency OlUserAgency= new OlUserAgency();
				 DataUtil dataUtil= new DataUtil();				 
				 DateTO dateTO = dataUtil.getFechaActual();
				try { 
					
			        OlUserAgency = olUserAgencyDAO.getByUserAgendy(usr.getId(), agencyId); 
			        //si la relacion usuario y empresa existe, no hago nada
			        
				} catch(NoExistenItemsException excep) {
					 
					//si no existe la relacion de usuario y empresa, la guardo
					OlUserAgency= new OlUserAgency();
					OlUserAgency.setUserId(usr.getId());
					OlUserAgency.setAgencyId(agencyId); 
					OlUserAgency.setCreateDate(new Date());
			 		OlUserAgency.setCreateUser(Const.SISTEMA);
					 tx= session.beginTransaction();
					olUserAgencyDAO.saveOrUpdate(OlUserAgency, session);
					tx.commit(); 
					
				} catch(Exception excep) {
					excep.printStackTrace(); 
					tx.rollback();
					throw new DataAccessErrorException();
				}   
				
				
				try { 
				    //actualizo el token
					OlUserAgency.setToken(token);
					OlUserAgency.setUpdateDate(new Date());
					OlUserAgency.setUpdateUser(Const.SISTEMA); 
				    tx= session.beginTransaction();
					olUserAgencyDAO.saveOrUpdate(OlUserAgency, session);
					tx.commit(); 
				} catch(Exception excep) {
					excep.printStackTrace(); 
					tx.rollback();
					throw new DataAccessErrorException();
				} 
			
			} catch(Exception excep) {
				excep.printStackTrace(); 
				throw new DataAccessErrorException();
			}  
	}

	
	public void saveAgency_V2(OlUser usr, Integer agencyId, String token)  throws  DataAccessErrorException {
		 Transaction tx= null; 
			try { 
				
				 OlUserAgencyDAO olUserAgencyDAO = new OlUserAgencyDAO(session);
				 OlUserAgency OlUserAgency= new OlUserAgency();
				 DataUtil dataUtil= new DataUtil();				 
				 DateTO dateTO = dataUtil.getFechaActual();
				
				 try {  
					 
					//si no existe la relacion de usuario y empresa, la guardo
					OlUserAgency= new OlUserAgency();
					OlUserAgency.setUserId(usr.getId());
					OlUserAgency.setAgencyId(agencyId);
					OlUserAgency.setToken(token);
					OlUserAgency.setCreateDate(new Date());
			 		OlUserAgency.setCreateUser(Const.SISTEMA);
					 tx= session.beginTransaction();
					olUserAgencyDAO.saveOrUpdate(OlUserAgency, session);
					tx.commit(); 
					
				} catch(Exception excep) {
					excep.printStackTrace(); 
					tx.rollback();
					throw new DataAccessErrorException();
				}   
			
			} catch(Exception excep) {
				excep.printStackTrace(); 
				throw new DataAccessErrorException();
			}  
	}

*/

}