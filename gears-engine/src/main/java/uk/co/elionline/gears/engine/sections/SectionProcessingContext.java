package uk.co.elionline.gears.engine.sections;

public interface SectionProcessingContext {
	public void haltAndProcess(Section... sections);

	public void queue(Section... sections);
}
