package uk.co.strangeskies.gears.engine.sections;

public interface SectionProcessingContext {
	public void haltAndProcess(Section... sections);

	public void queue(Section... sections);
}
