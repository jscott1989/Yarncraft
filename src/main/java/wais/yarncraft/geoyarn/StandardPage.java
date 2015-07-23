package wais.yarncraft.geoyarn;

public class StandardPage extends Page {
	
	private int nextChapterID;

	public StandardPage(Chapter chapter, String description, String story, Location[] locations, int nextChapterID) {
		super(chapter, description, story, locations);
		this.nextChapterID = nextChapterID;
	}

	@Override
	public void activate() {
		chapter.getGeoYarn().showText(story);
		chapter.getGeoYarn().goToChapter(nextChapterID);
	}

}
