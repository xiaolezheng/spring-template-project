package com.xxx.xtable.rest.controller;

import com.xxx.xtable.api.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/xtable/health")
public class HealthController extends BaseController {
    @GetMapping
    public Object get() {
        return Result.succ();
    }

    @PostMapping
    public Object post(@RequestBody Map map) {
        return Result.succ(map);
    }
}
