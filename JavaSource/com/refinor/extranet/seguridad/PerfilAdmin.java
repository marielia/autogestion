package com.refinor.extranet.seguridad;

import com.itsolver.util.io.FileUtil;

import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MusuarioWebDAO;

import com.refinor.extranet.util.Const;

import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.ExisteEmailException;

import com.refinor.extranet.util.exception.PersonaNoExisteException;

import com.refinor.extranet.util.exception.UsuarioNoExisteException;
import com.refinor.extranet.util.exception.UsuarioRegistroNoExisteException;
import com.refinor.extranet.util.exception.UsuarioYaRegistradoException;

import java.util.ArrayList;
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
	/**
	 * Devuelve las modalidades en que está registrada una Persona.
	 * 
	 * @param tipoDocumento		Tipo de Documento
	 * @param numeroDocumento	Número de Documento
	 * @return	ArrayList Con las modalidades con que está registrado (Cliente/Contratante, Agente, Proveedor, Miembro Seguro Colectivo)
	 * @throws DataAccessErrorException
	 */
	/*public List<Integer>  registracionesUsuario(Integer tipoDocumento, String numeroDocumento) throws DataAccessErrorException {
		List<Integer> registraciones= new ArrayList<Integer>();
		// Chequeo de Cliente
		ClienteDAO cliDAO= new ClienteDAO(session);
		List clientes= new ArrayList();
		try {
			clientes= cliDAO.getClientesPorDocumento(tipoDocumento, numeroDocumento);
			Iterator it= clientes.iterator();
			Cliente cli;
			boolean registrado= false;
			while(it.hasNext() && !registrado) {
				cli= (Cliente)it.next();
				if(cliDAO.clienteRegistrado(cli)) {
					registraciones.add(Const.TIPO_REGISTRO_CLIENTE);
					registrado= true;
				}
			}
		} catch(Exception ex) {
			throw new DataAccessErrorException();
		}
		// Chequeo de Agente Productor
		AgenteDAO agenteDAO= new AgenteDAO();
		List agentes;
		try {
			agentes= agenteDAO.getAgentePorDocumento(tipoDocumento, numeroDocumento, Const.TIPO_AGENTE_PRODUCTOR);
			Iterator it= agentes.iterator();
			boolean registrado= false;
			while(it.hasNext() && !registrado) {
				if(agenteDAO.agenteRegistrado((Agente)it.next())) {
					registraciones.add(Const.TIPO_REGISTRO_AGENTE);
					registrado= true;
				}
			}
		} catch(Exception ex) {
			throw new DataAccessErrorException();
		}
		// Chequeo de Agente Organizador
		try {
			agentes= agenteDAO.getAgentePorDocumento(tipoDocumento, numeroDocumento, Const.TIPO_AGENTE_ORGANIZADOR);
			Iterator it= agentes.iterator();
			boolean registrado= false;
			while(it.hasNext() && !registrado) {
				if(agenteDAO.agenteRegistrado((Agente)it.next())) {
					registraciones.add(Const.TIPO_REGISTRO_AGENTE_ORGANIZADOR);
					registrado= true;
				}
			}
		} catch(Exception ex) {
			throw new DataAccessErrorException();
		}
		// Chequeo de Proveedor
		ProveedorDAO proveDAO= new ProveedorDAO();
		Proveedor prove;
		try {
			prove= proveDAO.getProveedorPorDocumento(tipoDocumento, numeroDocumento);
			if(proveDAO.proveedorRegistrado(prove))
				registraciones.add(Const.TIPO_REGISTRO_PROVEEDOR);
		} catch(Exception ex) {
			throw new DataAccessErrorException();
		}
		// Chequeo de Persona de Seguro de Vida Colectivo
		PersonaVidaColectivoDAO personaDAO= new PersonaVidaColectivoDAO();
		PersonaVidaColectivo persona;
		try {
			persona= personaDAO.getPersonaPorDocumento(tipoDocumento, numeroDocumento);
			if(personaDAO.personaRegistrada(persona))
				registraciones.add(Const.TIPO_REGISTRO_MIEMBRO_COLECTIVO);
		} catch(Exception ex) {
			throw new DataAccessErrorException();
		}
		
		return registraciones;
	}
	
	*//**
	 * Chequea si un usuario está registrado o no.
	 * 
	 * @param tipoDocumento		Tipo de Documento
	 * @param numeroDocumento	Número de Documento
	 * @return	Verdadero, si está registrado, o falso si no.
	 * 
	 * @throws DataAccessErrorException
	 *//*
	public boolean usuarioRegistrado(Integer tipoDocumento, String numeroDocumento) throws DataAccessErrorException  {
		try {
			if(registracionesUsuario(tipoDocumento, numeroDocumento).size() > 0)
				return true;
			else
				return false;
		} catch (DataAccessErrorException ex) {
			throw new DataAccessErrorException();
		}
	}
	
	*//**
	 * Recupera las modalidades en las que una Persona se se encuentra inscripta en IAM 
	 * @param tipoDocumento		Tipo de Documento
	 * @param numeroDocumento	Numero de Documento
	 * 
	 * @return	Lista con las modalidades en las que está inscripta
	 *//*
	public List<Integer> enQueExistePersona(Integer tipoDocumento, String numeroDocumento) {
		List<Integer> enQueExiste= new ArrayList<Integer>();
		ClienteDAO cliDAO= new ClienteDAO(session);
		AgenteDAO agenteDAO= new AgenteDAO(session);
		ProveedorDAO proveDAO= new ProveedorDAO(session);
		PersonaVidaColectivoDAO personaDAO= new PersonaVidaColectivoDAO(session);
		try {
			if(cliDAO.getClientesPorDocumento(tipoDocumento, numeroDocumento).size() > 0) 
				enQueExiste.add(Const.TIPO_REGISTRO_CLIENTE);
		} catch(Exception ex) {
			// No hace nada, no lo registra como perteneciente a Clientes.
			ex.toString();
		}
		try {
			if(agenteDAO.getAgentePorDocumento(tipoDocumento, numeroDocumento, Const.TIPO_AGENTE_PRODUCTOR).size() > 0) {
				enQueExiste.add(Const.TIPO_REGISTRO_AGENTE);
			}
		} catch(Exception ex) {
			// No hace nada, no lo registra como perteneciente a Agentes.
			ex.toString();
		}
		try {
			if(agenteDAO.getAgentePorDocumento(tipoDocumento, numeroDocumento, Const.TIPO_AGENTE_ORGANIZADOR).size() > 0) {
				enQueExiste.add(Const.TIPO_REGISTRO_AGENTE_ORGANIZADOR);
			}
		} catch(Exception ex) {
			// No hace nada, no lo registra como perteneciente a Agentes.
			ex.toString();
		}
		try {
			proveDAO.getProveedorPorDocumento(tipoDocumento, numeroDocumento);
			enQueExiste.add(Const.TIPO_REGISTRO_PROVEEDOR);
		} catch(Exception ex) {
			// No hace nada, no lo registra como perteneciente a Proveedores.
			ex.toString();
		}
		try {
			personaDAO.getPersonaPorDocumento(tipoDocumento, numeroDocumento);
			enQueExiste.add(Const.TIPO_REGISTRO_MIEMBRO_COLECTIVO);
		} catch(Exception ex) {
			// No hace nada, no lo registra como perteneciente a Persona Miembro de Seguro Colectivo.
			ex.toString();
		}
		return enQueExiste;
	}
	
	*//**
	 * Recupera los modos en los que un Usuario está registrado.
	 * 
	 * @param tipoDocumento		Tipo de Documento
	 * @param numeroDocumento	Número de Documento
	 * @return	Un set con los tipos de registraciones que tiene el Usuario.
	 * @throws DataAccessErrorException
	 * @throws UsuarioNoExisteException
	 *//*
	public Set<UsuarioRegistro> comoQueSeRegistro(Integer tipoDocumento, String numeroDocumento)  throws DataAccessErrorException, UsuarioNoExisteException {
		try {
			UsuarioDAO usrDAO= new UsuarioDAO(session);
			Set registros= usrDAO.getPorDocumento(tipoDocumento, numeroDocumento).getUsuarioRegistros(); 
			return (registros == null ? new HashSet() : registros);
		} catch(UsuarioNoExisteException ex) {
			throw new UsuarioNoExisteException();
		} catch(Exception ex) {
			throw new DataAccessErrorException();
		}
	}*/
	
	
	/**
	 * Registra un Usuario
	 * 
	 * @param tipoDocumento		Tipo de Documento
	 * @param numeroDocumento	Número de Documento
	 * @param usr				Usuario (entidad con datos) a registrar
	 * @return El Usuario registrado.
	 * @throws Exception 
	 */
	public MusuarioWeb registrarUsuario(String numeroDocumento, MusuarioWeb usr,Session session) 
	throws PersonaNoExisteException, ExisteEmailException, UsuarioYaRegistradoException, 
			DataAccessErrorException {
	Transaction tx= null;
	try {
		// Chequea si existe el cliente
		MclientesDAO personaDAO= new MclientesDAO(session);
		List personas= new ArrayList();
		try {
			
			personas= personaDAO.getPorDocumento(numeroDocumento,usr.getEmail());			
			
			if(personas.size() == 0)
				throw new PersonaNoExisteException();
			
		} catch (PersonaNoExisteException ex) {
			throw ex;
		} catch(Exception ex) {
			throw new DataAccessErrorException();
		}
		
		
		//Chequea si el Usuario está definido. Si no, lo registra
		MusuarioWebDAO usrDAO= new MusuarioWebDAO(session);
		try {
			MusuarioWeb usuario= usrDAO.getPorDocumento(numeroDocumento);			
			throw new UsuarioYaRegistradoException();
		} catch(UsuarioNoExisteException ex) {
			// Chequea que no haya otro Usuario registrado con el mismo EMail
			try {
				usrDAO.getPorEmail(usr.getEmail());
				throw new ExisteEmailException();  // Validacion Inhibida para testing !!!
				//throw new UsuarioNoExisteException();
			} catch(UsuarioNoExisteException e) {	
				// Crea el nuevo usuario
				Mclientes unCliente = new Mclientes();
				tx= session.beginTransaction();
				//usr.setPersona(persona);
				usr.setCuit(numeroDocumento);
				unCliente = (Mclientes) personas.get(0);
				usr.setCodClienteAlfa(unCliente.getCodClienteAlfa());
				try {
					usrDAO.save(usr, session);
					tx.commit();
					return usr;
				} catch(Exception excep) {
					excep.printStackTrace();
					tx.rollback();
					throw new DataAccessErrorException();
				}
			}catch(ExisteEmailException e) {	// El Mail ya está en uso por otro usuario  // Validacion Inhibida para testing !!!
				throw e;
			} catch(Exception e) {
				throw new DataAccessErrorException();
			}
		} catch(UsuarioYaRegistradoException ex) {
			throw ex;
		} catch(DataAccessErrorException ex) {
			throw ex;
		}
	} catch(UsuarioYaRegistradoException ex) {
		throw ex;
	} catch(PersonaNoExisteException ex) {
		throw ex;
	} catch(ExisteEmailException ex) { // Validacion Inhibida para testing !!!
		throw ex;
	} catch(Exception ex) {
		ex.printStackTrace();
		tx.rollback();
		throw new DataAccessErrorException();
	}
	}
	
	/**
	 * Registra un Usuario
	 * 
	 * @param tipoDocumento		Tipo de Documento
	 * @param numeroDocumento	Número de Documento
	 * @param usr				Usuario (entidad con datos) a registrar
	 * @return El Usuario registrado.
	 * @throws Exception 
	 */
	public MusuarioWeb registrarUsuarioEmpleado(int codigo, int ccss, MusuarioWeb usr,Session session) 
	throws PersonaNoExisteException, ExisteEmailException, UsuarioYaRegistradoException, 
			DataAccessErrorException {
	Transaction tx= null;
	try {
			
		//Chequea si el Usuario está definido. Si no, lo registra
		MusuarioWebDAO usrDAO= new MusuarioWebDAO(session);
		try {
			MusuarioWeb usuario= usrDAO.getCodEmpleadoYCCSS(codigo, ccss);			
			throw new UsuarioYaRegistradoException();
		} catch(UsuarioNoExisteException ex) {
			// Chequea que no haya otro Usuario registrado con el mismo EMail
			try {
				usrDAO.getPorEmail(usr.getEmail());
				throw new ExisteEmailException();  // Validacion Inhibida para testing !!!
				//throw new UsuarioNoExisteException();
			} catch(UsuarioNoExisteException e) {	
				// Crea el nuevo usuario
				
				tx= session.beginTransaction();				
				try {
					usrDAO.save(usr, session);
					tx.commit();
					return usr;
				} catch(Exception excep) {
					excep.printStackTrace();
					tx.rollback();
					throw new DataAccessErrorException();
				}
			}catch(ExisteEmailException e) {	// El Mail ya está en uso por otro usuario  // Validacion Inhibida para testing !!!
				throw e;
			} catch(Exception e) {
				throw new DataAccessErrorException();
			}
		} catch(UsuarioYaRegistradoException ex) {
			throw ex;
		} catch(DataAccessErrorException ex) {
			throw ex;
		}
	} catch(UsuarioYaRegistradoException ex) {
		throw ex;
	}/* catch(PersonaNoExisteException ex) {
		throw ex;
	}*/ catch(ExisteEmailException ex) { // Validacion Inhibida para testing !!!
		throw ex;
	} catch(Exception ex) {
		ex.printStackTrace();
		tx.rollback();
		throw new DataAccessErrorException();
	}
	}
	
	/**
	 * Registra un Usuario en los perfiles en que aún no está registrado.
	 * 
	 * @param tipoDocumento		Tipo de Documento
	 * @param numeroDocumento	Número de Documento
	 * @param usr				Usuario
	 * @throws PersonaSinPerfilException
	 * @throws UsuarioYaRegistradoException
	 * @throws DataAccessErrorException
	 *//*
	public void registrarPerfiles(Integer tipoDocumento, String numeroDocumento, Usuario usr) throws PersonaSinPerfilException, UsuarioYaRegistradoException, DataAccessErrorException {
		Set registros= null;
		List<Integer> perfiles= null;
		try {
			// Chequea en qué está registrado
			registros= comoQueSeRegistro(tipoDocumento, numeroDocumento);
			// Chequea si la Persona tiene al menos un perfil
			perfiles= enQueExistePersona(tipoDocumento, numeroDocumento);
			if(perfiles.size() == 0)
				throw new PersonaSinPerfilException();
			// Chequea si está registrado en todos los perfiles en que figura
			if(perfiles.size() == registros.size()) {
				throw new UsuarioYaRegistradoException(); 
			} else if(perfiles.size() >= registros.size()) {
				// Registra en todos los perfiles en que no está registrado
				Integer tipoRegistro= null;
				Iterator<Integer> it= perfiles.iterator();
				while(it.hasNext()) {
					tipoRegistro= it.next();
					UsuarioRegistroDAO usrRegistroDAO= new UsuarioRegistroDAO(session);
					RegistroDAO regDAO= new RegistroDAO(session); 
					try {
						usrRegistroDAO.getUsuarioRegistro(tipoRegistro, usr.getId());
					} catch(UsuarioRegistroNoExisteException ex) {
						UsuarioRegistro usrRegistro= new UsuarioRegistro();
						usrRegistro.setRegistro(regDAO.get(tipoRegistro.intValue(), session));
						usrRegistro.setUsuario(usr);
						try {
							usrRegistroDAO.save(usrRegistro, session);
						} catch(Exception e) {
							throw new DataAccessErrorException(); 
						}
					}
				}
			}
		} catch(UsuarioYaRegistradoException ex) {
			throw ex;
		} catch(PersonaSinPerfilException ex) {
			throw ex;
		} catch(Exception ex) {
			throw new DataAccessErrorException();
		}
	}
	
	*//**
	 * Devuelve las funciones disponibles para el usuario de acuerdo 
	 * a su perfil.
	 * 
	 * @param usuarioId		Id de Usuario
	 * @return				Set con todal las funciones disponibles 
	 * 						para el usuario.
	 *//*
	@SuppressWarnings("unchecked")
	public Set<Funcion> getFunciones(int usuarioId, Integer perfilId, Integer tipoUsuario) throws DataAccessErrorException, PerfilNoExisteException {
		UsuarioDAO usrDAO= new UsuarioDAO(session);
		UsuarioInternoDAO usrIntDAO= new UsuarioInternoDAO(session);
		FuncionDAO funcDAO= new FuncionDAO(session);
		FuncionRolDAO funcRolDAO= new FuncionRolDAO(session);
		RolDAO rolDAO= new RolDAO(session);
		
		Set<Funcion> funciones= new HashSet<Funcion>();
		try {
			Set<FuncionRol> funcionesRol = new HashSet<FuncionRol>();
			Rol rol= null;
			if(perfilId.intValue() != 0 && tipoUsuario.equals(Const.TIPO_USUARIO_WEB)) {
				if(perfilId.equals(Const.TIPO_REGISTRO_CLIENTE))
					rol= rolDAO.load(Const.ROL_CLIENTE.intValue(), session);
				else if(perfilId.equals(Const.TIPO_REGISTRO_AGENTE)
						|| perfilId.equals(Const.TIPO_REGISTRO_AGENTE_ORGANIZADOR))
					rol= rolDAO.load(Const.ROL_AGENTE.intValue(), session);
				else if(perfilId.equals(Const.TIPO_REGISTRO_PROVEEDOR))
					rol= rolDAO.load(Const.ROL_PROVEEDOR.intValue(), session);
				else
					throw new PerfilNoExisteException();
				funcionesRol= rol.getFuncionRols();
				Iterator<FuncionRol> it2 = funcionesRol.iterator();
				Funcion func= null;
				while (it2.hasNext()) {
					func= (Funcion) funcDAO.loadInitialize(it2.next().getFuncion().getId(), session);
					if(func.getEstado() == 1)
						funciones.add(func);
				}
			} else {  // Perfil 0 (cero) - Usuario Tipo Interno
				Set roles = usrIntDAO.get(usuarioId, session).getUsuarioInternoRols();
				UsuarioInternoRol usrRol;
				Iterator it = roles.iterator();
				while (it.hasNext()) {
					usrRol = (UsuarioInternoRol) it.next();
					funcionesRol.addAll(funcRolDAO.getFuncionesRol(
							new Integer(usrRol.getRol().getId())).list());
					Iterator<FuncionRol> it2 = funcionesRol.iterator();
					Funcion func= null;
					while (it2.hasNext()) {
						func= (Funcion) funcDAO.get(it2.next().getFuncion().getId(), session);
						if(func.getEstado() == Const.FUNCION_ACTIVA)
							funciones.add(func);
					}
				}
			}
		} catch (PerfilNoExisteException e) {
			throw e;
		} catch (DataAccessErrorException e) {
			throw e;
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new DataAccessErrorException();
		}
		
		Hibernate.initialize(funciones);
		return funciones;
	}
	
	*//**
	 * Recupera las Funciones para un Menu dado (menuId) disponibles para el usuario logueado.
	 * 
	 * @param menuId		Id del Item de Menu
	 * @param funciones		Funciones disponibles para el Usuario logueado
	 * @return				Funciones disponibles para el Menu/Usuario
	 *//*
	public Set getFuncionesMenu(Integer menuId, Set funciones) {
		SortedSet<Funcion> funcionesDisponibles= new java.util.TreeSet<Funcion>(new FuncionComparator());
		try {
			MenuDAO menuDAO= new MenuDAO(session);
			Set funcionesMenu= (menuDAO.get(menuId.intValue(), session)).getFuncions();
			Funcion func;
			Iterator it= funcionesMenu.iterator();
			while(it.hasNext()) {
				func= (Funcion)it.next();
				if(funciones.contains(func))
					funcionesDisponibles.add(func);
			}
		} catch(Exception ex) {
			System.out.println("Error al consultar las funciones disponibles para el menu id " + menuId);
			ex.printStackTrace();
		}
	
		return funcionesDisponibles;
	}
	
	*//**
	 * Verifica si una Función está disponible para un Usuario
	 * por su token o identificador (ident).
	 * 
	 * @param usuarioId	Id del Usuario
	 * @param ident		Token/ident de la Función
	 * 
	 * @return	Verdadero si el Usuario dispone de la Función, 
	 * 			o falso si no.
	 *//*
	public boolean disponeDeFuncion(Integer usuarioId, String ident) {
		FuncionDAO funcDAO= new FuncionDAO();
		return funcDAO.disponeDeFuncion(usuarioId, ident);
	}
	
	*//**
	 * Recupera la lista de opciones para un "select" de perfiles
	 * disponibles para un usuario.
	 *  
	 * @param id	Id del Usuario
	 * @return	Lista de perfiles (SelectItems)
	*//*
	 
	@SuppressWarnings("unchecked")
	public List getRegistracionOptions(int id) {
		UsuarioDAO usrDAO= new UsuarioDAO(session);
		List<SelectItem> regOptions= new ArrayList<SelectItem>();
		Set registraciones= usrDAO.load(id, session).getUsuarioRegistros();
		Iterator it= registraciones.iterator();
		while(it.hasNext()) {
			UsuarioRegistro usrRegistro= (UsuarioRegistro)it.next();
			if(perfilActivo(usrRegistro))
				regOptions.add(new SelectItem(usrRegistro.getRegistro().getId(), usrRegistro.getRegistro().getDescripcion()));
		}
		Collections.sort(regOptions, new SelectItemComparator());
		return regOptions;
	}

	*//**
	 * Recupera la lista de opciones para un "select" de perfiles
	 * disponibles para un usuario.
	 * 
	 * @param tipoDocumento		Tipo de Documento
	 * @param numeroDocumento	Nùmero de Documento
	 * @return	Lista de perfiles (SelectItems)
	 * @throws PersonaNoExisteException
	 *//*
	@SuppressWarnings("unchecked")
	public List getPerfilesOptions(Integer tipoDocumento, String numeroDocumento, List personas) throws PersonaNoExisteException {
		RegistroDAO regDAO= new RegistroDAO(session);
		List perfiles= this.enQueExistePersona(tipoDocumento, numeroDocumento);
		List<SelectItem> perfilesOptions= new ArrayList<SelectItem>();
		Iterator it= perfiles.iterator();
		while(it.hasNext()) {
			Registro reg=  regDAO.load(((Integer)it.next()).intValue(), session);
			try {
				if(perfilActivo(tipoDocumento, numeroDocumento, reg.getId(), personas))
					perfilesOptions.add(new SelectItem(reg.getId(), reg.getDescripcion()));
			} catch(PersonaNoExisteException ex){
				throw ex;
			}
		}
		Collections.sort(perfilesOptions, new SelectItemComparator());
		return perfilesOptions;
	}
	
	*//**
	 * Chequea si un perfil está activo para una registración
	 * 
	 * @param usrRegistro	Registración (UsuarioRegistro)
	 * @return	Verdadero si está activo, o falso si no.
	 *//*
	public boolean perfilActivo(Integer tipoDocumento, String numeroDocumento, Integer registroId, List personas) throws PersonaNoExisteException { 
		ClienteDAO cliDAO= new ClienteDAO(session);
		AgenteDAO agenteDAO= new AgenteDAO(session);
		ProveedorDAO proveDAO= new ProveedorDAO(session);
		PersonaVidaColectivoDAO perVidaDAO= new PersonaVidaColectivoDAO(session);
		Persona per= null;
		Iterator it2= personas.iterator();
		while(it2.hasNext()) {
			per= (Persona)it2.next();
			if(registroId.intValue() == Const.TIPO_REGISTRO_CLIENTE.intValue()) {
				try {
					if(cliDAO.load(per.getNroPersona(), session).getCodEstado() == Const.ESTADO_ACTIVO)
						return true;
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			} else if(registroId.intValue() == Const.TIPO_REGISTRO_AGENTE.intValue()) { 
				try {
					if(agenteDAO.getAgentePorPersona(per.getNroPersona()).getCodEstado() == Const.ESTADO_ACTIVO)
						return true;
					List lstAgentePorPersona= agenteDAO.getListaAgentePorPersona(per.getNroPersona());
					Iterator itAgentes = lstAgentePorPersona.iterator();
					Agente agente = new Agente();
					Boolean esAgente=new Boolean(false);
					
					while(itAgentes.hasNext()){
						agente =(Agente)itAgentes.next();						
						if(agente.getCodEstado()== Const.ESTADO_ACTIVO){
							esAgente=new Boolean(true);
						}					
					}					
					return esAgente;
					
				} catch(AgenteNoExisteException ex) {
					// No hace Nada
					ex.toString();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			} else if(registroId.intValue() == Const.TIPO_REGISTRO_AGENTE_ORGANIZADOR.intValue()) { 
				try {
					if(agenteDAO.getAgentePorPersona(per.getNroPersona()).getCodEstado() == Const.ESTADO_ACTIVO)
						return true;
					
					List lstAgentePorPersona= agenteDAO.getListaAgentePorPersona(per.getNroPersona());
					Iterator itAgentes = lstAgentePorPersona.iterator();
					Agente agente = new Agente();
					Boolean esAgente=new Boolean(false);
					
					while(itAgentes.hasNext()){
						agente =(Agente)itAgentes.next();						
						if(agente.getCodEstado()== Const.ESTADO_ACTIVO){
							esAgente=new Boolean(true);
						}					
					}					
					return esAgente;
					
				} catch(AgenteNoExisteException ex) {
					// No hace Nada
					ex.toString();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			} else if(registroId.intValue() == Const.TIPO_REGISTRO_PROVEEDOR.intValue()) {
				try {
					if(proveDAO.load(per.getNroPersona(), session).getCodEstado() == Const.ESTADO_ACTIVO) 
						return true;
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			} else {
				try {
					if(perVidaDAO.getPersonaPorPersona(per.getNroPersona()).getCodEstado() == Const.ESTADO_ACTIVO)
						return true;
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return false;
	}
	
	*//**
	 * Registra un intento de logon fallido
	 * 
	 * @param usr Usuario
	 */
	public void registrarLogonFallido(MusuarioWeb usr) {
		Transaction tx= session.beginTransaction();
		MusuarioWebDAO usrDAO= new MusuarioWebDAO(session);
   		FileUtil fileUtil= new FileUtil();   		
   		Properties props= fileUtil.getPropertiesFile();
   		if(((new Date()).getTime() - usr.getUltimoIntentoFallido().getTime()) / 1000 
   					< new Integer(props.getProperty(Const.SEGUNDOS_LOGON_FALLIDO)).intValue()) {
   			usr.setUltimoIntentoFallido(new Date());
			usr.setIntentosFallidos(usr.getIntentosFallidos() + 1);
			if(usr.getIntentosFallidos() >= Const.INTENTO_FALLIDOS) {
				usr.setEstado(Const.USUARIO_INACTIVO);
			}
   		} else {
   			usr.setUltimoIntentoFallido(new Date());
			usr.setIntentosFallidos(1);
   		}
   		usrDAO.save(usr, session);
   		tx.commit();
	}
	
	/**
	 * "Resetea" los intentos de logon fallidos a 0 (cero), si es necesario
	 * 
	 * @param usr	Usuario
	 */
	public void limpiarIntentosFallidos(MusuarioWeb usr) {
		if(usr.getIntentosFallidos() > 0) {
			Transaction tx= session.beginTransaction();
			MusuarioWebDAO usrDAO= new MusuarioWebDAO(session);
			usr.setIntentosFallidos(0);
			usr.setUltimoIntentoFallido(Const.FECHA_RESET_INTENTO_FALLIDO);
	   		usrDAO.save(usr, session);
	   		tx.commit();
		}
	}
	
}
