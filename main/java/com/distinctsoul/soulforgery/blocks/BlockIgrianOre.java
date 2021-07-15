package com.distinctsoul.soulforgery.blocks;

import java.util.Random;

import com.distinctsoul.soulforgery.Main;
import com.distinctsoul.soulforgery.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockIgrianOre extends Block {
	
	public BlockIgrianOre() {
		super(Material.ROCK);
		setCreativeTab(Main.SOUL_FORGERY);
		setSoundType(SoundType.STONE);
		setHardness(4.0F);
		setResistance(25.0F);
		setHarvestLevel("pickaxe", 3);
		setLightLevel(0.1F);
		// setLightOpacity(1);
		// setBlockUnbreakable();
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemInit.IGRIAN_SHARDS;
	}
	
	@Override
	public int quantityDropped(Random random) {	
		return 1 + random.nextInt(2);
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		return this.quantityDropped(random) + random.nextInt(fortune + 1);
	}
}
