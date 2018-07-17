package com.qmm.multi.service.impl;

import com.qmm.multi.mapper.hive.HiveMapper;
import com.qmm.multi.mapper.mysql.MysqlMapper;
import com.qmm.multi.mapper.sqlserver.SqlserverMapper;
import com.qmm.multi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qinmengmei on 2018/7/12.
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private MysqlMapper mysqlMapper;

    @Autowired
    private SqlserverMapper sqlserverMapper;
    
    @Autowired
    private HiveMapper hiveMapper;

    @Override
    public List<String> list() {
        List<String> list = new ArrayList<>();
        List<String> list1 = mysqlMapper.listColumns("dap", "import_source");
        List<String> list2 = sqlserverMapper.listColumns("YG_RPT.dbo.test_user_temp");
        list.addAll(list1);
        list.addAll(list2);
        return list;
    }

    @Override
    public List<Map<String, String>> test() {
        List<Map<String, String>> createTable = hiveMapper.getCreateTable("default.test_table");
        return createTable;
    }

}
