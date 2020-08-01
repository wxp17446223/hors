package cn.hors.service;

import cn.hors.bean.PResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PResourceService{
    void authorization(Integer accountId, Integer... resourceIds);
    boolean insertResource(PResource resource);
    boolean updateResource(PResource resource);
    boolean deleteResourceById(@Param("ids") Integer... ids);
    List<PResource> find(PResource resource);
    PResource findById(Integer id);
    List<PResource> findByRoleId(Integer accountId);
}
