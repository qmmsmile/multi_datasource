package com.qmm.multi.config.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = HiveDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "hiveSqlSessionFactory")
public class HiveDataSourceConfig {

    // 精确到 hive 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.qmm.multi.mapper.hive";
    static final String MAPPER_LOCATION = "classpath:mapper/hive/*.xml";

    @Value("${hive.datasource.url}")
    private String url;

    @Value("${hive.datasource.username}")
    private String user;

    @Value("${hive.datasource.password}")
    private String password;

    @Value("${hive.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "hiveDataSource")
    public DataSource hiveDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "hiveTransactionManager")
    public DataSourceTransactionManager hiveTransactionManager() {
        return new DataSourceTransactionManager(hiveDataSource());
    }

    @Bean(name = "hiveSqlSessionFactory")
    public SqlSessionFactory hiveSqlSessionFactory(@Qualifier("hiveDataSource") DataSource hiveDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(hiveDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(HiveDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}