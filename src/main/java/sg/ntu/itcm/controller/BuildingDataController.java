package sg.ntu.itcm.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.alibaba.fastjson.JSONObject;

@Controller
public class BuildingDataController {
	private static Logger log = LoggerFactory.getLogger(BuildingDataController.class);
	
	@GetMapping(value = "/api/buildingStatus")
    public ResponseEntity<Object> loadMobileData(HttpServletResponse response) {
        return new ResponseEntity<Object>(getDummyBuildingStatusData(), new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping(value = "/buildingStatus")
    public ResponseEntity<Object> loadWebData(HttpServletResponse response) {
        return new ResponseEntity<Object>(getDummyBuildingStatusData(), new HttpHeaders(), HttpStatus.OK);
    }
	
	private String getDummyBuildingStatusData() {
		// humidity data
		JSONObject humidityData  = new JSONObject();
		humidityData.put("data", 56);
		humidityData.put("dataType", "Humidity");
		
		// air temperature
		JSONObject temperatureData  = new JSONObject();
		temperatureData.put("data", 19);
		temperatureData.put("dataType", "Air Temp");
		
		JSONObject data  = new JSONObject();
		data.put("humidity", humidityData);
		data.put("temperature", temperatureData);
		
		String out = data.toJSONString();
		
		log.info("json data: " + out);
		return out;
	}
}
