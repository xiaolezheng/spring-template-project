package com.xxx.xtable.rest.controller;

import com.xxx.xtable.api.Result;
import com.xxx.xtable.service.support.ContextHolder;
import com.xxx.xtable.service.support.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/xtable/debug")
public class DebugController extends BaseController {

    @PostMapping
    public Object post(@RequestBody Map map) {
        Principal principal = ContextHolder.getData(Principal.class);
        map.put("principal", principal);
        return Result.succ(map);
    }

    @GetMapping
    public Object get() {
        Principal principal = ContextHolder.getData(Principal.class);
        return Result.succ(principal);
    }
}
