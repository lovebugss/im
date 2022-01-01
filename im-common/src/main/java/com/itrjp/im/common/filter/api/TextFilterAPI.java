package com.itrjp.im.common.filter.api;


import com.itrjp.im.common.filter.dto.TextFilterDto;
import com.itrjp.im.common.filter.param.TextFilterParam;
import com.itrjp.im.common.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.itrjp.im.common.conts.URLConstant.FILTER_API;

@RequestMapping(FILTER_API)
public interface TextFilterAPI {

    @RequestMapping(value = "text", method = {RequestMethod.GET, RequestMethod.POST})
    Result<TextFilterDto> filter(TextFilterParam param);
}
