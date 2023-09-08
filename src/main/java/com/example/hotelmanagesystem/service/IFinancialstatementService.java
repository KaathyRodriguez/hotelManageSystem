package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Department;
import com.example.hotelmanagesystem.entity.Financialstatement;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kevin
 * @since 2023-06-02
 */
public interface IFinancialstatementService extends IService<Financialstatement> {

    Page<Financialstatement> findPage(Page<Financialstatement> page, String statementNo, String statementDate, String auditor);

    void removeByStatementNo(String statementNo);

    void removeByStatementNos(List<String> statementNos);

}
