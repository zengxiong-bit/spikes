package com.whkj.spikes.aspect;

import com.google.common.base.Stopwatch;
import com.whkj.spikes.consts.LoggerName;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MonitorLogAspect {

    private static final Logger perfLogger = LoggerFactory.getLogger(LoggerName.PERF_LOGGER_NAME);

    @Around(value = "@annotation(com.whkj.spikes.annotations.MonitorLog)")
    public Object addMonitorLog(ProceedingJoinPoint pjp) throws Throwable {
        final Stopwatch stopwatch = Stopwatch.createUnstarted().start();
        Signature signature = pjp.getSignature();
        String methodName = signature.getName();
        String className = parseShortClassName(signature
            .getDeclaringTypeName());
        String fullName = className + "_" + methodName;
        try {
            return pjp.proceed();
        } finally {
            stopwatch.stop();
            perfLogger.info("方法:{} 执行耗时:{} !", fullName, stopwatch);
        }
    }

    private static String parseShortClassName(String className) {
        return StringUtils.substringAfterLast(className, ".");
    }

}
