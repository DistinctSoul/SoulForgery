package com.distinctsoul.soulforgery.entity.render;

import com.distinctsoul.soulforgery.Main;
import com.distinctsoul.soulforgery.entity.EntityWraith;
import com.distinctsoul.soulforgery.entity.model.ModelWraith;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderWraith extends RenderLiving<EntityWraith> {
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID + ":textures/entity/wraith.png");
	
	public RenderWraith(RenderManager rendermanager) {
		super(rendermanager, new ModelWraith(), 0.5F);
	}
	
	protected ResourceLocation getEntityTexture(EntityWraith entity) {
		return TEXTURES;
		
	};
	
	@Override
	protected void applyRotations(EntityWraith entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
	}
}
