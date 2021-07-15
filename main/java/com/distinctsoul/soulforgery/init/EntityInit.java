package com.distinctsoul.soulforgery.init;

import com.distinctsoul.soulforgery.Main;
import com.distinctsoul.soulforgery.entity.EntityWraith;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {

	public static void registerEntities() {
		registerEntity("wraith", EntityWraith.class, Main.ENTITY_WRAITH, 16, 3289650, 6250335);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
		EntityRegistry.addSpawn(EntityWraith.class, 70, 1, 2, EnumCreatureType.MONSTER, Biomes.PLAINS);
	}
}
