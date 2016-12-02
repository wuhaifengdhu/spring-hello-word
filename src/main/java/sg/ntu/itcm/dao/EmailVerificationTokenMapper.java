package sg.ntu.itcm.dao;

import java.util.Date;

import sg.ntu.itcm.model.EmailVerificationToken;

public interface EmailVerificationTokenMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByUuid(String uuid);
    
    int deleteByVerificationToken(String VerificationToken);
    
    int deleteAllExpiredSince(Date now);

    int insert(EmailVerificationToken record);

    int insertSelective(EmailVerificationToken record);

    EmailVerificationToken selectByPrimaryKey(Integer id);
    
    EmailVerificationToken selectByUuid(String uuid);
    
    EmailVerificationToken selectByVerificationToken(String verificationToken);

    int updateByPrimaryKeySelective(EmailVerificationToken record);

    int updateByPrimaryKey(EmailVerificationToken record);
}