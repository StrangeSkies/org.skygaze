package uk.co.strangeskies.extengine.entity.assembly;

import uk.co.strangeskies.extengine.entity.management.EntityManager;
import uk.co.strangeskies.utilities.factory.Factory;

public interface Assembler extends Factory<Assemblage> {
	public void assemble(/* @ReadOnly */AssemblageView assemblage,
			EntityManager entityManager);
}
