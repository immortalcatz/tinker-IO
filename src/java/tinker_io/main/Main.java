package tinker_io.main;

//import tconstruct.plugins.mfr.TinkerMFR;
//import tconstruct.plugins.te4.TinkerTE4;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerTools;
import tinker_io.TileEntity.fim.FIMTileEntity;
import tinker_io.blocks.FuelInputMachine;
import tinker_io.handler.GuiHandler;
import tinker_io.handler.SORecipes;
//import tinker_io.mainRegistry.PartRegister;
import tinker_io.packet.PacketDispatcher;
//import tinker_io.plugins.ee3.EE3Main;
//import tinker_io.plugins.fmp.MainFmp;
//import tinker_io.plugins.waila.MainWaila;
import tinker_io.proxy.ClientProxy;
import tinker_io.proxy.ServerProxy;
import tinker_io.registry.BlockRegistry;
import tinker_io.registry.BlockRenderRegister;
import tinker_io.registry.ItemRegistry;
import tinker_io.registry.ItemRenderRegister;
import tinker_io.registry.RecipeRegistry;
import slimeknights.mantle.pulsar.config.ForgeCFG;
import slimeknights.mantle.pulsar.control.PulseManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(
		modid = Main.MODID,
		version = Main.VERSION,
		name = Main.Name,
		dependencies="required-after:Forge@[11.15.1.1722,);"
//				+ "required-after:TConstruct@[1.8.9-2.0.1,);"
				+ "after:NotEnoughItems;"
				+ "after:Waila;"
				+ "after:ForgeMultipart;"
				+ "after:EE3;")
public class Main
{
    public static final String MODID = "tinker_io";
    public static final String VERSION = "beta 1.4.2 a2-0001";
    public static final String Name = "Tinker I/O";
    
    public static boolean iguanas_support;
    
    //Proxy
    @SidedProxy(modId=Main.MODID, clientSide="tinker_io.proxy.ClientProxy", serverSide="tinker_io.proxy.ServerProxy")
	public static ServerProxy proxy;
    
    //MOD
    @Instance(Main.MODID)
    public static Main instance;
    
	
    //Creative Tabs
    public static CreativeTabs TinkerIOTabs = new CreativeTabs("TinkerIO_Tabs"){
    		public Item getTabIconItem() {
    			return TinkerTools.largePlate;
    		}
        };
        
    /**
     * read your config file, create Blocks, Items, etc. and register them with the GameRegistry.
     * @param event
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	  	
    	config.load();
    	
    	Property iguanas_support_cofg = config.get(Configuration.CATEGORY_GENERAL, "iguanas_support", true);
    	iguanas_support = config.get(Configuration.CATEGORY_GENERAL, "iguanas_support", true).getBoolean(true);
    	
    	config.save();
    	
    	if (!Loader.isModLoaded("IguanaTweaksTConstruct")) {
    		iguanas_support = false;
    	}
    	
		//magma heater
    	BlockRegistry.mainRegistry();
    	ItemRegistry.mainRegistry();

    	PacketDispatcher.registerPackets();
    	
		proxy.registerTileEntities();
		
		//MainFmp.startPlugin(); // Developing...
//		*** no waila
//		MainWaila.startPlugin();
//    	***
    }
    
    /**
     * build up data structures, add Crafting Recipes and register new handler
     * @param event
     */
	@Mod.EventHandler
	public static void init(FMLInitializationEvent event)
	{
		BlockRenderRegister.registerBlockRenderer();
		ItemRenderRegister.registerItemRenderer();
    	RecipeRegistry.mainRegistry();
	}
    
	/**
	 * communicate with other mods and adjust your setup based on this
	 * @param event
	 */
	  @Mod.EventHandler
	  public void postInit(FMLPostInitializationEvent event)
	  {
			proxy.registerNetworkStuff();
	  }
}