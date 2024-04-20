package com.whkj.spikes.service;

import com.whkj.spikes.bo.AppInfo;
import com.whkj.spikes.bo.EnvInfo;
import com.whkj.spikes.bo.JvmInfo;
import com.whkj.spikes.bo.OsInfo;
import com.whkj.spikes.util.MemoryMXBeanUtils;
import com.whkj.spikes.util.OperatingSystemMXBeanUtils;
import com.whkj.spikes.util.RuntimeUtils;
import com.whkj.spikes.util.UtilAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppInfoService {

    @Autowired
    private EnvInfo envInfo;

    @Autowired
    private OsInfo osInfo;

    @Autowired
    private JvmInfo jvmInfo;

    public AppInfo build() {
        AppInfo appInfo = new AppInfo();
        appInfo.setPid(RuntimeUtils.getPid());
        appInfo.setEnvInfo(envInfo);
        appInfo.setOsInfo(osInfo);
        appInfo.setJvmInfo(jvmInfo);
        appInfo.setRuntimeInfo(getRuntimeInfo());

        return appInfo;
    }

    private AppInfo.MemoryInfo getMemoryInfo() {
        AppInfo.MemoryInfo memoryInfo = new AppInfo.MemoryInfo();
        memoryInfo.setInit(MemoryMXBeanUtils.getHeapMemoryInitInMBytes() + "M");
        memoryInfo.setUsed(MemoryMXBeanUtils.getHeapMemoryUsedInMBytes() + "M");
        memoryInfo.setCommitted(MemoryMXBeanUtils.getHeapMemoryCommittedInMBytes() + "M");
        memoryInfo.setMax(MemoryMXBeanUtils.getHeapMemoryMaxInMBytes() + "M");
        return memoryInfo;
    }

    private AppInfo.RuntimeInfo getRuntimeInfo() {
        AppInfo.RuntimeInfo runtimeInfo = new AppInfo.RuntimeInfo();
        runtimeInfo.setVmStartTime(UtilAll.formatFriendlyTimeSpanByNow(RuntimeUtils.getVmStartTime()));
        runtimeInfo.setVmUpTime(UtilAll.toPrettyString(RuntimeUtils.getVmUpTime()));
        runtimeInfo.setSystemLoadAverage(OperatingSystemMXBeanUtils.getSystemLoadAverage());
        runtimeInfo.setMemoryInfo(getMemoryInfo());
        return runtimeInfo;
    }

}


