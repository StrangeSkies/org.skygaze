package uk.co.elionline.gears.entity.assembly.impl;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.entity.assembly.Assemblage;
import uk.co.elionline.gears.entity.assembly.Assembler;
import uk.co.elionline.gears.entity.management.EntityManager;

@Component
public class AssemblerImpl implements Assembler {
	private final AssemblageImpl base;

	public AssemblerImpl() {
		base = new AssemblageImpl();
	}

	@Override
	public void assemble(Assemblage assemblage, EntityManager entityManager) {
		if (!isDerivedFrom(assemblage, base)) {
			throw new IllegalArgumentException();
		}
		new AssemblyContextImpl(assemblage, entityManager).assemble();
	}

	@Override
	public Assemblage base() {
		return base;
	}

	protected static boolean isDerivedFrom(Assemblage assemblage, Assemblage base) {
		do {
			if (base == assemblage) {
				return true;
			}
			assemblage = assemblage.getBase();
		} while (assemblage != null);

		return false;
	}
}
