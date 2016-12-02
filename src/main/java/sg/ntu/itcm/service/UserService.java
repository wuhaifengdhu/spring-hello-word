package sg.ntu.itcm.service;

import sg.ntu.itcm.model.PasswordResetToken;
import sg.ntu.itcm.model.User;
import sg.ntu.itcm.model.EmailVerificationToken;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import org.springframework.http.ResponseEntity;

import sg.ntu.itcm.dto.UserDto;
import sg.ntu.itcm.dto.UserInfoDto;
import sg.ntu.itcm.error.UserAlreadyExistException;

public interface UserService {

    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);

    EmailVerificationToken getVerificationToken(String emailVerificationToken);

    EmailVerificationToken generateNewVerificationToken(String token);

    PasswordResetToken createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    User getUserByPasswordResetToken(String token);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String password);

    String validateVerificationToken(String token);

	User getUserByID(int id);
	
	String getUserInfo(String email);

	User changeUserPassword(final Locale locale, final String password, final String oldPassword);
	
	ResponseEntity<byte[]> downloadUserAvatar() throws FileNotFoundException, IOException;
	
	void updateUserInfo(final UserInfoDto userInfo);
	
}
