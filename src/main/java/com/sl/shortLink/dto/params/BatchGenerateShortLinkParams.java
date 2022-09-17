package com.sl.shortLink.dto.params;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 短链接生成请求参数
 *
 * @author wangzhiyong
 * @date 2022年09月12日 下午4:39
 */
@Data
public class BatchGenerateShortLinkParams implements Serializable {
    private static final long serialVersionUID = -1631990664484733161L;
    //要转换的原链接url
    private List<String> urls;
}
