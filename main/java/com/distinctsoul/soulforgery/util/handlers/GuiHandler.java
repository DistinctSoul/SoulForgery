package com.distinctsoul.soulforgery.util.handlers;

import com.distinctsoul.soulforgery.Main;
import com.distinctsoul.soulforgery.containers.ContainerShardFuser;
import com.distinctsoul.soulforgery.guis.GuiShardFuser;
import com.distinctsoul.soulforgery.tileentity.TileEntityShardFuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Main.GUI_SHARD_FUSER) return new ContainerShardFuser(player.inventory, (TileEntityShardFuser)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Main.GUI_SHARD_FUSER) return new GuiShardFuser(player.inventory, (TileEntityShardFuser)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}
}
