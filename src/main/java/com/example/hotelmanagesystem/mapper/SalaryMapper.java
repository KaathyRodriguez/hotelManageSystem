package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Room;
import com.example.hotelmanagesystem.entity.Salary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2023-06-01
 */
public interface SalaryMapper extends BaseMapper<Salary> {

    Page<Salary> findPage(Page<Salary> page, @Param("salaryNo") String salaryNo, @Param("empNo") String empNo);


    void removeBySalaryNo(@Param("salaryNo") String salaryNo);

    void removeBySalaryNos(@Param("salaryNos") List<String> salaryNos);

    void add();
}
