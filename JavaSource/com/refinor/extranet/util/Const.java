package com.refinor.extranet.util;



import java.util.Date;
import java.util.GregorianCalendar;

public class Const {

	
	// General
	public static String SESSION_TIMEOUT="session_timeout";
	

	public static String USUARIO_INTERNO="usuario_interno";
	public static String AGENTE="agente";
	public static String PERSONA="persona";
	public static String PERSONAS="personas";
	public static String TIPO_DOCUMENTO= "tipo_documento";
	public static String NUMERO_DOCUMENTO= "numero_documento";
	public static String FUNCIONES="funciones";
	public static String PERFIL= "perfil";
	public static String PERFILES= "perfiles";
	public static String TIPO_USUARIO= "tipo_usuario";
	public static char ESTADO_ACTIVO='A';
	public static char ESTADO_BAJA='B';
	public static String SELECCIONE= "- Seleccione -";
	public static String INGRESAR_LABEL= "Ingresar"; 
	public static String CONTINUAR_LABEL= "Continuar"; 
		
	
	public static int TIPO_OPERACION_DESCONOCIDO= 0;
	public static int TIPO_OPERACION_PATRIMONIAL= 1;
	public static int TIPO_OPERACION_VIDA= 2;
	public static int TIPO_OPERACION_VEHICULO= 3;
	public static String MOTIVO_ULTIMO_ENDOSO_REFACTURACION="motivo_ultimo_endoso_refacturacion";
	public static int MOTIVO_MOVIMIENTO_ITEM=2;
	public static String FECHA_ACTUAL="fechaActual";
	public static char TIPO_AGENTE_PRODUCTOR= 'P';
	public static char TIPO_AGENTE_ORGANIZADOR= 'O';
	public static String DOCUMENTO= "documento";
	public static char TIPO_PERSONA_CLIENTE= 'C';
	public static char TIPO_PERSONA_AGENTE= 'A';
	public static char TIPO_PERSONA_PROVEEDOR= 'P';
	public static String EXT_ARCHIVOS_SALIDA= "txt";
	public static String REPOSITORIO= "repositorio";
	public static int TIPO_REGISTRO_GRABACION_PERSONA= 1;
	public static int TIPO_REGISTRO_GRABACION_DOMICILIO= 2;
	public static int TIPO_REGISTRO_GRABACION_TELEFONO= 3;
	public static String SEPARADOR_ARCHIVO_SALIDA= "#";
	public static String ARCHIVO_DATOS_PERSONALES= "datos_personales.txt";
	public static String SUBJECT_CAMBIO_DATO= "subjectCambioDato"; 
	public static String EMAIL_CAMBIO_DATO= "email_cambio_dato";
	public static String EMAIL_CONTACTENOS= "email_contactenos";
	public static String CONCEPTO_SALDO_ANTERIOR= "Saldo Anterior";
	public static String SUBJECT_CONTACTENOS= "SubjectContactenos"; 
	public static String BODY_CONTACTENOS= "BodyContactenos"; 
	public static String SUBJECT_REGISTRAR_ASEGURADO= "SubjectRegistrarAsegurado"; 
	public static String BODY_REGISTRAR_ASEGURADO= "BodyRegistrarAsegurado"; 
	public static String EMAIL_REGISTRAR_ASEGURADO= "email_registrar_asegurado";
	public static int SECCION_HIBRIDA= 40;
	public static String FORMULARIO_DETALLE_POLIZA= "detallePoliza";
	public static String FORMULARIO_DETALLE_ENDOSO= "detalleEndoso";
	public static String PATH_CODIGOS_BARRA= "pathCodigosBarra";
	public static String NOMBRE_USU = "nombreUsu";
	public static String A_FACTURAR="a Facturar";
	public static String ARCHIVO_CONF_HIBERNATE="conf_hibernate";
	public static int TAMANIO_PAGINA=10;
	
