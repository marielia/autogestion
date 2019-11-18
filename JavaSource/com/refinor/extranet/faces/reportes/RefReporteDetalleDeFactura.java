package com.refinor.extranet.faces.reportes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.refinor.extranet.data.Mclientes;
import com.refinor.extranet.data.dao.MclientesDAO;
import com.refinor.extranet.data.dao.MfacturasVDAO;
import com.refinor.extranet.faces.base.AbstListado;
import com.refinor.extranet.faces.exportarExcel.ExportarExcelBean;
import com.refinor.extranet.seguridad.SeguridadAdmin;
import com.refinor.extranet.to.ClienteFacturaTO;
import com.refinor.extranet.to.MFacturaVTO;
import com.refinor.extranet.util.comparator.FacturaTOComparator;
import com.refinor.extranet.util.exception.DataAccessErrorException;
import com.refinor.extranet.util.exception.NoExistenItemsException;

public class RefReporteDetalleDeFactura extends AbstListado {

	private BigDecimal saldoFacturasYNotasDebito;
	private BigDecimal montoPagosAplicados;
	private BigDecimal saldoTotal;
	
	public RefReporteDetalleDeFactura() {
		// TODO Auto-generated constructor stub
	}

	public void cargcarListaPotFiltros() throws NoExistenItemsException, DataAccessErrorException ,Exception{
		mostrarLista=new Boolean(false);
		mostrarFrmLista=new Boolean(true);
		
	}

	@Override
	public void generarExcel() {
		try{			
			System.out.println("Paso por generar excel  !!");	
			if(getItems()!=null && getItems().size()!=0){
				ExportarExcelBean exportarExcelBean =  new ExportarExcelBean();		
				this.nombreArchivo = exportarExcelBean.generarExcelReporteDetalleFactura(getItems(),getFechaActualStr(),saldoFacturasYNotasDebito,montoPagosAplicados,saldoTotal);	
				
			}
		}catch(IOException ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
			this.nombreArchivo = "";
			AddErrorMessage(mensajeria.getMessage().getString("exportacion_erronea_msg"));
			AddErrorMessage(ex.getMessage());
		}

	}
	
	/**
	 * Metodo: buscar
	 * Objetivo: Buscar los clientes y docuemtos asociados. Calcular los saldos resumidos a mostrar.
	 * @param event
	 * @throws NoExistenItemsException
	 * @throws DataAccessErrorException
	 * @throws Exception
	 */
	public void buscar(ActionEvent event)throws NoExistenItemsException, DataAccessErrorException ,Exception{
		try{			
			setItems(obtenerFacturasPorCliente());	
			calcularResumenCtaCte(getItems());
//					Collections.sort(getItems(), new FacturaTOComparator());
			setSubItemsNivel1(getItems());	
			
			cargarPagina();					
			mostrarLista=new Boolean(true);
			mostrarFrmLista=new Boolean(true);
		}
		catch(NoExistenItemsException ex){
			ex.printStackTrace();
			AddErrorMessage(ex.getMessage());
			noMostrarLista();
			
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
	
	/**
	 * Metodo: calcularResumenCtaCte
	 * Objetivo: Calcular los montos resumidos de cta cte. Saldo Total = suma de saldos de facturas - suma de 
	 * monto documento no aplicados 
	 * @param lstClienteConFacturas
	 */
	public void calcularResumenCtaCte(List lstClienteConFacturas){
		
		Iterator itClienteConFacturas = lstClienteConFacturas.iterator();
		ClienteFacturaTO clienteFacturaTO= new ClienteFacturaTO();
		Iterator itFacturas= null;
		MFacturaVTO mFacturaVTO=null;
		saldoFacturasYNotasDebito= new BigDecimal("0.00");
		montoPagosAplicados= new BigDecimal("0.00");
		//Recorro los clientes
		while(itClienteConFacturas.hasNext()){
			clienteFacturaTO = (ClienteFacturaTO)itClienteConFacturas.next();
			itFacturas =  clienteFacturaTO.getLstFacturas().iterator();
			
			//recorro las facturas de cada cliente
			while(itFacturas.hasNext()){
				mFacturaVTO = (MFacturaVTO) itFacturas.next();				
				
				if( mFacturaVTO.getTipoComprobante()!=null && ( mFacturaVTO.getTipoComprobante().trim().equals("ND") 
						|| mFacturaVTO.getTipoComprobante().trim().equals("FC")
						|| mFacturaVTO.getTipoComprobante().trim().equals("NCNOAPLI"))){	
					
					BigDecimal monto = new BigDecimal("0.00");
					if(mFacturaVTO.getTipoComprobante().trim().equals("ND") 
							|| mFacturaVTO.getTipoComprobante().trim().equals("FC")	){						
						monto = mFacturaVTO.getSaldo();
					}if(mFacturaVTO.getTipoComprobante().trim().equals("NCNOAPLI")){
						monto = mFacturaVTO.getTotal();
					}
					
					System.out.println(mFacturaVTO.getNroFactura() + " monto "+ monto);
					if(monto==null) monto = new BigDecimal("0.00");
					saldoFacturasYNotasDebito = saldoFacturasYNotasDebito.add(monto);					
				}else if( mFacturaVTO.getTipoComprobante()!=null && mFacturaVTO.getTipoComprobante().trim().equals("RECNOAPLI")){
					montoPagosAplicados= montoPagosAplicados.add(mFacturaVTO.getTotal());
				}else if( mFacturaVTO.getTipoComprobante()!=null && mFacturaVTO.getTipoComprobante().trim().equals("RECNOAPLI2")){
					montoPagosAplicados= montoPagosAplicados.add(mFacturaVTO.getSaldo());
				}				
			}			
		}
		
		saldoTotal = saldoFacturasYNotasDebito.add(montoPagosAplicados);
		
	}
	
	/**
	 * Metodo: obtenerFacturasPorCliente
	 * Objetivo: Obtener para la lista de clientes, todas sus facturas o ND abonadasy los documentos
	 * que se hayan aplicado a las mismas y las facturas o ND adeudadas. Por último se muestra los recibos 
	 * y  notas de creditos no aplicadas.
	 * Hay un combo de donde se puede elegir un cliente en particular para buscar sus documentos. Si no se elige 
	 * ningún cliente trae todos.
	 * @return List lista de clientes, con sus facturas
	 * @throws NoExistenItemsException
	 * @throws DataAccessErrorException
	 * @throws Exception
	 */
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

	public BigDecimal getMontoPagosAplicados() {
		return montoPagosAplicados;
	}

	public void setMontoPagosAplicados(BigDecimal montoPagosAplicados) {
		this.montoPagosAplicados = montoPagosAplicados;
	}

	public BigDecimal getSaldoFacturasYNotasDebito() {
		return saldoFacturasYNotasDebito;
	}

	public void setSaldoFacturasYNotasDebito(BigDecimal saldoFacturasYNotasDebito) {
		this.saldoFacturasYNotasDebito = saldoFacturasYNotasDebito;
	}

	public BigDecimal getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(BigDecimal saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

}

