package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseVsaldoPorComprobanteClienteDAO;


public class VsaldoPorComprobanteClienteDAO extends BaseVsaldoPorComprobanteClienteDAO implements com.refinor.extranet.data.dao.iface.VsaldoPorComprobanteClienteDAO {

	public VsaldoPorComprobanteClienteDAO () {}
	
	public VsaldoPorComprobanteClienteDAO (Session session) {
		super(session);
	}


}