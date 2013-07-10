package uk.co.elionline.gears.entity.assembly.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.entity.assembly.Assemblage;
import uk.co.elionline.gears.entity.assembly.Assembler;
import uk.co.elionline.gears.entity.management.EntityManager;

@Component
public class AssemblerImpl implements Assembler {
	@Override
	public void assemble(Assemblage assemblage, EntityManager entityManager) {
		new AssemblyContextImpl(assemblage, entityManager).assemble();
	}

	@Override
	public Assemblage create() {
		return new AssemblageImpl();
	}
}
