package com.distinctsoul.soulforgery.containers.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotShardFuserOutput extends SlotItemHandler {
	public SlotShardFuserOutput(EntityPlayer player, IItemHandler itemHandler, int index, int x, int y) {
		super(itemHandler, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
	
	@Override
	public ItemStack decrStackSize(int amount) {
        
		if (this.getHasStack()) {
            Math.min(amount, this.getStack().getCount());
        }

        return super.decrStackSize(amount);
    }
	
	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
		this.onCrafting(stack);
		super.onTake(thePlayer, stack);
		return stack;
	}
}
