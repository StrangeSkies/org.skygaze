package uk.co.strangeskies.extengine.entity.assembly;

import java.net.URL;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.scheduling.Scheduler;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.modabi.SchemaManager;
import uk.co.strangeskies.reflection.TypeToken;

public abstract class CatalogueResource implements Catalogue {
	@Reference
	SchemaManager manager;

	private final String name;
	private final Package namespace;
	private final String extension;

	private Catalogue component;

	public CatalogueResource(String name, String extension) {
		this.name = name;
		this.namespace = getClass().getPackage();
		this.extension = extension;
	}

	public void initialise(SchemaManager manager) {
		this.manager = manager;
		initialise();
	}

	@Activate
	private void initialise() {
		String packageLocation = getClass().getPackage().getName().replaceAll(".", "/") + "/";
		URL location = getClass().getResource(packageLocation + name + "." + extension);

		component = manager.bind(Catalogue.SCHEMA_NAME, new TypeToken<Catalogue>() {}).from(location).resolve();

		if (component.getName() != null && !component.getName().equals(name)) {
			throw new IllegalArgumentException(
					"Resource name '" + name + "' does not match declared catalogue name '" + component.getName() + "'");
		}

		if (component.getNamespace() != null && !component.getNamespace().equals(namespace)) {
			throw new IllegalArgumentException("Resource location '" + namespace
					+ "' does not match declared catalogue namespace '" + component.getNamespace() + "'");
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Package getNamespace() {
		return namespace;
	}

	@Override
	public Set<Catalogue> getDependencies() {
		return component.getDependencies();
	}

	@Override
	public Set<BehaviourComponent> getBehaviours() {
		return component.getBehaviours();
	}

	@Override
	public Set<StateComponent<?, ?>> getStates() {
		return component.getStates();
	}

	@Override
	public Set<Scheduler> getSchedulers() {
		return component.getSchedulers();
	}

	@Override
	public Set<Pattern> getPatterns() {
		return component.getPatterns();
	}
}
