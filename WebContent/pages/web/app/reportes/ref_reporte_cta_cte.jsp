<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:loadBundle basename="com.refinor.extranet.bundle.Messages" var="Message"/>
<html>
<f:view>
	<head>
        <meta http-equiv="Content-Type"
             content="text/html; charset=windows-1252"/>
		<title><h:outputText value="#{Message.nombre_sistema}"/> </title>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/refinor.css">		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/cal2.js"></script>
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsfechas.js"></script>
		
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0">
		<%@ include file="../header.jsp" %>
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteCuentaCorrienteBean.mostrarFrmLista}" >	
		<t:saveState value="#{refReporteCuentaCorrienteBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteCuentaCorrienteBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>		
					
			 
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>			
			<t:column>
				<t:outputText value="#{Message.tit_informe_cta_cte}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>		
				
		
		<%/*FILTROS*/%>		
			<h:panelGrid columns="4" width="80%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column rendered="#{sessionScope.usuario.tipo==0}">
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column rendered="#{sessionScope.usuario.tipo==0}"></h:column>
				<h:column rendered="#{sessionScope.usuario.tipo==0}"></h:column>
				<h:column rendered="#{sessionScope.usuario.tipo==0}"></h:column>	
				<t:column rendered="#{sessionScope.usuario.tipo==0}">							
						<t:outputText styleClass="cpo8" value="&nbsp;&nbsp;#{Message.cliente_label}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />							
						<h:selectOneListbox id="cliente" size="1" value="#{refReporteCuentaCorrienteBean.cliente}" styleClass="campo"  >
						<f:selectItems value="#{refReporteCuentaCorrienteBean.clientes}" />
					</h:selectOneListbox>
				</t:column>
				<t:column rendered="#{sessionScope.usuario.tipo==0}">
											
				</t:column>									
			</h:panelGrid>
			
			<h:panelGrid columns="4" width="80%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column rendered="#{sessionScope.usuario.tipo==0}">	</h:column>
				<h:column rendered="#{sessionScope.usuario.tipo==0}">	</h:column>
				<h:column rendered="#{sessionScope.usuario.tipo==0}">	</h:column>				
				<h:column rendered="#{sessionScope.usuario.tipo==0}">
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteCuentaCorrienteBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
			<h:messages styleClass="errorNegro"/>	
			
		
		
		<h:panelGrid width="95%" cellspacing="3" columns="1" cellpadding="5" 
		rendered="#{refReporteCuentaCorrienteBean.mostrarLista}" styleClass="columnaTablaTexto" >
		<h:column rendered="#{sessionScope.usuario.tipo==0}">				
		   		<t:commandButton value="#{Message.generar_excel_cta_cte_simple_label}" actionListener="#{refReporteCuentaCorrienteBean.generarExcelNuevo}" styleClass="boton2"/>
				
				<h:outputLink  rendered="#{refReporteCuentaCorrienteBean.nombreArchivoSimple!='' && refReporteCuentaCorrienteBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteCuentaCorrienteBean.nombreArchivoSimple}">
					<h:outputText value=" #{Message.descargar_archivo_abel}" />
				</h:outputLink>				
		 </h:column>
		 
		 
		  <h:column rendered="#{sessionScope.usuario.tipo==0}">				
		   		<t:commandButton value="#{Message.generar_excel_cta_cte_compuesto_label}" actionListener="#{refReporteCuentaCorrienteBean.generarExcelNuevo2}" styleClass="boton2"/>
				
				<h:outputLink  rendered="#{refReporteCuentaCorrienteBean.nombreArchivo!='' && refReporteCuentaCorrienteBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteCuentaCorrienteBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_archivo_abel}" />
				</h:outputLink>					
				<%/* esto esta bien 	      		
		   		<h:outputLink  rendered="#{refReporteCuentaCorrienteBean.nombreArchivoSimple!='' && refReporteCuentaCorrienteBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteCuentaCorrienteBean.nombreArchivoSimple}">
					<h:outputText value=" #{Message.descargar_excel_cta_cte_simple_label}" />
				</h:outputLink>	
		   		<h:outputText styleClass="cpo7" rendered="#{refReporteCuentaCorrienteBean.mostrarLista}" value="&nbsp;&nbsp;&nbsp;&nbsp;.::.&nbsp;&nbsp;&nbsp;&nbsp;" escape="false"/>	
		
		   		<h:outputLink  rendered="#{refReporteCuentaCorrienteBean.nombreArchivo!='' && refReporteCuentaCorrienteBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteCuentaCorrienteBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_excel_cta_cte_compuesto_label}" />
				</h:outputLink>	
				*/%>
		 </h:column>
		</h:panelGrid> 
		 
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5"
		 rendered="#{refReporteCuentaCorrienteBean.mostrarLista}">
		 <h:column>
		   		<h:commandButton rendered="#{refReporteCuentaCorrienteBean.pagina.lastPage!=1 && refReporteCuentaCorrienteBean.pagina.totalElements!=0 && refReporteCuentaCorrienteBean.pagina.numpage != 1 && refReporteCuentaCorrienteBean.mostrarLista}" actionListener="#{refReporteCuentaCorrienteBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteCuentaCorrienteBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteCuentaCorrienteBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteCuentaCorrienteBean.pagina.numpage == 1}" rendered="#{refReporteCuentaCorrienteBean.pagina.lastPage!=1 && refReporteCuentaCorrienteBean.pagina.totalElements!=0  && refReporteCuentaCorrienteBean.pagina.numpage != 1 && refReporteCuentaCorrienteBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteCuentaCorrienteBean.mostrarLista}" value="#{refReporteCuentaCorrienteBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteCuentaCorrienteBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteCuentaCorrienteBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteCuentaCorrienteBean.mostrarLista}" value="#{refReporteCuentaCorrienteBean.pagina.totalElements}&nbsp;&nbsp;&nbsp;&nbsp;" escape="false"/>	
		   		<%/*
		   		<h:outputLink  rendered="#{refReporteCuentaCorrienteBean.nombreArchivo!='' && refReporteCuentaCorrienteBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteCuentaCorrienteBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
				*/ %>
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteCuentaCorrienteBean.pagina.lastPage!=1 && refReporteCuentaCorrienteBean.pagina.totalElements!=0 && refReporteCuentaCorrienteBean.pagina.numpage != refReporteCuentaCorrienteBean.pagina.lastPage && refReporteCuentaCorrienteBean.mostrarLista}" actionListener="#{refReporteCuentaCorrienteBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteCuentaCorrienteBean.pagina.numpage == refReporteCuentaCorrienteBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteCuentaCorrienteBean.pagina.lastPage!=1 && refReporteCuentaCorrienteBean.pagina.totalElements!=0 && refReporteCuentaCorrienteBean.pagina.numpage != refReporteCuentaCorrienteBean.pagina.lastPage && refReporteCuentaCorrienteBean.mostrarLista}" actionListener="#{refReporteCuentaCorrienteBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteCuentaCorrienteBean.pagina.numpage == refReporteCuentaCorrienteBean.pagina.lastPage}"/>			   		
		  
		 </h:column>
		 
		 
		 
		 <h:column rendered="#{sessionScope.usuario.tipo==1}">
		   		<h:outputLink  rendered="#{refReporteCuentaCorrienteBean.nombreArchivo!='' && refReporteCuentaCorrienteBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteCuentaCorrienteBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_excel_cta_cte_simple_label}" />
				</h:outputLink>	
		 </h:column>			 
		 </h:panelGrid>
		 
		<%/*MANEJO DE ERROR */%>
		
		
		<% /* LISTADO */ %>		
		<t:div rendered="#{refReporteCuentaCorrienteBean.mostrarLista}">
		<h:panelGrid width="95%" columns="1" columnClasses="bordeblanco"  cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteCuentaCorrienteBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,columnaTablaTexto, columnaTablaNumero , columnaTablaTexto, columnaTablaNumero, columnaTablaCentrada, columnaTablaTexto,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaTexto,columnaTablaCentrada"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteCuentaCorrienteBean.mostrarLista}" >
					
					<h:column rendered="#{sessionScope.usuario.tipo==0}">
						<f:facet name="header">
							<h:outputText value="#{Message.cod_cliente_Alfa}"/>
						</f:facet>
						<h:outputText value="#{item.codClienteAlfa}"/>
					</h:column>	
							 
					<h:column rendered="#{sessionScope.usuario.tipo==0}">
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion}"/>
					</h:column>	
					
					 
					<h:column rendered="#{sessionScope.usuario.tipo==0}">
						<f:facet name="header">
							<h:outputText value="#{Message.limite_credito_label}"/>
						</f:facet>
						<h:outputText value="#{item.topeCredito}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.comprobante_label}"/>
						</f:facet>
						<h:outputText value="#{item.comprobanteDescripcion}" escape="false" />
						
						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.sucursal_label}"/>
						</f:facet>
						<h:outputText value="#{item.sucursal}" rendered="#{item.sucursal!=0}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_comp_label}"/>
						</f:facet>
						<h:outputText value="#{item.comprobanteNumero}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_label}"/>
						</f:facet>
						<h:outputText value="#{item.fecha}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.vencimiento_label}"/>
						</f:facet>
						<h:outputText value="#{item.vencimiento}"/>
					</h:column>		
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.total_pesos_label}"/>
						</f:facet>
						<h:outputText value="#{item.netoFactura}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.monto_adeudado_label}"/>
						</f:facet>
						<h:outputText value="#{item.importe}" >
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.estado_label}"/>
						</f:facet>
						<h:outputText value="#{item.estadoStr}"/>
					</h:column>				
								
				</t:dataTable>	
				</h:column>
		</h:panelGrid>
		
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>			
		</t:panelGrid>
		
		
		<h:panelGrid columns="2" width="60%" columnClasses="cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="2">						 
				<h:column>
						<t:outputText value="&nbsp;&nbsp;#{Message.saldo_cta_cte_label}:" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>
					    <t:inputText readonly="true" value="#{refReporteCuentaCorrienteBean.saldoCtaCte}" size="15"  styleClass="alinaerADerechoMonto" >
							<f:convertNumber type="currency" pattern="#,##0.00"/>
						</t:inputText>
				</h:column>
				<h:column>
						<t:outputText value="&nbsp;&nbsp;#{Message.remitos_a_facturar_label}:" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>						 
						<t:inputText readonly="true" value="#{refReporteCuentaCorrienteBean.remitosAfacturar}" size="15"  styleClass="alinaerADerechoMonto" >
							<f:convertNumber type="currency" pattern="#,##0.00"/>
						</t:inputText>						
						<t:outputText value="&nbsp;&nbsp;"  escape="false"/>
						<t:commandButton value="#{Message.ver_remitos_label}" actionListener="#{refReporteCuentaCorrienteBean.verRemitos}" styleClass="boton"/>	
				</h:column>
				
				<h:column>
					      <t:outputLabel for="nombre" styleClass="cpo8">
							<t:outputText value="&nbsp;&nbsp;#{Message.saldo_del_cliente}:"  escape="false"/>
						  </t:outputLabel>
				</h:column>	
				<h:column>				     
						<t:inputText readonly="true" value="#{refReporteCuentaCorrienteBean.saldoCliente}" size="15"  styleClass="alinaerADerechoMonto" >
							<f:convertNumber type="currency" pattern="#,##0.00"/>
						</t:inputText>
				</h:column>	
				
				<t:div rendered="#{refReporteCuentaCorrienteBean.cliente!=null && refReporteCuentaCorrienteBean.cliente!=-1}">
				<h:column >
					      <t:outputLabel for="nombre" styleClass="cpo8">
							<t:outputText value="&nbsp;&nbsp;#{Message.limite_credito_label}:"  escape="false"/>
						  </t:outputLabel>
				</h:column>	
				</t:div>
				
				<t:div rendered="#{refReporteCuentaCorrienteBean.cliente!=null && refReporteCuentaCorrienteBean.cliente!=-1}">
				<h:column >				     
						<t:inputText readonly="true" value="#{refReporteCuentaCorrienteBean.limiteCredito}" size="15"  styleClass="alinaerADerechoMonto" >
							<f:convertNumber type="currency" pattern="#,##0.00"/>
						</t:inputText>
				</h:column>
				</t:div>	
				
				<t:div rendered="#{refReporteCuentaCorrienteBean.cliente!=null && refReporteCuentaCorrienteBean.cliente!=-1}">
				<h:column >
					      <t:outputLabel for="nombre" styleClass="cpo8">
							<t:outputText value="&nbsp;&nbsp;#{Message.saldo_disponible_cliente_label}:"  escape="false"/>
						  </t:outputLabel>
				</h:column>	
				</t:div>
					
				<t:div rendered="#{refReporteCuentaCorrienteBean.cliente!=null && refReporteCuentaCorrienteBean.cliente!=-1}">
				<h:column >				     
						<t:inputText readonly="true" value="#{refReporteCuentaCorrienteBean.limiteCredito - refReporteCuentaCorrienteBean.saldoCliente}" size="15"  styleClass="alinaerADerechoMonto" >
							<f:convertNumber type="currency" pattern="#,##0.00"/>
						</t:inputText>
				</h:column>	
				</t:div>
				
			</h:panelGrid>
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
						 columns="1" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>			
			</t:panelGrid>
			
			<h:panelGrid columns="1" width="60%" columnClasses="campoNoEditable" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="2">						 
					<h:column>
							<t:outputText value="&nbsp;&nbsp;#{Message.composicion_ctas_ctes_label}" styleClass="campoNoEditable" escape="false"/>
					</h:column>					
			</h:panelGrid>
			
			<h:panelGrid width="60%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
				<h:column>		
				<t:dataTable value="#{refReporteCuentaCorrienteBean.lstComposicionCtasCtes}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 >
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.recibo_a_cuenta}"/>
						</f:facet>
						<h:outputText value="#{item.recibidoACuentas}" escape="false" >
						<f:convertNumber type="currency" pattern="#,##0.00"/>
						</h:outputText>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.valores_posdatados_label}"/>
						</f:facet>
						<h:outputText value="#{item.saldoChequeNoCobrado}" escape="false" >
						<f:convertNumber type="currency" pattern="#,##0.00"/>
						</h:outputText>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.vencido_30_dias}"/>
						</f:facet>
						<h:outputText value="#{item.vencidoA30Dias}">
						<f:convertNumber type="currency" pattern="#,##0.00"/>
						</h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.vencido_mas_30_dias}"/>
						</f:facet>
						<h:outputText value="#{item.vencidoMas30Dias}">
						<f:convertNumber type="currency" pattern="#,##0.00"/>
						</h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.a_vence}"/>
						</f:facet>
						<h:outputText value="#{item.noVencidas}">
						<f:convertNumber type="currency" pattern="#,##0.00"/>
						</h:outputText>
					</h:column>
						
					
					
				</t:dataTable>	
				</h:column>
		</h:panelGrid>
		</t:div>	
			<h:panelGrid columns="1" width="80%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteCuentaCorrienteBean.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		</h:column>
	</h:panelGrid>
	
	
	
	
		 <t:saveState value="#{refReporteCuentaCorrienteBean.cliente}"></t:saveState>
		<t:saveState value="#{refReporteCuentaCorrienteBean.clientes}"></t:saveState>
		<t:saveState value="#{refReporteCuentaCorrienteBean.limiteCredito}"></t:saveState>
		
		<t:saveState value="#{refReporteCuentaCorrienteBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteCuentaCorrienteBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteCuentaCorrienteBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteCuentaCorrienteBean.nombreArchivo}"></t:saveState>
		<t:saveState value="#{refReporteCuentaCorrienteBean.nombreArchivoSimple}"></t:saveState>	
		<t:saveState value="#{refReporteCuentaCorrienteBean.remitosAfacturar}"></t:saveState>
		<t:saveState value="#{refReporteCuentaCorrienteBean.saldoCtaCte}"></t:saveState>
		<t:saveState value="#{refReporteCuentaCorrienteBean.saldoCliente}"></t:saveState>
		<t:saveState value="#{refReporteCuentaCorrienteBean.pagina}"></t:saveState>
		<t:saveState value="#{refReporteCuentaCorrienteBean.items}"></t:saveState>
			<t:saveState value="#{refReporteCuentaCorrienteBean.lstComposicionCtasCtes}"></t:saveState>
	</h:form>
	
	<% /*LISTADO DE REMITOS*/ %>
	<h:form id="frmFiltroCuentaSecundario" rendered="#{refReporteCuentaCorrienteBean.mostrarFrmListaSecundaria}">	
	
		<c:if var="puedeIngresarS" test="${!refReporteCuentaCorrienteBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if> 	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>   		
				
		 <t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_cta_cte_remitos}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION 	 */%>
		
		 
		 <t:saveState value="#{refReporteCuentaCorrienteBean.pagina}"></t:saveState>
		 <t:saveState value="#{refReporteCuentaCorrienteBean.paginaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteCuentaCorrienteBean.mostrarLista}"></t:saveState>
		 <t:saveState value="#{refReporteCuentaCorrienteBean.mostrarListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteCuentaCorrienteBean.mostrarFrmLista}"></t:saveState>
		 <t:saveState value="#{refReporteCuentaCorrienteBean.mostrarFrmListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteCuentaCorrienteBean.nombreArchivoSecundario}"></t:saveState>
		  
		    
		 
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>  	 
	   		
		   		<h:commandButton rendered="#{refReporteCuentaCorrienteBean.paginaSecundaria.lastPage!=1 && refReporteCuentaCorrienteBean.paginaSecundaria.totalElements!=0 && refReporteCuentaCorrienteBean.paginaSecundaria.numpage != 1 && refReporteCuentaCorrienteBean.mostrarListaSecundaria}" actionListener="#{refReporteCuentaCorrienteBean.primeraSecundaria}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteCuentaCorrienteBean.paginaSecundaria.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteCuentaCorrienteBean.anteriorSecundaria}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteCuentaCorrienteBean.paginaSecundaria.numpage == 1}" rendered="#{refReporteCuentaCorrienteBean.paginaSecundaria.lastPage!=1 && refReporteCuentaCorrienteBean.paginaSecundaria.totalElements!=0  && refReporteCuentaCorrienteBean.paginaSecundaria.numpage != 1 && refReporteCuentaCorrienteBean.mostrarListaSecundaria}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteCuentaCorrienteBean.mostrarListaSecundaria}" value="#{refReporteCuentaCorrienteBean.paginaSecundaria.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteCuentaCorrienteBean.paginaSecundaria.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteCuentaCorrienteBean.mostrarListaSecundaria}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteCuentaCorrienteBean.mostrarListaSecundaria}" value="#{refReporteCuentaCorrienteBean.paginaSecundaria.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteCuentaCorrienteBean.nombreArchivoSecundario!='' && refReporteCuentaCorrienteBean.mostrarListaSecundaria}" target="_blank" styleClass="cpo7b" value="#{refReporteCuentaCorrienteBean.nombreArchivoSecundario}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteCuentaCorrienteBean.paginaSecundaria.lastPage!=1 && refReporteCuentaCorrienteBean.paginaSecundaria.totalElements!=0 && refReporteCuentaCorrienteBean.paginaSecundaria.numpage != refReporteCuentaCorrienteBean.paginaSecundaria.lastPage && refReporteCuentaCorrienteBean.mostrarListaSecundaria}" actionListener="#{refReporteCuentaCorrienteBean.siguienteSecundaria}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteCuentaCorrienteBean.paginaSecundaria.numpage == refReporteCuentaCorrienteBean.paginaSecundaria.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteCuentaCorrienteBean.paginaSecundaria.lastPage!=1 && refReporteCuentaCorrienteBean.paginaSecundaria.totalElements!=0 && refReporteCuentaCorrienteBean.paginaSecundaria.numpage != refReporteCuentaCorrienteBean.paginaSecundaria.lastPage && refReporteCuentaCorrienteBean.mostrarListaSecundaria}" actionListener="#{refReporteCuentaCorrienteBean.ultimaSecundaria}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteCuentaCorrienteBean.paginaSecundaria.numpage == refReporteCuentaCorrienteBean.paginaSecundaria.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
	
		 
		
		<% /* LISTADO */ %>
		<t:div  style="overflow:scroll; height:100%; width:700px;"  >  
	
		
		<h:panelGrid width="90%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteCuentaCorrienteBean.subItemsNivel2}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto, columnaTablaTexto,  columnaTablaTexto, columnaTablaTexto, columnaTablaTexto,columnaTablaNumero,columnaTablaNumero, columnaTablaTexto, columnaTablaNumero,columnaTablaNumero,columnaTablaNumero, columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteCuentaCorrienteBean.mostrarListaSecundaria}" >				
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_label}"/>
						</f:facet>
						<h:outputText value="#{item.fecha} " >						
							
						</h:outputText>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.hora_label}"/>
						</f:facet>
						<h:outputText value="#{item.hora}" >						
						</h:outputText>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.ccss_label}"/>							
						</f:facet>
							<h:outputText value="#{item.ccss}"/>
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cod_cliente_Alfa}"/>
						</f:facet>
						<h:outputText value="#{item.codClienteAlfa} " />
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion} " />
						
					</h:column>	
					
										
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.sucursal_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroSucursal} " />
						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_remito_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroRemito}"/>
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.producto_label}"/>
						</f:facet>
						<h:outputText value="#{item.descProducto}"/>
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cantidad_label}"/>
						</f:facet>
						<h:outputText value="#{item.litros}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>		
							
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.precio_litro_label}"/>
						</f:facet>
						<h:outputText value="#{item.precioConImpuestos}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.total_pesos_label}"/>
						</f:facet>
						<h:outputText value="#{item.montoTotal}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
						
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.patente_label}"/>
						</f:facet>
						<h:outputText value="#{item.patente}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.chofer_label}"/>
						</f:facet>
						<h:outputText value="#{item.apellidoChofer}&nbsp; #{item.nombreChofer}" escape="false"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">							
							<h:outputText value="#{Message.un_chofer_label}"/>
						</f:facet>
						<h:outputText value="#{item.descrGrupoUNC}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.un_camion_label}"/>							
						</f:facet>
							<h:outputText value="#{item.descrGrupoUNV}"/>
					</h:column>	
									
					
					
								
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		
		</t:div>
			<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" actionListener="#{refReporteCuentaCorrienteBean.volverPrincipal}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
				
		</h:column>
	</h:panelGrid>	
	</h:form>	
		
	
	<t:panelGrid>
		<t:column>
			<h:outputText value="&nbsp;" escape="false"/>
		</t:column>
		<t:column>
			<h:outputText value="&nbsp;" escape="false"/>
		</t:column>
		<t:column>
			<h:outputText value="&nbsp;" escape="false"/>
		</t:column>
	</t:panelGrid>		
		
	<%@ include file="../footer.jsp" %>
	
	</body>	
</f:view>
</html> 
						