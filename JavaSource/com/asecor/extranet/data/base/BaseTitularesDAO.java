package com.asecor.extranet.data.base;

import org.hibernate.Hibernate;
import org.hibernate.Session; 


import org.hibernate.criterion.Order;

import com.asecor.extranet.data.dao.TitularesDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseTitularesDAO extends com.asecor.extranet.data.dao._RootDAO {

	public BaseTitularesDAO () {}
	
	public BaseTitularesDAO (Session session) {
		super(session);
	}

	// query name references
//	public static final String QUERY_FIND_USUARIO_BY_DOCUMENTO = "findUsuarioByDocumento";
//	public static final String QUERY_FIND_USUARIO_BY_EMAIL = "findUsuarioByEmail";
//	public static final String QUERY_FIND_BY_COD_CLIENTE_ALFA_AND_PASS = "findByCodClienteAlfaAndPass";
//	public static final String QUERY_FIND_BY_COD_CLIENTE_ALFA = "findByCodClienteAlfa";


	public static TitularesDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static TitularesDAO getInstance () {
		if (null == instance) instance = new com.asecor.extranet.data.dao.TitularesDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.asecor.extranet.data.Titulares.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.refinor.extranet.data.MusuarioWeb
	 */
	public com.asecor.extranet.data.Titulares cast (Object object) {
		return (com.asecor.extranet.data.Titulares) object;
	}

	public com.asecor.extranet.data.Titulares get(int key)
	{
		return (com.asecor.extranet.data.Titulares) get(getReferenceClass(), new java.lang.Integer(key));
	}

	public com.asecor.extranet.data.Titulares get(int key, Session s)
	{
		return (com.asecor.extranet.data.Titulares) get(getReferenceClass(), new java.lang.Integer(key), s);
	}

	public com.asecor.extranet.data.Titulares load(int key)
	{
		return (com.asecor.extranet.data.Titulares) load(getReferenceClass(), new java.lang.Integer(key));
	}

	public com.asecor.extranet.data.Titulares load(int key, Session s)
	{
		return (com.asecor.extranet.data.Titulares) load(getReferenceClass(), new java.lang.Integer(key), s);
	}

	public com.asecor.extranet.data.Titulares loadInitialize(int key, Session s) 
	{ 
		com.asecor.extranet.data.Titulares obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.asecor.extranet.data.Titulares> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.asecor.extranet.data.Titulares> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.asecor.extranet.data.Titulares> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param musuarioWeb a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.asecor.extranet.data.Titulares Titulares)
	{
		return (java.lang.Integer) super.save(Titulares);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param musuarioWeb a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.asecor.extranet.data.Titulares Titulares, Session s)
	{
		return (java.lang.Integer) save((Object) Titulares, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param musuarioWeb a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.asecor.extranet.data.Titulares Titulares)
	{
		saveOrUpdate((Object) Titulares);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param musuarioWeb a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.asecor.extranet.data.Titulares Titulares, Session s)
	{
		saveOrUpdate((Object) Titulares, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param musuarioWeb a transient instance containing updated state
	 */
	public void update(com.asecor.extranet.data.Titulares Titulares) 
	{
		update((Object) Titulares);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param musuarioWeb a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.asecor.extranet.data.Titulares Titulares, Session s)
	{
		update((Object) Titulares, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(int id)
	{
		delete((Object) load(id));
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param id the instance ID to be removed
	 * @param s the Session
	 */
	public void delete(int id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param musuarioWeb the instance to be removed
	 */
	public void delete(com.asecor.extranet.data.Titulares Titulares)
	{
		delete((Object) Titulares);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param musuarioWeb the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.asecor.extranet.data.Titulares Titulares, Session s)
	{
		delete((Object) Titulares, s);
	}
	
	/**
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
	 * For example 
	 * <ul> 
	 * <li>where a database trigger alters the object state upon insert or update</li>
	 * <li>after executing direct SQL (eg. a mass update) in the same session</li>
	 * <li>after inserting a Blob or Clob</li>
	 * </ul>
	 */
	public void refresh (com.asecor.extranet.data.Titulares Titulares, Session s)
	{
		refresh((Object) Titulares, s);
	}


}