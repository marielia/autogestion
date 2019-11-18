package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMlistasDAO;


public class MlistasDAO extends BaseMlistasDAO implements com.refinor.extranet.data.dao.iface.MlistasDAO {

	public MlistasDAO () {}
	
	public MlistasDAO (Session session) {
		super(session);
	}


}