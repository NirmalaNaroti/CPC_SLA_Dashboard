package com.fragma.dto;

public class HtmlSummary {

    String unit;

    String exitDate ;
    long totalVolume ;
    long cleanVolume ;
    long cleanVolSlaMet ;
    long cleanVolSlaNotMet;

    long refferalVolume;
    long refferalVolSlaMet ;
    long refferalVolSlaNotMet;
    long awaitingCover;

    String application ;

    private  long cleanVolumePercentage;
    private long cleanSlaMetPercentage;
    private long refferedVolPercentage;
    private long refferedSlaMetPercentage;

    private  long opsMissesVol;
    private  long ITMissesVol;

    double volumeWeight;
    double weightedCleanVolume;
    double weightedCleanVolumeSlaMet;
    double weightedReferralVolume;
    double weightedReferralVolumeSlaMet;


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }

    public long getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(long totalVolume) {
        this.totalVolume = totalVolume;
    }

    public long getCleanVolume() {
        return cleanVolume;
    }

    public void setCleanVolume(long cleanVolume) {
        this.cleanVolume = cleanVolume;
    }

    public long getCleanVolSlaMet() {
        return cleanVolSlaMet;
    }

    public void setCleanVolSlaMet(long cleanVolSlaMet) {
        this.cleanVolSlaMet = cleanVolSlaMet;
    }

    public long getCleanVolSlaNotMet() {
        return cleanVolSlaNotMet;
    }

    public void setCleanVolSlaNotMet(long cleanVolSlaNotMet) {
        this.cleanVolSlaNotMet = cleanVolSlaNotMet;
    }

    public long getRefferalVolume() {
        return refferalVolume;
    }

    public void setRefferalVolume(long refferalVolume) {
        this.refferalVolume = refferalVolume;
    }

    public long getRefferalVolSlaMet() {
        return refferalVolSlaMet;
    }

    public void setRefferalVolSlaMet(long refferalVolSlaMet) {
        this.refferalVolSlaMet = refferalVolSlaMet;
    }

    public long getRefferalVolSlaNotMet() {
        return refferalVolSlaNotMet;
    }

    public void setRefferalVolSlaNotMet(long refferalVolSlaNotMet) {
        this.refferalVolSlaNotMet = refferalVolSlaNotMet;
    }

    public long getAwaitingCover() {
        return awaitingCover;
    }

    public void setAwaitingCover(long awaitingCover) {
        this.awaitingCover = awaitingCover;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public long getCleanVolumePercentage() {
        return cleanVolumePercentage;
    }

    public void setCleanVolumePercentage(long cleanVolumePercentage) {
        this.cleanVolumePercentage = cleanVolumePercentage;
    }

    public long getCleanSlaMetPercentage() {
        return cleanSlaMetPercentage;
    }

    public void setCleanSlaMetPercentage(long cleanSlaMetPercentage) {
        this.cleanSlaMetPercentage = cleanSlaMetPercentage;
    }

    public long getRefferedVolPercentage() {
        return refferedVolPercentage;
    }

    public void setRefferedVolPercentage(long refferedVolPercentage) {
        this.refferedVolPercentage = refferedVolPercentage;
    }

    public long getRefferedSlaMetPercentage() {
        return refferedSlaMetPercentage;
    }

    public void setRefferedSlaMetPercentage(long refferedSlaMetPercentage) {
        this.refferedSlaMetPercentage = refferedSlaMetPercentage;
    }

    public long getOpsMissesVol() {
        return opsMissesVol;
    }

    public void setOpsMissesVol(long opsMissesVol) {
        this.opsMissesVol = opsMissesVol;
    }

    public long getITMissesVol() {
        return ITMissesVol;
    }

    public void setITMissesVol(long ITMissesVol) {
        this.ITMissesVol = ITMissesVol;
    }

    public double getVolumeWeight() {
        return volumeWeight;
    }

    public void setVolumeWeight(double volumeWeight) {
        this.volumeWeight = volumeWeight;
    }

    public double getWeightedCleanVolume() {
        return weightedCleanVolume;
    }

    public void setWeightedCleanVolume(double weightedCleanVolume) {
        this.weightedCleanVolume = weightedCleanVolume;
    }

    public double getWeightedCleanVolumeSlaMet() {
        return weightedCleanVolumeSlaMet;
    }

    public void setWeightedCleanVolumeSlaMet(double weightedCleanVolumeSlaMet) {
        this.weightedCleanVolumeSlaMet = weightedCleanVolumeSlaMet;
    }

    public double getWeightedReferralVolume() {
        return weightedReferralVolume;
    }

    public void setWeightedReferralVolume(double weightedReferralVolume) {
        this.weightedReferralVolume = weightedReferralVolume;
    }

    public double getWeightedReferralVolumeSlaMet() {
        return weightedReferralVolumeSlaMet;
    }

    public void setWeightedReferralVolumeSlaMet(double weightedReferralVolumeSlaMet) {
        this.weightedReferralVolumeSlaMet = weightedReferralVolumeSlaMet;
    }
}
