package uk.co.strangeskies.gears.entity.assembly;

import uk.co.strangeskies.gears.entity.management.EntityManager;
import uk.co.strangeskies.gears.utilities.Factory;

public interface Assembler extends Factory<Assemblage> {
	public void assemble(/* @ReadOnly */Assemblage assemblage,
			EntityManager entityManager);

	@Override
	public Assemblage create();
}
