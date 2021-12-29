package com.gestionjuego.autorizacionapuestas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class AutorizacionApuestasException extends HttpStatusCodeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5635859347295571392L;

	public AutorizacionApuestasException() {
		super(HttpStatus.NOT_FOUND);
	}

	public AutorizacionApuestasException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
}
