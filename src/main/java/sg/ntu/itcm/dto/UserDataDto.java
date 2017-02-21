package sg.ntu.itcm.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDataDto {

	@NotNull
    @Size(min = 1)
    private String uid;

	@NotNull
    @Size(min = 1)
    private String year;

	@NotNull
    @Size(min = 1)
    private String date;

	@NotNull
    @Size(min = 1)
    private String time;

	@NotNull
    @Size(min = 1)
    private String userFeedback;

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
    private String ambientLight;

	@NotNull
    @Size(min = 1)
    private String airPressure;

	@NotNull
    @Size(min = 1)
    private String uvLevelData;

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
    private String flightsAscended;

	@NotNull
    @Size(min = 1)
    private String flightsDescended;

	@NotNull
    @Size(min = 1)
    private String rate;

	@NotNull
    @Size(min = 1)
    private String steppingGain;

	@NotNull
    @Size(min = 1)
    private String steppingLoss;

	@NotNull
    @Size(min = 1)
    private String stepsAscended;

	@NotNull
    @Size(min = 1)
    private String stepsDescended;

	@NotNull
    @Size(min = 1)
    private String totalGain;

	@NotNull
    @Size(min = 1)
    private String totalLoss;

	@NotNull
    @Size(min = 1)
    private String caloryToday;

	@NotNull
    @Size(min = 1)
    private String distanceToday;

	@NotNull
    @Size(min = 1)
    private String currentPace;

	@NotNull
    @Size(min = 1)
    private String currentSpeed;

	@NotNull
    @Size(min = 1)
    private String currentMontionType;

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
    private String gsr;

	@NotNull
    @Size(min = 1)
    private String heartRate;

	@NotNull
    @Size(min = 1)
    private String stepToday;

	@NotNull
    @Size(min = 1)
    private String rrInterval;

	@NotNull
    @Size(min = 1)
    private String skinTemp;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(String userFeedback) {
        this.userFeedback = userFeedback;
    }

    public String getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(String airTemp) {
        this.airTemp = airTemp;
    }

    public String getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(String airHumidity) {
        this.airHumidity = airHumidity;
    }

    public String getAirvelocity() {
        return airVelocity;
    }

    public void setAirVelocity(String airVelocity) {
        this.airVelocity = airVelocity;
    }

    public String getMeanRadiantTemp() {
        return meanRadiantTemp;
    }

    public void setMeanRadiantTemp(String meanRadiantTemp) {
        this.meanRadiantTemp = meanRadiantTemp;
    }

    public String getMetabolicRate() {
        return metabolicRate;
    }

    public void setMetabolicRate(String metabolicRate) {
        this.metabolicRate = metabolicRate;
    }

    public String getClothingLevel() {
        return clothingLevel;
    }

    public void setClothingLevel(String clothingLevel) {
        this.clothingLevel = clothingLevel;
    }

    public String getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(String ambientLight) {
        this.ambientLight = ambientLight;
    }

    public String getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(String airPressure) {
        this.airPressure = airPressure;
    }

    public String getUvLevelData() {
        return uvLevelData;
    }

    public void setUvLevelData(String uvLevelData) {
        this.uvLevelData = uvLevelData;
    }

    public String getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(String accelerationX) {
        this.accelerationX = accelerationX;
    }

    public String getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(String accelerationY) {
        this.accelerationY = accelerationY;
    }

    public String getAccelerationZ() {
        return accelerationZ;
    }

    public void setAccelerationZ(String accelerationZ) {
        this.accelerationZ = accelerationZ;
    }

    public String getFlightsAscended() {
        return flightsAscended;
    }

    public void setFlightsAscended(String flightsAscended) {
        this.flightsAscended = flightsAscended;
    }

    public String getFlightsDescended() {
        return flightsDescended;
    }

    public void setFlightsDescended(String flightsDescended) {
        this.flightsDescended = flightsDescended;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSteppingGain() {
        return steppingGain;
    }

    public void setSteppingGain(String steppingGain) {
        this.steppingGain = steppingGain;
    }

    public String getSteppingLoss() {
        return steppingLoss;
    }

    public void setSteppingLoss(String steppingLoss) {
        this.steppingLoss = steppingLoss;
    }

    public String getStepsAscended() {
        return stepsAscended;
    }

    public void setStepsAscended(String stepsAscended) {
        this.stepsAscended = stepsAscended;
    }

    public String getStepsDescended() {
        return stepsDescended;
    }

    public void setStepsDescended(String stepsDescended) {
        this.stepsDescended = stepsDescended;
    }

    public String getTotalGain() {
        return totalGain;
    }

    public void setTotalGain(String totalGain) {
        this.totalGain = totalGain;
    }

    public String getTotalLoss() {
        return totalLoss;
    }

    public void setTotalLoss(String totalLoss) {
        this.totalLoss = totalLoss;
    }

    public String getCaloryToday() {
        return caloryToday;
    }

    public void setCaloryToday(String caloryToday) {
        this.caloryToday = caloryToday;
    }

    public String getDistanceToday() {
        return distanceToday;
    }

    public void setDistanceToday(String distanceToday) {
        this.distanceToday = distanceToday;
    }

    public String getCurrentPace() {
        return currentPace;
    }

    public void setCurrentPace(String currentPace) {
        this.currentPace = currentPace;
    }

    public String getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(String currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public String getCurrentMontionType() {
        return currentMontionType;
    }

    public void setCurrentMontionType(String currentMontionType) {
        this.currentMontionType = currentMontionType;
    }

    public String getAngularVelocityX() {
        return angularVelocityX;
    }

    public void setAngularVelocityX(String angularVelocityX) {
        this.angularVelocityX = angularVelocityX;
    }

    public String getAngularVelocityY() {
        return angularVelocityY;
    }

    public void setAngularVelocityY(String angularVelocityY) {
        this.angularVelocityY = angularVelocityY;
    }

    public String getAngularVelocityZ() {
        return angularVelocityZ;
    }

    public void setAngularVelocityZ(String angularVelocityZ) {
        this.angularVelocityZ = angularVelocityZ;
    }

    public String getGsr() {
        return gsr;
    }

    public void setGsr(String gsr) {
        this.gsr = gsr;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getStepToday() {
        return stepToday;
    }

    public void setStepToday(String stepToday) {
        this.stepToday = stepToday;
    }

    public String getRrInterval() {
        return rrInterval;
    }

    public void setRrInterval(String rrInterval) {
        this.rrInterval = rrInterval;
    }

    public String getSkinTemp() {
        return skinTemp;
    }

    public void setSkinTemp(String skinTemp) {
        this.skinTemp = skinTemp;
    }
}
