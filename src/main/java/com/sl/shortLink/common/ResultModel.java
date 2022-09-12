package com.sl.shortLink.common;

import com.sl.shortLink.common.basic.AbstractModel;
import lombok.Getter;
import lombok.Setter;

/**
 *  全局接口返回格式
 * @author wangzhiyong
 * @date 2021/12/18 下午4:34
 * @param
 * @return null
 */
@Setter
@Getter
public class ResultModel<T> extends AbstractModel {

    /**
     * code码：200成功
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;


}
