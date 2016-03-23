package uk.co.strangeskies.extengine.entity.assembly.impl;

import uk.co.strangeskies.extengine.entity.assembly.Assemblage;
import uk.co.strangeskies.extengine.entity.assembly.AssemblageFactory;

public class AssemblageFactoryImpl implements AssemblageFactory {
	@Override
	public Assemblage create(String name) {
		return new AssemblageImpl(name);
	}
}
