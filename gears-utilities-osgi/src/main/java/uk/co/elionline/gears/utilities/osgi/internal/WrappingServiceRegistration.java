package uk.co.elionline.gears.utilities.osgi.internal;

import org.osgi.framework.ServiceRegistration;

class WrappingServiceRegistration<T> {
	private final ManagedServiceWrapper<T> serviceWrapper;
	private final ServiceRegistration<T> serviceRegistration;

	public WrappingServiceRegistration(ManagedServiceWrapper<T> serviceWrapper,
			ServiceRegistration<T> serviceRegistration) {
		this.serviceWrapper = serviceWrapper;
		this.serviceRegistration = serviceRegistration;
	}

	public ManagedServiceWrapper<T> getServiceWrapper() {
		return serviceWrapper;
	}

	public ServiceRegistration<T> getServiceRegistration() {
		return serviceRegistration;
	}
}
