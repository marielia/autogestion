package com.refinor.extranet.to;

import java.io.Serializable;
import java.util.List;

import com.refinor.extranet.data.Mccss;

public class CcssRendicionTransferenciaTO implements Serializable {

	private Mccss ccss;
	private List<RendicionTranfTO> rendiciones;
	private String mensajeRendicion;
	public String getMensajeRendicion() {
		return mensajeRendicion;
	}
	public void setMensajeRendicion(String mensajeRendicion) {
		this.mensajeRendicion = mensajeRendicion;
	}
	/**
	 * 
	 */
	public CcssRendicionTransferenciaTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mccss getCcss() {
		return ccss;
	}
	public void setCcss(Mccss ccss) {
		this.ccss = ccss;
	}
	public List<RendicionTranfTO> getRendiciones() {
		return rendiciones;
	}
	public void setRendiciones(List<RendicionTranfTO> rendiciones) {
		this.rendiciones = rendiciones;
	}
	
	
}
