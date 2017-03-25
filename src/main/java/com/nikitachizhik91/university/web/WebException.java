package com.nikitachizhik91.university.web;

import javax.servlet.ServletException;

public class WebException extends ServletException {

	private static final long serialVersionUID = 6281329582743513431L;

	public WebException() {
	}

	public WebException(String message) {
		super(message);
	}

	public WebException(String message, Throwable cause) {
		super(message, cause);
	}

}
