package sg.ntu.itcm.dao;

import sg.ntu.itcm.model.UserFeedbackData;

public interface UserFeedbackDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFeedbackData record);

    int insertSelective(UserFeedbackData record);

    UserFeedbackData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFeedbackData record);

    int updateByPrimaryKey(UserFeedbackData record);
}