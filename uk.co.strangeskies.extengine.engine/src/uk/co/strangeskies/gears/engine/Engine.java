package uk.co.strangeskies.gears.engine;

import uk.co.strangeskies.gears.engine.sections.SectionProcessor;

public interface Engine {
	public void launch();

	public void setGame(Game game);

	public void setSectionProcessor(SectionProcessor sectionProcessor);
}
