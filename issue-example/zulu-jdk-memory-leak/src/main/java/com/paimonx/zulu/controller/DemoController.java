package com.paimonx.zulu.controller;

import com.paimonx.zulu.util.AesUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author xu
 * @date 2022/8/24
 */
@RestController
public class DemoController {

    /**
     * 一个简单的请求
     * 只是将字符串加密下
     * @return aes 加密后的内容
     */
    @GetMapping("demo")
    public String demo() {
        String key = "liuzhaoxu";
        return AesUtils.encrypt(UUID.randomUUID().toString(), key);
    }




}
