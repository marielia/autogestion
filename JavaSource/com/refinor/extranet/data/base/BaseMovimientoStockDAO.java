package com.refinor.extranet.data.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.refinor.extranet.data.dao.iface.MovimientoStockDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseMovimientoStockDAO extends com.refinor.extranet.data.dao._RootDAO {

	public BaseMovimientoStockDAO () {}
	
	public BaseMovimientoStockDAO (Session session) {
		super(session);
	}

	// query name references
	public static final String QUERY_FIND_MOVIMIENTO = "findMovimiento";


	public static MovimientoStockDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static MovimientoStockDAO getInstance () {
		if (null == instance) instance = new com.refinor.extranet.data.dao.MovimientoStockDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.refinor.extranet.data.MovimientoStock.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.refinor.extranet.data.MovimientoStock
	 */
	public com.refinor.extranet.data.MovimientoStock cast (Object object) {
		return (com.refinor.extranet.data.MovimientoStock) object;
	}

	public com.refinor.extranet.data.MovimientoStock get(com.refinor.extranet.data.MovimientoStockId key)
	{
		return (com.refinor.extranet.data.MovimientoStock) get(getReferenceClass(), key);
	}

	public com.refinor.extranet.data.MovimientoStock get(com.refinor.extranet.data.MovimientoStockId key, Session s)
	{
		return (com.refinor.extranet.data.MovimientoStock) get(getReferenceClass(), key, s);
	}

	public com.refinor.extranet.data.MovimientoStock load(com.refinor.extranet.data.MovimientoStockId key)
	{
		return (com.refinor.extranet.data.MovimientoStock) load(getReferenceClass(), key);
	}

	public com.refinor.extranet.data.MovimientoStock load(com.refinor.extranet.data.MovimientoStockId key, Session s)
	{
		return (com.refinor.extranet.data.MovimientoStock) load(getReferenceClass(), key, s);
	}

	public com.refinor.extranet.data.MovimientoStock loadInitialize(com.refinor.extranet.data.MovimientoStockId key, Session s) 
	{ 
		com.refinor.extranet.data.MovimientoStock obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.refinor.extranet.data.MovimientoStock> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.refinor.extranet.data.MovimientoStock> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.refinor.extranet.data.MovimientoStock> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param movimientoStock a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public com.refinor.extranet.data.MovimientoStockId save(com.refinor.extranet.data.MovimientoStock movimientoStock)
	{
		return (com.refinor.extranet.data.MovimientoStockId) super.save(movimientoStock);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param movimientoStock a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public com.refinor.extranet.data.MovimientoStockId save(com.refinor.extranet.data.MovimientoStock movimientoStock, Session s)
	{
		return (com.refinor.extranet.data.MovimientoStockId) save((Object) movimientoStock, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param movimientoStock a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.refinor.extranet.data.MovimientoStock movimientoStock)
	{
		saveOrUpdate((Object) movimientoStock);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param movimientoStock a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.refinor.extranet.data.MovimientoStock movimientoStock, Session s)
	{
		saveOrUpdate((Object) movimientoStock, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param movimientoStock a transient instance containing updated state
	 */
	public void update(com.refinor.extranet.data.MovimientoStock movimientoStock) 
	{
		update((Object) movimientoStock);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param movimientoStock a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.refinor.extranet.data.MovimientoStock movimientoStock, Session s)
	{
		update((Object) movimientoStock, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(com.refinor.extranet.data.MovimientoStockId id)
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
	public void delete(com.refinor.extranet.data.MovimientoStockId id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param movimientoStock the instance to be removed
	 */
	public void delete(com.refinor.extranet.data.MovimientoStock movimientoStock)
	{
		delete((Object) movimientoStock);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param movimientoStock the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.refinor.extranet.data.MovimientoStock movimientoStock, Session s)
	{
		delete((Object) movimientoStock, s);
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
	public void refresh (com.refinor.extranet.data.MovimientoStock movimientoStock, Session s)
	{
		refresh((Object) movimientoStock, s);
	}


}