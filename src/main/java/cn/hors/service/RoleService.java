package cn.hors.service;

import cn.hors.bean.PRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
    boolean insert(PRole role);
    boolean update(PRole role);
    boolean deleteById(@Param("ids") Integer... ids);
    List<PRole> find(PRole role);
    PRole findById(Integer id);
    List<PRole> findByAccountId(Integer roleId);

}
