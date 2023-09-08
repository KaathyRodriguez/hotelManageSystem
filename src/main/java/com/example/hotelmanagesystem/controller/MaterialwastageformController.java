package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.IMaterialwastageformService;
import com.example.hotelmanagesystem.entity.Materialwastageform;

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
@RequestMapping("/materialwastageform")
public class MaterialwastageformController {

    @Resource
    private IMaterialwastageformService materialwastageformService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Materialwastageform materialwastageform) {
        materialwastageformService.saveOrUpdate(materialwastageform);
        return Result.success();
    }

    @DeleteMapping("/{materialWastageFormNo}")
    public Result delete(@PathVariable String materialWastageFormNo) {
        materialwastageformService.removeByMaterialWastageFormNo(materialWastageFormNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> materialWastageFormNos) {
        materialwastageformService.removeByMaterialWastageFormNos(materialWastageFormNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(materialwastageformService.list());
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String materialWastageFormNo,
                           @RequestParam(defaultValue = "") String lossGoods,
                           @RequestParam(defaultValue = "") String lossDate) {

        return Result.success(materialwastageformService.findPage(new Page<>(pageNum, pageSize), materialWastageFormNo,lossGoods,lossDate));
    }

}

