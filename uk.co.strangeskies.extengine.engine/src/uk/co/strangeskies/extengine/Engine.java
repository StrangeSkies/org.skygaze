package uk.co.strangeskies.extengine;

import java.time.LocalDate;

import uk.co.strangeskies.extengine.sections.SectionProcessor;
import uk.co.strangeskies.modabi.Namespace;

public interface Engine {
	static final Namespace NAMESPACE = new Namespace(Engine.class.getPackage(), LocalDate.of(2016, 04, 10));

	void launch();

	void setGame(Game game);

	void setSectionProcessor(SectionProcessor sectionProcessor);
}
