package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.common.Result;
import com.example.hotelmanagesystem.entity.Room;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.ISupplierService;
import com.example.hotelmanagesystem.entity.Supplier;

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
@RequestMapping("/supplier")
public class SupplierController {

    @Resource
    private ISupplierService supplierService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Supplier supplier) {
        supplierService.saveOrUpdate(supplier);
        return Result.success();
    }

    @DeleteMapping("/{supplierNo}")
    public Result delete(@PathVariable String supplierNo) {
        supplierService.removeBySupplierNo(supplierNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> supplierNos) {
        supplierService.removeBySupplierNos(supplierNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(supplierService.list());
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String supplierNo,
                           @RequestParam(defaultValue = "") String supplierName,
                           @RequestParam(defaultValue = "") String contactPersonName) {

        return Result.success(supplierService.findPage(new Page<>(pageNum, pageSize), supplierNo,supplierName,contactPersonName));
    }

}

