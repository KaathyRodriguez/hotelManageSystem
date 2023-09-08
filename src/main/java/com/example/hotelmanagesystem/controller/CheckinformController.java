package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.ICheckinformService;
import com.example.hotelmanagesystem.entity.Checkinform;

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
@RequestMapping("/checkinform")
public class CheckinformController {

    @Resource
    private ICheckinformService checkinformService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Checkinform checkinform) {
        checkinformService.saveOrUpdate(checkinform);
        return Result.success();
    }

    @DeleteMapping("/{checkInFormNo}")
    public Result delete(@PathVariable String checkInFormNo) {
        checkinformService.removeByCheckInFormNo(checkInFormNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> checkInFormNos) {
        checkinformService.removeByCheckInFormNos(checkInFormNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(checkinformService.list());
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String checkInFormNo,
                           @RequestParam(defaultValue = "") String memberName,
                           @RequestParam(defaultValue = "") String state) {

//        QueryWrapper<Checkinform> queryWrapper = new QueryWrapper<>();
//        if (!"".equals(checkInFormNo)) {
//            queryWrapper.like("checkInFormNo", checkInFormNo);
//        }
//        if (!"".equals(memberName)) {
//            queryWrapper.like("memberName", memberName);
//        }
//        if (!"".equals(state)) {
//            queryWrapper.like("roomStatus", state);
//        }
//        return Result.success(checkinformService.page(new Page<>(pageNum, pageSize), queryWrapper));
        return Result.success(checkinformService.findPage(new Page<>(pageNum, pageSize), checkInFormNo,memberName,state));
    }

}

