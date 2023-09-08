package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.IRoomorderformService;
import com.example.hotelmanagesystem.entity.Roomorderform;

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
@RequestMapping("/roomorderform")
public class RoomorderformController {

    @Resource
    private IRoomorderformService roomorderformService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Roomorderform roomorderform) {
        roomorderformService.saveOrUpdate(roomorderform);
        return Result.success();
    }

    @DeleteMapping("/{orderID}")
    public Result delete(@PathVariable String orderID) {
        roomorderformService.removeByOrderID(orderID);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> orderIDs) {
        roomorderformService.removeByOrderIDs(orderIDs);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(roomorderformService.list());
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String orderID,
                           @RequestParam(defaultValue = "") String memberName,
                           @RequestParam(defaultValue = "") String orderStatus) {

        return Result.success(roomorderformService.findPage(new Page<>(pageNum, pageSize), orderID,memberName,orderStatus));
    }

}

