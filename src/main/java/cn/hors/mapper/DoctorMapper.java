package cn.hors.mapper;

import cn.hors.bean.Doctor;

import java.util.List;

public interface DoctorMapper {
    Doctor findById(Integer id);

    List<Doctor> findAll(Doctor doctor);

    List<Doctor> findAllByDepartId(Integer departId);
}