	public static String FECHA_INFORMACION_ACTUALIZADA="fechaInformacionActualizada";
	
	
	
		
	// Estados
	public static int FUNCION_ACTIVA= 1;
	public static int FUNCION_INACTIVA= 0;
	
	// Comprobantes
	public static String COMPROBANTE_FC_FACTURA="FC";
	public static String COMPROBANTE_DV_AJUSTE="DV";
	public static String COMPROBANTE_XM="XM";
	public static String[] COMPROBANTES_TIPO_FACTURA= {"FC", "DV", "XM", "XF", "EB", "EA", "EM"}; 
	
	// Tipos de Registro
	public static Integer TIPO_REGISTRO_INTERNO = new Integer(0);
	public static Integer TIPO_REGISTRO_CLIENTE = new Integer(1);
	public static Integer TIPO_REGISTRO_AGENTE = new Integer(2);
	public static Integer TIPO_REGISTRO_PROVEEDOR = new Integer(3);
	public static Integer TIPO_REGISTRO_MIEMBRO_COLECTIVO = new Integer(4);
	public static Integer TIPO_REGISTRO_AGENTE_ORGANIZADOR = new Integer(5);
	
	// Tipos de Usuario
	public static Integer TIPO_USUARIO_INTERNO = new Integer(0);
	public static Integer TIPO_USUARIO_WEB = new Integer(1);
	
	//Roles Predeterminados
//	public static Integer ROL_CLIENTE = new Integer(1);
	public static Integer ROL_AGENTE = new Integer(2);
	public static Integer ROL_PROVEEDOR = new Integer(3);
	
	
	// Navegación JSF
	public static String SUCCESS="success";
	public static String FAIL="fail";
	public static String SALIR="salir";
	
	public static String SUCCESS_MONOPERFIL="success_monoperfil";
	public static String A_LOGON= "logon";
	public static String A_BLANK= "blank";
	public static String SUCCESS_CONSULTA_POLIZAS="success_consulta_polizas";
	public static String SUCCESS_CUPONERA="success_cuponera";
	
	// Hibernate
	// Propiedad que indica el archivo de configuración
	public static String CONF_HIBERNATE="conf_heibernate";
	//public static String CONF_HIBERNATE="conf_hibernate";
	// Parámetros de Queries
	public static String PARAM_ID="id";
	public static String PARAM_TIPO_DOCUMENTO="tipoDocumento";
	public static String PARAM_NUMERO_DOCUMENTO="numeroDocumento";
	public static String PARAM_EMAIL="email";
	public static String PARAM_UDUARIO_ID="usuarioId";
	public static String PARAM_REGISTRO_ID="registroId";
	public static String PARAM_CLAVE="clave";
	public static String PARAM_CODIGO="codigo";
	public static String PARAM_NRO_PERSONA="nroPersona";
	public static String PARAM_NRO_AGENTE="nroAgente";
	public static String PARAM_NRO_OPERACION="nroOperacion";
	public static String PARAM_NRO_ENDOSO_OPERATIVO="nroEndosoOperativo";
	public static String PARAM_NRO_ITEM="nroItem";
	public static String PARAM_COD_COBERTURA= "codCobertura";
	public static String PARAM_COD_RIESGO= "codRiesgo";
	public static String PARAM_COD_SECCION= "codSeccion";
	public static String PARAM_MOTIVO_ENDOSO_REFACTURACION= "codMotivoEndosoRefacturacion";
	public static String PARAM_NRO_SINIESTRO= "nroSiniestro";
	public static String PARAM_FECHA= "fecha";
	public static String PARAM_COD_COMPROBANTE= "codComprobante";
	public static String PARAM_FORMA_PAGO= "formaPago";
	public static String PARAM_NRO_RIESGO_GENERAL= "nroRiesgoGeneral";
	
	public static String PARAM_COD_ESTADO= "codEstado";
	public static String PARAM_TIPO_AGENTE= "tipoAgente";
	public static String PARAM_PERIODO= "periodo";
	public static String PARAM_TIPO_PERSONA= "tipoPersona";
	public static String PARAM_ZONA_POSTAL= "zonaPostal";
	public static String PARAM_COD_MARCA= "codMarca";
	public static String PARAM_COD_MODELO= "codModelo";
	public static String PARAM_DOMINIO= "dominio";
	
