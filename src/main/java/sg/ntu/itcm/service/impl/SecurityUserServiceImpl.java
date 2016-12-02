package sg.ntu.itcm.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.ntu.itcm.dao.PasswordResetTokenMapper;
import sg.ntu.itcm.dao.UserMapper;
import sg.ntu.itcm.model.PasswordResetToken;
import sg.ntu.itcm.model.User;  
import sg.ntu.itcm.service.SecurityUserService;  

@Service("userService")
@Transactional
public class SecurityUserServiceImpl implements SecurityUserService { 
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
    private PasswordResetTokenMapper passwordTokenMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    // API

    @Override
    public String validatePasswordResetToken(long id, String token) {
        final PasswordResetToken passToken = passwordTokenMapper.selectByVerificationToken(token);
        if ((passToken == null) || (passToken.getId() != id)) {
            return "invalidToken";
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "expired";
        }

        final User user = userMapper.selectByUuid(passToken.getUserUuid());
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, userDetailsService.loadUserByUsername(user.getEmail()).getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return null;
    }
}
