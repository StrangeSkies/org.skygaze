package uk.co.elionline.gears.utilities.osgi.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

import org.osgi.framework.ServiceRegistration;

import uk.co.elionline.gears.utilities.osgi.ServiceWrapper;
import uk.co.elionline.gears.utilities.osgi.ServiceWrapper.HideServices;

class WrappedServiceSet {
	private final Object service;
	private final HashMap<String, Object> properties;

	private final Set<ManagedServiceWrapper<?>> serviceWrappers;
	private final Set<ServiceRegistration<?>> serviceRegistrations;

	private boolean hiding;

	public WrappedServiceSet(Object service, HashMap<String, Object> properties,
			Collection<? extends ManagedServiceWrapper<?>> serviceWrappers) {
		this.service = service;
		this.properties = properties;

		this.serviceWrappers = new TreeSet<>(new ManagedServiceWrapperComparator());
		this.serviceWrappers.addAll(serviceWrappers);

		serviceRegistrations = new HashSet<>();

		register();
	}

	private void register() {
		Set<WrappedService> wrappedServices = new HashSet<>();

		wrappedServices.add(new WrappedService(service));

		for (ManagedServiceWrapper<?> serviceWrapper : serviceWrappers) {
			for (WrappedService wrappedService : wrappedServices) {
				Hashtable<String, Object> properties = new Hashtable<>(
						wrappedService.getProperties());

				if (serviceWrapper.wrapServiceProperties(properties)) {
					properties.put(ServiceWrapperManagerImpl.class.getName(), true);

					Object wrappingService = serviceWrapper.wrapService(wrappedService
							.getWrappedService());

					serviceRegistrations.add(bundleContext.registerService(
							serviceWrapper.getServiceClass(), wrappingService, properties));

					if (serviceWrapper.getHideServices() == HideServices.WHEN_WRAPPED) {
						hiddenServices.add(wrappableServiceReference);
					}
				}
				if (serviceWrapper.getHideServices() == HideServices.ALWAYS) {
					hiddenServices.add(wrappableServiceReference);
				}
			}
		}
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

	public void update() {
		// TODO Auto-generated method stub

	}

	public void updateWrapper(ServiceWrapper<?> serviceWrapper) {
		// TODO Auto-generated method stub

	}

	public Set<ManagedServiceWrapper<?>> getServiceWrappers() {
		return serviceWrappers;
	}

	public boolean isHiding() {
		return hiding;
	}
}
