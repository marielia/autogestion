<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:loadBundle basename="com.refinor.extranet.bundle.Messages" var="Message"/>
<f:view>



<html>
	<head>
        <meta http-equiv="Content-Type"
             content="text/html; charset=windows-1252"/>
		<title><h:outputText value="#{Message.nombre_sistema}"/> </title>
		<link rel="stylesheet" type="text/css" href="<h:outputText value="#{Message.contexto_sistema}"/>/css/refinor.css">		
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" rightmargin="0" >
		<%@ include file="../header.jsp" %>				
	  	<h:form id="frmFiltroCuenta" rendered="#{refListadoInformesDisponiblesBean.mostrarFrmLista}">	
			
			<c:if var="puedeIngresarS" test="${!refListadoInformesDisponiblesBean.puedeIngresar}">		
				    <c:redirect url="/pages/web/app/salir.jsf"></c:redirect>
		   	</c:if> 
	   	
	   	
			<h:panelGrid width="100%" border="0" cellpadding="0" cellspacing="0" columns="1" styleClass="columnaTablaCentrada">
			<h:column>
			
			<t:panelGrid border="0" cellpadding="0" cellspacing="0" 
					 columns="1" columnClasses="titulosGrande" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="#{Message.tit_informe}"/>
				</t:column>
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
		
		
			<t:panelGrid border="0" columnClasses="columnaTablaTexto" cellpadding="0" cellspacing="0" 
					 columns="1" width="100%">
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
				<t:column>
					<t:outputText value="&nbsp;&nbsp;#{Message.bienvenido_label}&nbsp;&nbsp;" escape="false" styleClass="campoSinFondoGrande"/>
					<t:outputText  value="#{sessionScope.cliente.descripcion}"  escape="false"   styleClass="campoSinFondoGrandeBold" />
					<t:outputText  rendered="#{sessionScope.usuario.tipo==0}" value="#{Message.admin_refipass_label}"  escape="false"   styleClass="campoSinFondoGrandeBold" />
				</t:column>				
				<t:column><h:outputText  value="&nbsp;&nbsp;" escape="false" /></t:column>
			</t:panelGrid>
			
				  			
			   	<%/*MANEJO DE ERROR */%>		
						
				<h:panelGrid   rendered="#{sessionScope.usuario.tipo==1}" columns="2" width="100%" columnClasses="cpo8SinFondo" styleClass="columnaTablaCentradaYArriba" cellspacing="0" cellpadding="4">						 
						
						<h:column>		
						<h:panelGrid columns="1" width="90%" columnClasses="cpo8SinFondo" styleClass="columnaTablaCentradaYArriba" cellspacing="0" cellpadding="4">						 
							<h:column>
								<t:outputLabel for="consulte" styleClass="titulos">
									<h:outputText  value="&nbsp;#{Message.estado_cuenta_corriente_label} " escape="false" styleClass="titulos"/>
								</t:outputLabel>
							</h:column>	
							<h:column>
							</h:column>
						
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_cta_cte.jsf" >
									<h:outputText value="#{Message.cta_cte_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_facturas.jsf" >
									<h:outputText value="#{Message.facturas_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_recibo.jsf" >
									<h:outputText value="#{Message.recibos_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
								
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_remitos_completo.jsf" >
									<h:outputText value="#{Message.remitos_completo_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
								<t:outputLabel for="consulte" styleClass="titulos">
									<h:outputText  value="&nbsp;#{Message.otros_datos_label} " escape="false" styleClass="titulos"/>
								</t:outputLabel>
							</h:column>	
							<h:column>
							</h:column>
						
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_choferes.jsf" >
									<h:outputText value="#{Message.listado_choferes_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
						
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																		
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_vehiculos.jsf" >
									<h:outputText value="#{Message.listado_vehiculos_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_cupos_vehiculos.jsf?vaPor=CUP" >
									<h:outputText value="#{Message.listado_cupos_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
								<t:outputLabel styleClass="titulos">
									<h:outputText  value="&nbsp;#{Message.consulta_consumos_label} " escape="false" styleClass="titulos"/>
								</t:outputLabel>
							</h:column>	
							<h:column>
							</h:column>
							
							
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_consumo_chofer.jsf" >
									<h:outputText value="#{Message.consumo_chofer_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_consumo_por_grupo_chofer.jsf" >
									<h:outputText value="#{Message.unidad_negocio_choferes_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_consumo_vehiculo.jsf" >
									<h:outputText value="#{Message.consumo_vehiculo_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_consumo_por_grupo_vehiculo.jsf" >
									<h:outputText value="#{Message.unidad_negocio_vehiculos_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
								<t:outputLabel for="consulte" styleClass="titulos">
									<h:outputText  value="&nbsp;#{Message.formularios_label} " escape="false" styleClass="titulos"/>
								</t:outputLabel>
							</h:column>	
							<h:column>
							</h:column>
						
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/formularios/ref_alta_chofer.jsf" >
									<h:outputText value="#{Message.alta_chofer}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/formularios/ref_alta_vehiculo.jsf" >
									<h:outputText value="#{Message.alta_vehiculo_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							</h:panelGrid>
						
						
						
						</h:column>
						
						<h:column>	
						<h:panelGrid columns="1" width="65%" columnClasses="cpo8SinFondo" styleClass="columnaTablaCentradaYArriba" cellspacing="0" cellpadding="0">						 
							<h:column>					
