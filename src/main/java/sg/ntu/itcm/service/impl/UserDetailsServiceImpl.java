package sg.ntu.itcm.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sg.ntu.itcm.dao.UserMapper;
import sg.ntu.itcm.dao.UserRolesMapper;
import sg.ntu.itcm.model.User;
import sg.ntu.itcm.model.UserRoles;
import sg.ntu.itcm.service.LoginAttemptService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	private static Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    public UserDetailsServiceImpl() {
        super();
    }

    // API

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
    	
        final String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }

        try {
            final User user = userMapper.selectByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with email: " + email);
            }
            else {
            	log.info("user is found!");
            }
            
            boolean isActivated = (user.getActivationStatus().equals("YES")) ? true: false;
            log.info("user: " + user);
            
            if (!isActivated) {
            	throw new UsernameNotFoundException("Unactivated user with email: " + email);
            }
 
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), isActivated, true, true, true, getAuthorities(user.getUserUuid()));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    // UTIL
    public final Collection<? extends GrantedAuthority> getAuthorities(final String uuid) {
        return getGrantedAuthorities(userRolesMapper.selectByUuid(uuid));
    }
    
    private final List<GrantedAuthority> getGrantedAuthorities(final Collection<UserRoles> roles) {
    	log.info("user roles: " + roles);
    	
    	final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	for (final UserRoles role : roles) {
    		authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
    	return authorities;
    }

    private String getClientIP() {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
