package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.IRoomService;
import com.example.hotelmanagesystem.entity.Room;

import org.springframework.web.bind.annotation.RestController;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kevin
 * @since 2023-05-23
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Resource
    private IRoomService roomService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Room room) {
        roomService.saveOrUpdate(room);
        return Result.success();
    }

    @DeleteMapping("/{roomNo}")
    public Result delete(@PathVariable String roomNo) {
        roomService.removeByRoomNo(roomNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> roomNos) {
        return Result.success(roomService.removeByIds(roomNos));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(roomService.list());
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String roomNo,
                           @RequestParam(defaultValue = "") String roomType,
                           @RequestParam(defaultValue = "") String roomStatus) {
//        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("roomNo");
//        if (!"".equals(roomNo)) {
//            queryWrapper.like("roomNo", roomNo);
//        }
//        if (!"".equals(roomType)) {
//            queryWrapper.like("roomType", roomType);
//        }
//        if (!"".equals(roomStatus)) {
//            queryWrapper.like("roomStatus", roomStatus);
//        }

        return Result.success(roomService.findPage(new Page<>(pageNum, pageSize), roomNo,roomType,roomStatus));
    }

}

