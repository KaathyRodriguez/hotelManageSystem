package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.ICustomerService;
import com.example.hotelmanagesystem.entity.Customer;

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
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private ICustomerService customerService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Customer customer) {
        customerService.saveOrUpdate(customer);
        return Result.success();
    }

    @DeleteMapping("/{memberNo}")
    public Result delete(@PathVariable String memberNo) {
        customerService.removeByMemberNo(memberNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> memberNos) {
        customerService.removeByMemberNos(memberNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(customerService.list());
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String memberNo,
                           @RequestParam(defaultValue = "") String memberName,
                           @RequestParam(defaultValue = "") String memberSex) {

        return Result.success(customerService.findPage(new Page<>(pageNum, pageSize), memberNo,memberName,memberSex));
    }

}

