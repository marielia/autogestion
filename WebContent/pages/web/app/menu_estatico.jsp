<f:verbatim>
<div id="Layer1" style="position:absolute; width:170px; height:140px; z-index:1; left: 105px; top: 115px; background-color: #E7E4D8; layer-background-color: #E7E4D8; border: 1px solid #AAA076; overflow: hidden;" onMouseOver="big('Layer1')"; onMouseOut="small('Layer1')"> 

  <div align="center" >
   <font face="Verdana, Arial, Helvetica, sans-serif" >
    
	<h:outputText  value="&nbsp;#{Message.estado_cuenta_corriente_label} " escape="false" styleClass="datosMenu"/>
	<h:panelGrid columns="1" width="100%" columnClasses="cpo8SinFondo" styleClass="columnaTablaCentradaYArriba" cellspacing="0" cellpadding="3">						 
							
						
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>														
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_cta_cte.jsf" >
									<h:outputText value="#{Message.cta_cte_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_facturas.jsf" >
									<h:outputText value="#{Message.facturas_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>															
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_cobranzas.jsf" >
									<h:outputText value="#{Message.cobranzas_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>															
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_recibo.jsf" >
									<h:outputText value="#{Message.recibos_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>															
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_recibos_total.jsf" >
									<h:outputText value="#{Message.recibos_total_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>															
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_recibos_retenciones.jsf" >
									<h:outputText value="#{Message.recibos_retenciones_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>														
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_remitos_completo.jsf" >
									<h:outputText value="#{Message.remitos_completo_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>															
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_detalle_de_facturas.jsf" >
									<h:outputText value="#{Message.detalle_factura_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							</h:panelGrid>
							
    
	</font></div>
</div>

<div id="Layer2" style="position:absolute; width:160px; height:180px; z-index:1; left: 275px; top: 115px; background-color: #E7E4D8; layer-background-color: #FFFFCC; border: 1px solid #AAA076; overflow: hidden" onMouseOver="big('Layer2')"; onMouseOut="small('Layer2')"> 

<div align="center" >
   <font face="Verdana, Arial, Helvetica, sans-serif" >
    
	<h:outputText  value="&nbsp;#{Message.administracion_label} " escape="false" styleClass="datosMenu"/>
	<h:panelGrid columns="1" width="100%" columnClasses="cpo8SinFondo" styleClass="columnaTablaCentradaYArriba" cellspacing="0" cellpadding="3">						 
							
						
							<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">							
								<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_choferes.jsf" >
										<h:outputText value="#{Message.lista_choferes_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">	
							    <h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_vehiculos.jsf" >
										<h:outputText value="#{Message.lista_vehiculo_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">	
								<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																	
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_cupos.jsf" >
										<h:outputText value="#{Message.lista_cupos_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>							
								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
							<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_autorizaciones_usadas.jsf" >
										<h:outputText value="#{Message.listado_autorizaciones_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_combustible.jsf" >
										<h:outputText value="#{Message.listado_combustibles_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>															
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_rendicion_pendiente.jsf" >
										<h:outputText value="#{Message.listado_retenciones_pendientes_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_lista_detalles_rendiciones.jsf" >
										<h:outputText value="#{Message.listado_detalles_retenciones_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_volumen_vendido_producto_stock.jsf?codigoStock=1" >
										<h:outputText value="#{Message.listado_movimiento_stock_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>															
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_volumen_vendido_producto_stock.jsf?codigoStock=2" >
										<h:outputText value="#{Message.listado_stock_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
								
								<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">			
								<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>														
									<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_movimiento_stock.jsf" >
										<h:outputText value="#{Message.listado_movimiento_stock2_label}" styleClass="campoNoEditableSinFondo"/>
									</h:outputLink>							
								</t:outputLabel>
								</h:column>
							</h:panelGrid>
							
    
	</font></div>
</div>

<div id="Layer3" style="position:absolute; width:140px; height:180px; z-index:1; left: 435px; top: 115px; background-color: #E7E4D8; layer-background-color: #FFFFCC; border: 1px solid #AAA076; overflow: hidden" onMouseOver="big('Layer3')"; onMouseOut="small('Layer3')"> 
  <div align="center"><font face="Verdana, Arial, Helvetica, sans-serif">
    
<h:outputText  value="&nbsp;#{Message.consulta_consumos_label} " escape="false" styleClass="datosMenu"/>
	<h:panelGrid columns="1" width="100%" columnClasses="cpo8SinFondo" styleClass="columnaTablaCentradaYArriba" cellspacing="0" cellpadding="3">						 
							
						
							<h:column>
								<t:outputLabel styleClass="campoNoEditableSinFondo">							
								<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_consumo_chofer.jsf" >
									<h:outputText value="#{Message.consumo_chofer_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
								
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>	
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_consumo_por_grupo_chofer.jsf" >
									<h:outputText value="#{Message.unidad_negocio_choferes_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>	
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>	
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_consumo_vehiculo.jsf" >
									<h:outputText value="#{Message.consumo_vehiculo_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							
							<h:column>
							<t:outputLabel styleClass="campoNoEditableSinFondo">	
							<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>	
								<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_consumo_por_grupo_vehiculo.jsf" >
									<h:outputText value="#{Message.unidad_negocio_vehiculos_label}" styleClass="campoNoEditableSinFondo"/>
								</h:outputLink>							
							</t:outputLabel>
							</h:column>
							</h:panelGrid>
							
							
							</font></div>
