package uk.co.elionline.gears.entity.scene.impl;

import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.entity.scene.Assemblage;
import uk.co.elionline.gears.entity.scene.Scene;

public class SceneImpl implements Scene {
	private Assemblage root;

	@Override
	public void setRootAssemblage(Assemblage assemblage) {
		root = assemblage;
	}

	@Override
	public void assemble(EntityManager entities) {
		new AssemblyContextImpl(root).assemble(entities);
	}
}
