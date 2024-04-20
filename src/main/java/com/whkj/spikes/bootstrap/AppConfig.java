package com.whkj.spikes.bootstrap;

import com.whkj.spikes.bo.EnvInfo;
import com.whkj.spikes.bo.JvmInfo;
import com.whkj.spikes.bo.OsInfo;
import com.whkj.spikes.consts.Consts;
import com.whkj.spikes.consts.Env;
import com.whkj.spikes.filter.AccessFilter;
import com.whkj.spikes.filter.Slf4jMDCServletFilter;
import com.whkj.spikes.interceptor.LoginInterceptor;
import com.whkj.spikes.interceptor.MyInterceptor1;
import com.whkj.spikes.service.PassportService;
import com.whkj.spikes.servlet.SpringHelloWorldServlet;
import com.whkj.spikes.spring.SpringContextHolder;
import com.whkj.spikes.util.Platforms;
import com.whkj.spikes.util.RuntimeUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ksc
 */
@Configuration
@PropertySource(value = {"classpath:application.properties", "classpath:conf.properties"})
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private Environment environment;

    @Autowired
    private PassportService passportService;


    @Bean
    @Lazy(false)
    public SpringContextHolder getSpringContextHolder() {
        return new SpringContextHolder();
    }

    @Bean(name = "envInfo")
    public EnvInfo getEnvInfo() {
        String appName = environment.getProperty("info.app.name");
        String appVer = environment.getProperty("info.app.ver");
        String appEnvStr = environment.getProperty("info.app.env");
        Env env = Env.transformEnv(appEnvStr);
        String envTagStr = String.format("【%s - %s】", appName, appEnvStr);

        EnvInfo envInfo = new EnvInfo();
        envInfo.setName(appName);
        envInfo.setVer(appVer);
        envInfo.setEnvStr(appEnvStr);
        envInfo.setEnv(env);
        envInfo.setEnvTagStr(envTagStr);
        return envInfo;
    }

    @Bean(name = "osInfo")
    public OsInfo getOsInfo() {
        OsInfo osInfo = new OsInfo();
        osInfo.setName(Platforms.OS_NAME);
        osInfo.setVer(Platforms.OS_VERSION);
        osInfo.setArch(Platforms.OS_ARCH);
        osInfo.setAvailableProcessors(Consts.NCPU);
        return osInfo;
    }

    @Bean(name = "jvmInfo")
    public JvmInfo getJvmInfo() {
        JvmInfo jvmInfo = new JvmInfo();
        jvmInfo.setVmName(RuntimeUtils.getVmName());
        jvmInfo.setVmVer(RuntimeUtils.getVmVersion());
        jvmInfo.setVmVendor(RuntimeUtils.getVmVendor());
        jvmInfo.setJdkVer(SystemUtils.JAVA_SPECIFICATION_VERSION);
        return jvmInfo;
    }

    @Bean
    public FilterRegistrationBean getRequestIdFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new Slf4jMDCServletFilter());
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean getAccessFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new AccessFilter());
        filter.setOrder(1);
        return filter;
    }

  /*  @Bean
    public FilterRegistrationBean getMyFilter1() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter1());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setName("MyFilter1");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean getMyFilter2() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter2());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setName("MyFilter2");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean getMyFilter3() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter3());
        filterRegistrationBean.setOrder(-1);
        filterRegistrationBean.setName("MyFilter3");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean getMyFilter4() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new RequestResponseLoggingFilter());
        filterRegistrationBean.addUrlPatterns("/user/*");
//        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setName("RequestResponseLoggingFilter");
        return filterRegistrationBean;
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/inner/ops/**");
//        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
//        registry.addInterceptor(new MyInterceptor3()).addPathPatterns("/**");

        registry.addInterceptor(new LoginInterceptor(passportService)).addPathPatterns("/account/**");
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new SpringHelloWorldServlet(), "/springHelloWorld/*");
        bean.setLoadOnStartup(1);
        bean.addInitParameter("message", "SpringHelloWorldServlet special message");
        return bean;
    }

}
