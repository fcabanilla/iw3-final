package iua.edu.ar.business.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 931105484889458525L;

	public BusinessException() {
		
	}

	public BusinessException(String message) {
		super(message);
		
	}

	public BusinessException(Throwable cause) {
		super(cause);
		
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
