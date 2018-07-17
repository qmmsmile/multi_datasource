package com.qmm.multi.mapper.mysql;

import com.qmm.multi.model.po.DataType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by qinmengmei on 2018/7/6.
 */
@Mapper
public interface MysqlMapper {

    /**
     * 获取所有列名
     * @param db
     * @param tableName
     * @return
     */
    List<String> listColumns(@Param("db") String db, @Param("tableName") String tableName);

    /**
     * 获取字段类型
     * @param tableName
     * @return
     */
    List<DataType> listDataType(@Param("db") String db, @Param("tableName") String tableName);

    /**
     * 批量插入数据
     * @param excelData
     */
    void insertByBatch(@Param("list") List<List<String>> excelData, @Param("columns") List<String> columns,
                       @Param("tableName") String tableName, @Param("db") String db);

    /**
     * 删除表中所有数据
     * @param db
     * @param tableName
     */
    void deleteAll(@Param("db") String db, @Param("tableName") String tableName);
}
