package com.sl.shortLink.controller;

import com.sl.shortLink.common.ResultBuilder;
import com.sl.shortLink.common.ResultModel;
import com.sl.shortLink.dto.params.GenerateShortLinkParams;
import com.sl.shortLink.entity.ShortLink;
import com.sl.shortLink.service.ShortLinkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ShortLink)表控制层
 *
 * @author makejava
 * @since 2022-09-12 16:17:09
 */
@RestController
@RequestMapping("shortLink")
public class ShortLinkController {
    /**
     * 服务对象
     */
    @Resource
    private ShortLinkService shortLinkService;

    /**
     * 生成短链接
     * @author wangzhiyong
     * @date 2022/9/12 下午5:20
     * @param biz 系统标识
     * @param params 请求参数
     * @return com.sl.shortLink.common.ResultModel
     */
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public ResultModel generate(@RequestHeader("biz") String biz, @RequestBody GenerateShortLinkParams params) {
        if (ObjectUtils.isEmpty(biz) || ObjectUtils.isEmpty(params) || CollectionUtils.isEmpty(params.getUrls())) {
            return ResultBuilder.buildPageParamError("the params of request is missing");
        }
        return shortLinkService.generate(biz,params);
    }

}

