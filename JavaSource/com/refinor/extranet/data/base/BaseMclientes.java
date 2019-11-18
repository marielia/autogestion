package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MClientes table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MClientes"
 */

public abstract class BaseMclientes  implements Serializable {

	public static String REF = "Mclientes";
	public static String PROP_NOMBRE_FANTASIA = "nombreFantasia";
	public static String PROP_CUIT = "cuit";
	public static String PROP_SUMA_PTOS_TAR = "sumaPtosTar";
	public static String PROP_TOPE_PZO_FAC = "topePzoFac";
	public static String PROP_EMITE_RTO = "emiteRto";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_PAIS_PAGO = "paisPago";
	public static String PROP_RTO_DE_FC = "rtoDeFc";
	public static String PROP_CHEQUE_RECH = "chequeRech";
	public static String PROP_DOMICILIO = "domicilio";
	public static String PROP_PCIA = "pcia";
	public static String PROP_PEDIR_KM = "pedirKm";
	public static String PROP_VER_TOPE_CREDITO = "verTopeCredito";
	public static String PROP_CAT_IVA = "catIva";
	public static String PROP_DESCRIPCION = "descripcion";
	public static String PROP_CORRESPONDENCIA = "correspondencia";
	public static String PROP_TOPE_CREDITO = "topeCredito";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_PAIS = "pais";
	public static String PROP_REFIPASS = "refipass";
	public static String PROP_COD_CLIENTE_ALFA = "codClienteAlfa";
	public static String PROP_NO_DIS_UNIDAD = "noDisUnidad";
	public static String PROP_IMAIL = "imail";
	public static String PROP_INT_DIA = "intDia";
	public static String PROP_DOMICILIO_PAGO = "domicilioPago";
	public static String PROP_COD_JUR_DGI = "codJurDgi";
	public static String PROP_DT_VEHICULO = "dtVehiculo";
	public static String PROP_CONTACTO = "contacto";
	public static String PROP_TE = "te";
	public static String PROP_EXIGIR_ORD_CPRA = "exigirOrdCpra";
	public static String PROP_LISTA_PRECIO = "listaPrecio";
	public static String PROP_COD_COND_VTA = "codCondVta";
	public static String PROP_COD_INSCRIP_IB = "codInscripIb";
	public static String PROP_COD_ACTIVIDAD = "codActividad";
	public static String PROP_CREDITO_SUSP = "creditoSusp";
	public static String PROP_COD_IMPUESTO = "codImpuesto";
	public static String PROP_SUC_NRO = "sucNro";
	public static String PROP_LOCALIDAD_PAGO = "localidadPago";
	public static String PROP_COD_GR_AFINIDAD = "codGrAfinidad";
	public static String PROP_ACTIVO = "activo";
	public static String PROP_COD_P = "codP";
	public static String PROP_COD_ALFA = "codAlfa";
	public static String PROP_CTACTE_GLOBAL = "ctacteGlobal";
	public static String PROP_FH_BAJA = "fhBaja";
	public static String PROP_TOPE_PZO_CHE = "topePzoChe";
	public static String PROP_HIPER_CLIENTE = "hiperCliente";
	public static String PROP_BONIFICACION = "bonificacion";
	public static String PROP_CAT_GAN = "catGan";
	public static String PROP_PCIA_PAGO = "pciaPago";
	public static String PROP_NRO_IB = "nroIb";
	public static String PROP_COD_POST_PAGO = "codPostPago";
	public static String PROP_LOCALIDAD = "localidad";
	public static String PROP_COD_CTACTE = "codCtacte";


