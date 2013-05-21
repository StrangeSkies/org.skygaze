package uk.co.elionline.gears.entity.assembly;

import uk.co.elionline.gears.entity.management.EntityManager;

public interface Assembler {
	public void assemble(Assemblage assemblage, EntityManager entityManager);

	public/* @ReadOnly */Assemblage base();
}
