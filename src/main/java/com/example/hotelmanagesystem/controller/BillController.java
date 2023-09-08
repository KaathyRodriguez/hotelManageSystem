package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.IBillService;
import com.example.hotelmanagesystem.entity.Bill;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kevin
 * @since 2023-06-02
 */
@RestController
@RequestMapping("/bill")
public class BillController {

    @Resource
    private IBillService billService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Bill bill) {
        billService.saveOrUpdate(bill);
        return Result.success();
    }

    @DeleteMapping("/{billNo}")
    public Result delete(@PathVariable String billNo) {
        billService.removeByBillNo(billNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> billNos) {
        billService.removeByBillNos(billNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(billService.list());
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String billNo,
                           @RequestParam(defaultValue = "") String consumeName,
                           @RequestParam(defaultValue = "") String billStatus) {
        return Result.success(billService.findPage(new Page<>(pageNum, pageSize), billNo,consumeName,billStatus));
    }

}

