package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Customer;
import com.example.hotelmanagesystem.entity.Department;
import com.example.hotelmanagesystem.mapper.CustomerMapper;
import com.example.hotelmanagesystem.mapper.DepartmentMapper;
import com.example.hotelmanagesystem.service.ICustomerService;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Page<Customer> findPage(Page<Customer> page, String memberNo, String memberName, String memberSex) {
        return customerMapper.findPage(page, memberNo, memberName, memberSex);
    };
    @Override
    public void removeByMemberNo(String memberNo)
    {
        customerMapper.removeByMemberNo(memberNo);
    };

    @Override
    public void removeByMemberNos(List<String> memberNos)
    {
        customerMapper.removeByMemberNos(memberNos);
    };
}
