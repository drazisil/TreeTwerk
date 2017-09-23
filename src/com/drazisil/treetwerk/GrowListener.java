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

import static java.lang.Integer.parseInt;
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

        if (event.isSneaking()) {
            Location location = event.getPlayer().getLocation();
            World world = location.getWorld();
            Block belowBlock = world.getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
            if (belowBlock.getType() == SAPLING) {
                logger.info("Sapping Before at " + belowBlock.getLocation().toString() + ": " + belowBlock.toString());

                BlockState state = belowBlock.getState();
                MaterialData data = state.getData();
                Sapling sap = (Sapling) data;
                sap.setIsInstantGrowable(true);
                state.setData(sap);
                state.update(true, true);

                String oldTimeSpeed = world.getGameRuleValue("randomTickSpeed");

                // Set randomTickSpeed to higher
                location.getWorld().setGameRuleValue("randomTickSpeed", "20");
                logger.info("Tick: " + world.getGameRuleValue("randomTickSpeed"));

                // Schedule randomTimeSpeed game rule to return to normal.
                Bukkit.getServer().getScheduler().runTaskLater(TreeTwerk.getPlugin(), () -> {
                    location.getWorld().setGameRuleValue("randomTickSpeed", oldTimeSpeed);
                    logger.info("Tick: " + world.getGameRuleValue("randomTickSpeed"));
                },5);//run code in run() after ticksToWait ticks

                spawnBonemealParticles(location.getWorld(), location);

                // setIsInstantGrowable(true);
                logger.info("Sapping After at " + belowBlock.getLocation().toString() + ": " + belowBlock.toString());
            } else {
            }
        }

    }

    private static void spawnBonemealParticles(World worldIn, Location pos)
    {
        Random itemRand = new Random();

        BlockState iblockstate = worldIn.getBlockAt(pos).getState();

        if (iblockstate.getType() != Material.AIR)
        {
            for (int i = 0; i < 15; ++i)
            {
                double d0 = itemRand.nextGaussian() * 0.02D;
                double d1 = itemRand.nextGaussian() * 0.02D;
                double d2 = itemRand.nextGaussian() * 0.02D;
                worldIn.spawnParticle(Particle.VILLAGER_HAPPY, (double)((float)pos.getX() + itemRand.nextFloat()), pos.getY() + (double)itemRand.nextFloat() * iblockstate.getLocation(pos).getY(), (double)((float)pos.getZ() + itemRand.nextFloat()), 0, d0, d1, d2);
            }
        }
        else
        {
            for (int i1 = 0; i1 < 15; ++i1)
            {
                double d0 = itemRand.nextGaussian() * 0.02D;
                double d1 = itemRand.nextGaussian() * 0.02D;
                double d2 = itemRand.nextGaussian() * 0.02D;
                worldIn.spawnParticle(Particle.VILLAGER_HAPPY, (double)((float)pos.getX() + itemRand.nextFloat()), pos.getY() + (double)itemRand.nextFloat() * 1.0f, (double)((float)pos.getZ() + itemRand.nextFloat()), 0, d0, d1, d2);
            }
        }
    }
}