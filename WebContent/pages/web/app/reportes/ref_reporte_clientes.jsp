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
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0">
		<%@ include file="../header.jsp" %>
		
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteClientesBean.mostrarFrmLista}">	
		<t:saveState value="#{refReporteClientesBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteClientesBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_clientes}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>		
				
		<%/*FILTROS*/%>		
			<h:panelGrid columns="2" width="90%" columnClasses="cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
			<h:column>
				<t:outputLabel for="consulte" styleClass="titulos">
					<h:outputText  value="&nbsp;&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
				</t:outputLabel>
			</h:column>	
			<h:column></h:column>			
			</h:panelGrid>
			
			
			
			
			<h:panelGrid columns="4" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<t:column>							
						<t:outputText styleClass="cpo8" value="#{Message.cliente_label}" escape="false" />							
				</t:column>
				<t:column>
					<h:selectOneListbox id="cliente" size="1" value="#{refReporteClientesBean.cliente}" styleClass="campo"  
						  immediate="true" >
						<f:selectItems value="#{refReporteClientesBean.clientes}" />
					</h:selectOneListbox>						
				</t:column>				
				
				<h:column>
						<t:outputText value="#{Message.cod_cliente_alfanumerico}" styleClass="cpo8" escape="false"/>
				</h:column>
				<h:column>	
						<t:inputText id="codClienteAlfa" value="#{refReporteClientesBean.fltCodClienteAlfa}" size="20" maxlength="20" styleClass="campo" />
				</h:column>
				
				
				<h:column>
					      <t:outputLabel for="dni" styleClass="cpo8">
							<t:outputText value="#{Message.nombre_cliente}"  escape="false"/>
						  </t:outputLabel>
				</h:column>	
				<h:column>				     
						<t:inputText id="dni" value="#{refReporteClientesBean.nomdCliente}" size="20" maxlength="20" styleClass="campo" />
				</h:column>	
				
				<h:column>
					      <t:outputLabel for="nombre" styleClass="cpo8">
							<t:outputText value="#{Message.cuit_label}"  escape="false"/>
						  </t:outputLabel>
				</h:column>	
				<h:column>				     
						<t:inputText id="cuitCliente" value="#{refReporteClientesBean.fltCUIT}" size="20" maxlength="20" styleClass="campo" />
				</h:column>	
				
				<t:column>
					<h:outputLabel for="estado" styleClass="cpo8">
						<h:outputText  value="#{Message.estado_label}" escape="false"/>
					</h:outputLabel>
				</t:column>
				<t:column> 
					<h:outputText  value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false"/>		
					<h:selectOneRadio  value="#{refReporteClientesBean.estado}" styleClass="campoNoEditable">
					     <f:selectItems  value="#{refReporteClientesBean.estados}"/>
					</h:selectOneRadio>								
				</t:column>	
				
				<t:column>					
				</t:column>
				<t:column> 												
				</t:column>									
			</h:panelGrid>	
			
			<h:panelGrid columns="4" width="90%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>	</h:column>
				<h:column>	</h:column>
				<h:column>	</h:column>				
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteClientesBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		<t:saveState value="#{refReporteClientesBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteClientesBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteClientesBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteClientesBean.nombreArchivo}"></t:saveState>
				
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteClientesBean.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteClientesBean.pagina.lastPage!=1 && refReporteClientesBean.pagina.totalElements!=0 && refReporteClientesBean.pagina.numpage != 1 && refReporteClientesBean.mostrarLista}" actionListener="#{refReporteClientesBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteClientesBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteClientesBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteClientesBean.pagina.numpage == 1}" rendered="#{refReporteClientesBean.pagina.lastPage!=1 && refReporteClientesBean.pagina.totalElements!=0  && refReporteClientesBean.pagina.numpage != 1 && refReporteClientesBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteClientesBean.mostrarLista}" value="#{refReporteClientesBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteClientesBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteClientesBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteClientesBean.mostrarLista}" value="#{refReporteClientesBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteClientesBean.nombreArchivo!='' && refReporteClientesBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteClientesBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteClientesBean.pagina.lastPage!=1 && refReporteClientesBean.pagina.totalElements!=0 && refReporteClientesBean.pagina.numpage != refReporteClientesBean.pagina.lastPage && refReporteClientesBean.mostrarLista}" actionListener="#{refReporteClientesBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteClientesBean.pagina.numpage == refReporteClientesBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteClientesBean.pagina.lastPage!=1 && refReporteClientesBean.pagina.totalElements!=0 && refReporteClientesBean.pagina.numpage != refReporteClientesBean.pagina.lastPage && refReporteClientesBean.mostrarLista}" actionListener="#{refReporteClientesBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteClientesBean.pagina.numpage == refReporteClientesBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 
		 
		
		<% /* LISTADO */ %>
		
		<h:panelGrid width="90%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteClientesBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,  columnaTablaTexto, columnaTablaNumero,columnaTablaNumero, columnaTablaTexto,  columnaTablaNumero, columnaTablaTexto,columnaTablaTexto,columnaTablaTexto, columnaTablaNumero, columnaTablaTexto,columnaTablaTexto,columnaTablaTexto,columnaTablaTexto"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0"
							 rendered="#{refReporteClientesBean.mostrarLista}" >
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cod_cliente_Alfa}"/>
						</f:facet>
						<h:outputText value="#{item.codClienteAlfa}" escape="false"/>
						
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.nombre} " />
						
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cuit_label}"/>
						</f:facet>
						<h:outputText value="#{item.cuit}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.limite_credito_label}"/>
						</f:facet>
						<h:outputText value="#{item.limiteCredito}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.domicilio_label}"/>
						</f:facet>
						<h:outputText value="#{item.domicilio}"/>
					</h:column>	
					
					
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cod_postal_label}"/>
						</f:facet>
						<h:outputText value="#{item.codPostal}"/>
					</h:column>	
					
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.localidad_label}"/>
						</f:facet>
						<h:outputText value="#{item.localidad}"/>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.provincia_cli_label}"/>
						</f:facet>
						<h:outputText value="#{item.provincia}"/>
					</h:column>	
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cat_iva_label}"/>
						</f:facet>
						<h:outputText value="#{item.categoriaIVA}"/>
					</h:column>	
					
					
					
					
							
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_iibb_label}"/>
						</f:facet>
						<h:outputText value="#{item.numeroIIBB}" rendered="#{item.numeroIIBB != '0'}"/>
					</h:column>						
						
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.inscr_iibb_label}"/>
						</f:facet>
						<h:outputText value="#{item.inscripcionIIBB}"  />
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cond_vta_label}"/>
						</f:facet>
						<h:outputText value="#{item.condicionVta}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.activo_label}"/>
						</f:facet>
						<h:outputText value="#{item.activoDesc}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_baja_label}"/>
						</f:facet>
						<h:outputText value="#{item.fechaBaja}"/>
					</h:column>
					
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		
		
		<h:panelGrid columns="1" width="90%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteClientesBean.volver}" styleClass="boton"/>	
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
						