	// constructors
	public BaseMclientes () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMclientes (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.Integer hiperCliente;
	private java.lang.String descripcion;
	private java.lang.String nombreFantasia;
	private java.lang.String contacto;
	private java.lang.String imail;
	private java.lang.String sucNro;
	private java.lang.String te;
	private java.lang.String domicilio;
	private java.lang.Integer codP;
	private java.lang.String localidad;
	private java.lang.Integer pcia;
	private java.lang.String pais;
	private java.lang.String domicilioPago;
	private java.lang.Integer codPostPago;
	private java.lang.String localidadPago;
	private java.lang.Integer pciaPago;
	private java.lang.String paisPago;
	private java.lang.Integer catIva;
	private java.lang.Short listaPrecio;
	private java.lang.String cuit;
	private java.lang.Double topeCredito;
	private java.lang.Integer topePzoFac;
	private java.lang.Integer topePzoChe;
	private int idUserLock;
	private java.util.Date fhBaja;
	private java.lang.Integer catGan;
	private java.lang.Integer codJurDgi;
	private java.lang.Integer codImpuesto;
	private java.lang.String codAlfa;
	private java.lang.Boolean activo;
	private java.lang.Long nroIb;
	private java.math.BigDecimal bonificacion;
	private java.math.BigDecimal intDia;
	private java.lang.Boolean correspondencia;
	private java.lang.Boolean chequeRech;
	private java.lang.Boolean rtoDeFc;
	private java.lang.Boolean emiteRto;
	private java.lang.Boolean dtVehiculo;
	private java.lang.Boolean creditoSusp;
	private java.lang.Boolean sumaPtosTar;
	private java.lang.Boolean exigirOrdCpra;
	private java.lang.Integer codGrAfinidad;
	private java.lang.Integer codCondVta;
	private java.lang.Boolean refipass;
	private java.lang.Integer codCtacte;
	private java.lang.Boolean ctacteGlobal;
	private java.lang.Boolean pedirKm;
	private java.lang.Boolean noDisUnidad;
	private java.lang.Integer codInscripIb;
	private java.lang.Integer codActividad;
	private java.lang.String codClienteAlfa;
	private java.lang.Boolean verTopeCredito;
	



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="Codigo"
     */
	public int getCodigo () {
		return codigo;
	}

	/**
	 * Set the unique identifier of this class
	 * @param codigo the new ID
	 */
	public void setCodigo (int codigo) {
		this.codigo = codigo;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: HiperCliente
	 */
	public java.lang.Integer getHiperCliente () {
		return hiperCliente;
	}

	/**
	 * Set the value related to the column: HiperCliente
	 * @param hiperCliente the HiperCliente value
	 */
	public void setHiperCliente (java.lang.Integer hiperCliente) {
		this.hiperCliente = hiperCliente;
	}



	/**
	 * Return the value associated with the column: Descripcion
	 */
	public java.lang.String getDescripcion () {
		return descripcion;
	}

	/**
	 * Set the value related to the column: Descripcion
	 * @param descripcion the Descripcion value
	 */
	public void setDescripcion (java.lang.String descripcion) {
		this.descripcion = descripcion;
	}



	/**
	 * Return the value associated with the column: NombreFantasia
	 */
	public java.lang.String getNombreFantasia () {
		return nombreFantasia;
	}

	/**
	 * Set the value related to the column: NombreFantasia
	 * @param nombreFantasia the NombreFantasia value
	 */
	public void setNombreFantasia (java.lang.String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}



	/**
	 * Return the value associated with the column: Contacto
	 */
	public java.lang.String getContacto () {
		return contacto;
	}

	/**
	 * Set the value related to the column: Contacto
	 * @param contacto the Contacto value
	 */
	public void setContacto (java.lang.String contacto) {
		this.contacto = contacto;
	}



	/**
	 * Return the value associated with the column: IMail
	 */
	public java.lang.String getImail () {
		return imail;
	}

	/**
	 * Set the value related to the column: IMail
	 * @param imail the IMail value
	 */
	public void setImail (java.lang.String imail) {
		this.imail = imail;
	}



	/**
	 * Return the value associated with the column: SucNro
	 */
	public java.lang.String getSucNro () {
		return sucNro;
	}

	/**
	 * Set the value related to the column: SucNro
	 * @param sucNro the SucNro value
	 */
	public void setSucNro (java.lang.String sucNro) {
		this.sucNro = sucNro;
	}



	/**
	 * Return the value associated with the column: Te
	 */
	public java.lang.String getTe () {
		return te;
	}

	/**
	 * Set the value related to the column: Te
	 * @param te the Te value
	 */
	public void setTe (java.lang.String te) {
		this.te = te;
	}



	/**
	 * Return the value associated with the column: Domicilio
	 */
	public java.lang.String getDomicilio () {
		return domicilio;
	}

	/**
	 * Set the value related to the column: Domicilio
	 * @param domicilio the Domicilio value
	 */
	public void setDomicilio (java.lang.String domicilio) {
		this.domicilio = domicilio;
	}



	/**
	 * Return the value associated with the column: CodP
	 */
	public java.lang.Integer getCodP () {
		return codP;
	}

	/**
	 * Set the value related to the column: CodP
	 * @param codP the CodP value
	 */
	public void setCodP (java.lang.Integer codP) {
		this.codP = codP;
	}



	/**
	 * Return the value associated with the column: Localidad
	 */
	public java.lang.String getLocalidad () {
		return localidad;
	}

	/**
	 * Set the value related to the column: Localidad
	 * @param localidad the Localidad value
	 */
	public void setLocalidad (java.lang.String localidad) {
		this.localidad = localidad;
	}



	/**
	 * Return the value associated with the column: Pcia
	 */
	public java.lang.Integer getPcia () {
		return pcia;
	}

	/**
	 * Set the value related to the column: Pcia
	 * @param pcia the Pcia value
	 */
	public void setPcia (java.lang.Integer pcia) {
		this.pcia = pcia;
	}



	/**
	 * Return the value associated with the column: Pais
	 */
	public java.lang.String getPais () {
		return pais;
	}

	/**
	 * Set the value related to the column: Pais
	 * @param pais the Pais value
	 */
	public void setPais (java.lang.String pais) {
		this.pais = pais;
	}



	/**
	 * Return the value associated with the column: DomicilioPago
	 */
	public java.lang.String getDomicilioPago () {
		return domicilioPago;
	}

	/**
	 * Set the value related to the column: DomicilioPago
	 * @param domicilioPago the DomicilioPago value
	 */
	public void setDomicilioPago (java.lang.String domicilioPago) {
		this.domicilioPago = domicilioPago;
	}



	/**
	 * Return the value associated with the column: CodPostPago
	 */
	public java.lang.Integer getCodPostPago () {
		return codPostPago;
	}

	/**
	 * Set the value related to the column: CodPostPago
	 * @param codPostPago the CodPostPago value
	 */
	public void setCodPostPago (java.lang.Integer codPostPago) {
		this.codPostPago = codPostPago;
	}



	/**
	 * Return the value associated with the column: LocalidadPago
	 */
	public java.lang.String getLocalidadPago () {
		return localidadPago;
	}

	/**
	 * Set the value related to the column: LocalidadPago
	 * @param localidadPago the LocalidadPago value
	 */
	public void setLocalidadPago (java.lang.String localidadPago) {
		this.localidadPago = localidadPago;
	}



	/**
	 * Return the value associated with the column: PciaPago
	 */
	public java.lang.Integer getPciaPago () {
		return pciaPago;
	}

	/**
	 * Set the value related to the column: PciaPago
	 * @param pciaPago the PciaPago value
	 */
	public void setPciaPago (java.lang.Integer pciaPago) {
		this.pciaPago = pciaPago;
	}



	/**
	 * Return the value associated with the column: PaisPago
	 */
	public java.lang.String getPaisPago () {
		return paisPago;
	}

	/**
	 * Set the value related to the column: PaisPago
	 * @param paisPago the PaisPago value
	 */
	public void setPaisPago (java.lang.String paisPago) {
		this.paisPago = paisPago;
	}



	/**
	 * Return the value associated with the column: CatIva
	 */
	public java.lang.Integer getCatIva () {
		return catIva;
	}

	/**
	 * Set the value related to the column: CatIva
	 * @param catIva the CatIva value
	 */
	public void setCatIva (java.lang.Integer catIva) {
		this.catIva = catIva;
	}



	/**
	 * Return the value associated with the column: ListaPrecio
	 */
	public java.lang.Short getListaPrecio () {
		return listaPrecio;
	}

	/**
	 * Set the value related to the column: ListaPrecio
	 * @param listaPrecio the ListaPrecio value
	 */
	public void setListaPrecio (java.lang.Short listaPrecio) {
		this.listaPrecio = listaPrecio;
	}



	/**
	 * Return the value associated with the column: CUIT
	 */
	public java.lang.String getCuit () {
		return cuit;
	}

	/**
	 * Set the value related to the column: CUIT
	 * @param cuit the CUIT value
	 */
	public void setCuit (java.lang.String cuit) {
		this.cuit = cuit;
	}



	/**
	 * Return the value associated with the column: TopeCredito
	 */
	public java.lang.Double getTopeCredito () {
		return topeCredito;
	}

	/**
	 * Set the value related to the column: TopeCredito
	 * @param topeCredito the TopeCredito value
	 */
	public void setTopeCredito (java.lang.Double topeCredito) {
		this.topeCredito = topeCredito;
	}



	/**
	 * Return the value associated with the column: TopePzoFac
	 */
	public java.lang.Integer getTopePzoFac () {
		return topePzoFac;
	}

	/**
	 * Set the value related to the column: TopePzoFac
	 * @param topePzoFac the TopePzoFac value
	 */
	public void setTopePzoFac (java.lang.Integer topePzoFac) {
		this.topePzoFac = topePzoFac;
	}



	/**
	 * Return the value associated with the column: TopePzoChe
	 */
	public java.lang.Integer getTopePzoChe () {
		return topePzoChe;
	}

	/**
	 * Set the value related to the column: TopePzoChe
	 * @param topePzoChe the TopePzoChe value
	 */
	public void setTopePzoChe (java.lang.Integer topePzoChe) {
		this.topePzoChe = topePzoChe;
	}



	/**
	 * Return the value associated with the column: IdUserLock
	 */
	public int getIdUserLock () {
		return idUserLock;
	}

	/**
	 * Set the value related to the column: IdUserLock
	 * @param idUserLock the IdUserLock value
	 */
	public void setIdUserLock (int idUserLock) {
		this.idUserLock = idUserLock;
	}



	/**
	 * Return the value associated with the column: FhBaja
	 */
	public java.util.Date getFhBaja () {
		return fhBaja;
	}

	/**
	 * Set the value related to the column: FhBaja
	 * @param fhBaja the FhBaja value
	 */
	public void setFhBaja (java.util.Date fhBaja) {
		this.fhBaja = fhBaja;
	}



	/**
	 * Return the value associated with the column: CatGan
	 */
	public java.lang.Integer getCatGan () {
		return catGan;
	}

	/**
	 * Set the value related to the column: CatGan
	 * @param catGan the CatGan value
	 */
	public void setCatGan (java.lang.Integer catGan) {
		this.catGan = catGan;
	}



	/**
	 * Return the value associated with the column: CodJurDGI
	 */
	public java.lang.Integer getCodJurDgi () {
		return codJurDgi;
	}

	/**
	 * Set the value related to the column: CodJurDGI
	 * @param codJurDgi the CodJurDGI value
	 */
	public void setCodJurDgi (java.lang.Integer codJurDgi) {
		this.codJurDgi = codJurDgi;
	}



	/**
	 * Return the value associated with the column: cod_impuesto
	 */
	public java.lang.Integer getCodImpuesto () {
		return codImpuesto;
	}

	/**
	 * Set the value related to the column: cod_impuesto
	 * @param codImpuesto the cod_impuesto value
	 */
	public void setCodImpuesto (java.lang.Integer codImpuesto) {
		this.codImpuesto = codImpuesto;
	}



	/**
	 * Return the value associated with the column: cod_alfa
	 */
	public java.lang.String getCodAlfa () {
		return codAlfa;
	}

	/**
	 * Set the value related to the column: cod_alfa
	 * @param codAlfa the cod_alfa value
	 */
	public void setCodAlfa (java.lang.String codAlfa) {
		this.codAlfa = codAlfa;
	}



	/**
	 * Return the value associated with the column: activo
	 */
	public java.lang.Boolean isActivo () {
		return activo;
	}

	/**
	 * Set the value related to the column: activo
	 * @param activo the activo value
	 */
	public void setActivo (java.lang.Boolean activo) {
		this.activo = activo;
	}



	/**
	 * Return the value associated with the column: nro_ib
	 */
	public java.lang.Long getNroIb () {
		return nroIb;
	}

	/**
	 * Set the value related to the column: nro_ib
	 * @param nroIb the nro_ib value
	 */
	public void setNroIb (java.lang.Long nroIb) {
		this.nroIb = nroIb;
	}



	/**
	 * Return the value associated with the column: bonificacion
	 */
	public java.math.BigDecimal getBonificacion () {
		return bonificacion;
	}

	/**
	 * Set the value related to the column: bonificacion
	 * @param bonificacion the bonificacion value
	 */
	public void setBonificacion (java.math.BigDecimal bonificacion) {
		this.bonificacion = bonificacion;
	}



	/**
	 * Return the value associated with the column: int_dia
	 */
	public java.math.BigDecimal getIntDia () {
		return intDia;
	}

	/**
	 * Set the value related to the column: int_dia
	 * @param intDia the int_dia value
	 */
	public void setIntDia (java.math.BigDecimal intDia) {
		this.intDia = intDia;
	}



	/**
	 * Return the value associated with the column: correspondencia
	 */
	public java.lang.Boolean isCorrespondencia () {
		return correspondencia;
	}

	/**
	 * Set the value related to the column: correspondencia
	 * @param correspondencia the correspondencia value
	 */
	public void setCorrespondencia (java.lang.Boolean correspondencia) {
		this.correspondencia = correspondencia;
	}



	/**
	 * Return the value associated with the column: cheque_rech
	 */
	public java.lang.Boolean isChequeRech () {
		return chequeRech;
	}

	/**
	 * Set the value related to the column: cheque_rech
	 * @param chequeRech the cheque_rech value
	 */
	public void setChequeRech (java.lang.Boolean chequeRech) {
		this.chequeRech = chequeRech;
	}



	/**
	 * Return the value associated with the column: rto_de_fc
	 */
	public java.lang.Boolean isRtoDeFc () {
		return rtoDeFc;
	}

	/**
	 * Set the value related to the column: rto_de_fc
	 * @param rtoDeFc the rto_de_fc value
	 */
	public void setRtoDeFc (java.lang.Boolean rtoDeFc) {
		this.rtoDeFc = rtoDeFc;
	}



	/**
	 * Return the value associated with the column: emite_rto
	 */
	public java.lang.Boolean isEmiteRto () {
		return emiteRto;
	}

	/**
	 * Set the value related to the column: emite_rto
	 * @param emiteRto the emite_rto value
	 */
	public void setEmiteRto (java.lang.Boolean emiteRto) {
		this.emiteRto = emiteRto;
	}



	/**
	 * Return the value associated with the column: dt_vehiculo
	 */
	public java.lang.Boolean isDtVehiculo () {
		return dtVehiculo;
	}

	/**
	 * Set the value related to the column: dt_vehiculo
	 * @param dtVehiculo the dt_vehiculo value
	 */
	public void setDtVehiculo (java.lang.Boolean dtVehiculo) {
		this.dtVehiculo = dtVehiculo;
	}



	/**
	 * Return the value associated with the column: credito_susp
	 */
	public java.lang.Boolean isCreditoSusp () {
		return creditoSusp;
	}

	/**
	 * Set the value related to the column: credito_susp
	 * @param creditoSusp the credito_susp value
	 */
	public void setCreditoSusp (java.lang.Boolean creditoSusp) {
		this.creditoSusp = creditoSusp;
	}



	/**
	 * Return the value associated with the column: suma_ptos_tar
	 */
	public java.lang.Boolean isSumaPtosTar () {
		return sumaPtosTar;
	}

	/**
	 * Set the value related to the column: suma_ptos_tar
	 * @param sumaPtosTar the suma_ptos_tar value
	 */
	public void setSumaPtosTar (java.lang.Boolean sumaPtosTar) {
		this.sumaPtosTar = sumaPtosTar;
	}



	/**
	 * Return the value associated with the column: exigir_ord_cpra
	 */
	public java.lang.Boolean isExigirOrdCpra () {
		return exigirOrdCpra;
	}

	/**
	 * Set the value related to the column: exigir_ord_cpra
	 * @param exigirOrdCpra the exigir_ord_cpra value
	 */
	public void setExigirOrdCpra (java.lang.Boolean exigirOrdCpra) {
		this.exigirOrdCpra = exigirOrdCpra;
	}



	/**
	 * Return the value associated with the column: cod_gr_afinidad
	 */
	public java.lang.Integer getCodGrAfinidad () {
		return codGrAfinidad;
	}

	/**
	 * Set the value related to the column: cod_gr_afinidad
	 * @param codGrAfinidad the cod_gr_afinidad value
	 */
	public void setCodGrAfinidad (java.lang.Integer codGrAfinidad) {
		this.codGrAfinidad = codGrAfinidad;
	}



	/**
	 * Return the value associated with the column: cod_cond_vta
	 */
	public java.lang.Integer getCodCondVta () {
		return codCondVta;
	}

	/**
	 * Set the value related to the column: cod_cond_vta
	 * @param codCondVta the cod_cond_vta value
	 */
	public void setCodCondVta (java.lang.Integer codCondVta) {
		this.codCondVta = codCondVta;
	}



	/**
	 * Return the value associated with the column: refipass
	 */
	public java.lang.Boolean isRefipass () {
		return refipass;
	}

	/**
	 * Set the value related to the column: refipass
	 * @param refipass the refipass value
	 */
	public void setRefipass (java.lang.Boolean refipass) {
		this.refipass = refipass;
	}



	/**
	 * Return the value associated with the column: cod_ctacte
	 */
	public java.lang.Integer getCodCtacte () {
		return codCtacte;
	}

	/**
	 * Set the value related to the column: cod_ctacte
	 * @param codCtacte the cod_ctacte value
	 */
	public void setCodCtacte (java.lang.Integer codCtacte) {
		this.codCtacte = codCtacte;
	}



	/**
	 * Return the value associated with the column: ctacte_global
	 */
	public java.lang.Boolean isCtacteGlobal () {
		return ctacteGlobal;
	}

	/**
	 * Set the value related to the column: ctacte_global
	 * @param ctacteGlobal the ctacte_global value
	 */
	public void setCtacteGlobal (java.lang.Boolean ctacteGlobal) {
		this.ctacteGlobal = ctacteGlobal;
	}



	/**
	 * Return the value associated with the column: pedir_km
	 */
	public java.lang.Boolean isPedirKm () {
		return pedirKm;
	}

	/**
	 * Set the value related to the column: pedir_km
	 * @param pedirKm the pedir_km value
	 */
	public void setPedirKm (java.lang.Boolean pedirKm) {
		this.pedirKm = pedirKm;
	}



	/**
	 * Return the value associated with the column: NO_Dis_Unidad
	 */
	public java.lang.Boolean isNoDisUnidad () {
		return noDisUnidad;
	}

	/**
	 * Set the value related to the column: NO_Dis_Unidad
	 * @param noDisUnidad the NO_Dis_Unidad value
	 */
	public void setNoDisUnidad (java.lang.Boolean noDisUnidad) {
		this.noDisUnidad = noDisUnidad;
	}



	/**
	 * Return the value associated with the column: cod_inscripIB
	 */
	public java.lang.Integer getCodInscripIb () {
		return codInscripIb;
	}

	/**
	 * Set the value related to the column: cod_inscripIB
	 * @param codInscripIb the cod_inscripIB value
	 */
	public void setCodInscripIb (java.lang.Integer codInscripIb) {
		this.codInscripIb = codInscripIb;
	}



	/**
	 * Return the value associated with the column: cod_actividad
	 */
	public java.lang.Integer getCodActividad () {
		return codActividad;
	}

	/**
	 * Set the value related to the column: cod_actividad
	 * @param codActividad the cod_actividad value
	 */
	public void setCodActividad (java.lang.Integer codActividad) {
		this.codActividad = codActividad;
	}



	/**
	 * Return the value associated with the column: cod_cliente_alfa
	 */
	public java.lang.String getCodClienteAlfa () {
		return codClienteAlfa;
	}

	/**
	 * Set the value related to the column: cod_cliente_alfa
	 * @param codClienteAlfa the cod_cliente_alfa value
	 */
	public void setCodClienteAlfa (java.lang.String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
	}



	/**
	 * Return the value associated with the column: ver_topeCredito
	 */
	public java.lang.Boolean isVerTopeCredito () {
		return verTopeCredito;
	}

	/**
	 * Set the value related to the column: ver_topeCredito
	 * @param verTopeCredito the ver_topeCredito value
	 */
	public void setVerTopeCredito (java.lang.Boolean verTopeCredito) {
		this.verTopeCredito = verTopeCredito;
	}



	


	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Mclientes)) return false;
		else {
			com.refinor.extranet.data.Mclientes mclientes = (com.refinor.extranet.data.Mclientes) obj;
			return (this.getCodigo() == mclientes.getCodigo());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getCodigo();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}