package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMpedidosDtCargaDAO;


public class MpedidosDtCargaDAO extends BaseMpedidosDtCargaDAO implements com.refinor.extranet.data.dao.iface.MpedidosDtCargaDAO {

	public MpedidosDtCargaDAO () {}
	
	public MpedidosDtCargaDAO (Session session) {
		super(session);
	}


}