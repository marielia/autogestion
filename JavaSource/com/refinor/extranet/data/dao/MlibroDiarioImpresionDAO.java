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

import com.refinor.extranet.data.MlibroDiarioImpresion;
import com.refinor.extranet.data.base.BaseMlibroDiarioImpresionDAO;
import com.refinor.extranet.to.AsientoContableTO;
import com.refinor.extranet.to.MLibroDiarioImpresionTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;


public class MlibroDiarioImpresionDAO extends BaseMlibroDiarioImpresionDAO implements com.refinor.extranet.data.dao.iface.MlibroDiarioImpresionDAO {

	public MlibroDiarioImpresionDAO () {}
	
	public MlibroDiarioImpresionDAO (Session session) {
		super(session);
	}

	public static String FIND_ULTIMA_IMPRESION="finUltimaImpresion"; 
	
	public MLibroDiarioImpresionTO getUltimaImpresion(){
		MLibroDiarioImpresionTO mlibroDiarioImpresionTO=null;
	try{				
				
				List lst= new ArrayList();
				Map<String, Object> params = new HashMap<String, Object>();						 
				Query query = this.getNamedQuery(FIND_ULTIMA_IMPRESION, params, session);							
				lst = query.list();										  
				if(lst.size()>0){
						Iterator it = lst.iterator();
						lst=null;
						Object[] obj= null;						
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						while(it.hasNext()){
					        obj = (Object[]) it.next(); 
					        mlibroDiarioImpresionTO = new MLibroDiarioImpresionTO();					       
					        mlibroDiarioImpresionTO.setAnioPerdiodoAnterior(obj[0].toString());
					        mlibroDiarioImpresionTO.setMesPeriodoAnterior(obj[1].toString());
					        mlibroDiarioImpresionTO.setNroPaginaInicioAnterior(new Long(obj[2].toString()));
					        mlibroDiarioImpresionTO.setNroPaginaFinAnterior(new Long(obj[3].toString()));					        
					        mlibroDiarioImpresionTO.setPeriodoActualConsulta(sdf.format(obj[4]));
					        System.out.println("nroPaginaInicio "+mlibroDiarioImpresionTO.getPeriodoActualConsulta());
					        mlibroDiarioImpresionTO.setNroPaginaInicioActual(new Long(obj[5].toString()));
						}
				}		
				
	}catch(Exception ex){
		ex.printStackTrace();
	}
	return	mlibroDiarioImpresionTO;
	}
}