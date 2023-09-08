package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2023-05-23
 */
public interface RoomMapper extends BaseMapper<Room> {

    Page<Room> findPage(Page<Room> page, @Param("roomNo") String roomNo, @Param("roomType") String roomType, @Param("roomStatus") String roomStatus);


    void removeByRoomNo(@Param("roomNo") String roomNo);

    void removeByRoomNos(@Param("roomNos") List<String> roomNos);


}
