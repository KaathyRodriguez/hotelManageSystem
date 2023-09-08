package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Room;
import com.example.hotelmanagesystem.entity.Salary;
import com.example.hotelmanagesystem.mapper.RoomMapper;
import com.example.hotelmanagesystem.mapper.SalaryMapper;
import com.example.hotelmanagesystem.service.ISalaryService;
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
 * @since 2023-06-01
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {

    @Resource
    private SalaryMapper salaryMapper;

    @Override
    public Page<Salary> findPage(Page<Salary> page, String salaryNo, String empNo) {
        return salaryMapper.findPage(page, salaryNo, empNo);
    };

    @Override
    public void add()
    {
      salaryMapper.add();
    };
    @Override
    public void removeBySalaryNo(String salaryNo)
    {
        salaryMapper.removeBySalaryNo(salaryNo);
    };

    @Override
    public void removeBySalaryNos(List<String> salaryNos)
    {
        salaryMapper.removeBySalaryNos(salaryNos);
    };

}
