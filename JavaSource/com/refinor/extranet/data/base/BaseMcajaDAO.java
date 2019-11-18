package com.refinor.extranet.data.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.refinor.extranet.data.dao.iface.McajaDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseMcajaDAO extends com.refinor.extranet.data.dao._RootDAO {

	public BaseMcajaDAO () {}
	
	public BaseMcajaDAO (Session session) {
		super(session);
	}

	// query name references


	public static McajaDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static McajaDAO getInstance () {
		if (null == instance) instance = new com.refinor.extranet.data.dao.McajaDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.refinor.extranet.data.Mcaja.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.refinor.extranet.data.Mcaja
	 */
	public com.refinor.extranet.data.Mcaja cast (Object object) {
		return (com.refinor.extranet.data.Mcaja) object;
	}

	public com.refinor.extranet.data.Mcaja get(com.refinor.extranet.data.McajaId key)
	{
		return (com.refinor.extranet.data.Mcaja) get(getReferenceClass(), key);
	}

	public com.refinor.extranet.data.Mcaja get(com.refinor.extranet.data.McajaId key, Session s)
	{
		return (com.refinor.extranet.data.Mcaja) get(getReferenceClass(), key, s);
	}

	public com.refinor.extranet.data.Mcaja load(com.refinor.extranet.data.McajaId key)
	{
		return (com.refinor.extranet.data.Mcaja) load(getReferenceClass(), key);
	}

	public com.refinor.extranet.data.Mcaja load(com.refinor.extranet.data.McajaId key, Session s)
	{
		return (com.refinor.extranet.data.Mcaja) load(getReferenceClass(), key, s);
	}

	public com.refinor.extranet.data.Mcaja loadInitialize(com.refinor.extranet.data.McajaId key, Session s) 
	{ 
		com.refinor.extranet.data.Mcaja obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.refinor.extranet.data.Mcaja> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.refinor.extranet.data.Mcaja> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.refinor.extranet.data.Mcaja> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param mcaja a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public com.refinor.extranet.data.McajaId save(com.refinor.extranet.data.Mcaja mcaja)
	{
		return (com.refinor.extranet.data.McajaId) super.save(mcaja);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param mcaja a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public com.refinor.extranet.data.McajaId save(com.refinor.extranet.data.Mcaja mcaja, Session s)
	{
		return (com.refinor.extranet.data.McajaId) save((Object) mcaja, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param mcaja a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.refinor.extranet.data.Mcaja mcaja)
	{
		saveOrUpdate((Object) mcaja);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param mcaja a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.refinor.extranet.data.Mcaja mcaja, Session s)
	{
		saveOrUpdate((Object) mcaja, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param mcaja a transient instance containing updated state
	 */
	public void update(com.refinor.extranet.data.Mcaja mcaja) 
	{
		update((Object) mcaja);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param mcaja a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.refinor.extranet.data.Mcaja mcaja, Session s)
	{
		update((Object) mcaja, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(com.refinor.extranet.data.McajaId id)
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
	public void delete(com.refinor.extranet.data.McajaId id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param mcaja the instance to be removed
	 */
	public void delete(com.refinor.extranet.data.Mcaja mcaja)
	{
		delete((Object) mcaja);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param mcaja the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.refinor.extranet.data.Mcaja mcaja, Session s)
	{
		delete((Object) mcaja, s);
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
	public void refresh (com.refinor.extranet.data.Mcaja mcaja, Session s)
	{
		refresh((Object) mcaja, s);
	}


}