package com.distinctsoul.soulforgery.containers.slots;

import com.distinctsoul.soulforgery.tileentity.TileEntityShardFuser;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotShardFuserFuel extends Slot {
	
	public SlotShardFuserFuel(IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
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
