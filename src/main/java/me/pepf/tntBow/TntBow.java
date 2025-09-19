package me.pepf.tntBow;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public final class TntBow extends JavaPlugin {

    private final JavaPlugin plugin = this;
    private final NamespacedKey key = new NamespacedKey(plugin, "tnt_bow");
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ArrowEventHandler(this), this);
        getCommand("tntbow").setExecutor(new TntBowCommand(this));
    }


}