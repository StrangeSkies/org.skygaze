package uk.co.elionline.gears.utilities.osgi.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.hooks.service.EventListenerHook;
import org.osgi.framework.hooks.service.FindHook;
import org.osgi.framework.hooks.service.ListenerHook.ListenerInfo;
import org.osgi.service.component.annotations.Component;

import uk.co.elionline.gears.gearbox.utilities.ServiceWrapper;

@Component(service = ServiceWrapperManager.class)
public class ServiceWrapperManager implements EventListenerHook, FindHook {
	private final Map<ServiceReference<?>, ServiceRegistration<?>> wrappedServiceRegistrations;
	private final Map<Class<?>, Set<List<ServiceReference<ServiceWrapper<?>>>>> serviceWrappers;

	public ServiceWrapperManager() {
		wrappedServiceRegistrations = new HashMap<>();
		serviceWrappers = new HashMap<>();
	}

	@Override
	public void event(ServiceEvent event,
			Map<BundleContext, Collection<ListenerInfo>> listeners) {
		ServiceReference<?> serviceReference = event.getServiceReference();

		if (serviceReference.getProperty(ServiceWrapperManager.class.getName()) == null) {
			switch (event.getType()) {
			case org.osgi.framework.ServiceEvent.REGISTERED:
				registerWrappedServices(serviceReference);

				break;
			case org.osgi.framework.ServiceEvent.UNREGISTERING:
				try {
					wrappedServiceRegistrations.get(serviceReference).unregister();
				} catch (IllegalStateException | NullPointerException e) {
				}

				break;
			case org.osgi.framework.ServiceEvent.MODIFIED:
			case org.osgi.framework.ServiceEvent.MODIFIED_ENDMATCH:
				try {
					wrappedServiceRegistrations.get(serviceReference).setProperties(
							getProxiedServiceProperties(serviceReference));
				} catch (IllegalStateException | NullPointerException e) {
				}

				break;
			}

			if (wrappedServiceRegistrations.containsKey(serviceReference)) {
				listeners.clear();
			}
		}
	}

	private void registerWrappedServices(ServiceReference<?> serviceReference) {
		for (String className : (String[]) serviceReference
				.getProperty("objectClass")) {
			try {
				Class<?> serviceClass = serviceReference.getBundle().loadClass(
						className);

				registerWrappedService(serviceClass, serviceReference);
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private <T> void registerWrappedService(Class<T> serviceClass,
			ServiceReference<?> serviceReference) {
		Map<String, Object> properties = new HashMap<>();
		for (String propertyKey : serviceReference.getPropertyKeys()) {
			properties.put(propertyKey, serviceReference.getProperty(propertyKey));
		}

		T wrappedService = ((CompoundServiceWrapper<T>) serviceWrappers
				.get(serviceClass)).wrapService((T) serviceReference.getBundle()
				.getBundleContext().getService(serviceReference), properties);

		ServiceRegistration<T> serviceRegistration = serviceReference
				.getBundle()
				.getBundleContext()
				.registerService(serviceClass, wrappedService,
						getProxiedServiceProperties(serviceReference));

		wrappedServiceRegistrations.put(serviceReference, serviceRegistration);
	}

	private static Hashtable<String, Object> getProxiedServiceProperties(
			ServiceReference<?> serviceReference) {
		Hashtable<String, Object> properties = new Hashtable<>();
		for (String propertyKey : serviceReference.getPropertyKeys()) {
			properties.put(propertyKey, serviceReference.getProperty(propertyKey));
		}

		properties.put(ServiceWrapperManager.class.getName(), true);

		Integer serviceRanking;
		try {
			serviceRanking = (Integer) properties.get(Constants.SERVICE_RANKING);
			if (serviceRanking == null) {
				serviceRanking = 1;
			} else if (serviceRanking < Integer.MAX_VALUE) {
				serviceRanking += 1;
			}
		} catch (ClassCastException e) {
			serviceRanking = 1;
		}
		properties.put(Constants.SERVICE_RANKING, serviceRanking);

		return properties;
	}

	public void proxyExistingServices() {

	}

	@Override
	public void find(BundleContext context, String name, String filter,
			boolean allServices, Collection<ServiceReference<?>> references) {
		Iterator<ServiceReference<?>> iterator = references.iterator();
		while (iterator.hasNext()) {
			ServiceReference<?> serviceReference = iterator.next();

			if (wrappedServiceRegistrations.keySet().contains(serviceReference)) {
				iterator.remove();
			}
		}
	}
}
