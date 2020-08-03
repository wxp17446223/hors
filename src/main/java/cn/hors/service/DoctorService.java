package cn.hors.service;

import cn.hors.bean.Doctor;

import java.util.List;

public interface DoctorService{


    Doctor findById(Integer id);

    List<Doctor> findAll(Doctor doctor);

    List<Doctor> findAllByDepartId(Integer departId);

    /**
     * 根据医生名称模糊查询医生
     * @param name 输入
     * @return 医生集合
     */
    List<Doctor> findByName(String name);
}
