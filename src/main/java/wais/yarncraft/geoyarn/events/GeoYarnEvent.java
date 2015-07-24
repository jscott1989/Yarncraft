package wais.yarncraft.geoyarn.events;

import org.json.JSONObject;

import wais.yarncraft.geoyarn.locations.AndLocation;
import wais.yarncraft.geoyarn.locations.BiomeLocation;
import wais.yarncraft.geoyarn.locations.Location;
import wais.yarncraft.geoyarn.locations.NearbyLocation;
import wais.yarncraft.geoyarn.locations.NotLocation;
import wais.yarncraft.geoyarn.locations.PointLocation;
import wais.yarncraft.geoyarn.locations.TagLocation;
import wais.yarncraft.util.Point3D;

public abstract class GeoYarnEvent {

	public static GeoYarnEvent create(JSONObject jsonLocation) throws InvalidGeoYarnEventException{
		String type = jsonLocation.getString("type");
		if (type.equals("create")){
			return new CreateEvent(jsonLocation.getString("structure"));
		}
		else if(type.equals("spawn")){
			return new SpawnEvent(jsonLocation.getString("mob"), jsonLocation.getInt("number"));
		}
		else if(type.equals("weather")){
			return new WeatherEvent(jsonLocation.getString("weather"), jsonLocation.getInt("duration"));
		}
		else{
			throw new InvalidGeoYarnEventException();
		}
	}
	
	public abstract void activate();
	
}
