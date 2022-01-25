package net.pixlies.nations.config.impl;

import net.pixlies.nations.Nations;
import net.pixlies.nations.config.Config;

import java.io.File;

public class ConfConfig extends Config {

    public ConfConfig() {
        super(new File(Nations.getInstance().getModuleFolder().getAbsolutePath(), "config.yml"), "config.yml");
    }

}
