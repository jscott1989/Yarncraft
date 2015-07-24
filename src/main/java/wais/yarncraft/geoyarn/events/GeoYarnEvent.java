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

	public static GeoYarnEvent create(JSONObject jsonEvent) throws InvalidGeoYarnEventException{
		String type = jsonEvent.getString("type");
		if (type.equals("create")){
			return new CreateEvent(jsonEvent.getString("structure"));
		}
		else if(type.equals("spawn")){
			return new SpawnEvent(jsonEvent.getString("mob"), jsonEvent.getInt("number"));
		}
		else if(type.equals("weather")){
			return new WeatherEvent(jsonEvent.getString("weather"));
		}
		else{
			throw new InvalidGeoYarnEventException();
		}
	}
	
	public abstract void activate();
	
}
