package com.dyl.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dyl.mybatis_plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    public UserMapper userMapper;

    @Test
    void contextLoads() {

        userMapper.selectList(new QueryWrapper<>()).forEach(System.out::println);
    }

    @Test
    public void testInsert() {

    }
}
