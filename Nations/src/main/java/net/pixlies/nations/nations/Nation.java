package net.pixlies.nations.nations;

import net.pixlies.nations.nations.ranks.Rank;

import java.util.Map;

public class Nation implements NationsEntity {

    private Map<String, Map<String, Object>> ranks;

    public Nation create() {
        ranks.put("leader", Rank.LEADER().toMap());
        ranks.put("admin", Rank.ADMIN().toMap());
        ranks.put("member", Rank.MEMBER().toMap());
        ranks.put("newbie", Rank.NEWBIE().toMap());
        save();
        return this;
    }

    private void save() {
    }

    @Override
    public String id() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }
    
}