	//etiquetas del menu para las consultas
	public static String PARAM_NRO_POLIZA= "nroPoliza";
	public static String PARAM_FECHA_EMISION= "fechaEmision";
	public static String PARAM_FECHA_DESDE_EMISION= "fechaDesdeEmision";
	public static String PARAM_FECHA_HASTA_EMISION= "fechaHastaEmision";
	public static String PARAM_FECHA_INICIO_VIGENCIA= "fechaInicioVigencia";
	public static String PARAM_FECHA_FIN_VIGENVIA= "fechaFinVigencia";	
	public static String PARAM_FIN_VIGENCIA_POLIZA= "finVigenciaPoliza";
	public static String PARAM_TIPO_SECCION= "tipoSeccion";
	public static String PARAM_NOMBRE_CLIENTE = "nombrePersona";
	public static String PARAM_NOMBRE_AGENTE = "nombreAgenteDelOr";
	public static String PARAM_NUMERO_AGENTE = "numeroAgenteDelOr";
	
	public static String PARAM_ACTIVO = "activo";
	public static String PARAM_BAJA = "baja";
	public static String PARAM_CUIT = "cuit";
	

	public static String POLIZA_CONSULTA= "poliza_consulta";
	public static String COBRANZA_CONSULTA= "cobranza_consulta";
	public static String SINIESTRO_CONSULTA="siniestro_consulta";
	
	public static String KEY_POLIZA= "Póliza";
	public static String KEY_ENDOSOS= "Endosos";
	public static String KEY_POLIZA_ACTIVA= "Activa";
	public static String KEY_POLIZA_ANULADA= "Anulada";
	public static String POLIZA_ACTIVA= "A";
	public static String POLIZA_ANULADA= "C";
	public static Integer VALUE_TODOS = new Integer(0);
	public static Integer VALUE_POLIZA_ACTIVA = new Integer(1);
	public static Integer VALUE_POLIZA_ANULADA = new Integer(2);
	public static String KEY_TODOS = "- Todos -";
	//Nombre de parametros 
	public static String JSCOOK_ACTION= "jscook_action";
	public static String CONSULTA= "consulta";
	public static String MENU= "menu";
	public static String MANSAJE_NO_HAY_DATOS ="No existen datos para los criterios ingresados.";
	public static String PARAM_NRO_CUOTA = "nroCuota";
	
	/*Tipos de domicilios*/	
	public static String KEY_AGENCIA= "Agencia";
	public static Integer VALUE_AGENCIA = new Integer(1);
	public static String KEY_CHAR_AGENCIA = "0"; //cero
	
	public static String KEY_COBRANZA= "Cobranza";
	public static Integer VALUE_COBRANZA = new Integer(2);
	public static String KEY_CHAR_COBRANZA = "C"; 
	
	public static String KEY_CORREO_ELECTRONICO= "Correo Electronico";
	public static Integer VALUE_CORREOELECTRONICO = new Integer(3);
	public static String KEY_CHAR_CORREOELECTRONICO = "E"; 
	
	public static String KEY_LEGAL= "Legal";
	public static Integer VALUE_LEGAL = new Integer(4);
	public static String KEY_CHAR_LEGAL = "L"; //cero
	
	public static String KEY_PARTICULAR_D= "Particular";
	public static Integer VALUE_PARTICULAR_D = new Integer(5);
	public static String KEY_CHAR_PARTICULAR_D = "P"; 
	
	/*Tipos de linas*/
	public static String KEY_COMERCIAL= "Comercial";
	public static Integer VALUE_COMERCIAL = new Integer(1);
	public static String KEY_CHAR_COMERCIAL = "C"; 
	
	public static String KEY_PARTICULAR_T= "Particular";
	public static Integer VALUE_PARTICULAR_T = new Integer(2);
	public static String KEY_CHAR_PARTICULAR_T = "P";

