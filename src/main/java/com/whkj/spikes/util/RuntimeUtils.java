package com.whkj.spikes.util;


import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class RuntimeUtils {
    private static final RuntimeMXBean RUNTIME_MX_BEAN = ManagementFactory.getRuntimeMXBean();


    /////// RuntimeMXBean相关 //////

    /**
     * 获得当前进程的PID
     * 当失败时返回-1
     */
    public static int getPid() {
        // format: "pid@hostname"
        String jvmName = RUNTIME_MX_BEAN.getName();
        String[] split = jvmName.split("@");
        if (split.length != 2) {
            return -1;
        }

        try {
            return Integer.parseInt(split[0]);
        } catch (Exception e) { // NOSONAR
            return -1;
        }
    }


    /**
     * 返回应用启动到现在的毫秒数
     */
    public static long getVmUpTime() {
        return RUNTIME_MX_BEAN.getUptime();
    }

    public static long getVmStartTime() {
        return RUNTIME_MX_BEAN.getStartTime();
    }

    public static String getVmName() {
        return RUNTIME_MX_BEAN.getVmName();
    }

    public static String getVmVersion() {
        return RUNTIME_MX_BEAN.getVmVersion();
    }

    public static String getVmVendor() {
        return RUNTIME_MX_BEAN.getVmVendor();
    }



    /**
     * 获取CPU核数
     */
    public static int getCores() {
        return Runtime.getRuntime().availableProcessors();
    }

}
