package uk.co.strangeskies.gears.entity.assembly;

import uk.co.strangeskies.gears.entity.management.EntityManager;
import uk.co.strangeskies.gears.utilities.factory.Factory;

public interface Assembler extends Factory<Assemblage> {
	public void assemble(/* @ReadOnly */AssemblageView assemblage,
			EntityManager entityManager);

	@Override
	public Assemblage create();
}
