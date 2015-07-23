package wais.yarncraft.geoyarn;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class GeoYarn {
	
	private Chapter currentChapter;
	private Page lastActivatedPage;
	
	private HashMap<Integer, Chapter> chapters = new HashMap<Integer, Chapter>();
	
	public GeoYarn(String filename) {
		Chapter c1 = new Chapter(this, 1);
		chapters.put(1, c1);
		chapters.put(2, new Chapter(this, 2));
		
		goToChapter(c1);
	}
	
	public void update() {
		Page p = currentChapter.getMatchingPage();
		if (p != null && !p.equals(lastActivatedPage)) {
			p.activate();
			lastActivatedPage = p;
		}
	}
	
	public static void showText(String text) {
		showText("\2478[\247cYarnCraft\2478] \247f ", text);
	}
	
	public static void showText(String prefix, String text) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(prefix + text));
	}
	
	public void goToChapter(Chapter chapter) {
		this.currentChapter = chapter;
		for (Page p : currentChapter.getPages()) {
			showText("> \247f ", p.getDescription());
		}
	}
	
	public void goToChapter(int chapterID) {
		goToChapter(chapters.get(chapterID));
	}
}
