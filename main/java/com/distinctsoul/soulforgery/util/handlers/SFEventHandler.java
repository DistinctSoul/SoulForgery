package com.distinctsoul.soulforgery.util.handlers;

import java.util.Random;

import com.distinctsoul.soulforgery.entity.EntityWraith;
import com.distinctsoul.soulforgery.init.ItemInit;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class SFEventHandler {
	
	@SubscribeEvent
	public void onEvent(LivingDropsEvent event) {
		
		if (event.getSource().getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			Random rand = new Random();
			int nextRand = rand.nextInt(3);
		
			if (event.getEntityLiving() instanceof EntityMob) {
				
				if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == ItemInit.IGRIAN_SWORD) {
					
					if (!(event.getEntityLiving() instanceof EntityWraith)) {
						
						if (nextRand == 2) {
							event.getEntityLiving().dropItem(ItemInit.TAINTED_SOUL, 1);
						}
					}
					else if ((event.getEntityLiving() instanceof EntityWraith)) {
						
						if (nextRand == 2) {
							event.getEntityLiving().dropItem(ItemInit.CORRUPTED_SOUL, 1);
						}
					}
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		RenderHandler.registerEntityRenders();
	}
}