package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Supplier;
import com.example.hotelmanagesystem.entity.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2023-06-01
 */
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    Page<Warehouse> findPage(Page<Warehouse> page, @Param("warehouseNo") String warehouseNo, @Param("warehouseName") String warehouseName, @Param("warehouseManager") String warehouseManager);


    void removeByWarehouseNo(@Param("warehouseNo") String warehouseNo);

    void removeByWarehouseNos(@Param("warehouseNos") List<String> warehouseNos);

}
