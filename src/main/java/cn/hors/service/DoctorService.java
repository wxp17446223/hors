package cn.hors.service;

import cn.hors.bean.Doctor;

import java.util.List;

public interface DoctorService{


    Doctor findById(Integer id);

    List<Doctor> findAll(Doctor doctor);

    List<Doctor> findAllByDepartId(Integer departId);
}
