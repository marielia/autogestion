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
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteRendicionPendienteBean.mostrarFrmLista}" >	
		<t:saveState value="#{refReporteRendicionPendienteBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteRendicionPendienteBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>		
					
			 
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>			
			<t:column>
				<t:outputText value="#{Message.tit_informe_rendiciones_pendientes}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
			
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>			
		
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>
		   		<h:commandButton rendered="#{refReporteRendicionPendienteBean.pagina.lastPage!=1 && refReporteRendicionPendienteBean.pagina.totalElements!=0 && refReporteRendicionPendienteBean.pagina.numpage != 1 && refReporteRendicionPendienteBean.mostrarLista}" actionListener="#{refReporteRendicionPendienteBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteRendicionPendienteBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteRendicionPendienteBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteRendicionPendienteBean.pagina.numpage == 1}" rendered="#{refReporteRendicionPendienteBean.pagina.lastPage!=1 && refReporteRendicionPendienteBean.pagina.totalElements!=0  && refReporteRendicionPendienteBean.pagina.numpage != 1 && refReporteRendicionPendienteBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteRendicionPendienteBean.mostrarLista}" value="#{refReporteRendicionPendienteBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteRendicionPendienteBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteRendicionPendienteBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteRendicionPendienteBean.mostrarLista}" value="#{refReporteRendicionPendienteBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteRendicionPendienteBean.nombreArchivo!='' && refReporteRendicionPendienteBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteRendicionPendienteBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteRendicionPendienteBean.pagina.lastPage!=1 && refReporteRendicionPendienteBean.pagina.totalElements!=0 && refReporteRendicionPendienteBean.pagina.numpage != refReporteRendicionPendienteBean.pagina.lastPage && refReporteRendicionPendienteBean.mostrarLista}" actionListener="#{refReporteRendicionPendienteBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteRendicionPendienteBean.pagina.numpage == refReporteRendicionPendienteBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteRendicionPendienteBean.pagina.lastPage!=1 && refReporteRendicionPendienteBean.pagina.totalElements!=0 && refReporteRendicionPendienteBean.pagina.numpage != refReporteRendicionPendienteBean.pagina.lastPage && refReporteRendicionPendienteBean.mostrarLista}" actionListener="#{refReporteRendicionPendienteBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteRendicionPendienteBean.pagina.numpage == refReporteRendicionPendienteBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>	
		
		
		<% /* LISTADO */ %>		
		<h:panelGrid width="80%" columns="1" columnClasses="bordeblanco"  cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteRendicionPendienteBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto, columnaTablaNumero , columnaTablaNumero,  columnaTablaCentrada, columnaTablaTexto,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaTexto,columnaTablaCentrada"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteRendicionPendienteBean.mostrarLista}" >
							 
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.centros_de_servicios_label}"/>
						</f:facet>
						<h:outputText value="#{item.descrCCSS}" escape="false" />
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.importe_pendiente_label}"/>
						</f:facet>
						<h:outputText value="#{item.importe}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.dias_pendientes_label}"/>
						</f:facet>
						<h:outputText value="#{item.cantidadDias}" rendered="#{item.cantidadDias!=-1}"/>
						<h:outputText value="#{Message.no_info_label}" rendered="#{item.cantidadDias==-1}"/>
					</h:column>
					
					<%/* ver */%>
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.facturas_label}"/>
						</f:facet>						     						
						 <t:commandLink styleClass="linkOperacion" 
									   actionListener="#{refReporteRendicionPendienteBean.verTodosLosDatos}" 								  
									   title="Ver Facturas pendientes de rendición." >
									   <f:param name="codCCSSparam" id="codCCSSparam" value="#{item.codCCSS}"/>									   
									   <h:outputText value="#{Message.ver_label}" /> 
						</t:commandLink>						
					</h:column>					
								
				</t:dataTable>	
				</h:column>
		</h:panelGrid>
		
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>			
		</t:panelGrid>
		
		
		
			
			
			
			<h:panelGrid columns="1" width="80%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteRendicionPendienteBean.volver}" styleClass="boton"/>	
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
				
		</h:column>
	</h:panelGrid>
	
	 
		<t:saveState value="#{refReporteRendicionPendienteBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteRendicionPendienteBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteRendicionPendienteBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteRendicionPendienteBean.nombreArchivo}"></t:saveState>	
		<t:saveState value="#{refReporteRendicionPendienteBean.pagina}"></t:saveState>	
	</h:form>
	
	<%/* lista de facturas pendientes de rendicion*/ %>	
	<h:form id="frmFiltroCuentaSecundario" rendered="#{refReporteRendicionPendienteBean.mostrarFrmListaSecundaria}">	
	
		<c:if var="puedeIngresarS" test="${!refReporteRendicionPendienteBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		</c:if> 	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>   		
				
		 <t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_rendiciones_pendientes_facturas}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION 	 */%>
		
		 
		 <t:saveState value="#{refReporteRendicionPendienteBean.pagina}"></t:saveState>
		 <t:saveState value="#{refReporteRendicionPendienteBean.paginaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteRendicionPendienteBean.mostrarLista}"></t:saveState>
		 <t:saveState value="#{refReporteRendicionPendienteBean.mostrarListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteRendicionPendienteBean.mostrarFrmLista}"></t:saveState>
		 <t:saveState value="#{refReporteRendicionPendienteBean.mostrarFrmListaSecundaria}"></t:saveState>
		 <t:saveState value="#{refReporteRendicionPendienteBean.nombreArchivoSecundario}"></t:saveState>
	     
		 
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>  	 
	   		
		   		<h:commandButton rendered="#{refReporteRendicionPendienteBean.paginaSecundaria.lastPage!=1 && refReporteRendicionPendienteBean.paginaSecundaria.totalElements!=0 && refReporteRendicionPendienteBean.paginaSecundaria.numpage != 1 && refReporteRendicionPendienteBean.mostrarListaSecundaria}" actionListener="#{refReporteRendicionPendienteBean.primeraSecundaria}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteRendicionPendienteBean.paginaSecundaria.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteRendicionPendienteBean.anteriorSecundaria}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteRendicionPendienteBean.paginaSecundaria.numpage == 1}" rendered="#{refReporteRendicionPendienteBean.paginaSecundaria.lastPage!=1 && refReporteRendicionPendienteBean.paginaSecundaria.totalElements!=0  && refReporteRendicionPendienteBean.paginaSecundaria.numpage != 1 && refReporteRendicionPendienteBean.mostrarListaSecundaria}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteRendicionPendienteBean.mostrarListaSecundaria}" value="#{refReporteRendicionPendienteBean.paginaSecundaria.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteRendicionPendienteBean.paginaSecundaria.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteRendicionPendienteBean.mostrarListaSecundaria}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteRendicionPendienteBean.mostrarListaSecundaria}" value="#{refReporteRendicionPendienteBean.paginaSecundaria.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteRendicionPendienteBean.nombreArchivoSecundario!='' && refReporteRendicionPendienteBean.mostrarListaSecundaria}" target="_blank" styleClass="cpo7b" value="#{refReporteRendicionPendienteBean.nombreArchivoSecundario}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteRendicionPendienteBean.paginaSecundaria.lastPage!=1 && refReporteRendicionPendienteBean.paginaSecundaria.totalElements!=0 && refReporteRendicionPendienteBean.paginaSecundaria.numpage != refReporteRendicionPendienteBean.paginaSecundaria.lastPage && refReporteRendicionPendienteBean.mostrarListaSecundaria}" actionListener="#{refReporteRendicionPendienteBean.siguienteSecundaria}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteRendicionPendienteBean.paginaSecundaria.numpage == refReporteRendicionPendienteBean.paginaSecundaria.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteRendicionPendienteBean.paginaSecundaria.lastPage!=1 && refReporteRendicionPendienteBean.paginaSecundaria.totalElements!=0 && refReporteRendicionPendienteBean.paginaSecundaria.numpage != refReporteRendicionPendienteBean.paginaSecundaria.lastPage && refReporteRendicionPendienteBean.mostrarListaSecundaria}" actionListener="#{refReporteRendicionPendienteBean.ultimaSecundaria}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteRendicionPendienteBean.paginaSecundaria.numpage == refReporteRendicionPendienteBean.paginaSecundaria.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
	
		 
		
		<% /* LISTADO */ %>
		
		<t:div  style="overflow:scroll; height:100%; width:700px;"  rendered="#{refReporteRendicionPendienteBean.mostrarListaSecundaria}">  		
		<h:panelGrid width="90%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteRendicionPendienteBean.subItemsNivel2}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto, columnaTablaTexto,  columnaTablaTexto, columnaTablaTexto, columnaTablaTexto,columnaTablaNumero,columnaTablaNumero, columnaTablaTexto, columnaTablaNumero,columnaTablaNumero,columnaTablaNumero, columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteRendicionPendienteBean.mostrarListaSecundaria}" >				
					
					
				<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_label}"/>
						</f:facet>
						<h:outputText value="#{item.fecha} " />
						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.tipo_label}"/>
						</f:facet>
						<h:outputText value="#{item.tipo}"/>
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cod_cliente_Alfa}"/>
						</f:facet>
						<h:outputText value="#{item.codClienteAlfa}"/>
					</h:column>	
					
					<h:column >
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.centro_servicio_label}"/>
						</f:facet>
						<h:outputText value="#{item.ccss}"/>
					</h:column>	
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.prefijo_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroSucursal}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.numero_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroFactura}"/>
						
					</h:column>		
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.monto_label}"/>
						</f:facet>
						<h:outputText value="#{item.netoFactura}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.monto_tot_label}"/>
						</f:facet>
						<h:outputText value="#{item.pagoAplicado}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.monto_adeudado_label}"/>
						</f:facet>
						<h:outputText value="#{item.saldo}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
						
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_vto_label}"/>
						</f:facet>
						<h:outputText value="#{item.feVto}">
						 
						 </h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.condicion_label}"/>
						</f:facet>
						<h:outputText value="#{item.tipoPagoFactura}"> </h:outputText>
					</h:column>					
								
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		
		</t:div>
		
		
			<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" actionListener="#{refReporteRendicionPendienteBean.volverPrincipal}" styleClass="boton"/>	
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
						