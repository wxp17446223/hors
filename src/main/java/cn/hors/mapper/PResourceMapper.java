package cn.hors.mapper;

import cn.hors.bean.PResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PResourceMapper {
    int insertResource(PResource resource);
    int updateResource(PResource resource);
    int deleteResourceById(@Param("ids") Integer... ids);
    List<PResource> find(PResource resource);
    PResource findById(@Param("id") Integer id);
    List<PResource> findByRoleId(@Param("roleId") Integer roleId);
    void deleteAuthorization(@Param("roleId") Integer roleId);
    List<Integer> findIdByPid(@Param("id") Integer id);
    void Authorization(@Param("roleId") Integer accountId, @Param("resourceIds") Integer... resourceIds);
}