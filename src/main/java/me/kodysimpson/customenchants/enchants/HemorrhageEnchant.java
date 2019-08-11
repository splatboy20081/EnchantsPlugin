package me.kodysimpson.customenchants.enchants;

import me.kodysimpson.customenchants.CustomEnchants;
import me.kodysimpson.customenchants.tasks.BleedOutTask;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class HemorrhageEnchant extends Enchantment implements Listener {

    public HemorrhageEnchant(String namespace){
        super(new NamespacedKey(CustomEnchants.getPlugin(), namespace));
    }

    //Define what happens for the enchant
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e){
        //Check to see if a player hurt another entity
        if (e.getDamager() instanceof Player){
            Player player = ((Player) e.getDamager()).getPlayer();
            //see if they are holding a sword with the enchant
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getEnchantments().containsKey(Enchantment.getByKey(CustomEnchants.hemorrhageEnchant.getKey()))){

                //Make the victim slowly bleed to death
                LivingEntity victim = (LivingEntity) e.getEntity();
                player.sendMessage(ChatColor.DARK_RED + "You have just hemorrhaged your target.");
                //once every 5 seconds
                BukkitTask task = new BleedOutTask(victim).runTaskTimer(CustomEnchants.getPlugin(), 0L, 100L);
            }

        }
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
    }

    @Override
    public String getName() {
        return "Throw";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 2;
    }

    @Override
    public NamespacedKey getKey() {
        return super.getKey();
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }
}
