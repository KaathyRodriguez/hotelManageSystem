package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Employee;
import com.example.hotelmanagesystem.entity.Goods;
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
public interface GoodsMapper extends BaseMapper<Goods> {


    Page<Goods> findPage(Page<Goods> page, @Param("goodsNo") String goodsNo, @Param("goodsName") String goodsName, @Param("goodsType") String goodsType);
    void removeByGoodsNo(@Param("goodsNo") String goodsNo);

    void removeByGoodsNos(@Param("goodsNos") List<String> goodsNos);


}