	/*Estados para domicilio y telefono*/
	public static String COD_ESTADO_BAJA = "B";
	public static String COD_ESTADO_NUEVO = "N";
	public static String COD_ESTADO_MODIFICADO = "M";
	
	public static String ARCHIVO_DOMICILIO =  "domicilio.txt";
	public static String ARCHIVO_TELEFONO =  "telefono.txt";
	
	public static String NRO_AFA = "094565";


	/*cantidad de posicion que debe tener cada variable*/
	public static int CANT_POS_SECCION = new Integer(3);
	public static int CANT_POS_NROPOLIZA = new Integer(8);
	public static int CANT_POS_NROCERTIFICADO = new Integer(7);
	public static int CANT_POS_NRORENOVACION = new Integer(3);
	public static int CANT_POS_NROSUPLEMENTO = new Integer (3);
	public static int CANT_POS_NROENDOSOOPERATIVO = new Integer(7);
	public static int CANT_POS_NROCUOTA = new Integer(2);
	public static int CANT_POS_VENCIMIENTO = new Integer(6);
	public static int CANT_POS_MONEDA = new Integer(2);
	public static int CANT_POS_IMPORTE = new Integer(7);
	public static int CANT_POS_DV = new Integer(1);
	public static int CANT_POS_SECCIONCOMP = new Integer (2);
	public static int CANT_POS_NROPOLIZACOMP = new Integer (7);
	public static int CANT_POS_NROSUPLEMENTOCOMP = new Integer (3);
	public static int CANT_POS_NROCUOTACOMP = new Integer (2);
	public static String DIR_ARCHIVOS = "dirArchivos";
	
	//valores por defecto para generar codigo de barra
	public static String barcodeType="CODE128";
	public static String sizeX = "300";
	public static String sizeY="50";
	public static String rotation="0";
	public static String barColor="";
	public static String backColor = "";	
	public static String showText="true";	
	public static String fontName="Arial";
	public static String fontSize="10";
	public static String fontStyle="BOLD";
	public static String transparent="true";
	public static String alignment="CENTER";
	public static String imageFormat="JPG";
	public static String quietZone=null;
	
	public static String FLOTA="Flota";
	public static String VARIAS="Varias";
	
	//	probando -barcode4j- probando
	public static String COD_BARR_TYPE= "code128";
	public static String COD_BARR_HEIGHT= "2.5cm";
	public static String COD_BARR_WIDTH= "0.5mm";
	public static String COD_BARR_WIDE_FACTOR= "3";
	public static String COD_BARR_FORMAT= "jpeg";
	public static String COD_BARR_RESOLUCION= "300";	
	public static String COD_BARR_GEN_CODIGO_BARRA= "genCodigoBarra";
	public static String COD_BARR_HUMAN_READABLE_SIZE="12pt";
	public static String COD_BARR_HUMAN_READABLE="Arial";
		
	public static String TIPO_DOC_ASE="tipo_doc_ase";
	public static String NUMERO_DOC_ASE="numero_doc_ase";
	public static String TIPO_DOC_ASE_CHAR="tipo_doc_ase_char";
	public static String PATH_EXTRANET = "/refipass/";
	
	public static String ARCHIVO_EXCEL_LISTADO_POLIZAS = "listado_polizas.xls";
	public static String ARCHIVO_EXCEL_REPORTE_OP_SEGURO = "_Soporte_Registro_Emision.xls";
	public static String ARCHIVO_EXCEL_REPORTE_OP_IMPUTACION = "_Soporte_Registro_Cobranzas.xls";
	public static String ARCHIVO_EXCEL_PRELIQUIDACION_COBRANZAS = "_Premios_Pendientes_Exigibles.xls";
	
