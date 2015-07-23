package wais.yarncraft.geoyarn;

import java.util.HashMap;

import wais.yarncraft.util.Point3D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class Chapter {
	
	private GeoYarn geoYarn;
	
	private HashMap<Integer, Page> pages = new HashMap<Integer, Page>();
	
	public Chapter(GeoYarn geoYarn, int chapterID) {
		this.geoYarn = geoYarn;
		
		if (chapterID == 1) {
			pages.put(1, new StandardPage(this, "Get in the water", "YOU FALL IN THE RIVER. IT IS WET", new Location[]{
					new TagLocation("wet")
			}, 2));
//			pages.put(1, new StandardPage(this, "Go somewhere dark", "IT IS DARK", new Location[]{
//					new TagLocation("dark")
//			}, 2));
		} else {
			pages.put(1, new FinalPage(this, "Get out of the water", "That's better!", new Location[]{
					new NotLocation(new TagLocation("wet"))
			}));
//			pages.put(2, new FinalPage(this, "Go somewhere light", "It is light again", new Location[]{
//					new TagLocation("light")
//			}));
		}
	}
	
	public GeoYarn getGeoYarn() {
		return geoYarn;
	}
	
	public Page[] getPages() {
		return pages.values().toArray(new Page[]{});
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
}
