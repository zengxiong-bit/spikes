package com.whkj.spikes.bo;

import lombok.Data;

@Data
public class AppInfo {

    private Integer pid;

    private EnvInfo envInfo;

    private OsInfo osInfo;

    private JvmInfo jvmInfo;

    private RuntimeInfo runtimeInfo;

    @Data
    public static class RuntimeInfo {

        private String vmStartTime;

        private String vmUpTime;

        private Double systemLoadAverage;

        private MemoryInfo memoryInfo;

    }

    @Data
    public static class MemoryInfo {

        private String init;

        private String used;

        private String committed;

        private String max;
    }
}
