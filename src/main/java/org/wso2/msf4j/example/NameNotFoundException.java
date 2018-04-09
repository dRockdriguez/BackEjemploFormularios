package org.wso2.msf4j.example;

public class NameNotFoundException  extends Exception{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -6638733070557486594L;

	public NameNotFoundException() {
	        super();
	    }

	    public NameNotFoundException(String message) {
	        super(message);
	    }

	    public NameNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public NameNotFoundException(Throwable cause) {
	        super(cause);
	    }

	    protected NameNotFoundException(String message, Throwable cause,
	                                      boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }
}
