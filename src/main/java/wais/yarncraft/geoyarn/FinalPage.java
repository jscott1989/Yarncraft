package wais.yarncraft.geoyarn;

public class FinalPage extends Page {

	public FinalPage(Chapter chapter, String description, String story, Location[] locations) {
		super(chapter, description, story, locations);
	}

	@Override
	public void activate() {
		chapter.getGeoYarn().showText(story);
		// TODO: Clean up if we've modified the world
	}

}
