package com.distinctsoul.soulforgery.items.tools;

import com.distinctsoul.soulforgery.Main;
import com.distinctsoul.soulforgery.init.ModItems;
import com.distinctsoul.soulforgery.util.IProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ItemIgrianSword extends ItemSword {
	
	public ItemIgrianSword(ToolMaterial material) {
		
		super(material);
		setCreativeTab(Main.SOUL_FORGERY);
	}

	public void registerModels() {
		
		IProxy.registerItemRenderer(this, 0, "inventory");
	}
}
