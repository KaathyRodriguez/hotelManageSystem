package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.IGoodsService;
import com.example.hotelmanagesystem.entity.Goods;

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
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private IGoodsService goodsService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Goods goods) {
        goodsService.saveOrUpdate(goods);
        return Result.success();
    }

    @DeleteMapping("/{goodsNo}")
    public Result delete(@PathVariable String goodsNo) {
        goodsService.removeByGoodsNo(goodsNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> goodsNos) {
        goodsService.removeByGoodsNos(goodsNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(goodsService.list());
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String goodsNo,
                           @RequestParam(defaultValue = "") String goodsName,
                           @RequestParam(defaultValue = "") String goodsType) {
        return Result.success(goodsService.findPage(new Page<>(pageNum, pageSize), goodsNo,goodsName,goodsType));
    }

}

