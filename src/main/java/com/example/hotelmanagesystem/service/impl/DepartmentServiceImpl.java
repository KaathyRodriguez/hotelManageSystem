package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Department;
import com.example.hotelmanagesystem.entity.User;
import com.example.hotelmanagesystem.mapper.DepartmentMapper;
import com.example.hotelmanagesystem.mapper.UserMapper;
import com.example.hotelmanagesystem.service.IDepartmentService;
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
 * @since 2023-05-30
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public Page<Department> findPage(Page<Department> page, String deptNo, String deptName, String deptManager) {
        return departmentMapper.findPage(page, deptNo, deptName, deptManager);
    };
    @Override
    public void removeByDeptNo(String deptNo)
    {
        departmentMapper.removeByDeptNo(deptNo);
    };

    @Override
    public void removeByDeptNos(List<String> deptNos)
    {
        departmentMapper.removeByDeptNos(deptNos);
    };

}
