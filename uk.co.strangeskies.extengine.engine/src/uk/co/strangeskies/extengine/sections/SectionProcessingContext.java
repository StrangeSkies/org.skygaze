package uk.co.strangeskies.extengine.sections;

public interface SectionProcessingContext {
	public void haltAndProcess(Section... sections);

	public void queue(Section... sections);
}
