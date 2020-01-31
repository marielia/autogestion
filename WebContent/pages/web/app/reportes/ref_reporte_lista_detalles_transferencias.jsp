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

		<script>
		/*
		function validar() { 				
				var cuit = document.getElementById('frmRegistracion:cuit');
				var email = document.getElementById('frmRegistracion:clave');

				if (cuit.value == '' ||					
					email.value == '') {
						alert('Debe completar los campos obligatorios.');
					return false;
				} else {
					if(emailCheck(email.value, false)) 
						return true;
					else
						email.focus();
						return false;	
				}			
				
				return true;
			}
			*/
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0">
		<%@ include file="../header.jsp" %>
		
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteListaDetalleTranferenciasBean.mostrarFrmLista}">	
		<t:saveState value="#{refReporteListaDetalleTranferenciasBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteListaDetalleTranferenciasBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  columnClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_lista_detalle_transferencias}"/>
			</t:column>
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>	
		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" rowClasses="nada" width="100%">			
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
		</t:panelGrid>		
				
		<%/*FILTROS*/%>		
			<h:panelGrid columns="4" width="77%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<t:outputLabel for="consulte" styleClass="titulos">
						<h:outputText  value="&nbsp;#{Message.consulte_por_label} " escape="false" styleClass="titulos"/>
					</t:outputLabel>
				</h:column>	
				<h:column></h:column>
				<h:column></h:column>
				<h:column></h:column>									
			</h:panelGrid>
			
			<h:panelGrid columns="2" width="77%" columnClasses="cpo8-30b,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
		
				<%/* CENTRO DE SERVICIO CCSS 
				*/ %>
				<t:column>
					<h:outputLabel  styleClass="cpo8">
						<h:outputText  value="&nbsp;&nbsp;&nbsp;#{Message.centro_servicio_label}&nbsp;&nbsp;" escape="false"/>
					</h:outputLabel>	
				</t:column>						
				
				<t:column>	
					<h:selectOneListbox id="ccss" size="1" value="#{refReporteListaDetalleTranferenciasBean.ccss}" 
					    styleClass="campo" onchange="submit();" immediate="true" 
					   valueChangeListener="#{refReporteListaDetalleTranferenciasBean.cargarSusRendiciones}" >
						<f:selectItems value="#{refReporteListaDetalleTranferenciasBean.lstccss}" />
					</h:selectOneListbox>
									
				</t:column>	
				
				<%/* RENDICION
				*/ %>
				<t:column>							
					<t:outputLabel  styleClass="cpo8">
						<t:outputText value="&nbsp;&nbsp;&nbsp;#{Message.nro_rendicion_label}&nbsp;&nbsp;" escape="false" />							
					</t:outputLabel>		
				</t:column>
				
				<t:column>
					<h:selectOneListbox id="rendicion" size="1" value="#{refReporteListaDetalleTranferenciasBean.rendicion}" styleClass="campo" immediate="true" >
						<f:selectItems value="#{refReporteListaDetalleTranferenciasBean.rendiciones}" />
					</h:selectOneListbox>	
					
				</t:column>	
				
									
			</h:panelGrid>			
				
			
			<h:panelGrid columns="4" width="77%" columnClasses="cpo8,cpo8,cpo8,cpo8" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>	
								<t:panelGrid border="0" cellpadding="" cellspacing="0" styleClass="columnaTablaTexto" 
										columns="1" columnClasses="cpo11-20" width="100%" >							
										<t:column>
											<t:div styleClass="cpo11">
												<t:outputText id="datosOblig" value="&nbsp;&nbsp;&nbsp;&nbsp;#{Message.datos_obligatorios_texto}"  styleClass="datoObligatorio" escape="false"/>
											</t:div>
										</t:column>										
								</t:panelGrid>
								</h:column>	
				<h:column>	</h:column>
						
				<h:column>
					<h:panelGrid columns="1" width="100%"  columnClasses="fondo" styleClass="columnaTablaNumero" cellspacing="0">						 
				     	<h:column>				     	
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteListaDetalleTranferenciasBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteListaDetalleTranferenciasBean.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteListaDetalleTranferenciasBean.pagina.lastPage!=1 && refReporteListaDetalleTranferenciasBean.pagina.totalElements!=0 && refReporteListaDetalleTranferenciasBean.pagina.numpage != 1 && refReporteListaDetalleTranferenciasBean.mostrarLista}" actionListener="#{refReporteListaDetalleTranferenciasBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteListaDetalleTranferenciasBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteListaDetalleTranferenciasBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteListaDetalleTranferenciasBean.pagina.numpage == 1}" rendered="#{refReporteListaDetalleTranferenciasBean.pagina.lastPage!=1 && refReporteListaDetalleTranferenciasBean.pagina.totalElements!=0  && refReporteListaDetalleTranferenciasBean.pagina.numpage != 1 && refReporteListaDetalleTranferenciasBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteListaDetalleTranferenciasBean.mostrarLista}" value="#{refReporteListaDetalleTranferenciasBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteListaDetalleTranferenciasBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteListaDetalleTranferenciasBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteListaDetalleTranferenciasBean.mostrarLista}" value="#{refReporteListaDetalleTranferenciasBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteListaDetalleTranferenciasBean.nombreArchivo!='' && refReporteListaDetalleTranferenciasBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteListaDetalleTranferenciasBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteListaDetalleTranferenciasBean.pagina.lastPage!=1 && refReporteListaDetalleTranferenciasBean.pagina.totalElements!=0 && refReporteListaDetalleTranferenciasBean.pagina.numpage != refReporteListaDetalleTranferenciasBean.pagina.lastPage && refReporteListaDetalleTranferenciasBean.mostrarLista}" actionListener="#{refReporteListaDetalleTranferenciasBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteListaDetalleTranferenciasBean.pagina.numpage == refReporteListaDetalleTranferenciasBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteListaDetalleTranferenciasBean.pagina.lastPage!=1 && refReporteListaDetalleTranferenciasBean.pagina.totalElements!=0 && refReporteListaDetalleTranferenciasBean.pagina.numpage != refReporteListaDetalleTranferenciasBean.pagina.lastPage && refReporteListaDetalleTranferenciasBean.mostrarLista}" actionListener="#{refReporteListaDetalleTranferenciasBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteListaDetalleTranferenciasBean.pagina.numpage == refReporteListaDetalleTranferenciasBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 <t:saveState value="#{refReporteListaDetalleTranferenciasBean.ccss}"></t:saveState>
		 <t:saveState value="#{refReporteListaDetalleTranferenciasBean.lstccss}"></t:saveState>
		 <t:saveState value="#{refReporteListaDetalleTranferenciasBean.rendicion}"></t:saveState>
		 <t:saveState value="#{refReporteListaDetalleTranferenciasBean.rendiciones}"></t:saveState>
		<t:saveState value="#{refReporteListaDetalleTranferenciasBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteListaDetalleTranferenciasBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteListaDetalleTranferenciasBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteListaDetalleTranferenciasBean.nombreArchivo}"></t:saveState>

		<% /* LISTADO */ %>
		<t:div  style="overflow:scroll; height:100%; width:700px;"  rendered="#{refReporteListaDetalleTranferenciasBean.mostrarLista}">  
	
		<h:panelGrid width="130%" columns="1"  cellspacing="0" cellpadding="0" >						 
		<h:column>
		<%/* cliente */ %>
				<t:dataTable value="#{refReporteListaDetalleTranferenciasBean.subItemsNivel1}" var="item2" 
									 rowClasses="fila2" 
									 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
									 cellspacing="2" border="0"
									 rendered="#{refReporteListaDetalleTranferenciasBean.mostrarLista}" >
				
				<h:column>	
				<h:panelGrid width="100%" columns="1" columnClasses="bordeblanco" cellspacing="0" cellpadding="0" >						 
				<h:column>					
								<h:panelGrid width="100%" columns="1" 
								 cellspacing="0" cellpadding="0" 
								 columnClasses="subtitulosCli"
								
								 >				
									<h:column>							
										<h:outputText value="CCSS : #{item2.ccss.descCcss}" />						
									</h:column>
								</h:panelGrid>
				</h:column>		

				<h:column>
				
				
						<t:dataTable value="#{item2.rendiciones}" var="item" 
									 rowClasses="fila2" 
									 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
									 cellspacing="0" border="0"
									 rendered="#{refReporteListaDetalleTranferenciasBean.mostrarLista}"
									 renderedIfEmpty="false" >
						<h:column>	
						<t:div rendered="#{refReporteListaDetalleTranferenciasBean.mostrarLista}" >
				<h:panelGrid width="100%" columns="7" 
								 cellspacing="0" cellpadding="0" 
								 columnClasses="columnaTablaCentradaFijo1,columnaTablaCentradaFijo2,  columnaTablaCentradaFijo2, columnaTablaCentradaFijo2, columnaTablaCentradaFijo3,columnaTablaCentradaFijo2,columnaTablaCentradaFijo4"
								 styleClass="subtitulosDocAplicados"						 						
								 >
					<h:column>											
						<h:outputText value="#{Message.operacion_label}"></h:outputText>
					</h:column>
					<h:column>											
						<h:outputText value="#{Message.nro_rendicion_label}"></h:outputText>
					</h:column>			 	
					<h:column>										
						<h:outputText value="#{Message.nro_ejercicio_label}" />						
					</h:column>	
															
					<h:column>											
						<h:outputText value="#{Message.nro_asiento_label}"></h:outputText>
					</h:column>	
					
					<h:column>											
						<h:outputText value="#{Message.cuenta_label}"></h:outputText>
					</h:column>						
					
					<h:column>	
						<h:outputText value="#{Message.fecha_label}"></h:outputText>							
					</h:column>	
					
					<h:column>
						<h:outputText value="#{Message.importe_label}"></h:outputText>													
					</h:column>						 
		  	    </h:panelGrid>		
				</t:div>
							<h:panelGrid width="100%" columns="1" columnClasses="bordeblanco" cellspacing="0" cellpadding="0" >						 
							<h:column>					
								<h:panelGrid width="100%" columns="7" 
								 cellspacing="1" cellpadding="1" 
								 headerClass="subtitulosFact"
								 columnClasses="columnaTablaCentradaFijo1,columnaTablaCentradaFijo21,  columnaTablaCentradaFijo21, columnaTablaCentradaFijo21, columnaTablaCentradaFijo3,columnaTablaCentradaFijo21,columnaTablaCentradaFijo4"
								 
								 >						
									
								<h:column>									   						
									<h:outputText value="#{item.rendicion.tipoOperacion}" />						
								</h:column>
													
								<h:column>									   						
									<h:outputText value="#{item.rendicion.nroRendicion}" />						
								</h:column>
								
								<h:column>														
									<h:outputText value="#{item.rendicion.nroEjercicio}" />
								</h:column>									
								
								<h:column>															
									<h:outputText value="#{item.rendicion.nroAsiento}" escape="false"/>							
								</h:column>	
								
								<h:column>																
									<h:outputText value="#{item.rendicion.cuentaBanco}"></h:outputText>
								</h:column>	
								
								<h:column>													
									<h:outputText value="#{item.rendicion.fecha}">	 </h:outputText>
								</h:column>	
								
								
								<h:column>															
									<h:outputText value="#{item.rendicion.importe}">
									 <f:convertNumber type="currency" pattern="#,##0.00"/>
									 </h:outputText>
								</h:column>	
								</h:panelGrid>
							</h:column>
							
							
							<%/* doc aplicados */ %>
							<h:column>
														 
							  <t:dataTable value="#{item.lstTransferencias}" var="item1" 
									 rowClasses="fila2" 
									 columnClasses="columnaTablaCentradaFijo1,columnaTablaCentradaFijo21,  columnaTablaCentradaFijo21, columnaTablaCentradaFijo21, columnaTablaCentradaFijo3,columnaTablaCentradaFijo21,columnaTablaCentradaFijo4"
							
									 headerClass="subtitulosDocAplicados" footerClass="footerTabla" width="100%" cellpadding="1" 
									 cellspacing="1" border="0" 
									 renderedIfEmpty="false"
									 >
							
							
								<h:column>									   						
									<h:outputText value="#{item1.tipoOperacion}" />						
								</h:column>
								
							    <h:column>								       								
									<h:outputText value="#{item1.nroRendicion}" />						
								</h:column>
				
								
								<h:column>								    							
									<h:outputText value="#{item1.nroEjercicio}" />
								</h:column>					
								
								
								<h:column>						
									<h:outputText value="#{item1.nroAsiento}" escape="false"/>							
								</h:column>	
								
								<h:column>						
									<h:outputText value="#{item1.cuentaBanco}">									 
									 </h:outputText>
								</h:column>	
								
								<h:column>							
									<h:outputText value="#{item1.fecha}">	 </h:outputText>
								</h:column>	
								
								
								<h:column>	
									<h:outputText value="#{item1.importe}">
									 <f:convertNumber type="currency" pattern="#,##0.00"/>
									 </h:outputText>
								</h:column>	
								
								
													
														
								</t:dataTable>
							</h:column> 
							
							
							</h:panelGrid>
							
							
						</h:column>			
						</t:dataTable>
					
					</h:column>	
					</h:panelGrid>
						
				</h:column>
				</t:dataTable>			
				</h:column>
				</h:panelGrid>
		</t:div>
			<h:panelGrid columns="1" width="80%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteListaDetalleTranferenciasBean.volver}" styleClass="boton"/>	
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
						