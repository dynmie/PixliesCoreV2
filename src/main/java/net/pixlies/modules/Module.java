package net.pixlies.modules;

import lombok.AccessLevel;
import lombok.Getter;
import net.pixlies.Main;

import java.io.File;
import java.util.logging.Logger;

public abstract class Module {

    @Getter private final ModuleDescription description = Main.getInstance().getModuleManager().getDescription(this);
    @Getter private final File moduleFolder = new File(
            Main.getInstance().getDataFolder().getAbsolutePath() + File.separator + "modules" + File.separator + description.getName());
    @Getter private final Logger logger = Logger.getLogger(description.getName());

    public abstract void onLoad();

    public abstract void onDrop();

}
