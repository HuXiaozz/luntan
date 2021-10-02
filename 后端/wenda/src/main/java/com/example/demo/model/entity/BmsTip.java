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
public class BmsTip implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 作者
     */
    @TableField("author")
    private String author;

    /**
     * 1：使用，0：过期
     */
    @TableField("type")
    private boolean type;

}