package cn.hors.service;

import cn.hors.bean.Departments;

import java.util.List;

public interface DepartmentsService{


    Departments findById(Integer id);

    List<Departments> findAll(Departments departments);
}
