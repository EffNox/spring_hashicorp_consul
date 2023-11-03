package nr.springhashicorpconsul.controllers;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ConsulController {

    DiscoveryClient    discoveryClient;
    LoadBalancerClient loadBalancer;

    @GetMapping
    ResponseEntity<?> getMethodName(HttpServletRequest request) {
        var sb = new StringBuilder();

        sb.append("<h1>Hello World</h1>");

        /* Service Discovery */
        if (loadBalancer.choose("MS-CONSUL") != null)
            sb.append("<p>load balancer: " + loadBalancer.choose("MS-CONSUL").getInstanceId() + "</p>");
        if (discoveryClient.getInstances("MS-CONSUL") != null)
            sb.append("<p>instances: " + discoveryClient.getInstances("MS-CONSUL").size() + "</p>");
        // System.out.println("sb: " + sb);
        return ResponseEntity.ok(sb.toString());
    }

}
