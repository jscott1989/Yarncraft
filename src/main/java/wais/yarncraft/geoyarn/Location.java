package wais.yarncraft.geoyarn;

import net.minecraft.client.entity.EntityPlayerSP;

public abstract class Location {
	public abstract boolean match(EntityPlayerSP player);
}
