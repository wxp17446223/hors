package cn.hors.service;

import cn.hors.bean.Account;

import java.util.List;

public interface AccountService{


    int deleteByPrimaryKey(Integer accountId);

    boolean insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    boolean update(Account record);

    Account login(String account, String password);

    List<Account> find(Account account);

    Account findById(Integer id);

    boolean deleteByIds(Integer... ids);
}
