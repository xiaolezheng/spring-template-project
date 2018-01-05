package com.xxx.xtable.rest.controller;

import com.xxx.xtable.api.Result;
import com.xxx.xtable.api.model.XTable;
import com.xxx.xtable.api.support.ModuleType;
import com.xxx.xtable.service.XTableService;
import com.xxx.xtable.service.support.ContextHolder;
import com.xxx.xtable.service.support.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(value = "/xtable/meta")
public class XTableController extends BaseController {
    @Autowired
    XTableService xTableService;

    @PostMapping("/create")
    public Object create(@RequestBody XTable xTable) {
        xTableService.create(xTable);
        return Result.succ(xTable);
    }

    @PutMapping("/update")
    public Object update(@RequestBody XTable xTable) {
        xTableService.update(xTable);
        return Result.succ(xTable);
    }

    @PutMapping("/delete/{xTableId}/{segmentId}")
    public Object delete(@PathVariable("xTableId") String xTableId, @PathVariable("segmentId") String segmentId) {
        Principal principal = ContextHolder.getData(Principal.class);
        XTable xTable = xTableService.delete(principal.getTenantId(), xTableId, segmentId);
        return Result.succ(xTable);
    }

    @GetMapping("/find/{moduleType}")
    public Object find(@PathVariable("moduleType") Integer moduleType) {
        Principal principal = ContextHolder.getData(Principal.class);
        XTable xTable = xTableService.find(principal.getTenantId(), ModuleType.valueOf(moduleType));
        return Result.succ(xTable);
    }

    @GetMapping("/find/id/{xTableId}")
    public Object find(@PathVariable("xTableId") String xTableId) {
        Principal principal = ContextHolder.getData(Principal.class);
        XTable xTable = xTableService.find(principal.getTenantId(), xTableId);
        return Result.succ(xTable);
    }
}
