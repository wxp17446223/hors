package cn.hors.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import cn.hors.bean.UserInfo;
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
    public boolean insert(UserInfo record) {
        return userinfoMapper.insert(record)>0;
    }

    @Override
    public int insertSelective(UserInfo record) {
        return userinfoMapper.insertSelective(record);
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer userId) {
        return userinfoMapper.selectByPrimaryKey(userId);
    }


    @Override
    public UserInfo findById(Integer accountId) {
        return userinfoMapper.findById(accountId);
    }

    @Override
    public boolean update(UserInfo user) {
        return userinfoMapper.update(user)>0;
    }

    @Override
    public UserInfo findByAccId(Integer accountId) {
        return userinfoMapper.findByAccId(accountId);
    }

    @Override
    public int updatePic(UserInfo userinfo) {
        return userinfoMapper.updatePic(userinfo);
    }

    @Override
    public boolean deleteByIds(Integer... ids) {
        return userinfoMapper.deleteByIds(ids)>0?true:false;
    }


}
