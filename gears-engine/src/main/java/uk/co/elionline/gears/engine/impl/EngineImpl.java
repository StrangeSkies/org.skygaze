package uk.co.elionline.gears.engine.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import uk.co.elionline.gears.engine.Engine;
import uk.co.elionline.gears.engine.Game;
import uk.co.elionline.gears.engine.sections.SectionProcessor;

@Component(service = Engine.class, immediate = true)
public class EngineImpl implements Engine {
	private Game game;
	private SectionProcessor sectionProcessor;

	@Activate
	public void launch() {
		getSectionProcessor().process(getGame().getEntranceSection());
	}

	public Game getGame() {
		return game;
	}

	@Reference(service = Game.class)
	public void setGame(Game game) {
		this.game = game;
	}

	public void unsetGame(Game game) {
		this.game = null;
	}

	public SectionProcessor getSectionProcessor() {
		return sectionProcessor;
	}

	@Reference(service = SectionProcessor.class)
	public void setSectionProcessor(SectionProcessor sectionProcessor) {
		this.sectionProcessor = sectionProcessor;
	}

	public void unsetSectionProcessor(SectionProcessor sectionProcessor) {
		this.sectionProcessor = null;
	}
}
