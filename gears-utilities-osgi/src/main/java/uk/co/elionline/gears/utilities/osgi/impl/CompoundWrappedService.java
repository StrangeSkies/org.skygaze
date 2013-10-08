package uk.co.elionline.gears.utilities.osgi.impl;

import java.util.Map;
import java.util.Set;

class CompoundWrappedService extends WrappedService {
	private final WrappedService parent;

	private final ManagedServiceWrapper<?> wrapper;

	CompoundWrappedService(Object service, Map<String, Object> properties,
			Set<Class<?>> classes, WrappedService wrappedService,
			ManagedServiceWrapper<?> serviceWrapper) {
		super(service, properties, classes);

		parent = wrappedService;
		wrapper = serviceWrapper;
	}

	public WrappedService getParent() {
		return parent;
	}

	public ManagedServiceWrapper<?> getWrapper() {
		return wrapper;
	}
}
