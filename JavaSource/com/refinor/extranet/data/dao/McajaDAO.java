package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMcajaDAO;


public class McajaDAO extends BaseMcajaDAO implements com.refinor.extranet.data.dao.iface.McajaDAO {

	public McajaDAO () {}
	
	public McajaDAO (Session session) {
		super(session);
	}


}