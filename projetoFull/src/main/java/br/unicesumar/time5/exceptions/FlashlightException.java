
package br.unicesumar.time5.exceptions;

import java.io.Serializable;

/**
 *
 * @author PauloHenrique
 */
public class FlashlightException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String message;

    public FlashlightException() {

    }

    public FlashlightException(String msg) {
        this.message = msg;
    }

    public String format() {
        return message;
    }

}
