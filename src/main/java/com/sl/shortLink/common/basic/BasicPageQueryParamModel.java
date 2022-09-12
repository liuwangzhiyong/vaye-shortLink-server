package com.sl.shortLink.common.basic;

import com.sl.shortLink.common.CommonParams;
import lombok.Data;
/**
 *
 * @author wangzhiyong
 * @date 2022/3/13 上午10:21
 * @param
 * @return null
 */
@Data
public class BasicPageQueryParamModel extends CommonParams {

    //当前页数
    private Integer currentPage = 1;

    //当前个数
    private Integer pageSize = 10;

    public Integer getBeginNum(){
        return (this.getCurrentPage()-1)*this.getPageSize();
    }
}
