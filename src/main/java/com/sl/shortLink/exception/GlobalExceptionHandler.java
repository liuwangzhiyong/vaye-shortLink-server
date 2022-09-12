package com.sl.shortLink.exception;

import com.sl.shortLink.common.ResultBuilder;
import com.sl.shortLink.common.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  全局异常处理
 * @author wangzhiyong
 * @date 2021/12/18 下午4:30
 * @param
 * @return null
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     *  参数合法性校验
     * @author wangzhiyong
     * @date 2021/12/18 下午4:30
     * @param e
     * @return com.sw.swtransferserver.common.ResultModel
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultModel handleValidationBodyException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException=参数合法性校验错误",e.getMessage(),e);
        for (ObjectError s : e.getBindingResult().getAllErrors()) {
            return ResultBuilder.buildSystemError(s.getDefaultMessage());
        }
        return ResultBuilder.buildSystemError();
    }

    /**
     *  处理系统异常
     * @author wangzhiyong
     * @date 2021/12/18 下午4:30
     * @param e
     * @return com.sw.swtransferserver.common.ResultModel
     */
    @ExceptionHandler(Exception.class)
    public ResultModel handleProcessException(Exception e){
        log.error("Exception=系统异常",e.getMessage(),e);
        return ResultBuilder.buildSystemError();
    }
}
