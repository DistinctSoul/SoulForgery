package com.distinctsoul.soulforgery.guis;

import com.distinctsoul.soulforgery.Main;
import com.distinctsoul.soulforgery.containers.ContainerShardFuser;
import com.distinctsoul.soulforgery.tileentity.TileEntityShardFuser;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiShardFuser extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID + ":textures/gui/shard_fuser.png");
	private final InventoryPlayer player;
	private final TileEntityShardFuser tileentity;
	
	public GuiShardFuser(InventoryPlayer player, TileEntityShardFuser tileentity) {
		super(new ContainerShardFuser(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 8, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if (TileEntityShardFuser.isActive(tileentity)) {
			int k = this.getChargeLeftScaled(18);
			this.drawTexturedModalRect(this.guiLeft + 19, this.guiTop + 40 + 18 - k, 176, 18 - k, 14, k + 1);
		}
		
		int l = this.getFuseProgressScaled(36);
		this.drawTexturedModalRect(this.guiLeft + 75, this.guiTop + 22, 176, 19, l + 1, 28);
	}
	
	private int getChargeLeftScaled(int pixels) {
		int i = this.tileentity.getField(1);
		if (i == 0) i = 200;
		return this.tileentity.getField(0) * pixels / i;
	}
	
	private int getFuseProgressScaled(int pixels) {
		int i = this.tileentity.getField(2);
		int j = this.tileentity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
