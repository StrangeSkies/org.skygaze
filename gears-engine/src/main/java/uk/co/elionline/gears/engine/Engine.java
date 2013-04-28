package uk.co.elionline.gears.engine;

import uk.co.elionline.gears.engine.sections.SectionProcessor;

public interface Engine {
	public void launch();

	public void setGame(Game game);

	public void setSectionProcessor(SectionProcessor sectionProcessor);
}
