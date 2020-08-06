package cn.hors.service;

import cn.hors.bean.UserInfo;
public interface UserInfoService {


    int deleteByPrimaryKey(Integer userId);

    boolean insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);


    UserInfo findById(Integer userId);

    boolean update(UserInfo user);

    UserInfo findByAccId(Integer accountId);

    int updatePic(UserInfo userinfo);

    boolean deleteByIds(Integer... ids);
}
