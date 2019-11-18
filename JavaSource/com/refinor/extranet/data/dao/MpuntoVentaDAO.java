package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMpuntoVentaDAO;


public class MpuntoVentaDAO extends BaseMpuntoVentaDAO implements com.refinor.extranet.data.dao.iface.MpuntoVentaDAO {

	public MpuntoVentaDAO () {}
	
	public MpuntoVentaDAO (Session session) {
		super(session);
	}


}