package uk.co.strangeskies.gears.entity.assembly.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.strangeskies.gears.entity.assembly.Assemblage;
import uk.co.strangeskies.gears.entity.assembly.AssemblageView;
import uk.co.strangeskies.gears.entity.assembly.Assembler;
import uk.co.strangeskies.gears.entity.management.EntityManager;

@Component
public class AssemblerImpl implements Assembler {
	@Override
	public void assemble(AssemblageView assemblage, EntityManager entityManager) {
		new AssemblyContextImpl(assemblage, entityManager).assemble();
	}

	@Override
	public Assemblage create() {
		return new AssemblageImpl();
	}
}
