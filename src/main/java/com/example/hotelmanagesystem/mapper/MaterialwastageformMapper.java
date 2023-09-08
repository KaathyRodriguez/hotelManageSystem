package com.example.hotelmanagesystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Department;
import com.example.hotelmanagesystem.entity.Materialwastageform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kevin
 * @since 2023-06-01
 */
public interface MaterialwastageformMapper extends BaseMapper<Materialwastageform> {

    Page<Materialwastageform> findPage(Page<Materialwastageform> page, @Param("materialWastageFormNo") String materialWastageFormNo, @Param("lossGoods") String lossGoods, @Param("lossDate") String lossDate);


    void removeByMaterialWastageFormNo(@Param("materialWastageFormNo") String materialWastageFormNo);

    void removeByMaterialWastageFormNos(@Param("materialWastageFormNos") List<String> materialWastageFormNos);
}
