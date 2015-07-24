package wais.yarncraft.geoyarn.locations;

import wais.yarncraft.YarnCraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class TagLocation extends Location {
	
	String tag;
	
	public TagLocation(String tag) {
		this.tag = tag;
	}
	
	@Override
	public boolean match(EntityPlayerSP player) {
		for (String t : YarnCraft.generateTags(player)) {
			if (t.equals(tag)) {
				return true;
			}
		}
		return false;
	}

}