	//Reportes - codigos de secciones
	public static int COD_SECCION_VIDA_COLECTIVO = new Integer(21);
	public static int COD_SECCION_VIDA_OBLIGATORIO = new Integer(28);
	public static int COD_SECCION_ACCIDENTES_PERSONALES_COLECTIVOS = new Integer(18);
	public static int COD_SECCION_VIDA_INDIVIDUAL = new Integer(19);
	public static int COD_SECCION_ACCIDENTE_PERSONAL_INDIVIDUAL = new Integer(20);	
	public static int COD_SECCION_CAUCION = new Integer(12);
	public static int COD_SECCION_COMBINADO_FAMILIAR = new Integer(50);
	public static int COD_SECCION_INTEGRAL_COMERCIO = new Integer(27);
	public static int COD_FORMA_PAGO_ANULADO_AMORTIZACION = new Integer(9);
	
	
	public static char ESTADO_PAGADO_OPERACION_CUOTA= 'P';
	public static char ESTADO_DEUDA_OPERACION_CUOTA= 'D';
	
	//REFIPASS
	public static String TAMANIO_PAGINA_ARCHIVO="tamanio_pagina";
	public static String PARAM_COD_VENDEDOR="codVendedor";
	public static String PARAM_COD_CCSS="codCcss";
	public static String PARAM_COD_PROVINCIA="codProvincia";
	public static String PARAM_NRO_RENDICION="nroRendicion";
	public static String PARAM_NRO_OPER_RENDICION="nroOperRendicion";
	public static String PARAM_COD_CLIENTE="codCliente";
	public static String PARAM_COD_MOTIVO="codMotivo";
	public static String PARAM_COD_DESCRIPCION="codDescripcion";
	public static String PARAM_COD_CHOFER="codChofer";
	public static String PARAM_COD_VEHICULO="codVehiculo";
	
	
	public static String PARAM_COD_CLIENTE_INT="codClienteInt";
	public static String PARAM_NOMBRE="nombre";
	public static String PARAM_COD_UNIDAD_NEGOCIO="codUnidadNegocio";
	public static String PARAM_COD_GRUPO_UN="codGrupoUN";
	public static String PARAM_DESCRIPCION_UNIDAD_NEGOCIO="descripcionUnidadNegocio";
	public static String PARAM_DESCRIPCION_GRUPO_UN="descripcionGrupoUN";
	public static String PARAM_CHOFER_ACTIVO="choferActivo";
	public static String PARAM_NOMBRE_CHOFER="nombreChofer";
	
	public static String PARAM_CHOFER_BAJA="choferBaja";
	public static String PARAM_DNI="dni";
	public static String PARAM_PATENTE= "patente";
	public static String PARAM_COD_PRODUCTO= "codProducto";
	public static String PARAM_COD_ART= "codArt";
	public static String PARAM_LETRA_ART= "letraArt";
	
	public static String PARAM_COD_LISTA_PRECIO= "codListaPrecio";
	public static String PARAM_TIPO_COMPROBANTE_MANUAL= "tipoCompManual";
	public static String PARAM_TIPO_COMPROBANTE= "tipoComprobante";
	public static String PARAM_NRO_AUTORIZACION= "nroAutorizacion";
	public static String PARAM_SUC_MANUAL= "sucursalManual";
	public static String PARAM_NRO_REMITO_1= "nroRemito1";
	public static String PARAM_COD_AUTORIZACION= "codAutorizacion";
	public static String PARAM_COD_EMPLEADO= "codEmpleado";
	public static String PARAM_NRO_OPER_CAJA= "nroOperCaja";
	public static String PARAM_FECHA_DESDE= "fechaDesde";
	public static String PARAM_FECHA_HASTA= "fechaHasta";
	public static String PARAM_FECHA_DESDE_DOS= "fechaDesdeDos";
	public static String PARAM_FECHA_HASTA_DOS= "fechaHastaDos";
	
	public static String PARAM_NRO_RECIBO_DESDE= "nroReciboDesde";
	public static String PARAM_NRO_RECIBO_HASTA= "nroReciboHasta";
	public static String PARAM_NRO_REMITO_DESDE= "nroRemitoDesde";
	public static String PARAM_NRO_REMITO_HASTA= "nroRemitoHasta";
	
