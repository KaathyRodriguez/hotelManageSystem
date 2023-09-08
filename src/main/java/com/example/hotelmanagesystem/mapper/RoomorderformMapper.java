package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Room;
import com.example.hotelmanagesystem.entity.Roomorderform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2023-05-31
 */
public interface RoomorderformMapper extends BaseMapper<Roomorderform> {

    Page<Roomorderform> findPage(Page<Roomorderform> page, @Param("orderID") String orderID, @Param("memberName") String memberName, @Param("orderStatus") String orderStatus);


    void removeByOrderID(@Param("orderID") String orderID);

    void removeByOrderIDs(@Param("orderIDs") List<String> orderIDs);

}
