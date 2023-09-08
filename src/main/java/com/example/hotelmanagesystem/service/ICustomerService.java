package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotelmanagesystem.entity.Department;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kevin
 * @since 2023-05-31
 */
public interface ICustomerService extends IService<Customer> {

    Page<Customer> findPage(Page<Customer> page, String memberNo, String memberName, String memberSex);

    void removeByMemberNo(String memberNo);

    void removeByMemberNos(List<String> memberNos);

}
