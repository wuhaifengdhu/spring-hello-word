package sg.ntu.itcm.handler;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import sg.ntu.itcm.service.LocalizationMessagesService;

@Component("authenticationFailureHandler")
public class CustomizedAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private static Logger log=LoggerFactory.getLogger(CustomizedAuthenticationFailureHandler.class);

    @Autowired
    private LocalizationMessagesService context;

    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {
        setDefaultFailureUrl("/login?error=true");
        
        super.onAuthenticationFailure(request, response, exception);
        
        // resolve locale by using CookieLocaleResolver
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName("iTCMLocaleCookie");
        final Locale locale = resolver.resolveLocale(request);
        
        log.info("Locale: " + locale);
        
        String errorMessage = context.getMessages().getMessage("message.badCredentials", null, locale);

        if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
            errorMessage = context.getMessages().getMessage("auth.message.disabled", null, locale);
        } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
            errorMessage = context.getMessages().getMessage("auth.message.expired", null, locale);
        } else if (exception.getMessage().equalsIgnoreCase("blocked")) {
            errorMessage = context.getMessages().getMessage("auth.message.blocked", null, locale);
        }

        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
    }
}