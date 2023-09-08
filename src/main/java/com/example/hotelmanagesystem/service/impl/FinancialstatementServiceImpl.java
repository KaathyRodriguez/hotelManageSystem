package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Department;
import com.example.hotelmanagesystem.entity.Financialstatement;
import com.example.hotelmanagesystem.mapper.DepartmentMapper;
import com.example.hotelmanagesystem.mapper.FinancialstatementMapper;
import com.example.hotelmanagesystem.service.IFinancialstatementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kevin
 * @since 2023-06-02
 */
@Service
public class FinancialstatementServiceImpl extends ServiceImpl<FinancialstatementMapper, Financialstatement> implements IFinancialstatementService {

    @Resource
    private FinancialstatementMapper financialstatementMapper;

    @Override
    public Page<Financialstatement> findPage(Page<Financialstatement> page, String statementNo, String statementDate, String auditor) {
        return financialstatementMapper.findPage(page, statementNo, statementDate, auditor);
    };
    @Override
    public void removeByStatementNo(String statementNo)
    {
        financialstatementMapper.removeByStatementNo(statementNo);
    };

    @Override
    public void removeByStatementNos(List<String> statementNos)
    {
        financialstatementMapper.removeByStatementNos(statementNos);
    };
}
