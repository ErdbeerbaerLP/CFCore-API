package de.erdbeerbaerlp.cfcore.exceptions;

public class InvalidAPIKeyException extends RuntimeException {
    public InvalidAPIKeyException() {
        super("CFCore API Key invalid or unset");
    }
}
