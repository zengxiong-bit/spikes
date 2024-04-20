package com.whkj.spikes.filter;

import com.google.common.net.HttpHeaders;
import com.whkj.spikes.util.Ips;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

public class AccessFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger("access");

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = Instant.now().toEpochMilli();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        try {
            chain.doFilter(new ContentCachingRequestWrapper(httpServletRequest), response);
        } finally {
            long costMills = Instant.now().toEpochMilli() - start;
            if (httpServletRequest != null) {
                String remoteIp = Ips.getRealIP(httpServletRequest);
                String method = httpServletRequest.getMethod();
                String url = httpServletRequest.getRequestURI();
                String ua = httpServletRequest.getHeader(HttpHeaders.USER_AGENT);
                String requestId = httpServletRequest.getParameter("x-request-id");
                requestId = requestId == null ? StringUtils.EMPTY : requestId;
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                int responseCode = httpServletResponse == null ? -1 : (httpServletResponse).getStatus();
                LOGGER.info(remoteIp + "|" + method + "|" + url + "|" + responseCode + "|" + costMills + "|" + ua + "|" + requestId);
            } else {
                LOGGER.error("http servlet request is null");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
