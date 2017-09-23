package com.drazisil.treetwerk;

import org.bukkit.plugin.java.JavaPlugin;

public class TreeTwerk extends JavaPlugin {

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new GrowListener(), this);
        getLogger().info("onEnable has been invoked!");
    }

    @Override
    public void onDisable(){
        getLogger().info("onDisable has been invoked!");
    }
}

