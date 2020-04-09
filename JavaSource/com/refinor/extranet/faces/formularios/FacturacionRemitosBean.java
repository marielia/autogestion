
package com.refinor.extranet.faces.formularios;

 
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List; 
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem; 
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.refinor.extranet.data.Marticulos;
import com.refinor.extranet.data.Mccss;
import com.refinor.extranet.data.Mchofer;
import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.MunidadN;
import com.refinor.extranet.data.MusuarioWeb;
import com.refinor.extranet.data.Mvehiculo;
import com.refinor.extranet.data.dao.AlmacenDAO;
import com.refinor.extranet.data.dao.MarticulosDAO;
import com.refinor.extranet.data.dao.MccssDAO;
import com.refinor.extranet.data.dao.MchoferDAO;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MunidadNDAO;
import com.refinor.extranet.data.dao.MvehiculoDAO;
import com.refinor.extranet.data.dao.MvendedorDAO;
import com.refinor.extranet.faces.base.AbstBackingBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.CCSSTO;
import com.refinor.extranet.to.FacturacionRemitoTO;
import com.refinor.extranet.to.FormaPagoTO;
import com.refinor.extranet.to.MEmpleadosTO;
import com.refinor.extranet.to.MProductoTO;
import com.refinor.extranet.to.MTurnoTO;
import com.refinor.extranet.to.MVehiculoTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.Messages;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.DatosObligatoriosException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

 

public class FacturacionRemitosBean extends AbstBackingBean {
	protected Session sessionHib;

	private int pantalla;
	private Messages mensajeria;
	private FacturacionRemitoTO facturacionRemitoTO  = new FacturacionRemitoTO();;
 
	
	private List<SelectItem> formasPago;
	private Integer formaPago;
	private List<SelectItem> productos;
	private Integer producto;
	private Boolean mostrarCodBarra;
	private Boolean  habilitarDatosChofer;
	private String codArticulo;
	private String descArticulo;
	private String cantArticulo;
	private List subItemsNivel1 = new ArrayList();

	public FacturacionRemitosBean() {
		try {

			sessionHib = (new AlmacenDAO()).getSession();
			mensajeria = new Messages(); 

			MusuarioWeb usuario = (MusuarioWeb) getSessionValue("usuario");

			inicializarValores();
			 

		} catch (Exception ex) {
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}

	}

