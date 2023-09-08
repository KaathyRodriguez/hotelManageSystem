package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.ICateringorderformService;
import com.example.hotelmanagesystem.entity.Cateringorderform;

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
@RequestMapping("/cateringorderform")
public class CateringorderformController {

    @Resource
    private ICateringorderformService cateringorderformService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Cateringorderform cateringorderform) {
        cateringorderformService.saveOrUpdate(cateringorderform);
        return Result.success();
    }

    @DeleteMapping("/{orderNo}")
    public Result delete(@PathVariable String orderNo) {
        cateringorderformService.removeByOrderNo(orderNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> orderNos) {
        cateringorderformService.removeByOrderNos(orderNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(cateringorderformService.list());
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String orderNo,
                           @RequestParam(defaultValue = "") String purchaseDate,
                           @RequestParam(defaultValue = "") String orderType) {

        return Result.success(cateringorderformService.findPage(new Page<>(pageNum, pageSize), orderNo,purchaseDate,orderType));
    }

}

