package sg.ntu.itcm.constants.enumerator;

import sg.ntu.itcm.constants.UserRole;

public class UserRoleEnumerator {

	public String getUserRoleString(UserRole userRole) {
		switch (userRole)
	    {
	      case ROLE_ADMIN: return "ROLE_ADMIN";
	      case ROLE_USER: return "ROLE_USER";
	      default: return "ROLE_USER";
	    }
	}
}
