package com.refinor.extranet.data.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;

public abstract class _RootDAO extends com.refinor.extranet.data.base._BaseRootDAO {

	
	
	public _RootDAO () {}
	
	public _RootDAO (Session session) {
		setSession(session);
	}

	public static void initialize (java.io.File configFile) throws HibernateException {
		if (null == configFile && sessionFactoryMap.size() > 0) return;
		else if (null != sessionFactoryMap.get(configFile.getAbsolutePath())) return;
		else {
			Configuration cfg = new Configuration();
			if (null == configFile)
				cfg.configure();
			else
				cfg.configure(configFile);
			setSessionFactory(configFile.getAbsolutePath(), cfg.buildSessionFactory());
		}
	}
/*
	If you are using lazy loading, uncomment this
	Somewhere, you should call RootDAO.closeCurrentThreadSessions();
	public void closeSession (Session session) {
		// do nothing here because the session will be closed later
	}
*/

/*
	If you are pulling the SessionFactory from a JNDI tree, uncomment this
	protected SessionFactory getSessionFactory(String configFile) {
		// If you have a single session factory, ignore the configFile parameter
		// Otherwise, you can set a meta attribute under the class node called "config-file" which
		// will be passed in here so you can tell what session factory an individual mapping file
		// belongs to
		return (SessionFactory) new InitialContext().lookup("java:/{SessionFactoryName}");
	}
*/
}