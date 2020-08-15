package com.asecor.extranet.faces.polizas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.asecor.extranet.data.Adherentes;
import com.asecor.extranet.data.Cobranzas;
import com.asecor.extranet.data.Polizas;
import com.asecor.extranet.data.Titulares;
import com.asecor.extranet.data.dao.AdherentesDAO;
import com.asecor.extranet.data.dao.CobranzasDAO;
import com.asecor.extranet.data.dao.PolizasDAO;
import com.asecor.extranet.data.dao.TitularesDAO;
import com.asecor.extranet.data.dao._RootDAO;
import com.asecor.extranet.faces.base.AbstBackingBean;
import com.asecor.extranet.util.Const;
import com.asecor.extranet.util.exception.DataAccessErrorException;
import com.asecor.util.io.FileUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ConsultaPolizas  extends AbstBackingBean{
	private Session session;
	private CobranzasDAO cobranzasDAO=null;
	private AdherentesDAO adherentesDAO=null;
	private Polizas poliza;
	private Titulares titular;
	List<Cobranzas> cobranzas;
	List<Adherentes> adherentes;
	private Float premioFamiliar;
	private String plan;
	private boolean cancelar;
	private List<Adherentes> personas = new ArrayList<Adherentes>();
	@Override
	public Boolean getPuedeIngresar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void preRenderView() {
	      HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true );
	      //tune session params, eg. session.setMaxInactiveInterval(..);

	      //perform other pre-render stuff, like setting user context...
	}
	public ConsultaPolizas() {
		super();
		FileUtil fileUtil= new FileUtil();
   		Properties props= fileUtil.getPropertiesFile();
   		premioFamiliar=Float.valueOf(props.getProperty("premio_familiar"));
   	 
   		File hibernateCfgXml= new File(props.getProperty(Const.ARCHIVO_CONF_HIBERNATE));   		
 		_RootDAO.initialize(hibernateCfgXml);
 		
 		session=  new PolizasDAO().getSession(); 
 		cobranzasDAO= new CobranzasDAO(session); 
 		adherentesDAO=new AdherentesDAO(session);
 		TitularesDAO titularesDAO=new TitularesDAO(session);
	
 		  
		this.poliza=new Polizas();
		 //puedeIngresar = true; 
		 
		FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		response.setHeader("Cache-Control", "no-cache"); 
		
		
	}
	
	
	public String detallePolizas(Polizas poliza) {
		
		this.poliza=poliza;
		if(this.poliza.getPremio().equals(premioFamiliar)) 
			poliza.setPlan("FAMILIAR");
		else
			poliza.setPlan("INDIVIDUAL");
		if(poliza.getCodEstadoPoliza()=="X")
		this.cancelar=true;
		 try {
			
			 cobranzas = cobranzasDAO.findCobranzasByPoliza(poliza);
			adherentes= adherentesDAO.findAdherentesByPoliza(poliza);
		} catch (DataAccessErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "detallePoliza";
	}
	
public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException{
		
		FacesContext facesContext = FacesContext. getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		if(null==this.adherentes){
		this.adherentes=new ArrayList<Adherentes>();
		this.adherentes.add(new Adherentes(0,0,0," "," ",null," "));
		}
		actionEvent.getComponent().getAttributes().get("adherentes");
		Map params = externalContext.getRequestParameterMap();
		
		Map<String,Object> parametros= new HashMap<String,Object>();
	String nombres=params.get("nombre").toString().concat(" ,").concat(params.get("apellido").toString());
		parametros.put("lista", null!=this.adherentes?"1":"0");
		parametros.put("codSolicitud",params.get("codSolicitud").toString());
		parametros.put("nroCertificado",params.get("nroCertificado"));
		parametros.put("nombres",nombres);
		parametros.put("dni",params.get("dni"));
		parametros.put("fechaNac",params.get("fechNac").toString().subSequence(0, 10));
		parametros.put("plan",params.get("plan"));
		parametros.put("domicilio",params.get("domicilio"));
		parametros.put("vigencia",params.get("vigencia"));
		parametros.put("plan",params.get("plan"));
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reporteAsecor.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(this.adherentes));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=ReporteSepelio_".concat(params.get("nroCertificado").toString()).concat(".pdf"));
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public Polizas getPoliza() {
		return poliza;
	}

	public void setPoliza(Polizas poliza) {
		this.poliza = poliza;
	}

	public List<Cobranzas> getCobranzas() {
		return cobranzas;
	}

	public void setCobranzas(List<Cobranzas> cobranzas) {
		this.cobranzas = cobranzas;
	}

	public List<Adherentes> getAdherentes() {
		return adherentes;
	}

	public void setAdherentes(List<Adherentes> adherentes) {
		this.adherentes = adherentes;
	}

	

	public Titulares getTitular() {
		return titular;
	}

	public void setTitular(Titulares titular) {
		this.titular = titular;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public boolean isCancelar() {
		return cancelar;
	}

	public void setCancelar(boolean cancelar) {
		this.cancelar = cancelar;
	}
	
}
