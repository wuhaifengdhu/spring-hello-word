package sg.ntu.itcm.service.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sg.ntu.itcm.service.GenericService;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

@Service("genericService")
public class GenericServiceImpl implements GenericService {
	private static Logger log = LoggerFactory.getLogger(GenericServiceImpl.class);
	
	@Autowired
	ServletContext servletContext;

	@Override
	public String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
	
	@Override
	public InputStream resizeImage(MultipartFile file) throws IOException {
		BufferedImage sourceImage = ImageIO.read(file.getInputStream());
		int width = sourceImage.getWidth();
		int height = sourceImage.getHeight();
		
		if (width < 256 && height < 256) {
			return file.getInputStream();
		}
		
		if (width >= height) {
			height = (height * 256)/width;
			return createthumbnail(sourceImage, 256, height);
		}
		width = (width* 256)/height;
		return createthumbnail(sourceImage, width, 256);
	}
	
	private InputStream createthumbnail(BufferedImage sourceImage, int width, int height) throws IOException {
		Image thumbnail = sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage bufferedThumbnail = new BufferedImage(thumbnail.getWidth(null),
		                                                    thumbnail.getHeight(null),
		                                                    BufferedImage.TYPE_INT_RGB);
		bufferedThumbnail.getGraphics().drawImage(thumbnail, 0, 0, null);
		ByteArrayOutputStream outputImage = new ByteArrayOutputStream();
		ImageIO.write(bufferedThumbnail, "png", outputImage);
		return new ByteArrayInputStream(outputImage.toByteArray());
	}
	
	@Override
	public void createUserDefaultAvatar(final String uuid) throws IOException {
		String fullPath = servletContext.getRealPath("/resources/img/default_avatar.png");
	    FileUtils.copyInputStreamToFile(new FileInputStream(new File(fullPath)), new File(getUserAvatarPath() + File.separator + uuid));
	    log.info("Create default avatar successfully!");
	}
	
	@Override
	public String getUserAvatarPath() {
		String rootPath = System.getProperty("catalina.home");
	    File dir = new File(rootPath + File.separator + "UserAvatars");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir.getAbsolutePath();
	}
}
