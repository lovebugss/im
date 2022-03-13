package com.itrjp.im.service.service.impl;

import com.itrjp.im.service.entity.User;
import com.itrjp.im.service.mapper.UserMapper;
import com.itrjp.im.service.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author renjp
 * @since 2022-03-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
