package com.itrjp.im.service.service.impl;

import com.itrjp.im.service.service.NoticeService;
import com.itrjp.im.service.service.OnLineService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 上线
 *
 * @author renjp
 * @date 2022/3/8 22:41
 */
@Service
public class OnLineServiceImpl implements NoticeService, OnLineService {

    @Override
    public boolean match(String type) {
        return Objects.equals(type, ONLINE);
    }

    @Override
    public void onEvent(byte[] data) {

    }
}