</div>

<div id="Layer4" style="position:absolute; width:130px; height:180px; z-index:1; left: 575px; top: 115px; background-color: #E7E4D8; layer-background-color: #FFFFCC; border: 1px solid #AAA076; overflow: hidden" onMouseOver="big('Layer4')"; onMouseOut="small('Layer4')"> 
  <div align="center"><font face="Verdana, Arial, Helvetica, sans-serif">
    
	<h:outputText  value="&nbsp;#{Message.formularios_label} " escape="false" styleClass="datosMenu"/>
	<h:panelGrid columns="1" width="100%" columnClasses="cpo8SinFondo" styleClass="columnaTablaCentradaYArriba" cellspacing="0" cellpadding="3">						 
	<h:column  rendered="#{sessionScope.usuario.codigoRol==3}">
	<t:outputLabel styleClass="campoNoEditableSinFondo">			
	<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																			
		<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/formularios/ref_mesa_ayuda.jsf" >
			<h:outputText value="#{Message.mesa_ayuda_label}" styleClass="campoNoEditableSinFondo"/>
		</h:outputLink>							
	</t:outputLabel>
	</h:column>
	
	
	<h:column rendered="#{sessionScope.usuario.codigoRol==1}">
	<t:outputLabel styleClass="campoNoEditableSinFondo">			
	<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																			
		<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/formularios/ref_alta_usuarios_web_empleados.jsf" >
			<h:outputText value="#{Message.alta_usu_web_empleado_label}" styleClass="campoNoEditableSinFondo"/>
		</h:outputLink>							
	</t:outputLabel>
	</h:column>						
						
							
	</h:panelGrid>
							
							
	</font>
	</div>
</div>

<div id="Layer5" style="position:absolute; width:159px; height:180px; z-index:1; left: 705px; top: 115px; background-color: #E7E4D8; layer-background-color: #FFFFCC; border: 1px solid #AAA076; overflow: hidden" onMouseOver="big('Layer5')"; onMouseOut="small('Layer5')"> 
  <div align="center"><font face="Verdana, Arial, Helvetica, sans-serif">
    
	<h:outputText  value="&nbsp;#{Message.informes_contables_label} " escape="false" styleClass="datosMenu"/>
	<h:panelGrid columns="1" width="100%" columnClasses="cpo8SinFondo" styleClass="columnaTablaCentradaYArriba" cellspacing="0" cellpadding="3">						 
	<h:column>
	<t:outputLabel styleClass="campoNoEditableSinFondo">			
	<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																		
		<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_percepcion_de_iva.jsf" >
			<h:outputText value="#{Message.percepciones_iva_label}" styleClass="campoNoEditableSinFondo"/>
		</h:outputLink>							
	</t:outputLabel>
	</h:column>
	
	
	<h:column>
	<t:outputLabel styleClass="campoNoEditableSinFondo">			
	<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																				
		<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_percepcion_de_iibb.jsf" >
			<h:outputText value="#{Message.percepciones_iibb_label}" styleClass="campoNoEditableSinFondo"/>
		</h:outputLink>							
	</t:outputLabel>
	</h:column>
	
	<h:column>
	<t:outputLabel styleClass="campoNoEditableSinFondo">			
	<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																		
		<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_mayor_contable.jsf" >
			<h:outputText value="#{Message.mayor_contable_label}" styleClass="campoNoEditableSinFondo"/>
		</h:outputLink>							
	</t:outputLabel>
	</h:column>
	
	
	<h:column>
	<t:outputLabel styleClass="campoNoEditableSinFondo">			
	<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																			
		<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_sumas_saldos.jsf" >
			<h:outputText value="#{Message.sumas_saldos_label}" styleClass="campoNoEditableSinFondo"/>
		</h:outputLink>							
	</t:outputLabel>
	</h:column>
	
	
	<h:column>
	<t:outputLabel styleClass="campoNoEditableSinFondo">			
	<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																	
		<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_subdiario_ventas.jsf" >
			<h:outputText value="#{Message.subdiario_ventas_label}" styleClass="campoNoEditableSinFondo"/>
		</h:outputLink>							
	</t:outputLabel>
	</h:column>
	
	
	<h:column>
	<t:outputLabel styleClass="campoNoEditableSinFondo">			
	<h:outputText  value=":. " escape="false" style="font-weight: bold;"/>																		
		<h:outputLink value="#{Message.contexto_sistema}/pages/web/app/reportes/ref_reporte_asientos_con_diferencias.jsf" >
			<h:outputText value="#{Message.asientos_con_diferencias_label}" styleClass="campoNoEditableSinFondo"/>
		</h:outputLink>							
	</t:outputLabel>
	</h:column>						
						
							
	</h:panelGrid>
							
							
	</font>
	</div>
</div>
</f:verbatim>