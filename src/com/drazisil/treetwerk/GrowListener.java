package com.drazisil.treetwerk;

/**
 * Created by jwbec on 7/16/2017.
 */
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Sapling;

import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

import static org.bukkit.Material.SAPLING;

public final class GrowListener implements Listener {

    public final Logger logger = Logger.getLogger("MineCraft");

    @EventHandler
    public void GrowEvent(StructureGrowEvent event) {

        // Set randomTickSpeed to 3

        logger.info("Grow at " + event.getLocation().toString() + ", Bonemeal?: " + String.valueOf(event.isFromBonemeal()));

    }

    @EventHandler
    public void EventListener(PlayerToggleSneakEvent event) {

        Random itemRand = new Random();

        if (event.isSneaking()) {
            Location location = event.getPlayer().getLocation();
            Block belowBlock = event.getPlayer().getWorld().getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
            if (belowBlock.getType() == SAPLING) {
                logger.info("Sapping Before at " + belowBlock.getLocation().toString() + ": " + belowBlock.toString());

                BlockState state = belowBlock.getState();
                MaterialData data = state.getData();
                Sapling sap = (Sapling) data;
                sap.setIsInstantGrowable(true);
                state.setData(sap);
                state.update(true, true);

                // Set randomTickSpeed to higher

                // Spawn happyVillager particles
                int amount = 15;
                for (int i1 = 0; i1 < amount; ++i1)
                {
                    double d0 = itemRand.nextGaussian() * 0.02D;
                    double d1 = itemRand.nextGaussian() * 0.02D;
                    double d2 = itemRand.nextGaussian() * 0.02D;
                    event.getPlayer().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location.getX() + d0, location.getBlockY() + d1, location.getBlockZ() + d2, 0);
                }



                // setIsInstantGrowable(true);
                logger.info("Sapping After at " + belowBlock.getLocation().toString() + ": " + belowBlock.toString());
            } else {
            }
        }

    }
}