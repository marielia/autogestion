
package com.refinor.extranet.faces.formularios;

 
import java.util.List; 
import javax.faces.event.ActionEvent; 
import javax.faces.model.SelectItem; 
import org.hibernate.Session;
import org.hibernate.Transaction;  
import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.dao.AlmacenDAO; 
import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.FacturacionRemitoTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;

 

public class FacturacionRemitosBean extends AbstBackingBean {
	protected Session sessionHib;

	private int pantalla;
	private Messages mensajeria;
	private FacturacionRemitoTO facturacionRemitoTO;

	private List<SelectItem> unidadesNegocio;
	private Integer unidadNegocio;

	public FacturacionRemitosBean() {
		try {

			sessionHib = (new AlmacenDAO()).getSession();
			mensajeria = new Messages();

			MusuarioWeb usuario = (MusuarioWeb) getSessionValue("usuario");

			inicializarValores();
			cargarCombos();

		} catch (Exception ex) {
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}

	}

	public void guardar(ActionEvent event) {
		Transaction tx = null;
		/*
		 * try{ if(this.nombre==null || this.nombre.trim().equals("") ||
		 * this.apellido==null || this.apellido.trim().equals("") ||
		 * this.numeroDocumento==null || this.grupoUnidadNegocio==0 ||
		 * this.unidadNegocio==0){ throw new
		 * DatosObligatoriosException(mensajeria.getMessage().getString(
		 * "datos_onligatorios_label")); }
		 * 
		 * 
		 * if(existeChoferConDNICodigoCliente(this.numeroDocumento,
		 * obtenerCodCliente().getCodigo())){ throw new
		 * ChoferYaExisteException(mensajeria.getMessage().getString(
		 * "nro_documento_ya_registrado_msg")); }
		 * 
		 * Mchofer mChofer = new Mchofer(); //mChofer.setCodigo(obtenerCodigoChofer());
		 * mChofer.setCodigo(obtenerCodigoChoferDesdeSecuencia());
		 * mChofer.setCodCli(obtenerCodCliente().getCodigo());
		 * mChofer.setNombre(nombre); mChofer.setApellido(apellido);
		 * mChofer.setPinChof(this.pinChofer.trim());
		 * mChofer.setDni(this.numeroDocumento);
		 * System.out.println("inicializado->"+inicializado+ " activo->"+activo);
		 * mChofer.setInicializado(this.inicializado); mChofer.setActivo(this.activo);
		 * mChofer.setUnidadNeg(this.unidadNegocio); mChofer.setIdUserLock(new
		 * Boolean(false)); mChofer.setFAlta(new Date());
		 * 
		 * try { tx= sessionHib.beginTransaction(); MchoferDAO mChoferDAO = new
		 * MchoferDAO(sessionHib); mChoferDAO.save(mChofer, sessionHib);
		 * 
		 * // agregado el 22/06/2009 - marcela MsecuenciasDAO mSecuenciaDAO= new
		 * MsecuenciasDAO(sessionHib); Msecuencias mSecuencia= new Msecuencias();
		 * mSecuencia= mSecuenciaDAO.load(Const.NOMB_TABLA_MCHOFER, sessionHib);
		 * mSecuencia.setSecuencia(mChofer.getCodigo()+1);
		 * mSecuenciaDAO.update(mSecuencia,sessionHib);
		 * 
		 * //enviarMailAdministrador(); try { enviarMailAdministrador("smtp.gmail.com",
		 * "587", "false", "true", "true","nietosilvana@gmail.com", "Gasgas259",
		 * "CHECKING SETTINGS", "CHECKING EMAIL FUNCTIONALITY", "text/html",
		 * "nietosilvana@yahoo.com.ar"); } catch (Exception ex) { ex.printStackTrace();
		 * }
		 * 
		 * 
		 * 
		 * try { enviarConGMail();
		 * 
		 * } catch (Exception e) { System.out.println("Error en el envio de mail: " +
		 * e.getMessage()); e.printStackTrace(); }
		 * 
		 * tx.commit(); //envio de mail a refipass para hacerle saber que hay un chofer
		 * esperando el ok
		 * mensajeGuardado=mensajeria.getMessage().getString("registro_ok_chofer_mag");
		 * pantalla=3; // }catch(NoSePudeEnviarMailException excep) { //
		 * excep.printStackTrace(); // tx.rollback(); //
		 * AddErrorMessage(excep.getMessage()); } catch(Exception excep) {
		 * excep.printStackTrace(); tx.rollback(); throw new DataAccessErrorException();
		 * }
		 * 
		 * }catch(DatosObligatoriosException ex){ ex.printStackTrace();
		 * AddErrorMessage(ex.getMessage()); }catch(IOException ex){
		 * ex.printStackTrace(); AddErrorMessage(ex.getMessage());
		 * }catch(ChoferYaExisteException ex){ ex.printStackTrace();
		 * AddErrorMessage(ex.getMessage()); }catch(PersonaNoExisteException ex){
		 * ex.printStackTrace(); AddErrorMessage(ex.getMessage());
		 * }catch(DataAccessErrorException ex){ ex.printStackTrace();
		 * AddErrorMessage(ex.getMessage()); }catch(Exception ex){ ex.printStackTrace();
		 * AddErrorMessage(new DataAccessErrorException().getMessage()); }
		 */

	}


	 
 

