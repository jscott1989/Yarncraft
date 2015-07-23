package wais.yarncraft.geoyarn;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

import net.minecraft.client.entity.EntityPlayerSP;
import wais.yarncraft.util.Point3D;

public class Page {
	private int id;
	
	protected Chapter chapter;
	protected String story;
	protected String description;
	
	private Location[] locations;
	
	private int nextChapterID = -1;
	
	public Page(int id, Chapter chapter, String story, Location[] locations, int nextChapterID, String description) {
		this.id = id;
		this.chapter = chapter;
		this.story = story;
		this.locations = locations;
		this.nextChapterID = nextChapterID;
		this.description = description;
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
			locations.add(Location.create((JSONObject) locationIter.next()));
		}
		int nextChapterID = jsonPage.getInt("next_chapter");
		String description = jsonPage.getString("description");
		return new Page(id, chapter, story, locations.toArray(new Location[]{}), nextChapterID, description);
	}
}
