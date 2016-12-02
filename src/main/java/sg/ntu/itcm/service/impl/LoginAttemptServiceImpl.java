package sg.ntu.itcm.service.impl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import sg.ntu.itcm.service.LoginAttemptService;

@Service("loginAttemptService")
public class LoginAttemptServiceImpl implements LoginAttemptService {

    private final int MAX_ATTEMPT = 10;
    private LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptServiceImpl() {
        super();
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(final String key) {
                return 0;
            }
        });
    }

    @Override
    public void loginSucceeded(final String key) {
        attemptsCache.invalidate(key);
    }

    @Override
    public void loginFailed(final String key) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(key);
        } catch (final ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }
    
    @Override
    public boolean isBlocked(final String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } catch (final ExecutionException e) {
            return false;
        }
    }
}
