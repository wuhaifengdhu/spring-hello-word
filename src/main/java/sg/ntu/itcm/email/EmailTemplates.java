package sg.ntu.itcm.email;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class EmailTemplates {

	public SendCloudEmailTemplate getUserActivationEmailTemplate(String username, String urlLink) {
		String templateName = "ijugou_register_template";
		Map<String, String> templateMap = ImmutableMap.<String, String>builder().put("name", username).put("link", urlLink).build();
		return new SendCloudEmailTemplate(templateName, templateMap);
	}
	
	public SendCloudEmailTemplate getUserResetPasswordEmailTemplate(String username, String urlLink) {
		// need change
		String templateName = "ijugou_register_template";
		Map<String, String> templateMap = ImmutableMap.<String, String>builder().put("name", username).put("link", urlLink).build();
		return new SendCloudEmailTemplate(templateName, templateMap);
	}
}
