package com.gara.sericefeign.config;

import lombok.Data;

@Data
public class ImportConfigure {

    public ImportConfigure() {

        System.out.println("===========" + ImportConfigure.class.getName());
    }
}
