package wais.yarncraft.geoyarn;

import net.minecraft.client.entity.EntityPlayerSP;

public class NearbyLocation extends Location {
	
	public NearbyLocation(String nearby, int distance) {
		
	}
	
	@Override
	public boolean match(EntityPlayerSP player) {
		return false;
	}

}
