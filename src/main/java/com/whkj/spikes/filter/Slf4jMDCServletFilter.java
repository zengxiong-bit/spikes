package com.whkj.spikes.filter;

import com.whkj.spikes.trace.ReqTraceConsts;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Slf4jMDCServletFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(Slf4jMDCServletFilter.class);

    private static final List<String> filterUrls = new ArrayList<>();

    @Override
    protected void initFilterBean() throws ServletException {
        String filterUrlStr = this.getFilterConfig().getInitParameter("filterUrl");
        if (StringUtils.isNotBlank(filterUrlStr)) {
            log.debug("发现配置过滤url参数:{}" , filterUrlStr);
            String[] urlArray = filterUrlStr.split(";");
            filterUrls.addAll(Arrays.asList(urlArray));
        }
    }

    /**
     * 异步处理可能会丢失数据
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        if (!filterUrls.contains(request.getRequestURI())) {
            initContextHolders(request);
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            resetContextHolders();
        }
    }

    /**
     * 获取或构造请求参数，默认是请求id和父类名称以及时间
     */
    private void initContextHolders(HttpServletRequest request) {
        String requestId = request.getHeader(ReqTraceConsts.REQUEST_ID);
        if (StringUtils.isBlank(requestId)) {
            requestId = UUID.randomUUID().toString();
        }
        MDC.put(ReqTraceConsts.TRACE_KEY, requestId);
    }

    private void resetContextHolders() {
        MDC.remove(ReqTraceConsts.TRACE_KEY);
    }

}
