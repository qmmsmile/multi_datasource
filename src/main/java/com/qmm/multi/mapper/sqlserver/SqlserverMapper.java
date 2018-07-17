package com.qmm.multi.mapper.sqlserver;

import com.qmm.multi.model.po.DataType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by qinmengmei on 2018/7/6.
 */
@Mapper
public interface SqlserverMapper {
    /**
     * 获取所有列名
     * @param tableName
     * @return
     */
    List<String> listColumns(@Param("tableName") String tableName);
    /**
     * 获取字段类型
     * @param tableName
     * @return
     */
    List<DataType> listDataType(@Param("tableName") String tableName);
    /**
     * 批量插入数据
     * @param excelData
     */
    void insertByBatch(@Param("list") List<List<String>> excelData, @Param("columns") List<String> columns,
                       @Param("tableName") String tableName, @Param("db") String db);

    /**
     * 删除表中所有数据
     * @param tableName
     */
    void deleteAll(@Param("tableName") String tableName);
}
