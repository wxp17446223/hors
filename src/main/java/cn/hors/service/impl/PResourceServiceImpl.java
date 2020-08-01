package cn.hors.service.impl;

import cn.hors.bean.PResource;
import cn.hors.mapper.PResourceMapper;
import cn.hors.service.PResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PResourceServiceImpl implements PResourceService {

    @Resource
    private PResourceMapper pResourceMapper;

    @Transactional
    @Override
    public void authorization(Integer accountId, Integer... resourceIds) {
        //每次提交都清除授权
        pResourceMapper.deleteAuthorization(accountId);
        //重新授权
        if(resourceIds != null &&resourceIds.length>0) {
            pResourceMapper.Authorization(accountId, resourceIds);
        }
    }

    @Override
    public boolean insertResource(PResource resource) {
        return pResourceMapper.insertResource(resource)>0?true:false;
    }

    @Override
    public boolean updateResource(PResource resource) {
        return pResourceMapper.updateResource(resource)>0?true:false;
    }

    @Override
    public boolean deleteResourceById(Integer... ids) {
        for (Integer id : ids) {
            List<Integer> idByPid = pResourceMapper.findIdByPid(id);
            for (Integer sid : idByPid) {
                pResourceMapper.deleteResourceById(sid);
            }
        }
        return pResourceMapper.deleteResourceById(ids)>0?true:false;
    }

    @Override
    public List<PResource> find(PResource resource) {
        return pResourceMapper.find(resource);
    }

    @Override
    public PResource findById(Integer id) {
        return pResourceMapper.findById(id);
    }

    @Override
    public List<PResource> findByRoleId(Integer roleId) {
        return pResourceMapper.findByRoleId(roleId);
    }
}
