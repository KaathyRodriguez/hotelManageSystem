package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Cateringorderform;
import com.example.hotelmanagesystem.entity.Supplyform;
import com.example.hotelmanagesystem.mapper.CateringorderformMapper;
import com.example.hotelmanagesystem.mapper.SupplyformMapper;
import com.example.hotelmanagesystem.service.ISupplyformService;
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
public class SupplyformServiceImpl extends ServiceImpl<SupplyformMapper, Supplyform> implements ISupplyformService {

    @Resource
    private SupplyformMapper supplyformMapper;

    @Override
    public Page<Supplyform> findPage(Page<Supplyform> page, String supplyFormNo, String purchaseDate, String purchaseManager) {
        return supplyformMapper.findPage(page, supplyFormNo, purchaseDate, purchaseManager);
    };
    @Override
    public void removeBySupplyFormNo(String supplyFormNo)
    {
        supplyformMapper.removeBySupplyFormNo(supplyFormNo);
    };

    @Override
    public void removeBySupplyFormNos(List<String> supplyFormNos)
    {
        supplyformMapper.removeBySupplyFormNos(supplyFormNos);
    };

}
