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
		
		
	<h:form id="frmFiltroCuenta" rendered="#{refReporteListaDetallesRendicionesBean.mostrarFrmLista}">	
		<t:saveState value="#{refReporteListaDetallesRendicionesBean.pagina}"></t:saveState>
		<c:if var="puedeIngresarS" test="${!refReporteListaDetallesRendicionesBean.puedeIngresar}">		
			    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
	   	</c:if> 
	   	
	   	
	<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
	<h:column>
		  			
	   		
		<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1"  rowClasses="titulosGrande" width="100%">
			<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			<%@ include file="../datosCliente.jsp" %>	
			<t:column>
				<t:outputText value="#{Message.tit_informe_lista_detalle_rendiciones}"/>
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
					<h:selectOneListbox id="ccss" size="1" value="#{refReporteListaDetallesRendicionesBean.ccss}" 
					    styleClass="campo" onchange="submit();" immediate="true" 
					   valueChangeListener="#{refReporteListaDetallesRendicionesBean.cargarSusRendiciones}" >
						<f:selectItems value="#{refReporteListaDetallesRendicionesBean.lstccss}" />
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
					<h:selectOneListbox id="rendicion" size="1" value="#{refReporteListaDetallesRendicionesBean.rendicion}" styleClass="campo" immediate="true" >
						<f:selectItems value="#{refReporteListaDetallesRendicionesBean.rendiciones}" />
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
				    		<t:commandButton value="#{Message.buscar_label}" actionListener="#{refReporteListaDetallesRendicionesBean.buscar}" styleClass="boton"/>
				    	</h:column>
				 	</h:panelGrid> 
			 	</h:column>				 	
			</h:panelGrid>
			
		
				
		<%/*PAGINACION*/ %>	
		
		<%/*MANEJO DE ERROR */%>
		<h:messages styleClass="errorNegro"/>
		
		<%/* PAGINACION*/%>
		<t:saveState value="#{refReporteListaDetallesRendicionesBean.pagina}"></t:saveState>
		 <h:panelGrid width="100%" cellspacing="3" columns="1" cellpadding="5">
		 <h:column>   		
		 
	   		
		   		<h:commandButton rendered="#{refReporteListaDetallesRendicionesBean.pagina.lastPage!=1 && refReporteListaDetallesRendicionesBean.pagina.totalElements!=0 && refReporteListaDetallesRendicionesBean.pagina.numpage != 1 && refReporteListaDetallesRendicionesBean.mostrarLista}" actionListener="#{refReporteListaDetallesRendicionesBean.primera}" value="" styleClass="botonPaginadoPrimero" disabled="#{refReporteListaDetallesRendicionesBean.pagina.numpage == 1}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton actionListener="#{refReporteListaDetallesRendicionesBean.anterior}" value=""  styleClass="botonPaginadoAnterior" disabled="#{refReporteListaDetallesRendicionesBean.pagina.numpage == 1}" rendered="#{refReporteListaDetallesRendicionesBean.pagina.lastPage!=1 && refReporteListaDetallesRendicionesBean.pagina.totalElements!=0  && refReporteListaDetallesRendicionesBean.pagina.numpage != 1 && refReporteListaDetallesRendicionesBean.mostrarLista}"/>	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   
		  		<t:outputText styleClass="cpo7" rendered="#{refReporteListaDetallesRendicionesBean.mostrarLista}" value="#{refReporteListaDetallesRendicionesBean.pagina.nroRegistroDesde}&nbsp;&nbsp;#{Message.a_label}&nbsp;&nbsp;#{refReporteListaDetallesRendicionesBean.pagina.nroRegistroHasta}" escape="false" />
		   		<t:outputText styleClass="cpo7" rendered="#{refReporteListaDetallesRendicionesBean.mostrarLista}" value="&nbsp;&nbsp;#{Message.de_label}&nbsp;&nbsp;" escape="false" />
		  		<h:outputText styleClass="cpo7" rendered="#{refReporteListaDetallesRendicionesBean.mostrarLista}" value="#{refReporteListaDetallesRendicionesBean.pagina.totalElements}&nbsp;&nbsp;-&nbsp;&nbsp;" escape="false"/>	
		   		
		   		<h:outputLink  rendered="#{refReporteListaDetallesRendicionesBean.nombreArchivo!='' && refReporteListaDetallesRendicionesBean.mostrarLista}" target="_blank" styleClass="cpo7b" value="#{refReporteListaDetallesRendicionesBean.nombreArchivo}">
					<h:outputText value=" #{Message.descargar_label}" />
				</h:outputLink>	
		   		
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteListaDetallesRendicionesBean.pagina.lastPage!=1 && refReporteListaDetallesRendicionesBean.pagina.totalElements!=0 && refReporteListaDetallesRendicionesBean.pagina.numpage != refReporteListaDetallesRendicionesBean.pagina.lastPage && refReporteListaDetallesRendicionesBean.mostrarLista}" actionListener="#{refReporteListaDetallesRendicionesBean.siguiente}" value="" styleClass="botonPaginadoSiguiente" disabled="#{refReporteListaDetallesRendicionesBean.pagina.numpage == refReporteListaDetallesRendicionesBean.pagina.lastPage}" />	
		   		<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
		   		<h:commandButton rendered="#{refReporteListaDetallesRendicionesBean.pagina.lastPage!=1 && refReporteListaDetallesRendicionesBean.pagina.totalElements!=0 && refReporteListaDetallesRendicionesBean.pagina.numpage != refReporteListaDetallesRendicionesBean.pagina.lastPage && refReporteListaDetallesRendicionesBean.mostrarLista}" actionListener="#{refReporteListaDetallesRendicionesBean.ultima}" value="" styleClass="botonPaginadoUltimo" disabled="#{refReporteListaDetallesRendicionesBean.pagina.numpage == refReporteListaDetallesRendicionesBean.pagina.lastPage}"/>			   		
		  
		 </h:column>		 
		 </h:panelGrid>
		 <t:saveState value="#{refReporteListaDetallesRendicionesBean.ccss}"></t:saveState>
		 <t:saveState value="#{refReporteListaDetallesRendicionesBean.lstccss}"></t:saveState>
		 <t:saveState value="#{refReporteListaDetallesRendicionesBean.rendicion}"></t:saveState>
		 <t:saveState value="#{refReporteListaDetallesRendicionesBean.rendiciones}"></t:saveState>
		<t:saveState value="#{refReporteListaDetallesRendicionesBean.subItemsNivel1}"></t:saveState>
		<t:saveState value="#{refReporteListaDetallesRendicionesBean.mostrarLista}"></t:saveState>
		<t:saveState value="#{refReporteListaDetallesRendicionesBean.mostrarFrmLista}"></t:saveState>
		<t:saveState value="#{refReporteListaDetallesRendicionesBean.nombreArchivo}"></t:saveState>
		 <t:saveState value="#{refReporteListaDetallesRendicionesBean.total}"></t:saveState>
		<% /* LISTADO */ %>
		<t:div  style="overflow:scroll; height:100%; width:700px;"  rendered="#{refReporteListaDetallesRendicionesBean.mostrarLista}">  
	
		<h:panelGrid width="95%" columns="1" columnClasses="bordeblanco" cellspacing="1" cellpadding="1" >						 
		<h:column>		
				<t:dataTable value="#{refReporteListaDetallesRendicionesBean.subItemsNivel1}" var="item" 
							 rowClasses="fila1, fila2" 
							 columnClasses="columnaTablaTexto,columnaTablaNumero,columnaTablaTexto,columnaTablaTexto,columnaTablaNumero,columnaTablaNumero, columnaTablaNumero, columnaTablaTexto,columnaTablaTexto,columnaTablaTexto ,columnaTablaNumero, columnaTablaNumero, columnaTablaTexto"
							 headerClass="subtitulos" footerClass="footerTabla" width="100%" cellpadding="2" 
							 cellspacing="1" border="0" 
							 rendered="#{refReporteListaDetallesRendicionesBean.mostrarLista}" >
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_label}"/>
						</f:facet>								
						<h:outputText value="#{item.fecha}"  escape="false"/>				
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_factura_largo_label}"/>
						</f:facet>
						<h:outputText value="#{item.nroSucursal}" escape="false"/>
						<h:outputText value="&nbsp;-&nbsp;" escape="false"/>
						<h:outputText value="#{item.nroFactura}" escape="false"/>
						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.centro_servicio_label}"/>
						</f:facet>
						<h:outputText value="#{item.ccss}"/>
					</h:column>					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cliente_label}"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion}"/>
					</h:column>
					
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.producto_label}"/>
						</f:facet>
						<h:outputText value="#{item.descArticulo}"/>
					</h:column>		
					
									
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.cantidad_label}"/>
						</f:facet>
						<h:outputText value="#{item.cantidad}" escape="false">						
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>
					
				
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.precio_solo_label}&nbsp;$" escape="false" />
						</f:facet>
						<h:outputText value="#{item.precioKilo}" escape="false">
						 <f:convertNumber type="currency" pattern="#,##0.0000"/>
						 </h:outputText>
						 <f:facet name="footer">
							<h:outputText value="Total&nbsp;$" escape="false">							
							</h:outputText>
						</f:facet>						
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.total_pesos_label}"/>
						</f:facet>
						<h:outputText value="#{item.preciototal}" escape="false">						
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
						 <f:facet name="footer">
							<h:outputText value="#{refReporteListaDetallesRendicionesBean.total}">
							<f:convertNumber type="currency" pattern="#,##0.00"/>
							</h:outputText>
						</f:facet>
					</h:column>				
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.patente_label}"/>
						</f:facet>
						<h:outputText value="#{item.patente}" escape="false"/>						
					</h:column>					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.chofer_label}"/>
						</f:facet>
						<h:outputText value="#{item.apellidoChofer},&nbsp;" escape="false"/>
						<h:outputText value="#{item.nombreChofer}"/>						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.total_subdiario_label}&nbsp;$" escape="false" />
						</f:facet>
						<h:outputText value="#{item.montoTotalSubdiario}" escape="false">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>						 						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.nro_rendicion_label}" escape="false" />
						</f:facet>
						 <h:outputText value="#{item.nroRendicionRefipass}" escape="false"/>												 			 						
					</h:column>
					
					<h:column>
						<f:facet name="header">
							<h:outputText value="#{Message.fecha_rendicion_label}"/>
						</f:facet>								
						<h:outputText value="#{item.fechaRendicion}"  escape="false"/>				
					</h:column>
					
					
					
							
				</t:dataTable>	
				</h:column>
				</h:panelGrid>
		</t:div>
			<h:panelGrid columns="1" width="80%" columnClasses="nada" styleClass="columnaTablaCentrada" cellspacing="0" cellpadding="4">						 
				<h:column>
					<h:panelGrid columns="1" width="100%"  styleClass="columnaTablaNumero" cellspacing="0" cellpadding="4">						 
				     	<h:column>				    		
				    		<t:commandButton value="#{Message.volver_label}" action="#{refReporteListaDetallesRendicionesBean.volver}" styleClass="boton"/>	
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
						