package uk.co.elionline.gears.utilities.osgi.impl;

import java.util.Hashtable;
import java.util.Map;

class CompoundWrappedService {
	private final Object service;
	private final Map<String, Object> properties;

	private final CompoundWrappedService parent;

	private final ManagedServiceWrapper<?> wrapper;

	public CompoundWrappedService(Object service, Map<String, Object> properties) {
		this.service = service;
		this.properties = properties;

		parent = null;
		wrapper = null;
	}

	public CompoundWrappedService(Object service, Map<String, Object> properties,
			CompoundWrappedService wrappedService,
			ManagedServiceWrapper<?> serviceWrapper) {
		this.service = service;
		this.properties = properties;

		parent = wrappedService;

		wrapper = serviceWrapper;
	}

	public CompoundWrappedService getParent() {
		return parent;
	}

	public ManagedServiceWrapper<?> getWrapper() {
		return wrapper;
	}

	public Object getService() {
		return service;
	}

	public Hashtable<String, Object> getProperties() {
		return new Hashtable<>(properties);
	}
}
