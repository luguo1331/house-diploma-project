package com.sise.house.user.biz.exception;

import com.sise.common.exception.MyException;
import com.sise.common.rest.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Stalary
 * @description
 * @date 2018/04/14
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServerResponse handle(Exception e) {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return ServerResponse.createByErrorCodeMessage(myException.getCode(), myException.getMessage());
        } else {
            log.error("[系统异常]", e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            return ServerResponse.createByErrorCodeMessage(500, "user 运行时异常！" + sw.toString());
        }
    }

    public ExceptionHandle() {
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServerResponse handleMethodArgumentException(MethodArgumentNotValidException ex) {
        log.debug("数据验证失败：{}", ex.getMessage());
        return errorMsg(ex.getBindingResult());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServerResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.debug("数据验证失败：{}", ex.getMessage());
        Map<String, String> map = new HashMap();
        map.put(ex.getParameterName(), "不能为空啊大兄弟");
        return ServerResponse.createByErrorDate(map);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServerResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("请求方式有问题" + ex.getMessage());
        Map<String, String> map = new HashMap();
        map.put(ex.getMessage(), "参数有问题啊大兄弟");
        return ServerResponse.createByErrorDate(map);
    }

    private ServerResponse errorMsg(BindingResult bindingResult) {
        Map<String, String> map = new HashMap();
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            Iterator errorIterator = errors.iterator();
            while (errorIterator.hasNext()) {
                FieldError err = (FieldError) errorIterator.next();
                map.put(err.getField(), err.getDefaultMessage());
            }
        }
        return ServerResponse.createByErrorDate(map);
    }
}
