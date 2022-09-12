package com.sl.shortLink.service.impl;

import com.sl.shortLink.common.ResultBuilder;
import com.sl.shortLink.common.ResultModel;
import com.sl.shortLink.dao.ShortLinkDao;
import com.sl.shortLink.dto.params.GenerateShortLinkParams;
import com.sl.shortLink.entity.ShortLink;
import com.sl.shortLink.service.ShortLinkService;
import com.sl.shortLink.utils.BaseUtils;
import com.sl.shortLink.utils.SnowflakeIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (ShortLink)表服务实现类
 *
 * @author makejava
 * @since 2022-09-12 16:17:10
 */
@Service("shortLinkService")
public class ShortLinkServiceImpl implements ShortLinkService {

    @Resource
    private ShortLinkDao shortLinkDao;

    @Override
    public ResultModel generate(String biz, GenerateShortLinkParams params) {
        long id = SnowflakeIdUtil.nextId();
        String shortKey = BaseUtils.getShortKey(id);
        ShortLink shortLink = new ShortLink();
        shortLink.setId(id);
        shortLink.setShortKey(shortKey);
        shortLink.setOriginalUrl(params.getUrls().get(0));
        shortLink.setBiz(biz);
        shortLink.setGmtCreated(new Date());
        shortLinkDao.insert(shortLink);
        //todo shortKey待完善
        return ResultBuilder.buildSuccessData(shortKey);
    }
}
