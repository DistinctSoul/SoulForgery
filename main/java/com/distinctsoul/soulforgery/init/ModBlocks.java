package com.distinctsoul.soulforgery.init;

import java.util.ArrayList;
import java.util.List;

import com.distinctsoul.soulforgery.Main;
import com.distinctsoul.soulforgery.blocks.BlockIgrianOre;
import com.distinctsoul.soulforgery.blocks.machines.BlockShardFuser;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(Main.MODID)
public class ModBlocks {
	
	// Blocks
	public static final BlockIgrianOre IGRIAN_ORE = null;
	
	public static final BlockShardFuser SHARD_FUSER = null;
}