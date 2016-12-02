package sg.ntu.itcm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

	public Date getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		try {
			return dateFormat.parse(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
}
