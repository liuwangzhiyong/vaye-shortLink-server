package com.sl.shortLink.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author wangzhiyong
 * @date 2021年12月27日 下午1:57
 */
@Slf4j
public class UrlUtils {

    public static String urlEncode(String url){
        if (StringUtils.isEmpty(url)) {
            return url;
        }
        try {
            String encoded = URLEncoder.encode(url, "UTF-8");
            return encoded.replace("+", "%20").replace("*", "%2A").replace("~", "%7E").replace("/", "%2F");
        } catch (UnsupportedEncodingException e) {
            log.error("url编码异常");
            e.printStackTrace();
            return "";
        }
    }

    public static String urlDecode(String url) {
        if (StringUtils.isEmpty(url)) {
            return url;
        } else {
            try {
                return URLDecoder.decode(url, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error("url解码异常");
                e.printStackTrace();
                return url;
            }
        }
    }

}
