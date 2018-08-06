package com.qmm.multi.service;

import com.qmm.multi.model.po.DataType;

import java.util.List;
import java.util.Map;

/**
 * Created by qinmengmei on 2018/7/12.
 */
public interface TestService {

    List<String> list();

    List<Map<String, String>> test();

    List<DataType> listDataType();
}
