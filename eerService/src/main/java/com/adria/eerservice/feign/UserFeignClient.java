package com.adria.eerservice.feign;


import com.adria.eerservice.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserFeignClient {

    @GetMapping("/users/{id}") // Endpoint in the User microservice
    User getUserById(@PathVariable("id") Long userId);
}
