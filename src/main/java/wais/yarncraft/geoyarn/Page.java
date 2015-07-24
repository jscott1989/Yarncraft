package wais.yarncraft.geoyarn;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import net.minecraft.client.entity.EntityPlayerSP;
import wais.yarncraft.geoyarn.events.GeoYarnEvent;
import wais.yarncraft.geoyarn.events.InvalidGeoYarnEventException;
import wais.yarncraft.geoyarn.events.WeatherEvent;
import wais.yarncraft.geoyarn.locations.Location;
import wais.yarncraft.util.Point3D;

public class Page {
	private int id;
	
	private Chapter chapter;
	private String story;
	private String description;
	private GeoYarnEvent[] events;
	private Location[] locations;
	
	private int nextChapterID = -1;
	
	public Page(int id, Chapter chapter, String story, Location[] locations, int nextChapterID, String description, GeoYarnEvent[] events) {
		this.id = id;
		this.chapter = chapter;
		this.story = story;
		this.locations = locations;
		this.nextChapterID = nextChapterID;
		this.description = description;
		this.events = events;
	}
	
	public Page(int id, Chapter chapter, String story, Location[] locations, int nextChapterID, String description) {
		this(id, chapter, story, locations, nextChapterID, description, new GeoYarnEvent[]{});
	}

	public static Page create(JSONObject jsonPage, Chapter chapter) {
		System.out.println(jsonPage);
		
		int id = jsonPage.getInt("id");
		String story = jsonPage.getString("content");
		
		ArrayList<Location> locations = new ArrayList<Location>();
		Iterator<Object> locationIter = jsonPage.getJSONArray("locations").iterator();
		while(locationIter.hasNext()){
			try {
				locations.add(Location.create((JSONObject) locationIter.next()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int nextChapterID = jsonPage.getInt("next_chapter");
		String description = jsonPage.getString("description");
		
		ArrayList<GeoYarnEvent> events = new ArrayList<GeoYarnEvent>();
		try{
			Iterator<Object> eventIter = jsonPage.getJSONArray("events").iterator();
			while(eventIter.hasNext()){
				try {
					events.add(GeoYarnEvent.create((JSONObject) eventIter.next()));
				} catch (InvalidGeoYarnEventException e) {
					e.printStackTrace();
				}
			}
		}
		catch (JSONException e){
			e.printStackTrace();
		}
		
		return new Page(id, chapter, story, locations.toArray(new Location[]{}), nextChapterID, description, events.toArray(new GeoYarnEvent[]{}));
	}
	
	public boolean matches(EntityPlayerSP player) {
		for (Location location : locations) {
			if (location.match(player)) {
				return true;
			}
		}
		return false;
	}
	
	public void activate(){
		chapter.getGeoYarn().showText(story);
		for (GeoYarnEvent event : events){
			event.activate();
		}
		if (nextChapterID != -1){
			chapter.getGeoYarn().goToChapter(nextChapterID);
		}
		else{
			//TODO: CLean up world etc.
		}
	}
	
	public String getDescription() {
		return description;
	}

	public Integer getID() {
		return id;
	}
}
