package com.whkj.spikes.controller;

import com.google.common.base.Strings;
import com.whkj.spikes.bo.rest.RestResult;
import com.whkj.spikes.consts.Consts;
import com.whkj.spikes.consts.ErrorCodeEnum;
import com.whkj.spikes.exception.BusinessException;
import com.whkj.spikes.exception.InternalException;
import com.whkj.spikes.util.ExceptionUtil;
import com.whkj.spikes.util.Reflections;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 全局的异常处理.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 400 - Bad Request.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public RestResult handleMissingServletRequestParameterException(final MissingServletRequestParameterException e) {
        log.error("缺少请求参数", e);
        return RestResult.failure().code(HttpStatus.BAD_REQUEST.value()).msg("required_parameter_is_not_present");
    }

    /**
     * 400 - Bad Request.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public RestResult handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        log.error("参数解析失败", e);
        return RestResult.failure().code(HttpStatus.BAD_REQUEST.value()).msg("参数不合法");
    }

    /**
     * 400 - Bad Request.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public RestResult handleTypeMismatchException(final TypeMismatchException e) {
        log.error("参数解析失败,参数类型不匹配", e);
        return RestResult.failure().code(HttpStatus.BAD_REQUEST.value()).msg("参数解析失败，参数类型不匹配.");
    }

    /**
     * 400 - Bad Request.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public RestResult handleServiceException(final ConstraintViolationException e) {
        log.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return RestResult.failure().msg("parameter:" + message);
    }

    /**
     * 400 - Bad Request.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public RestResult handleValidationException(final ValidationException e) {
        log.error("参数验证失败", e);
        return RestResult.failure().msg("validation_exception");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public RestResult handleBindException(final BindException ex) {
        log.error("参数绑定失败", ex);
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        for (FieldError fieldError : fieldErrors) {
            String error = Strings.nullToEmpty(fieldError.getDefaultMessage());
            if (error.contains("NumberFormatException")) {
                error = fieldError.getField() + ":请输入正确的数字！";
            }
            if (error.contains("ConversionFailedException") && error.contains("annotation.DateTimeFormat")) {
                StringBuilder buff = new StringBuilder(fieldError.getField());
                buff.append("时间格式不合法！");
                Field filed = Reflections.getAccessibleField(ex.getTarget(), fieldError.getField());
                if (filed!= null && filed.isAnnotationPresent(DateTimeFormat.class)) {
                    buff.append("正确的时间格式为:").append(filed.getAnnotation(DateTimeFormat.class).pattern());
                }
                error = buff.toString();
            }
            errors.add(error);
        }

        for (ObjectError objectError : globalErrors) {
            String error = objectError.getDefaultMessage();
            errors.add(error);
        }
        return RestResult.failure().code(Consts.DEFAULT_ERROR_CODE).msg(StringUtils.join(errors, "|"));
    }

    /**
     * 400 - Bad Request.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResult handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        return this.handleException(new BindException(ex.getBindingResult()));
    }

    /**
     * 405 - Method Not Allowed.
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public RestResult handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法", e);
        return RestResult.failure().code(HttpStatus.METHOD_NOT_ALLOWED.value()).msg("request_method_not_supported. supportedMethods: " + Arrays.toString(e.getSupportedMethods()));
    }

    /**
     * 415 - Unsupported Media Type.
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public RestResult handleHttpMediaTypeNotSupportedException(final HttpMediaTypeNotSupportedException e) {
        log.error("不支持当前媒体类型", e);
        return RestResult.failure().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).msg("Unsupported Media Type. supportedMediaTypes:{} " + e.getSupportedMediaTypes());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public RestResult handleBusinessException(final BusinessException ex) {
        log.error("occur BusinessException.", ex);
        return RestResult.failure().code(ex.getErrorCode()).msg(ex.getMessage());
    }

    @ExceptionHandler(InternalException.class)
    @ResponseBody
    public RestResult handleInternalException(final InternalException ex) {
        log.error("occur InternalException.", ex);
        return RestResult.failure().code(ErrorCodeEnum.ERROR_INTERNAL.getErrorCode()).msg(Consts.INTERNAL_ERROR_DEFAULT_SHOW_MSG);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResult handleException(final Exception ex) {
        log.error("occur Exception.", ex);
        String errMsg = ExceptionUtil.getErrorMessageWithRootAndNestedException(ex);
        return RestResult.failure().code(Consts.DEFAULT_ERROR_CODE).msg(errMsg);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public RestResult handleRuntimeException(final RuntimeException ex) {
        log.error("occur RuntimeException.", ex);
        return RestResult.failure().code(ErrorCodeEnum.ERROR_INTERNAL.getErrorCode()).msg(Consts.INTERNAL_ERROR_DEFAULT_SHOW_MSG);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseBody
    public RestResult handleNullPointerException(final NullPointerException ex) {
        log.error("occur NullPointerException.", ex);
        String message = ex.getMessage();
        String finalMessage = StringUtils.isBlank(message) || "null".equalsIgnoreCase(message) ? Consts.INTERNAL_ERROR_DEFAULT_SHOW_MSG : message;
        return RestResult.failure().code(ErrorCodeEnum.ERROR_INTERNAL.getErrorCode()).msg(finalMessage);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseBody
    public RestResult handleIllegalArgumentException(final IllegalArgumentException ex) {
        log.error("occur IllegalArgumentException.", ex);
        String message = ex.getMessage();
        String finalMessage = StringUtils.isBlank(message)? Consts.INTERNAL_ERROR_DEFAULT_SHOW_MSG : message;
        return RestResult.failure().code(ErrorCodeEnum.ERROR_INTERNAL.getErrorCode()).msg(finalMessage);
    }

}
