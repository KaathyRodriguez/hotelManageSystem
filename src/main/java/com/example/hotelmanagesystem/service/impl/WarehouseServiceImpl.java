package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Supplier;
import com.example.hotelmanagesystem.entity.Warehouse;
import com.example.hotelmanagesystem.mapper.SupplierMapper;
import com.example.hotelmanagesystem.mapper.WarehouseMapper;
import com.example.hotelmanagesystem.service.IWarehouseService;
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
 * @since 2023-06-01
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseService {

    @Resource
    private WarehouseMapper warehouseMapper;

    @Override
    public Page<Warehouse> findPage(Page<Warehouse> page, String warehouseNo, String warehouseName, String warehouseManager) {
        return warehouseMapper.findPage(page, warehouseNo, warehouseName, warehouseManager);
    };
    @Override
    public void removeByWarehouseNo(String warehouseNo)
    {
        warehouseMapper.removeByWarehouseNo(warehouseNo);
    };

    @Override
    public void removeByWarehouseNos(List<String> warehouseNos)
    {
        warehouseMapper.removeByWarehouseNos(warehouseNos);
    };

}
