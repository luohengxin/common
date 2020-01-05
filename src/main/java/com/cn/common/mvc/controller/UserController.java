package com.cn.common.mvc.controller;


import com.cn.common.mvc.http.MethodLog;
import com.cn.common.mvc.pojo.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/user")
public class UserController {

    @RequestMapping("/get")
    public UserDTO getUser(@RequestParam String name){

        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        return userDTO;
    }

    @PostMapping("/create")
    @MethodLog
    public void create(@Validated @RequestBody UserDTO userDTO){

    }

}
