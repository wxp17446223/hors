package cn.hors.service;

import cn.hors.bean.Account;
public interface AccountService{


    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

}
