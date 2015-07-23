package wais.yarncraft.geoyarn;

import org.json.JSONArray;

import net.minecraft.client.entity.EntityPlayerSP;

public abstract class Location {
	public abstract boolean match(EntityPlayerSP player);

	public static Location[] create(JSONArray jsonArray) {
		Location[] locs = new Location[1];
		locs[0] = new TagLocation("wet");
		return locs;
	}
}
