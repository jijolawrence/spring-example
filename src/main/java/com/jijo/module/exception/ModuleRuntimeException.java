package com.jijo.module.exception;

/**
 * @author jijo.lawrence
 *
 */
public class ModuleRuntimeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 238161190996463880L;

    public ModuleRuntimeException(final String message) {
        super(message);
    }

    public ModuleRuntimeException(final Exception exception) {
        super(exception);
    }

    public ModuleRuntimeException(final String message, final Exception exception) {
        super(message, exception);
    }

}
