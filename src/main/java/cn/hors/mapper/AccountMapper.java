package cn.hors.mapper;

import cn.hors.bean.Account;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    int update(Account record);

    Account login( @Param("account")String account,@Param("password") String password);

    List<Account> find(Account account);

    int deleteByIds(@Param("ids") Integer ... ids);

    Account findById(@Param("id") Integer id);
}