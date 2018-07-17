package com.qmm.multi.mapper.hive;

import com.qmm.multi.model.po.DataType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by qinmengmei on 2018/7/10.
 */
@Mapper
public interface HiveMapper {

    /**
     * test
     * @return
     */
    Integer count();

    /**
     * 获取表详细信息
     * @param tableName
     * @return
     */
    List<DataType> listDesc(@Param("tableName") String tableName);

    /**
     * 获取分区
     * @param tableName
     * @return
     */
    List<String> listPartitions(@Param("tableName") String tableName);

    /**
     * 获取建表信息
     * @param tableName
     * @return
     */
    List<Map<String, String>> getCreateTable(@Param("tableName") String tableName);

    /**
     * 执行分区
     * @param tableName
     */
    void executePartition(@Param("tableName") String tableName);
}
