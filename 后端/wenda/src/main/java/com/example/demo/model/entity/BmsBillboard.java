package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data  //自动生成get，set方法
@Builder      //使其构造方法支持链式创建
@Accessors(chain = true)    //使其构造方法支持链式创建与bulider创建方法不同
@TableName("bms_billboard")
@NoArgsConstructor  //生成无参构造方法
@AllArgsConstructor  //生成一个全部参的构造方法
public class BmsBillboard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    //表的id，设置为自增长
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 公告牌
     */
    @TableField("content")
    private String content;

    /**
     * 公告时间
     */
    //对应字段，自动填充
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 1：展示中，0：过期
     */
    @Builder.Default   //牧人构建
    @TableField("`show`")
    private boolean show = false;  //默认为false

}