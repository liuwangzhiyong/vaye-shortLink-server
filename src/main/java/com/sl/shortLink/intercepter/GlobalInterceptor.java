package com.sl.shortLink.intercepter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.shortLink.common.ResultBuilder;
import com.sl.shortLink.common.ResultModel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author wangzhiyong
 * @date 2022/3/9 下午4:42
 * @param
 * @return null
 */
@Slf4j
@Setter
public class GlobalInterceptor implements HandlerInterceptor {

    private ObjectMapper objectMapper;

    public GlobalInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();
        log.info("url={}",url);
        return true;
    }

    protected void noAuth(HttpServletResponse response) throws IOException {
        response.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.OK.value());
        ResultModel resultModel = ResultBuilder.buildNoAuth();
        String json = objectMapper.writeValueAsString(resultModel);
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.close();
        writer.close();
    }
}
