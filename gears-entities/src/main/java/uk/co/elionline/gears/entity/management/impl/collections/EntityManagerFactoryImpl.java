package uk.co.elionline.gears.entity.management.impl.collections;

import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.entity.management.EntityManagerFactory;

@Component
public class EntityManagerFactoryImpl implements EntityManagerFactory {
	@Override
	public EntityManager create() {
		return new EntityManagerImpl();
	}
}
