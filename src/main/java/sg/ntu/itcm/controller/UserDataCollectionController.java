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
        log.info("Received user data: {}", userDataDto.toString());
        
        final UserData userData = new UserData();
        userData.setUid(userDataDto.getUid());
        userData.setTimestamp(userDataDto.getTimestamp());
        userData.setAirtemp(userDataDto.getAirTemp());
        userData.setAirhumidity(userDataDto.getAirHumidity());
        userData.setAirvelocity(userDataDto.getAirVelocity());
        userData.setMeanradianttemp(userDataDto.getMeanRadiantTemp());
        userData.setMetabolicrate(userDataDto.getMetabolicRate());
        userData.setClothinglevel(userDataDto.getClothingLevel());
        userData.setHeartrate(userDataDto.getHeartRate());
        userData.setRrinterval(userDataDto.getRrInterval());
        userData.setGsr(userDataDto.getGsr());
        userData.setSkintemp(userDataDto.getSkinTemp());
        userData.setUserfeedback(userDataDto.getUserFeedback());
        userDataMapper.insert(userData);
        
        GenericResponse responseBody = new GenericResponse("success");
        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
    }
	
	@PostMapping(value = "/api/feedbackUpload")
    public ResponseEntity<GenericResponse> userFeedbackUpload(@Valid final UserDataDto userFeedbackDataDto, final Locale locale, final HttpServletRequest request) {
        log.info("Received user feedback: {}", userFeedbackDataDto.toString());
        
        final UserFeedbackData userFeedbackData = new UserFeedbackData();
        userFeedbackData.setUid(userFeedbackDataDto.getUid());
        userFeedbackData.setTimestamp(userFeedbackDataDto.getTimestamp());
        userFeedbackData.setAirtemp(userFeedbackDataDto.getAirTemp());
        userFeedbackData.setAirhumidity(userFeedbackDataDto.getAirHumidity());
        userFeedbackData.setAirvelocity(userFeedbackDataDto.getAirVelocity());
        userFeedbackData.setMeanradianttemp(userFeedbackDataDto.getMeanRadiantTemp());
        userFeedbackData.setMetabolicrate(userFeedbackDataDto.getMetabolicRate());
        userFeedbackData.setClothinglevel(userFeedbackDataDto.getClothingLevel());
        userFeedbackData.setHeartrate(userFeedbackDataDto.getHeartRate());
        userFeedbackData.setRrinterval(userFeedbackDataDto.getRrInterval());
        userFeedbackData.setGsr(userFeedbackDataDto.getGsr());
        userFeedbackData.setSkintemp(userFeedbackDataDto.getSkinTemp());
        userFeedbackData.setUserfeedback(userFeedbackDataDto.getUserFeedback());
        userFeedbackDataMapper.insert(userFeedbackData);
        
        GenericResponse responseBody = new GenericResponse("success");
        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
    }

}
