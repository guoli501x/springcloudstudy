package cn.guoli.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试网关
 *
 * @author guoli
 * @data 2021-07-17 21:40
 */
@Slf4j
@RestController
public class GatewayTestController {
    @PostMapping("/gateway/test/header/{id}")
    public String gatewayTestHeader(HttpServletRequest request, @PathVariable String id) {
        return "id = " + id + ", header : X-Request-red : " + request.getHeader("X-Request-red")
                + " X-Request-red-id : " + request.getHeader("X-Request-red-id");
    }

    @PostMapping("/gateway/test/parameter")
    public String gatewayTestParameter(@RequestParam String color) {
        return "color = " + color;
    }

    @PostMapping("/gateway/test/fallback")
    public int gatewayTestFallback() {
        log.info("测试fallback");
        return 1 / 0;
    }

    @PostMapping("/gateway/test")
    public String test(@RequestParam String name, @RequestParam String age) {
        return name + age;
    }
}
