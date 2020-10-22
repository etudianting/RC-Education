package com.pfe.zuulserver.proxies;

import com.pfe.zuulserver.beans.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "microservice-users")
//@RibbonClient(name = "microservice-users")
public interface UsersProxy {

    @GetMapping("/users/findByUsername/{username}")
    UserBean findByUsername(@Valid @PathVariable String username);

    @GetMapping("/users/findByUsernameOrEmail/{usernameOrEmail}")
    UserBean findByUsernameOrEmail(@Valid @PathVariable String usernameOrEmail);

}