package uk.co.elionline.gears.utilities.osgi.impl;

import java.util.Hashtable;
import java.util.Set;

public abstract class WrappedService {
	private final Object service;
	private final Hashtable<String, Object> properties;

	private final boolean visible;

	public WrappedService(Object service, Hashtable<String, Object> properties) {
		this.service = service;
		this.properties = properties;

		visible = false;
	}

	public Object getService() {
		return service;
	}

	public Hashtable<String, Object> getProperties() {
		return properties;
	}

	public abstract Set<CompoundWrappedService> getWrappedServices();

	public boolean isVisible() {
		return visible;
	}
}
