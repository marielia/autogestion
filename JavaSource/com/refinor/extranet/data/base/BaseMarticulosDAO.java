package com.refinor.extranet.data.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.refinor.extranet.data.dao.iface.MarticulosDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseMarticulosDAO extends com.refinor.extranet.data.dao._RootDAO {

	public BaseMarticulosDAO () {}
	
	public BaseMarticulosDAO (Session session) {
		super(session);
	}

	// query name references
	public static final String QUERY_FIND_ARTICULOS = "findArticulos";


	public static MarticulosDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static MarticulosDAO getInstance () {
		if (null == instance) instance = new com.refinor.extranet.data.dao.MarticulosDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.refinor.extranet.data.Marticulos.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.refinor.extranet.data.Marticulos
	 */
	public com.refinor.extranet.data.Marticulos cast (Object object) {
		return (com.refinor.extranet.data.Marticulos) object;
	}

	public com.refinor.extranet.data.Marticulos get(int key)
	{
		return (com.refinor.extranet.data.Marticulos) get(getReferenceClass(), new java.lang.Integer(key));
	}

	public com.refinor.extranet.data.Marticulos get(int key, Session s)
	{
		return (com.refinor.extranet.data.Marticulos) get(getReferenceClass(), new java.lang.Integer(key), s);
	}

	public com.refinor.extranet.data.Marticulos load(int key)
	{
		return (com.refinor.extranet.data.Marticulos) load(getReferenceClass(), new java.lang.Integer(key));
	}

	public com.refinor.extranet.data.Marticulos load(int key, Session s)
	{
		return (com.refinor.extranet.data.Marticulos) load(getReferenceClass(), new java.lang.Integer(key), s);
	}

	public com.refinor.extranet.data.Marticulos loadInitialize(int key, Session s) 
	{ 
		com.refinor.extranet.data.Marticulos obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.refinor.extranet.data.Marticulos> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.refinor.extranet.data.Marticulos> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.refinor.extranet.data.Marticulos> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param marticulos a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.refinor.extranet.data.Marticulos marticulos)
	{
		return (java.lang.Integer) super.save(marticulos);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param marticulos a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.refinor.extranet.data.Marticulos marticulos, Session s)
	{
		return (java.lang.Integer) save((Object) marticulos, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param marticulos a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.refinor.extranet.data.Marticulos marticulos)
	{
		saveOrUpdate((Object) marticulos);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param marticulos a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.refinor.extranet.data.Marticulos marticulos, Session s)
	{
		saveOrUpdate((Object) marticulos, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param marticulos a transient instance containing updated state
	 */
	public void update(com.refinor.extranet.data.Marticulos marticulos) 
	{
		update((Object) marticulos);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param marticulos a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.refinor.extranet.data.Marticulos marticulos, Session s)
	{
		update((Object) marticulos, s);
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
	 * @param marticulos the instance to be removed
	 */
	public void delete(com.refinor.extranet.data.Marticulos marticulos)
	{
		delete((Object) marticulos);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param marticulos the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.refinor.extranet.data.Marticulos marticulos, Session s)
	{
		delete((Object) marticulos, s);
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
	public void refresh (com.refinor.extranet.data.Marticulos marticulos, Session s)
	{
		refresh((Object) marticulos, s);
	}


}