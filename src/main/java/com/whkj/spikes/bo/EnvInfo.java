package com.whkj.spikes.bo;

import com.whkj.spikes.consts.Env;
import lombok.Data;

@Data
public class EnvInfo {

    private String name;

    private String ver;

    private String envStr;

    private Env env;

    private String envTagStr;

    public boolean isProd() {
        return env == Env.PROD;
    }

}
