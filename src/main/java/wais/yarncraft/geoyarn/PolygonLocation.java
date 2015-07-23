package wais.yarncraft.geoyarn;

import wais.yarncraft.util.Point3D;
import wais.yarncraft.util.Polygon3D;
import net.minecraft.client.entity.EntityPlayerSP;

public class PolygonLocation extends Location {
	
	private Polygon3D polygon;
	
	public PolygonLocation(Polygon3D polygon) {
		this.polygon = polygon;
	}

	@Override
	public boolean match(EntityPlayerSP player) {
		Point3D current = new Point3D(player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ);
		return polygon.contains(current);
	}

}
