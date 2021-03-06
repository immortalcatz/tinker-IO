package tinker_io.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import tinker_io.main.Main;
import tinker_io.mainRegistry.ItemRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class Upgrade extends Item {
	public Upgrade(String unlocalizedName){
		super();
		setUnlocalizedName(unlocalizedName);
		setCreativeTab(Main.TinkerIOTabs);
		//setTextureName(Main.MODID + ":" + "Upgrade");
		setHasSubtypes(true);
		setMaxStackSize(8);
	}
	
	public IIcon[] icons = new IIcon[7];
	
	@Override
	public void registerIcons(IIconRegister reg) {
		this.icons[0] = reg.registerIcon(Main.MODID + ":" + "UPG_Base");
		this.icons[1] = reg.registerIcon(Main.MODID + ":" + "UPG_slot1");
		this.icons[2] = reg.registerIcon(Main.MODID + ":" + "UPG_slot2");
		this.icons[3] = reg.registerIcon(Main.MODID + ":" + "UPG_slot3");
		this.icons[4] = reg.registerIcon(Main.MODID + ":" + "UPG_slot4");
		this.icons[5] = reg.registerIcon(Main.MODID + ":" + "UPG_Redstone");
		this.icons[6] = reg.registerIcon(Main.MODID + ":" + "UPG_slot_infinity");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
	    if (meta > 6)
	        meta = 0;

	    return this.icons[meta];
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 7; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}
	
	public static boolean isShiftKeyDown(){
        return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
    }
	
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
		ItemStack UpgBase = new ItemStack(ItemRegistry.Upgrade, 1, 0);
		ItemStack slotUPG1 = new ItemStack(ItemRegistry.Upgrade, 1, 1);
		ItemStack slotUPG2 = new ItemStack(ItemRegistry.Upgrade, 1, 2);
		ItemStack slotUPG3 = new ItemStack(ItemRegistry.Upgrade, 1, 3);
		ItemStack slotUPG4 = new ItemStack(ItemRegistry.Upgrade, 1, 4);
		ItemStack slotUPGinfinity = new ItemStack(ItemRegistry.Upgrade, 1, 6);
		ItemStack redstoneUPG = new ItemStack(ItemRegistry.Upgrade ,1 ,5);
		
		if(this.isShiftKeyDown()){
			if(itemStack.isItemEqual(UpgBase)){
				list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tio.toolTips.UpgBase"));
			}else if(itemStack.isItemEqual(slotUPG1)){
				list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tio.toolTips.slotUPG1"));
				list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tio.toolTips.slotUPG1.usage1"));
				list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tio.toolTips.slotUPG1.usage2"));
			}else if(itemStack.isItemEqual(slotUPG2)){
				list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tio.toolTips.slotUPG2"));
				list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tio.toolTips.slotUPG2.usage1"));
				list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tio.toolTips.slotUPG2.usage2"));
			}else if(itemStack.isItemEqual(slotUPG3)){
				list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tio.toolTips.slotUPG3"));
				list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tio.toolTips.slotUPG3.usage1"));
				list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tio.toolTips.slotUPG3.usage2"));
			}else if(itemStack.isItemEqual(slotUPG4)){
				list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tio.toolTips.slotUPG4"));
				list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tio.toolTips.slotUPG4.usage1"));
				list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tio.toolTips.slotUPG4.usage2"));
			}else if(itemStack.isItemEqual(slotUPGinfinity)){
				list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tio.toolTips.slotUPGinfinity"));
				list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tio.toolTips.slotUPGinfinity.usage1"));
			}else if(itemStack.isItemEqual(redstoneUPG)){
				list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("tio.toolTips.redstoneUPG"));
				list.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("tio.toolTips.redstoneUPG.usage1"));
			}
		}else{
			list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tio.toolTips.common.holdShift"));
		}
		
		
	}

}

