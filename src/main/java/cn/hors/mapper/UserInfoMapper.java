package cn.hors.mapper;

import cn.hors.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    UserInfo findById(Integer userId);

    int update(UserInfo user);

    UserInfo findByAccId(Integer accountId);

    int updatePic(UserInfo userinfo);

    int deleteByIds(@Param("ids") Integer... ids);
}