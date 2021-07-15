package com.distinctsoul.soulforgery.util.handlers;

import com.distinctsoul.soulforgery.Main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler {
	
	public static final ResourceLocation WRAITH = LootTableList.register(new ResourceLocation(Main.MODID, "wraith"));
}
