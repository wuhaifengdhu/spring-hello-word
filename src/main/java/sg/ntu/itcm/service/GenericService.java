package sg.ntu.itcm.service;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface GenericService {

	String getAppUrl(HttpServletRequest request);
	
	public InputStream resizeImage(MultipartFile file) throws IOException;
	
	public void createUserDefaultAvatar(final String uuid) throws IOException;
	
	public String getUserAvatarPath();
}
