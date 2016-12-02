package sg.ntu.itcm.service;

public interface SecurityUserService {
	public String validatePasswordResetToken(long id, String token);
}
