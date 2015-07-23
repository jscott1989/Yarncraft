package wais.yarncraft;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GeoYarnReceiver {
	
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
    public void eventHandler(RenderGameOverlayEvent event) {
		// TODO: Figure out what event this should attach to
		YarnCraft.geoYarn.update();
	}
	
	@SubscribeEvent
	public void onConnectedToServerEvent(EntityJoinWorldEvent event) {
		if (event.entity.getClass().getName().equals("net.minecraft.client.entity.EntityPlayerSP")) {
			YarnCraft.init();
		}
	}
}
