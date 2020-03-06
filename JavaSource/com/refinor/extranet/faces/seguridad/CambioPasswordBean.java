/*
 * Created on oct 02, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.refinor.extranet.faces.seguridad;


import com.itsolver.util.seguridad.PasswordService;

import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.dao.MusuarioWebDAO;
import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.to.ArchivoTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;

import java.io.File;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author  mliz  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class CambioPasswordBean extends AbstBackingBean {



	/**
	 * @author mliz
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	
		private java.lang.String email;
		private java.lang.String pwdNueva;
		private java.lang.String pwdRptNueva;
		private java.lang.String pwdAnterior;
		public java.lang.String password;
		public MusuarioWeb usuario;
		public Boolean mostrarFinal;
		public Boolean mostrarPantalla;
		public Boolean primerLogon;
		public String botonLabel;
		private String mensaje;
		private Session session;
		private String mensajeExi;
		private Messages mensajeria;
		private String codClienteAlfa;
		
		/**
		 * @return the mensajeria
		 */
		public Messages getMensajeria() {
			return mensajeria;
		}

		/**
		 * @param mensajeria the mensajeria to set
		 */
		public void setMensajeria(Messages mensajeria) {
			this.mensajeria = mensajeria;
		}

		public CambioPasswordBean() {
			try	{				
				session= (new MusuarioWebDAO()).getSession();
				password =getClave();
				codClienteAlfa= getUsuario();
				setMostrarFinal(new Boolean(false));
				setPrimerLogon(new Boolean(false));
				setMostrarPantalla(new Boolean(true));
				mensajeria = new Messages();
			} catch(Exception ex) {
					AddErrorMessage((new DataAccessErrorException()).getMessage());
					ex.printStackTrace();
			}
		}	

		public String getClave() throws Exception  {
			HttpSession sesion= this.getSession();
			try {
				//saco pwd de variables de sesion
				return ((MusuarioWeb)(sesion.getAttribute(Const.USUARIO))).getClave();
			} catch(Exception ex) {
				System.out.println("Error en procedimiento de Base de Datos al chequear el usuario ");
				throw ex;
				
			}
		}

		public void guardar(ActionEvent event) {
			PasswordService ps= PasswordService.getInstance();
			try 
			{
				//Validacion de datos requeridos
				if(!pwdAnterior.trim().equals("") && !pwdNueva.trim().equals("") && !pwdRptNueva.trim().equals("")){
					if (password.equals(ps.encrypt(pwdAnterior)))
						if (pwdNueva.equals(pwdRptNueva))
						{
							cambiarClave(((MusuarioWeb)(this.getSessionValue(Const.USUARIO))).getId());
							setMostrarFinal(new Boolean(true));
							setMostrarPantalla(new Boolean(false)); 
							this.setMensajeExi(mensajeria.getMessage().getString("cambio_contrasenia_exi_msg"));
							
							if(primerLogon.booleanValue()==new Boolean(true))
								setBotonLabel(Const.INGRESAR_LABEL);
							else
								setBotonLabel(Const.INICIO_LABEL);
						}
						else
							AddErrorMessage(mensajeria.getMessage().getString("no_coincide_pws_nueva_msg"));
					else
						AddErrorMessage(mensajeria.getMessage().getString("contrasenia_distinta_msg"));
				}
				else{
					AddErrorMessage(mensajeria.getMessage().getString("ingrese_datos_contrasenia_msg"));
				}
				
			} catch(Exception ex) {
				AddErrorMessage((new DataAccessErrorException()).getMessage());
				ex.printStackTrace();
			} finally {
				session.close();
			}
			
		}
		
		public void cambiarClave(int id) {
			MusuarioWebDAO usrDAO= new MusuarioWebDAO(session);
			PasswordService ps= PasswordService.getInstance();
			try {
				Transaction tx= session.beginTransaction();
				MusuarioWeb usuario = usrDAO.load(id, session);
				usuario.setClave(ps.encrypt(pwdNueva));	
				if(usuario.getCambioClave() == Const.DEBE_CAMBIAR_CONTRASENIA) {
					primerLogon= new Boolean(true);
					usuario.setCambioClave(Const.NO_DEBE_CAMBIAR_CONTRASENIA);	
				}
				else
					primerLogon= new Boolean(false);
				usrDAO.update(usuario, session);
				tx.commit();
				this.setSessionValue(Const.USUARIO, usuario);
			} catch(Exception ex) {
				AddErrorMessage((new DataAccessErrorException()).getMessage());
				ex.printStackTrace();
			}
		}
		
		/**
		 * Recupera Tipo y Número de Documento concatenados, correspondiente al Usuario logueado.
		 * 
		 * @return	Tipo y Número de Documento Concatenados.
		 */
		public String getUsuario() {			
			try {
				return ((MusuarioWeb)this.getSessionValue(Const.USUARIO)).getCodClienteAlfa();
			} catch(Exception ex) {
				ex.printStackTrace();
				return "";
			}
		}
		
		/**
		 * @return  the email
		 * @uml.property  name="email"
		 */
		public java.lang.String getEmail() {
			return email;
		}
		
		/**
		 * @param email  the email to set
		 * @uml.property  name="email"
		 */
		public void setEmail(java.lang.String email) {
		}
		
		/**
		 * @return  the pwdAnterior
		 * @uml.property  name="pwdAnterior"
		 */
		public java.lang.String getPwdAnterior() {
			return pwdAnterior;
		}
		/**
		 * @param pwdAnterior  the pwdAnterior to set
		 * @uml.property  name="pwdAnterior"
		 */
		public void setPwdAnterior(java.lang.String pwdAnterior) {
			this.pwdAnterior = pwdAnterior;
		}
		/**
		 * @return  the pwdNueva
		 * @uml.property  name="pwdNueva"
		 */
		public java.lang.String getPwdNueva() {
			return pwdNueva;
		}
		/**
		 * @param pwdNueva  the pwdNueva to set
		 * @uml.property  name="pwdNueva"
		 */
		public void setPwdNueva(java.lang.String pwdNueva) {
			this.pwdNueva = pwdNueva;
		}
		/**
		 * @return  the pwdRptNueva
		 * @uml.property  name="pwdRptNueva"
		 */
		public java.lang.String getPwdRptNueva() {
			return pwdRptNueva;
		}
		/**
		 * @param pwdRptNueva  the pwdRptNueva to set
		 * @uml.property  name="pwdRptNueva"
		 */
		public void setPwdRptNueva(java.lang.String pwdRptNueva) {
			this.pwdRptNueva = pwdRptNueva;
		}
		/**
		 * @return  the mensaje
		 * @uml.property  name="mensaje"
		 */
		public String getMensaje() {
			return mensaje;
		}
		/**
		 * @param mensaje  the mensaje to set
		 * @uml.property  name="mensaje"
		 */
		public void setMensaje(String mensaje) {
			//this.mensaje = mensaje;
		}
		/**
		 * @return  the password
		 * @uml.property  name="password"
		 */
		public java.lang.String getPassword() {
			return password;
		}
		/**
		 * @param password  the password to set
		 * @uml.property  name="password"
		 */
		public void setPassword(java.lang.String password) {
			this.password = password;
		}

		
			
		
		public String alogin() {	
			String goToPage=null;					
			if(primerLogon.booleanValue()==true){
				goToPage =  Const.ANCLA_INDEX;
				return goToPage;
			}else{	
				goToPage =  Const.ANCLA_LISTA_REPORTES_DISPONIBLES;
				return goToPage;
			}			
		}
		
		
		public Boolean getPuedeIngresar() {
			if(getSession().getAttribute(Const.USUARIO)!= null)
				return new Boolean(true);
			else
				return new Boolean(false);
		}

		/**
		 * @param mostrarFinal  the mostrarFinal to set
		 * @uml.property  name="mostrarFinal"
		 */
		public void setMostrarFinal(Boolean mostrarFinal) {
			this.mostrarFinal = mostrarFinal;
		}

		/**
		 * @param mostrarPantalla  the mostrarPantalla to set
		 * @uml.property  name="mostrarPantalla"
		 */
		public void setMostrarPantalla(Boolean mostrarPantalla) {
			this.mostrarPantalla = mostrarPantalla;
		}

		/**
		 * @return  the mostrarFinal
		 * @uml.property  name="mostrarFinal"
		 */
		public Boolean getMostrarFinal() {
			return mostrarFinal;
		}

		/**
		 * @return  the mostrarPantalla
		 * @uml.property  name="mostrarPantalla"
		 */
		public Boolean getMostrarPantalla() {
			return mostrarPantalla;
		}

		/**
		 * @return  the botonLabel
		 * @uml.property  name="botonLabel"
		 */
		public String getBotonLabel() {
			return botonLabel;
		}

		/**
		 * @param botonLabel  the botonLabel to set
		 * @uml.property  name="botonLabel"
		 */
		public void setBotonLabel(String botonLabel) {
			this.botonLabel = botonLabel;
		}

		/**
		 * @return  the primerLogon
		 * @uml.property  name="primerLogon"
		 */
		public Boolean getPrimerLogon() {
			return primerLogon;
		}

		/**
		 * @param primerLogon  the primerLogon to set
		 * @uml.property  name="primerLogon"
		 */
		public void setPrimerLogon(Boolean primerLogon) {
			this.primerLogon = primerLogon;
		}

		/**
		 * @return the mensajeExi
		 */
		public String getMensajeExi() {
			return mensajeExi;
		}

		/**
		 * @param mensajeExi the mensajeExi to set
		 */
		public void setMensajeExi(String mensajeExi) {
			this.mensajeExi = mensajeExi;
		}

		public String getCodClienteAlfa() {
			return codClienteAlfa;
		}

		public void setCodClienteAlfa(String codClienteAlfa) {
			this.codClienteAlfa = codClienteAlfa;
		}

		public void setUsuario(MusuarioWeb usuario) {
			this.usuario = usuario;
		}

		
		
		public String volver(ActionEvent event) {		
			String goToPage = Const.ANCLA_LISTA_REPORTES_DISPONIBLES;  
			Runtime.getRuntime().gc(); 
			return goToPage; 
		}
		
	}


