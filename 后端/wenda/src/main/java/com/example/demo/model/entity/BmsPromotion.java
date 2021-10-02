package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data  //自动生成get，set方法
@Builder      //使其构造方法支持链式创建
@Accessors(chain = true)    //使其构造方法支持链式创建与bulider创建方法不同
@TableName("bms_promotion")
@NoArgsConstructor  //生成无参构造方法
@AllArgsConstructor  //生成一个全部参的构造方法
public class BmsPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 内容
     */
    @TableField("title")
    private String title;

    /**
     * 作者
     */
    @TableField("link")
    private String link;

    /**
     * 1：使用，0：过期
     */
    @TableField("description")
    private String description;

}