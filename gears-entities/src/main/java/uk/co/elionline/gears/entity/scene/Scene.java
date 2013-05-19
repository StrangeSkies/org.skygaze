package uk.co.elionline.gears.entity.scene;

import uk.co.elionline.gears.entity.management.EntityManager;

public interface Scene {
	public void setRootAssemblage(Assemblage assemblage);

	public void assemble(EntityManager entities);
}
