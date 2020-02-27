package com.distinctsoul.soulforgery;

import com.distinctsoul.soulforgery.blocks.BlockIgrianOre;
import com.distinctsoul.soulforgery.blocks.machines.BlockShardFuser;
import com.distinctsoul.soulforgery.init.ModBlocks;
import com.distinctsoul.soulforgery.materials.SoulForgeryMaterials;
import com.distinctsoul.soulforgery.util.handlers.GuiHandler;
import com.distinctsoul.soulforgery.util.handlers.TileEntityHandler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber (modid = Main.MODID)
public class EventSubscriber {
	
	@SubscribeEvent
	public static void registerBlocks(Register<Block> event) {
		
		TileEntityHandler.registerTileEntities();
		
		final Block[] blocks = {
						new BlockIgrianOre().setRegistryName("igrian_ore").setTranslationKey(Main.MODID + "." + "igrian_ore"),
						new BlockShardFuser().setRegistryName("shard_fuser").setTranslationKey(Main.MODID + "." + "shard_fuser"),
		};
		
		event.getRegistry().registerAll(blocks);
	}
	
	@SubscribeEvent
	public static void registerItems(Register<Item> event) {
			
			final Item[] items = {
						new Item().setRegistryName("tainted_soul").setTranslationKey(Main.MODID + "." + "tainted_soul").setCreativeTab(Main.SOUL_FORGERY),
						new Item().setRegistryName("igrian_shards").setTranslationKey(Main.MODID + "." + "igrian_shards").setCreativeTab(Main.SOUL_FORGERY),
						new Item().setRegistryName("igrian_ingot").setTranslationKey(Main.MODID + "." + "igrian_ingot").setCreativeTab(Main.SOUL_FORGERY),
						new ItemSword(SoulForgeryMaterials.IGRIAN_TOOL).setRegistryName("igrian_sword").setTranslationKey(Main.MODID + "." + "igrian_sword").setCreativeTab(Main.SOUL_FORGERY),	
			};
			
			final Item[] itemBlocks = {
			new ItemBlock(ModBlocks.IGRIAN_ORE).setRegistryName(ModBlocks.IGRIAN_ORE.getRegistryName()),
			new ItemBlock(ModBlocks.SHARD_FUSER).setRegistryName(ModBlocks.SHARD_FUSER.getRegistryName()),
		};
			
			event.getRegistry().registerAll(items);
			event.getRegistry().registerAll(itemBlocks);
	}
	
}
