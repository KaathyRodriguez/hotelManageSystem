package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Department;
import com.example.hotelmanagesystem.entity.Goods;
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
public interface IGoodsService extends IService<Goods> {


    Page<Goods> findPage(Page<Goods> page, String goodsNo, String goodsName, String goodsType);

    void removeByGoodsNo(String goodsNo);

    void removeByGoodsNos(List<String> goodsNos);

}
