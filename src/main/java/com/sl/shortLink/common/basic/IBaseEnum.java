package com.sl.shortLink.common.basic;


import java.io.Serializable;

/**
 *
 * @author wangzhiyong
 * @date 2021/12/20 下午3:19
 * @param
 * @return null
 */
public interface IBaseEnum<T extends Serializable> {
    T getValue();

    String getName();
}