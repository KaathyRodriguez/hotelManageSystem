package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kevin
 * @since 2023-05-23
 */
public interface IRoomService extends IService<Room> {

    Page<Room> findPage(Page<Room> objectPage, String roomNo, String roomType, String roomStatus);

    void removeByRoomNo(String roomNo);

    void removeByRoomNos(List<String> roomNos);

}
