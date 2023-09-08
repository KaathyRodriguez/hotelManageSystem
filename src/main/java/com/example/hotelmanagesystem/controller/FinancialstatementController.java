package com.example.hotelmanagesystem.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hotelmanagesystem.common.Result;

import com.example.hotelmanagesystem.service.IFinancialstatementService;
import com.example.hotelmanagesystem.entity.Financialstatement;

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
@RequestMapping("/financialstatement")
public class FinancialstatementController {

    @Resource
    private IFinancialstatementService financialstatementService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Financialstatement financialstatement) {
        financialstatementService.saveOrUpdate(financialstatement);
        return Result.success();
    }

    @DeleteMapping("/{statementNo}")
    public Result delete(@PathVariable String statementNo) {
        financialstatementService.removeByStatementNo(statementNo);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> statementNos) {
        financialstatementService.removeByStatementNos(statementNos);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(financialstatementService.list());
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String statementNo,
                           @RequestParam(defaultValue = "") String statementDate,
                           @RequestParam(defaultValue = "") String auditor) {
        return Result.success(financialstatementService.findPage(new Page<>(pageNum, pageSize), statementNo,statementDate,auditor));
    }

}

