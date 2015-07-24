package wais.yarncraft.geoyarn.events;

import net.minecraft.client.Minecraft;

public class WeatherEvent extends GeoYarnEvent{

	private String weather;
	
	public WeatherEvent(String weather) {
		if(weather.equals("clear") || weather.equals("rain") || weather.equals("thunder")){
			this.weather = weather;
		}
		else{
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void activate() {
		if(weather.equals("rain")){
			Minecraft.getMinecraft().theWorld.getWorldInfo().setRaining(true);
			Minecraft.getMinecraft().theWorld.rainingStrength = 2;
		}
		else if(weather.equals("thunder")){
			Minecraft.getMinecraft().theWorld.getWorldInfo().setThundering(true);
			Minecraft.getMinecraft().theWorld.rainingStrength = 2;
			Minecraft.getMinecraft().theWorld.thunderingStrength = 2;
		}
		else{
			Minecraft.getMinecraft().theWorld.getWorldInfo().setRaining(false);
			Minecraft.getMinecraft().theWorld.getWorldInfo().setThundering(false);
			
			Minecraft.getMinecraft().theWorld.rainingStrength = 0;
			Minecraft.getMinecraft().theWorld.thunderingStrength = 0;
		}
	}

}
