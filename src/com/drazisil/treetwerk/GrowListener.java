package com.drazisil.treetwerk;

/**
 * Created by jwbec on 7/16/2017.
 */
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Sapling;

import java.util.Random;
import java.util.logging.Logger;

import static org.bukkit.Material.AIR;
import static org.bukkit.Material.SAPLING;

public final class GrowListener implements Listener {



    public final Logger logger = Logger.getLogger("MineCraft");

    @EventHandler
    public void GrowEvent(StructureGrowEvent event) {

        // Set randomTickSpeed to 3

        logger.info("Grow at " + event.getLocation().toString() + ", Bonemeal?: " + String.valueOf(event.isFromBonemeal()));

    }

    @EventHandler
    public void SneakListener(PlayerToggleSneakEvent event) {

        // Is the player sneaking?
        if (event.isSneaking()) {

            // Get world that event occured in
            World playerWorld = event.getPlayer().getWorld();

            // Get get the block that player is sneaking on
            Block sneakBlock = playerWorld.getBlockAt(event.getPlayer().getLocation());


            // Get block to the north
            Block sneakBlockNorth = sneakBlock.getRelative(BlockFace.NORTH);

            // Get block above player's feet
            Block aboveSneakBlockNorth = sneakBlockNorth.getRelative(BlockFace.UP);

            if (sneakBlockNorth.getType() == SAPLING) {
                logger.info("Sappling Before at " + sneakBlockNorth.getLocation().toString() + ": " + sneakBlockNorth.toString());

                BlockState sneakBlockNorthState = sneakBlockNorth.getState();
                MaterialData sneakBlockNorthData = sneakBlockNorthState.getData();
                Sapling saplingNorthData = (Sapling) sneakBlockNorthData;
                saplingNorthData.setIsInstantGrowable(true);
                sneakBlockNorthState.setData(saplingNorthData);
                sneakBlockNorthState.update();

                // Spawn bonemeal particles
                spawnBonemealParticles(playerWorld, aboveSneakBlockNorth.getLocation(), 0);

                // Update the block above the block the player is sneaking on
                BlockState aboveSneakBlockState = aboveSneakBlockNorth.getState();
                aboveSneakBlockState.setType(AIR);
                aboveSneakBlockState.update(true, true);

                // setIsInstantGrowable(true);
                logger.info("Sappling After at " + sneakBlockNorth.getLocation().toString() + ": " + sneakBlockNorth.toString());
            }
        }

    }

    private static void spawnBonemealParticles(World worldIn, Location pos, int amount)
    {
        if (amount == 0)
        {
            amount = 15;
        }

        Random itemRand = new Random();

        BlockState iblockstate = worldIn.getBlockAt(pos).getState();

        if (iblockstate.getType() != Material.AIR)
        {
            for (int i = 0; i < amount; ++i)
            {
                double d0 = itemRand.nextGaussian() * 0.02D;
                double d1 = itemRand.nextGaussian() * 0.02D;
                double d2 = itemRand.nextGaussian() * 0.02D;
                worldIn.spawnParticle(Particle.VILLAGER_HAPPY, (double)((float)pos.getX() + itemRand.nextFloat()), pos.getY() + (double)itemRand.nextFloat() * pos.getY() + 1, (double)((float)pos.getZ() + itemRand.nextFloat()), 1, d0, d1, d2);
            }
        }
        else
        {
            for (int i1 = 0; i1 < amount; ++i1)
            {
                double d0 = itemRand.nextGaussian() * 0.02D;
                double d1 = itemRand.nextGaussian() * 0.02D;
                double d2 = itemRand.nextGaussian() * 0.02D;
                worldIn.spawnParticle(Particle.VILLAGER_HAPPY, (double)((float)pos.getX() + itemRand.nextFloat()), (double)pos.getY() + (double)itemRand.nextFloat() * 1.0f, (double)((float)pos.getZ() + itemRand.nextFloat()), 1, d0, d1, d2);
            }
        }
    }
}