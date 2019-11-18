package com.refinor.extranet.data.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.refinor.extranet.data.dao.iface.MchoferDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseMchoferDAO extends com.refinor.extranet.data.dao._RootDAO {

	public BaseMchoferDAO () {}
	
	public BaseMchoferDAO (Session session) {
		super(session);
	}

	// query name references
	public static final String QUERY_FIND_CHOFER_NRO_DOCUMENTO = "findChoferNroDocumento";
	public static final String QUERY_FIND_CHOFER_POR_CLIENTE = "findChoferPorCliente";
	public static final String QUERY_FIND_CHOFER_D_N_IX_CODIGO_CHOFX_CLIENTE = "findChoferDNIxCodigoChofxCliente";
	public static final String QUERY_FIND_CHOFER_D_N_IX_CLIENTE = "findChoferDNIxCliente";
	public static final String QUERY_FIND_CHOFER_POR_NOMBRE_CLIENTE = "findChoferPorNombreCliente";
	public static final String QUERY_FIND_CHOFER_BY_NOMBRE_GRUPO_U_N_UNIDAD_NEGOCIO = "findChoferByNombreGrupoUNUnidadNegocio";
	public static final String QUERY_FIND_CODIGO_CHOFER = "findCodigoChofer";


	public static MchoferDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static MchoferDAO getInstance () {
		if (null == instance) instance = new com.refinor.extranet.data.dao.MchoferDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.refinor.extranet.data.Mchofer.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.refinor.extranet.data.Mchofer
	 */
	public com.refinor.extranet.data.Mchofer cast (Object object) {
		return (com.refinor.extranet.data.Mchofer) object;
	}

	public com.refinor.extranet.data.Mchofer get(int key)
	{
		return (com.refinor.extranet.data.Mchofer) get(getReferenceClass(), new java.lang.Integer(key));
	}

	public com.refinor.extranet.data.Mchofer get(int key, Session s)
	{
		return (com.refinor.extranet.data.Mchofer) get(getReferenceClass(), new java.lang.Integer(key), s);
	}

	public com.refinor.extranet.data.Mchofer load(int key)
	{
		return (com.refinor.extranet.data.Mchofer) load(getReferenceClass(), new java.lang.Integer(key));
	}

	public com.refinor.extranet.data.Mchofer load(int key, Session s)
	{
		return (com.refinor.extranet.data.Mchofer) load(getReferenceClass(), new java.lang.Integer(key), s);
	}

	public com.refinor.extranet.data.Mchofer loadInitialize(int key, Session s) 
	{ 
		com.refinor.extranet.data.Mchofer obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.refinor.extranet.data.Mchofer> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.refinor.extranet.data.Mchofer> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.refinor.extranet.data.Mchofer> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param mchofer a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.refinor.extranet.data.Mchofer mchofer)
	{
		return (java.lang.Integer) super.save(mchofer);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param mchofer a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.refinor.extranet.data.Mchofer mchofer, Session s)
	{
		return (java.lang.Integer) save((Object) mchofer, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param mchofer a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.refinor.extranet.data.Mchofer mchofer)
	{
		saveOrUpdate((Object) mchofer);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param mchofer a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.refinor.extranet.data.Mchofer mchofer, Session s)
	{
		saveOrUpdate((Object) mchofer, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param mchofer a transient instance containing updated state
	 */
	public void update(com.refinor.extranet.data.Mchofer mchofer) 
	{
		update((Object) mchofer);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param mchofer a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.refinor.extranet.data.Mchofer mchofer, Session s)
	{
		update((Object) mchofer, s);
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
	 * @param mchofer the instance to be removed
	 */
	public void delete(com.refinor.extranet.data.Mchofer mchofer)
	{
		delete((Object) mchofer);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param mchofer the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.refinor.extranet.data.Mchofer mchofer, Session s)
	{
		delete((Object) mchofer, s);
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
	public void refresh (com.refinor.extranet.data.Mchofer mchofer, Session s)
	{
		refresh((Object) mchofer, s);
	}


}