package net.pixlies.modules;

import net.pixlies.Main;

public interface Module {

    Main instance = Main.getInstance();

    void onLoad();

    void onDrop();

}
