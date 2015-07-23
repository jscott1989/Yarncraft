package wais.yarncraft.geoyarn;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;

public class BiomeLocation extends Location {
	
	private String biomeName;
	
	public BiomeLocation(String biomeName) {
		this.biomeName = biomeName;
	}
	
	@Override
	public boolean match(EntityPlayerSP player) {
		return (Minecraft.getMinecraft().theWorld.getBiomeGenForCoords(new BlockPos(player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ)).biomeName == biomeName);
	}
	
}
