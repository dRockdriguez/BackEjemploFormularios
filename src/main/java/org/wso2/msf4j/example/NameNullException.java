package org.wso2.msf4j.example;

public class NameNullException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1501196532316823129L;

	public NameNullException() {
	        super();
	    }

	    public NameNullException(String message) {
	        super(message);
	    }

	    public NameNullException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public NameNullException(Throwable cause) {
	        super(cause);
	    }

	    protected NameNullException(String message, Throwable cause,
	                                      boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }
}
