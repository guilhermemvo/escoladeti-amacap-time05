package br.unicesumar.time5.entity;

/**
 *
 * @author PauloHenrique
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UploadResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> errors;
    private UploadResultCount resultCount;
    private String filename;
    private String status;
    private String message;

    public UploadResult() {
        errors = new ArrayList<>();
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UploadResult(String filename, String status, String message) {
        this.filename = filename;
        this.status = status;
        this.message = message;
    }


    public UploadResultCount getResultCount() {
        return resultCount;
    }

    public void setResultCount(final UploadResultCount resultCount) {
        this.resultCount = resultCount;
        
    }

    public String getFilename() {
        return filename;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
    
}
