package uk.co.elionline.gears.utilities.osgi.impl;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class CompoundWrappedService extends WrappedService {
	private final WrappedService parent;

	private final ManagedServiceWrapper<?> wrapper;

	CompoundWrappedService(Object service, Map<String, Object> properties,
			Set<Class<?>> classes, WrappedService wrappedService,
			ManagedServiceWrapper<?> serviceWrapper) {
		super(service, new Hashtable<>(properties));

		parent = wrappedService;
		wrapper = serviceWrapper;
	}

	public WrappedService getParent() {
		return parent;
	}

	public ManagedServiceWrapper<?> getWrapper() {
		return wrapper;
	}

	@Override
	public Set<CompoundWrappedService> getWrappedServices() {
		// TODO Auto-generated method stub
		return null;
	}
}
