package com.refinor.extranet.data.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.refinor.extranet.data.base.BaseMibimpuestoDAO;
import com.refinor.extranet.to.MImpuestoTO;
import com.refinor.extranet.to.PercepcionesIIBBTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;


public class MibimpuestoDAO extends BaseMibimpuestoDAO implements com.refinor.extranet.data.dao.iface.MibimpuestoDAO {

	public MibimpuestoDAO () {}
	
	public MibimpuestoDAO (Session session) {
		super(session);
	}
	
	
	public List getImpuestoPorProvincia(Integer codProvincia) throws DataAccessErrorException{
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_PROVINCIA, codProvincia);			
			Query personasQry = this.getNamedQuery("findImpuestoPorProvincia", params, session);
			List personas= personasQry.list();
			
			Iterator it = personas.iterator();
			personas= new ArrayList();
			MImpuestoTO impuestoTO= new MImpuestoTO();
			Object[] objChofer= null;
			
			while(it.hasNext()){
		        objChofer = (Object[]) it.next(); 
		        impuestoTO = new MImpuestoTO();		       
		        impuestoTO.setInscripcionIIBB(new Integer(objChofer[0].toString()));
		        impuestoTO.setInscripcionIVA(new Integer(objChofer[1].toString())); 
		        impuestoTO.setProvincia(new Integer(objChofer[2].toString()));
		        impuestoTO.setActividad(new Integer(objChofer[3].toString()));
		        impuestoTO.setTipoProducto(new Integer(objChofer[4].toString()));
		        impuestoTO.setAlicuota(new BigDecimal(objChofer[5].toString())); 
		        personas.add(impuestoTO);
		        
			}
			
			if(personas.size() == 0)
				throw new PersonaNoExisteException();
			else
				return personas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	public List getImpuestoPorProvinciaIncripIIBB(Integer codProvincia, Integer codIncripIIBB) throws DataAccessErrorException{
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.PARAM_COD_PROVINCIA, codProvincia);			
			params.put(Const.COD_INCRIPCION_IIBB, codIncripIIBB);
			Query personasQry = this.getNamedQuery("findImpuestoPorProvinciaAndIncripIIBB", params, session);
			List personas= personasQry.list();
			
			Iterator it = personas.iterator();
			personas= new ArrayList();
			MImpuestoTO impuestoTO= new MImpuestoTO();
			Object[] objChofer= null;
			
			while(it.hasNext()){
		        objChofer = (Object[]) it.next(); 
		        impuestoTO = new MImpuestoTO();		       
		        impuestoTO.setInscripcionIIBB(new Integer(objChofer[0].toString()));
		        impuestoTO.setInscripcionIVA(new Integer(objChofer[1].toString())); 
		        impuestoTO.setProvincia(new Integer(objChofer[2].toString()));
		        impuestoTO.setActividad(new Integer(objChofer[3].toString()));
		        impuestoTO.setTipoProducto(new Integer(objChofer[4].toString()));
		        impuestoTO.setAlicuota(new BigDecimal(objChofer[5].toString())); 
		        personas.add(impuestoTO);
		        
			}
				return personas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}



}