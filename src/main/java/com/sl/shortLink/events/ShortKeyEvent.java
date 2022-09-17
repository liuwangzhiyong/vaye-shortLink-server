package com.sl.shortLink.events;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 短链接事件实体类
 *
 * @author wangzhiyong
 * @date 2022年09月13日 下午3:02
 */
@Data
@AllArgsConstructor
public class ShortKeyEvent {
    private Integer count;
}
