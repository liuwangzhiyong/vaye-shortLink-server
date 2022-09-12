package com.sl.shortLink.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ShortLink)实体类
 *
 * @author makejava
 * @since 2022-09-12 16:17:10
 */
@Data
public class ShortLink implements Serializable {
    private static final long serialVersionUID = -65670311063530214L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 短链接字符串
     */
    private String shortKey;
    /**
     * 原长链接
     */
    private String originalUrl;
    /**
     * 系统标识
     */
    private String biz;
    /**
     * 创建时间
     */
    private Date gmtCreated;
    /**
     * 修改时间
     */
    private Date gmtModified;

}

