package com.refinor.extranet.util.exception;

public class ChoferYaExisteException extends Exception {

	public ChoferYaExisteException() {
		// TODO Auto-generated constructor stub
		super("Ya existe un chofer con el DNI ingresado.");
	}

	public ChoferYaExisteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ChoferYaExisteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ChoferYaExisteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
