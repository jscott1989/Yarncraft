package wais.yarncraft.geoyarn.locations;

import net.minecraft.client.entity.EntityPlayerSP;

public class AndLocation extends Location {
	
	private Location location1;
	private Location location2;
	
	public AndLocation(Location location1, Location location2) {
		this.location1 = location1;
		this.location2 = location2;
	}
	
	@Override
	public boolean match(EntityPlayerSP player) {
		return location1.match(player) && location2.match(player);
	}

}
