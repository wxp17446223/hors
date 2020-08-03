package cn.hors.service;

import cn.hors.bean.PAccount;
import cn.hors.bean.Userinfo;
public interface UserinfoService{


    int deleteByPrimaryKey(Integer userId);

    boolean insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer userId);


    Userinfo findById(Integer userId);

    boolean update(Userinfo user);

    Userinfo findByAccId(Integer accountId);

    int updatePic(Userinfo userinfo);
}
