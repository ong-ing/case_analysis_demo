package com.onging.dynamic.service;

import com.onging.dynamic.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;


    void test(){
        testMapper.countAll();
    }
}
