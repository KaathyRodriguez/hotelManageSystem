package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.ISupplyformService;
import com.example.hotelmanagesystem.entity.Supplyform;

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
@RequestMapping("/supplyform")
public class SupplyformController {

    @Resource
    private ISupplyformService supplyformService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Supplyform supplyform) {
        supplyformService.saveOrUpdate(supplyform);
        return Result.success();
    }

    @DeleteMapping("/{supplyFormNo}")
    public Result delete(@PathVariable String supplyFormNo) {
        supplyformService.removeBySupplyFormNo(supplyFormNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> supplyFormNos) {
        supplyformService.removeBySupplyFormNos(supplyFormNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(supplyformService.list());
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String supplyFormNo,
                           @RequestParam(defaultValue = "") String purchaseDate,
                           @RequestParam(defaultValue = "") String purchaseManager) {
        return Result.success(supplyformService.findPage(new Page<>(pageNum, pageSize), supplyFormNo,purchaseDate,purchaseManager));
    }

}

