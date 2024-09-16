package com.dyl.mybatis_plus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // 对应数据库uuid、自增id、雪花算法等等
    @TableId(type = IdType.AUTO) // 一旦自己配置，就需要手动设置
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Version// MP的乐观锁注解
    private Integer version;

    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}