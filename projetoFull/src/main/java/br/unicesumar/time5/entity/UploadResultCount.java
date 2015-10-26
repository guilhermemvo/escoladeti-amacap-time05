package br.unicesumar.time5.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PauloHenrique
 */
public class UploadResultCount implements Serializable {

    private static final long serialVersionUID = 1L;

    public int numBaits = 0;//
    public int numPreys = 0;//
    public int numExperiments = 0;//
    public int numControls = 0;
    public int numInteractions = 0;//
    public int numUniqueInteractions = 0;//
    public int numPreysNotMapped = 0;//
    public int numBaitsNotMapped = 0;//
    public int numExperimentsNotMapped = 0;//
    public int numInteractionsNotMapped = 0;//
    public int numUniqueInteractionsNotMapped = 0;//
    public Map<AffinityPurificationType, Integer> numExpPerAffinityPurifcation = new HashMap<AffinityPurificationType, Integer>();
    public Map<AffinityPurificationType, Integer> numControlPerAffinityPurifcation = new HashMap<AffinityPurificationType, Integer>();

    public UploadResultCount() {

    }

    public UploadResultCount(int numBaits, int numPreys, int numExperiments, int numControls, int numInteractions,
            int numUniqueInteractions, Map<AffinityPurificationType, Integer> numExpPerAffinityPurifcation,
            Map<AffinityPurificationType, Integer> numControlPerAffinityPurifcation) {
        this.numBaits = numBaits;
        this.numPreys = numPreys;
        this.numExperiments = numExperiments;
        this.numControls = numControls;
        this.numInteractions = numInteractions;
        this.numUniqueInteractions = numUniqueInteractions;
        this.numExpPerAffinityPurifcation = numExpPerAffinityPurifcation;
        this.numControlPerAffinityPurifcation = numControlPerAffinityPurifcation;
    }

    public int getNumBaits() {
        return numBaits;
    }

    public void setNumBaits(final int numBaits) {
        this.numBaits = numBaits;
    }

    public int getNumPreys() {
        return numPreys;
    }

    public void setNumPreys(final int numPreys) {
        this.numPreys = numPreys;
    }

    public int getNumExperiments() {
        return numExperiments;
    }

    public void setNumExperiments(final int numExperiments) {
        this.numExperiments = numExperiments;
    }

    public int getNumControls() {
        return numControls;
    }

    public void setNumControls(final int numControls) {
        this.numControls = numControls;
    }

    public int getNumInteractions() {
        return numInteractions;
    }

    public void setNumInteractions(final int numInteractions) {
        this.numInteractions = numInteractions;
    }

    public int getNumUniqueInteractions() {
        return numUniqueInteractions;
    }

    public void setNumUniqueInteractions(final int numUniqueInteractions) {
        this.numUniqueInteractions = numUniqueInteractions;
    }

    public Map<AffinityPurificationType, Integer> getNumExpPerAffinityPurifcation() {
        return numExpPerAffinityPurifcation;
    }

    public void setNumExpPerAffinityPurifcation(final Map<AffinityPurificationType, Integer> numExpPerAffinityPurifcation) {
        this.numExpPerAffinityPurifcation = numExpPerAffinityPurifcation;
    }

    public Map<AffinityPurificationType, Integer> getNumControlPerAffinityPurifcation() {
        return numControlPerAffinityPurifcation;
    }

    public void setNumControlPerAffinityPurifcation(final Map<AffinityPurificationType, Integer> numControlPerAffinityPurifcation) {
        this.numControlPerAffinityPurifcation = numControlPerAffinityPurifcation;
    }

    public int getNumBaitsNotMapped() {
        return numBaitsNotMapped;
    }

    public void setNumBaitsNotMapped(final int numBaitsNotMapped) {
        this.numBaitsNotMapped = numBaitsNotMapped;
    }

    public int getNumPreysNotMapped() {
        return numPreysNotMapped;
    }

    public void setNumPreysNotMapped(final int numPreysNotMapped) {
        this.numPreysNotMapped = numPreysNotMapped;
    }

    public int getNumInteractionsNotMapped() {
        return numInteractionsNotMapped;
    }

    public void setNumInteractionsNotMapped(final int numInteractionsNotMapped) {
        this.numInteractionsNotMapped = numInteractionsNotMapped;
    }

    public int getNumUniqueInteractionsNotMapped() {
        return numUniqueInteractionsNotMapped;
    }

    public void setNumUniqueInteractionsNotMapped(final int numUniqueInteractionsNotMapped) {
        this.numUniqueInteractionsNotMapped = numUniqueInteractionsNotMapped;
    }

    public int getNumExperimentsNotMapped() {
        return numExperimentsNotMapped;
    }

    public void setNumExperimentsNotMapped(final int numExperimentsNotMapped) {
        this.numExperimentsNotMapped = numExperimentsNotMapped;
    }

}
