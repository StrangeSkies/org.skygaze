package uk.co.elionline.gears.utilities.osgi.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
	private final Map<ServiceWrapper<?>, ManagedServiceWrapper<?>> managedServiceWrappers;

	private final SetMultiMap<ServiceReference<?>, CompoundWrappedService> wrappedServices;

	public ServiceWrapperManagerImpl() {
		wrappedServiceClasses = new HashSetMultiHashMap<>();
		managedServiceWrappers = new HashMap<>();

		wrappedServices = new HashSetMultiHashMap<>();
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
		managedServiceWrappers.put(serviceWrapper, managedServiceWrapper);

		updateManagedServiceWrapper(serviceWrapper, serviceProperties);
	}

	private void removeManagedServiceWrapper(ServiceWrapper<?> serviceWrapper) {
		wrappedServiceClasses.remove(serviceWrapper.getServiceClass(),
				managedServiceWrappers.remove(serviceWrapper));
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

		managedServiceWrappers.get(serviceWrapper).update(serviceRanking,
				hideServices); // TODO link to WrappedService
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
			if (wrappedServices.containsKey(serviceReference)) {
				listeners.clear();
			}
		}
	}

	private Map<String, Object> getProperties(ServiceReference<?> serviceReference) {
		Map<String, Object> properties = new HashMap<>();
		for (String propertyKey : serviceReference.getPropertyKeys()) {
			properties.put(propertyKey, serviceReference.getProperty(propertyKey));
		}

		return properties;
	}

	private Set<Class<?>> getClasses(ServiceReference<?> serviceReference) {
		Set<Class<?>> serviceClasses = new HashSet<>();
		try {
			for (String className : getClassNames(serviceReference)) {
				serviceClasses.add(serviceReference.getBundle().loadClass(className));
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return serviceClasses;
	}

	private String[] getClassNames(ServiceReference<?> serviceReference) {
		return (String[]) serviceReference.getProperty("objectClass");
	}

	private void registerWrappingServices(ServiceReference<?> serviceReference) {
		BundleContext context = serviceReference.getBundle().getBundleContext();

		Set<Class<?>> serviceClasses = getClasses(serviceReference);

		Set<ManagedServiceWrapper<?>> serviceWrappers = new TreeSet<>(
				new ManagedServiceWrapperComparator());
		serviceWrappers.addAll(wrappedServiceClasses.getAll(serviceClasses));

		wrappedServices.add(serviceReference,
				new CompoundWrappedService(context.getService(serviceReference),
						getProperties(serviceReference)));
		for (ManagedServiceWrapper<?> serviceWrapper : serviceWrappers) {
			for (CompoundWrappedService wrappedService : wrappedServices
					.get(serviceReference)) {
				Hashtable<String, Object> properties = new Hashtable<>(
						wrappedService.getProperties());

				if (serviceWrapper.wrapServiceProperties(properties)) {
					properties.put(ServiceWrapperManagerImpl.class.getName(), true);

					Object wrappingService = wrapService(wrappedService.getService(),
							serviceWrapper, serviceClasses);

					wrappedServices.add(serviceReference, wrappedService);
				}
			}
		}

		for (CompoundWrappedService wrappedService : wrappedServices
				.get(serviceReference)) {
			serviceRegistrations.add(context.registerService(
					getClassNames(serviceReference), wrappedService.getService(),
					wrappedService.getProperties()));
		}
	}

	private <T> Object wrapService(final Object service,
			ManagedServiceWrapper<T> serviceWrapper, Set<Class<?>> serviceClasses) {
		@SuppressWarnings("unchecked")
		final T wrappingService = serviceWrapper.wrapService((T) service);
		final Class<T> wrapperClass = serviceWrapper.getServiceClass();

		List<Class<?>> orderedServiceClasses = new ArrayList<>(serviceClasses);
		orderedServiceClasses.remove(wrapperClass);
		orderedServiceClasses.add(0, wrapperClass);

		return Proxy.newProxyInstance(null,
				orderedServiceClasses.toArray(new Class<?>[0]),
				new InvocationHandler() {
					@Override
					public Object invoke(Object object, Method method, Object[] args)
							throws Throwable {
						Class<?> declaringClass = method.getDeclaringClass();
						/*
						 * TODO this ^ isn't good enough. Need to be able to change
						 * preference to 'wrapperClass' when more than one interface
						 * contains a method.
						 * 
						 * WILL REPLACE WITH commons-proxy
						 */

						if (declaringClass == Object.class
								|| declaringClass == wrapperClass) {
							return method.invoke(wrappingService, args);
						} else {
							return method.invoke(service, args);
						}
					}
				});
	}

	private <T> void unregisterWrappingServices(
			ServiceReference<T> serviceReference) {
		Set<CompoundWrappedService> wrappedServiceSet = wrappedServices
				.remove(serviceReference);

		for (CompoundWrappedService wrappedService : wrappedServiceSet) {
			wrappedService.unregister();
		}
	}

	private void updateWrappingServices(ServiceReference<?> serviceReference) {
		wrappedServices.get(serviceReference).update();
	}

	@Override
	public void find(BundleContext context, String name, String filter,
			boolean allServices, Collection<ServiceReference<?>> references) {
		Iterator<ServiceReference<?>> iterator = references.iterator();
		while (iterator.hasNext()) {
			if (wrappedServices.containsKey(iterator.next())) {
				iterator.remove();
			}
		}
	}
}
