package com.refinor.extranet.data.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.refinor.extranet.data.dao.iface.MpedidosDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseMpedidosDAO extends com.refinor.extranet.data.dao._RootDAO {

	public BaseMpedidosDAO () {}
	
	public BaseMpedidosDAO (Session session) {
		super(session);
	}

	// query name references
	public static final String QUERY_FIND_REMITOS_BY_CLIENTE_PATENTE = "findRemitosByClientePatente";
	public static final String QUERY_OBTENER_SUMA_CONSUMO_POR_CLIENTE_PATENTE = "obtenerSumaConsumoPorClientePatente";
	public static final String QUERY_FIND_CONSUMOS_VEHICULOS = "findConsumosVehiculos";
	public static final String QUERY_FIND_REMITOS_BY_CLIENTE_PATENTE_PRODUCTO_FECHAS = "findRemitosByClientePatenteProductoFechas";
	public static final String QUERY_FIND_CONSUMOS_POR_GRUPO_VEHICULOS = "findConsumosPorGrupoVehiculos";
	public static final String QUERY_FIND_CONSUMOS_CHOFERES = "findConsumosChoferes";
	public static final String QUERY_FIND_REMITOS_BY_CLIENTE_D_N_I_PRODUCTO_FECHAS = "findRemitosByClienteDNIProductoFechas";
	public static final String QUERY_FIND_CONSUMOS_POR_GRUPO_CHOFERES = "findConsumosPorGrupoChoferes";
	public static final String QUERY_FIND_REMITOS_BY_CLIENTE_PRODUCTO_FECHAS = "findRemitosByClienteProductoFechas";
	public static final String QUERY_FIND_REMITOS_BY_CLIENTE = "findRemitosByCliente";
	public static final String QUERY_FIND_REMITOS_A_FACTURAR = "findRemitosAFacturar";
	public static final String QUERY_FIND_REMITOS_POR_CIENTEY_ORDEN_FACTURA = "findRemitosPorCienteyOrdenFactura";
	public static final String QUERY_FIND_REMITOS_POR_CIENTE_REMITO_SUCURSAL = "findRemitosPorCienteRemitoSucursal";
	public static final String QUERY_FIND_REMITOS_FACTURADO_EN_M_FACTURA_V = "findRemitosFacturadoEnMFacturaV";
	public static final String QUERY_FIND_REMITOS_FACTURADO_EN_M_REMITO_FACTURA = "findRemitosFacturadoEnMRemitoFactura";
	public static final String QUERY_FIND_REMITOS_FACTURADO_EN_M_REMITO_FACTURA_REFACTURADOS = "findRemitosFacturadoEnMRemitoFacturaRefacturados";
	public static final String QUERY_FIND_REMITOS_NO_FACTURADOS = "findRemitosNoFacturados";
	public static final String QUERY_FIND_REMITOS_NO_FACTURADOS_NO_REFACTURADOS = "findRemitosNoFacturadosNoRefacturados";
	public static final String QUERY_FIN_MESES_M_PEDIDOS = "finMesesMPedidos";
	public static final String QUERY_FIN_ANIOS_M_PEDIDOS = "finAniosMPedidos";
	public static final String QUERY_FIND_REMITOS_FACTURAD_Y_NO_FACTURADOS_V_2 = "findRemitosFacturadYNoFacturadosV_2";


	public static MpedidosDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static MpedidosDAO getInstance () {
		if (null == instance) instance = new com.refinor.extranet.data.dao.MpedidosDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.refinor.extranet.data.Mpedidos.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.refinor.extranet.data.Mpedidos
	 */
	public com.refinor.extranet.data.Mpedidos cast (Object object) {
		return (com.refinor.extranet.data.Mpedidos) object;
	}

	public com.refinor.extranet.data.Mpedidos get(com.refinor.extranet.data.MpedidosId key)
	{
		return (com.refinor.extranet.data.Mpedidos) get(getReferenceClass(), key);
	}

	public com.refinor.extranet.data.Mpedidos get(com.refinor.extranet.data.MpedidosId key, Session s)
	{
		return (com.refinor.extranet.data.Mpedidos) get(getReferenceClass(), key, s);
	}

	public com.refinor.extranet.data.Mpedidos load(com.refinor.extranet.data.MpedidosId key)
	{
		return (com.refinor.extranet.data.Mpedidos) load(getReferenceClass(), key);
	}

	public com.refinor.extranet.data.Mpedidos load(com.refinor.extranet.data.MpedidosId key, Session s)
	{
		return (com.refinor.extranet.data.Mpedidos) load(getReferenceClass(), key, s);
	}

	public com.refinor.extranet.data.Mpedidos loadInitialize(com.refinor.extranet.data.MpedidosId key, Session s) 
	{ 
		com.refinor.extranet.data.Mpedidos obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.refinor.extranet.data.Mpedidos> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.refinor.extranet.data.Mpedidos> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.refinor.extranet.data.Mpedidos> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param mpedidos a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public com.refinor.extranet.data.MpedidosId save(com.refinor.extranet.data.Mpedidos mpedidos)
	{
		return (com.refinor.extranet.data.MpedidosId) super.save(mpedidos);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param mpedidos a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public com.refinor.extranet.data.MpedidosId save(com.refinor.extranet.data.Mpedidos mpedidos, Session s)
	{
		return (com.refinor.extranet.data.MpedidosId) save((Object) mpedidos, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param mpedidos a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.refinor.extranet.data.Mpedidos mpedidos)
	{
		saveOrUpdate((Object) mpedidos);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param mpedidos a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.refinor.extranet.data.Mpedidos mpedidos, Session s)
	{
		saveOrUpdate((Object) mpedidos, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param mpedidos a transient instance containing updated state
	 */
	public void update(com.refinor.extranet.data.Mpedidos mpedidos) 
	{
		update((Object) mpedidos);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param mpedidos a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.refinor.extranet.data.Mpedidos mpedidos, Session s)
	{
		update((Object) mpedidos, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(com.refinor.extranet.data.MpedidosId id)
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
	public void delete(com.refinor.extranet.data.MpedidosId id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param mpedidos the instance to be removed
	 */
	public void delete(com.refinor.extranet.data.Mpedidos mpedidos)
	{
		delete((Object) mpedidos);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param mpedidos the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.refinor.extranet.data.Mpedidos mpedidos, Session s)
	{
		delete((Object) mpedidos, s);
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
	public void refresh (com.refinor.extranet.data.Mpedidos mpedidos, Session s)
	{
		refresh((Object) mpedidos, s);
	}


}