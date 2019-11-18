package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMremitoFacturaAnuladaDAO;


public class MremitoFacturaAnuladaDAO extends BaseMremitoFacturaAnuladaDAO implements com.refinor.extranet.data.dao.iface.MremitoFacturaAnuladaDAO {

	public MremitoFacturaAnuladaDAO () {}
	
	public MremitoFacturaAnuladaDAO (Session session) {
		super(session);
	}


}