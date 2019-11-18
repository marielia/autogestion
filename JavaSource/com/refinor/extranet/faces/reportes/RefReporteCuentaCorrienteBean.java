package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.event.ActionEvent;
import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MctasCtesDAO;
import com.refinor.extranet.data.dao.MpedidosDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.ClienteFacturaTO;
import com.refinor.extranet.to.ComposicionCtasCtesTO;
import com.refinor.extranet.to.CtasCtesTO;
import com.refinor.extranet.to.HashTO;
import com.refinor.extranet.util.Const;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;
import com.refinor.extranet.util.paginado.Page;

public class RefReporteCuentaCorrienteBean extends AbstListado {

	private List lstComposicionCtasCtes;
	private Double limiteCredito;
	private String nombreArchivoSimple;
	
	

	public RefReporteCuentaCorrienteBean() {
		try{			
			limpiarLst();
			
			nombreArchivoSimple = "";
			nombreArchivo= "";
			
			String codClienteAlfa=null;			
			if(getTipoUsuarioLogueado()==1){
				//es cliente				
				codClienteAlfa=codCliente;					
			}else if(getTipoUsuarioLogueado()==0){
				//es refipass			
				if(this.cliente!=null && !this.cliente.equals(new Integer(-1)))
					codClienteAlfa = obtenerCodClienteAlfa(this.cliente);	
			}	
			
					
		}catch(DataAccessErrorException ex){
				ex.printStackTrace();
				AddErrorMessage(ex.getMessage());
				noMostrarLista();
		}catch(Exception ex){
				ex.printStackTrace();
				AddErrorMessage(ex.getMessage());
				noMostrarLista();
		}
	
	}

	
	public void cargarPaginaCtaCte(){
		//PAGINACION		
		this.pagina = new Page(getSubItemsNivel1(),1,tamanioPaginacion);
		setSubItemsNivel1(pagina.getPage());
		
		//PAGINACION
	}
	
	public BigDecimal obtenerSaldosCtasCtes(List items){
		 Iterator it = items.iterator();
		 CtasCtesTO ctasCtesTO= new CtasCtesTO();
		 BigDecimal saldoTotal= new BigDecimal(0.00);
		 BigDecimal saldoAVencer= new BigDecimal(0.00);
		 BigDecimal saldoVencA30Dias= new BigDecimal(0.00);
		 BigDecimal saldoVencMas30Dias= new BigDecimal(0.00);
		 BigDecimal saldoRecibidoACuentas= new BigDecimal(0.00);
		 BigDecimal saldoChequeNoCobrado= new BigDecimal(0.00);
		 
		 while(it.hasNext()){
			 ctasCtesTO = (CtasCtesTO)it.next(); 
			 System.out.println(ctasCtesTO.getComprobanteNumero() + " " + ctasCtesTO.getComprobanteDescripcion());
			 
			 if(ctasCtesTO.getEstadoInt()==0 && (ctasCtesTO.getComprobanteDescripcion().contains("FACTURA") 
					 || ctasCtesTO.getComprobanteDescripcion().contains("REMITO")
					 || ctasCtesTO.getComprobanteDescripcion().contains(Const.NOTA_DEBITO))){
				 saldoAVencer= saldoAVencer.add(ctasCtesTO.getImporte());
			 }else  if(ctasCtesTO.getEstadoInt()==1 && (ctasCtesTO.getComprobanteDescripcion().contains("FACTURA") 
					 || ctasCtesTO.getComprobanteDescripcion().contains("REMITO")
					 || ctasCtesTO.getComprobanteDescripcion().contains(Const.NOTA_DEBITO))){
				 saldoVencA30Dias = saldoVencA30Dias.add(ctasCtesTO.getImporte());
			 }else  if(ctasCtesTO.getEstadoInt()==2 && (ctasCtesTO.getComprobanteDescripcion().contains("FACTURA") 
					 || ctasCtesTO.getComprobanteDescripcion().contains("REMITO")
					 || ctasCtesTO.getComprobanteDescripcion().contains(Const.NOTA_DEBITO))){
				 saldoVencMas30Dias = saldoVencMas30Dias.add(ctasCtesTO.getImporte());
			 }else if(ctasCtesTO.getComprobanteDescripcion().contains(Const.RECIBO_NO_APLICADO) 
					 || ctasCtesTO.getComprobanteDescripcion().contains(Const.PAGO_NO_APLICADO_RECIBO)
					 || ctasCtesTO.getComprobanteDescripcion().contains(Const.NOTA_CREDITO)
					 || ctasCtesTO.getComprobanteDescripcion().contains(Const.NC_NO_APLICADO)
					){
				 saldoRecibidoACuentas= saldoRecibidoACuentas.add(ctasCtesTO.getImporte());
			 }else if(ctasCtesTO.getComprobanteDescripcion().contains(Const.VALOR_POSDATADO)){
				 saldoChequeNoCobrado=saldoChequeNoCobrado.add(ctasCtesTO.getImporte());
			 }
			 
			 saldoTotal=saldoTotal.add(ctasCtesTO.getImporte());
		 }
		 
		 lstComposicionCtasCtes= new ArrayList();
		 ComposicionCtasCtesTO composcionTO = new ComposicionCtasCtesTO();
		 composcionTO.setNoVencidas(saldoAVencer);
		 composcionTO.setVencidoA30Dias(saldoVencA30Dias);
		 composcionTO.setVencidoMas30Dias(saldoVencMas30Dias);
		 composcionTO.setRecibidoACuentas(saldoRecibidoACuentas);
		 composcionTO.setSaldoChequeNoCobrado(saldoChequeNoCobrado);
		 
		 lstComposicionCtasCtes.add(composcionTO);
		 
		 return saldoTotal;
	}
	
