package net.pixlies.modules;

import com.google.gson.Gson;
import lombok.Data;
import lombok.SneakyThrows;
import net.pixlies.Main;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Data
public class ModuleManager {

    private static final Main instance = Main.getInstance();

    private List<Module> modules = new ArrayList<>();
    private Map<String, ModuleDescription> moduleDescriptions = new HashMap<>();

    @SneakyThrows
    public void loadModules() {
        File modulesFolder = new File(instance.getDataFolder().getAbsolutePath() + "/modules/");
        modulesFolder.mkdirs();
        if (!modulesFolder.exists())
            modulesFolder.mkdir();

        for (File jarFile : Objects.requireNonNull(modulesFolder.listFiles())) {
            if (!jarFile.getName().endsWith(".jar")) continue;
            Validate.notNull(jarFile, "File cannot be null");

            JarFile jar = null;
            InputStream stream = null;

            try {
                jar = new JarFile(jarFile);
                JarEntry entry = jar.getJarEntry("info.json");

                if (entry == null) {
                    instance.getLogger().severe("Module " + jarFile.getName() + " does not contain info.json! Contact the developer to fix this issue ASAP.");
                    return;
                }

                stream = jar.getInputStream(entry);

                ModuleDescription infoJson = new Gson().fromJson(IOUtils.toString(stream, StandardCharsets.UTF_8), ModuleDescription.class);

                URLClassLoader child = new URLClassLoader(
                        new URL[] {jarFile.toURI().toURL()},
                        this.getClass().getClassLoader()
                );
                Class<? extends Module> mainClass = Class.forName(infoJson.getMain(), true, child).asSubclass(Module.class);
                Module moduleInstance = mainClass.getDeclaredConstructor().newInstance();

                modules.add(moduleInstance);
                moduleDescriptions.put(infoJson.getName(), infoJson);


                instance.getLogger().info("Loading module " + infoJson.getName() + " v" + infoJson.getVersion() + "...");
                try {
                    moduleInstance.onLoad();
                    instance.getLogger().info("The module " + infoJson.getName() + " v" + infoJson.getVersion() + " has been successfully loaded!");
                } catch (Exception e) {
                    instance.getLogger().severe("Failed to load module " + infoJson.getName() + " v" + infoJson.getVersion() + "!");
                    e.printStackTrace();
                }
            } finally {
                if (jar != null) {
                    try {
                        jar.close();
                    } catch (IOException ignored) {
                    }
                }
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }

    }

}
