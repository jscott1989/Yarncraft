package wais.yarncraft.geoyarn;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;

import wais.yarncraft.util.Point3D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class Chapter {
	
	private int id;
	private GeoYarn geoYarn;
	
	private HashMap<Integer, Page> pages = new HashMap<Integer, Page>();
	
	public Chapter(GeoYarn geoYarn, int chapterID) {
		this.geoYarn = geoYarn;
		this.id = chapterID;
		
		if (chapterID == 1) {
			pages.put(1, new Page(1, this, "YOU FALL IN THE RIVER. IT IS WET", new Location[]{
					new BiomeLocation("River")
			}, 2));
		} else {
			pages.put(1, new Page(1, this, "That's better!", new Location[]{
					new NotLocation(new BiomeLocation("River"))
			}, -1));
		}
	}
	
	public static Chapter create(GeoYarn geoyarn, JSONObject jsonChapter){
		
		
		Chapter chapter = new Chapter(geoyarn, jsonChapter.getInt("id"));
		
		HashMap<Integer, Page> pages = new HashMap<Integer, Page>();
		
		Iterator<Object> pageIter = jsonChapter.getJSONArray("pages").iterator();
		while(pageIter.hasNext()){
			Page page = Page.create((JSONObject) pageIter.next());
			pages.put(page.getID(), page);
			
		}
	
		chapter.setPages(pages);
		
		return chapter;
	}
	
	public void addPage(Page page){
		pages.put(page.getID(), page);
	}
	
	public void setPages(HashMap<Integer, Page> pages){
		this.pages = pages;
	}
	
	public Page[] getPages() {
		return pages.values().toArray(new Page[]{});
	}
	
	public GeoYarn getGeoYarn() {
		return geoYarn;
	}
	
	public Page getMatchingPage() {
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		
		for (Page p : pages.values()) {
			if (p.matches(player)) {
				return p;
			}
		}
		return null;
	}

	public Integer getID() {
		return id;
	}
}
