package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Customer;
import com.example.hotelmanagesystem.entity.Roomorderform;
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
public interface IRoomorderformService extends IService<Roomorderform> {


    Page<Roomorderform> findPage(Page<Roomorderform> page, String orderID, String memberName, String orderStatus);

    void removeByOrderID(String orderID);

    void removeByOrderIDs(List<String> orderIDs);
}
