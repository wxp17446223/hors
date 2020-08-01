package cn.hors.mapper;

import cn.hors.bean.PRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<PRole> findByAccountId(@Param("id") Integer accountId);
     int insert(PRole role);
     int update(PRole role);
     int deleteByIds(@Param("ids") Integer... ids);
     List<PRole> find(PRole role);
     PRole findById(@Param("id") Integer id);

 /**
  * 解除指定账号的角色绑定
  * @param accountId
  * @param roleIds
  */
 void bindRole(@Param("accountId") Integer accountId, @Param("roleIds") Integer[] roleIds);

 /**
  * 指定账号角色
  * @param accountId
  */
 void unBindRole(@Param("accountId") Integer accountId);

}
