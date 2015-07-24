package wais.yarncraft.geoyarn.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;

public class SpawnEvent extends GeoYarnEvent {

	private Entity entity;
	private String type;
	private int number;
	private EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	
	
	public SpawnEvent(String type, int number) {
		this.type = type;
		this.number = number;
	}

	@Override
	public void activate() {
		
		for(int i=0; i<number; i++){
			if (type.equals("villager")){
				 entity = new EntityChicken(Minecraft.getMinecraft().theWorld);
			}
			entity.setPositionAndUpdate(player.posX, player.posY, player.posZ);
			Minecraft.getMinecraft().theWorld.spawnEntityInWorld(entity);
		}
	}

}
