package net.pixlies.modules;

import lombok.AccessLevel;
import lombok.Getter;
import net.pixlies.Main;

import java.io.File;

public abstract class Module {

    @Getter(AccessLevel.PROTECTED) private static final Main pixlies = Main.getInstance();
    @Getter private final ModuleDescription description = Main.getInstance().getModuleManager().getDescription(this);
    @Getter(AccessLevel.PROTECTED) private final File moduleFolder = new File(
            Main.getInstance().getDataFolder().getAbsolutePath() + File.separator + "modules" + File.separator + description.getName());

    public abstract void onLoad();

    public abstract void onDrop();

}
