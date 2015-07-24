package wais.yarncraft.geoyarn.events;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class SpawnEvent extends GeoYarnEvent {

	private Entity entity;
	private String type;
	private int number;
	private EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	private Random rand = new Random();
	
	public SpawnEvent(String type, int number) {
		this.type = type;
		this.number = number;
	}

	@Override
	public void activate() {
		
		for(int i=0; i<number; i++){
			if (type.equals("villager")){
				 entity = new EntityVillager(Minecraft.getMinecraft().theWorld);
			}
			entity.setPositionAndUpdate(player.posX + randomOffset(), player.posY, player.posZ+ randomOffset());
			Minecraft.getMinecraft().theWorld.spawnEntityInWorld(entity);
			((EntityLiving) entity).setAIMoveSpeed((float)0.5);
		}
	}
	
	public int randomOffset(){
		return rand.nextInt(11) - 5;
	}

}
