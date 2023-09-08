package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Cateringorderform;
import com.example.hotelmanagesystem.entity.Checkinform;
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
public interface ICheckinformService extends IService<Checkinform> {

    Page<Checkinform> findPage(Page<Checkinform> page, String checkInFormNo, String memberNo, String state);

    void removeByCheckInFormNo(String checkInFormNo);

    void removeByCheckInFormNos(List<String> checkInFormNos);

}
