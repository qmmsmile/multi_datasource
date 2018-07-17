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
@MapperScan(basePackages = SqlserverDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "sqlserverSqlSessionFactory")
public class SqlserverDataSourceConfig {

    // 精确到 sqlserver 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.qmm.multi.mapper.sqlserver";
    static final String MAPPER_LOCATION = "classpath:mapper/sqlserver/*.xml";

    @Value("${sqlserver.datasource.url}")
    private String url;

    @Value("${sqlserver.datasource.username}")
    private String user;

    @Value("${sqlserver.datasource.password}")
    private String password;

    @Value("${sqlserver.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "sqlserverDataSource")
    public DataSource sqlserverDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "sqlserverTransactionManager")
    public DataSourceTransactionManager sqlserverTransactionManager() {
        return new DataSourceTransactionManager(sqlserverDataSource());
    }

    @Bean(name = "sqlserverSqlSessionFactory")
    public SqlSessionFactory sqlserverSqlSessionFactory(@Qualifier("sqlserverDataSource") DataSource sqlserverDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(sqlserverDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(SqlserverDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}