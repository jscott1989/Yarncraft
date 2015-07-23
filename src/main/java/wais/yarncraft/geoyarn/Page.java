package wais.yarncraft.geoyarn;

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
	
	public Page(int id, Chapter chapter, String story, Location[] locations, int nextChapterID) {
		this.id = id;
		this.chapter = chapter;
		this.story = story;
		this.locations = locations;
		this.nextChapterID = nextChapterID;
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

	public static Page create(JSONObject next) {
		return new Page(int id, Chapter chapter, String story, Location[] locations, int nextChapterID);
	}
}
