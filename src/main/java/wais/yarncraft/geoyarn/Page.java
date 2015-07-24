package wais.yarncraft.geoyarn;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

import net.minecraft.client.entity.EntityPlayerSP;
import wais.yarncraft.geoyarn.events.GeoYarnEvent;
import wais.yarncraft.geoyarn.locations.Location;
import wais.yarncraft.util.Point3D;

public class Page {
	private int id;
	
	private Chapter chapter;
	private String story;
	private String description;
	private GeoYarnEvent event;
	private Location[] locations;
	
	private int nextChapterID = -1;
	
	public Page(int id, Chapter chapter, String story, Location[] locations, int nextChapterID, String description, GeoYarnEvent event) {
		this.id = id;
		this.chapter = chapter;
		this.story = story;
		this.locations = locations;
		this.nextChapterID = nextChapterID;
		this.description = description;
		this.event = event;
	}
	
	public Page(int id, Chapter chapter, String story, Location[] locations, int nextChapterID, String description) {
		this(id, chapter, story, locations, nextChapterID, description, null);
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
		if (event != null){
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
		return new Page(id, chapter, story, locations.toArray(new Location[]{}), nextChapterID, description);
	}
}
