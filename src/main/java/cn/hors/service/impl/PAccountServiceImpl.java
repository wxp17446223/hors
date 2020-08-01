package cn.hors.service.impl;

import cn.hors.bean.PAccount;
import cn.hors.mapper.PAccountMapper;
import cn.hors.mapper.RoleMapper;
import cn.hors.service.PAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PAccountServiceImpl implements PAccountService {

    @Resource
    private PAccountMapper pAccountMapper;
    @Resource
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public boolean insertAccount(PAccount account, Integer[] roleIds) {
        int ans = pAccountMapper.insertAccount(account);
        roleMapper.bindRole(account.getId(),roleIds);
        return ans>0?true:false;
    }

    @Transactional
    @Override
    public boolean updateAccount(PAccount account,Integer[] roleIds) {
        int ans = pAccountMapper.updateAccount(account);
        roleMapper.unBindRole(account.getId());
        roleMapper.bindRole(account.getId(),roleIds);
        return ans>0?true:false;
    }

    @Override
    public boolean deleteAccountById(Integer... ids) {
        pAccountMapper.deleteMapping(ids);
        return pAccountMapper.deleteAccountById(ids)>0?true:false;
    }

    @Override
    public List<PAccount> find(PAccount account) {
        return pAccountMapper.find(account);
    }

    @Override
    public PAccount findByAccount(String name) {
        return pAccountMapper.findByAccout(name);
    }

    @Override
    public int updateHead(PAccount pAccount) {
        return pAccountMapper.updateHead(pAccount);
    }
}
