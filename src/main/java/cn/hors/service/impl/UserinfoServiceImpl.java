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
    public int insert(Userinfo record) {
        return userinfoMapper.insert(record);
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
    public int updateByPrimaryKeySelective(Userinfo record) {
        return userinfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Userinfo record) {
        return userinfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public Userinfo findById(Integer accountId) {
        return userinfoMapper.findById(accountId);
    }


}
