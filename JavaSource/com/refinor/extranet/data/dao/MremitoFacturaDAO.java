package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMremitoFacturaDAO;


public class MremitoFacturaDAO extends BaseMremitoFacturaDAO implements com.refinor.extranet.data.dao.iface.MremitoFacturaDAO {

	public MremitoFacturaDAO () {}
	
	public MremitoFacturaDAO (Session session) {
		super(session);
	}


}