package com.sl.shortLink.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sl.shortLink.enums.ResultCodeEnum;

import java.util.List;

/**
 *  返回实体构造工具
 * @author wangzhiyong
 * @date 2021/12/18 下午4:35
 * @param
 * @return null
 */
public class ResultBuilder {

    /**
     * 返回参数错误信息
     */
    public static ResultModel buildParamError(){
        return buildParamError(ResultCodeEnum.MISSING_REQUEST_PARAMETER.getDesc());
    }

    public static ResultModel buildParamError(String errMsg){
        return buildParamError(ResultCodeEnum.MISSING_REQUEST_PARAMETER.getCode(), errMsg);
    }

    public static ResultModel buildParamError(Integer errCode, String errMsg){
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errCode);
        resultModel.setMsg(errMsg);
        return resultModel;
    }
    /**
     *  返回系统错误信息
     * @author wangzhiyong
     * @date 2021/11/17 上午10:53
     * @return com.sw.swtransferserver.common.ResultModel
     */
    public static ResultModel buildSystemError(){
        return buildSystemError(ResultCodeEnum.SYSTEM_INSIDE_ERROR.getDesc());
    }

    public static ResultModel buildSystemError(String errMsg){
        return buildParamError(ResultCodeEnum.SYSTEM_INSIDE_ERROR.getCode(), errMsg);
    }

    public static ResultModel buildSystemError(Integer errCode, String errMsg){
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errCode);
        resultModel.setMsg(errMsg);
        return resultModel;
    }

    /**
     * 返回正确的信息
     */
    public static <T> ResultModel<T> buildSuccessData(T t){
        return buildSuccess(t, ResultCodeEnum.SUCCEED.getDesc());
    }

    /**
     * 返回正确的信息
     */
    public static ResultModel buildSuccess(){
        return buildSuccess(ResultCodeEnum.SUCCEED.getDesc());
    }

    public static ResultModel buildSuccess(String msg){
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(ResultCodeEnum.SUCCEED.getCode());
        resultModel.setMsg(msg);
        return resultModel;
    }

    /**
     * 返回正确的信息
     */
    public static <T> ResultModel<T> buildSuccess(T t, String msg){
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(ResultCodeEnum.SUCCEED.getCode());
        resultModel.setMsg(msg);
        resultModel.setData(t);
        return resultModel;
    }
    /**
     * 返回错误的信息
     */
    public static ResultModel buildFailed(){
        return buildFailed(ResultCodeEnum.FAILED.getDesc());
    }

    public static ResultModel buildFailed(String errMsg){
        return buildFailed(ResultCodeEnum.FAILED.getCode(), errMsg);
    }

    public static ResultModel buildFailed(Integer errCode, String errMsg){
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errCode);
        resultModel.setMsg(errMsg);
        return resultModel;
    }

    public static ResultModel buildNoAuth() {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(ResultCodeEnum.PERMISSION_DENIED.getCode());
        resultModel.setMsg(ResultCodeEnum.PERMISSION_DENIED.getDesc());
        return resultModel;
    }

    public static <T> ResultPageModel<List<T>> buildPage(Page<T> page){
        ResultPageModel<List<T>> pageModel = new ResultPageModel();
        pageModel.setCode(ResultCodeEnum.SUCCEED.getCode());
        pageModel.setMsg(ResultCodeEnum.SUCCEED.getDesc());
        pageModel.setTotalCount(page.getTotal());
        pageModel.setTotalPage(page.getPages());
        pageModel.setCurrentPage(page.getCurrent());
        pageModel.setPageSize(page.getSize());
        pageModel.setHasMore(page.hasNext() ? true : false);
        pageModel.setData(page.getRecords());
        return pageModel;
    }

    public static <T> ResultPageModel<List<T>> buildPage(Page<?> page, List<T> list) {
        ResultPageModel<List<T>> pageModel = new ResultPageModel();
        pageModel.setCode(ResultCodeEnum.SUCCEED.getCode());
        pageModel.setMsg(ResultCodeEnum.SUCCEED.getDesc());
        pageModel.setTotalCount(page.getTotal() );
        pageModel.setTotalPage(page.getPages());
        pageModel.setCurrentPage(page.getCurrent());
        pageModel.setPageSize(page.getSize());
        pageModel.setHasMore(page.hasNext() ? true : false);
        pageModel.setData(list);
        return pageModel;
    }
    public static <T> ResultPageModel<T> buildPageData(Page<?> page,T data){
        ResultPageModel<T> pageModel = new ResultPageModel();
        pageModel.setCode(ResultCodeEnum.SUCCEED.getCode());
        pageModel.setMsg(ResultCodeEnum.SUCCEED.getDesc());
        pageModel.setTotalCount(page.getTotal() );
        pageModel.setTotalPage(page.getPages());
        pageModel.setCurrentPage(page.getCurrent());
        pageModel.setPageSize(page.getSize());
        pageModel.setHasMore(page.hasNext() ? true : false);
        pageModel.setData(data);
        return pageModel;
    }
    public static ResultPageModel buildPageSysError(){
        ResultPageModel pageModel = new ResultPageModel();
        pageModel.setCode(ResultCodeEnum.SYSTEM_INSIDE_ERROR.getCode());
        pageModel.setMsg(ResultCodeEnum.SYSTEM_INSIDE_ERROR.getDesc());
        return pageModel;
    }
    public static ResultPageModel buildPageParamError(){
        return buildPageParamError(ResultCodeEnum.MISSING_REQUEST_PARAMETER.getDesc());
    }

    public static ResultPageModel buildPageParamError(String msg){

        ResultPageModel pageModel = new ResultPageModel();
        pageModel.setCode(ResultCodeEnum.MISSING_REQUEST_PARAMETER.getCode());
        pageModel.setMsg(msg);
        return pageModel;
    }
}
