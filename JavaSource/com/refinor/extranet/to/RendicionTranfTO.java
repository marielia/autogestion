package com.refinor.extranet.to;

import java.io.Serializable;
import java.util.List;

public class RendicionTranfTO implements Serializable {
private MCuentaBancoTO rendicion;
private List<MCuentaBancoTO> lstTransferencias;
/**
 * 
 */
public RendicionTranfTO() {
	super();
	// TODO Auto-generated constructor stub
}
public List<MCuentaBancoTO> getLstTransferencias() {
	return lstTransferencias;
}
public void setLstTransferencias(List<MCuentaBancoTO> lstTransferencias) {
	this.lstTransferencias = lstTransferencias;
}
public MCuentaBancoTO getRendicion() {
	return rendicion;
}
public void setRendicion(MCuentaBancoTO rendicion) {
	this.rendicion = rendicion;
}

}
