package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Bill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotelmanagesystem.entity.Customer;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kevin
 * @since 2023-06-02
 */
public interface IBillService extends IService<Bill> {

    Page<Bill> findPage(Page<Bill> page, String billNo, String billTime, String billStatus);

    void removeByBillNo(String billNo);

    void removeByBillNos(List<String> billNos);

}
