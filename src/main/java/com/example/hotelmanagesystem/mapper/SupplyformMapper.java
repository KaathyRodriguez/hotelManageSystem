package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Cateringorderform;
import com.example.hotelmanagesystem.entity.Supplyform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2023-05-31
 */
public interface SupplyformMapper extends BaseMapper<Supplyform> {

    Page<Supplyform> findPage(Page<Supplyform> page, @Param("supplyFormNo") String supplyFormNo, @Param("purchaseDate") String purchaseDate, @Param("purchaseManager") String purchaseManager);


    void removeBySupplyFormNo(@Param("supplyFormNo") String supplyFormNo);

    void removeBySupplyFormNos(@Param("supplyFormNos") List<String> supplyFormNos);


}
