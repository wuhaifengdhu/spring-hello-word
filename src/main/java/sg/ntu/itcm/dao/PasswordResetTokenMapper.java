package sg.ntu.itcm.dao;

import java.util.Date;

import sg.ntu.itcm.model.PasswordResetToken;

public interface PasswordResetTokenMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByUuid(String uuid);
    
    int deleteByVerificationToken(String VerificationToken);
    
    int deleteAllExpiredSince(Date now);

    int insert(PasswordResetToken record);

    int insertSelective(PasswordResetToken record);

    PasswordResetToken selectByPrimaryKey(Integer id);
    
    PasswordResetToken selectByUuid(String uuid);
    
    PasswordResetToken selectByVerificationToken(String verificationToken);

    int updateByPrimaryKeySelective(PasswordResetToken record);

    int updateByPrimaryKey(PasswordResetToken record);
}