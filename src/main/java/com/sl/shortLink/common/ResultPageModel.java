package com.sl.shortLink.common;

import lombok.Data;

@Data
public class ResultPageModel<T> extends ResultModel<T> {

    //总记录数
    private Long totalCount;
    //总页数
    private Long totalPage;
    //当前页
    private Long currentPage;
    //每页显示的条数
    private Long pageSize;
    //是否有更多的数据
    private Boolean hasMore;
}
