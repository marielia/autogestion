package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseAlmacenDAO;


public class AlmacenDAO extends BaseAlmacenDAO implements com.refinor.extranet.data.dao.iface.AlmacenDAO {

	public AlmacenDAO () {}
	
	public AlmacenDAO (Session session) {
		super(session);
	}


}