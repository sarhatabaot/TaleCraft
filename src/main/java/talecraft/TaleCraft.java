package talecraft;

import java.util.Random;
import java.util.logging.Logger;

import com.google.common.base.Predicate;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import talecraft.client.ClientProxy;
import talecraft.client.render.renderables.SelectionBoxRenderer;

@Mod(TaleCraft.MOD_ID)
public class TaleCraft {
	public static final String MOD_ID = "talecraft";
	public static final String VERSION = "1.0.0";
	public static final Logger logger = Logger.getLogger(TaleCraft.MOD_ID);
	public static final Random random = new Random();
	public static World lastVisitedWorld;
	public static Minecraft mc = Minecraft.getInstance();
	private static ClientProxy clientProxy;
	private static final String protVersion = "1.0.0";
	private static final Predicate<String> pred = (ver) -> {return ver.equals(protVersion);};
	public static SimpleChannel network = NetworkRegistry.newSimpleChannel(new ResourceLocation(TaleCraft.MOD_ID, "talecraft-net"), ()->{return protVersion;}, pred, pred);

	public TaleCraft() {
		TaleCraftRegistered.load();
		talecraft.network.NetworkRegistry.init();

		//Register loading state listeners
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverStarting);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);

		//Register event handlers
		MinecraftForge.EVENT_BUS.register(TaleCraftEvents.class);
	}
	public void serverStarting(FMLServerStartingEvent evt)
	{
		//TODO Register commands...
	}
	public void commonSetup(FMLCommonSetupEvent event) {
	}
	public void clientSetup(FMLClientSetupEvent event) {

		TaleCraft.clientProxy = new ClientProxy();
		ClientProxy.settings.init();
		logger.info("Client Setup");
		TaleCraftEvents.registerTileEntityRenderers();
		TaleCraft.asClient().getRenderer().addStaticRenderer(new SelectionBoxRenderer());

	}
	public void serverSetup(FMLDedicatedServerSetupEvent event) {

	}
	public void postInit(InterModProcessEvent event) {

	}
	public void loadComplete(FMLLoadCompleteEvent event) {
	}
	/**
	 * @return TRUE, if the client is in build-mode (aka: creative-mode), FALSE if not.
	 **/
	@OnlyIn(Dist.CLIENT)
	public static boolean isBuildMode() {
		return mc.playerController != null && mc.playerController.isInCreativeMode();
	}

	public static void setPresence(String title, String iconKey) {
		setPresence(title, "", iconKey);
	}
	public static void setPresence(String title, String subtitle, String iconKey) {
		//Unused for now
	}
	@OnlyIn(Dist.CLIENT)
	public static ClientProxy asClient() {
		// TODO Auto-generated method stub
		return TaleCraft.clientProxy;
	}





}
