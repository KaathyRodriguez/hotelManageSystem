package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Bill;
import com.example.hotelmanagesystem.entity.Customer;
import com.example.hotelmanagesystem.mapper.BillMapper;
import com.example.hotelmanagesystem.mapper.CustomerMapper;
import com.example.hotelmanagesystem.service.IBillService;
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
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

    @Resource
    private BillMapper billMapper;

    @Override
    public Page<Bill> findPage(Page<Bill> page, String billNo, String billTime, String billStatus) {
        return billMapper.findPage(page, billNo, billTime, billStatus);
    };
    @Override
    public void removeByBillNo(String billNo)
    {
        billMapper.removeByBillNo(billNo);
    };

    @Override
    public void removeByBillNos(List<String> billNos)
    {
        billMapper.removeByBillNos(billNos);
    };

}