	public void generar(ActionEvent event) {
		Transaction tx = null;
		
		  try{ 
			  
				if(facturacionRemitoTO.getDniChofer().trim().equals("") || 
						facturacionRemitoTO.getDniChofer().trim().equals("0") ||
						facturacionRemitoTO.getDniChofer()==null	)
				{				 
					 throw new  DatosObligatoriosException("Debe ingresar el D.N.I. del chofer.");					 
				} 

				if(facturacionRemitoTO.getPinChofer().trim().equals("") || 
						facturacionRemitoTO.getPinChofer().trim().equals("0") ||
						facturacionRemitoTO.getPinChofer()==null	)
				{				 
					 throw new  DatosObligatoriosException("Debe ingresar el P.I.N. del chofer.");					 
				} 
				   
				
				if(facturacionRemitoTO.getKilometraje().trim().equals("") || 
						facturacionRemitoTO.getKilometraje().trim().equals("0") ||
						facturacionRemitoTO.getKilometraje()==null	)
				{				 
					 throw new  DatosObligatoriosException("Debe ingresar el kilometraje del chofer.");					 
				} 
		 
				
				//validar que el dni y el cliente esten asociados
				MchoferDAO mchoferDAO = new MchoferDAO(sessionHib);
				try {
				    Mchofer mchofer = mchoferDAO.getChoferActivoPorCliente(new Integer(facturacionRemitoTO.getDniChofer()),  facturacionRemitoTO.getCodCliente());
				    
				    if(!mchofer.getPinChof().equals(facturacionRemitoTO.getPinChofer()))
				    {
				    	 throw new  DatosObligatoriosException("El P.I.N. del chofer es incorrecto.");
				    }
				    
				}catch (NoExistenItemsException ex)
				{
					 throw new  DatosObligatoriosException("No es un D.N.I. válido.");
				}
				
				 
		 
		  }catch(DatosObligatoriosException ex){
			  ex.printStackTrace();
			  AddErrorMessage(ex.getMessage()); 
	      }catch(Exception ex){ 
	    	    ex.printStackTrace();
	      		AddErrorMessage(new DataAccessErrorException().getMessage()); 
		  }
		

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

	private void cargarComboFormaPago(int condVta, int codCCSS ) {

		 MccssDAO mCCSSDAO = new MccssDAO(sessionHib);
		 formasPago = new ArrayList<SelectItem>();
		// formasPago.add(new SelectItem(new Integer(-1),""));
		 
		try {
			FormaPagoTO formaPagoTO;			
			List lstDatos = mCCSSDAO.getFormasPagos(condVta, codCCSS);		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				formaPagoTO = (FormaPagoTO)it.next();
				formasPago.add(new SelectItem(new Integer(formaPagoTO.getCodigo()),formaPagoTO.getDescripcion()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se han podido recuperar los vendedores.");
		}	
		 

	}
	
	
	private void cargarComboProdcutoLimiteCarga(int condVehiculo, int codCCSS ) {

		 MarticulosDAO marticulosDAO = new MarticulosDAO(sessionHib);
		 productos = new ArrayList<SelectItem>();
		 productos.add(new SelectItem(new Integer(-1),""));
		 
		try {
			MProductoTO mProductoTO;			
			List lstDatos = marticulosDAO.getProductosLimiteCarga(condVehiculo, codCCSS);		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mProductoTO = (MProductoTO)it.next();
				productos.add(new SelectItem(new Integer(mProductoTO.getCodArticulo()),mProductoTO.getDescripcion()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("No se ha podido recuperar los productos.");
		}	
		 

	}
	
	private void cargarComboProdcutoSinLimiteCarga(  int codCCSS ) {

		 MarticulosDAO marticulosDAO = new MarticulosDAO(sessionHib);
		 productos = new ArrayList<SelectItem>();
		 productos.add(new SelectItem(new Integer(-1),""));
		 
		try {
			MProductoTO mProductoTO;			
			List lstDatos = marticulosDAO.getProductosSinLimiteCarga(codCCSS );		
			Iterator it = lstDatos.iterator();			
			
			while(it.hasNext()) {
				mProductoTO = (MProductoTO)it.next();
				productos.add(new SelectItem(new Integer(mProductoTO.getCodArticulo()),mProductoTO.getDescripcion()));
			}					
			
		} catch(Exception ex) {
			ex.printStackTrace();			
			AddErrorMessage("\"No se ha podido recuperar lo productos.");
		}	
		 

	}
 

	private void inicializarValores() {
		
		facturacionRemitoTO.setFecha(new Date());
		System.out. println("fecha de hoy "+ new Date());
		
		 this.codArticulo = "";
		 this.descArticulo ="";
		 this.cantArticulo = "";
		 
		MusuarioWeb usuario = (MusuarioWeb)getSessionValue("usuario"); 
		MccssDAO mccssDAO= new MccssDAO(sessionHib);
		Mccss mccss= mccssDAO.get(usuario.getCodigoCcssEmpleado());
		facturacionRemitoTO.setCodCCSS(mccss.getCodCcss());
		facturacionRemitoTO.setDescCCSS(mccss.getDescCcss());
		 
		
		MTurnoTO mTurnoTO;
		try {
			//verificar si hay turno vigente abierto
			if(!sessionHib.isOpen())
			{
				sessionHib = (new AlmacenDAO()).getSession();
			}
			mccssDAO= new MccssDAO(sessionHib);
			mTurnoTO = mccssDAO.getTurnoVigenteAbierto(Const.SECTOR_PLAYA, Const.ESTADO_TURNO_VIGENTE, new Integer(mccss.getCodCcss()));
			facturacionRemitoTO.setCodTurno(mTurnoTO.getCodTurno());
			facturacionRemitoTO.setDescTurno(mTurnoTO.getDescTurno());
			facturacionRemitoTO.setCodAlmacen(mTurnoTO.getCodAlmacen());
			facturacionRemitoTO.setDescAlmacen(mTurnoTO.getDescAlmacen());
		} catch (DataAccessErrorException e) {			 
			e.printStackTrace();
			AddErrorMessage(e.getMessage());
		} catch (NoExistenItemsException e) {			 
			e.printStackTrace();
			AddErrorMessage("No hay un Turno abierto para el CCSS.");
		} 
		 
		
		this.pantalla=1; 
		this.mostrarCodBarra=true;
		this.habilitarDatosChofer=true;
		 
	}
	
	
	
	public void validarVehiculo(ActionEvent event)  {
		 
		
		if(facturacionRemitoTO!=null && facturacionRemitoTO.getCodTarjeta()!=null )
		{
			//buscar la patente 
			MvehiculoDAO mVehiculoDAO= new MvehiculoDAO(sessionHib); 
			
		    try {
		    	MVehiculoTO mvehiculoTO = mVehiculoDAO.getVehiculoPorCodBarra(facturacionRemitoTO.getCodTarjeta());
				
				if(mvehiculoTO!=null)					
				{ 
					if(mvehiculoTO.getActivo()==false || mvehiculoTO.getInicializado()==false)					
					{ 
						AddErrorMessage("Vehículo inhabilitado para la carga.");
						this.mostrarCodBarra=true;
						this.habilitarDatosChofer=true;
						
					}else
					{ 	

						if(mvehiculoTO.getEsRefipass()==false)					
						{ 
							AddErrorMessage("El cliente no es REFIPASS.");
							this.mostrarCodBarra=true;
							this.habilitarDatosChofer=true;
							
						}else
						{ 	
							//es refipass
							
							facturacionRemitoTO.setPatente(mvehiculoTO.getDominio());
							facturacionRemitoTO.setDniChofer("");
							facturacionRemitoTO.setKilometraje("");
							facturacionRemitoTO.setPinChofer("");
							
							//validar si el cliente existe
							 Mclientes mClientes = new Mclientes();
							try {
								 MclientesDAO mclientesDAO = new MclientesDAO(sessionHib);
								 mClientes =  mclientesDAO.getClienteporCodClienteAlfa(mvehiculoTO.getCodClienteAlfa()); 
								 							
								 facturacionRemitoTO.setCodAlfaCliente(mvehiculoTO.getCodClienteAlfa());
								 facturacionRemitoTO.setDescCliente(mClientes.getDescripcion());
								 facturacionRemitoTO.setCodCliente(mClientes.getCodigo()); 
								 
								 //cargarComboFormaPago
								 cargarComboFormaPago(mClientes.getCodCondVta(), facturacionRemitoTO.getCodCCSS()); 
								 
								// cargar producto
								  MarticulosDAO marticulosDAO = new MarticulosDAO(sessionHib);
								 if(mvehiculoTO.getVerificaLimiteCarga())
								 {  
									  cargarComboProdcutoLimiteCarga(mvehiculoTO.getCodigo() ,  facturacionRemitoTO.getCodCCSS());  
								 }
								 else
								 {
									  cargarComboProdcutoSinLimiteCarga(facturacionRemitoTO.getCodCCSS());
								 }
								 
								 
								 this.mostrarCodBarra=false;
							     this.habilitarDatosChofer=false;  
							}catch (NoExistenItemsException ex)
							{
								AddErrorMessage("Cliente inexistente.");
								this.mostrarCodBarra=true;
								this.habilitarDatosChofer=true;
								
							}
							
							
							try {
							    //existePeriodoMensual
						        MccssDAO mccssDAO= new MccssDAO(sessionHib);
							    if( mccssDAO.ExistePeriodoMensual(facturacionRemitoTO.getCodCCSS(), facturacionRemitoTO.getFecha() ))
							    {
							    	
							    	 this.mostrarCodBarra=false;
								     this.habilitarDatosChofer=false;
							    } 
							}catch (NoExistenItemsException ex)
							{
								AddErrorMessage("La fecha de facturación no se encuentra dentro de un período abierto.");
								this.mostrarCodBarra=true;
								this.habilitarDatosChofer=true;
								
							}
							
							
							
							try {
								 MccssDAO mccssDAO= new MccssDAO(sessionHib);
								 CCSSTO ccssto =  mccssDAO.getCCSSTO(facturacionRemitoTO.getCodCCSS());
								
								 if(!mClientes.isRefipass() || !ccssto.getEsTerceros())
								 {
									   
										AddErrorMessage("El cliente no es Refipass o el CCSS no es de terceros.");
										this.mostrarCodBarra=true;
										this.habilitarDatosChofer=true;
								 }
							
							}catch (NoExistenItemsException ex)
							{
								AddErrorMessage("No se encontró CCSS.");
								this.mostrarCodBarra=true;
								this.habilitarDatosChofer=true; 
							}

							
							//El cliente tiene suspendida la carga 
							 if(mClientes.isCreditoSusp())
							 {    
								AddErrorMessage("El cliente tiene suspendida la carga.");
								this.mostrarCodBarra=true;
								this.habilitarDatosChofer=true;
							 }
							
							 
							 //No se pudo recuperar la unidad de negocio del vehículo.						 
							if( mvehiculoTO.getCodUnidadNegocio() ==null)
							 {    
								AddErrorMessage("No se pudo recuperar la unidad de negocio del vehículo..");
								this.mostrarCodBarra=true;
								this.habilitarDatosChofer=true;
							 }
							
							
						}
						
					}
					
				}
				 
				
			} catch (NoExistenItemsException | IOException | DataAccessErrorException e) {
				// el vehiculo no existe				 
				AddErrorMessage("El vehículo no existe.");
				this.mostrarCodBarra=true;
				this.habilitarDatosChofer=true;
			}
		}

	}
 
 
	public void cargarProducto(ValueChangeEvent event){
		
		 Integer codArticulo = ((Integer)event.getNewValue()).intValue(); 
		 try {
				
			 MarticulosDAO MarticulosDAO= new MarticulosDAO();
			 Marticulos  Marticulos = MarticulosDAO.get(codArticulo);
			 this.codArticulo = Marticulos.getCodigo()+"";
			 this.descArticulo = Marticulos.getDescripcion();
			 this.cantArticulo = "";
					
		} catch(Exception ex) {
			ex.printStackTrace();
			AddErrorMessage("No se recupero el Grupo.");
		}	
	}
	
	
	public void incluirLinea(ActionEvent event){ 
	 
		 try {  
			 
			 MProductoTO mProductoTO= new MProductoTO();
			 mProductoTO.setCodArticulo(new Integer(this.codArticulo));
			 mProductoTO.setDescripcion( this.descArticulo);
			 mProductoTO.setCantidad(new BigDecimal(this.cantArticulo));
			 
			 this.subItemsNivel1.add(mProductoTO);
			 
			 this.codArticulo =  "";
			 this.descArticulo =  "";
			 this.cantArticulo = "";
					
		} catch(Exception ex) {
			ex.printStackTrace();
			AddErrorMessage("No se recupero el Grupo.");
		}	
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

	 
	 

	public FacturacionRemitoTO getFacturacionRemitoTO() {
		return facturacionRemitoTO;
	}

	public void setFacturacionRemitoTO(FacturacionRemitoTO facturacionRemitoTO) {
		this.facturacionRemitoTO = facturacionRemitoTO;
	}

	public Boolean getMostrarCodBarra() {
		return mostrarCodBarra;
	}

	public void setMostrarCodBarra(Boolean mostrarCodBarra) {
		this.mostrarCodBarra = mostrarCodBarra;
	}

	public Boolean getHabilitarDatosChofer() {
		return habilitarDatosChofer;
	}

	public void setHabilitarDatosChofer(Boolean habilitarDatosChofer) {
		this.habilitarDatosChofer = habilitarDatosChofer;
	}

	public List<SelectItem> getFormasPago() {
		return formasPago;
	}

	public void setFormasPago(List<SelectItem> formasPago) {
		this.formasPago = formasPago;
	}

	public Integer getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Integer formaPago) {
		this.formaPago = formaPago;
	}

	public List<SelectItem> getProductos() {
		return productos;
	}

	public void setProductos(List<SelectItem> productos) {
		this.productos = productos;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	 

	public String getDescArticulo() {
		return descArticulo;
	}

	public void setDescArticulo(String descArticulo) {
		this.descArticulo = descArticulo;
	}

	public String getCantArticulo() {
		return cantArticulo;
	}

	public void setCantArticulo(String cantArticulo) {
		this.cantArticulo = cantArticulo;
	}

	public String getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
	}

	public List getSubItemsNivel1() {
		return subItemsNivel1;
	}

	public void setSubItemsNivel1(List subItemsNivel1) {
		this.subItemsNivel1 = subItemsNivel1;
	}

	 
	
	
}
