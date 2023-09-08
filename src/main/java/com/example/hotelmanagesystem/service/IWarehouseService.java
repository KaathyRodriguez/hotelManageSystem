package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Supplier;
import com.example.hotelmanagesystem.entity.Warehouse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kevin
 * @since 2023-06-01
 */
public interface IWarehouseService extends IService<Warehouse> {

    Page<Warehouse> findPage(Page<Warehouse> objectPage, String warehouseNo, String warehouseName, String warehouseManager);

    void removeByWarehouseNo(String warehouseNo);

    void removeByWarehouseNos(List<String> warehouseNos);

}
