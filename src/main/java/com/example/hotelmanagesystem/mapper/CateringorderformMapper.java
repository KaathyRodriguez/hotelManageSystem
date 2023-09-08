package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Cateringorderform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotelmanagesystem.entity.Roomorderform;
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
public interface CateringorderformMapper extends BaseMapper<Cateringorderform> {

    Page<Cateringorderform> findPage(Page<Cateringorderform> page, @Param("orderNo") String orderNo, @Param("purchaseDate") String purchaseDate, @Param("orderType") String orderType);


    void removeByOrderNo(@Param("orderNo") String orderNo);

    void removeByOrderNos(@Param("orderNos") List<String> orderNos);


}
