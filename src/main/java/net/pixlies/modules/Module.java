package net.pixlies.modules;

import lombok.Getter;
import net.pixlies.Main;

public class Module {

    protected static final Main instance = Main.getInstance();
    @Getter ModuleDescription description = Main.getInstance().getModuleManager().getDescription(this);

    public void onLoad() {

    }

    public void onDrop() {

    }

}
