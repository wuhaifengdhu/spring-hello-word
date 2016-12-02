package sg.ntu.itcm.dao;

import java.util.Collection;

import sg.ntu.itcm.model.UserRoles;

public interface UserRolesMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByUuid(String uuid);

    int insert(UserRoles record);

    int insertSelective(UserRoles record);

    UserRoles selectByPrimaryKey(Integer id);
    
    Collection<UserRoles> selectByUuid(String uuid);

    int updateByPrimaryKeySelective(UserRoles record);

    int updateByPrimaryKey(UserRoles record);
}