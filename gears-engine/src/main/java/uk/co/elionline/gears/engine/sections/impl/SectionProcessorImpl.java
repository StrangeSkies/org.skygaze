package uk.co.elionline.gears.engine.sections.impl;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.engine.sections.Section;
import uk.co.elionline.gears.engine.sections.SectionProcessingContext;
import uk.co.elionline.gears.engine.sections.SectionProcessor;

@Component(service = SectionProcessor.class)
public class SectionProcessorImpl implements SectionProcessor {
	private final Deque<Queue<Section>> sectionStack;

	public SectionProcessorImpl() {
		sectionStack = new ArrayDeque<>();
	}

	@Override
	public void process(Section... sections) {
		Queue<Section> sectionQueue = new ArrayDeque<>();
		for (Section section : sections) {
			sectionQueue.add(section);
		}
		sectionStack.push(sectionQueue);
		while (!sectionQueue.isEmpty()) {
			final Section processing = sectionQueue.poll();
			processing.enter(new SectionProcessingContext() {
				@Override
				public void queue(Section... sections) {
					sectionStack.peek().addAll(Arrays.asList(sections));
				}

				@Override
				public void haltAndProcess(Section... sections) {
					processing.halt();
					process(sections);
					processing.resume();
				}
			});
		}
		sectionStack.pop();
	}
}
