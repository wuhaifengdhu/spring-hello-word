package sg.ntu.itcm.dao;

import sg.ntu.itcm.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByUuid(String uuid);
    
    int deleteByEmail(String email);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    
    User selectByUuid(String uuid);
    
    User selectByEmail(String email);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}