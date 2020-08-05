package cn.hors.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.hors.mapper.AccountMapper;
import cn.hors.bean.Account;
import cn.hors.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Resource
    private AccountMapper accountMapper;

    @Override
    public int deleteByPrimaryKey(Integer accountId) {
        return accountMapper.deleteByPrimaryKey(accountId);
    }

    @Override
    public boolean insert(Account record) {
        return accountMapper.insert(record)>0?true:false;
    }

    @Override
    public int insertSelective(Account record) {
        return accountMapper.insertSelective(record);
    }

    @Override
    public Account selectByPrimaryKey(Integer accountId) {
        return accountMapper.selectByPrimaryKey(accountId);
    }

    @Override
    public int updateByPrimaryKeySelective(Account record) {
        return accountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public boolean update(Account record) {
        return accountMapper.update(record)>0?true:false;
    }

    @Override
    public Account login(String account, String password) {
        return accountMapper.login(account,password);
    }

    @Override
    public List<Account> find(Account account) {
        return accountMapper.find(account);
    }

    @Override
    public Account findById(Integer id) {
        return accountMapper.findById(id);
    }

    @Override
    public boolean deleteByIds(Integer... ids) {
        return accountMapper.deleteByIds(ids)>0?true:false;
    }

}
