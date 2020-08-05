package cn.hors.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.hors.bean.Doctor;
import cn.hors.mapper.DoctorMapper;
import cn.hors.service.DoctorService;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Resource
    private DoctorMapper doctorMapper;

    @Override
    public Doctor findById(Integer id) {
        return doctorMapper.findById(id);
    }

    @Override
    public List<Doctor> findAll(Doctor doctor) {
        return doctorMapper.findAll(doctor);
    }

    @Override
    public List<Doctor> findAllByDepartId(Integer departId) {
        return doctorMapper.findAllByDepartId(departId);
    }

    @Override
    public List<Doctor> findByName(String name) {
        return doctorMapper.findByName(name);
    }

    @Override
    public Doctor findByOrd(Integer id) {
        return doctorMapper.findByOrd(id);
    }

    @Override
    public boolean update(Doctor doctor) {
        return doctorMapper.update(doctor)>0;
    }

    @Override
    public boolean insert(Doctor doctor) {
        return doctorMapper.insert(doctor)>0;
    }

    @Override
    public Doctor findByJobNumber(Integer jobNumber) {
        return doctorMapper.findByJobNumber(jobNumber);
    }

    @Override
    public boolean deleteByIds(Integer[] ids) {
        return doctorMapper.deleteByIds(ids)>0;
    }
}
