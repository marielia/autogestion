package com.refinor.extranet.data.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.refinor.extranet.data.dao.iface.MfacturasVDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseMfacturasVDAO extends com.refinor.extranet.data.dao._RootDAO {

	public BaseMfacturasVDAO () {}
	
	public BaseMfacturasVDAO (Session session) {
		super(session);
	}

	// query name references
	public static final String QUERY_FIND_FACTURAS_POR_CLIENTE_Y_FECHAS = "findFacturasPorClienteYFechas";
	public static final String QUERY_FIND_FACTURAS_NO_ABONADAS2_POR_CLIENTE_Y_FECHAS = "findFacturasNoAbonadas2PorClienteYFechas";
	public static final String QUERY_FIND_FACTURAS_NO_ABONADAS_POR_CLIENTE_Y_FECHAS = "findFacturasNoAbonadasPorClienteYFechas";
	public static final String QUERY_FIND_FACTURAS_IMPAGAS_POR_CLIENTE = "findFacturasImpagasPorCliente";
	public static final String QUERY_FIND_REMITOS_IMPAGAS_POR_CLIENTE = "findRemitosImpagasPorCliente";
	public static final String QUERY_FIND_DOCUMENTO_A_CUENTA_IMPAGOS_POR_CLIENTE = "findDocumentoACuentaImpagosPorCliente";
	public static final String QUERY_FIND_FACTURA_POR_RECIBO_CLIENTE = "findFacturaPorReciboCliente";
	public static final String QUERY_FIND_COBRANZAS_POR_CLIENTE_Y_FECHAS = "findCobranzasPorClienteYFechas";
	public static final String QUERY_FIND_C_C_S_SE_IMPORTES = "findCCSSeImportes";
	public static final String QUERY_FIND_FECHA_MAXIMA_C_C_S_S_FACTURA = "findFechaMaximaCCSSFactura";
	public static final String QUERY_FIND_RENDICIONES_POR_C_C_S_S = "findRendicionesPorCCSS";
	public static final String QUERY_FIND_FACTURAS_CONTADO_POR_NRO_RENDICION = "findFacturasContadoPorNroRendicion";
	public static final String QUERY_FIND_PERCEPCIONES_I_V_A_P_I_V_A_CERO = "findPercepcionesIVAPIVACero";
	public static final String QUERY_FIND_PERCEPCIONES_I_V_A_P_I_V_A_DISTINTO_CERO = "findPercepcionesIVAPIVADistintoCero";
	public static final String QUERY_FIND_PERCEPCIONES_I_I_B_B = "findPercepcionesIIBB";
	public static final String QUERY_FIND_MOVIMIENTO_STOCK = "findMovimientoStock";
	public static final String QUERY_FIND_STOCK_COMBUSTIBLE = "findStockCombustible";
	public static final String QUERY_FIND_FACTURAS_PENDIENTES_DE_RENDICION = "findFacturasPendientesDeRendicion";
	public static final String QUERY_FIND_TOTAL_FACTURADO_COMPLETO = "findTotalFacturadoCompleto";
	public static final String QUERY_FIND_TOTAL_FACTURADO_DE_MFACTURA = "findTotalFacturadoDeMfactura";
	public static final String QUERY_FIND_TOTAL_FACTURADO_DE_M_CTA_CTE = "findTotalFacturadoDeMCtaCte";


	public static MfacturasVDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static MfacturasVDAO getInstance () {
		if (null == instance) instance = new com.refinor.extranet.data.dao.MfacturasVDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.refinor.extranet.data.MfacturasV.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.refinor.extranet.data.MfacturasV
	 */
	public com.refinor.extranet.data.MfacturasV cast (Object object) {
		return (com.refinor.extranet.data.MfacturasV) object;
	}

	public com.refinor.extranet.data.MfacturasV get(com.refinor.extranet.data.MfacturasVId key)
	{
		return (com.refinor.extranet.data.MfacturasV) get(getReferenceClass(), key);
	}

	public com.refinor.extranet.data.MfacturasV get(com.refinor.extranet.data.MfacturasVId key, Session s)
	{
		return (com.refinor.extranet.data.MfacturasV) get(getReferenceClass(), key, s);
	}

	public com.refinor.extranet.data.MfacturasV load(com.refinor.extranet.data.MfacturasVId key)
	{
		return (com.refinor.extranet.data.MfacturasV) load(getReferenceClass(), key);
	}

	public com.refinor.extranet.data.MfacturasV load(com.refinor.extranet.data.MfacturasVId key, Session s)
	{
		return (com.refinor.extranet.data.MfacturasV) load(getReferenceClass(), key, s);
	}

	public com.refinor.extranet.data.MfacturasV loadInitialize(com.refinor.extranet.data.MfacturasVId key, Session s) 
	{ 
		com.refinor.extranet.data.MfacturasV obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.refinor.extranet.data.MfacturasV> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.refinor.extranet.data.MfacturasV> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.refinor.extranet.data.MfacturasV> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param mfacturasV a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public com.refinor.extranet.data.MfacturasVId save(com.refinor.extranet.data.MfacturasV mfacturasV)
	{
		return (com.refinor.extranet.data.MfacturasVId) super.save(mfacturasV);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param mfacturasV a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public com.refinor.extranet.data.MfacturasVId save(com.refinor.extranet.data.MfacturasV mfacturasV, Session s)
	{
		return (com.refinor.extranet.data.MfacturasVId) save((Object) mfacturasV, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param mfacturasV a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.refinor.extranet.data.MfacturasV mfacturasV)
	{
		saveOrUpdate((Object) mfacturasV);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param mfacturasV a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.refinor.extranet.data.MfacturasV mfacturasV, Session s)
	{
		saveOrUpdate((Object) mfacturasV, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param mfacturasV a transient instance containing updated state
	 */
	public void update(com.refinor.extranet.data.MfacturasV mfacturasV) 
	{
		update((Object) mfacturasV);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param mfacturasV a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.refinor.extranet.data.MfacturasV mfacturasV, Session s)
	{
		update((Object) mfacturasV, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(com.refinor.extranet.data.MfacturasVId id)
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
	public void delete(com.refinor.extranet.data.MfacturasVId id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param mfacturasV the instance to be removed
	 */
	public void delete(com.refinor.extranet.data.MfacturasV mfacturasV)
	{
		delete((Object) mfacturasV);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param mfacturasV the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.refinor.extranet.data.MfacturasV mfacturasV, Session s)
	{
		delete((Object) mfacturasV, s);
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
	public void refresh (com.refinor.extranet.data.MfacturasV mfacturasV, Session s)
	{
		refresh((Object) mfacturasV, s);
	}


}