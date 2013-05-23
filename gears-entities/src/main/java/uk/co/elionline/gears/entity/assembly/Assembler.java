package uk.co.elionline.gears.entity.assembly;

import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.utilities.Factory;

public interface Assembler extends Factory<Assemblage> {
	public void assemble(/* @ReadOnly */Assemblage assemblage,
			EntityManager entityManager);

	@Override
	public Assemblage create();
}
