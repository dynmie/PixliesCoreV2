package net.pixlies.nations.nations.chunk;

import co.aikar.commands.lib.util.Table;
import com.google.gson.JsonObject;
import net.pixlies.nations.Nations;
import net.pixlies.nations.nations.Nation;

import java.util.Map;

public class NationChunk {

    private static final Nations instance = Nations.getInstance();

    public static Map<String, Table<Integer, Integer, NationChunk>> table;

    private String              nationId, world;
    private int                 x,z;
    private NationChunkType     type;
    private JsonObject          data;

    public void claim(boolean claim) {
        Table<Integer, Integer, NationChunk> rst = table.get(world);
        rst.put(x,z, this);
        table.put(world, rst);
        Nation nation = Nation.getById(nationId);
//        if(!nation.getChunks().contains(serialize())) {
//            nations.getChunks().add(serialize());
//            nations.save();
//        }

        if (claim) instance.getLogger().info("§b" + type.name() + "-Chunk claimed at §e" + x + "§8, §e " + z + "§bfor §e" + nation.getName());
    }

}
