package com.sl.shortLink.utils;

import com.sl.shortLink.constants.SlConstants;
import org.hashids.Hashids;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 短链接服务工具类
 *
 * @author wangzhiyong
 * @date 2022年09月12日 下午5:09
 */
public class SlUtils {

    private static final Hashids hashids = new Hashids(SlConstants.HASHIDS_SALT,6);

    public static final long MAX_NUMBER = 9007199254740992L;

    /**
     * 获取短链接（本方法适用于）
     * PS：如果传入的参数id > 9007199254740992L，建议使用另外一个获取方法：{@link BaseUtils#getShortKey(long)}
     * 虽然本方法支持大于9007199254740992L，但是生成的短链接字符串位数长度位17左右
     * @author wangzhiyong
     * @date 2022/9/12 下午5:12
     * @param id
     * @return java.lang.String
     */
    public static String getShortKey(long id){
        if (id > MAX_NUMBER) {
            long[] ids = new long[3];
            ids[0] = id / MAX_NUMBER;
            ids[1] = id % MAX_NUMBER;
            ids[2] = ThreadLocalRandom.current().nextLong(1000);
            return hashids.encode(ids);
        } else {
            return hashids.encode(id);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            long id = SnowflakeIdUtil.nextId();
            String shortKey = getShortKey(id);
            System.out.println("shortKey = " + shortKey);
        }
    }
}
