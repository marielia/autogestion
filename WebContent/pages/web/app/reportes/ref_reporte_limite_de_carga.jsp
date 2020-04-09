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
		<title><h:outputText value="#{Message.nombre_sistema}"/></title>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/refinor.css">		
		<script type="text/javascript" src="<h:outputText value="#{Message.contexto_sistema}"/>/js/comun/jsutiles.js"></script>				
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" >
		<%@ include file="../header.jsp" %>				
		
		<h:form id="frmLimiteDeCarga">
			<t:saveState value="#{refReporteLimiteDeCargaBean.pagina}"></t:saveState>
		  	<c:if test="${!refReporteLimiteDeCargaBean.puedeIngresar}">
				<c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
			</c:if>	  
		
			<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" columnClasses="columnaTablaCentrada">
				<!--  TITULO  -->	
				<h:column>	
					<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
							 columns="1"  columnClasses="titulosGrande" width="100%">
						<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
						<t:column>
							<t:outputText value="#{Message.tit_limite_de_carga_de_cliente}"/>
						</t:column>
						<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
					</t:panelGrid>
					
					<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
							 columns="1" rowClasses="nada" width="100%">
						<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
					</t:panelGrid>
					
					<h:messages styleClass="errorNegro"/>
			  	
			 		<t:panelGrid columns="1" width="100%" columnClasses="cpo8Center" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">
						<t:column>
							<t:outputLabel styleClass="titulos">
								<h:outputText  value="#{Message.limite_de_cliente_label}" escape="false" />
							</t:outputLabel>
						</t:column>				
					</t:panelGrid>
					
					<t:panelGrid border="0" cellpadding="4" cellspacing="0" 
						columns="6" columnClasses="cpo8, cpo8, cpo8, cpo8, cpo8, cpo8" width="100%" rowClasses="filaTablaCabecera">									
						
						<%/*  Primera fila  */%>
						<t:column>							
							<t:outputLabel  styleClass="datoNegrita">
								<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;#{Message.cliente_label}&nbsp;:" escape="false" />							
							</t:outputLabel>		
						</t:column>
												
						<t:column>
							<t:outputLabel styleClass="datoNegrita">
								<t:outputText  value="#{refReporteLimiteDeCargaBean.codigo}&nbsp;&nbsp;#{refReporteLimiteDeCargaBean.nombreCliente}" escape="false" />
							</t:outputLabel>
						</t:column>
						
						<t:column/>
						<t:column/>
						<t:column/>
						<t:column/>
						
						<%/*  Segunda fila  */%>
						<t:column>
							<t:outputLabel styleClass="datoNegrita">
								<t:outputText  value="&nbsp;&nbsp;&nbsp;&nbsp;#{Message.tope_label}&nbsp;:" escape="false" />
							</t:outputLabel>
						</t:column>
						
						<t:column>
							<t:outputLabel styleClass="datoNegrita">
								<t:outputText  value="#{refReporteLimiteDeCargaBean.tope}" escape="false" />
							</t:outputLabel>
						</t:column>
						
						<t:column>
							<t:outputLabel styleClass="datoNegrita">
								<t:outputText  value="#{Message.utilizado_label}&nbsp;:" escape="false" />
							</t:outputLabel>
						</t:column>
						
						<t:column>
							<t:outputLabel styleClass="datoNegrita">
								<t:outputText  value="#{refReporteLimiteDeCargaBean.utilizadoCliente}" escape="false" />
							</t:outputLabel>
						</t:column>
						
						<t:column>
							<t:outputLabel styleClass="datoNegrita">
								<t:outputText  value="#{Message.disponible_label}&nbsp;:" escape="false" />
							</t:outputLabel>
						</t:column>
						
						<t:column>
							<t:outputLabel styleClass="datoNegrita">
								<t:outputText  value="#{refReporteLimiteDeCargaBean.disponible}" escape="false" />
							</t:outputLabel>
						</t:column>
					</t:panelGrid>
					
					<t:panelGrid columns="1" width="100%" columnClasses="cpo8Center" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">
						<t:column>
							<t:outputLabel styleClass="titulos">
								<h:outputText  value="#{Message.limite_de_carga_label}" escape="false" />
							</t:outputLabel>
						</t:column>				
					</t:panelGrid>
	
					<t:panelGrid border="0" cellpadding="4" cellspacing="0" columns="1" columnClasses="cpo8" width="100%" 
						rowClasses="filaTablaCabecera">
						<t:column>							
							<t:outputLabel  styleClass="datoNegrita">
								<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;#{Message.patente_label}&nbsp;:" escape="false" />							
							</t:outputLabel>	
							<t:outputLabel  styleClass="datoNegrita">
								<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;#{refReporteLimiteDeCargaBean.dominio}" escape="false" />							
							</t:outputLabel>		
						</t:column>
					</t:panelGrid>
					
					<% /* LISTADO */ %>
					<h:panelGrid width="100%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
						<h:column>		
							<t:dataTable value="#{refReporteLimiteDeCargaBean.subItemsNivel1}" var="item" 
										 rowClasses="fila1, fila2" 
										 columnClasses="columnaTablaTexto, columnaTablaTexto, columnaTablaTexto, columnaTablaNumero, columnaTablaNumero, columnaTablaNumero"
										 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
										 cellspacing="1" border="0" >
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{Message.agrupado_label}"/>
									</f:facet>
									<h:outputText value="#{item.agrupado}"/>
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{Message.descripcion_label}"/>
									</f:facet>
									<h:outputText value="#{item.descripcion} " />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{Message.limite_label}"/>
									</f:facet>
									<h:outputText value="#{item.limite}"/>
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{Message.litros_label}"/>
									</f:facet>
									<h:outputText value="#{item.litros}"/>
								</h:column>							
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{Message.utilizado_label}"/>
									</f:facet>
									<h:outputText value="#{item.utilizado}"/>
								</h:column>							
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{Message.permitido_label}"/>
									</f:facet>
									<h:outputText value="#{item.permitido}"/>
								</h:column>			
							</t:dataTable>	
						</h:column>
					</h:panelGrid>
		
					<h:panelGrid columns="1" width="100%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
						<h:column>
							<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
						     	<h:column>				    		
						    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteLimiteDeCargaBean.volver}" styleClass="boton"/>	
						    	</h:column>
						 	</h:panelGrid> 
				 		</h:column>				 	
					</h:panelGrid>
				</h:column>
			</h:panelGrid>
			
		</h:form>		
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
				 columns="1"   width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>				
		</t:panelGrid>
		<%@ include file="../footer.jsp" %>
		
	</body>	
</f:view>
</html> 