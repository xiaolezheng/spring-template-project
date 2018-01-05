package com.xxx.xtable.rest.controller;

import com.google.common.collect.Lists;
import com.xxx.xtable.api.Result;
import com.xxx.xtable.api.model.XTableData;
import com.xxx.xtable.api.support.ModuleType;
import com.xxx.xtable.service.XTableDataService;
import com.xxx.xtable.service.support.ContextHolder;
import com.xxx.xtable.service.support.Principal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/xtable/data")
public class XTableDataController extends BaseController {
    @Autowired
    XTableDataService xTableDataService;

    @PostMapping("/save")
    public Object create(@RequestBody XTableData xTableData) {
        XTableData data = xTableDataService.save(xTableData);

        return Result.succ(data);
    }

    @PutMapping("/delete/{id}")
    public Object delete(@PathVariable("id") String id) {
        Principal principal = ContextHolder.getData(Principal.class);
        XTableData data = xTableDataService.delete(principal.getTenantId(), id);

        return Result.succ(data);
    }

    @GetMapping("/find/moduleType/{moduleType}/{relateId}")
    public Object find(@PathVariable("moduleType") Integer moduleType, @PathVariable("relateId") Long relateId) {
        Principal principal = ContextHolder.getData(Principal.class);
        List<XTableData> dataList = xTableDataService.find(principal.getTenantId(), ModuleType.valueOf(moduleType), Lists.newArrayList(relateId));
        if (CollectionUtils.isNotEmpty(dataList) && dataList.size() == 1) {
            return Result.succ(dataList.get(0));
        }

        return Result.succ("not found data,please check request param!");
    }

    @GetMapping("/find/xTableId/{xTableId}/{relateId}")
    public Object find(@PathVariable("xTableId") String xTableId, @PathVariable("relateId") Long relateId) {
        Principal principal = ContextHolder.getData(Principal.class);
        List<XTableData> dataList = xTableDataService.find(principal.getTenantId(), xTableId, Lists.newArrayList(relateId));
        if (CollectionUtils.isNotEmpty(dataList) && dataList.size() == 1) {
            return Result.succ(dataList.get(0));
        }

        return Result.succ("not found data,please check request param!");
    }

    @GetMapping("/find/xTableId/{xTableId}")
    public Object find(@PathVariable("xTableId") String xTableId) {
        Principal principal = ContextHolder.getData(Principal.class);
        List<XTableData> dataList = xTableDataService.find(principal.getTenantId(), xTableId);
        if (CollectionUtils.isNotEmpty(dataList)) {
            return Result.succ(dataList);
        }

        return Result.succ("not found data,please check request param!");
    }
}
