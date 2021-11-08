package net.pixlies.modules;

import lombok.Data;

@Data
public class ModuleDescription {

    private String name;
    private String[] authors;
    private String version;
    private String main;
    private boolean activated;

    public ModuleDescription(String name, String[] authors, String version, String main) {
        this.name = name;
        this.authors = authors;
        this.version = version;
        this.main = main;
        activated = true;
    }

    public void deactivate() {
        activated = false;
    }

    public void activate() {
        activated = true;
    }

}
