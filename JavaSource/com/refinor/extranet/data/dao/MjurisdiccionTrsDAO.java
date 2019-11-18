package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMjurisdiccionTrsDAO;


public class MjurisdiccionTrsDAO extends BaseMjurisdiccionTrsDAO implements com.refinor.extranet.data.dao.iface.MjurisdiccionTrsDAO {

	public MjurisdiccionTrsDAO () {}
	
	public MjurisdiccionTrsDAO (Session session) {
		super(session);
	}


}