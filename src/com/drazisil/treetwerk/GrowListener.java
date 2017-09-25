package com.drazisil.treetwerk;

/**
 * Created by jwbec on 7/16/2017.
 */

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Sapling;

import java.util.Random;
import java.util.logging.Logger;

import static org.bukkit.Material.AIR;
import static org.bukkit.Material.SAPLING;

public final class GrowListener implements Listener {


    private final Logger logger = Logger.getLogger("MineCraft");

    @EventHandler
    public void SneakListener(PlayerToggleSneakEvent event) {

        // Is the player sneaking?
        if (event.isSneaking()) {

            // Get world that event occurred in
            World playerWorld = event.getPlayer().getWorld();

            // Get the block that player is sneaking on
            Block sneakBlock = playerWorld.getBlockAt(event.getPlayer().getLocation());

            // TODO: Search for saplings in a radius

            // Get block to the north
            Block sneakBlockNorth = sneakBlock.getRelative(BlockFace.NORTH);

            if (sneakBlockNorth.getType() == SAPLING) {

                // TODO: Don't always grow

                growTreeAt(playerWorld, sneakBlockNorth);
            }
        }

    }

    private void growTreeAt(World world, Block block) {
        // logger.info("Sappling Before at " + sneakBlockNorth.getLocation().toString() + ": " + sneakBlockNorth.toString());

        BlockState blockState = block.getState();
        MaterialData sneakBlockNorthData = blockState.getData();
        Sapling blockNorthData = (Sapling) sneakBlockNorthData;

        // Get block above
        Block aboveBlock = block.getRelative(BlockFace.UP);

        // Spawn bonemeal particles
        spawnBonemealParticles(world, aboveBlock.getLocation(), 0);

        blockState.setType(AIR);
        blockState.update(true);

        // Get what kind of sapling it is
        String species = ((Sapling) sneakBlockNorthData).getSpecies().name();

        switch (species) {
            case "BIRCH":
                world.generateTree(block.getLocation(), TreeType.BIRCH);
                break;
            case "ACACIA":
                world.generateTree(block.getLocation(), TreeType.ACACIA);
                break;
            default:
                logger.info(species);
                world.generateTree(block.getLocation(), TreeType.TALL_REDWOOD);

        }

        // logger.info("Sappling After at " + sneakBlockNorth.getLocation().toString() + ": " + sneakBlockNorth.toString());

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