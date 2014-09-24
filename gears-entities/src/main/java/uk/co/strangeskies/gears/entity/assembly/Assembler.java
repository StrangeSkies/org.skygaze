package uk.co.strangeskies.gears.entity.assembly;

import uk.co.strangeskies.gears.entity.management.EntityManager;

public interface Assembler {
	public void assemble(/* @ReadOnly */AssemblageView assemblage,
			EntityManager entityManager);
}
