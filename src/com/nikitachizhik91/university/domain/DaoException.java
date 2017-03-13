package com.nikitachizhik91.university.domain;

public class DaoException extends Exception {

	private static final long serialVersionUID = -6584281854895616335L;

	public DaoException() {
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

}
