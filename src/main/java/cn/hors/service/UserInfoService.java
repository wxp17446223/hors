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

    /**
     * 通过手机号码查询是否有注册患者信息
     * @param phone
     * @return
     */
    UserInfo findByPhone(String phone);

    boolean deleteByIds(Integer... ids);
}
