package com.example.demo.model.dto;



import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.model.entity.BmsPost;
import com.example.demo.model.entity.BmsTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor  //生成无参构造方法
@AllArgsConstructor  //生成一个全部参的构造方法
public class UpDataPoat implements Serializable {

    private BmsPost bmsPost;

    private BmsTag bmsTag;

}

