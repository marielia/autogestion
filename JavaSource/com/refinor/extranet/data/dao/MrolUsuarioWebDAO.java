package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMrolUsuarioWebDAO;


public class MrolUsuarioWebDAO extends BaseMrolUsuarioWebDAO implements com.refinor.extranet.data.dao.iface.MrolUsuarioWebDAO {

	public MrolUsuarioWebDAO () {}
	
	public MrolUsuarioWebDAO (Session session) {
		super(session);
	}


}