package uk.co.elionline.gears.utilities.osgi.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import uk.co.elionline.gears.utilities.osgi.ServiceWrapper;
import uk.co.elionline.gears.utilities.osgi.ServiceWrapper.HideServices;

class WrappingServiceRegistration {
	private final Set<ManagedServiceWrapper<?>> serviceWrappers;
	private final Set<ServiceRegistration<?>> serviceRegistrations;

	public WrappingServiceRegistration(Object serviceReference,
			Collection<? extends ManagedServiceWrapper<?>> serviceWrappers) {
		this.serviceWrappers = new TreeSet<>(new ManagedServiceWrapperComparator());
		this.serviceWrappers.addAll(serviceWrappers);

		serviceRegistrations = new HashSet<>();

		register();
	}

	private void register() {
		serviceRegistrations.clear();

		Set<ServiceReference<?>> hiddenServices;

		Set<ServiceReference<?>> wrappableServiceReferences = new HashSet<>();
		wrappableServiceReferences.add(serviceReference);

		for (ManagedServiceWrapper<?> serviceWrapper : serviceWrappers) {
			for (ServiceReference<?> wrappableServiceReference : wrappableServiceReferences) {
				Hashtable<String, Object> properties = getProperties(wrappableServiceReference);

				if (serviceWrapper.wrapServiceProperties(properties)) {
					properties.put(ServiceWrapperManagerImpl.class.getName(), true);

					BundleContext bundleContext = wrappableServiceReference.getBundle()
							.getBundleContext();

					Object wrappingService = serviceWrapper.wrapService(bundleContext
							.getService(wrappableServiceReference));

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

	private Hashtable<String, Object> getProperties(
			ServiceReference<?> serviceReference) {
		Hashtable<String, Object> properties = new Hashtable<>();
		for (String propertyKey : serviceReference.getPropertyKeys()) {
			properties.put(propertyKey, serviceReference.getProperty(propertyKey));
		}

		return properties;
	}

	public Set<ManagedServiceWrapper<?>> getServiceWrappers() {
		return serviceWrappers;
	}

	public boolean isHiding() {
		return false;
	}
}
