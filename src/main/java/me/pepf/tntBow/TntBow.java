package me.pepf.tntBow;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class TntBow extends JavaPlugin {

    private final JavaPlugin plugin = this;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ArrowEventHandler(this), this);
        getCommand("tntbow").setExecutor(new TntBowCommand(this));
    }


}