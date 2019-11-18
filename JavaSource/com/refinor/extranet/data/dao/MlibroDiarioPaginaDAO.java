package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMlibroDiarioPaginaDAO;


public class MlibroDiarioPaginaDAO extends BaseMlibroDiarioPaginaDAO implements com.refinor.extranet.data.dao.iface.MlibroDiarioPaginaDAO {

	public MlibroDiarioPaginaDAO () {}
	
	public MlibroDiarioPaginaDAO (Session session) {
		super(session);
	}


}