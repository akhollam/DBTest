package com.exceptions;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String messae) {
		super(messae);
	}

	public ServiceException(String messae, Exception e) {
		super(messae, e);
	}

}
