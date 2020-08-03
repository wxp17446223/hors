package cn.hors.mapper;

import cn.hors.bean.PAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PAccountMapper {
    /**
     * 新增管理员
     * @param account 管理员bean
     * @return
     */
    int insertAccount(PAccount account);

    /**
     * 更新管理员
     * @param account 管理员bean
     * @return
     */
    int updateAccount(PAccount account);

    /**
     * 通过id删除管理员
     * @param ids 管理员id集合
     * @return
     */
    int deleteAccountById(@Param("ids") Integer... ids);

    /**
     * 查询所有管理员
     * @param account
     * @return
     */
    List<PAccount> find(PAccount account);

    /**
     * 删除管理员与资源的联系
     * @param ids
     */
    void deleteMapping(@Param("ids") Integer... ids);

    /**
     * 通过id得到管理员
     * @param name
     * @return
     */
    PAccount findByAccout(@Param("name") String name);

    /**
     * 更新管理员头像
     * @param pAccount
     * @return
     */
    int updateHead(PAccount pAccount);
}