package me.kodysimpson.customenchants;

import me.kodysimpson.customenchants.enchants.HemorrhageEnchant;
import me.kodysimpson.customenchants.listeners.JoinListener;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public final class CustomEnchants extends JavaPlugin {

    //An easier way to provide an instance of the plugin to other classes
    public static CustomEnchants PLUGIN;

    //Custom enchantments list
    public static ArrayList<Enchantment> custom_enchants = new ArrayList<>();
    public static HemorrhageEnchant hemorrhageEnchant;
    //more enchants under here

    @Override
    public void onEnable() {
        PLUGIN = this;

        //Create an instance of the custom enchants
        hemorrhageEnchant = new HemorrhageEnchant("hemorrhage");

        //Add each enchantment to list so we can use
        custom_enchants.add(hemorrhageEnchant);

        //Register custom enchantments
        registerEnchantment(hemorrhageEnchant);

        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        //Register listener(s) in the enchantments
        this.getServer().getPluginManager().registerEvents(hemorrhageEnchant, this);
    }

    @Override
    public void onDisable() {
        // Disable the Power enchantment
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            for (Enchantment enchantment : custom_enchants){
                if(byKey.containsKey(enchantment.getKey())) {
                    byKey.remove(enchantment.getKey());
                }
            }

            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            for (Enchantment enchantment : custom_enchants){
                if(byName.containsKey(enchantment.getName())) {
                    byName.remove(enchantment.getName());
                }
            }
        } catch (Exception ignored) { }
    }

    //Load custom enchantments
    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            // It's been registered!
        }
    }

    public static CustomEnchants getPlugin(){
        return PLUGIN;
    }

}
