package sg.ntu.itcm.controller;

import java.util.ArrayList;

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
public class RewardController {
	private static Logger log = LoggerFactory.getLogger(RewardController.class);

	@GetMapping(value = "/api/rewardHistory")
    public ResponseEntity<Object> loadMobileData(HttpServletResponse response) {
        return new ResponseEntity<Object>(getDummyRewardHistoryData(), new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping(value = "/rewardHistory")
    public ResponseEntity<Object> loadWebData(HttpServletResponse response) {
        return new ResponseEntity<Object>(getDummyRewardHistoryData(), new HttpHeaders(), HttpStatus.OK);
    }
	
	private String getDummyRewardHistoryData() {
		// sept data
		ArrayList<Object> dataset1 = new ArrayList<Object>();
		for (int i = 11; i > 0; i-- ) {
			JSONObject data  = new JSONObject();
			data.put("rewardDate", i + " Sept");
			data.put("rewardQuantity", i);
			data.put("rewardRemark", "Movie Ticket Reward");
			data.put("rewardType", "Movie Ticket");
			data.put("rewardValue", "S$13");
			dataset1.add(data);
		}
		
		JSONObject sepData  = new JSONObject();
		sepData.put("dataArray", dataset1);
		sepData.put("year", "2016");
		sepData.put("month", "September");
		
		// aug data
		ArrayList<Object> dataset2 = new ArrayList<Object>();
		for (int i = 30; i > 0; i-=2 ) {
			JSONObject data  = new JSONObject();
			data.put("rewardDate", i + " Aug");
			data.put("rewardQuantity", 3);
			data.put("rewardRemark", "Cash Voucher Reward");
			data.put("rewardType", "Voucher");
			data.put("rewardValue", "S$30");
			dataset2.add(data);
		}
		
		JSONObject augData  = new JSONObject();
		augData.put("dataArray", dataset2);
		augData.put("year", "2016");
		augData.put("month", "August");
		
		
		ArrayList<Object> datasets = new ArrayList<Object>();
		datasets.add(sepData);
		datasets.add(augData);
		
		// data
		JSONObject data  = new JSONObject();
		data.put("sectionArray", datasets);
		
		String out = data.toJSONString();
		
		log.info("json data: " + out);
		return out;
	}
}
