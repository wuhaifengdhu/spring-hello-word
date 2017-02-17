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
    private String accelerationX;
    @NotNull
    @Size(min = 1)
    private String accelerationY;
    @NotNull
    @Size(min = 1)
    private String accelerationZ;
    
    @NotNull
    @Size(min = 1)
    private String angularVelocityX;
    @NotNull
    @Size(min = 1)
    private String angularVelocityY;
    @NotNull
    @Size(min = 1)
    private String angularVelocityZ;
	
    @NotNull
    @Size(min = 1)
    private String userFeedback;
	
	public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }
    
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(final String airTemp) {
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
    
    public String getMeanRadiantTemp() {
        return meanRadiantTemp;
    }

    public void setMeanRadiantTemp(final String meanRadiantTemp) {
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
    
    public String getRrInterval() {
        return rrInterval;
    }

    public void setRrInterval(final String rrInterval) {
        this.rrInterval = rrInterval;
    }
    
    public String getGsr() {
        return gsr;
    }

    public void setGsr(final String gsr) {
        this.gsr = gsr;
    }
    
    public String getSkinTemp() {
        return skinTemp;
    }

    public void setSkinTemp(final String skinTemp) {
        this.skinTemp = skinTemp;
    }
    
    public String getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(final String accelerationX) {
        this.accelerationX = accelerationX;
    }
    
    public String getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(final String accelerationY) {
        this.accelerationY = accelerationY;
    }
    
    public String getAccelerationZ() {
        return accelerationZ;
    }

    public void setAccelerationZ(final String accelerationZ) {
        this.accelerationZ = accelerationZ;
    }
    
    public String getAngularVelocityX() {
        return angularVelocityX;
    }

    public void setAngularVelocityX(final String angularVelocityX) {
        this.angularVelocityX = angularVelocityX;
    }
    
    public String getAngularVelocityY() {
        return angularVelocityY;
    }

    public void setAngularVelocityY(final String angularVelocityY) {
        this.angularVelocityY = angularVelocityY;
    }
    
    public String getAngularVelocityZ() {
        return angularVelocityZ;
    }

    public void setAngularVelocityZ(final String angularVelocityZ) {
        this.angularVelocityZ = angularVelocityZ;
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
        .append("[Timestamp=").append(timestamp).append("]")
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
        .append("[AccelerationX=").append(accelerationX).append("]")
        .append("[AccelerationY=").append(accelerationY).append("]")
        .append("[AccelerationZ=").append(accelerationZ).append("]")
        .append("[AngularVelocityX=").append(angularVelocityX).append("]")
        .append("[AngularVelocityY=").append(angularVelocityY).append("]")
        .append("[AngularVelocityZ=").append(angularVelocityZ).append("]")
        .append("[UserFeedback=").append(userFeedback).append("]");
        return builder.toString();
    }
}
