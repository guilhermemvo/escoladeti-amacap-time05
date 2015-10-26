package br.unicesumar.time5.entity;

import br.unicesumar.time5.exceptions.FlashlightException;
import java.io.Serializable;

/**
 *
 * @author PauloHenrique
 */
public class UploadError implements Serializable {

    private static final long serialVersionUID = 1L;

    private FlashlightException exception;
    private int row;
    private int col;
    private String data;

    public UploadError() {

    }

    public UploadError(final int row, final int col, final String data, final FlashlightException e) {
        this.exception = e;
        this.row = row;
        this.col = col;
        this.data = data;
    }

    public FlashlightException getException() {
        return exception;
    }

    public void setException(final FlashlightException exception) {
        this.exception = exception;
    }

    public int getRow() {
        return row;
    }

    public void setRow(final int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(final int col) {
        this.col = col;
    }

    public String getData() {
        return data;
    }

    public void setData(final String data) {
        this.data = data;
    }

}
