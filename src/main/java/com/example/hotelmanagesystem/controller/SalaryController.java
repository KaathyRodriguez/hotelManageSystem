package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.ISalaryService;
import com.example.hotelmanagesystem.entity.Salary;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kevin
 * @since 2023-06-01
 */
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Resource
    private ISalaryService salaryService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Salary salary) {
        salaryService.saveOrUpdate(salary);
        return Result.success();
    }

    // 加薪
    @PostMapping("/add")
    public Result add() {
        salaryService.add();
        return Result.success();
    }

    @DeleteMapping("/{salaryNo}")
    public Result delete(@PathVariable String salaryNo) {
        salaryService.removeBySalaryNo(salaryNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> salaryNos) {
        salaryService.removeBySalaryNos(salaryNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(salaryService.list());
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String salaryNo,
                           @RequestParam(defaultValue = "") String empNo) {

        return Result.success(salaryService.findPage(new Page<>(pageNum, pageSize), salaryNo,empNo));
    }

}

