package com.dyl.mybatis_plus.service.impl;

import com.dyl.mybatis_plus.entity.User;
import com.dyl.mybatis_plus.mapper.UserMapper;
import com.dyl.mybatis_plus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dyl
 * @since 2024-09-17 08:21:12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
