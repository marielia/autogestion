package com.asecor.extranet.util.exception;

	/**
	 * @author mliz
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
public class UsuarioNoConfirmadoException extends Exception {

		/**
		 * @param message
		 * @param cause
		 */
		public UsuarioNoConfirmadoException(String message, Throwable cause) {
			super(message, cause);
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param message
		 */
		public UsuarioNoConfirmadoException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param cause
		 */
		public UsuarioNoConfirmadoException(Throwable cause) {
			super(cause);
			// TODO Auto-generated constructor stub
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public UsuarioNoConfirmadoException() {
			super("No existen Items.");	
		}
}
