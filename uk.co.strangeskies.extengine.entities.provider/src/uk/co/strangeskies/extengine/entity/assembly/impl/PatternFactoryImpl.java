package uk.co.strangeskies.extengine.entity.assembly.impl;

import uk.co.strangeskies.extengine.entity.assembly.Pattern;
import uk.co.strangeskies.extengine.entity.assembly.PatternFactory;

public class PatternFactoryImpl implements PatternFactory {
	@Override
	public Pattern create(String name) {
		return new PatternImpl(name);
	}
}
