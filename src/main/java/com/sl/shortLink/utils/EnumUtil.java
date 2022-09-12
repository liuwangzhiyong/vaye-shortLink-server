package com.sl.shortLink.utils;
import com.sl.shortLink.common.basic.IBaseEnum;

import java.io.Serializable;
import java.util.EnumSet;

/**
 *  枚举工具类
 * @author wangzhiyong
 * @date 2021/12/20 下午3:20
 * @param
 * @return null
 */
public class EnumUtil {

    /**
     * 枚举转换
     */
    public static <T extends Serializable, E extends Enum<E> & IBaseEnum<T>> E convert(T value, Class<E> cla) {
        if (null == value) {
            return null;
        }
        if (null == cla) {
            return null;
        }
        EnumSet<E> set = EnumSet.allOf(cla);
        if (set == null || set.size() <= 0) {
            return null;
        }
        for (E e : set) {
            T t = e.getValue();
            if(null != t){
                if(value.equals(t)){
                    return e;
                }
            }
        }
        return null;
    }

    public static <T extends Serializable, E extends Enum<E> & IBaseEnum<T>> boolean isAny(T value, E... baseEnum) {
        if (null == value) {
            return false;
        }
        if (null == baseEnum || baseEnum.length == 0) {
            return false;
        }
        for (E en : baseEnum) {
            if (en.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static <T extends Serializable, E extends Enum<E> & IBaseEnum<T>> boolean isNotAny(T value, E... baseEnum) {
        return !isAny(value, baseEnum);
    }

    public static <E extends Enum<E> & IBaseEnum<?>> boolean isAny(E currentEnum, E... enums) {
        if (null == currentEnum) {
            return false;
        }
        if (null == enums || enums.length == 0) {
            return false;
        }
        for (E en : enums) {
            if (currentEnum == en) {
                return true;
            }
        }
        return false;
    }

    public static <E extends Enum<E> & IBaseEnum<?>> boolean isNotAny(E currentEnum, E... enums) {
        return !isAny(currentEnum, enums);
    }
}
