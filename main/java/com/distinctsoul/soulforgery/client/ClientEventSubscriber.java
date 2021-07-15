package com.distinctsoul.soulforgery.client;

import com.distinctsoul.soulforgery.Main;
import com.distinctsoul.soulforgery.init.BlockInit;
import com.distinctsoul.soulforgery.init.ItemInit;
import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

import static net.minecraftforge.fml.relauncher.Side.CLIENT;

@Mod.EventBusSubscriber(modid = Main.MODID, value = CLIENT)
public final class ClientEventSubscriber {
	
	private static final Logger LOGGER = LogManager.getLogger();
	private static final String DEFAULT_VARIANT = "normal";
	
	@SubscribeEvent
	public static void onRegisterModelsEvent(@Nonnull final ModelRegistryEvent event) {
		
		ForgeRegistries.BLOCKS.getValuesCollection().stream()
						.filter(block -> block.getRegistryName().getNamespace().equals(Main.MODID))
						.forEach(ClientEventSubscriber::registerItemBlockModel);
		
		registerItemBlockModel(BlockInit.IGRIAN_ORE);
		registerItemBlockModel(BlockInit.SHARD_FUSER);
		
		registerItemModel(ItemInit.IGRIAN_INGOT);
		registerItemModel(ItemInit.IGRIAN_SHARDS);
		registerItemModel(ItemInit.TAINTED_SOUL);
		registerItemModel(ItemInit.CORRUPTED_SOUL);
		registerItemModel(ItemInit.IGRIAN_SWORD);
		registerItemModel(ItemInit.LIVING_CLOTH);
		
		LOGGER.debug("Registered models");
	}
	
	private static void registerItemModel(@Nonnull final Item item) {
		Preconditions.checkNotNull(item, "Item cannot be null!");
		final ResourceLocation registryName = item.getRegistryName();
		Preconditions.checkNotNull(registryName, "Item Registry Name cannot be null!");
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), DEFAULT_VARIANT));
	}

	private static void registerItemBlockModel(@Nonnull final Block block) {
		Preconditions.checkNotNull(block, "Block cannot be null!");
		final ResourceLocation registryName = block.getRegistryName();
		Preconditions.checkNotNull(registryName, "Block Registry Name cannot be null!");
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), DEFAULT_VARIANT));
	}
	
	@SubscribeEvent
	public static void onTextureStitchEvent(@Nonnull final TextureStitchEvent event) {
		// Register texture for Shard Fuser
		final ResourceLocation registryName = BlockInit.SHARD_FUSER.getRegistryName();
		event.getMap().registerSprite(new ResourceLocation(registryName.getNamespace(), "blocks/" + registryName.getPath()));
	}
}