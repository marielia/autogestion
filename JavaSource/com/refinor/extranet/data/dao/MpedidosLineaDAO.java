package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMpedidosLineaDAO;


public class MpedidosLineaDAO extends BaseMpedidosLineaDAO implements com.refinor.extranet.data.dao.iface.MpedidosLineaDAO {

	public MpedidosLineaDAO () {}
	
	public MpedidosLineaDAO (Session session) {
		super(session);
	}


}