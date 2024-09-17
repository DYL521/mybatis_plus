package com.dyl.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyl.mybatis_plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    @Test
    public void testQuery() {
        User user = userMapper.selectById(1L);
        System.out.println(user);

        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    @Test
    public void testQueryMap() {
        HashMap<String, Object> map = new HashMap<>();
//        map.put("id",1);
        map.put("name", "Sandy");
        // 自定义查询
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }

    @Test
    public void testPage() {
// 参数一：当前页
// 参数二：页面大小
// 使用了分页插件之后，所有的分页操作也变得简单的！
        Page<User> page = new Page<>(2, 5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }

    @Test
    public void testDelete(){
        userMapper.deleteById(1L);
    }
}