	public static String PARAM_CLIENTE_DESDE= "clienteDesde";
	public static String PARAM_CLIENTE_HASTA= "clienteHasta";
	
	public static String PARAM_CUENTA_DESDE= "cuentaDesde";
	public static String PARAM_CUENTA_HASTA= "cuentaHasta";
	
	public static String PARAM_NRO_ASIENTO_DESDE= "nroAsientoDesde";
	public static String PARAM_NRO_ASIENTO_HASTA= "nroAsientoHasta";
	
	public static String PARAM_ORDEN_FACTURA= "ordenFactura";	
	public static String PARAM_VEHICULO_ACTIVO="vehiculoActivo";
	public static String PARAM_VEHICULO_BAJA="vehiculoBaja";	
	public static String PARAM_VEHICULO_INICIALIZADO="vehiculoInicializado";
	public static String PARAM_VEHICULO_NO_INICIALIZADO="vehiculoNoInicializado";
	public static String PARAM_CHOFER_INICIALIZADO="choferInicializado";
	public static String PARAM_CHOFER_NO_INICIALIZADO="choferNoInicializado";
	
	public static String PARAM_OPT_REFACTURACION="optRefacturacion";
	public static String FACTURA= "FACTURA";
	public static String REMITO= "REMITO";
	public static String RECIBO_NO_APLICADO= "RECIBO NO APLICADO";
	public static String PAGO_NO_APLICADO_RECIBO= "PAGO NO APLICADO DE RECIBO";
	public static String NC_NO_APLICADO= "NOTA DE CREDITO NO APLICADA";
	public static String VALOR_POSDATADO= "VALOR POSDATADO";
	
	public static String NOTA_CREDITO= "NOTA DE CR�DITO";
	public static String NOTA_DEBITO= "NOTA DE D�BITO";
	public static char USUARIO_ACTIVO= 'A';
	public static char USUARIO_INACTIVO= 'S';
	public static Date FECHA_RESET_INTENTO_FALLIDO= (new GregorianCalendar(2000, 0, 1)).getTime();
	public static String EMAIL= "email";
	public static String PARAM_COD_CLIENTE_ALFA="codClienteAlfa";
	public static String PARAM_NRO_REMITO="nroRemito";
	public static String PARAM_NRO_SUCURSAL="nroSucursal";
	public static String PARAM_NRO_FACTURA="nroFactura";
	
	public static String ANCLA_CAMBIO_PASSWORD= "cambio_password";
	public static String ANCLA_LISTA_REPORTES_DISPONIBLES= "lista_reportes_disponibles";
	public static String ANCLA_INDEX= "index";
	public static String ANCLA_LISTA_CHOFERES= "lista_choferes";
	public static String ANCLA_LISTA_VEHICULOS= "lista_vehiculos";
	public static String ANCLA_LISTA_AUTORIZACIONES= "lista_autorizaciones";
	
	public static String SEGUNDOS_LOGON_FALLIDO="segundos_logon_fallido";
	public static int INTENTO_FALLIDOS=10;
	public static Integer DEBE_CAMBIAR_CONTRASENIA= new Integer(1);
	public static Integer NO_DEBE_CAMBIAR_CONTRASENIA= new Integer(0);
	
	public static String USUARIO="usuario";
	public static String CLIENTE="cliente";
	public static String INICIO_LABEL= "Volver a Menu"; 
	public static String PARAM_COD_GRUPO_UNIDAD_NEGOCIO="codGrupoUnidadNegocio";	
	public static String PARAM_ESTADO= "estado";
	public static String PARAM_NRO_MOTIVO_AUT= "nroMotivoAutorizacion";
	public static Integer ROL_CLIENTE= new Integer(2);
	public static Integer ROL_ADMIN= new Integer(1);
	public static Integer ROL_EMPLEADO= new Integer(3);
	public static String ANULADO= "ANULADO";
	public static String ACTIVO= "ACTIVO";
	public static String PARAM_NOMB_TABLA= "nombTabla";
	public static String NOMB_TABLA_MCHOFER= "MChofer";
	
