package wais.yarncraft.geoyarn.locations;

import org.json.JSONArray;
import org.json.JSONObject;

import wais.yarncraft.util.Point3D;
import net.minecraft.client.entity.EntityPlayerSP;

public abstract class Location {
	public abstract boolean match(EntityPlayerSP player);
	
	
	public static String[] PARAMETERS = {};

	public static Location create(JSONObject jsonLocation) throws Exception{
		String type = jsonLocation.getString("type");
		if (type.equals("BiomeLocation")){
			return new BiomeLocation(jsonLocation.getString("biome"));
		}
		else if(type.equals("NotLocation")){
			return new NotLocation(Location.create(jsonLocation.getJSONObject("location")));
		}
		else if(type.equals("AndLocation")){
			return new AndLocation(Location.create(jsonLocation.getJSONObject("location1")), Location.create(jsonLocation.getJSONObject("location2")));
		}
		else if(type.equals("NearbyLocation")){
			return new NearbyLocation(jsonLocation.getString("nearby"), jsonLocation.getInt("distance"));
		}
		else if(type.equals("TagLocation")){
			return new TagLocation(jsonLocation.getString("tag"));
		}
		else if(type.equals("PointLocation")){
			return new PointLocation(new Point3D(jsonLocation.getDouble("x"), jsonLocation.getDouble("y"), jsonLocation.getDouble("z")));
		}
		else{
			throw new Exception();
		}
	}
}
