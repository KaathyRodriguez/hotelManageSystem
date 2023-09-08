package com.example.hotelmanagesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.entity.Cateringorderform;
import com.example.hotelmanagesystem.entity.Checkinform;
import com.example.hotelmanagesystem.mapper.CateringorderformMapper;
import com.example.hotelmanagesystem.mapper.CheckinformMapper;
import com.example.hotelmanagesystem.service.ICheckinformService;
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
public class CheckinformServiceImpl extends ServiceImpl<CheckinformMapper, Checkinform> implements ICheckinformService {
    @Resource
    private CheckinformMapper checkinformMapper;

    @Override
    public Page<Checkinform> findPage(Page<Checkinform> page, String checkInFormNo, String memberNo, String state) {
        return checkinformMapper.findPage(page, checkInFormNo, memberNo, state);
    };
    @Override
    public void removeByCheckInFormNo(String checkInFormNo)
    {
        checkinformMapper.removeByCheckInFormNo(checkInFormNo);
    };

    @Override
    public void removeByCheckInFormNos(List<String> checkInFormNos)
    {
        checkinformMapper.removeByCheckInFormNos(checkInFormNos);
    };

}
