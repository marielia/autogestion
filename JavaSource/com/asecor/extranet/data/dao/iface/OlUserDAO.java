package com.asecor.extranet.data.dao.iface;

import java.io.Serializable;

public interface OlUserDAO {
	public com.asecor.extranet.data.TitularWeb get(int key);

	public com.asecor.extranet.data.TitularWeb load(int key);

	public java.util.List<com.asecor.extranet.data.TitularWeb> findAll ();


	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param musuarioWeb a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.asecor.extranet.data.TitularWeb olUser);

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param musuarioWeb a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.asecor.extranet.data.TitularWeb olUser);

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param musuarioWeb a transient instance containing updated state
	 */
	public void update(com.asecor.extranet.data.TitularWeb olUser);

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(int id);

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param musuarioWeb the instance to be removed
	 */
	public void delete(com.asecor.extranet.data.TitularWeb olUser);


}