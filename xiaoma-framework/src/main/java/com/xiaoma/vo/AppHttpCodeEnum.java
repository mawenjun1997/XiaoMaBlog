package com.xiaoma.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AppHttpCodeEnum {


    //success
    SUCCESS(200, "操作成功"),
    //login
    NEED_LOGIN(401, "需要登陆后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    SYSTEM_ERROR(500, "出现错误"),
    USERNAME_EXIST(501, "用户名已经存在"),
    PHONENUMBER_EXIST(502, "手机号已存在"),
    REQUIRE_USERNAME(504, "必须填写用户名"),
    LOGIN_ERROR(505, "用户名或密码错误")
    ;
    private Integer code;
    private String msg;

}
