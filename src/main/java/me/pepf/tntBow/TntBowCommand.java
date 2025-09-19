package me.pepf.tntBow;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class TntBowCommand implements CommandExecutor {
    NamespacedKey key;
    public TntBowCommand(JavaPlugin plugin) {
        key = new NamespacedKey(plugin, "tnt_bow");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p))
            return false;
        if (!p.isOp()) {
            p.sendMessage("§cYou must be an OP to use this command!");
            return false;
        }
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();

        bowMeta.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);

        bowMeta.setDisplayName("§4§lTNT Bow");
        bow.setItemMeta(bowMeta);

        p.getInventory().addItem(bow);
        p.sendMessage("You have been given the TNT Bow!");
        return true;
    }

}
