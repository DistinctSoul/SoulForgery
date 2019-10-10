package com.distinctsoul.soulforgery.util.handlers;

import com.distinctsoul.soulforgery.Main;
import com.distinctsoul.soulforgery.tileentity.TileEntityShardFuser;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityShardFuser.class, ":shard_fuser");
	}
}
