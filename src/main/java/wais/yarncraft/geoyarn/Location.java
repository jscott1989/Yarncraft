package wais.yarncraft.geoyarn;

import org.json.JSONArray;
import org.json.JSONObject;

import net.minecraft.client.entity.EntityPlayerSP;

public abstract class Location {
	public abstract boolean match(EntityPlayerSP player);

	public static Location create(JSONObject jsonLocation){
//		Location location = null;
//		try {
//			Class classDefinition = Class.forName(className);
//			location = (Location) classDefinition.newInstance();
//		} catch (InstantiationException e) {
//			System.out.println(e);
//		} catch (IllegalAccessException e) {
//			System.out.println(e);
//		} catch (ClassNotFoundException e) {
//			System.out.println(e);
//		}
//		return location;
		return new TagLocation("wet");
	}
}
