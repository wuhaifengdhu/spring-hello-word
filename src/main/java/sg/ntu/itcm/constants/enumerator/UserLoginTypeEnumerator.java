package sg.ntu.itcm.constants.enumerator;

import sg.ntu.itcm.constants.UserLoginType;

public class UserLoginTypeEnumerator {

	public String getUserRoleString(UserLoginType userLoginType) {
		switch (userLoginType)
	    {
	      case ITCM_USER: return "ITCM_USER";
	      case FACEBOOK: return "FACEBOOK_USER";
	      case TWITTER: return "TWITTER_USER";
	      default: return "ITCM_USER";
	    }
	}
}
