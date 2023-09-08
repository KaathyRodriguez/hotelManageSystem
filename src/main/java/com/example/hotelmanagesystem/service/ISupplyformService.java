package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Cateringorderform;
import com.example.hotelmanagesystem.entity.Supplyform;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kevin
 * @since 2023-05-31
 */
public interface ISupplyformService extends IService<Supplyform> {

    Page<Supplyform> findPage(Page<Supplyform> page, String supplyFormNo, String purchaseDate, String purchaseManager);

    void removeBySupplyFormNo(String supplyFormNo);

    void removeBySupplyFormNos(List<String> supplyFormNos);

}
