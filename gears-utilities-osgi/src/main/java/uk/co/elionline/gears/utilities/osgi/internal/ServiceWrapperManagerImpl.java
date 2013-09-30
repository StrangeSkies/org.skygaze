package uk.co.elionline.gears.utilities.osgi.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
	private final SetMultiMap<Class<?>, ManagedServiceWrapper<?>> wrappedServiceClasses;
	private final Map<ServiceWrapper<?>, ManagedServiceWrapper<?>> serviceWrappers;

	private final Map<ServiceReference<?>, WrappingServiceRegistration> wrappedServices;

	public ServiceWrapperManagerImpl() {
		wrappedServiceClasses = new HashSetMultiHashMap<>();
		serviceWrappers = new HashMap<>();

		wrappedServices = new HashMap<>();
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

		ManagedServiceWrapper<T> managedServiceWrapper = new ManagedServiceWrapper<T>(
				serviceWrapper);
		wrappedServiceClasses.add(serviceWrapper.getServiceClass(),
				managedServiceWrapper);
		serviceWrappers.put(serviceWrapper, managedServiceWrapper);

		updateManagedServiceWrapper(serviceWrapper, serviceProperties);
	}

	private <T> void removeManagedServiceWrapper(ServiceWrapper<T> serviceWrapper) {
		wrappedServiceClasses.remove(serviceWrapper.getServiceClass(),
				serviceWrappers.remove(serviceWrapper));
	}

	private void updateManagedServiceWrapper(ServiceWrapper<?> serviceWrapper,
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

		serviceWrappers.get(serviceWrapper).update(serviceRanking, hideServices);
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
			if (wrappedServices.get(serviceReference).isHiding()) {
				listeners.clear();
			}
		}
	}

	private void registerWrappingServices(ServiceReference<?> serviceReference) {
		Set<Class<?>> serviceClasses = new HashSet<>();
		try {
			for (String className : (String[]) serviceReference
					.getProperty("objectClass")) {
				serviceClasses.add(serviceReference.getBundle().loadClass(className));
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		WrappingServiceRegistration wrappingServiceRegistration = new WrappingServiceRegistration(
				serviceReference, wrappedServiceClasses.getAll(serviceClasses));

		wrappedServices.put(serviceReference, wrappingServiceRegistration);
	}

	private <T> void unregisterWrappingServices(
			ServiceReference<T> serviceReference) {
		WrappingServiceRegistration wrappingServiceRegistration = wrappedServices
				.remove(serviceReference);

		wrappingServiceRegistration.unregister();
	}

	private void updateWrappingServices(ServiceReference<?> serviceReference) {
		wrappedServices.get(serviceReference).update();
	}

	@Override
	public void find(BundleContext context, String name, String filter,
			boolean allServices, Collection<ServiceReference<?>> references) {
		Iterator<ServiceReference<?>> iterator = references.iterator();
		while (iterator.hasNext()) {
			WrappingServiceRegistration wrappingServiceRegistration = wrappedServices
					.get(iterator.next());

			if (wrappingServiceRegistration != null
					&& wrappingServiceRegistration.isHiding()) {
				iterator.remove();
			}
		}
	}
}
