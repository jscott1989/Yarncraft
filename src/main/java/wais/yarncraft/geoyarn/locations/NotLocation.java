package wais.yarncraft.geoyarn.locations;

import net.minecraft.client.entity.EntityPlayerSP;

public class NotLocation extends Location {
	
	private Location location;
	
	public NotLocation(Location location) {
		this.location = location;
	}
	
	@Override
	public boolean match(EntityPlayerSP player) {
		return !location.match(player);
	}

}
