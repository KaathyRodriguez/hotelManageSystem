package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2023-05-31
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    Page<Employee> findPage(Page<Employee> page, @Param("empNo") String empNo, @Param("memberName") String memberName, @Param("empPostName") String empPostName);
    void removeByEmpNo(@Param("empNo") String empNo);

    void removeByEmpNos(@Param("empNos") List<String> empNos);

}
