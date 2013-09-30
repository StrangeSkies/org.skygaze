package uk.co.elionline.gears.utilities.osgi.impl;

import java.util.Map;

class WrappedService {
	private final Object service;
	private final Map<String, Object> properties;
	private final ManagedServiceWrapper<?> wrapper;

	public WrappedService(Object service, Map<String, Object> properties,
			ManagedServiceWrapper<?> wrapper) {
		this.service = service;
		this.properties = properties;
		this.wrapper = wrapper;
	}

	public Map<String, Object> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getWrappedService() {
		// TODO Auto-generated method stub
		return null;
	}
}
