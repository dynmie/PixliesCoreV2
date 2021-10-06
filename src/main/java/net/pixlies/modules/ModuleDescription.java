package net.pixlies.modules;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModuleDescription {

    private String name;
    private String[] authors;
    private String version;
    private String main;

}
