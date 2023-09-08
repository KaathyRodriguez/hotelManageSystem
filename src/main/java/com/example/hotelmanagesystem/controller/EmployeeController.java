package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.IEmployeeService;
import com.example.hotelmanagesystem.entity.Employee;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kevin
 * @since 2023-05-31
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private IEmployeeService employeeService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Employee employee) {
        employeeService.saveOrUpdate(employee);
        return Result.success();
    }

    @DeleteMapping("/{empNo}")
    public Result delete(@PathVariable String empNo) {
        employeeService.removeByEmpNo(empNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> empNos) {
        employeeService.removeByEmpNos(empNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(employeeService.list());
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String empNo,
                           @RequestParam(defaultValue = "") String memberName,
                           @RequestParam(defaultValue = "") String empPostName) {
        return Result.success(employeeService.findPage(new Page<>(pageNum, pageSize), empNo,memberName,empPostName));
    }

}

