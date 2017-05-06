package com.nikitachizhik91.university.service;

public class DomainException extends Exception {

	
	private static final long serialVersionUID = 6281329582743513431L;

	public DomainException() {
	}

	public DomainException(String message) {
		super(message);
	}

	public DomainException(String message, Throwable cause) {
		super(message, cause);
	}

}
