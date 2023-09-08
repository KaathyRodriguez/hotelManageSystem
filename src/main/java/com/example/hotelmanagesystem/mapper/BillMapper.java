package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Bill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotelmanagesystem.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2023-06-02
 */
public interface BillMapper extends BaseMapper<Bill> {

    Page<Bill> findPage(Page<Bill> page, @Param("billNo") String billNo, @Param("billTime") String billTime, @Param("billStatus") String billStatus);


    void removeByBillNo(@Param("billNo") String billNo);

    void removeByBillNos(@Param("billNos") List<String> billNos);

}
