package cn.hors.service;

import cn.hors.bean.PAccount;

import java.util.List;

public interface PAccountService{
    /**
     * 新增账号
     * @param account 账号bean
     * @param roleIds 资源ids
     * @return
     */
    boolean insertAccount(PAccount account, Integer[] roleIds);

    /**
     * 更新账号
     * @param account 账号bean
     * @param roleIds 资源集合
     * @return
     */
    boolean updateAccount(PAccount account, Integer[] roleIds);

    /**
     * 删除账号
     * @param ids 账户id集合
     * * @return
     */
    boolean deleteAccountById(Integer... ids);

    /**
     * 查询所有账号
     * @param account 账号bean
     * @return
     */
    List<PAccount> find(PAccount account);

    /**
     * 查询账号
     * @param account 账号bean
     * @return
     */
    PAccount findByAccount(String account);

    /**
     * 跟新账号头像
     * @param pAccount 账号bean
     * @return
     */
    int updateHead(PAccount pAccount);
}
