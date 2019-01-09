package com.bskyb.internettv.parental_control_service.services.exception;

public class TechnicalFailureException extends Exception {

    public TechnicalFailureException(String message, Exception e) {
        super(message, e);
    }
}
