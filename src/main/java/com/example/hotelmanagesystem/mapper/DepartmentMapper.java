package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2023-05-30
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    Page<Department> findPage(Page<Department> page, @Param("deptNo") String deptNo, @Param("deptName") String deptName, @Param("deptManager") String deptManager);


    void removeByDeptNo(@Param("deptNo") String deptNo);

    void removeByDeptNos(@Param("deptNos") List<String> deptNos);
}
