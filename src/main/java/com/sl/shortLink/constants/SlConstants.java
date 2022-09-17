package com.sl.shortLink.constants;

/**
 * 短链接服务常量类
 *
 * @author wangzhiyong
 * @date 2022年09月12日 下午5:06
 */
public class SlConstants {
    //hashids对应的盐值
    public static final String HASHIDS_SALT = "sl202209120506";
    //队列大小
    public static final Integer QUEUE_SIZE = 12;

    //队列负载因子
    public static final float DEFAULT_LOAD_FACTOR = 0.25f;

    //需要往队列中添加元素的阀值
    public static final Integer ADD_QUEUE_ELEMENT_THRESHOLD = (int)(QUEUE_SIZE * DEFAULT_LOAD_FACTOR);

    //需要往队列中补充元素的个数
    public static final Integer REPLENISH_QUEUE_ELEMENT_COUNT = (int)(QUEUE_SIZE * (1 - DEFAULT_LOAD_FACTOR));

    //单次批量生成短链接的个数
    public static final Integer BATCH_GENERATE_MAX_NUM = 20;

    public static void main(String[] args) {
        System.out.println("ADD_QUEUE_ELEMENT_THRESHOLD = " + ADD_QUEUE_ELEMENT_THRESHOLD);
        System.out.println("REPLENISH_QUEUE_ELEMENT_COUNT = " + REPLENISH_QUEUE_ELEMENT_COUNT);
    }
}
