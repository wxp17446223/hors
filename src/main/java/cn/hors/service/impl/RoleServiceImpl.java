package cn.hors.service.impl;

import cn.hors.bean.PRole;
import cn.hors.mapper.RoleMapper;
import cn.hors.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper mapper;

    @Override
    public boolean insert(PRole role) {
        return  mapper.insert(role)>0?true:false;
    }

    @Override
    public boolean update(PRole role) {
        return mapper.update(role)>0?true:false;
    }

    @Override
    public boolean deleteById(Integer... ids) {
        return mapper.deleteByIds(ids)>0?true:false;
    }

    @Override
    public List<PRole> find(PRole role) {
        return mapper.find(role);
    }

    @Override
    public PRole findById(Integer id) {
        return mapper.findById(id);
    }

    @Override
    public List<PRole> findByAccountId(Integer accountId) {
        return mapper.findByAccountId(accountId);
    }
}
