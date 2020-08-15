package com.asecor.extranet.data.dao.iface;

public interface PolizasDAO {
	public com.asecor.extranet.data.Polizas get(int key);

	public com.asecor.extranet.data.Polizas load(int key);

	public java.util.List<com.asecor.extranet.data.Polizas> findAll ();	

}
