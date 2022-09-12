package com.sl.shortLink.controller;

import com.sl.shortLink.common.ResultModel;
import com.sl.shortLink.common.ResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *  健康检查
 * @author wangzhiyong
 * @date 2021/12/18 下午4:32
 * @param
 * @return null
 */
@RestController
@Slf4j
public class HealthCheckController {
    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    public ResultModel execute() {
        return ResultBuilder.buildSuccess("vaye-shortLink-server");
    }
}
