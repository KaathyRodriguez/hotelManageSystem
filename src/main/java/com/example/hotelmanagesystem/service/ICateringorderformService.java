package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Cateringorderform;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotelmanagesystem.entity.Roomorderform;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kevin
 * @since 2023-05-31
 */
public interface ICateringorderformService extends IService<Cateringorderform> {

    Page<Cateringorderform> findPage(Page<Cateringorderform> page, String orderNo, String purchaseDate, String orderType);

    void removeByOrderNo(String orderNo);

    void removeByOrderNos(List<String> orderNos);
}
