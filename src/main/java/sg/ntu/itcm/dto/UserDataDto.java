package sg.ntu.itcm.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDataDto {
	
	@NotNull
    @Size(min = 1)
    private String uid;
	
	@NotNull
    @Size(min = 1)
    private String timestamp;
	
	@NotNull
    @Size(min = 1)
    private String airTemp;
	
	@NotNull
    @Size(min = 1)
    private String airHumidity;
	
	@NotNull
    @Size(min = 1)
    private String airVelocity;
	
	@NotNull
    @Size(min = 1)
    private String meanRadiantTemp;
	
	@NotNull
    @Size(min = 1)
    private String metabolicRate;
	
	@NotNull
    @Size(min = 1)
    private String clothingLevel;
	
	@NotNull
    @Size(min = 1)
    private String heartRate;
	
	@NotNull
    @Size(min = 1)
    private String rrInterval;
	
	@NotNull
    @Size(min = 1)
    private String gsr;
	
	@NotNull
    @Size(min = 1)
    private String skinTemp;
	
	@NotNull
    @Size(min = 1)
    private String userFeedback;
	
	public String getUserID() {
        return uid;
    }

    public void setUserID(final String uid) {
        this.uid = uid;
    }
    
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getAirTemperature() {
        return airTemp;
    }

    public void setAirTemperature(final String airTemp) {
        this.airTemp = airTemp;
    }
    
    public String getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(final String airHumidity) {
        this.airHumidity = airHumidity;
    }
    
    public String getAirVelocity() {
        return airVelocity;
    }

    public void setAirVelocity(final String airVelocity) {
        this.airVelocity = airVelocity;
    }
    
    public String getMeanRadiantTemperature() {
        return meanRadiantTemp;
    }

    public void setMeanRadiantTemperature(final String meanRadiantTemp) {
        this.meanRadiantTemp = meanRadiantTemp;
    }
    
    public String getMetabolicRate() {
        return metabolicRate;
    }

    public void setMetabolicRate(final String metabolicRate) {
        this.metabolicRate = metabolicRate;
    }
    
    public String getClothingLevel() {
        return clothingLevel;
    }

    public void setClothingLevel(final String clothingLevel) {
        this.clothingLevel = clothingLevel;
    }
    
    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(final String heartRate) {
        this.heartRate = heartRate;
    }
    
    public String getRRInterval() {
        return rrInterval;
    }

    public void setRRInterval(final String rrInterval) {
        this.rrInterval = rrInterval;
    }
    
    public String getGSR() {
        return gsr;
    }

    public void setGSR(final String gsr) {
        this.gsr = gsr;
    }
    
    public String getSkinTemperature() {
        return skinTemp;
    }

    public void setSkinTemperature(final String skinTemp) {
        this.skinTemp = skinTemp;
    }
    
    public String getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(final String userFeedback) {
        this.userFeedback = userFeedback;
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [Uid=").append(uid).append("]")
        .append("[AirTemp=").append(airTemp).append("]")
        .append("[AirHumidity=").append(airHumidity).append("]")
        .append("[AirVelocity=").append(airVelocity).append("]")
        .append("[MeanRadiantTemp=").append(meanRadiantTemp).append("]")
        .append("[MetabolicRate=").append(metabolicRate).append("]")
        .append("[ClothingLevel=").append(clothingLevel).append("]")
        .append("[HeartRate=").append(heartRate).append("]")
        .append("[RRInterval=").append(rrInterval).append("]")
        .append("[GSR=").append(gsr).append("]")
        .append("[SkinTemp=").append(skinTemp).append("]")
        .append("[UserFeedback=").append(userFeedback).append("]");
        return builder.toString();
    }
}
