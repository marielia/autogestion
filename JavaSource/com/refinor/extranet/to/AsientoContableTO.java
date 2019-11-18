package com.refinor.extranet.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class AsientoContableTO implements Serializable {

	private Integer ccddId;
	private String ccssDesc;
	private Integer nroEjercicio;
	private Integer nroAsiento;
	private String cuenta;
	private String debHab;
	private BigDecimal valor;
	private String leyenda;
	private String fecha;
	private String hora;
	private String codClienteAlfa;
	private String descripcionCliente;
	private BigDecimal saldo;
	private Integer ultimo;
	private String cuentaDesc;
	
	private String tipoComp;
	private String letra;
	private String tipoComprobante;
	private Integer nroSuc;
	private Integer nroComp;
	
	private BigDecimal valorDebe;
	private BigDecimal valorHaber;
	private String comprobanteAsociado;
	private String nroRendicion;
	private Integer idCuenta;
	
	private String debHab2;
	
	
	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getCuentaDesc() {
		return cuentaDesc;
	}

	public void setCuentaDesc(String cuentaDesc) {
		this.cuentaDesc = cuentaDesc;
	}

	public Integer getCcddId() {
		return ccddId;
	}

	public void setCcddId(Integer ccddId) {
		this.ccddId = ccddId;
	}

	public String getCcssDesc() {
		return ccssDesc;
	}

	public void setCcssDesc(String ccssDesc) {
		this.ccssDesc = ccssDesc;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDebHab() {
		return debHab;
	}

	public void setDebHab(String debHab) {
		this.debHab = debHab;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	

	public Integer getNroAsiento() {
		return nroAsiento;
	}

	public void setNroAsiento(Integer nroAsiento) {
		this.nroAsiento = nroAsiento;
	}

	public Integer getNroEjercicio() {
		return nroEjercicio;
	}

	public void setNroEjercicio(Integer nroEjercicio) {
		this.nroEjercicio = nroEjercicio;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public AsientoContableTO() {
		// TODO Auto-generated constructor stub
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getUltimo() {
		return ultimo;
	}

	public void setUltimo(Integer ultimo) {
		this.ultimo = ultimo;
	}

	

	

	public Integer getNroComp() {
		return nroComp;
	}

	public void setNroComp(Integer nroComp) {
		this.nroComp = nroComp;
	}

	public Integer getNroSuc() {
		return nroSuc;
	}

	public void setNroSuc(Integer nroSuc) {
		this.nroSuc = nroSuc;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public String getTipoComp() {
		return tipoComp;
	}

	public void setTipoComp(String tipoComp) {
		this.tipoComp = tipoComp;
	}

	public BigDecimal getValorDebe() {
		return valorDebe;
	}

	public void setValorDebe(BigDecimal valorDebe) {
		this.valorDebe = valorDebe;
	}

	public BigDecimal getValorHaber() {
		return valorHaber;
	}

	public void setValorHaber(BigDecimal valorHaber) {
		this.valorHaber = valorHaber;
	}

	public String getCodClienteAlfa() {
		return codClienteAlfa;
	}

	public void setCodClienteAlfa(String codClienteAlfa) {
		this.codClienteAlfa = codClienteAlfa;
	}

	public String getDescripcionCliente() {
		return descripcionCliente;
	}

	public void setDescripcionCliente(String descripcionCliente) {
		this.descripcionCliente = descripcionCliente;
	}

	public String getComprobanteAsociado() {
		return comprobanteAsociado;
	}

	public void setComprobanteAsociado(String comprobanteAsociado) {
		this.comprobanteAsociado = comprobanteAsociado;
	}

	public String getNroRendicion() {
		return nroRendicion;
	}

	public void setNroRendicion(String nroRendicion) {
		this.nroRendicion = nroRendicion;
	}

	public String getDebHab2() {
		return debHab2;
	}

	public void setDebHab2(String debHab2) {
		this.debHab2 = debHab2;
	}

	

}
