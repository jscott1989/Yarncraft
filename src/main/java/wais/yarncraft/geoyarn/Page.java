package wais.yarncraft.geoyarn;

import net.minecraft.client.entity.EntityPlayerSP;
import wais.yarncraft.util.Point3D;

public abstract class Page {
	protected Chapter chapter;
	protected String story;
	protected String description;
	
	private Location[] locations;
	
	public Page(Chapter chapter, String description, String story, Location[] locations) {
		this.description = description;
		this.chapter = chapter;
		this.story = story;
		this.locations = locations;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean matches(EntityPlayerSP player) {
		for (Location location : locations) {
			if (location.match(player)) {
				return true;
			}
		}
		return false;
	}
	
	public abstract void activate();
}
