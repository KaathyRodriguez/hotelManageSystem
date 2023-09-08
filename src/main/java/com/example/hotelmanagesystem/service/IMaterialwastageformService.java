package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Checkinform;
import com.example.hotelmanagesystem.entity.Materialwastageform;
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
public interface IMaterialwastageformService extends IService<Materialwastageform> {


    Page<Materialwastageform> findPage(Page<Materialwastageform> page, String materialWastageFormNo, String lossGoods, String lossDate);

    void removeByMaterialWastageFormNo(String materialWastageFormNo);

    void removeByMaterialWastageFormNos(List<String> materialWastageFormNos);

}
