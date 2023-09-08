package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Room;
import com.example.hotelmanagesystem.entity.Salary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kevin
 * @since 2023-06-01
 */
public interface ISalaryService extends IService<Salary> {

    Page<Salary> findPage(Page<Salary> objectPage, String salaryNo, String empNo);

    void add();
    void removeBySalaryNo(String salaryNo);

    void removeBySalaryNos(List<String> salaryNos);
}
