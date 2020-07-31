package cn.hors.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.hors.bean.Userinfo;
import cn.hors.mapper.UserinfoMapper;
import cn.hors.service.UserinfoService;
@Service
public class UserinfoServiceImpl implements UserinfoService{

    @Resource
    private UserinfoMapper userinfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return userinfoMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public boolean insert(Userinfo record) {
        return userinfoMapper.insert(record)>0;
    }

    @Override
    public int insertSelective(Userinfo record) {
        return userinfoMapper.insertSelective(record);
    }

    @Override
    public Userinfo selectByPrimaryKey(Integer userId) {
        return userinfoMapper.selectByPrimaryKey(userId);
    }


    @Override
    public Userinfo findById(Integer accountId) {
        return userinfoMapper.findById(accountId);
    }

    @Override
    public boolean update(Userinfo user) {
        return userinfoMapper.update(user)>0;
    }

    @Override
    public Userinfo findByAccId(Integer accountId) {
        return userinfoMapper.findByAccId(accountId);
    }


}
