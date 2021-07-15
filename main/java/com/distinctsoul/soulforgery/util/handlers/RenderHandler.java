package com.distinctsoul.soulforgery.util.handlers;

import com.distinctsoul.soulforgery.entity.EntityWraith;
import com.distinctsoul.soulforgery.entity.render.RenderWraith;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
	
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityWraith.class, new IRenderFactory<EntityWraith>() {

			@Override
			public Render<? super EntityWraith> createRenderFor(RenderManager manager) {
				return new RenderWraith(manager);
			}
		});
	}
}
