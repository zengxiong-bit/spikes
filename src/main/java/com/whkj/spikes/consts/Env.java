package com.whkj.spikes.consts;


import io.micrometer.core.instrument.util.StringUtils;

public enum Env {

    UNKNOWN("unknown"), LOCAL("local"), DEV("dev"), UAT("uat"),PROD("prod");

    public final String name;

    Env(String name) {
        this.name = name;
    }

    public static Env transformEnv(String envName) {
        if (StringUtils.isBlank(envName)) {
            return UNKNOWN;
        }

        switch (envName.trim().toLowerCase()) {
            case "local":
                return LOCAL;
            case "dev":
                return DEV;
            case "uat":
            case "test":
                return UAT;
            case "production":
            case "prod":
                return PROD;
            default:
                return UNKNOWN;
        }
    }
}
