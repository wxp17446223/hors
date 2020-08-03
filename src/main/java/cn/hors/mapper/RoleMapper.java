package cn.hors.mapper;

import cn.hors.bean.PRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
 /**
  * 通过id得到角色
  * @param accountId 角色id
  * @return
  */
    List<PRole> findByAccountId(@Param("id") Integer accountId);

 /**
  * 新增角色
  * @param role 角色bean
  * @return
  */
 int insert(PRole role);

 /**
  * 更新角色
  * @param role 角色bean
  * @return
  */
     int update(PRole role);

 /**
  * 删除角色
  * @param ids 角色id集合
  * @return
  */
 int deleteByIds(@Param("ids") Integer... ids);

 /**
  * 查询所有角色
  * @param role 角色bean
  * @return
  */
     List<PRole> find(PRole role);

 /**
  * 通过id得到角色
  * @param id 角色id
  * @return
  */
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
