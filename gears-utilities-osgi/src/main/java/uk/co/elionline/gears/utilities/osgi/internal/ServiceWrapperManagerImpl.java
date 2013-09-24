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
import uk.co.elionline.gears.utilities.osgi.ServiceWrapperManager;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
@Component(service = { EventListenerHook.class, FindHook.class })
public class ServiceWrapperManagerImpl implements ServiceWrapperManager {
	private final Map<ServiceReference<?>, ServiceRegistration<?>> wrappedServiceRegistrations;
	private final SetMultiMap<Class<?>, ServiceWrapper<?>> wrappedServices;

	public ServiceWrapperManagerImpl() {
		wrappedServiceRegistrations = new HashMap<>();
		wrappedServices = new TreeSetMultiHashMap<>();
	}

	@Override
	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public void addServiceWrapper(ServiceWrapper<?> serviceWrapper,
			Map<String, Object> serviceProperties) {
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
					.get(ServiceWrapper.HIDE_WRAPPED_SERVICE_TYPES);
		} catch (ClassCastException | NullPointerException e) {
			hideWrappedServices = false;
		}

		addServiceWrapper(serviceWrapper, serviceRanking, hideWrappedServices);
	}

	public <T> void addServiceWrapper(ServiceWrapper<T> serviceWrapper,
			int serviceRanking, boolean hideWrappedServices) {
		@SuppressWarnings("unchecked")
		CompoundServiceWrapper<T> compoundWrappedServiceSet = (CompoundServiceWrapper<T>) wrappedServices
				.get(serviceWrapper.getServiceClass());

		if (compoundWrappedServiceSet == null) {
			compoundWrappedServiceSet = new CompoundServiceWrapper<>();
			wrappedServices.put(serviceWrapper.getServiceClass(),
					compoundWrappedServiceSet);
		}

		compoundWrappedServiceSet.add(serviceWrapper, serviceRanking,
				hideWrappedServices);
	}

	@Override
	public void modifyServiceWrapper(ServiceWrapper<?> serviceWrapper,
			Map<String, Object> serviceProperties) {
		addServiceWrapper(serviceWrapper, serviceProperties);
	}

	@Override
	public void removeServiceWrapper(ServiceWrapper<?> serviceWrapper) {
		@SuppressWarnings("unchecked")
		CompoundServiceWrapper<?> compoundWrappedServiceSet = wrappedServices
				.get(serviceWrapper.getServiceClass());

		if (compoundWrappedServiceSet != null) {
			compoundWrappedServiceSet.remove(serviceWrapper);
		}
	}

	@Override
	public void event(ServiceEvent event,
			Map<BundleContext, Collection<ListenerInfo>> listeners) {
		ServiceReference<?> serviceReference = event.getServiceReference();

		if (serviceReference.getProperty(ServiceWrapperManagerImpl.class.getName()) == null) {
			switch (event.getType()) {
			case ServiceEvent.REGISTERED:
				ServiceRegistration<?> serviceRegistration = registerWrappedServices(serviceReference);

				try {
					wrappedServiceRegistrations
							.put(serviceReference, serviceRegistration);
				} catch (IllegalStateException | NullPointerException e) {
				}

				break;
			case ServiceEvent.UNREGISTERING:
				try {
					wrappedServiceRegistrations.get(serviceReference).unregister();
				} catch (IllegalStateException | NullPointerException e) {
				}

				break;
			case ServiceEvent.MODIFIED:
			case ServiceEvent.MODIFIED_ENDMATCH:
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
