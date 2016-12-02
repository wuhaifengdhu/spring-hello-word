package sg.ntu.itcm.task;

import sg.ntu.itcm.dao.PasswordResetTokenMapper;
import sg.ntu.itcm.dao.EmailVerificationTokenMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;

@Service
@Transactional
public class TokensPurgeTask {
	
	private static Logger log=LoggerFactory.getLogger(TokensPurgeTask.class);

    @Autowired
    EmailVerificationTokenMapper emailVerificationTokenMapper;

    @Autowired
    PasswordResetTokenMapper passwordResetTokenMapper;

    @Scheduled(cron = "${purge.cron.expression}")
    public void purgeExpired() {
    	
    	log.info("Start to clear expired tokens......");

        Date now = Date.from(Instant.now());

        passwordResetTokenMapper.deleteAllExpiredSince(now);
        emailVerificationTokenMapper.deleteAllExpiredSince(now);
    }
}
