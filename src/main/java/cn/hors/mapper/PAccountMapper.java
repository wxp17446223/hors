package cn.hors.mapper;

import cn.hors.bean.PAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PAccountMapper {
    int insertAccount(PAccount account);
    int updateAccount(PAccount account);
    int deleteAccountById(@Param("ids") Integer... ids);
    List<PAccount> find(PAccount account);
    void deleteMapping(@Param("ids") Integer... ids);
    PAccount findByAccout(@Param("name") String name);
    int updateHead(PAccount pAccount);
}