package com.refinor.extranet.data.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.refinor.extranet.data.base.BaseMexclusionTipoDAO;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;


public class MexclusionTipoDAO extends BaseMexclusionTipoDAO implements com.refinor.extranet.data.dao.iface.MexclusionTipoDAO {

	public MexclusionTipoDAO () {}
	
	public MexclusionTipoDAO (Session session) {
		super(session);
	}
	
	public static String FIND_TIPOS_EXCLUSION = "findTiposExclusion";
	public List getTiposExclusiones() throws PersonaNoExisteException, DataAccessErrorException {
		try {				
			Query personasQry = this.getNamedQuery(FIND_TIPOS_EXCLUSION, session);
			List lstData= personasQry.list();
			return lstData;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}


}