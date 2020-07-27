package cn.hors.mapper;

import cn.hors.bean.Departments;

import java.util.List;

public interface DepartmentsMapper {
    Departments findById(Integer id);

    List<Departments> findAll(Departments departments);
}