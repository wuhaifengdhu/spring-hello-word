package sg.ntu.itcm.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import sg.ntu.itcm.dao.UserDataMapper;
import sg.ntu.itcm.dao.UserFeedbackDataMapper;
import sg.ntu.itcm.dto.UserDataDto;
import sg.ntu.itcm.model.UserData;
import sg.ntu.itcm.model.UserFeedbackData;
import sg.ntu.itcm.util.GenericResponse;

@Controller
public class UserDataCollectionController {
	
	private static Logger log = LoggerFactory.getLogger(UserDataCollectionController.class);
	
	@Autowired
    private UserDataMapper userDataMapper;
	
	@Autowired
    private UserFeedbackDataMapper userFeedbackDataMapper;
	
	@PostMapping(value = "/api/dataUpload")
    public ResponseEntity<GenericResponse> userDataCollectionUpload(@Valid final UserDataDto userDataDto, final Locale locale, final HttpServletRequest request) {
        log.info("Received auto collected user data.");
        
        UserData userData = new UserData();
        userData.setUid(userDataDto.getUid());
        userData.setYear(userDataDto.getYear());
        userData.setDate(userDataDto.getDate());
        userData.setTime(userDataDto.getTime());
        userData.setUserfeedback(userDataDto.getUserFeedback());
        
        userData.setAirtemp(userDataDto.getAirTemp());
        userData.setAirhumidity(userDataDto.getAirHumidity());
        userData.setAirvelocity(userDataDto.getAirvelocity());
        userData.setMeanradianttemp(userDataDto.getMeanRadiantTemp());
        userData.setMetabolicrate(userDataDto.getMetabolicRate());
        userData.setClothinglevel(userDataDto.getClothingLevel());
        
        userData.setAmbientlight(userDataDto.getAmbientLight());
        userData.setAirpressure(userDataDto.getAirPressure());
        userData.setUvleveldata(userDataDto.getUvLevelData());
        userData.setAccelerationx(userDataDto.getAccelerationX());
        userData.setAccelerationy(userDataDto.getAccelerationY());
        userData.setAccelerationz(userDataDto.getAccelerationZ());
        userData.setFlightsascended(userDataDto.getFlightsAscended());
        userData.setFlightsdescended(userDataDto.getFlightsDescended());
        userData.setRate(userDataDto.getRate());
        userData.setSteppinggain(userDataDto.getSteppingGain());
        userData.setSteppingloss(userDataDto.getSteppingLoss());
        userData.setStepsascended(userDataDto.getStepsAscended());
        userData.setStepsdescended(userDataDto.getStepsDescended());
        userData.setTotalgain(userDataDto.getTotalGain());
        userData.setTotalloss(userDataDto.getTotalLoss());
        userData.setCalorytoday(userDataDto.getCaloryToday());
        userData.setDistancetoday(userDataDto.getDistanceToday());
        userData.setCurrentpace(userDataDto.getCurrentPace());
        userData.setCurrentspeed(userDataDto.getCurrentSpeed());
        userData.setCurrentmontiontype(userDataDto.getCurrentMontionType());
        userData.setAngularvelocityx(userDataDto.getAngularVelocityX());
        userData.setAngularvelocityy(userDataDto.getAngularVelocityY());
        userData.setAngularvelocityz(userDataDto.getAngularVelocityZ());
        userData.setGsr(userDataDto.getGsr());
        userData.setHeartrate(userDataDto.getHeartRate());
        userData.setSteptoday(userDataDto.getStepToday());
        userData.setRrinterval(userDataDto.getRrInterval());
        userData.setSkintemp(userDataDto.getSkinTemp());
        
        userDataMapper.insert(userData);
        
        GenericResponse responseBody = new GenericResponse("success");
        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
    }
	
