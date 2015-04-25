package uk.co.strangeskies.extengine.entity.assembly.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.strangeskies.extengine.entity.assembly.Assemblage;
import uk.co.strangeskies.extengine.entity.assembly.AssemblageView;
import uk.co.strangeskies.extengine.entity.assembly.Assembler;
import uk.co.strangeskies.extengine.entity.management.EntityManager;

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
