package com.refinor.extranet.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.refinor.extranet.data.MautorizacionCodigos;
import com.refinor.extranet.data.Mchofer;
import com.refinor.extranet.data.base.BaseMautorizacionCodigosDAO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoSePudeEnviarMailException;


public class MautorizacionCodigosDAO extends BaseMautorizacionCodigosDAO implements com.refinor.extranet.data.dao.iface.MautorizacionCodigosDAO {

	public MautorizacionCodigosDAO () {}
	
	public MautorizacionCodigosDAO (Session session) {
		super(session);
	}
	/**
	 * Metodo:getCodigoAutorizacion();
	 * Funcion: Ejecuta el Store Procedure para generar el codigo de autorizacion
	 * @return
	 * @throws DataAccessErrorException
	 */
	public MautorizacionCodigos getCodigoAutorizacion() throws DataAccessErrorException{
		Transaction tx= null;
		try {
			List lstChoferesPorFiltro= new ArrayList();		
			tx= session.beginTransaction();			
			Query query = session.getNamedQuery("get_CodigoAutorizacion_SP");
			lstChoferesPorFiltro= query.list();					
			tx.commit();
			return (MautorizacionCodigos)lstChoferesPorFiltro.get(0);	
		
		} catch(Exception excep) {
			excep.printStackTrace();
			tx.rollback();
			throw new DataAccessErrorException();
		}	
	}


}