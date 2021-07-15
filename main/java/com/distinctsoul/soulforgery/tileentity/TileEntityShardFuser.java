package com.distinctsoul.soulforgery.tileentity;

import com.distinctsoul.soulforgery.blocks.machines.BlockShardFuser;
import com.distinctsoul.soulforgery.recipes.ShardFuserRecipes;
import com.distinctsoul.soulforgery.util.handlers.OutputItemStackHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityShardFuser extends TileEntity implements ITickable {
	protected ItemStackHandler outputSlot;
	private ItemStackHandler handler = new ItemStackHandler(4);
	private String customName;
	
	private int chargeTime;
	private int currentChargeTime;
	private int fuseTime;
	private int totalFuseTime = 200;
	
	public TileEntityShardFuser() {
		outputSlot = new ItemStackHandler();
		new OutputItemStackHandler(outputSlot);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		else return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.shard_fuser");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.chargeTime = compound.getInteger("ChargeTime");
		this.fuseTime = compound.getInteger("FuseTime");
		this.totalFuseTime = compound.getInteger("FuseTimeTotal");
		this.currentChargeTime = getItemChargeTime((ItemStack)this.handler.getStackInSlot(2));
		
		if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("ChargeTime", (short)this.chargeTime);
		compound.setInteger("FuseTime", (short)this.fuseTime);
		compound.setInteger("FuseTimeTotal", (short)this.totalFuseTime);
		compound.setTag("Inventory", this.handler.serializeNBT());
		
		if (this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	public boolean isActive() {
		return this.chargeTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isActive(TileEntityShardFuser tileentity) {
		return tileentity.getField(0) > 0;
	}
	
	public void update() {
		boolean flag = this.isActive();
		boolean flag1 = false;
		
		if (this.isActive()) this.chargeTime--;
		
		if (!this.world.isRemote) {
			ItemStack spiritFuel = this.handler.getStackInSlot(2);
			
			if (this.isActive() || !spiritFuel.isEmpty() && !this.handler.getStackInSlot(0).isEmpty() || this.handler.getStackInSlot(1).isEmpty()) {
				
				if (!this.isActive() && this.canFuse()) {
					this.chargeTime = getItemChargeTime(spiritFuel);
					this.currentChargeTime = this.chargeTime;
					
					if (this.isActive() && !spiritFuel.isEmpty()) {
						Item item = spiritFuel.getItem();
						spiritFuel.shrink(1);
						
						if (spiritFuel.isEmpty()) {
							ItemStack item1 = item.getContainerItem(spiritFuel);
							this.handler.setStackInSlot(2, item1);
						}
					}
				}
			}
			
			if (this.isActive() && this.canFuse()) {
				this.fuseTime++;
				
				if (this.fuseTime == this.totalFuseTime) {
					
					this.fuseTime = 0;
					this.totalFuseTime = this.getFuseTime((ItemStack)this.handler.getStackInSlot(0), (ItemStack)this.handler.getStackInSlot(1));
					this.fuseItem();
				}
			}
			else this.fuseTime = 0;
		}
			
		else if (!this.isActive() && this.fuseTime > 0) {
			this.fuseTime = MathHelper.clamp(this.fuseTime - 2, 0, this.totalFuseTime);
		}
			
		if (flag != this.isActive()) {
			flag1 = true;
			BlockShardFuser.setState(this.isActive(), this.world, this.pos);
		}
		
		if (flag1) this.markDirty();
	}
	
	public int getFuseTime(ItemStack input1, ItemStack input2) {
		return 200;
	}
	
	private boolean canFuse() {
		
		if (((ItemStack)this.handler.getStackInSlot(0)).isEmpty() || ((ItemStack)this.handler.getStackInSlot(1)).isEmpty()) return false;
		else {
			ItemStack result = ShardFuserRecipes.getInstance().getFusingResult((ItemStack)this.handler.getStackInSlot(0), (ItemStack)this.handler.getStackInSlot(1));
			
			if (result.isEmpty()) return false;
			else {
				ItemStack output = (ItemStack)this.handler.getStackInSlot(3);
				if (output.isEmpty()) return true;
				if (!output.isItemEqual(result)) return false;
				int resCount = output.getCount() + result.getCount();
				return resCount <= 64 && resCount <= output.getMaxStackSize();
			}
		}
	}
	
	public void fuseItem() {
		
		if (this.canFuse()) {
			ItemStack input1 = (ItemStack)this.handler.getStackInSlot(0);
			ItemStack input2 = (ItemStack)this.handler.getStackInSlot(1);
			ItemStack result = ShardFuserRecipes.getInstance().getFusingResult(input1, input2);
			ItemStack output = (ItemStack)this.handler.getStackInSlot(3);
			
			if (output.isEmpty()) this.handler.setStackInSlot(3, result.copy());
			else if (output.getItem() == result.getItem()) output.grow(result.getCount());
			
			input1.shrink(1);
			input2.shrink(1);
		}
	}
	
	public static int getItemChargeTime(ItemStack spiritFuel) {
		
		if (spiritFuel.isEmpty()) return 0;
		else {
			Item item = spiritFuel.getItem();
			
			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
				Block block = Block.getBlockFromItem(item);
				
				if (block == Blocks.GLOWSTONE) return 800;
				if (block == Blocks.REDSTONE_LAMP) return 800;
			}
			
			if (item == Items.GLOWSTONE_DUST) return 200;
		}
		int chargeTime = getItemChargeTime(spiritFuel);
		return chargeTime;
	}
	
	public static boolean isItemSpiritFuel(ItemStack spiritFuel) {
		return getItemChargeTime(spiritFuel) > 0;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	public int getField(int id) {
		
		switch(id) {
		case 0:
			return this.chargeTime;
		case 1:
			return this.currentChargeTime;
		case 2:
			return this.fuseTime;
		case 3:
			return this.totalFuseTime;
		default:
			return 0;
		}
	}
	
	public void setField(int id, int value) {
		
		switch(id) {
		case 0:
			this.chargeTime = value;
			break;
		case 1:
			this.currentChargeTime = value;
			break;
		case 2:
			this.fuseTime = value;
			break;
		case 3:
			this.totalFuseTime = value;
		}
	}
	
	public int getFieldCount() {
		return 4;
	}
}
