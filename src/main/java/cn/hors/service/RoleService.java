package cn.hors.service;

import cn.hors.bean.PRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
    /**
     * 新增角色
     * @param role 角色bean
     * @return
     */
    boolean insert(PRole role);

    /**
     * 更新角色
     * @param role 角色bean
     * @return
     */
    boolean update(PRole role);

    /**
     * 删除角色
     * @param ids 角色id集合
     * @return
     */
    boolean deleteById(@Param("ids") Integer... ids);

    /**
     * 查询角色
     * @param role 角色bean
     * @return
     */
    List<PRole> find(PRole role);

    /**
     * 通过id查询角色
     * @param id 角色id
     * @return
     */
    PRole findById(Integer id);

    /**
     * 查询账号的所有资源
     * @param roleId 账号id
     * @return
     */
    List<PRole> findByAccountId(Integer roleId);

}
