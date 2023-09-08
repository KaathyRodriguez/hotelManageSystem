package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Cateringorderform;
import com.example.hotelmanagesystem.entity.Roomorderform;
import com.example.hotelmanagesystem.mapper.CateringorderformMapper;
import com.example.hotelmanagesystem.mapper.RoomorderformMapper;
import com.example.hotelmanagesystem.service.ICateringorderformService;
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
public class CateringorderformServiceImpl extends ServiceImpl<CateringorderformMapper, Cateringorderform> implements ICateringorderformService {

    @Resource
    private CateringorderformMapper cateringorderformMapper;

    @Override
    public Page<Cateringorderform> findPage(Page<Cateringorderform> page, String orderNo, String purchaseDate, String orderType) {
        return cateringorderformMapper.findPage(page, orderNo, purchaseDate, orderType);
    };
    @Override
    public void removeByOrderNo(String orderNo)
    {
        cateringorderformMapper.removeByOrderNo(orderNo);
    };

    @Override
    public void removeByOrderNos(List<String> orderNos)
    {
        cateringorderformMapper.removeByOrderNos(orderNos);
    };

}
