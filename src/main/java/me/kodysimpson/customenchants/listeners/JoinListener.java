package me.kodysimpson.customenchants.listeners;

import me.kodysimpson.customenchants.CustomEnchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        //Give the player a chestplate with the enchantment when they join
        Player player = e.getPlayer();
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        sword.addUnsafeEnchantment(CustomEnchants.hemorrhageEnchant, 1);
        player.getInventory().setItemInMainHand(sword);
    }

}
