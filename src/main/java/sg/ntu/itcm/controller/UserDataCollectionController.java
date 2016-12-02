package sg.ntu.itcm.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import sg.ntu.itcm.dto.UserDataDto;
import sg.ntu.itcm.util.GenericResponse;

@Controller
public class UserDataCollectionController {
	
	private static Logger log = LoggerFactory.getLogger(UserDataCollectionController.class);
	
	@PostMapping(value = "/api/dataUpload")
    public ResponseEntity<GenericResponse> registerUserAccount(@Valid final UserDataDto userData, final Locale locale, final HttpServletRequest request) {
        log.info("Received user data: {}", userData);
        
        
        GenericResponse responseBody = new GenericResponse("success");
        return new ResponseEntity<GenericResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
    }

}
