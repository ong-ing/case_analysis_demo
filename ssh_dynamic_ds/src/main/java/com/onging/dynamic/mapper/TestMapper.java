package com.onging.dynamic.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * 注解DS，表明这个mapper下所有的查询都使用proxyds1这个数据源
 */
@DS("proxyds1")
@Mapper
public interface TestMapper extends BaseMapper<Map<String, Object>> {


    /**
     * 测试的查询方法
     *
     * @return {@link java.util.List}<{@link java.util.Map}<{@link String},{@link String}>>
     */
    @Select("SELECT COUNT(1) FROM test_table")
    int countAll();

}
