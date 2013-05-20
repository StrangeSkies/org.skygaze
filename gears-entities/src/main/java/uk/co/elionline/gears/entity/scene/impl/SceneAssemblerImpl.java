package uk.co.elionline.gears.entity.scene.impl;

import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.entity.scene.Assemblage;
import uk.co.elionline.gears.entity.scene.SceneAssembler;

public class SceneAssemblerImpl implements SceneAssembler {
	@Override
	public void assemble(Assemblage assemblage, EntityManager entities) {
		new AssemblyContextImpl(assemblage, entities).assemble();
	}
}
