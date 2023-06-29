package com.onging.dynamic.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * 也可以采用这种方式，在每个方法上注解@DS指明数据源
 *
 */
@Mapper
public interface Test2Mapper extends BaseMapper<Map<String,Object>> {

    /**
     * 测试的查询方法
     *
     * @return {@link java.util.List}<{@link java.util.Map}<{@link String},{@link String}>>
     */
    @DS("proxyds1")
    @Select("SELECT COUNT(1) FROM test_table")
    int countAllFromProxy1();

    /**
     * 测试的查询方法
     *
     * @return {@link java.util.List}<{@link java.util.Map}<{@link String},{@link String}>>
     */
    @DS("proxyds2")
    @Select("SELECT COUNT(1) FROM test_table")
    int countAllFromProxy2();
}