	public void limpiarLst(){
		  BigDecimal saldoAVencer= new BigDecimal(0.00);
		 BigDecimal saldoVencA30Dias= new BigDecimal(0.00);
		 BigDecimal saldoVencMas30Dias= new BigDecimal(0.00);
		 BigDecimal saldoRecibidoACuentas= new BigDecimal(0.00);
		 lstComposicionCtasCtes= new ArrayList();
		 ComposicionCtasCtesTO composcionTO = new ComposicionCtasCtesTO();
		 composcionTO.setNoVencidas(saldoAVencer);
		 composcionTO.setVencidoA30Dias(saldoVencA30Dias);
		 composcionTO.setVencidoMas30Dias(saldoVencMas30Dias);
		 composcionTO.setRecibidoACuentas(saldoRecibidoACuentas);
		 composcionTO.setSaldoChequeNoCobrado(saldoRecibidoACuentas);
		 lstComposicionCtasCtes.add(composcionTO);
	}
		
	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception{
		mostrarLista=new Boolean(true);
		mostrarFrmLista=new Boolean(true);		
	}	
	
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		this.nombreArchivo="";
		this.nombreArchivoSimple="";
		try{	
			MctasCtesDAO mctacteDAO = new MctasCtesDAO(getSessionHib());
			
			String codClienteAlfa=null;
			if(getTipoUsuarioLogueado()==0){
				//es refipass
				
				if(this.cliente!=null && !this.cliente.equals(new Integer(-1))){
					codClienteAlfa = obtenerCodClienteAlfa(this.cliente);
				}
				
				setItems(mctacteDAO.getFacturasNoAbonadas(codClienteAlfa,1));			
			}
			
			if(this.cliente!=null && !this.cliente.equals(new Integer(-1))){
				//cuando tenga seleccionado un cliente determinado muestro su limite de crtedito.				
				this.limiteCredito = obtenerTopeCredito(this.cliente);				
			}
			setSubItemsNivel1(getItems());		
			cargarPaginaCtaCte();					
			mostrarLista=new Boolean(true);
			mostrarFrmLista=new Boolean(true);
			
			setSaldoCtaCte(obtenerSaldosCtasCtes(getItems()));			
			
			MpedidosDAO mPedidoDAO = new MpedidosDAO(getSessionHib());	
			try {
				setRemitosAfacturar(mPedidoDAO.getTotalRemitosAFacturar(codClienteAlfa));
			} catch (DataAccessErrorException e) {			
				e.printStackTrace();			
			} catch (NoExistenItemsException e) {			
				e.printStackTrace();			
			}
			
			try {			
				setSaldoCliente(getSaldoCtaCte().add(getRemitosAfacturar()));
			} catch (Exception e) {			
					e.printStackTrace();
					AddErrorMessage("Hubo errores al calcular el Saldo del Cliente.");
			}
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			setSaldoCliente(new BigDecimal(0.00));
			setSaldoCtaCte(new BigDecimal(0.00));
			setLimiteCredito(0.00);
			limpiarLst();
			setRemitosAfacturar(new BigDecimal(0.00));
			noMostrarLista();			
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			setSaldoCliente(new BigDecimal(0.00));
			setSaldoCtaCte(new BigDecimal(0.00));
			setLimiteCredito(0.00);
			setRemitosAfacturar(new BigDecimal(0.00));
			limpiarLst();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}catch(Exception ex){
			ex.printStackTrace();
			setSaldoCliente(new BigDecimal(0.00));
			setSaldoCtaCte(new BigDecimal(0.00));
			setLimiteCredito(0.00);
			setRemitosAfacturar(new BigDecimal(0.00));
			limpiarLst();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}
		}

	@Override
	public void generarExcel() {
		try{			
			//System.out.println("Paso por generar excel !!");
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();		
				
				if (getTipoUsuarioLogueado()==1) {
					this.nombreArchivo = exportarExcelBean.generarExcelReporteCtasCtes(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());	
				}else if(getTipoUsuarioLogueado()==0){
					//Hacer un nuevo formato para el excel.					
					List lstDocumento = obtenerListadoCtaCtePorCliente();
					//this.nombreArchivo = exportarExcelBean.generarExcelReporteCtaCteFacturaPorCliente(lstDocumento,"",getTipoUsuarioLogueado(),getFechaActualStr());
					this.nombreArchivoSimple = exportarExcelBean.generarExcelReporteCtasCtes(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());	
					
				}
				
			}	
		}catch(IOException ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			this.nombreArchivoSimple = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			this.nombreArchivoSimple = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}

	}
	
	
	public String generarExcelNuevo(ActionEvent evetnt) {
		try{			
			System.out.println("Paso por generar excel !!");
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();		
				
				if (getTipoUsuarioLogueado()==1) {
					this.nombreArchivo = exportarExcelBean.generarExcelReporteCtasCtes(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());	
				}else if(getTipoUsuarioLogueado()==0){
						this.nombreArchivoSimple = exportarExcelBean.generarExcelReporteCtasCtes(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());	
					
				}
				
			}	
		}catch(IOException ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			this.nombreArchivoSimple = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			this.nombreArchivoSimple = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}
		
		return this.nombreArchivoSimple;

	}
	
	
	public String generarExcelNuevo2(ActionEvent evetnt) {
		try{			
			System.out.println("Paso por generar excel 02!!");
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();		
				
				if (getTipoUsuarioLogueado()==1) {
					this.nombreArchivo = exportarExcelBean.generarExcelReporteCtasCtes(getItems(),getFechaActualStr(),getTipoUsuarioLogueado());	
				}else if(getTipoUsuarioLogueado()==0){
					//Hacer un nuevo formato para el excel.					
					List lstDocumento = obtenerListadoCtaCtePorCliente();
					this.nombreArchivo = exportarExcelBean.generarExcelReporteCtaCteFacturaPorCliente(lstDocumento,"",getTipoUsuarioLogueado(),getFechaActualStr());
				}
				
			}	
		}catch(IOException ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			this.nombreArchivoSimple = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			this.nombreArchivoSimple = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}
		
		return this.nombreArchivo;

	}
	
	
	public List obtenerListadoCtaCtePorCliente()throws NoExistenItemsException,DataAccessErrorException,Exception{
		
		try{			
			List lstFacturas=null;
			List lstResumen=null;
			List<ClienteFacturaTO> lstClientesYFactura= new ArrayList<ClienteFacturaTO>();
			MclientesDAO mclientesDAO =  new MclientesDAO(getSessionHib());
			//buscar clientes
			List clientes = new ArrayList();
			if(cliente==null || cliente.equals(new Integer(-1))){
			   clientes = mclientesDAO.getClientePorEstado(true);
			}else {			
			   clientes = mclientesDAO.getClientePorId(cliente);
			}
			
			Mclientes mClientes= new Mclientes();
			ClienteFacturaTO clienteFacturaTO= new ClienteFacturaTO();
			HashTO resumenDocumentos = null;
			for(int i=0; i<clientes.size(); i++){
				mClientes = (Mclientes)clientes.get(i);
				lstFacturas = new ArrayList();
				lstResumen= new ArrayList();
				try{
					MctasCtesDAO mctacteDAO = new MctasCtesDAO(getSessionHib());	
					//mando en 1 como mostrarDocNoAplicados, para pedir que busque las doc no aplicados.
					lstFacturas = mctacteDAO.getFacturasNoAbonadas(mClientes.getCodClienteAlfa(),0);
				}catch (NoExistenItemsException ex){
					ex.printStackTrace();
				}
				
				clienteFacturaTO = new ClienteFacturaTO();
				clienteFacturaTO.setCliente(mClientes);
				clienteFacturaTO.setLstFacturas(lstFacturas);
				
				resumenDocumentos = new HashTO();
				
//				saldo de facturas, nc, y nd
				resumenDocumentos.setTitulo("Saldo de Facturas y ND: ");
				BigDecimal saldoCtaCte = obtenerSaldosFacturas(clienteFacturaTO.getLstFacturas());
				resumenDocumentos.setMonto(saldoCtaCte);
				lstResumen.add(resumenDocumentos);
				
				
				
//				saldo de documentos a cuenta
				resumenDocumentos = new HashTO();
				resumenDocumentos.setTitulo("Saldo Documentos A Cuenta: ");
				BigDecimal saldoDocACuenta = obtenerSaldoRecibidoACuentas(clienteFacturaTO.getLstFacturas());
				resumenDocumentos.setMonto(saldoDocACuenta);
				lstResumen.add(resumenDocumentos);
				
//				saldo de valores o cheuqes podatados
				resumenDocumentos = new HashTO();
				resumenDocumentos.setTitulo("Saldo Valores Posdatados: ");
				BigDecimal saldoValPosData = obtenerSaldoChequeNoCobrado(clienteFacturaTO.getLstFacturas());
				resumenDocumentos.setMonto(saldoValPosData);
				lstResumen.add(resumenDocumentos);
				
//				remitos a facturar
				resumenDocumentos = new HashTO();
				resumenDocumentos.setTitulo("Remitos a Facturar: ");
				BigDecimal remitoAFac = obtenerResumenRemitos(mClientes.getCodClienteAlfa());
				resumenDocumentos.setMonto(remitoAFac);
				lstResumen.add(resumenDocumentos);
				
				resumenDocumentos = new HashTO();
				lstResumen.add(resumenDocumentos);
				
				//saldo total	
				resumenDocumentos = new HashTO();
				resumenDocumentos.setTitulo("Saldo Cliente: ");
				BigDecimal saldoCliente = saldoCtaCte.add(remitoAFac.add(saldoDocACuenta.add(saldoValPosData)));
				resumenDocumentos.setMonto(saldoCliente);
				lstResumen.add(resumenDocumentos);
				
				
				//limite de credito	
				resumenDocumentos = new HashTO();
				resumenDocumentos.setTitulo("Limite de Credito: ");
				Double limiteCredito = clienteFacturaTO.getCliente().getTopeCredito();
				resumenDocumentos.setMonto(new BigDecimal(limiteCredito));
				lstResumen.add(resumenDocumentos);
				
				resumenDocumentos = new HashTO();
				lstResumen.add(resumenDocumentos);
				
				//saldo disponible	
				resumenDocumentos = new HashTO();
				resumenDocumentos.setTitulo("Saldo Disponible: ");
				resumenDocumentos.setMonto(new BigDecimal(limiteCredito).subtract(saldoCliente));
				lstResumen.add(resumenDocumentos);				
				
				
				clienteFacturaTO.setLstResumenCtaCte(lstResumen);
				clienteFacturaTO.setMuestraFactura(1);
				lstClientesYFactura.add(clienteFacturaTO);
							
			}
			 
			return lstClientesYFactura;
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			throw ex;			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
			
		}
	}
	
	public BigDecimal obtenerSaldosFacturas(List items){
		 
		 CtasCtesTO ctasCtesTO= new CtasCtesTO();
		 BigDecimal saldoTotal= new BigDecimal(0.00);
		 BigDecimal saldoAVencer= new BigDecimal(0.00);
		 BigDecimal saldoVencA30Dias= new BigDecimal(0.00);
		 BigDecimal saldoVencMas30Dias= new BigDecimal(0.00);
			
		if(items!=null && items.size()>0){
			 Iterator it = items.iterator();
			 while(it.hasNext()){
				 ctasCtesTO = (CtasCtesTO)it.next(); 
				 
				 if(ctasCtesTO.getEstadoInt()==0 && (ctasCtesTO.getComprobanteDescripcion().contains("FACTURA") 
						 || ctasCtesTO.getComprobanteDescripcion().contains("REMITO")
						 || ctasCtesTO.getComprobanteDescripcion().contains(Const.NOTA_DEBITO))){
					 saldoAVencer= saldoAVencer.add(ctasCtesTO.getImporte());
				 }else  if(ctasCtesTO.getEstadoInt()==1 && (ctasCtesTO.getComprobanteDescripcion().contains("FACTURA") 
						 || ctasCtesTO.getComprobanteDescripcion().contains("REMITO")
						 || ctasCtesTO.getComprobanteDescripcion().contains(Const.NOTA_DEBITO))){
					 saldoVencA30Dias = saldoVencA30Dias.add(ctasCtesTO.getImporte());
				 }else  if(ctasCtesTO.getEstadoInt()==2 && (ctasCtesTO.getComprobanteDescripcion().contains("FACTURA") 
						 || ctasCtesTO.getComprobanteDescripcion().contains("REMITO")
						 || ctasCtesTO.getComprobanteDescripcion().contains(Const.NOTA_DEBITO))){
					 saldoVencMas30Dias = saldoVencMas30Dias.add(ctasCtesTO.getImporte());
				 }
				 
				
			 }
		}
		 saldoTotal=saldoAVencer.add(saldoVencA30Dias.add(saldoVencMas30Dias));
		 return saldoTotal;
	}
	
	public BigDecimal obtenerSaldoChequeNoCobrado(List items){
		
		 CtasCtesTO ctasCtesTO= new CtasCtesTO();
		 BigDecimal saldoChequeNoCobrado= new BigDecimal(0.00);	
		 
		 if(items!=null && items.size()>0){
			 Iterator it = items.iterator();
			 while(it.hasNext()){
				 ctasCtesTO = (CtasCtesTO)it.next();			 
				 if(ctasCtesTO.getComprobanteDescripcion().contains(Const.VALOR_POSDATADO)){
					 saldoChequeNoCobrado=saldoChequeNoCobrado.add(ctasCtesTO.getImporte());
				 } 
			 }	 
		 }
		 return saldoChequeNoCobrado;
	}
	
	
	
	public BigDecimal obtenerSaldoRecibidoACuentas(List items){
		 
		 CtasCtesTO ctasCtesTO= new CtasCtesTO();		
		 BigDecimal saldoRecibidoACuentas= new BigDecimal(0.00);
		 if(items!=null && items.size()>0){
			 Iterator it = items.iterator();		 
			 while(it.hasNext()){
				 ctasCtesTO = (CtasCtesTO)it.next(); 			 
				 if(ctasCtesTO.getComprobanteDescripcion().contains(Const.RECIBO_NO_APLICADO) 
						 || ctasCtesTO.getComprobanteDescripcion().contains(Const.PAGO_NO_APLICADO_RECIBO) 
						 || ctasCtesTO.getComprobanteDescripcion().contains(Const.NOTA_CREDITO)
						 || ctasCtesTO.getComprobanteDescripcion().contains(Const.NC_NO_APLICADO)){
					 saldoRecibidoACuentas= saldoRecibidoACuentas.add(ctasCtesTO.getImporte());
				 }		 
			 }
		 }
				
		 return saldoRecibidoACuentas;
	}
	
	private BigDecimal obtenerResumenRemitos(String codClienteAlfa){
		MpedidosDAO mPedidoDAO = new MpedidosDAO(getSessionHib());	
		BigDecimal remitos= new BigDecimal("0.00");
		try {
			remitos = mPedidoDAO.getTotalRemitosAFacturar(codClienteAlfa);
		} catch (DataAccessErrorException e) {			
			e.printStackTrace();			
		} catch (NoExistenItemsException e) {			
			e.printStackTrace();			
		}
	return 	remitos;
	}

	@Override
	public Boolean getPuedeIngresar() {		
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}
	
	public void verRemitos(ActionEvent event){
		try{
			
			String codClienteAlfa=null;			
			if(getTipoUsuarioLogueado()==1){
				//es cliente				
				codClienteAlfa=codCliente;					
			}else if(getTipoUsuarioLogueado()==0){
				//es refipass			
				if(this.cliente!=null && !this.cliente.equals(new Integer(-1)))
					codClienteAlfa = obtenerCodClienteAlfa(this.cliente);	
			}
		
			
			mostrarFrmListaSecundaria= new Boolean(true);
			mostrarListaSecundaria= new Boolean(true);
			mostrarFrmLista= new Boolean(false);
			mostrarLista= new Boolean(false);			
			
			MpedidosDAO mPedidosDAO = new MpedidosDAO(getSessionHib());
			setItems2(mPedidosDAO.getRemitosPorCliente(codClienteAlfa));
			
			setSubItemsNivel2(getItems2());			

			this.paginaSecundaria = new Page(getSubItemsNivel2(),1,tamanioPaginacion);
			setSubItemsNivel2(paginaSecundaria.getPage());
			generarExcelSecundario();		
			
			mostrarFrmListaSecundaria= new Boolean(true);
			mostrarListaSecundaria= new Boolean(true);
			mostrarFrmLista= new Boolean(false);
			mostrarLista= new Boolean(false);		
		}catch(DataAccessErrorException ex){
			mostrarListaSecundaria= new Boolean(false);
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}catch(NoExistenItemsException ex){
			mostrarListaSecundaria= new Boolean(false);
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
		}		
	}
	
	public void volverPrincipal(ActionEvent event){
		mostrarFrmListaSecundaria= new Boolean(false);
		mostrarListaSecundaria= new Boolean(false);
		mostrarLista=new Boolean(true);
		mostrarFrmLista=new Boolean(true);			
	}
	
	public void generarExcelSecundario() {
		try{			
				//System.out.println("Paso por generar excel de remitos !!");			
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();			
				this.nombreArchivoSecundario = exportarExcelBean.generarExcelReporteRemitosCTACTE(getItems2(),getFechaActualStr());			
			}catch(IOException ex){
				ex.printStackTrace();
				this.nombreArchivoSecundario = "";
				AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
				AddErrorMessage(ex.getMessage());
			}catch(Exception ex){
				ex.printStackTrace();
				this.nombreArchivoSecundario = "";
				AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
				AddErrorMessage(ex.getMessage());
			}

		}
	

	public Double getLimiteCredito() {
		return limiteCredito;
	}


	public void setLimiteCredito(Double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}


	public List getLstComposicionCtasCtes() {
		return lstComposicionCtasCtes;
	}

	public void setLstComposicionCtasCtes(List lstComposicionCtasCtes) {
		this.lstComposicionCtasCtes = lstComposicionCtasCtes;
	}
	public String getNombreArchivoSimple() {
		return nombreArchivoSimple;
	}

	public void setNombreArchivoSimple(String nombreArchivoSimple) {
		this.nombreArchivoSimple = nombreArchivoSimple;
	}
}
