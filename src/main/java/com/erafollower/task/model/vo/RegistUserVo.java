package com.erafollower.task.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @describe
 * @auth len
 * @createTime 2019/5/18
 */
@Data
@ApiModel("注册用户信息VO")
public class RegistUserVo {

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("帐号")
    private String account;

    @ApiModelProperty("密码")
    private String  password;

}
