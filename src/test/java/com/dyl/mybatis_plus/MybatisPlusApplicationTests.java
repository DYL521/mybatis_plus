package com.dyl.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dyl.mybatis_plus.mapper.UserMapper;
import com.dyl.mybatis_plus.pojo.User;
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
        User user = new User();
        user.setEmail("xxxx@qq.com");
        user.setName("xxx");
        user.setAge(18);
        userMapper.insert(user);
    }

    // 测试乐观锁success
    @Test
    public void testUpdate() {
        // 1、查询用户信息
        User user = userMapper.selectById(1L);

        // 2、更新用户信息
        user.setName("yyyy");
        user.setEmail("yyyy@qq.com");

        //3、执行更新操作
        userMapper.updateById(user);
    }

    // 测试乐观锁fail
    @Test
    public void testFail() {
        // 1、线程1查询用户信息
        User user = userMapper.selectById(1L);
        user.setName("yyyy");
        user.setEmail("yyyy@qq.com");

        // 线程2：模拟线程2进行了插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("w'w'w'w");
        user2.setEmail("wwww@qq.com");
        userMapper.updateById(user2);

        // 可以使用自旋锁，尝试多次提交
        userMapper.updateById(user);// 如果没有乐观锁，那么就会覆盖 插队线程的值
    }
}
