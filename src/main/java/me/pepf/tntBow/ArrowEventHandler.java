package me.pepf.tntBow;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

class ArrowEventHandler implements Listener {
    private final NamespacedKey key;

    public ArrowEventHandler(JavaPlugin plugin) {
        this.key = new NamespacedKey(plugin, "tnt_bow");
    }
    @EventHandler
    void onArrowShot(ProjectileLaunchEvent e)
    {
        //The bow should only fire tnt arrows if it has the right
        //persistentDataContainer. Then adds a persistentDataContainer
        //to the arrow, so that the "onArrowLand" function knows that the arrow is shot
        //by the player with this bow in the Main or offhand.
        if (e.getEntity() instanceof Arrow arrow && arrow.getShooter() instanceof Player player) {
            var bow = player.getInventory().getItemInMainHand();
            if (!bow.getType().equals(Material.BOW))
                bow = player.getInventory().getItemInOffHand();
            if (!bow.getType().equals(Material.BOW))
                return;
            var bowMeta = bow.getItemMeta();
            if (bowMeta.getPersistentDataContainer().has(key)) {
                arrow.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);
            }
        }

    }

    @EventHandler
    void onArrowLand(ProjectileHitEvent e) {
        //Checks if the Projectile hitting something is an arrow and is shot by a player.
        //The name of the item has to be "TNT Bow" in order to work
        if (e.getEntity() instanceof Arrow shotArrow
                && shotArrow.getShooter() instanceof Player
                && shotArrow.getPersistentDataContainer().has(key)) {
            var arrowLocation = shotArrow.getLocation();
            //Spawns a tnt at the Location of the Arrow and makes it instantly explode
            TNTPrimed tnt = (TNTPrimed)arrowLocation.getWorld().spawnEntity(arrowLocation, EntityType.TNT);
            tnt.setFuseTicks(0);

            shotArrow.remove();
        }
    }
}
