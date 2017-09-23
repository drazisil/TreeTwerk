package com.drazisil.treetwerk;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TreeTwerk extends JavaPlugin {

    public static TreeTwerk plugin;

    @Override
    public void onEnable(){
        plugin = this;
        getServer().getPluginManager().registerEvents(new GrowListener(), this);
        getLogger().info("onEnable has been invoked!");
    }

    @Override
    public void onDisable(){
        getLogger().info("onDisable has been invoked!");
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}

