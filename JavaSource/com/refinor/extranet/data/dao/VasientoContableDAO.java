package com.refinor.extranet.data.dao;

import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseVasientoContableDAO;


public class VasientoContableDAO extends BaseVasientoContableDAO implements com.refinor.extranet.data.dao.iface.VasientoContableDAO {

	public VasientoContableDAO () {}
	
	public VasientoContableDAO (Session session) {
		super(session);
	}


}