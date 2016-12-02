package sg.ntu.itcm.service;

public interface LoginAttemptService {
	
	public void loginSucceeded(final String key);

    public void loginFailed(final String key);

    public boolean isBlocked(final String key);
}
