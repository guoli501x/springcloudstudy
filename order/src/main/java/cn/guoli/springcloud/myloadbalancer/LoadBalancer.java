package cn.guoli.springcloud.myloadbalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 负载均衡算法
 *
 * @author guoli
 * @Date 2020/6/26 22:58
 */
public interface LoadBalancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
