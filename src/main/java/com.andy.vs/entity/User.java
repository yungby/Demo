package com.andy.vs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("d_user")
@ApiModel(value = "用户对象")
public class User implements Serializable {
    @TableId
    @ApiModelProperty("用户Id")
    public String userId;
    @TableField
    @ApiModelProperty("用户名字")
    public String userName;
    @TableField
    @ApiModelProperty("用户年龄")
    public String userAge;
}