	@PostMapping(value = "/api/feedbackUpload")
    public ResponseEntity<GenericResponse> userFeedbackUpload(@Valid final UserDataDto userFeedbackDataDto, final Locale locale, final HttpServletRequest request) {
        log.info("Received user feedback.");
        
        UserFeedbackData userFeedbackData = new UserFeedbackData();
        userFeedbackData.setUid(userFeedbackDataDto.getUid());
        userFeedbackData.setYear(userFeedbackDataDto.getYear());
        userFeedbackData.setDate(userFeedbackDataDto.getDate());
        userFeedbackData.setTime(userFeedbackDataDto.getTime());
        userFeedbackData.setUserfeedback(userFeedbackDataDto.getUserFeedback());
        
        userFeedbackData.setAirtemp(userFeedbackDataDto.getAirTemp());
        userFeedbackData.setAirhumidity(userFeedbackDataDto.getAirHumidity());
        userFeedbackData.setAirvelocity(userFeedbackDataDto.getAirvelocity());
        userFeedbackData.setMeanradianttemp(userFeedbackDataDto.getMeanRadiantTemp());
        userFeedbackData.setMetabolicrate(userFeedbackDataDto.getMetabolicRate());
        userFeedbackData.setClothinglevel(userFeedbackDataDto.getClothingLevel());
        
        userFeedbackData.setAmbientlight(userFeedbackDataDto.getAmbientLight());
        userFeedbackData.setAirpressure(userFeedbackDataDto.getAirPressure());
        userFeedbackData.setUvleveldata(userFeedbackDataDto.getUvLevelData());
        userFeedbackData.setAccelerationx(userFeedbackDataDto.getAccelerationX());
        userFeedbackData.setAccelerationy(userFeedbackDataDto.getAccelerationY());
        userFeedbackData.setAccelerationz(userFeedbackDataDto.getAccelerationZ());
        userFeedbackData.setFlightsascended(userFeedbackDataDto.getFlightsAscended());
        userFeedbackData.setFlightsdescended(userFeedbackDataDto.getFlightsDescended());
        userFeedbackData.setRate(userFeedbackDataDto.getRate());
        userFeedbackData.setSteppinggain(userFeedbackDataDto.getSteppingGain());
        userFeedbackData.setSteppingloss(userFeedbackDataDto.getSteppingLoss());
        userFeedbackData.setStepsascended(userFeedbackDataDto.getStepsAscended());
        userFeedbackData.setStepsdescended(userFeedbackDataDto.getStepsDescended());
        userFeedbackData.setTotalgain(userFeedbackDataDto.getTotalGain());
        userFeedbackData.setTotalloss(userFeedbackDataDto.getTotalLoss());
        userFeedbackData.setCalorytoday(userFeedbackDataDto.getCaloryToday());
        userFeedbackData.setDistancetoday(userFeedbackDataDto.getDistanceToday());
        userFeedbackData.setCurrentpace(userFeedbackDataDto.getCurrentPace());
        userFeedbackData.setCurrentspeed(userFeedbackDataDto.getCurrentSpeed());
        userFeedbackData.setCurrentmontiontype(userFeedbackDataDto.getCurrentMontionType());
        userFeedbackData.setAngularvelocityx(userFeedbackDataDto.getAngularVelocityX());
        userFeedbackData.setAngularvelocityy(userFeedbackDataDto.getAngularVelocityY());
        userFeedbackData.setAngularvelocityz(userFeedbackDataDto.getAngularVelocityZ());
        userFeedbackData.setGsr(userFeedbackDataDto.getGsr());
        userFeedbackData.setHeartrate(userFeedbackDataDto.getHeartRate());
        userFeedbackData.setSteptoday(userFeedbackDataDto.getStepToday());
        userFeedbackData.setRrinterval(userFeedbackDataDto.getRrInterval());
        userFeedbackData.setSkintemp(userFeedbackDataDto.getSkinTemp());
        
        userFeedbackDataMapper.insert(userFeedbackData);
        
        GenericResponse responseBody = new GenericResponse("success");
        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
    }

}
