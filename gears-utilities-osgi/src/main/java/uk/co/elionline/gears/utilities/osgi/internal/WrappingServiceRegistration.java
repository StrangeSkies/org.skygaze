package uk.co.elionline.gears.utilities.osgi.internal;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import uk.co.elionline.gears.utilities.osgi.ServiceWrapper.HideServices;

class WrappingServiceRegistration {
	private final Set<ManagedServiceWrapper<?>> serviceWrappers;
	private final ServiceReference<?> serviceReference;
	private final Set<ServiceRegistration<?>> serviceRegistrations;

	public WrappingServiceRegistration(ServiceReference<?> serviceReference) {
		this.serviceWrappers = new TreeSet<>();
		this.serviceReference = serviceReference;
		serviceRegistrations = new HashSet<>();

		try {
			Set<Class<?>> serviceClasses = new HashSet<>();
			for (String className : (String[]) serviceReference
					.getProperty("objectClass")) {
				serviceClasses.add(serviceReference.getBundle().loadClass(className));
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public void register() {
		this.serviceWrappers.clear();
		this.serviceWrappers.addAll(serviceWrappers);
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
}
