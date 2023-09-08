package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Room;
import com.example.hotelmanagesystem.entity.Roomorderform;
import com.example.hotelmanagesystem.mapper.RoomMapper;
import com.example.hotelmanagesystem.mapper.RoomorderformMapper;
import com.example.hotelmanagesystem.service.IRoomorderformService;
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
 * @since 2023-05-31
 */
@Service
public class RoomorderformServiceImpl extends ServiceImpl<RoomorderformMapper, Roomorderform> implements IRoomorderformService {

    @Resource
    private RoomorderformMapper roomorderformMapper;

    @Override
    public Page<Roomorderform> findPage(Page<Roomorderform> page, String orderID, String memberName, String orderStatus) {
        return roomorderformMapper.findPage(page, orderID, memberName, orderStatus);
    };
    @Override
    public void removeByOrderID(String orderID)
    {
        roomorderformMapper.removeByOrderID(orderID);
    };

    @Override
    public void removeByOrderIDs(List<String> orderIDs)
    {
        roomorderformMapper.removeByOrderIDs(orderIDs);
    };

}
