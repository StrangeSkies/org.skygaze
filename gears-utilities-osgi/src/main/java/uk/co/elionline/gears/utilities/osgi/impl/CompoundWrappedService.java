package uk.co.elionline.gears.utilities.osgi.impl;

import java.util.Hashtable;
import java.util.Map;

import org.osgi.framework.ServiceRegistration;

class CompoundWrappedService {
	private final Object service;
	private final Hashtable<String, Object> properties;

	private final CompoundWrappedService parent;

	private final ManagedServiceWrapper<?> wrapper;

	public CompoundWrappedService(Object service,
			Hashtable<String, Object> properties) {
		this.service = service;
		this.properties = properties;

		wrapper = null;
		parent = null;
	}

	public void register() {

	}

	public void unregister() {
		for (ManagedServiceWrapper<?> serviceWrapper : serviceWrappers) {
			serviceWrapper.unwrapService(serviceReference.getBundle()
					.getBundleContext().getService(serviceReference));
			for (ServiceRegistration<?> serviceRegistration : serviceRegistrations) {
				serviceRegistration.unregister();
			}
		}
	}

	public void update(Map<String, Object> properties) {
		// TODO Auto-generated method stub

	}

	public void updateWrapper(ManagedServiceWrapper<?> serviceWrapper) {
		// TODO Auto-generated method stub

	}

	public Object getService() {
		return service;
	}

	public Hashtable<String, Object> getProperties() {
		return properties;
	}
}
