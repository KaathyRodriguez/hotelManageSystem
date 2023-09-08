package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2023-05-30
 */
public interface SupplierMapper extends BaseMapper<Supplier> {

    Page<Supplier> findPage(Page<Supplier> page, @Param("supplierNo") String supplierNo, @Param("supplierName") String supplierName, @Param("contactPersonName") String contactPersonName);


    void removeBySupplierNo(@Param("supplierNo") String supplierNo);

    void removeBySupplierNos(@Param("supplierNos") List<String> supplierNos);


}
