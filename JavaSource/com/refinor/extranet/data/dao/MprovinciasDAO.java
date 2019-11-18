package com.refinor.extranet.data.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMprovinciasDAO;
import com.refinor.extranet.to.MImpuestoTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;


public class MprovinciasDAO extends BaseMprovinciasDAO implements com.refinor.extranet.data.dao.iface.MprovinciasDAO {

	public MprovinciasDAO () {}
	
	public MprovinciasDAO (Session session) {
		super(session);
	}
		
	public List getJuridcciones() throws DataAccessErrorException{
		try {
			Map<String, Object> params = new HashMap<String, Object>();					
			Query personasQry = this.getNamedQuery("findJuriddciones", session);
			List personas= personasQry.list();	
			
			if(personas.size() == 0)
				throw new PersonaNoExisteException();
			else
				return personas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}


}