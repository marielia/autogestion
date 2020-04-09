package com.refinor.extranet.faces.reportes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.Mvehiculo;
import com.refinor.extranet.data.dao.AlmacenDAO;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MctasCtesDAO;
import com.refinor.extranet.data.dao.MlimiteCargaDAO;
import com.refinor.extranet.data.dao.MvehiculoDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.paginado.Page;

public class RefReporteLimiteDeCargaBean extends AbstListado {
	
	private Session sessionHib;
	private Messages mensajeria;
	private Mclientes cliente;
	private Integer codigo;
	private String nombreCliente;
	private BigDecimal tope;
	private BigDecimal utilizadoCliente;
	private BigDecimal disponible;
	private Mvehiculo vehiculo;
	private String dominio;
	
	public RefReporteLimiteDeCargaBean() {
		
		try {
			
			sessionHib = (new AlmacenDAO()).getSession();	
			mensajeria = new Messages();
			inicializarValores(51, 117);
			
		} catch (Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}
		
	}
	
	public void AddErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));		
	}
	
	private void inicializarValores(int codCliente, int codVehiculo){
		//buscar los datos del cliente y del limite de carga del vehiculo para mostrar
		try{
			
			MclientesDAO mClientesDAO = new MclientesDAO(sessionHib);
			MctasCtesDAO mCtasCtesDAO = new MctasCtesDAO(sessionHib);
			cliente = mClientesDAO.get(codCliente, sessionHib);
			mClientesDAO.refresh(cliente, sessionHib);
			this.codigo = cliente.getCodigo();
			this.nombreCliente = cliente.getDescripcion();
			this.tope = (BigDecimal.valueOf(cliente.getTopeCredito())).setScale(2, RoundingMode.HALF_UP);
			this.utilizadoCliente = (mCtasCtesDAO.getUtilizadoCliente(codCliente)).setScale(2, RoundingMode.HALF_UP);
			this.disponible = this.tope.subtract(this.utilizadoCliente);
			
			MvehiculoDAO mVehiculoDAO = new MvehiculoDAO(sessionHib);
			MlimiteCargaDAO mLimiteCargaDAO = new MlimiteCargaDAO(sessionHib);
			vehiculo = mVehiculoDAO.get(codVehiculo, sessionHib);
			mVehiculoDAO.refresh(vehiculo, sessionHib);
			this.dominio = vehiculo.getDominio();
			setItems(mLimiteCargaDAO.getLimitesCargaPorCodVehiculo(codVehiculo));
			setSubItemsNivel1(getItems());
			cargarPagina();	
			
		}catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(new DataAccessErrorException().getMessage());
		}
		
	}
	
	public String volver() {		
		String goToPage = Const.ANCLA_LISTA_REPORTES_DISPONIBLES;			
		return goToPage;
			
	}
	
	public Session getSessionHib() {
		return sessionHib;
	}

	public void setSessionHib(Session sessionHib) {
		this.sessionHib = sessionHib;
	}

	public Messages getMensajeria() {
		return mensajeria;
	}

	public void setMensajeria(Messages mensajeria) {
		this.mensajeria = mensajeria;
	}

	public void setCliente(Mclientes cliente) {
		this.cliente = cliente;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public BigDecimal getTope() {
		return tope;
	}

	public void setTope(BigDecimal tope) {
		this.tope = tope;
	}

	public BigDecimal getUtilizadoCliente() {
		return utilizadoCliente;
	}

	public void setUtilizadoCliente(BigDecimal utilizadoCliente) {
		this.utilizadoCliente = utilizadoCliente;
	}

	public BigDecimal getDisponible() {
		return disponible;
	}

	public void setDisponible(BigDecimal disponible) {
		this.disponible = disponible;
	}

	public Mvehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Mvehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	
	@Override
	public Boolean getPuedeIngresar() {		
		System.out.println("Verificando permiso para ver la pagina de limites de carga");			
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}

	@Override
	public void generarExcel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException, Exception {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void cargarPagina(){
		//PAGINACION		
		this.pagina = new Page(getSubItemsNivel1(),1,tamanioPaginacion);
		setSubItemsNivel1(pagina.getPage());
		//generarExcel();
		//PAGINACION
	}

}
