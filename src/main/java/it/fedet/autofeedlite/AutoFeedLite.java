package it.fedet.autofeedlite;

import it.fedet.autofeedlite.events.ConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoFeedLite extends JavaPlugin {

    @Override
    public void onEnable() {
        registerEvents();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new ConsumeEvent(), this);
    }
}
