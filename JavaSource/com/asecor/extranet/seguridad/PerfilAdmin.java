package com.asecor.extranet.seguridad;

import com.asecor.util.io.FileUtil;
import com.asecor.extranet.data.TitularWeb;
import com.asecor.extranet.data.dao.UsuarioWebDAO;
import com.asecor.extranet.to.DateTO;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.DataUtil;  

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.Collections;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import javax.faces.model.SelectItem;

public class PerfilAdmin {

	public PerfilAdmin(Session session) {
		this.session= session;
	}
	private Session session;
	 
	
	public java.sql.Date getCurrencyDate(){ 
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		return     date;
	}
	
	public   java.sql.Date getCurrencyDateTime(){ 
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		return     date;
	}
	
	public void registrarLogonFallido(TitularWeb usr) {
		Transaction tx= session.beginTransaction();
		UsuarioWebDAO usrDAO= new UsuarioWebDAO(session);
   		FileUtil fileUtil= new FileUtil();   		
   		Properties props= fileUtil.getPropertiesFile();
   		DataUtil dataUtil= new DataUtil();
   	    DateTO dateTO =	dataUtil.getFechaActual();   	 
   		
   		if(((new Date()).getTime() - usr.getLastFailedLogon().getTime()) / 1000 
   					< new Integer(props.getProperty(Const.SEGUNDOS_LOGON_FALLIDO)).intValue()) {
   		    usr.setLastFailedLogon(new Date(dateTO.getAnio(),dateTO.getMes(),dateTO.getDia(),dateTO.getHora(),dateTO.getMinuto(),dateTO.getSegundo() ) );
        	usr.setFailedLogons(usr.getFailedLogons() + 1);  
			
			if(usr.getFailedLogons() >= Const.INTENTO_FALLIDOS) {
				usr.setActive(false); 
			}
   		} else { 
   			
   		  usr.setFailedLogons(1); 
          usr.setLastFailedLogon(new Date(dateTO.getAnio(),dateTO.getMes(),dateTO.getDia(),dateTO.getHora(),dateTO.getMinuto(),dateTO.getSegundo() ) ); 
  			
   		}
   		
   		usrDAO.saveOrUpdate(usr, session);
   		tx.commit();
	}
	
	/**
	 * "Resetea" los intentos de logon fallidos a 0 (cero), si es necesario
	 * 
	 * @param usr	Usuario
	 */
	public void limpiarIntentosFallidos(TitularWeb usr) {
		
			Transaction tx= session.beginTransaction();
			UsuarioWebDAO usrDAO= new UsuarioWebDAO(session);
			DataUtil dataUtil= new DataUtil();
	   	    DateTO dateTO =	dataUtil.getFechaActual(); 
			usr.setLastLogin(new Date(dateTO.getAnio(),dateTO.getMes(),dateTO.getDia(),dateTO.getHora(),dateTO.getMinuto(),dateTO.getSegundo() ) );
			
			if(usr.getFailedLogons() > 0) {
				usr.setFailedLogons(0);
				//usr.setUltimoIntentoFallido(Const.FECHA_RESET_INTENTO_FALLIDO);
			}
			
	   		usrDAO.save(usr, session);
	   		tx.commit();
		
	}
	
}
