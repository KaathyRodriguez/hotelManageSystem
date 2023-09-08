package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Checkinform;
import com.example.hotelmanagesystem.entity.Materialwastageform;
import com.example.hotelmanagesystem.mapper.CheckinformMapper;
import com.example.hotelmanagesystem.mapper.MaterialwastageformMapper;
import com.example.hotelmanagesystem.service.IMaterialwastageformService;
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
 * @since 2023-06-01
 */
@Service
public class MaterialwastageformServiceImpl extends ServiceImpl<MaterialwastageformMapper, Materialwastageform> implements IMaterialwastageformService {
    @Resource
    private MaterialwastageformMapper materialwastageformMapper;

    @Override
    public Page<Materialwastageform> findPage(Page<Materialwastageform> page, String materialWastageFormNo, String lossGoods, String lossDate) {
        return materialwastageformMapper.findPage(page, materialWastageFormNo, lossGoods, lossDate);
    };
    @Override
    public void removeByMaterialWastageFormNo(String materialWastageFormNo)
    {
        materialwastageformMapper.removeByMaterialWastageFormNo(materialWastageFormNo);
    };

    @Override
    public void removeByMaterialWastageFormNos(List<String> materialWastageFormNos)
    {
        materialwastageformMapper.removeByMaterialWastageFormNos(materialWastageFormNos);
    };
}
