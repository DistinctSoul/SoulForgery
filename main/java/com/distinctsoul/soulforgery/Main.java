package com.distinctsoul.soulforgery;

import com.distinctsoul.soulforgery.init.EntityInit;
import com.distinctsoul.soulforgery.tabs.SoulForgeryTab;
import com.distinctsoul.soulforgery.util.IProxy;
import com.distinctsoul.soulforgery.util.handlers.GuiHandler;
import com.distinctsoul.soulforgery.util.handlers.SFEventHandler;
import com.distinctsoul.soulforgery.util.handlers.SFSoundHandler;
import com.distinctsoul.soulforgery.world.ModWorldGen;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main {

	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Main.CLIENT_PROXY_CLASS, serverSide = Main.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	public static final String MODID = "soulforgery";
	public static final String NAME = "Soul Forgery";
	public static final String VERSION = "0.0.1";
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";
	public static final String CLIENT_PROXY_CLASS = "com.distinctsoul.soulforgery.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.distinctsoul.soulforgery.proxy.ServerProxy";
	
	public static final int GUI_SHARD_FUSER = 1;
	public static final int ENTITY_WRAITH = 20;
	
	public static final String CLIENT = "com.distinctsoul.soulforgergy.proxy.ClientProxy";
	public static final String SERVER = "com.distinctsoul.soulforgergy.proxy.ServerProxy";
	
	public static final CreativeTabs SOUL_FORGERY = (CreativeTabs) new SoulForgeryTab();
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		System.out.println(Main.NAME + " is loading!");
		proxy.preInit(event);
		
		MinecraftForge.EVENT_BUS.register(new SFEventHandler());
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
		EntityInit.registerEntities();
		SFSoundHandler.registerSounds();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.init(event);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		
	}
}
