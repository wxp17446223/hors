package cn.hors.mapper;

import cn.hors.bean.PAccount;
import cn.hors.bean.Userinfo;

public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer userId);

    Userinfo findById(Integer userId);

    int update(Userinfo user);

    Userinfo findByAccId(Integer accountId);

    int updatePic(Userinfo userinfo);
}