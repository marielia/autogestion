
package com.refinor.extranet.faces.reportes;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MctasCtesDAO;
import com.refinor.extranet.data.dao.MfacturasVDAO;
import com.refinor.extranet.data.dao.MpedidosDAO;
import com.refinor.extranet.data.dao.MreciboDAO;

import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.ClienteFacturaTO;
import com.refinor.extranet.to.CtaCteClienteTO;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class RefReporteResumenClientesBean extends AbstListado {
	
	private CtaCteClienteTO cuenta;
	
	public RefReporteResumenClientesBean(){
		
	}

	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		try{		
			//buscar cliente
			if(!sessionHib.isOpen())
				sessionHib = (new MclientesDAO()).getSession();
			
			MclientesDAO mCliDAO= new MclientesDAO(sessionHib);
			Mclientes mcli= mCliDAO.get(this.getCliente(), sessionHib);			
			cuenta = new CtaCteClienteTO(mcli.getDescripcion(), mcli.getCodigo(),mcli.getCodClienteAlfa(),mcli.getTopeCredito());
			
			//FACTURADO
			MfacturasVDAO mFactDAO= new MfacturasVDAO(sessionHib);	
			DecimalFormat format = new DecimalFormat("#.##"); 
			double totalFactCompleto=mFactDAO.obtenerTotalFacturadoCompleto(cuenta.getCodCLiente());
			cuenta.setTotalFacturadoCompleto(format.format(totalFactCompleto));
			double totalFactMfact = mFactDAO.obtenerTotalFacturadoDeMFacturasV(cuenta.getCodCLiente());
			cuenta.setTotalFacturadoDeMFactV(format.format(totalFactMfact));
			double totalFactMCtaCte = mFactDAO.obtenerTotalFacturadoDeMCtaCte(cuenta.getCodCLiente());
			cuenta.setTotalFacturadoDeMCtaCte(format.format(totalFactMCtaCte));
			
			//cuenta.setDifTotalFacturado(format.format(totalFactCompleto-totalFactMfact));
			
			//RECIBIDO
			MreciboDAO mRecDAO= new MreciboDAO(sessionHib);	
			double totalRecCompleto=mRecDAO.obtenerTotalReciboCompleto(cuenta.getCodCLiente());
			cuenta.setTotalReciboCompleto(format.format(totalRecCompleto));
			double totalRecMrec = mRecDAO.obtenerTotalReciboMrecibo(cuenta.getCodCLiente());
			cuenta.setTotalReciboDeMRec(format.format(totalRecMrec));
			double totalRecMctacte =Math.abs(mRecDAO.obtenerTotalReciboMctacte(cuenta.getCodCLiente()));
			cuenta.setTotalReciboMctacte(format.format(totalRecMctacte));
			
//			cuenta.setDifTotalRec(format.format(mRecDAO.obtenerTotalReciboCompleto(cuenta.getCodCLiente())-mRecDAO.obtenerTotalReciboMrecibo(cuenta.getCodCLiente())));
			
			
			//REMITO		
			
			double factCMenosRecC = totalFactCompleto-totalRecCompleto;
			cuenta.setTotalFacturaMenosReciboCompleto(format.format(factCMenosRecC));
			double factCMenosRecS = totalFactMfact - totalRecMrec;
			cuenta.setTotalFacturaMenosReciboMfacMrec(format.format(factCMenosRecS));
			
			double factMenosRecCtaCte = totalFactMCtaCte - totalRecMctacte;
			cuenta.setTotalFacturaMenosReciboMCTaCte(format.format(factMenosRecCtaCte));
			
			
			//REMITO
			MpedidosDAO mpeidosDAO= new MpedidosDAO(sessionHib);
			BigDecimal totRemitoMped=mpeidosDAO.getTotalRemitosAFacturar(cuenta.getCodCLiente());
			cuenta.setTotalRemitoMpedido(format.format(totRemitoMped));
			
			MctasCtesDAO mctacteDAO= new MctasCtesDAO(sessionHib);
			BigDecimal totRemitoCtaCte=mctacteDAO.getTotalRemitosAFacturar(cuenta.getCodCLiente());
			cuenta.setTotalRemitoCtaCte(format.format(totRemitoCtaCte));
			
			
			cuenta.setDifRemitoMpedidoVsCtaCte(format.format(totRemitoMped.subtract(totRemitoCtaCte)));
			
			//factura-recibo+remito OLD
			BigDecimal saldoC=new BigDecimal(factCMenosRecC).add(totRemitoMped);
			cuenta.setSaldoCompleto(format.format(saldoC));
			//OLD
			
			//Saldo tal cual se calcula en HO
			setItems(mctacteDAO.getFacturasNoAbonadas(mcli.getCodClienteAlfa(),1));
			RefReporteCuentaCorrienteBean a = new RefReporteCuentaCorrienteBean();
			setSaldoCtaCte(a.obtenerSaldosCtasCtes(getItems()));
			
			MpedidosDAO mPedidoDAO = new MpedidosDAO(getSessionHib());	
			try {
				setRemitosAfacturar(mPedidoDAO.getTotalRemitosAFacturar(mcli.getCodClienteAlfa()));
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
			
			cuenta.setSaldoCompleto(format.format(getSaldoCliente()));
			
			//fin Saldo tal cual se calcula en HO
			
			BigDecimal saldo=new BigDecimal(factCMenosRecS).add(totRemitoCtaCte);
			cuenta.setSaldo(format.format(saldo));
			
			cuenta.setDifSaldo(format.format(saldoC.subtract(saldo)));			
		
			
			BigDecimal saldoPorCC = mctacteDAO.getTotalSaldoPorComprobanteCliente(cuenta.getCodCLiente());
			cuenta.setSaldoPorComprobanteCliente(format.format(saldoPorCC));					
			
			cuenta.setDisponibleCompleto(format.format(new BigDecimal(cuenta.getDblLimiteCreditoCliente()).subtract(saldoC)));		
			
//			Saldo tal cual se calcula en CCSS 
			cuenta.setDisponible(format.format(new BigDecimal(cuenta.getDblLimiteCreditoCliente()).subtract(saldoPorCC)));
				
			
			mostrarLista=new Boolean(true);
			mostrarFrmLista=new Boolean(true);
		}
		/*catch(NoExistenItemsException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
			
		}catch(DataAccessErrorException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}*/catch(Exception ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
		}
	}
	
	public List obtenerFacturasPorCliente()throws NoExistenItemsException,DataAccessErrorException,Exception{
		
		try{			
			MfacturasVDAO mfacturasVDAO = new MfacturasVDAO(getSessionHib());
			List lstFacturas=null;
			List<ClienteFacturaTO> lstClientesYFactura= new ArrayList<ClienteFacturaTO>();
			MclientesDAO mclientesDAO =  new MclientesDAO(getSessionHib());
			//buscar clientes
			List clientes = new ArrayList();
			if(cliente.equals(new Integer(-1))){
			   clientes = mclientesDAO.getClientePorEstado(true);
			}else {			
			  clientes = mclientesDAO.getClientePorId(cliente);
			}
			
			Mclientes mClientes= new Mclientes();
			ClienteFacturaTO clienteFacturaTO= new ClienteFacturaTO();
			for(int i=0; i<clientes.size(); i++){
				mClientes = (Mclientes)clientes.get(i);
				lstFacturas = new ArrayList();
				try{
					lstFacturas = mfacturasVDAO.getTodasLasFacturasAgrupadas(fltFechaDesde,fltFechaHasta,fltFechaDesdeDos,fltFechaHastaDos,mClientes.getCodigo());
				}catch (NoExistenItemsException ex){
					ex.printStackTrace();
				}
				clienteFacturaTO = new ClienteFacturaTO();
				clienteFacturaTO.setCliente(mClientes);
				clienteFacturaTO.setLstFacturas(lstFacturas);
				
				if(lstFacturas.size()>0){
					clienteFacturaTO.setMuestraFactura(1);
					lstClientesYFactura.add(clienteFacturaTO);
				}				
				
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
		
	@Override
	public void cargcarListaPotFiltros() throws NoExistenItemsException,
			DataAccessErrorException, Exception {
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);

	}

	@Override
	public void generarExcel() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Metodo: getPuedeIngresar
	 * Funcion: Verificar si tiene permiso para el ingreso a la pagina
	 */
	public Boolean getPuedeIngresar() {
		// Cambiar cuando haya manejo de usuario y funciones. Por ahora solo verifica que haya un usuario en session
		//si no esta en session sale del sistema
		//return SeguridadAdmin.getTienePermiso(SeguridadAdmin.MENU_DATOS_PERSONALES);
		return SeguridadAdmin.getTienePermiso(1);
	}

	public CtaCteClienteTO getCuenta() {
		return cuenta;
	}

	public void setCuenta(CtaCteClienteTO cuenta) {
		this.cuenta = cuenta;
	}
	
	

}
