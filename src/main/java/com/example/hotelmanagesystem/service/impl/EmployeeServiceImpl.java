package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Employee;
import com.example.hotelmanagesystem.entity.User;
import com.example.hotelmanagesystem.mapper.EmployeeMapper;
import com.example.hotelmanagesystem.mapper.UserMapper;
import com.example.hotelmanagesystem.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kevin
 * @since 2023-05-31
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Page<Employee> findPage(Page<Employee> page, String empNo, String memberName, String empPostName) {
        return employeeMapper.findPage(page, empNo, memberName, empPostName);
    };
    @Override
    public void removeByEmpNo(String empNo)
    {
        employeeMapper.removeByEmpNo(empNo);
    };

    @Override
    public void removeByEmpNos(List<String> empNos)
    {
        employeeMapper.removeByEmpNos(empNos);
    };

}
