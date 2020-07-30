package cn.hors.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.hors.mapper.DepartmentsMapper;
import cn.hors.bean.Departments;
import cn.hors.service.DepartmentsService;

import java.util.List;

@Service
public class DepartmentsServiceImpl implements DepartmentsService{

    @Resource
    private DepartmentsMapper departmentsMapper;

    @Override
    public Departments findById(Integer departId) {
        return departmentsMapper.findById(departId);
    }

    @Override
    public List<Departments> findAllByDid(Integer d_id) {
        return departmentsMapper.findAllByDid(d_id);
    }

    @Override
    public List<Departments> findAll(Departments departments) {
        return departmentsMapper.findAll(departments);
    }

    @Override
    public Departments findAllByDepartId(Integer departId) {
        return departmentsMapper.findAllByDepartId(departId);
    }
}