<%-- 								<t:graphicImage   value="/img/fondo_sistema2.jpg"/> --%>
								<t:graphicImage   value=""/>
							</h:column>
						</h:panelGrid>
						</h:column>
												
					</h:panelGrid>
					
					<h:panelGrid   rendered="#{sessionScope.usuario.tipo==0}" columns="2" width="100%" columnClasses="cpo8SinFondo" styleClass="columnaTablaCentradaYArriba" cellspacing="0" cellpadding="4">						 
						
						<h:column>		
							<h:panelGrid columns="1" width="90%" columnClasses="cpo8SinFondo" styleClass="columnaTablaCentradaYArriba" cellspacing="0" cellpadding="4">						 
							<h:column>
								<t:outputLabel for="consulte" styleClass="titulos">
									<h:outputText  value="&nbsp;#{Message.estado_cuenta_corriente_label} " escape="false" styleClass="titulos"/>
								</t:outputLabel>
							</h:column>	
							<h:column>
							</h:column>
						
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_cta_cte.jsf" >
									<h:outputText value="#{Message.cta_cte_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_facturas.jsf" >
									<h:outputText value="#{Message.facturas_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_cobranzas.jsf" >
									<h:outputText value="#{Message.cobranzas_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_recibo.jsf" >
									<h:outputText value="#{Message.recibos_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_recibos_total.jsf" >
									<h:outputText value="#{Message.recibos_total_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_recibos_retenciones.jsf" >
									<h:outputText value="#{Message.recibos_retenciones_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_remitos_completo.jsf" >
									<h:outputText value="#{Message.remitos_completo_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_remitos_refacturacion.jsf" >
									<h:outputText value="#{Message.remitos_refacturacion_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_detalle_de_facturas.jsf" >
									<h:outputText value="#{Message.detalle_factura_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_resumen_clientes.jsf" >
									<h:outputText value="Detalle Cta Cte Por Cliente" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
								<h:column>
									<t:outputLabel for="consulte" styleClass="titulos">
										<h:outputText  value="&nbsp;#{Message.administracion_label} " escape="false" styleClass="titulos"/>
									</t:outputLabel>
								</h:column>	
								<h:column>
								</h:column>
							
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_clientes.jsf" >
										<h:outputText value="#{Message.lista_clientes_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>	
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_choferes.jsf" >
										<h:outputText value="#{Message.lista_choferes_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">	
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																		
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_vehiculos.jsf" >
										<h:outputText value="#{Message.lista_vehiculo_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">	
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																		
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_cupo_y_consumo.jsf?vaPor=CON" >
										<h:outputText value="#{Message.lista_consumo_v2_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>	
								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">	
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																		
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_cupos_vehiculos.jsf?vaPor=CUP" >
										<h:outputText value="#{Message.lista_cupos_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>									
								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_autorizaciones_usadas.jsf" >
										<h:outputText value="#{Message.listado_autorizaciones_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_combustible.jsf" >
										<h:outputText value="#{Message.listado_combustibles_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_rendicion_pendiente.jsf" >
										<h:outputText value="#{Message.listado_retenciones_pendientes_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_lista_detalles_rendiciones.jsf" >
										<h:outputText value="#{Message.listado_detalles_retenciones_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_lista_detalles_transferencias.jsf" >
										<h:outputText value="#{Message.listado_detalles_transferencias_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_volumen_vendido_producto_stock.jsf?codigoStock=1" >
										<h:outputText value="#{Message.listado_movimiento_stock_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_volumen_vendido_producto_stock.jsf?codigoStock=2" >
										<h:outputText value="#{Message.listado_stock_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_movimiento_stock.jsf" >
										<h:outputText value="#{Message.listado_movimiento_stock2_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_movimiento_stock_v2.jsf" >
										<h:outputText value="#{Message.listado_movimiento_stock2_label} Mejorado" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_exclusion_por_cliente.jsf" >
										<h:outputText value="#{Message.listado_exclusion_por_cliente_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_lista_precios.jsf" >
										<h:outputText value="#{Message.listado_precios_por_ccss_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_ley_cordoba.jsf" >
										<h:outputText value="#{Message.listado_impuesto_ley_cordoba}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
							<h:column>
								<t:outputLabel styleClass="titulos">
									<h:outputText  value="&nbsp;#{Message.consulta_consumos_label} " escape="false" styleClass="titulos"/>
								</t:outputLabel>
							</h:column>	
							<h:column>
							</h:column>
							
							
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_consumo_chofer.jsf" >
									<h:outputText value="#{Message.consumo_chofer_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
								
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_consumo_por_grupo_chofer.jsf" >
									<h:outputText value="#{Message.unidad_negocio_choferes_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>	
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_consumo_vehiculo.jsf" >
									<h:outputText value="#{Message.consumo_vehiculo_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
							<t:graphicImage value="/img/icono_poliactiva.gif"/>
							<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_consumo_por_grupo_vehiculo.jsf" >
									<h:outputText value="#{Message.unidad_negocio_vehiculos_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
																	
							<h:column>
								<t:outputLabel for="consulte" styleClass="titulos">
									<h:outputText  value="&nbsp;#{Message.formularios_label} " escape="false" styleClass="titulos"/>
								</t:outputLabel>
							</h:column>	
							<h:column>
							</h:column>
							
								<h:column  rendered="#{sessionScope.usuario.codigoRol==3}">
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/formularios/ref_mesa_ayuda.jsf" >
										<h:outputText value="#{Message.mesa_ayuda_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
								<h:column  rendered="#{sessionScope.usuario.codigoRol==3}">
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/formularios/ref_procesa_archivo_autorizaciones.jsf" >
										<h:outputText value="#{Message.procesa_archivo_autorizaciones}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>	
								
									
								<h:column   >
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/formularios/ref_procesa_archivo_remito_manual.jsf" >
										<h:outputText value="Cargar Remitos Manuales desde Archivo" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>				
								
								
								<h:column rendered="#{sessionScope.usuario.codigoRol==1}">
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/formularios/ref_alta_usuarios_web_empleados.jsf" >
										<h:outputText value="#{Message.alta_usu_web_empleado_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
								<h:column>
								<t:outputLabel for="consulte" styleClass="titulos">
									<h:outputText  value="&nbsp;#{Message.informes_contables_label} " escape="false" styleClass="titulos"/>
								</t:outputLabel>
								</h:column>	
								<h:column>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_percepcion_de_iva.jsf" >
										<h:outputText value="#{Message.percepciones_iva_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_percepcion_de_iibb.jsf" >
										<h:outputText value="#{Message.percepciones_iibb_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_mayor_contable.jsf" >
										<h:outputText value="#{Message.mayor_contable_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_sumas_saldos.jsf" >
										<h:outputText value="#{Message.sumas_saldos_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_subdiario_ventas.jsf" >
										<h:outputText value="#{Message.subdiario_ventas_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_asientos_con_diferencias.jsf" >
										<h:outputText value="#{Message.asientos_con_diferencias_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<%/**/ %>
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_libro_diario.jsf" >
										<h:outputText value="#{Message.libro_diario_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
								<%/*generar archivos de ventas y alicuota*/ %>
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>		
								<t:graphicImage value="/img/icono_poliactiva.gif"/>
								<h:outputText  value="&nbsp;&nbsp;&nbsp; " escape="false"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_genera_archivo.jsf" >
										<h:outputText value="Archivos Venta y Alicuota" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
							</h:panelGrid>
						</h:column>
						
						<h:column>	
							<h:panelGrid columns="1" width="65%" columnClasses="cpo8SinFondo" styleClass="columnaTablaCentradaYArriba" cellspacing="0" cellpadding="0">						 
								<h:column>					
<%-- 									<t:graphicImage   value="/img/fondo_sistema2.jpg"/> --%>
										<t:graphicImage   value=""/>
								</h:column>
							</h:panelGrid>
						</h:column>
												
					</h:panelGrid>
				
				</h:column>
			</h:panelGrid>			
		</h:form>
		
		<%@ include file="../footer.jsp" %>

	</body>	
</html> 
</f:view>