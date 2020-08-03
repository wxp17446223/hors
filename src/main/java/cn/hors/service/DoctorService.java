package cn.hors.service;

import cn.hors.bean.Doctor;

import java.util.List;

public interface DoctorService{

    /**
     * 通过医生ID 查询医生信息
     * @param id
     * @return
     */
    Doctor findById(Integer id);

    /**
     * 动态查询所有医生信息
     * @param doctor
     * @return
     */
    List<Doctor> findAll(Doctor doctor);

    /**
     * 通过医生所在科室ID 查询所有医生信息
     * @param departId
     * @return
     */
    List<Doctor> findAllByDepartId(Integer departId);
}
