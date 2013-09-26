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

import uk.co.elionline.gears.utilities.collections.ArrayListMultiHashMap;
import uk.co.elionline.gears.utilities.collections.ListMultiMap;
import uk.co.elionline.gears.utilities.collections.SetMultiMap;
import uk.co.elionline.gears.utilities.collections.TreeSetMultiHashMap;
import uk.co.elionline.gears.utilities.osgi.ServiceWrapper;
import uk.co.elionline.gears.utilities.osgi.ServiceWrapper.HideServices;
import uk.co.elionline.gears.utilities.osgi.ServiceWrapperManager;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
@Component(service = { EventListenerHook.class, FindHook.class })
public class ServiceWrapperManagerImpl implements ServiceWrapperManager {
	private final ListMultiMap<ServiceReference<?>, WrappingServiceRegistration> wrappingServiceRegistrations;

	private final SetMultiMap<Class<?>, ManagedServiceWrapper> serviceWrappers;

	public ServiceWrapperManagerImpl() {
		wrappingServiceRegistrations = new ArrayListMultiHashMap<>();
		serviceWrappers = new TreeSetMultiHashMap<>();
	}

	@Override
	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC, target = "("
			+ ServiceWrapper.WRAP_EXISTING_SERVICES + "=false)")
	public void addServiceWrapper(ServiceWrapper<?> serviceWrapper,
			Map<String, Object> serviceProperties) {
		try {
			if ((Boolean) serviceProperties
					.get(ServiceWrapper.WRAP_EXISTING_SERVICES) == true) {
				throw new IllegalArgumentException(
						ServiceWrapper.WRAP_EXISTING_SERVICES + " = true @ "
								+ serviceWrapper);
			}
		} catch (ClassCastException e) {
		}

		int serviceRanking;
		try {
			serviceRanking = (Integer) serviceProperties
					.get(Constants.SERVICE_RANKING);
		} catch (ClassCastException | NullPointerException e) {
			serviceRanking = 0;
		}

		HideServices hideServices;
		try {
			hideServices = HideServices.valueOf((String) serviceProperties
					.get(ServiceWrapper.HIDE_SERVICES));
		} catch (ClassCastException | NullPointerException e) {
			hideServices = HideServices.NEVER;
		}

		serviceWrappers
				.add(serviceWrapper.getServiceClass(), new ManagedServiceWrapper(
						serviceWrapper, serviceRanking, hideServices));
	}

	@Override
	public void modifyServiceWrapper(ServiceWrapper<?> serviceWrapper,
			Map<String, Object> serviceProperties) {
		addServiceWrapper(serviceWrapper, serviceProperties);
	}

	@Override
	public void removeServiceWrapper(ServiceWrapper<?> serviceWrapper) {
		serviceWrappers.remove(serviceWrapper.getServiceClass(),
				new ManagedServiceWrapper(serviceWrapper, null, null));
	}

	@Override
	public void event(ServiceEvent event,
			Map<BundleContext, Collection<ListenerInfo>> listeners) {
		ServiceReference<?> serviceReference = event.getServiceReference();

		if (serviceReference.getProperty(ServiceWrapperManagerImpl.class.getName()) == null) {
			switch (event.getType()) {
			case ServiceEvent.REGISTERED:
				registerWrappingServices(serviceReference);

				break;
			case ServiceEvent.UNREGISTERING:
				unregisterWrappingServices(serviceReference);

				break;
			case ServiceEvent.MODIFIED:
			case ServiceEvent.MODIFIED_ENDMATCH:
				try {
					wrappingServiceRegistrations.get(serviceReference).setProperties(
							getWrappedServiceProperties(serviceReference));
				} catch (IllegalStateException | NullPointerException e) {
				}

				break;
			}
			if (wrappingServiceRegistrations.containsKey(serviceReference)) {
				listeners.clear();
			}
		}
	}

	private void registerWrappingServices(ServiceReference<?> serviceReference) {
		String[] classNames = (String[]) serviceReference
				.getProperty("objectClass");

		if (classNames.length == 1) {
			try {
				Class<?> serviceClass = serviceReference.getBundle().loadClass(
						classNames[0]);

				registerWrappedService(serviceClass, serviceReference);

				wrappingServiceRegistrations.addAll(serviceReference, null);
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		} else {

			for (String className : classNames) {
			}
		}

	}

	private String[] getClassNames(ServiceReference<?> serviceReference) {
		return (String[]) serviceReference.getProperty("objectClass");
	}

	private <T> ServiceRegistration<T> registerWrappedService(
			Class<T> serviceClass, ServiceReference<?> serviceReference) {
		Map<String, Object> properties = new HashMap<>();
		for (String propertyKey : serviceReference.getPropertyKeys()) {
			properties.put(propertyKey, serviceReference.getProperty(propertyKey));
		}

		@SuppressWarnings("unchecked")
		CompoundServiceWrapper<T> compoundServiceWrapper = (CompoundServiceWrapper<T>) serviceWrappers
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

	private <T> void unregisterWrappingServices(
			ServiceReference<T> serviceReference) {
		try {
			for (WrappingServiceRegistration<T> wrappingServiceRegistration : wrappingServiceRegistrations
					.get(serviceReference)) {
				wrappingServiceRegistration
						.getServiceWrapper()
						.getServiceWrapper()
						.unwrapService(
								serviceReference.getBundle().getBundleContext()
										.getService(serviceReference));
				wrappingServiceRegistration.getServiceRegistration().unregister();
			}
		} catch (IllegalStateException | NullPointerException e) {
		}
	}

	@Override
	public void find(BundleContext context, String name, String filter,
			boolean allServices, Collection<ServiceReference<?>> references) {
		Iterator<ServiceReference<?>> iterator = references.iterator();
		while (iterator.hasNext()) {
			ServiceReference<?> serviceReference = iterator.next();

			WrappedServiceRegistrationGroup wrappedServiceRegistrationGroup = wrappingServiceRegistrations
					.get(serviceReference);
			if (wrappedServiceRegistrationGroup != null) {
				iterator.remove();
			}
		}
	}
}
