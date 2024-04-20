package com.whkj.spikes.bo;

import lombok.Data;


@Data
public class OsInfo {

    private String name;

    private String ver;

    private String arch;

    private int availableProcessors;

}