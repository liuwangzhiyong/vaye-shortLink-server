package com.sl.shortLink.service;

import com.sl.shortLink.common.ResultModel;
import com.sl.shortLink.dto.params.GenerateShortLinkParams;
import com.sl.shortLink.entity.ShortLink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (ShortLink)表服务接口
 *
 * @author makejava
 * @since 2022-09-12 16:17:10
 */
public interface ShortLinkService {

    ResultModel generate(String biz, GenerateShortLinkParams params);
}
