package wais.yarncraft.geoyarn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class GeoYarn {
	
	private Chapter currentChapter;
	private Page lastActivatedPage;
	
	private HashMap<Integer, Chapter> chapters = new HashMap<Integer, Chapter>();
	
	public GeoYarn(String filename) throws IOException {
		
		String text = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
		createChapters(new JSONArray(text));
		goToChapter(1);
	}
	
	private void createChapters(JSONArray jsonChapters){
		Iterator<Object> chapterIter = jsonChapters.iterator();
			while(chapterIter.hasNext()){
				
				JSONObject jsonChapter = (JSONObject) chapterIter.next();
			
				Chapter chapter = Chapter.create(this, jsonChapter);
				chapters.put(chapter.getID(), chapter);
		}
		 
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
