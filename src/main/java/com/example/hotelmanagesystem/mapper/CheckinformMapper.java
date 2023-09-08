package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Cateringorderform;
import com.example.hotelmanagesystem.entity.Checkinform;
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
public interface CheckinformMapper extends BaseMapper<Checkinform> {


    Page<Checkinform> findPage(Page<Checkinform> page, @Param("checkInFormNo") String checkInFormNo, @Param("memberNo") String memberNo, @Param("state") String state);


    void removeByCheckInFormNo(@Param("checkInFormNo") String checkInFormNo);

    void removeByCheckInFormNos(@Param("checkInFormNos") List<String> checkInFormNos);


}
