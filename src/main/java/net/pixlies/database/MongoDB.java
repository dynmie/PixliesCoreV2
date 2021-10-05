package net.pixlies.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import net.pixlies.Main;
import org.bson.Document;
import org.bukkit.Bukkit;

@Getter
public class MongoDB {

    private static final Main instance = Main.getInstance();

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> userCollection;

    public MongoDB init() {
        String uri = instance.getConfig().getString("mongodb-connectionstring");
        if (uri == null) {
            instance.getLogger().warning("Plugin can't start because MongoDB URI is missing.");
            Bukkit.getPluginManager().disablePlugin(instance);
            return this;
        }
        MongoClientURI clientURI = new MongoClientURI(uri);
        client = new MongoClient(clientURI);

        database = client.getDatabase("admin");

        userCollection = database.getCollection(instance.getConfig().getString("users-collection", "users"));

        return this;
    }

}
