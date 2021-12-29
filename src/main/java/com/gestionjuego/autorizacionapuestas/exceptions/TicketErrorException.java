package com.gestionjuego.autorizacionapuestas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class TicketErrorException extends HttpStatusCodeException {
    private static final long serialVersionUID = 73263616501570402L;

    public TicketErrorException() {
        super(HttpStatus.NOT_FOUND);
    }

    public TicketErrorException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
