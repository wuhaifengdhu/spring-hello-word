package sg.ntu.itcm.email;

import java.util.Map;

public class SendCloudEmailTemplate {

	private String templateName;
	
	private Map<String, String> templateMap;
	
	public SendCloudEmailTemplate(){
		super();
	}
	
	public SendCloudEmailTemplate(String templateName, Map<String, String> templateMap){
		super();
		this.templateName = templateName;
		this.templateMap = templateMap;
	}
	
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	public String getTemplateName() {
		return this.templateName;
	}
	
	public void setTemplateMap(Map<String, String> templateMap){
		this.templateMap = templateMap;
	}
	
	public Map<String, String> getTemplateMap() {
		return this.templateMap;
	}
}
