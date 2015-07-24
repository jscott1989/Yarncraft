package wais.yarncraft.geoyarn.locations;

import net.minecraft.client.entity.EntityPlayerSP;
import wais.yarncraft.util.Point3D;

public class PointLocation extends Location {
	
	private Point3D point;
	private double distance;
	
	public PointLocation(Point3D point, double distance) {
		this.point = point;
		this.distance = distance;
	}
	
	public PointLocation(Point3D point) {
		this(point, 2.0);
	}
	
	@Override
	public boolean match(EntityPlayerSP player) {
		Point3D current = new Point3D(player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ);
		return (point.distance(current) <= distance);
	}

}
