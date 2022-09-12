package com.sl.shortLink.enums;

import org.springframework.util.ObjectUtils;

/**
 *
 * @author wangzhiyong
 * @date 2022/9/12 上午10:35
 * @return null
 */
public enum ResultCodeEnum {
    SUCCEED(200,"成功"),
    FAILED(201,"失败"),
    SYSTEM_INSIDE_ERROR(10000,"系统错误"),
    VERSION_TOO_LOWER(10001,"版本过低，请升级新版本"),
    SYSTEM_IS_BUSY(10002,"系统忙，请稍后再试"),
    SYSTEM_IS_UPDATING(10003,"系统正在升级"),
    PERMISSION_DENIED(10004,"权限不足，禁止访问"),
    REQUEST_METHOD_NOT_SUPPORT(10005,"请求方法不被支持"),
    MISSING_REQUEST_PARAMETER(10008,"请求参数不正确"),
    BAD_PARAMETER(10009,"参数错误")
    ;
    private Integer code;
    private String desc;

    ResultCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(Integer code){
        if (ObjectUtils.isEmpty(code)) {
            return "";
        }
        for (ResultCodeEnum mode : values()) {
            if (mode.code.equals(code)) {
                return mode.getDesc();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String str = " ";
        boolean empty = ObjectUtils.isEmpty(str);
        System.out.println("empty = " + empty);
    }
}
