/**
 * 
 */
package com.refinor.extranet.faces.seguridad;

import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.seguridad.PerfilAdmin;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.PersonaNoExisteException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 * @author  mliz
 */
public class PerfilBean extends AbstBackingBean {
	private Integer perfil;
	private Session session;
	private List perfiles;
	
	public PerfilBean() {
		/*session= (new PersonaDAO()).getSession();
		PerfilAdmin perfAdmin= new PerfilAdmin(session);
		Usuario usr= (Usuario)this.getSessionValue(Const.USUARIO);
		try {
			perfiles= perfAdmin.getPerfilesOptions((new DataUtil()).getTipoDocumentoId(usr.getTipoDocumento(), session), 
													usr.getNumeroDocumento(), (List)this.getSessionValue(Const.PERSONAS));
			
			FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
			HttpServletResponse response =
				(HttpServletResponse)context.getExternalContext().getResponse();
			response.setHeader("Cache-Control", "no-cache");

		} catch(PersonaNoExisteException ex) {
			AddErrorMessage(ex.getMessage());
		} catch(Exception ex) {
			AddErrorMessage((new DataAccessErrorException()).getMessage());
		}
		this.setSessionValue(Const.PERFILES, perfiles);*/
	}
	
	//public String aceptar() throws DataAccessErrorException {
		/*DataUtil dataUtil= new DataUtil();
		PersonaDAO personaDAO= new PersonaDAO(session);
		PerfilAdmin perfAdmin= new PerfilAdmin(session);
		Usuario usuario= (Usuario)this.getSessionValue(Const.USUARIO);
		this.setSessionValue(Const.PERFIL, perfil);
		try {
			char tipoPersona= dataUtil.getTipoPersona(((Integer)this.getSessionValue(Const.PERFIL)).intValue());
			List personas= personaDAO.getPorDocumento(dataUtil.getTipoDocumentoId(usuario.getTipoDocumento(), session), usuario.getNumeroDocumento(), tipoPersona);
			
			//AFA NO TIENE ORGANIZADORES - se omite esta funcionalidad - Silvana Nieto 26072007
			if(tipoPersona == Const.TIPO_PERSONA_AGENTE){
				personas= getPersonasPerfilAgente(personas, (Integer)this.getSessionValue(Const.PERFIL));
			}
			
			this.setSessionValue(Const.PERSONAS, personas);
			this.setSessionValue(Const.FUNCIONES, perfAdmin.getFunciones(usuario.getId(), perfil, Const.TIPO_USUARIO_WEB));
						
			 Agregado:Al entrar por agente que pase por la busqueda de polizas -  05032007 - Silvana Nieto				
			String irAPagina = null;
			if(tipoPersona == Const.TIPO_PERSONA_AGENTE){
				//agente
				irAPagina =Const.SUCCESS_CONSULTA_POLIZAS;
			}else if(tipoPersona == Const.TIPO_PERSONA_CLIENTE){
				//cliente
				irAPagina = Const.SUCCESS;
			}
			
			System.out.println("irAPagina -->"+ irAPagina);
			return irAPagina;
			 End Agregado 
			
			
		} catch(Exception ex) {
			AddErrorMessage((new DataAccessErrorException()).getMessage());
			ex.printStackTrace();
			return Const.FAIL;
		}*/
	//}
	
	/**
	 * Depura una lista de Personas correspondientes a Tipo de Persona "A" (Agente) de acuerdo
	 * al Perfil recibido como parámetro.
	 * 
	 * @param personas	Lista de Personas
	 * @param perfil	Perfil deseado
	 * @return	Lista de Personas correspondientes al Perfil.
	 */
	/*No soporta una persona varios agente - no se usa porque AFA no tiene organizadores solo PRODUCTORES*/
	/*private List getPersonasPerfilAgente(List personas, Integer perfil) {
		AgenteDAO agenteDAO= new AgenteDAO(session);
		List<Persona> personasPerfil= new ArrayList<Persona>();
		char tipoAgente= (perfil.equals(Const.TIPO_REGISTRO_AGENTE_ORGANIZADOR) ? Const.TIPO_AGENTE_ORGANIZADOR : Const.TIPO_AGENTE_PRODUCTOR);
		Persona persona= null;
		Iterator it= personas.iterator();
		while(it.hasNext()) {
			persona= (Persona)it.next();
			try {
				if(agenteDAO.getAgentePorPersona(persona.getNroPersona()).getTipoAgente() == tipoAgente)
					personasPerfil.add(persona);
			} catch(Exception ex) {
				System.out.println("Error al intentar recuperar el Tipo de Agente en getPersonasPerfilAgente");
				ex.printStackTrace();
			}
		}
		return personasPerfil;
	}*/

	
	/*public TipoDocumento getTipoDocumento() {
		try {
			TipoDocumentoDAO tipoDocDAO= new TipoDocumentoDAO(session);
			return tipoDocDAO.getPorCodigo(persona.getTipoDocumento());
		} catch(Exception ex) {
			this.AddErrorMessage(new DataAccessErrorException().getMessage());
			return new TipoDocumento();
		}
	}*/
	
	/**
	 * Recupera Tipo y Número de Documento concatenados, correspondiente al Usuario logueado.
	 * 
	 * @return	Tipo y Número de Documento Concatenados.
	 */
	/*public String getDocumento() {
		TipoDocumentoDAO tipoDocDAO= new TipoDocumentoDAO(session);
		try {
			return tipoDocDAO.getPorCodigo(((Usuario)this.getSessionValue(Const.USUARIO)).getTipoDocumento()).getSigla() +
					" - " + ((Usuario)this.getSessionValue(Const.USUARIO)).getNumeroDocumento();
		} catch(Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
	*/
	/**
	 * @return  the perfil
	 * @uml.property  name="perfil"
	 */
	public Integer getPerfil() {
		return perfil;
	}

	/**
	 * @param perfil  the perfil to set
	 * @uml.property  name="perfil"
	 */
	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	/**
	 * @return  the perfiles
	 * @uml.property  name="perfiles"
	 */
	public List getPerfiles() {
		return perfiles;
	}

	/**
	 * @param perfiles  the perfiles to set
	 * @uml.property  name="perfiles"
	 */
	public void setPerfiles(List perfiles) {
		this.perfiles = perfiles;
	}

	public Boolean getPuedeIngresar() {
		if(getSession().getAttribute(Const.USUARIO)!= null)
			return new Boolean(true);
		else
			return new Boolean(false);
	}
}
