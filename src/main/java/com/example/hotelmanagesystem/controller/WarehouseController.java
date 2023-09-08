package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.IWarehouseService;
import com.example.hotelmanagesystem.entity.Warehouse;

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
@RequestMapping("/warehouse")
public class WarehouseController {

    @Resource
    private IWarehouseService warehouseService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Warehouse warehouse) {
        warehouseService.saveOrUpdate(warehouse);
        return Result.success();
    }

    @DeleteMapping("/{warehouseNo}")
    public Result delete(@PathVariable String warehouseNo) {
        warehouseService.removeByWarehouseNo(warehouseNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> warehouseNos) {
        warehouseService.removeByWarehouseNos(warehouseNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(warehouseService.list());
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String warehouseNo,
                           @RequestParam(defaultValue = "") String warehouseName,
                           @RequestParam(defaultValue = "") String warehouseManager) {

        return Result.success(warehouseService.findPage(new Page<>(pageNum, pageSize), warehouseNo,warehouseName,warehouseManager));
    }

}

