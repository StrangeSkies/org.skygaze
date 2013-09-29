package uk.co.elionline.gears.utilities.osgi.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.hooks.service.EventListenerHook;
import org.osgi.framework.hooks.service.FindHook;
import org.osgi.framework.hooks.service.ListenerHook.ListenerInfo;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import uk.co.elionline.gears.utilities.collections.HashSetMultiHashMap;
import uk.co.elionline.gears.utilities.collections.SetMultiMap;
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
	private final SetMultiMap<Class<?>, ManagedServiceWrapper<?>> serviceWrappers;

	private final SetMultiMap<ManagedServiceWrapper<?>, ServiceReference<?>> wrappedServices;

	private final Map<ServiceReference<?>, WrappingServiceRegistration> wrappingServiceRegistrations;

	public ServiceWrapperManagerImpl() {
		serviceWrappers = new HashSetMultiHashMap<>();

		wrappedServices = new HashSetMultiHashMap<>();

		wrappingServiceRegistrations = new HashMap<>();
	}

	@Override
	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC, target = "("
			+ ServiceWrapper.WRAP_EXISTING_SERVICES + "=false)")
	public void addServiceWrapper(ServiceWrapper<?> serviceWrapper,
			Map<String, Object> serviceProperties) {
		addManagedServiceWrapper(serviceWrapper, serviceProperties);
	}

	@Override
	public void removeServiceWrapper(ServiceWrapper<?> serviceWrapper) {
		removeManagedServiceWrapper(serviceWrapper);
	}

	@Override
	public void modifyServiceWrapper(ServiceWrapper<?> serviceWrapper,
			Map<String, Object> serviceProperties) {
		updateManagedServiceWrapper(serviceWrapper, serviceProperties);
	}

	private <T> void addManagedServiceWrapper(ServiceWrapper<T> serviceWrapper,
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

		serviceWrappers.add(serviceWrapper.getServiceClass(),
				new ManagedServiceWrapper<T>(serviceWrapper, serviceRanking,
						hideServices));
	}

	private <T> void removeManagedServiceWrapper(ServiceWrapper<T> serviceWrapper) {
		serviceWrappers.remove(serviceWrapper.getServiceClass(),
				new ManagedServiceWrapper<T>(serviceWrapper, null, null));
	}

	private void updateManagedServiceWrapper(ServiceWrapper<?> serviceWrapper,
			Map<String, Object> serviceProperties) {
		for (ServiceReference<?> serviceReference : wrappedServices
				.get(serviceWrapper)) {
			wrappingServiceRegistrations.get(serviceReference).updateWrapper(
					serviceWrapper);
		}
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
				updateWrappingServices(serviceReference);

				break;
			}
			if (wrappingServiceRegistrations.containsKey(serviceReference)) {
				listeners.clear();
			}
		}
	}

	private void registerWrappingServices(ServiceReference<?> serviceReference) {
		WrappingServiceRegistration wrappingServiceRegistration = new WrappingServiceRegistration(
				serviceReference);
		wrappingServiceRegistration.register();

		wrappedServices.addToAll(wrappingServiceRegistration.getServiceWrappers(),
				serviceReference);

		wrappingServiceRegistrations.put(serviceReference,
				wrappingServiceRegistration);
	}

	private <T> void unregisterWrappingServices(
			ServiceReference<T> serviceReference) {
		WrappingServiceRegistration wrappingServiceRegistration = wrappingServiceRegistrations
				.remove(serviceReference);

		wrappedServices.removeFromAll(
				wrappingServiceRegistration.getServiceWrappers(), serviceReference);

		wrappingServiceRegistration.unregister();
	}

	private void updateWrappingServices(ServiceReference<?> serviceReference) {
		wrappingServiceRegistrations.get(serviceReference).update();
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