	/*
	 * private Mclientes obtenerCodCliente() throws DataAccessErrorException,
	 * PersonaNoExisteException { try { MusuarioWeb usuario = (MusuarioWeb)
	 * getSession().getAttribute(Const.USUARIO);
	 * 
	 * MclientesDAO mclientesDAO = new MclientesDAO(sessionHib); List clientes =
	 * mclientesDAO.getPorDocumento(usuario.getCuit()); cliente = ((Mclientes)
	 * clientes.get(0)); return cliente; } catch (PersonaNoExisteException e) {
	 * e.printStackTrace(); AddErrorMessage(e.getMessage()); throw e; } catch
	 * (DataAccessErrorException e) { e.printStackTrace();
	 * AddErrorMessage(e.getMessage()); throw e;
	 * 
	 * }
	 * 
	 * }
	 */

	private void cargarCombos( ) {

		/*
		 * MgrupoUnDAO mgrupoUnDAO = new MgrupoUnDAO(sessionHib); MchoferDAO mChoferDAO
		 * = new MchoferDAO(sessionHib); gruposUnidadNegocio = new
		 * ArrayList<SelectItem>();
		 * 
		 * try { List lstDatos = null; MgrupoUn mgrupoUn;
		 * 
		 * if (tipo == 1) { // es cliente MusuarioWeb usuario = (MusuarioWeb)
		 * getSession().getAttribute(Const.USUARIO); lstDatos =
		 * mgrupoUnDAO.getGruposUNPorCliente(usuario.getCuit()); } else { // es refipass
		 * MclientesDAO mClientesDAO = new MclientesDAO(sessionHib); Mchofer mChofer =
		 * mChoferDAO.getChoferPorCodigo(nroChofer); Mclientes mCliente =
		 * mClientesDAO.getClienteById(mChofer.getCodCli()); lstDatos =
		 * mgrupoUnDAO.getGruposUNPorCliente(mCliente.getCuit()); }
		 * 
		 * unidadesNegocio = new ArrayList<SelectItem>();
		 * 
		 * if (nroChofer != null) { MunidadNDAO munidadNDao = new
		 * MunidadNDAO(sessionHib); try { MunidadN munidadN; List lstDatos1 =
		 * munidadNDao.getUnidadesNegocioPorGrupo(grupoUnidadNegocio);
		 * 
		 * Iterator it1 = lstDatos1.iterator(); while (it1.hasNext()) { munidadN =
		 * (MunidadN) it1.next(); unidadesNegocio .add(new SelectItem(new
		 * Integer(munidadN.getCodigo()), munidadN.getDescripcion())); } } catch
		 * (Exception ex) { AddErrorMessage(ex.getMessage()); unidadesNegocio = new
		 * ArrayList<SelectItem>(); unidadesNegocio.add(new SelectItem(new Integer(0),
		 * "- Seleccione un Grupo -")); ex.printStackTrace();
		 * AddErrorMessage(mensajeria.getMessage().getString(
		 * "grupo_no_tiene_unidades_negocio")); } } else { gruposUnidadNegocio.add(new
		 * SelectItem(new Integer(0), "- Seleccione un Grupo -"));
		 * unidadesNegocio.add(new SelectItem(new Integer(0),
		 * "- Seleccione un Grupo -")); }
		 * 
		 * Iterator it = lstDatos.iterator();
		 * 
		 * while (it.hasNext()) { mgrupoUn = (MgrupoUn) it.next();
		 * gruposUnidadNegocio.add(new SelectItem(new Integer(mgrupoUn.getCodigo()),
		 * mgrupoUn.getDescripcion())); }
		 * 
		 * } catch (Exception ex) { ex.printStackTrace();
		 * AddErrorMessage("No se han podido recuperar los Grupos de Unidad de Negocio."
		 * ); }
		 */

	}
 

	private void inicializarValores() {
		/*
		 * this.nombre = null; this.apellido = null; this.pinChofer = null;
		 * this.numeroDocumento = null; this.inicializado = new Boolean(false);
		 * this.activo = new Boolean(false); this.pantalla = 1; this.pinChofer = null;
		 */
		
		this.pantalla=1;

	}
 

	/**
	 * volver a la pantalla de lista de menu
	 * 
	 * @author snieto
	 * @return String Pagina a donde vuelve
	 */
	public String volver() {
		getSession().removeAttribute("nroChofer");
		String goToPage = Const.ANCLA_LISTA_REPORTES_DISPONIBLES;
		return goToPage;

	}

	 

	@Override
	public Boolean getPuedeIngresar() {
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica
		// que haya un usuario en session
		// si no esta en session sale del sistema
		// return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}

	public Session getSessionHib() {
		return sessionHib;
	}

	public void setSessionHib(Session sessionHib) {
		this.sessionHib = sessionHib;
	}

	public int getPantalla() {
		return pantalla;
	}

	public void setPantalla(int pantalla) {
		this.pantalla = pantalla;
	}

	public Messages getMensajeria() {
		return mensajeria;
	}

	public void setMensajeria(Messages mensajeria) {
		this.mensajeria = mensajeria;
	}

	public List<SelectItem> getUnidadesNegocio() {
		return unidadesNegocio;
	}

	public void setUnidadesNegocio(List<SelectItem> unidadesNegocio) {
		this.unidadesNegocio = unidadesNegocio;
	}

	public Integer getUnidadNegocio() {
		return unidadNegocio;
	}

	public void setUnidadNegocio(Integer unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}

	public FacturacionRemitoTO getFacturacionRemitoTO() {
		return facturacionRemitoTO;
	}

	public void setFacturacionRemitoTO(FacturacionRemitoTO facturacionRemitoTO) {
		this.facturacionRemitoTO = facturacionRemitoTO;
	}

	 
	
	
	
	
	
}
