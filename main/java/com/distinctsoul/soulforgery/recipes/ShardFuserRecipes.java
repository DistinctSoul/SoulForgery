package com.distinctsoul.soulforgery.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.distinctsoul.soulforgery.init.ItemInit;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.item.ItemStack;

public class ShardFuserRecipes {	
	private static final ShardFuserRecipes INSTANCE = new ShardFuserRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> fusingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static ShardFuserRecipes getInstance() {
		return INSTANCE;
	}
	
	private ShardFuserRecipes() {
		addFusingRecipe(new ItemStack(ItemInit.IGRIAN_SHARDS), new ItemStack(ItemInit.IGRIAN_SHARDS), new ItemStack(ItemInit.IGRIAN_INGOT), 5.0F);
	}

	
	public void addFusingRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
		
		if (getFusingResult(input1, input2) != ItemStack.EMPTY) return;
		
		this.fusingList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getFusingResult(ItemStack input1, ItemStack input2) {
		
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.fusingList.columnMap().entrySet()) {
			
			if (this.compareItemStacks(input1, (ItemStack)entry.getKey())) {
				
				for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
					
					if (this.compareItemStacks(input2, (ItemStack)ent.getKey())) {
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getFusingList() {
		return this.fusingList;
	}
	
	public float getFusingExperience(ItemStack stack) {
		
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}