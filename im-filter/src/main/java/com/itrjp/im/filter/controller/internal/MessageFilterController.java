package com.itrjp.im.filter.controller.internal;

import com.itrjp.im.common.filter.api.TextFilterAPI;
import com.itrjp.im.common.filter.dto.TextFilterDto;
import com.itrjp.im.common.filter.param.TextFilterParam;
import com.itrjp.im.common.result.Result;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文本过滤器
 */
@RestController
public class MessageFilterController implements TextFilterAPI {


    @Override
    public Result<TextFilterDto> filter(TextFilterParam param) {
        TextFilterDto result = new TextFilterDto();
        return Result.success(result);
    }
}
