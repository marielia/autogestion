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
		
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteAsientosConDiferencias.mostrarFrmLista}">	
		<t:saveState value="#{refReporteAsientosConDiferencias.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteAsientosConDiferencias.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_asiento_con_diferencia}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>		
				
		<%/*FILTROS*/%>		
			<h:panelGrid columns="4" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column></h:column>
				<h:column></h:column>
				<h:column></h:column>									
			</h:panelGrid>
			
					
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8-30b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
		
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.desde_asiento_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:inputText id="clientedesde" value="#{refReporteAsientosConDiferencias.fltNroAsientoDesde}"  size="12" maxlength="10" styleClass="campo">						
					</h:inputText>
					
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.al_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>							
					
					<h:inputText id="clientehasta" value="#{refReporteAsientosConDiferencias.fltNroAsientoHasta}"  size="12" maxlength="10" styleClass="campo">						
					</h:inputText>				
				</t:column>	
				
				<%/* CENTRO DE SERVICIO CCSS 
				*/ %>
				<t:column>
					<h:outputLabel  styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;#{Message.centro_servicio_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:selectOneListbox id="ccss" size="1" value="#{refReporteAsientosConDiferencias.ccss}" styleClass="campo">
						<f:selectItems value="#{refReporteAsientosConDiferencias.lstccss}" />
					</h:selectOneListbox>				
				</t:column>	
													
			</h:panelGrid>
						
			
			<h:panelGrid columns="4" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>	</h:column>
				<h:column>	</h:column>
				<h:column>	</h:column>				
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteAsientosConDiferencias.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteAsientosConDiferencias.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteAsientosConDiferencias.pagina.lastPage!=1 && refReporteAsientosConDiferencias.pagina.totalElements!=0 && refReporteAsientosConDiferencias.pagina.numpage != 1 && refReporteAsientosConDiferencias.mostrarLista}" actionListener="#{refReporteAsientosConDiferencias.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteAsientosConDiferencias.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteAsientosConDiferencias.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteAsientosConDiferencias.pagina.numpage == 1}" rendered="#{refReporteAsientosConDiferencias.pagina.lastPage!=1 && refReporteAsientosConDiferencias.pagina.totalElements!=0  && refReporteAsientosConDiferencias.pagina.numpage != 1 && refReporteAsientosConDiferencias.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteAsientosConDiferencias.mostrarLista}" value="#{refReporteAsientosConDiferencias.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteAsientosConDiferencias.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteAsientosConDiferencias.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteAsientosConDiferencias.mostrarLista}" value="#{refReporteAsientosConDiferencias.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteAsientosConDiferencias.nombreArchivo!='' && refReporteAsientosConDiferencias.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteAsientosConDiferencias.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteAsientosConDiferencias.pagina.lastPage!=1 && refReporteAsientosConDiferencias.pagina.totalElements!=0 && refReporteAsientosConDiferencias.pagina.numpage != refReporteAsientosConDiferencias.pagina.lastPage && refReporteAsientosConDiferencias.mostrarLista}" actionListener="#{refReporteAsientosConDiferencias.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteAsientosConDiferencias.pagina.numpage == refReporteAsientosConDiferencias.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteAsientosConDiferencias.pagina.lastPage!=1 && refReporteAsientosConDiferencias.pagina.totalElements!=0 && refReporteAsientosConDiferencias.pagina.numpage != refReporteAsientosConDiferencias.pagina.lastPage && refReporteAsientosConDiferencias.mostrarLista}" actionListener="#{refReporteAsientosConDiferencias.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteAsientosConDiferencias.pagina.numpage == refReporteAsientosConDiferencias.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 
		<t:saveState value="#{refReporteAsientosConDiferencias.ccss}"></t:saveState>
		<t:saveState value="#{refReporteAsientosConDiferencias.lstccss}"></t:saveState>		 
		<t:saveState value="#{refReporteAsientosConDiferencias.fltNroAsientoDesde}"></t:saveState>
		<t:saveState value="#{refReporteAsientosConDiferencias.fltNroAsientoHasta}"></t:saveState>
		<t:saveState value="#{refReporteAsientosConDiferencias.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteAsientosConDiferencias.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteAsientosConDiferencias.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteAsientosConDiferencias.nombreArchivo}"></t:saveState>
			
		<% /* LISTADO */%>
		
		<h:panelGrid width="90%" columns="1" columnClasses="bordeblanco" cellspacing="0" cellpadding="0" >						 
		<h:column>		
		
				<t:dataTable value="#{refReporteAsientosConDiferencias.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero,columnaTablaNumero, columnaTablaNumero, columnaTablaNumero"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteAsientosConDiferencias.mostrarLista}" >
					
										
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.ccss_label}"/>
						</f:facet>
						<h:outputText value="#{item.ccssDesc}" />
					</h:column>						
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_ejercicio_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroEjercicio}" />						
					</h:column>					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_asiento_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroAsiento}" />
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.debe_label}"/>
						</f:facet>
						
						<h:outputText value="#{item.valorDebe}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.haber_label}"/>
						</f:facet>
						<h:outputText value="#{item.valorHaber}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.monto_adeudado_label}"/>
						</f:facet>
						 <h:outputText value="#{item.saldo}" >
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>					
					</h:column>				
							
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
				
			
				
				
			<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteAsientosConDiferencias.volver}" styleClass="boton"/>	
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
						