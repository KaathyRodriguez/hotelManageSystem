package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Room;
import com.example.hotelmanagesystem.entity.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kevin
 * @since 2023-05-30
 */
public interface ISupplierService extends IService<Supplier> {
    Page<Supplier> findPage(Page<Supplier> objectPage, String supplierNo, String supplierName, String contactPersonName);

    void removeBySupplierNo(String supplierNo);

    void removeBySupplierNos(List<String> supplierNos);
}
