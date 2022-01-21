package net.pixlies.modules;

import lombok.Data;

@Data
public class ModuleDescription {

    private final String name;
    private final String[] authors;
    private final String version;
    private final String main;
    private boolean activated;

    public ModuleDescription(String name, String[] authors, String version, String main) {
        this.name = name;
        this.authors = authors;
        this.version = version;
        this.main = main;
        activated = true;
    }

    /**
     *  Do not set this from the description, use the module manager's methods instead.
     * @param activated Set the activation status.
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

}
