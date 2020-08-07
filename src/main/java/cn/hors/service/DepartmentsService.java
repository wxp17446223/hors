package cn.hors.service;

import cn.hors.bean.Departments;
import cn.hors.bean.Doctor;

import java.util.List;

public interface DepartmentsService{


    Departments findById(Integer departId);

    List<Departments> findAll(Departments departments);

    List<Departments> findAllByDid(Integer d_id);

    Departments findAllByDepartId(Integer departId);

    boolean deleteByIds(Integer... ids);

    boolean update(Departments departments);

    boolean insert(Departments departments);

    List<Departments> findChildDepart();
}
