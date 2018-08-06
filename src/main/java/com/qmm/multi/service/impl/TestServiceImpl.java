package com.qmm.multi.service.impl;

import com.qmm.multi.mapper.hive.HiveMapper;
import com.qmm.multi.mapper.mysql.MysqlMapper;
import com.qmm.multi.mapper.sqlserver.SqlserverMapper;
import com.qmm.multi.model.po.DataType;
import com.qmm.multi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qinmengmei
 * @date 2018/7/12
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    /**
     * 这里的单引号不能少，否则会报错，被识别是一个对象
     */
    private static final String DEMO_CACHE_NAME = "users";
    private static final String CACHE_KEY = "'test'";

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

    /**
     * @Cacheable : Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
     * @CacheEvict : 清除缓存。
     * @CachePut : 也可以声明一个方法支持缓存功能。使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。
     * 这三个方法中都有两个主要的属性：value 指的是 ehcache.xml 中的缓存策略空间；key 指的是缓存的标识，同时可以用 # 来引用参数。
     */
    @Cacheable(value = DEMO_CACHE_NAME, key = CACHE_KEY)
    @Override
    public List<DataType> listDataType() {
        System.out.println("没有使用缓存。");
        return mysqlMapper.listDataType("ssm", "user");
    }

}
