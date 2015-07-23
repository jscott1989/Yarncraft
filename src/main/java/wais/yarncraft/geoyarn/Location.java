package wais.yarncraft.geoyarn;

import org.json.JSONArray;
import org.json.JSONObject;

import wais.yarncraft.util.Point3D;
import net.minecraft.client.entity.EntityPlayerSP;

public abstract class Location {
	public abstract boolean match(EntityPlayerSP player);
	
	
	public static String[] PARAMETERS = {};

	public static Location create(JSONObject jsonLocation) throws Exception{
		String type = jsonLocation.getString("type");
		if (type == "BiomeLocation"){
			return new BiomeLocation(jsonLocation.getString("biome"));
		}
		else if(type == "NotLocation"){
			return new NotLocation(Location.create(jsonLocation.getJSONObject("location")));
		}
		else if(type == "AndLocation"){
			return new AndLocation(Location.create(jsonLocation.getJSONObject("location1")), Location.create(jsonLocation.getJSONObject("location2")));
		}
		else if(type == "NearbyLocation"){
			return new NearbyLocation(jsonLocation.getString("nearby"), jsonLocation.getInt("distance"));
		}
		else if(type == "TagLocation"){
			return new TagLocation(jsonLocation.getString("tag"));
		}
		else if(type == "PointLocation"){
			return new PointLocation(new Point3D(jsonLocation.getDouble("x"), jsonLocation.getDouble("y"), jsonLocation.getDouble("z")));
		}
		else{
			throw new Exception();
		}
	}
}
