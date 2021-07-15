package com.distinctsoul.soulforgery.containers.slots;

import com.distinctsoul.soulforgery.tileentity.TileEntityShardFuser;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotShardFuserFuel extends SlotItemHandler {
	
	public SlotShardFuserFuel(IItemHandler itemHandler, int index, int x, int y) {
		super(itemHandler, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityShardFuser.isItemSpiritFuel(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}
}
