package com.sl.shortLink.common.basic;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 *  抽象模型基础类
 * @author wangzhiyong
 * @date 2021/12/18 下午4:35
 * @param
 * @return null
 */
public abstract class AbstractModel implements Serializable {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
