package com.sl.shortLink.utils;

import org.apache.commons.lang3.RandomUtils;

/**
 * Base工具类
 *
 * @author wangzhiyong
 * @date 2022年09月12日 下午5:42
 */
public class BaseUtils {

    private static final String BASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 10进制转换为62进制字符串
     * @author wangzhiyong
     * @date 2022/9/12 下午5:44
     * @param num
     * @return java.lang.String
     */
    public static String toBase62(long num) {
        StringBuilder sb = new StringBuilder();
        int targetBase = BASE.length();
        do {
            int i = (int) (num % targetBase);
            sb.append(BASE.charAt(i));
            num /= targetBase;
        } while (num > 0);

        return sb.reverse().toString();
    }

    /**
     * 62进制字符串转换为10进制
     * @author wangzhiyong
     * @date 2022/9/12 下午5:45
     * @param input
     * @return long
     */
    public static long toBase10(String input) {
        int srcBase = BASE.length();
        long id = 0;
        String r = new StringBuilder(input).reverse().toString();

        for (int i = 0; i < r.length(); i++) {
            int charIndex = BASE.indexOf(r.charAt(i));
            id += charIndex * (long) Math.pow(srcBase, i);
        }

        return id;
    }

    /**
     * 插入随机位(从低位开始，每 5 位后面插入一个随机位，直到高位都是 0 就不再插入)
     * 一定要对每个 id 进行一样的处理，一开始就确定下来固定的位置，如可以每 4 位插一个随机位，也可以在固定第 10 位、第 17 位、第 xx 位等，这样才能保证算法的安全性：两个不一样的数，在固定位置都插入随机位，结果一定不一样。
     * 该方法的目的是为了生成出来的key不规律
     * @author wangzhiyong
     * @date 2022/9/12 下午5:48
     * @param val
     * @return long
     */
    private static long insertRandomBitPer5Bits(long val) {
        long result = val;
        long high = val;
        for (int i = 0; i < 10; i++) {
            if (high == 0) {
                break;
            }
            int pos = 5 + 5 * i + i;
            high = result >> pos;
            result = ((high << 1 | RandomUtils.nextInt(0, 2)) << pos)
                    | (result & (-1L >>> (64 - pos)));
        }
        return Math.abs(result);
    }

    /**
     * 生成短链接key（使用此方法建议 id 从一个中等模式的大小开始，如 100w，而不是从 1 开始，如果id< 9007199254740992L,建议使用：{@link SlUtils#getShortKey(long)}
     * 生成的长度在10位左右
     * @author wangzhiyong
     * @date 2022/9/12 下午6:07
     * @param id
     * @return java.lang.String
     */
    public static String getShortKey(long id){
        long l = insertRandomBitPer5Bits(id);
        String shortKey = toBase62(l);
        return shortKey;
    }
}
