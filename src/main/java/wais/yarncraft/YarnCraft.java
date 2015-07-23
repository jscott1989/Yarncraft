package wais.yarncraft;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import wais.yarncraft.geoyarn.GeoYarn;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = YarnCraft.MODID, name = YarnCraft.NAME, version = YarnCraft.VERSION)
public class YarnCraft
{
    public static final String MODID = "yarncraft";
    public static final String NAME = "YarnCraft";
    public static final String VERSION = "1.0";
    
    public static GeoYarn geoYarn;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
	    GeoYarnReceiver g = new GeoYarnReceiver();
		MinecraftForge.EVENT_BUS.register(g);
	}
	
	public static void init() {
		 try {
			geoYarn = new GeoYarn("story.json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Block getBlock(double x, double y, double z) {
		return getBlockState(x, y, z).getBlock();
	}
	
	private static IBlockState getBlockState(double x, double y, double z) {
		return Minecraft.getMinecraft().theWorld.getBlockState(new BlockPos(x, y, z));
	}
	
	private static Material getMaterial(double x, double y, double z) {
		return getBlockState(x, y, z).getBlock().getMaterial();
	}
	
	private static double getLightLevel(double x, double y, double z) {
		int coreX = MathHelper.floor_double(x);
		int coreY = MathHelper.floor_double(y);
		int coreZ = MathHelper.floor_double(z);
		
		int totalLight = 0;
		int totalBlocks = 0;
		for (int xx = coreX - 5; x <= coreX + 5; x++) {
			for (int yy = coreY - 5; y <= coreY + 5; y++) {
				for (int zz = coreZ - 5; z <= coreZ + 5; z++) {
					if (!getBlockState(xx, yy, zz).getBlock().getUseNeighborBrightness()) {
						totalLight += getBlockState(xx, yy, zz).getBlock().getLightValue();
						totalBlocks += 1;
					}
				}
			}
		}
		return (double)totalLight / totalBlocks;
	}
    
    public static String[] generateTags(EntityPlayerSP player) {
    	int AREA_SIZE = 3;
    	
    	ArrayList<String> tags = new ArrayList<String>();
    	
    	HashMap<Material, Integer> counter = new HashMap<Material, Integer>();
    	
    	for (double x = player.lastTickPosX - AREA_SIZE; x < player.lastTickPosX + AREA_SIZE; x++) {
    		for (double y = player.lastTickPosY - AREA_SIZE; y < player.lastTickPosY + AREA_SIZE; y++) {
    			for (double z = player.lastTickPosZ - AREA_SIZE; z < player.lastTickPosZ + AREA_SIZE; z++) {
    				Material m = getMaterial(x, y, z);
    				if (counter.containsKey(m)) {
    					counter.put(m, counter.get(m) + 1);
    				} else {
    					counter.put(m, 1);
    				}
    	    	}	
        	}
    	}
    	
    	
    	// wet: Is the player in water?
    	if (getMaterial(player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ) == Material.water) {
    		tags.add("wet");
    	}
    	
//    	int DARK_VALUE = 3;
//    	int LIGHT_VALUE = 12;
//    	
//    	double lightValue = getLightLevel(player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ);
//    	if (lightValue < DARK_VALUE) {
//    		tags.add("dark");
//    	} else if (lightValue > LIGHT_VALUE) {
//    		tags.add("light");
//    	}
    	return tags.toArray(new String[]{});
    }
}
