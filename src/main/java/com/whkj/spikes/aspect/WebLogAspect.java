package com.whkj.spikes.aspect;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import com.whkj.spikes.consts.LoggerName;
import com.whkj.spikes.util.JacksonMapper;
import com.whkj.spikes.util.WebIpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Controller层日志打印.
 */
@Component
@Aspect
@Slf4j
public class WebLogAspect {

    private static final Logger webLogger = LoggerFactory.getLogger(LoggerName.WEB_LOGGER_NAME);

    @Autowired(required = false)
    private HttpServletRequest request;

//    @Autowired
//    private InnerAlarmService innerAlarmService;

    @Around(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object addMonitorLog(final ProceedingJoinPoint pjp) throws Throwable {
        final Stopwatch stopwatch = Stopwatch.createUnstarted().start();
        webLogger.debug("request:{}", request);
        String ip = WebIpUtils.getIpAddr(request);

        Signature signature = pjp.getSignature();
        String className = StringUtils.substringAfterLast(signature.getDeclaringTypeName(), ".");
        String methodName = signature.getName();
        String fullName = className + "_" + methodName;
        Map<String, Object> params = getHttpRequestArgParams(pjp);
        Map<String, String> headers = getHeader(request);
        webLogger.info("方法:{}, 请求参数:{},headers:{}, 请求Ip:{}", fullName, params, headers, ip);

        try {
            Object returnObject = pjp.proceed();
            webLogger.info("方法:{}, 请求参数:{}! 请求Ip:{}", fullName, params, ip);
            return returnObject;
        } catch (final Exception e) {
            webLogger.error("方法:{} 出错!  请求参数:{}!  请求Ip:{}", fullName, params, ip, e);
//            innerAlarm(e, servletPath, fullName, ak, params, loginUserId);
            throw e;
        } finally {
            stopwatch.stop();
            webLogger.info("方法:{} 执行耗时:{} !", fullName, stopwatch);
        }
    }

    private void innerAlarm(Exception e, String servletPath, String fullName, String ak, Object requestArgs, String loginUserId) {
        //报警
    }


    private Map<String, Object> getHttpRequestArgParams(ProceedingJoinPoint pjp) {
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        Map<String, Object> params = new LinkedHashMap<>();

        if (request != null) {
            params.put("url", request.getRequestURL());
            params.put("method", request.getMethod());
            params.put("content-type", request.getContentType());
        }

        // 循环获得所有参数对象
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            //如果是BindingAwareModelMap这种类型的 直接跳过
            if (arg instanceof BindingAwareModelMap) {
                continue;
            }
            String key = "args[" + i + "]";
            String value = getArg(arg);
            if (StringUtils.isNotBlank(value)) {
                params.put(key, value);
            }
        }

        return params;
    }

    private String getArg(Object arg) {
        if (null == arg) {
            return "空参数";
        }
        if (arg instanceof HttpServletRequest || arg instanceof HttpServletResponse || arg instanceof MultipartFile) {
            Class<?> clazz = getClass();
            if (clazz == WebLogAspect.class) {
                return "";
            }
            return clazz.getName();
        }
        try {
            return JacksonMapper.INSTANCE.toJson(arg);
        } catch (Exception e) {
            //ignore ex
//            innerAlarmService.innerAlarm("请求参数序列化为Json出错. " + ExceptionUtil.getErrorMessageWithRootAndNestedException(e));
            return "";
        }
    }

    private Map<String, String> getHeader(HttpServletRequest request) {
        Map<String, String> headers = Maps.newHashMap();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String header = enumeration.nextElement();
            headers.put(header, request.getHeader(header));
        }
        return headers;
    }

}
