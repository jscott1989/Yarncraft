package wais.yarncraft.geoyarn;

import java.lang.reflect.Field;
import java.util.HashMap;

import wais.yarncraft.YarnCraft;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;

public class NearbyLocation extends Location {
	
	private String nearby;
	private int distance;
	
	public NearbyLocation(String nearby, int distance) {
		this.nearby = nearby;
		this.distance = distance;
	}
	
	@Override
	public boolean match(EntityPlayerSP player) {
		// Check if there is a material with the same name as "Nearby"
		for (Field field : Material.class.getDeclaredFields()) {
		    if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
		        if (field.getName().equalsIgnoreCase(nearby)) {
		        	// We're looking for a material
		        	HashMap<Material, Integer> m = YarnCraft.countAroundPlayer(player, distance);
		        	try {
						return m.getOrDefault(field.get(null), 0) > 0;
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }
		}
		
		// Next check for items
		for (int x = (int)player.lastTickPosX - distance; x <= (int)player.lastTickPosX + distance; x++) {
			for (int y = (int)player.lastTickPosY - distance; y <= (int)player.lastTickPosY + distance; y++) {
				for (int z = (int)player.lastTickPosZ - distance; z <= (int)player.lastTickPosZ + distance; z++) {
					Item i = YarnCraft.getBlock(x, y, z).getItem(Minecraft.getMinecraft().theWorld, new BlockPos(x, y, z));
					if (i != null) {
						if (i.getUnlocalizedName().equals(nearby)) {
							return true;
						}
					}
				}
			}
		}
		
		distance = 2;
		//
		for (int x = (int)player.lastTickPosX - distance; x <= (int)player.lastTickPosX + distance; x++) {
			for (int y = (int)player.lastTickPosY - distance; y <= (int)player.lastTickPosY + distance; y++) {
				for (int z = (int)player.lastTickPosZ - distance; z <= (int)player.lastTickPosZ + distance; z++) {
					Item i = YarnCraft.getBlock(x, y, z).getItem(Minecraft.getMinecraft().theWorld, new BlockPos(x, y, z));
					if (i != null && i.getUnlocalizedName().equals("tile.enchanmentTable")) {
						System.out.println(i.getUnlocalizedName());
					}
				}
			}
		}
		
		
		return false;
	}

}
