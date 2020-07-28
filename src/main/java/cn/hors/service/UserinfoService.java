package cn.hors.service;

import cn.hors.bean.Doctor;
import cn.hors.bean.Userinfo;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserinfoService{


    int deleteByPrimaryKey(Integer userId);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);

    List<Userinfo> findAll(Userinfo userinfo);
}
