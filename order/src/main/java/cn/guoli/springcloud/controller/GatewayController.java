package cn.guoli.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 通过网关调用payment
 *
 * @author guoli
 * @data 2021-09-11 15:54
 */
@Slf4j
@RestController
public class GatewayController {
    @PostMapping("/gateway/test")
    public String gatewayTest(@RequestParam String name, @RequestParam String age) {
        log.info("/gateway/test 接口入参：{}, {}", name, age);
        String resultString = "";
        HttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder("http://127.0.0.1:9527/test?name=" + name);
            // 设置请求参数
            builder.setParameter("age", age);
            builder.setParameter("id", "1");
            URI uri = builder.build();
            HttpPost httpPost = new HttpPost(uri);
            // 设置消息头
            httpPost.setHeader("Cookie", "username=zhangsan");
            httpPost.setHeader("X-Request-Id", "123456");
            httpPost.setHeader("Host", "www.somehost.com");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            } else if (httpResponse.getStatusLine().getStatusCode() == 404) {
                resultString = "404";
            } else {
                resultString = "500";
            }
        } catch (URISyntaxException | IOException | ParseException e) {
            e.printStackTrace();
        }
        log.info("/gateway/test 接口出参：{}", resultString);
        return resultString;
    }
}
