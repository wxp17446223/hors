package cn.hors.service;

import cn.hors.bean.PAccount;

import java.util.List;

public interface PAccountService{
    boolean insertAccount(PAccount account, Integer[] roleIds);
    boolean updateAccount(PAccount account, Integer[] roleIds);
    boolean deleteAccountById(Integer... ids);
    List<PAccount> find(PAccount account);
    PAccount findByAccount(String account);
    int updateHead(PAccount pAccount);
}
