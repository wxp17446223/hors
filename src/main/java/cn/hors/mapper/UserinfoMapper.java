package cn.hors.mapper;

import cn.hors.bean.Userinfo;

import java.util.List;

public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);

    List<Userinfo> findAll(Userinfo userinfo);
}