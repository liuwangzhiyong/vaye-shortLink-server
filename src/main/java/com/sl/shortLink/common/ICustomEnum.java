package com.sl.shortLink.common;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.sl.shortLink.common.basic.IBaseEnum;

import java.io.Serializable;

/**
 *
 * @author wangzhiyong
 * @date 2021/12/20 下午3:25
 * @param
 * @return null
 */
public interface ICustomEnum<T extends Serializable> extends IEnum, IBaseEnum<T> {

}
