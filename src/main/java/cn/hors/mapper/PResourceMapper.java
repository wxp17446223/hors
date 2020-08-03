package cn.hors.mapper;

import cn.hors.bean.PResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PResourceMapper {
    /**
     * 新增资源
     * @param resource 资源bean
     * @return
     */
    int insertResource(PResource resource);

    /**
     * 更新资源
     * @param resource 资源bean
     * @return
     */
    int updateResource(PResource resource);

    /**
     * 通过id删除资源
     * @param ids 资源id集合
     * @return
     */
    int deleteResourceById(@Param("ids") Integer... ids);

    /**
     * 查询所有资源
     * @param resource
     * @return
     */
    List<PResource> find(PResource resource);

    /**
     * 通过id查询资源
     * @param id 资源id
     * @return
     */
    PResource findById(@Param("id") Integer id);

    /**
     * 查询角色所有的资源
     * @param roleId
     * @return
     */
    List<PResource> findByRoleId(@Param("roleId") Integer roleId);

    /**
     * 清空授权
     * @param roleId 角色id
     */
    void deleteAuthorization(@Param("roleId") Integer roleId);

    /**
     * 通过子id查询父id
     * @param id 子id
     * @return
     */
    List<Integer> findIdByPid(@Param("id") Integer id);

    /**
     * 对角色进行授权
     * @param accountId 角色id
     * @param resourceIds 资源id集合
     */
    void Authorization(@Param("roleId") Integer accountId, @Param("resourceIds") Integer... resourceIds);
}