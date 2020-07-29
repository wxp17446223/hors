package cn.hors.mapper;

import cn.hors.bean.Departments;
import cn.hors.bean.Doctor;

import java.util.List;

public interface DepartmentsMapper {
    /**
     * 通过主键查询
     * @param departId 主键：科室ID
     * @return 返回科室ID对应的科室
     */
    Departments findById(Integer departId);

    /**
     * 动态查询所有科室
     * @param departments 科室信息
     * @return 返回所有科室列表
     */
    List<Departments> findAll(Departments departments);

    /**
     * 通过d_id查询所以科室
     * @param d_id d_id
     * @return 返回所有最终父科室列表
     */
    List<Departments> findAllByDid(Integer d_id);

    /**
     * 通过科室Id查询科室所有医生信息
     * @param departId
     * @return
     */
    Departments findAllByDepartId(Integer departId);




}