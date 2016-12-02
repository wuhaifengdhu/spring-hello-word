package sg.ntu.itcm.controller;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChartController {
	
	private static Logger log = LoggerFactory.getLogger(ChartController.class);
	
	@GetMapping(value = "/api/chartData")
    public ResponseEntity<Object> loadMobileData(HttpServletResponse response) {
		return new ResponseEntity<Object>(dummyChartData(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/chartData")
    public ResponseEntity<Object> loadWebData(HttpServletResponse response) {
        return new ResponseEntity<Object>(dummyChartData(), new HttpHeaders(), HttpStatus.OK);
    }

	private String dummyChartData() {
		// labels
		ArrayList<String> labelList = new ArrayList<String>();
		labelList.add("January"); labelList.add("February");
		labelList.add("March"); labelList.add("April");
		labelList.add("May"); labelList.add("June");
		labelList.add("July"); labelList.add("August");
		labelList.add("September"); labelList.add("October");
		labelList.add("November"); labelList.add("December");
		
		JSONObject data  = new JSONObject();
		data.put("labels", labelList);
		
		// dataset labels
		ArrayList<String> datasetLabelList = new ArrayList<String>();
		datasetLabelList.add("DMAL Lab"); 
		datasetLabelList.add("ETL Lab");
		
		data.put("datasetLabels", datasetLabelList);
		
		// dataset1
		ArrayList<Object> dataList = new ArrayList<Object>();
		dataList.add(Integer.parseInt("24")); 
		dataList.add(Integer.parseInt("25"));
		dataList.add(Integer.parseInt("26"));
		dataList.add(Integer.parseInt("18"));
		dataList.add(Integer.parseInt("27"));
		dataList.add(Integer.parseInt("29"));
		dataList.add(Integer.parseInt("22"));
		dataList.add(Integer.parseInt("23"));
		dataList.add(Integer.parseInt("30"));
		dataList.add(Integer.parseInt("17"));
		dataList.add(Integer.parseInt("19"));
		dataList.add(Integer.parseInt("21"));
		
		JSONObject dataset1  = new JSONObject();
		dataset1.put("datasetLabel", datasetLabelList.get(0));
		dataset1.put("data", dataList);
		
		// dataset2
		dataList = new ArrayList<Object>();
		dataList.add(Integer.parseInt("27")); 
		dataList.add(Integer.parseInt("21"));
		dataList.add(Integer.parseInt("29"));
		dataList.add(Integer.parseInt("19"));
		dataList.add(Integer.parseInt("24"));
		dataList.add(Integer.parseInt("21"));
		dataList.add(Integer.parseInt("28"));
		dataList.add(Integer.parseInt("25"));
		dataList.add(Integer.parseInt("20"));
		dataList.add(Integer.parseInt("29"));
		dataList.add(Integer.parseInt("18"));
		dataList.add(Integer.parseInt("27"));
		
		JSONObject dataset2  = new JSONObject();
		dataset2.put("datasetLabel", datasetLabelList.get(1));
		dataset2.put("data", dataList);
		
		ArrayList<Object> datasetList = new ArrayList<Object>();
		datasetList.add(dataset1); 
		datasetList.add(dataset2);
		
		data.put("data", datasetList);
		
		String out = data.toJSONString();
		
		log.info("json data: " + out);
		return out;
	}
}
