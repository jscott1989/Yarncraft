package wais.yarncraft.geoyarn;

import net.minecraft.client.entity.EntityPlayerSP;

public class NearbyLocation extends Location {
	
	private String nearby;
	private int distance;
	
	public NearbyLocation(String nearby, int distance) {
		this.nearby = nearby;
		this.distance = distance;
	}
	
	@Override
	public boolean match(EntityPlayerSP player) {
		return false;
	}

}
