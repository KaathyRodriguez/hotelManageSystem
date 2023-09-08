package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Customer;
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
public interface CustomerMapper extends BaseMapper<Customer> {

    Page<Customer> findPage(Page<Customer> page, @Param("memberNo") String memberNo, @Param("memberName") String memberName, @Param("memberSex") String memberSex);


    void removeByMemberNo(@Param("memberNo") String memberNo);

    void removeByMemberNos(@Param("memberNos") List<String> memberNos);
}


