package com.itrjp.im.service.service.impl;

import com.itrjp.im.service.entity.Message;
import com.itrjp.im.service.mapper.MessageMapper;
import com.itrjp.im.service.service.IMessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
