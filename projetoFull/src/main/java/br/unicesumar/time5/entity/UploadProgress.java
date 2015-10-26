package br.unicesumar.time5.entity;

import java.io.Serializable;

/**
 *
 * @author PauloHenrique
 */
public class UploadProgress implements Serializable {

    private static final long serialVersionUID = 1L;

    private double parsingProgress = 0;
    private double mappingProgress = 0;
    private double retrievalProgress = 0;
    private double scoreProgress = 0;
    private double updateProgress = 0;

    public UploadProgress() {

    }

    public void setParsingProgress(double parsingProgress) {
        this.parsingProgress = parsingProgress;
    }

    public double getParsingProgress() {
        return parsingProgress;
    }

    public void setMappingProgress(double mappingProgress) {
        this.mappingProgress = mappingProgress;
    }

    public double getMappingProgress() {
        return mappingProgress;
    }

    public void setRetrievalProgress(double retrievalProgress) {
        this.retrievalProgress = retrievalProgress;
    }

    public double getRetrievalProgress() {
        return retrievalProgress;
    }

    public void setScoreProgress(double scoreProgress) {
        this.scoreProgress = scoreProgress;
    }

    public double getScoreProgress() {
        return scoreProgress;
    }

    public void setUpdateProgress(double updateProgress) {
        this.updateProgress = updateProgress;
    }

    public double getUpdateProgress() {
        return updateProgress;
    }
}
