package uk.co.elionline.gears.utilities.osgi.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.hooks.service.EventListenerHook;
import org.osgi.framework.hooks.service.FindHook;
import org.osgi.framework.hooks.service.ListenerHook.ListenerInfo;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import uk.co.elionline.gears.utilities.collections.SetMultiMap;
import uk.co.elionline.gears.utilities.collections.TreeSetMultiHashMap;
import uk.co.elionline.gears.utilities.osgi.ServiceWrapper;

@Component(service = { EventListenerHook.class, FindHook.class })
public class ServiceWrapperManager implements EventListenerHook, FindHook {
	private final Map<ServiceReference<?>, ServiceRegistration<?>> wrappedServiceRegistrations;
	private final SetMultiMap<Class<?>, ServiceWrapper<?, ?>> wrappedServices;

	public ServiceWrapperManager() {
		wrappedServiceRegistrations = new HashMap<>();
		wrappedServices = new TreeSetMultiHashMap<>();
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public <T> void addServiceWrapper(ServiceWrapper<T, ?> serviceWrapper,
			Map<String, Object> serviceProperties) {
		@SuppressWarnings("unchecked")
		CompoundServiceWrapper<T> compoundWrappedServiceSet = (CompoundServiceWrapper<T>) wrappedServices
				.get(serviceWrapper.getServiceClass());

		if (compoundWrappedServiceSet == null) {
			compoundWrappedServiceSet = new CompoundServiceWrapper<>();
			wrappedServices.put(serviceWrapper.getServiceClass(),
					compoundWrappedServiceSet);
		}

		int serviceRanking;
		boolean hideWrappedServices;
		try {
			serviceRanking = (Integer) serviceProperties
					.get(Constants.SERVICE_RANKING);
		} catch (ClassCastException | NullPointerException e) {
			serviceRanking = 0;
		}
		try {
			hideWrappedServices = (Boolean) serviceProperties
					.get(ServiceWrapper.HIDE_WRAPPED_SERVICES);
		} catch (ClassCastException | NullPointerException e) {
			hideWrappedServices = false;
		}
		compoundWrappedServiceSet.add(serviceWrapper, serviceRanking,
				hideWrappedServices);
	}

	public <T> void modifyServiceWrapper(ServiceWrapper<T, ?> serviceWrapper,
			Map<String, Object> serviceProperties) {
		addServiceWrapper(serviceWrapper, serviceProperties);
	}

	public <T> void removeServiceWrapper(ServiceWrapper<T, ?> serviceWrapper) {
		@SuppressWarnings("unchecked")
		CompoundServiceWrapper<T> compoundWrappedServiceSet = (CompoundServiceWrapper<T>) wrappedServices
				.get(serviceWrapper.getServiceClass());

		if (compoundWrappedServiceSet != null) {
			compoundWrappedServiceSet.remove(serviceWrapper);
		}
	}

	@Override
	public void event(ServiceEvent event,
			Map<BundleContext, Collection<ListenerInfo>> listeners) {
		ServiceReference<?> serviceReference = event.getServiceReference();

		if (serviceReference.getProperty(ServiceWrapperManager.class.getName()) == null) {
			switch (event.getType()) {
			case org.osgi.framework.ServiceEvent.REGISTERED:
				ServiceRegistration<?> serviceRegistration = registerWrappedServices(serviceReference);

				try {
					wrappedServiceRegistrations
							.put(serviceReference, serviceRegistration);
				} catch (IllegalStateException | NullPointerException e) {
				}

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
							getWrappedServiceProperties(serviceReference));
				} catch (IllegalStateException | NullPointerException e) {
				}

				break;
			}

			if (wrappedServiceRegistrations.containsKey(serviceReference)) {
				listeners.clear();
			}
		}
	}

	private ServiceRegistration<?> registerWrappedServices(
			ServiceReference<?> serviceReference) {
		String[] classNames = (String[]) serviceReference
				.getProperty("objectClass");

		if (classNames.length == 1) {
			try {
				Class<?> serviceClass = serviceReference.getBundle().loadClass(
						classNames[0]);

				return registerWrappedService(serviceClass, serviceReference);
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();

				return null;
			}
		} else {

			for (String className : classNames) {
			}
		}
	}

	private <T> ServiceRegistration<T> registerWrappedService(
			Class<T> serviceClass, ServiceReference<?> serviceReference) {
		Map<String, Object> properties = new HashMap<>();
		for (String propertyKey : serviceReference.getPropertyKeys()) {
			properties.put(propertyKey, serviceReference.getProperty(propertyKey));
		}

		@SuppressWarnings("unchecked")
		CompoundServiceWrapper<T> compoundServiceWrapper = (CompoundServiceWrapper<T>) wrappedServices
				.get(serviceClass);
		@SuppressWarnings("unchecked")
		T wrappedService = compoundServiceWrapper.wrapService((T) serviceReference
				.getBundle().getBundleContext().getService(serviceReference),
				properties);

		ServiceRegistration<T> serviceRegistration = serviceReference
				.getBundle()
				.getBundleContext()
				.registerService(serviceClass, wrappedService,
						getWrappedServiceProperties(serviceReference));
	}

	public void proxyExistingServices() {

	}

	@Override
	public void find(BundleContext context, String name, String filter,
			boolean allServices, Collection<ServiceReference<?>> references) {
		Iterator<ServiceReference<?>> iterator = references.iterator();
		while (iterator.hasNext()) {
			ServiceReference<?> serviceReference = iterator.next();

			if (wrappedServiceRegistrations.containsKey(serviceReference)) {
				iterator.remove();
			}
		}
	}
}
