package com.itrjp.im.service.service.impl;

import com.itrjp.im.service.service.NoticeService;
import com.itrjp.im.service.service.OffLineService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 下线
 *
 * @author renjp
 * @date 2022/3/8 22:49
 */
@Service
public class OffLineServiceImpl implements NoticeService, OffLineService {
    @Override
    public boolean match(String type) {
        return Objects.equals(type, OFFLINE);
    }

    @Override
    public void onEvent(byte[] data) {

    }
}
