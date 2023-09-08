package com.example.hotelmanagesystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.common.Constants;
import com.example.hotelmanagesystem.controller.dto.UserDTO;
import com.example.hotelmanagesystem.entity.Supplier;
import com.example.hotelmanagesystem.entity.User;
import com.example.hotelmanagesystem.exception.ServiceException;
import com.example.hotelmanagesystem.mapper.SupplierMapper;
import com.example.hotelmanagesystem.mapper.UserMapper;
import com.example.hotelmanagesystem.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotelmanagesystem.utils.TokenUtils;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    private static final Log LOG = Log.get();
    @Resource
    private UserMapper userMapper;


    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            //设置token
            String token = TokenUtils.getToken(one.getId().toString(), one.getPassword());
            userDTO.setPassword(SecureUtil.md5(userDTO.getPassword()));
            userDTO.setToken(token);
            return userDTO;

        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }


    @Override
    public Page<User> findPage(Page<User> page, String id, String username, String role) {
        return userMapper.findPage(page, id, username, role);
    };

    @Override
    public  User getById(String id)
    {
        return userMapper.getById(id);
    };
    @Override
    public void removeById(String id)
    {
        userMapper.removeById(id);
    };

    @Override
    public void removeByIds(List<String> ids)
    {
        userMapper.removeByIds(ids);
    };

}
