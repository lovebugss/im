package com.itrjp.im.api.controller;

import com.itrjp.im.api.enums.TypeEnum;
import com.itrjp.im.api.param.BaseAPIParam;
import com.itrjp.im.api.param.BaseParam;
import com.itrjp.im.api.service.AuthService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("{type}/channel")
public class ChannelController extends BaseController {


    public ChannelController(ObjectProvider<AuthService<? extends BaseParam>> authService) {
        super(authService);
    }


    @PostMapping("create")
    public ResponseEntity<String> create(@PathVariable("type") TypeEnum type, BaseAPIParam param) {

        // 鉴权
        auth(type, param);

        return ResponseEntity.ok("ok");
    }

}
