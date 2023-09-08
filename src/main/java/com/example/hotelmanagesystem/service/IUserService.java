package com.example.hotelmanagesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotelmanagesystem.controller.dto.UserDTO;
import com.example.hotelmanagesystem.entity.Supplier;
import com.example.hotelmanagesystem.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kevin
 * @since 2023-05-30
 */
public interface IUserService extends IService<User> {

    Page<User> findPage(Page<User> page, String id, String username, String role);

    User getById(String id);

    void removeById(String id);

    void removeByIds(List<String> ids);

    UserDTO login(UserDTO userDTO);
}
