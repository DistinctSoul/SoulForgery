package com.distinctsoul.soulforgery.util.handlers;

import com.distinctsoul.soulforgery.Main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SFSoundHandler {
	public static SoundEvent ENTITY_WRAITH_AMBIENT;
	public static SoundEvent ENTITY_WRAITH_HURT, ENTITY_WRAITH_DEATH;
	
	public static void registerSounds() {
		ENTITY_WRAITH_AMBIENT = registerSound("entity.wraith.ambient");
		ENTITY_WRAITH_HURT = registerSound("entity.wraith.hurt");
		ENTITY_WRAITH_DEATH = registerSound("entity.wraith.death");
	}
	
	private static SoundEvent registerSound(String name) {
		ResourceLocation location = new ResourceLocation(Main.MODID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
