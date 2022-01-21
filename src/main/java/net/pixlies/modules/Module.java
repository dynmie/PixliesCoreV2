package net.pixlies.modules;

import lombok.AccessLevel;
import lombok.Getter;
import net.pixlies.Main;

public abstract class Module {

    @Getter(AccessLevel.PROTECTED) private static final Main instance = Main.getInstance();
    @Getter private final ModuleDescription description = Main.getInstance().getModuleManager().getDescription(this);

    public abstract void onLoad();

    public abstract void onDrop();

}
