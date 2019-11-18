package com.refinor.extranet.data.dao.iface;

import java.io.Serializable;

public interface MvehiculoDAO {
	public com.refinor.extranet.data.Mvehiculo get(int key);

	public com.refinor.extranet.data.Mvehiculo load(int key);

	public java.util.List<com.refinor.extranet.data.Mvehiculo> findAll ();


	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param mvehiculo a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.refinor.extranet.data.Mvehiculo mvehiculo);

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param mvehiculo a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.refinor.extranet.data.Mvehiculo mvehiculo);

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param mvehiculo a transient instance containing updated state
	 */
	public void update(com.refinor.extranet.data.Mvehiculo mvehiculo);

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(int id);

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param mvehiculo the instance to be removed
	 */
	public void delete(com.refinor.extranet.data.Mvehiculo mvehiculo);


}