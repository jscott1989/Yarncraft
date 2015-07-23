package wais.yarncraft.geoyarn;

import org.json.JSONArray;
import org.json.JSONObject;

import net.minecraft.client.entity.EntityPlayerSP;

public abstract class Location {
	public abstract boolean match(EntityPlayerSP player);
	
	
	public static String[] PARAMETERS = {};

	public static Location create(JSONObject jsonLocation){
		switch (jsonLocation.getString("type")){
			case: return location;
			default: throw new Exception();
		}
	}
}
