package uk.co.strangeskies.extengine;

import uk.co.strangeskies.extengine.sections.SectionProcessor;

public interface Engine {
	public void launch();

	public void setGame(Game game);

	public void setSectionProcessor(SectionProcessor sectionProcessor);
}
