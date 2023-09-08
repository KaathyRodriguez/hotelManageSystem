package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.IDepartmentService;
import com.example.hotelmanagesystem.entity.Department;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kevin
 * @since 2023-05-30
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private IDepartmentService departmentService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Department department) {
        departmentService.saveOrUpdate(department);
        return Result.success();
    }

    @DeleteMapping("/{deptNo}")
    public Result delete(@PathVariable String deptNo) {
        departmentService.removeByDeptNo(deptNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> deptNos) {
        departmentService.removeByDeptNos(deptNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(departmentService.list());
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String deptNo,
                           @RequestParam(defaultValue = "") String deptName,
                           @RequestParam(defaultValue = "") String deptManager) {

        return Result.success(departmentService.findPage(new Page<>(pageNum, pageSize), deptNo,deptName,deptManager));
    }

}

