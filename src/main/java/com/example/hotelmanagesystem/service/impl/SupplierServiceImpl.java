package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Room;
import com.example.hotelmanagesystem.entity.Supplier;
import com.example.hotelmanagesystem.mapper.RoomMapper;
import com.example.hotelmanagesystem.mapper.SupplierMapper;
import com.example.hotelmanagesystem.service.ISupplierService;
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
 * @since 2023-05-30
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {
    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public Page<Supplier> findPage(Page<Supplier> page, String supplierNo, String supplierName, String contactPersonName) {
        return supplierMapper.findPage(page, supplierNo, supplierName, contactPersonName);
    };
    @Override
    public void removeBySupplierNo(String supplierNo)
    {
        supplierMapper.removeBySupplierNo(supplierNo);
    };

    @Override
    public void removeBySupplierNos(List<String> supplierNos)
    {
        supplierMapper.removeBySupplierNos(supplierNos);
    };
}
