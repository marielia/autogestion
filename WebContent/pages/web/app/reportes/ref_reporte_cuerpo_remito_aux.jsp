 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 


					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.fecha_label}"/> --%>
							<h:outputText value="#{Message.fecha_label}"/>
						</f:facet>
						<h:outputText value="#{item.fecha} " >						
							<f:convertDateTime timeZone="GMT-3" pattern="dd/MM/yyyy"/>
						</h:outputText>
					</h:column>
					
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.hora_label}"/> --%>
								<h:outputText value="Hora"/>
						</f:facet>
						<h:outputText value="#{item.hora}" >						
						</h:outputText>
					</h:column>
					
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.ccss_label}"/>	 --%>
							<h:outputText value="CCSS"/>							
						</f:facet>
							<h:outputText value="#{item.ccss}"/>
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.cod_cliente_Alfa}"/> --%>
								<h:outputText value="COD CLIENTE"/>
						</f:facet>
						<h:outputText value="#{item.codClienteAlfa} " />
						
					</h:column>	
					
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.cliente_label}"/> --%>
							<h:outputText value="Cliente"/>
						</f:facet>
						<h:outputText value="#{item.cliDescripcion} " />
						
					</h:column>	
					
										
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.sucursal_label}"/> --%>
							<h:outputText value="Pref."/>
						</f:facet>
						<h:outputText value="#{item.nroSucursal} " />
						
					</h:column>
					
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.nro_remito_label}"/> --%>
							<h:outputText value="Nro. Rto."/>
						</f:facet>
						<h:outputText value="#{item.nroRemito}"/>
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.producto_label}"/> --%>
								<h:outputText value="Producto"/>
						</f:facet>
						<h:outputText value="#{item.descProducto}"/>
					</h:column>
					
					
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.cantidad_label}"/> --%>
								<h:outputText value="Cantidad"/>
						</f:facet>
						<h:outputText value="#{item.litros}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>		
							
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.precio_litro_label}"/> --%>
								<h:outputText value="Precio por Litro"/>  
						</f:facet>
						<h:outputText value="#{item.precioConImpuestos}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.total_pesos_label}"/> --%>
								<h:outputText value="Total $"/>
						</f:facet>
						<h:outputText value="#{item.montoTotal}">
						 <f:convertNumber type="currency" pattern="#,##0.00"/>
						 </h:outputText>
					</h:column>	
					
						
					
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.patente_label}"/> --%>
								<h:outputText value="Patente"/>
						</f:facet>
						<h:outputText value="#{item.patente}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.chofer_label}"/> --%>
								<h:outputText value="Chofer"/> 
						</f:facet>
						<h:outputText value="#{item.apellidoChofer}&nbsp; #{item.nombreChofer}" escape="false"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">							
<%-- 							<h:outputText value="#{Message.un_chofer_label}"/> --%>
								<h:outputText value="=U.N.Chofer"/> 
						</f:facet>
						<h:outputText value="#{item.descrGrupoUNC}"/>
					</h:column>
					
					<h:column>
						<f:facet name="header">
<%-- 							<h:outputText value="#{Message.un_camion_label}"/>	 --%>
							    <h:outputText value="U.N.Cami\u00F3n"/>							
						</f:facet>
							<h:outputText value="#{item.descrGrupoUNV}"/>
					</h:column>	