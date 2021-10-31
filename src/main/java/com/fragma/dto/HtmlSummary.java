package com.fragma.dto;

public class HtmlSummary {

    String unit;

    String exitDate ;
    Long totalVolume ;
    Long cleanVolume ;
    Long cleanVolSlaMet ;
    Long cleanVolSlaNotMet;

    Long refferalVolume;
    Long refferalVolSlaMet ;
    Long refferalVolSlaNotMet;
    Long awaitingCover;

    String application ;

    private  Long cleanVolumePercentage;
    private Long cleanSlaMetPercentage;
    private Long refferedVolPercentage;
    private Long refferedSlaMetPercentage;

    private  Long opsMissesVol;
    private  Long ITMissesVol;

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

    public Long getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Long totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Long getCleanVolume() {
        return cleanVolume;
    }

    public void setCleanVolume(Long cleanVolume) {
        this.cleanVolume = cleanVolume;
    }

    public Long getCleanVolSlaMet() {
        return cleanVolSlaMet;
    }

    public void setCleanVolSlaMet(Long cleanVolSlaMet) {
        this.cleanVolSlaMet = cleanVolSlaMet;
    }

    public Long getCleanVolSlaNotMet() {
        return cleanVolSlaNotMet;
    }

    public void setCleanVolSlaNotMet(Long cleanVolSlaNotMet) {
        this.cleanVolSlaNotMet = cleanVolSlaNotMet;
    }

    public Long getRefferalVolume() {
        return refferalVolume;
    }

    public void setRefferalVolume(Long refferalVolume) {
        this.refferalVolume = refferalVolume;
    }

    public Long getRefferalVolSlaMet() {
        return refferalVolSlaMet;
    }

    public void setRefferalVolSlaMet(Long refferalVolSlaMet) {
        this.refferalVolSlaMet = refferalVolSlaMet;
    }

    public Long getRefferalVolSlaNotMet() {
        return refferalVolSlaNotMet;
    }

    public void setRefferalVolSlaNotMet(Long refferalVolSlaNotMet) {
        this.refferalVolSlaNotMet = refferalVolSlaNotMet;
    }

    public Long getAwaitingCover() {
        return awaitingCover;
    }

    public void setAwaitingCover(Long awaitingCover) {
        this.awaitingCover = awaitingCover;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Long getCleanVolumePercentage() {
        return cleanVolumePercentage;
    }

    public void setCleanVolumePercentage(Long cleanVolumePercentage) {
        this.cleanVolumePercentage = cleanVolumePercentage;
    }

    public Long getCleanSlaMetPercentage() {
        return cleanSlaMetPercentage;
    }

    public void setCleanSlaMetPercentage(Long cleanSlaMetPercentage) {
        this.cleanSlaMetPercentage = cleanSlaMetPercentage;
    }

    public Long getRefferedVolPercentage() {
        return refferedVolPercentage;
    }

    public void setRefferedVolPercentage(Long refferedVolPercentage) {
        this.refferedVolPercentage = refferedVolPercentage;
    }

    public Long getRefferedSlaMetPercentage() {
        return refferedSlaMetPercentage;
    }

    public void setRefferedSlaMetPercentage(Long refferedSlaMetPercentage) {
        this.refferedSlaMetPercentage = refferedSlaMetPercentage;
    }

    public Long getOpsMissesVol() {
        return opsMissesVol;
    }

    public void setOpsMissesVol(Long opsMissesVol) {
        this.opsMissesVol = opsMissesVol;
    }

    public Long getITMissesVol() {
        return ITMissesVol;
    }

    public void setITMissesVol(Long ITMissesVol) {
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
