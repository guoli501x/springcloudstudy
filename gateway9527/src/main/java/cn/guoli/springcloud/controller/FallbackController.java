package cn.guoli.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 回退
 *
 * @author guoli
 * @data 2021-07-19 10:05
 */
@RestController
public class FallbackController {
    @RequestMapping("/fallback")
    public String fallback() {
        return "请稍后再试";
    }
}
