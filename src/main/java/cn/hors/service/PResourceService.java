package cn.hors.service;

import cn.hors.bean.PResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PResourceService{
    /**
     * 授权
     * @param accountId 账号id
     * @param resourceIds  资源id集合
     */
    void authorization(Integer accountId, Integer... resourceIds);

    /**
     * 新增资源
     * @param resource 资源bean
     * @return
     */
    boolean insertResource(PResource resource);

    /**
     * 更新资源
     * @param resource 资源bean
     * @return
     */
    boolean updateResource(PResource resource);

    /**
     * 通过id删除资源
     * @param ids 资源id集合
     * @return
     */
    boolean deleteResourceById(@Param("ids") Integer... ids);

    /**
     * 查询资源
     * @param resource 资源bean
     * @return
     */
    List<PResource> find(PResource resource);

    /**
     * 通过id查询资源
     * @param id 资源id
     * @return
     */
    PResource findById(Integer id);

    /**
     * 得到该id角色的所有资源
     * @param accountId 角色id
     * @return
     */
    List<PResource> findByRoleId(Integer accountId);
}
