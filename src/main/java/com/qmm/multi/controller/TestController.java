package com.qmm.multi.controller;

import com.qmm.multi.model.dto.DataResult;
import com.qmm.multi.model.po.DataType;
import com.qmm.multi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by qinmengmei on 2018/7/12.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/list")
    public DataResult<List<String>> list (@RequestParam(required = true) String key, String key1, String key2){
        List<String> list = testService.list();
        List<Map<String, String>> test = testService.test();
        list.add(test.get(1).get(key));
        return DataResult.ok(list);
    }

    @GetMapping(value = "/test1")
    public List<Map<String, String>> test(){
        return testService.test();
    }

    @GetMapping(value = "/listDataType")
    public DataResult<List<DataType>> listDataType(){
        return DataResult.ok(testService.listDataType());
    }

}
