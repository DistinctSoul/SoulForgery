package com.distinctsoul.soulforgery.tabs;

import com.distinctsoul.soulforgery.Main;
import com.distinctsoul.soulforgery.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SoulForgeryTab extends CreativeTabs {
	
	public SoulForgeryTab() {
		super(Main.MODID);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.IGRIAN_INGOT);
	}
}
