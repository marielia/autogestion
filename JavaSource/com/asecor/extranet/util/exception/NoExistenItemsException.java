package com.asecor.extranet.util.exception;

	/**
	 * @author mliz
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
public class NoExistenItemsException extends Exception {

		/**
		 * @param message
		 * @param cause
		 */
		public NoExistenItemsException(String message, Throwable cause) {
			super(message, cause);
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param message
		 */
		public NoExistenItemsException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param cause
		 */
		public NoExistenItemsException(Throwable cause) {
			super(cause);
			// TODO Auto-generated constructor stub
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NoExistenItemsException() {
			super("No existen Items.");	
		}
}
