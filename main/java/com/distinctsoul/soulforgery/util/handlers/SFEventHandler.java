package com.distinctsoul.soulforgery.util.handlers;

import java.util.Random;

import com.distinctsoul.soulforgery.init.ModItems;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class SFEventHandler {
	
	@SubscribeEvent
	public void onEvent(LivingDropsEvent event) {
		
		if (event.getEntityLiving() instanceof EntityZombie) {
			
			if (event.getSource().getTrueSource() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
				Random rand = new Random();
				
				if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == ModItems.IGRIAN_SWORD) {
					event.getEntityLiving().dropItem(ModItems.TAINTED_SOUL, rand.nextInt(2));
				}
			}
		}
	}
}