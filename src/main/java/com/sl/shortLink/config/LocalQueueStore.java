package com.sl.shortLink.config;

import com.sl.shortLink.constants.SlConstants;
import com.sl.shortLink.utils.BaseUtils;
import com.sl.shortLink.utils.SnowflakeIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 本地短链接存储
 *
 * @author wangzhiyong
 * @date 2022年09月13日 下午2:29
 */
@Slf4j
public class LocalQueueStore {
    private static ArrayBlockingQueue<Pair<Long,String>> queueStore = new ArrayBlockingQueue<>(SlConstants.QUEUE_SIZE);

    /**
     * 初始化队列
     * @author wangzhiyong
     * @date 2022/9/13 下午2:36
     */
    public static void init(){
        for (int i = 0; i < SlConstants.QUEUE_SIZE; i++) {
            long id = SnowflakeIdUtil.nextId();
            String shortKey = BaseUtils.getShortKey(id);
            queueStore.offer(Pair.of(id,shortKey));
        }
    }

    /**
     * 添加短链接
     * @author wangzhiyong
     * @date 2022/9/13 下午2:44
     * @param count 添加的个数
     */
    public static void addShortKeys(int count){
        if (count <= 0) {
            log.error("LocalQueueStore-addShortKeys is error,count={}",count);
            return;
        }
        for (int i = 0; i < count; i++) {
            long id = SnowflakeIdUtil.nextId();
            String shortKey = BaseUtils.getShortKey(id);
            queueStore.offer(Pair.of(id,shortKey));
        }
    }

    /**
     * 获取多个短链接
     * @author wangzhiyong
     * @date 2022/9/13 下午2:49
     * @param count 获取短链接的个数
     * @return java.util.List<java.lang.String>
     */
    public static List<Pair<Long,String>> getShortKeys(int count){
        List<Pair<Long,String>> shortKeys = new ArrayList<>();
        if (count <= 0) {
            log.error("LocalQueueStore-addShortKeys is error,count={}",count);
            return shortKeys;
        }
        if (queueStore.size() <= count) {
            count = queueStore.size();
        }
        for (int i = 0; i < count; i++) {
            Pair<Long,String> pair = queueStore.poll();
            shortKeys.add(pair);
        }
        return shortKeys;
    }

    /**
     * 获取单个短链接
     * @author wangzhiyong
     * @date 2022/9/13 下午3:32
     * @return org.apache.commons.lang3.tuple.Pair<java.lang.Long,java.lang.String>
     */
    public static Pair<Long,String> getShortKey(){
        if (queueStore.size() < 0) {
            return null;
        }
       return queueStore.poll();
    }

    /**
     * 获取阻塞队列元素数量
     * @author wangzhiyong
     * @date 2022/9/13 下午4:45
     * @return int
     */
    public static int getSize(){
        return queueStore.size();
    }


}
