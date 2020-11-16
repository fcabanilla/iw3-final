package iua.edu.ar.business.exception;

public class PasswordException extends Exception {

	private static final long serialVersionUID = 931105484889458525L;

	public PasswordException() {
		
	}

	public PasswordException(String message) {
		super(message);
		
	}

	public PasswordException(Throwable cause) {
		super(cause);
		
	}

	public PasswordException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public PasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
