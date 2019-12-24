package com.refinor.extranet.data.dao;

 
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List; 

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.mapping.Map;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.refinor.extranet.data.MFacturaManual_tmp;
 
import com.refinor.extranet.data.base.BaseMFacturaManual_tmpDAO;
import com.refinor.extranet.to.AsientoContableTO;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;
 


public class MFacturaManual_tmpDAO extends BaseMFacturaManual_tmpDAO implements com.refinor.extranet.data.dao.iface.MFacturaManual_tmpDAO  {

	public MFacturaManual_tmpDAO () {}
	
	public MFacturaManual_tmpDAO (Session session) {
		super(session);
	}
	
	
	public List<MFacturaManual_tmp> getMFacturaManual_tmp() throws PersonaNoExisteException, DataAccessErrorException {
		try {		
			Query  query = this.getNamedQuery("findMFacturaManual_tmp", session);
			List lst= query.list();
			return lst;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	
	public List<MFacturaManual_tmp> getMFacturaManuales_tmp() throws PersonaNoExisteException, DataAccessErrorException {
		try {		
			Query  query = this.getNamedQuery("findMFacturaManuales_tmp", session); 
			List lst = query.list();
			List<MFacturaManual_tmp> lstMFacturaManual_tmp = new ArrayList();
		
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(lst.size()>0){
				Iterator it = lst.iterator();
				lst= new ArrayList();
				MFacturaManual_tmp mFacturaManual_tmp= new MFacturaManual_tmp();
				Object[] obj = null;		
				
				while(it.hasNext()){
					obj = (Object[]) it.next(); 
					mFacturaManual_tmp= new MFacturaManual_tmp(); 
					mFacturaManual_tmp.setId(new Integer(obj[0].toString()));
					mFacturaManual_tmp.setFechaStr(sdf.format(obj[1]));
					 mFacturaManual_tmp.setNroSucursal(new Integer(obj[2].toString()));
					 mFacturaManual_tmp.setNroFactura(new Integer(obj[3].toString()));
					 mFacturaManual_tmp.setClienteAlfa( (obj[4].toString()));
					 mFacturaManual_tmp.setCodArticulo(new Integer(obj[5].toString()));
					 mFacturaManual_tmp.setBultosBig(new BigDecimal(obj[6].toString()));
					 mFacturaManual_tmp.setDni(new Integer(obj[7].toString()));
					 mFacturaManual_tmp.setDominio( (obj[8].toString()));
					 mFacturaManual_tmp.setNroAutorizacion( (obj[9].toString()));
					 mFacturaManual_tmp.setEstado( (obj[10].toString()));
					 mFacturaManual_tmp.setKilometros(new BigDecimal(obj[11].toString()));
					 mFacturaManual_tmp.setErrores( (obj[12].toString()));
					 lstMFacturaManual_tmp.add(mFacturaManual_tmp);
				}
			}
			
			 
			
			return lstMFacturaManual_tmp;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}
	
	 
	/**
	 * procesaFacturaManual();
	 * Funcion: Ejecuta el Store Procedure para generar el codigo de autorizacion
	 * @return
	 * @throws DataAccessErrorException
	 */
	public String procesaFacturaManual( ) throws DataAccessErrorException {
		try{
			  Transaction tx= null;
			try {
				
				  tx= session.beginTransaction();  
				  Query query = session.getNamedQuery("spProcesaFacturaManual_tmp"); 
				  List<Object[]> list = (List<Object[]>) query.list();
				  System.out.println( " rpta"  + list.get(0));
				// lstChoferesPorFiltro = query.list();
				 // System.out.println( " result " +result);
				 // lstChoferesPorFiltro = query.list();
				  tx.commit();							
				System.out.println( " se guardo el registro " );
				//pantalla=2;
			} catch(Exception excep) {
				excep.printStackTrace();
				  tx.rollback();
				throw new DataAccessErrorException();
			}
			
			
		        
			return "OK";
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
	}

	 
 
	
}