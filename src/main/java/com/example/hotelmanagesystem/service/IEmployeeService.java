package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotelmanagesystem.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kevin
 * @since 2023-05-31
 */
public interface IEmployeeService extends IService<Employee> {

    Page<Employee> findPage(Page<Employee> page, String empNo, String memberName, String empPostName);

    void removeByEmpNo(String empNo);

    void removeByEmpNos(List<String> empNos);

}