	public static String RETENCION_IVA= "I.V.A.";
	public static String RETENCION_GANANCIAS= "Ganancias";
	public static String RETENCION_INGRESOS_BRUTOS= "Ingresos Brutos";
	public static String RETENCION_SIJP= "SIJP";
	public static String RETENCION_SEGURIDAD_SOCIAL= "Seguridad Social";
	public static String RETENCION_TRS= "TRS";
	public static String RETENCION_TISSH= "TISSH";
	public static String RETENCION_TMT= "Tasa Municipal de Tartagal";
	public static String CONDICION_CONTADO= "Contado";
	public static String CONDICION_CTA_CTE= "Cuenta Corriente";
	public static int CONDICION_COD_CONTADO= 2;
	public static int CONDICION_COD_CTA_CTE= 1;
	
	public static String ESTADO_ENVIADO= "Enviado";
	public static String ESTADO_RECIBIDO= "Recepcionado";
	
	public static String ESTADO_FACTURADO= "Facturado";
	public static String ESTADO_NO_FACTURADO= "No Facturado";
	
	public static Integer COD_IMPUESTO= 767;
	public static Integer COD_REGIMEN= 493;
	public static Integer COD_OPERACION= 2;
	public static Integer COD_CONDICION= 1;
	
	public static int COD_CCSS_HEAD_OFICCE= 1;
	public static String COD_HEAD_OFICCE= "codCcssHeadOffice";
	public static String PARAM_TIPO_OPERACION= "tipoOperacion";
	public static String PARAM_TIPO_OPERACION_DOS= "tipoOperacionDos";
	public static String PARAM_COBRO_FACTURA_CLIENTE_CTA_CTE= "Cobro Factura Cliente";
	public static String PARAM_APLICAADEL_FACTURA_CLIENTE= "AplicAdel Factura Cliente";
	
	public static String COD_INCRIPCION_IIBB= "codInscripIb";
	public static String PARAM_DESCRIPCION="descripcion";
	
	//indices de columnas del archivo de generacion de autorizaciones
	public static int COLUMNA_COD_CLIENTE_ALFA= 0;
	public static int COLUMNA_MOTIVO_AUTORIZACION= 2;
	public static int COLUMNA_DESCRIPCION_MOTIVO_AUTORIZACION= 3;
	public static int COLUMNA_APELLIDO_CHOFER= 4;
	public static int COLUMNA_NOMBRE_CHOFER= 5;
	public static int COLUMNA_PATENTE= 6;
	public static int COLUMNA_TIPO_COMPROBANTE_MANUAL= 7;
	public static int COLUMNA_SUCURSAL_MANUAL= 8;
	public static int COLUMNA_NRO_REMITO_MANUAL= 9;
	public static int COLUMNA_PRODCUTO= 10;
	public static int COLUMNA_LITROS= 11;
	public static int COLUMNA_TIPO_COMPROBANTE_ANULAR= 12;
	public static int COLUMNA_SUCURSAL_ANULAR= 13;
	public static int COLUMNA_NRO_REMIITO_ANULAR= 14;
	public static int COLUMNA_NRO_AUTORIZACION= 15;
	public static String PARAM_PORCENTAJE_EXCLUSION= "porcentajeExclusion"; 
	public static String PARAM_FECHA_AUX= "fechaAux";																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													
	public static String PARAM_TIPO_EXCLUSION= "tipoExclusion";
	public static String SEPARADOR_ARCHIVO= "   ";
	public static String SEPARADOR_ARCHIVO_2= " ";
	
	public static String VISTA_PREVIA_TXT= "vistaPreviaTxt";
	public static String IMPRESION_DEFINITVA_TXT= "impresionDefinitivaTxt";
	public static String HOJA_DE_CALCULO_XLS= "hojaDeCalculoXls";
}



