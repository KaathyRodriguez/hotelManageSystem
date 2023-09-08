package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Department;
import com.example.hotelmanagesystem.entity.Goods;
import com.example.hotelmanagesystem.mapper.DepartmentMapper;
import com.example.hotelmanagesystem.mapper.GoodsMapper;
import com.example.hotelmanagesystem.service.IGoodsService;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Page<Goods> findPage(Page<Goods> page, String goodsNo, String goodsName, String goodsType) {
        return goodsMapper.findPage(page, goodsNo, goodsName, goodsType);
    };
    @Override
    public void removeByGoodsNo(String goodsNo)
    {
        goodsMapper.removeByGoodsNo(goodsNo);
    };

    @Override
    public void removeByGoodsNos(List<String> goodsNos)
    {
        goodsMapper.removeByGoodsNos(goodsNos);
    };

}
