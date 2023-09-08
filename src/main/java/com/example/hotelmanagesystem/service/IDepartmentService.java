package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotelmanagesystem.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kevin
 * @since 2023-05-30
 */
public interface IDepartmentService extends IService<Department> {

    Page<Department> findPage(Page<Department> page, String deptNo, String deptName, String deptManager);

    void removeByDeptNo(String deptNo);

    void removeByDeptNos(List<String> deptNos);
}
