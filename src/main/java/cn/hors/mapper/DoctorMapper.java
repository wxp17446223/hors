package cn.hors.mapper;

import cn.hors.bean.Doctor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorMapper {

    Doctor findById(Integer id);

    List<Doctor> findAll(Doctor doctor);

    List<Doctor> findAllByDepartId(Integer departId);

    /**
     * 通过名字模糊查询医生
     * @param name 输入
     * @return 医生集合
     */
    List<Doctor> findByName(@Param("name") String name);


}