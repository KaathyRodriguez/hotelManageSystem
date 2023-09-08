package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Room;
import com.example.hotelmanagesystem.mapper.RoomMapper;
import com.example.hotelmanagesystem.service.IRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kevin
 * @since 2023-05-23
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

    @Resource
    private RoomMapper roomMapper;

    @Override
    public Page<Room> findPage(Page<Room> page, String roomNo, String roomType, String roomStatus) {
        return roomMapper.findPage(page, roomNo, roomType, roomStatus);
    };
    @Override
    public void removeByRoomNo(String roomNo)
    {
        roomMapper.removeByRoomNo(roomNo);
    };

    @Override
    public void removeByRoomNos(List<String> roomNos)
    {
        roomMapper.removeByRoomNos(roomNos);
    };


}
