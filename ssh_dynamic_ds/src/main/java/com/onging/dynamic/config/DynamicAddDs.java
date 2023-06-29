package com.onging.dynamic.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.HikariDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
public class DynamicAddDs implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private HikariDataSourceCreator hikariDataSourceCreator;


    void addProxyDs1() {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setUrl("jdbc:mysql://127.0.0.1:" + SSHConfiguration.PROXY_PORT + "/db1?characterEncoding=UTF-8&useServerPrepStmts=false&rewriteBatchedStatements=true&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai");
        dataSourceProperty.setUsername("xxxxx");
        dataSourceProperty.setPassword("xxxxxxx");
        dataSourceProperty.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceProperty.setLazy(true);
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource dataSource = hikariDataSourceCreator.createDataSource(dataSourceProperty);
        log.info("设置proxyds1数据源=" + dataSourceProperty.getUrl());
        ds.addDataSource("proxyds1", dataSource);
    }

    void addProxyDs2() {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setUrl("jdbc:mysql://127.0.0.1:" + SSHConfiguration.PROXY_PORT + "/db2?characterEncoding=UTF-8&useServerPrepStmts=false&rewriteBatchedStatements=true&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai");
        dataSourceProperty.setUsername("xxxxxx");
        dataSourceProperty.setPassword("xxxxxxxx");
        dataSourceProperty.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceProperty.setLazy(true);
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource dataSource = hikariDataSourceCreator.createDataSource(dataSourceProperty);
        log.info("设置proxyds2数据源=" + dataSourceProperty.getUrl());
        ds.addDataSource("proxyds2", dataSource);
    }

    @Override
    public void run(String... args) throws Exception {
        addProxyDs1();
        addProxyDs2();
    }
}
