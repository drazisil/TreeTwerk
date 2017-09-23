package com.drazisil.treetwerk;

/**
 * Created by jwbec on 7/16/2017.
 */
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

public final class EventListener implements Listener {

    public final Logger logger = Logger.getLogger("MineCraft");

    @EventHandler
    public void EventListener(PlayerEvent event) {

        logger.info(event.getEventName());

    }
}