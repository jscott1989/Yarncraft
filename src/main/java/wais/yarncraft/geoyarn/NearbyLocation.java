package wais.yarncraft.geoyarn;

import wais.yarncraft.YarnCraft;
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
		// Look in the area surrounding the player for the object specified by "nearby"
//		for (int x = (int)player.lastTickPosX - distance; x < (int)player.lastTickPosX + distance; x++) {
//			for (int y = (int)player.lastTickPosY - distance; y < (int)player.lastTickPosY + distance; y++) {
//				for (int z = (int)player.lastTickPosZ - distance; z < (int)player.lastTickPosZ + distance; z++) {
////					Item i = YarnCraft.getBlock(x, y, z).getItem(Minecraft.getMinecraft().theWorld, new BlockPos(x, y, z));
////					System.out.println(x);
//				}
//			}
//		}
//		
		return false;
	}

}
